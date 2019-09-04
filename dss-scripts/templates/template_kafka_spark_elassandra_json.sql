/*
 * Copyright 2019 Infosys Ltd.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
INSERT  INTO tbl_template(tmplte_id,tmplte_file_four,tmplte_file_one,tmplte_file_three,tmplte_file_two,tmplte_prcs_type,tmplte_sink_type,tmplte_src_type,tmplte_name,tmplte_data_type,tmplte_lookup_type,tmplte_algorithm_type)
VALUES (1,NULL, '#!/usr/bin/python
from __future__ import print_function

import sys
from pyspark.context import SparkContext
from pyspark.sql import SQLContext
from pyspark.sql import Row
from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils
from pyspark.conf import SparkConf
import pyspark.sql.types as pst
import json
from cassandra import ConsistencyLevel
from cassandra.cluster import Cluster
from cassandra.query import SimpleStatement
import logging

log = logging.getLogger()
log.setLevel(\'DEBUG\')
handler = logging.StreamHandler()
handler.setFormatter(logging.Formatter("%(asctime)s [%(levelname)s] %(name)s: %(message)s"))
log.addHandler(handler)

prototype = $DATA_PROTOTYPE$

$DEFINE_USER_FUNC$

def getSqlContextInstance(sparkContext):
    if (\'sqlContextSingletonInstance\' not in globals()):
    	sqlContext = SQLContext(sparkContext)
    	$REGISTER_USER_FUNC$
        globals()[\'sqlContextSingletonInstance\'] = sqlContext
    return globals()[\'sqlContextSingletonInstance\']

def df_from_rdd(rdd, prototype, sql):
    
    schema = infer_schema(prototype)
    row_rdd = rdd.map(lambda x: _rowify(x, prototype))
    return sql.createDataFrame(row_rdd, schema)
	
def infer_schema(rec):
    
    if isinstance(rec, dict):
        return pst.StructType([pst.StructField(key, infer_schema(value), True)
                              for key, value in sorted(rec.items())])
    elif isinstance(rec, list):
        if len(rec) == 0:
            raise ValueError("can\'t infer type of an empty list")
			
	elem_type = infer_schema(rec[0])
        for elem in rec:
            this_type = infer_schema(elem)
            if elem_type != this_type:
                raise ValueError("can\'t infer type of a list with inconsistent elem types")
        return pst.ArrayType(elem_type)
    else:
        return pst._infer_type(rec)

		
def _rowify(x, prototype):
    
    def _equivalent_types(x, y):
        if type(x) in [str, unicode] and type(y) in [str, unicode]:
            return True
        return isinstance(x, type(y)) or isinstance(y, type(x))

    if x is None:
        return None
    elif isinstance(prototype, dict):
        if type(x) != dict:
            raise ValueError("expected dict, got %s instead" % type(x))
        rowified_dict = {}
        for key, val in x.items():
            if key not in prototype:
                raise ValueError("got unexpected field %s" % key)
            rowified_dict[key] = _rowify(val, prototype[key])
            for key in prototype:
                if key not in x:
                    raise ValueError(
                        "expected %s field but didn\'t find it" % key)
        return Row(**rowified_dict)
    elif isinstance(prototype, list):
        if type(x) != list:
            raise ValueError("expected list, got %s instead" % type(x))
        return [_rowify(e, prototype[0]) for e in x]
    else:
        if not _equivalent_types(x, prototype):
            raise ValueError("expected %s, got %s instead" %
                             (type(prototype), type(x)))
        return x
	
$CREATE_TABLE_FUNC$
	
$PROCESS_LOGIC_FUNC$
	
if __name__ == "__main__":
	
	log.info("Start Application...")
	zkQuorum = "$ZOOKEEPER_LIST$"
	kafkaTopic = "$TOPIC_NAME$"
	kafkaGroup = "$KAFKA_GROUP$"
	numThread = $NUM_THREAD$
	readOffset = "$READ_OFFSET$"
	sinkNode = "$SINK_NODELST$"
	execMode = "$EXEC_MODE$"
	appName = "$SPARK_APPNAME$"
			
	conf = SparkConf()
	conf.setMaster(execMode)
	conf.setAppName(appName)
	conf.set("spark.cassandra.connection.host", sinkNode)
	
	$ADDITIONAL_PARAMETERS$
	
	sc = SparkContext(conf=conf)
	ssc = StreamingContext(sc, $SPARK_BATCH_INTERVAL$)
	ssc.checkpoint("$CHECK_POINT_DIR$")
	
	log.info("Creating tables...")
	createTables()
	
	log.info("Start Streaming...")
	kafkaStream = KafkaUtils.createStream(ssc, zkQuorum, kafkaGroup, {kafkaTopic: numThread})
	
	$PARSING_LOGIC$
	
	$ITERATIVE_LOGIC$
	
	ssc.start()
	ssc.awaitTermination()',NULL,NULL,'spark','elassandra','kafka',NULL,'json',NULL,NULL);