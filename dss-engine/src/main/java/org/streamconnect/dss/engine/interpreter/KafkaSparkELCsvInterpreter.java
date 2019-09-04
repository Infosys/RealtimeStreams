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
package org.streamconnect.dss.engine.interpreter;

import java.util.List;
import java.util.Map;


import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import org.streamconnect.dss.engine.prefab.InterpreterType;
import org.streamconnect.dss.engine.prefab.StreamFlowInterpreter;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.InterpreterException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import org.streamconnect.dss.util.PropReader;
import org.streamconnect.dss.util.Util;
import org.streamconnect.dss.dto.*;


/**
 * Kafka-Spark-Elassandra-Csv data pipeline Interpreter for Python is created
 * here
 *
 */

@Service
public class KafkaSparkELCsvInterpreter extends StreamFlowInterpreter {

    /** The logger. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(KafkaSparkELCsvInterpreter.class);

    /**
     * default constructor.
     */
    public KafkaSparkELCsvInterpreter() {

    }

    /**
     * parameterized constructor.
     *
     * @param features
     *            the features
     */
    public KafkaSparkELCsvInterpreter(final String features) {
        LOGGER.info("Listed the Global Features - " + this.getClass() + "  "
                + features);
    }

    /**
     * parameterized constructor.
     *
     * @param objPipeline
     *            the obj pipeline
     */
    public KafkaSparkELCsvInterpreter(final PipelineConfigDto objPipeline) {
        pipelineConf = objPipeline;
    }

    /**
     * Method to set pre-requisites for data pipeline.
     *
     * @param objPipelineConf
     *            the obj pipeline conf
     * @return the prerequisite
     */
    public boolean getPrerequisite(final PipelineConfigDto objPipelineConf) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : getPrerequisite function : "
                + Constants.START_STATUS);
        // TODO business logic
        boolean bRet = false;
        try {
            pipelineConf = objPipelineConf;
            InterpreterType objInterPreType = new InterpreterType();
            objInterPreType
                    .setStrSourceType(((SourceDto) pipelineConf.getObjSource())
                            .getStrSourceType());
            objInterPreType.setStrProcessType(
                    ((ProcessDto) pipelineConf.getObjProcess())
                            .getStrProcessType());
            objInterPreType.setStrSinkType(
                    ((SinkDto) pipelineConf.getObjSink()).getStrSinkType());
            objInterPreType.setStrDataType(
                    pipelineConf.getStrDataSchema().getStrSchemaType());

            bRet = pipeline.setInterpreterTemplate(objInterPreType);

        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in getPrerequisite function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : getPrerequisite function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method to create/build data pipeline source related configurations &
     * settings.
     *
     * @return true, if successful
     */
    public boolean buildSource() {
        // TODO business logic
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : buildSource function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            KafkaStreamSrcDto objSrcConfig = mapper.readValue(
                    gson.toJson(((SourceDto) pipelineConf.getObjSource())
                            .getObjSourceConfigDetails()),
                    KafkaStreamSrcDto.class);
            pipeline.setInterpreterProcess("$ZOOKEEPER_LIST$",
                    objSrcConfig.getStrZookeeperlst());
            pipeline.setInterpreterProcess("$TOPIC_NAME$",
                    objSrcConfig.getStrTopicName());
            pipeline.setInterpreterProcess("$KAFKA_GROUP$",
                    objSrcConfig.getStrGroupName());
            pipeline.setInterpreterProcess("$NUM_THREAD$",
                    Integer.toString(objSrcConfig
                            .getNumThread())/* objSrcConfig.getNumThread() */);
            pipeline.setInterpreterProcess("$READ_OFFSET$",
                    objSrcConfig.getStrOffset());

            bRet = true;

        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in buildSource function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : buildSource function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * create/build data pipeline engine/process related configurations &
     * settings.
     *
     * @return true, if successful
     */
    public boolean buildEngine() {
        // TODO business logic
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : buildEngine function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            SparkProcessDto objEngConf = mapper
                    .readValue(
                            gson.toJson(
                                    ((ProcessDto) pipelineConf.getObjProcess())
                                            .getObjConfigDetails()),
                            SparkProcessDto.class);

            pipeline.setInterpreterProcess("$SPARK_APPNAME$",
                    pipelineConf.getStrPipelineName());
            pipeline.setInterpreterProcess("$SPARK_BATCH_INTERVAL$",
                    Long.toString(objEngConf.getnBatchInterval()));
            pipeline.setInterpreterProcess("$EXEC_MODE$",
                    objEngConf.getStrSparkMaster());

            String additionalSparkConfigParameters = "";
            for (KeyValueDto additionalParams : objEngConf.getAddlParams()) {
                additionalSparkConfigParameters = additionalSparkConfigParameters
                        + "conf.set(\"" + additionalParams.getKey() + "\", \""
                        + additionalParams.getValue() + "\")\n\t";
            }
            LOGGER.info("additionalSparkConfigParameters = "
                    + additionalSparkConfigParameters);
            pipeline.setInterpreterProcess("$ADDITIONAL_PARAMETERS$",
                    additionalSparkConfigParameters);
            String checkPointDir = PropReader
                    .getPropertyValue(Constants.SPARK_CHECKPOINT_DIR)
                    + pipelineConf.getStrPipelineName();
            LOGGER.info("checkPointDir = " + checkPointDir);
            pipeline.setInterpreterProcess("$CHECK_POINT_DIR$", checkPointDir);

            bRet = true;
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in buildEngine function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : buildEngine function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * create/build data pipeline process logic.
     *
     * @return true, if successful
     */
    public boolean buildProcess() {
        // TODO business logic
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : buildProcess function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            if (genParsingLogic() && genIterativeLogic() && genProcessLogic()) {
                bRet = true;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in buildProcess function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : buildProcess function : "
                + Constants.END_STATUS);
        return bRet;
    }

    /**
     * Method to generate process logic.
     *
     * @return boolean
     */
    public boolean genProcessLogic() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : genProcessLogic function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {

            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);

            Object sourceSchemaObj =pipelineConf.getStrDataSchema()
                    .getObjSchema();
            String schemaString = getDataSchema(sourceSchemaObj);
            if (pipelineConf.getStrDataSchema() != null && pipelineConf
                    .getStrDataSchema().getObjSampleData() != null) {
                pipeline.setInterpreterProcess("$DATA_PROTOTYPE$",
                        schemaString);

            }

            buildUserFunctionLogic();
            String strCode = "";
            String auditKeySpace = "";
            Map<String, List<BusinessSqlDto>> mapWindowLst = getWindowBasedList(
                    pipelineConf.getObjBusinessSqlDto());
            if (mapWindowLst != null && !mapWindowLst.isEmpty()) {
                for (Map.Entry<String, List<BusinessSqlDto>> entry : mapWindowLst
                        .entrySet()) {
                    strCode += "\ndef processfunc" + entry.getKey()
                            + "(time,rdd):\n\t";
                    strCode += "if(rdd.count() > 0):\n\t\t";
                    strCode += "try:\n\t\t\t";
                    strCode += "saverawdata(rdd)\n\t\t\t";
                    strCode += "sqlContext = getSqlContextInstance(rdd.context)\n\t\t\t";

                    // Object sourceSchemaObj =
                    // pipelineConf.getStrDataSchema().getObjSchema();
                    // String schemaString = getDataSchema(sourceSchemaObj);
                    // strCode +="prototype = "+schemaString+"\n\t\t\t";
                    strCode += "df0 = sqlContext.createDataFrame(rdd,prototype)\n\t\t\t";
                    // strCode += "df0 = df_from_rdd(rdd, prototype,
                    // sqlContext)\n\t\t\t";
                    List<BusinessSqlDto> objBusiSql = entry.getValue();
                    if (objBusiSql.size() > 0) {
                        for (int nIndex = 0; nIndex < objBusiSql
                                .size(); nIndex++) {
                            BusinessSqlDto objSql = objBusiSql.get(nIndex);
                            int nPred = objSql.getInPredecessor();

                            if (0 == nIndex) {
                                strCode += "df" + Integer.toString(nIndex)
                                        + ".registerTempTable(\""
                                        + pipelineConf.getStrInitialTableName()
                                        + "\")\n\t\t\t";
                            }

                            //Query format change
                            String strQuery = objSql.getStrQuery();
                            if(strQuery.startsWith("select * from ")){
                                //Generate Process Query
                                String processQuery = genProcessQuery(sourceSchemaObj);
                                strCode += "df" + Integer.toString(nIndex + 1)
                                        + " = sqlContext.sql(\""
                                        + processQuery + "\")\n\t\t\t";
                            }else {

                                strCode += "df" + Integer.toString(nIndex + 1)
                                        + " = sqlContext.sql(\""
                                        + objSql.getStrQuery() + "\")\n\t\t\t";
                            }


                            strCode += "df" + Integer.toString(nIndex + 1)
                                    + ".registerTempTable(\""
                                    + objSql.getStrTableName() + "\")\n\t\t\t";

                            auditKeySpace = "audit_" + pipelineConf.getStrPipelineName();

                            strCode += "prDf = sqlContext.sql('select uid from "+pipelineConf.getStrInitialTableName()+"')\n\t\t\t";
                            strCode += "tempid = str(uuid.uuid4())[:5]\n\t\t\t";
                            strCode += "fetch_rule_name = '"+objSql.getStrBusinessRule()+"'\n\t\t\t";
                            strCode += "process_query = '"+strQuery+"'\n\t\t\t";
                            strCode += "records_processed = df1.count()\n\t\t\t";
                            strCode += "prDf = prDf.withColumn('pr_time',lit(created_time))\n\t\t\t";
                            strCode += "prDf = prDf.withColumn('pr_rule',lit(fetch_rule_name))\n\t\t\t";
                            strCode += "prDf = prDf.withColumn('pr_query',lit(process_query))\n\t\t\t";
                            strCode += "prDf = prDf.withColumn('pr_count',lit(records_processed))\n\t\t\t";
                            strCode += "prDf = prDf.withColumn('pr_id',lit(tempid))\n\t\t\t";
                            strCode += "prDf.write.format(\"org.apache.spark.sql.cassandra\").mode('append').options(table=\"tbl_process_info\", keyspace=\""+auditKeySpace+"\").save()\n\t\t\t";




                            if (objSql.isSave()) {
                                QuerySinkDto querySinkDetails = objSql
                                        .getQuerySinkDetails();
                                if ("cassandra".equalsIgnoreCase(
                                        querySinkDetails.getStrPersitTo())
                                        || "elassandra".equalsIgnoreCase(
                                        querySinkDetails
                                                .getStrPersitTo())) {
                                    CassandraPersists cassandraPersists = mapper
                                            .readValue(
                                                    gson.toJson(querySinkDetails
                                                            .getPersitConfig()),
                                                    CassandraPersists.class);
                                    strCode += "df"
                                            + Integer.toString(nIndex + 1)
                                            + ".write.format(\"org.apache.spark.sql.cassandra\").mode('append').options(table=\""
                                            + objSql.getStrTableName()
                                            + "\", keyspace=\""
                                            + cassandraPersists.getStrKeySpace()
                                            + "\").save()\n\t\t\t";
                                }

                            }

                            if (objSql.isCache()) {
                            }
                        }

                    }

                    strCode += "\n\t\texcept Exception as e:\n\t\t\t";
                    strCode += "print(\"Exception occurred while processing\")\n\t\t\t";
                    strCode += "print(e.message)\n\t\t\t";
                    strCode +=  "saveerrordata(rdd,'Error in Process Layer :'+e.message)\n\t\t\t";
                    strCode += "\n\t\tfinally:\n\t\t\t";
                    strCode += "print(\"Final loop\")\n\t\t\t";
                }
            }

            pipeline.setInterpreterProcess("$PROCESS_LOGIC_FUNC$", strCode);
            bRet = true;

        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in genProcessLogic function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : genProcessLogic function : "
                + Constants.END_STATUS);
        return bRet;
    }

    /**
     * Method to generate parsing logic.
     *
     * @return boolean
     */
    public boolean genParsingLogic() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : genParsingLogic function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            Object sourceSchemaObj = pipelineConf.getStrDataSchema()
                    .getObjSchema();
            String rddSchemaString = getRDDType(sourceSchemaObj);
            String strCode = "";
            // Get Schema Delimiter for Data Parsing
            String schemaDelimiter = pipelineConf.getStrDataSchema()
                    .getStrSchemaDelimitor();
            if (pipelineConf.getStrDataSchema().getStrSchemaType()
                    .equalsIgnoreCase("delimited")) {
                // strCode += "parsedStream = kafkaStream.map(lambda csv : csv[1]).map(lambda row:row.split(\""
                // + schemaDelimiter + "\"))" + rddSchemaString + "\n";
                //Test Code
                //str(uuid.uuid4())[:5]
                strCode += "parsedStream = kafkaStream.map(lambda csv : csv[1]).map(lambda row:row.split(\""
                        + schemaDelimiter + "\"))" +rddSchemaString + "\n";
            }
            // kafkaStream.map(lambda csv : csv[1]).map(lambda
            // row:row.split(","))
            pipeline.setInterpreterProcess("$PARSING_LOGIC$", strCode);

            bRet = true;
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in genParsingLogic function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : genParsingLogic function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method to generate iterative logic.
     *
     * @return boolean
     */
    public boolean genIterativeLogic() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : genIterativeLogic function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            String strCode = "";
            if (pipelineConf.getStrDataSchema().getStrSchemaType()
                    .equalsIgnoreCase("delimited")) {
                Map<String, List<BusinessSqlDto>> mapWindowLst = getWindowBasedList(
                        pipelineConf.getObjBusinessSqlDto());
                if (mapWindowLst != null && !mapWindowLst.isEmpty()) {
                    for (Map.Entry<String, List<BusinessSqlDto>> entry : mapWindowLst
                            .entrySet()) {
                        String strProcessFunc = "processfunc" + entry.getKey();
                        if ("NoWindow".equalsIgnoreCase(entry.getKey())) {
                            strCode += "parsedStream.foreachRDD("
                                    + strProcessFunc + ")\n\t";
                        } else {
                            strCode += "process" + entry.getKey()
                                    + " = parsedStream.window("
                                    + entry.getValue().get(0).getWindowPeriod()
                                    + ", " + entry.getValue().get(0)
                                    .getSlidingInterval()
                                    + ")\n\t";
                            strCode += "process" + entry.getKey()
                                    + ".foreachRDD(" + strProcessFunc + ")\n\t";
                        }
                    }
                }
            }

            pipeline.setInterpreterProcess("$ITERATIVE_LOGIC$", strCode);
            bRet = true;
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in genIterativeLogic function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : genIterativeLogic function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method to create/build data pipeline sink part.
     *
     * @return true, if successful
     */
    public boolean buildSink() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : buildSink function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            CassandraSinkDto objSinkConf = mapper.readValue(
                    gson.toJson(((SinkDto) pipelineConf.getObjSink())
                            .getObjSinkConfigDetails()),
                    CassandraSinkDto.class);

            if (genSinkTableLogic()) {
                pipeline.setInterpreterProcess("$SINK_NODELST$",
                        objSinkConf.getStrNodelst());
                bRet = true;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in buildSink function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : buildSink function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method to retrieve data pipeline executable.
     *
     * @return the data pipeline
     */
    public PipelineDetails getDataPipeline() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : getDataPipeline function : "
                + Constants.START_STATUS);
        PipelineDetails retPipeDtls = new PipelineDetails();
        try {
            retPipeDtls = pipeline
                    .generatePipeline(pipelineConf.getStrPipelineName());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in getDataPipeline function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : getDataPipeline function : "
                + Constants.END_STATUS);
        return retPipeDtls;

    }

    /**
     * Method to create tables.
     *
     * @param strDBName
     *            the str DB name
     * @param strPersistTo
     *            the str persist to
     * @param strPrimaryKey
     *            the str primary key
     * @param objBSql
     *            the obj B sql
     * @return String
     */
    private String createTableQry(final String strDBName, final String strPersistTo,
                                  final String strPrimaryKey, final BusinessSqlDto objBSql,final String strTimetoLive) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : createTableQry function : "
                + Constants.START_STATUS);
        String strRet = "";
        try {

            //String ttlValue = PropReader.getPropertyValue(Constants.CASSANDRA_TIME_TO_LIVE);
            //String ttlValue =

            strRet += "session_" + strPersistTo
                    + ".execute(\" CREATE TABLE IF NOT EXISTS " + strDBName
                    + "." + objBSql.getStrTableName() + " (";
            List<EntitySchema> objTableSchm = objBSql.getSchemaList();
            String strSchm = "";
            String strPriKey = "";
            for (int nSInd = 0; nSInd < objTableSchm.size(); nSInd++) {
                if (objTableSchm.get(nSInd).isSelected()) {
                    strSchm += objTableSchm.get(nSInd).getName() + " "
                            + objTableSchm.get(nSInd).getType() + ",";
                }
            }
            strRet += strSchm;
            strRet += " PRIMARY KEY(" + strPrimaryKey
                    + ") ) with default_time_to_live =" + strTimetoLive + "\" )\n\t";

        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in createTableQry function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : createTableQry function : "
                + Constants.START_STATUS);
        return strRet;
    }

    /**
     * Method to create index.
     *
     * @param strDBName
     *            the str DB name
     * @param strPersistTo
     *            the str persist to
     * @param strIndexKey
     *            the str index key
     * @param objBSql
     *            the obj B sql
     * @return String
     */
    private String createIndexQry(final String strDBName, final String strPersistTo,
                                  final String strIndexKey, final BusinessSqlDto objBSql) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : createIndexQry function : "
                + Constants.START_STATUS);
        String strRet = "";
        try {
            strRet = "session_" + strPersistTo
                    + ".execute(\" CREATE INDEX IF NOT EXISTS ON " + strDBName
                    + "." + objBSql.getStrTableName() + " ( " + strIndexKey
                    + " ) " + "\")\n\t";

        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in createIndexQry function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : createIndexQry function : "
                + Constants.END_STATUS);
        return strRet;
    }

    /**
     * Method to generate sink table logic.
     *
     * @return boolean
     */
    public boolean genSinkTableLogic() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : genSinkTableLogic function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            String strCode = "def createTables():\n\t";
            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);

            List<BusinessSqlDto> objBQry = (List<BusinessSqlDto>) pipelineConf
                    .getObjBusinessSqlDto();
            boolean cassandradb = false;
            boolean elassandradb = false;
            for (int nIndex = 0; nIndex < objBQry.size(); nIndex++) {
                BusinessSqlDto objBSql = objBQry.get(nIndex);
                QuerySinkDto querySinkDetails = objBSql.getQuerySinkDetails();
                String strDBName = "", strPersistTo = "", strPrimaryKey = "",
                        strIndexKey = "";
                String strTimetoLive = "";
                Map<String, String> objAddlParams = null;
                if ("cassandra"
                        .equalsIgnoreCase(querySinkDetails.getStrPersitTo())
                        || "elassandra".equalsIgnoreCase(
                        querySinkDetails.getStrPersitTo())) {
                    CassandraPersists cassandraPersists = mapper.readValue(
                            gson.toJson(querySinkDetails.getPersitConfig()),
                            CassandraPersists.class);
                    String strCluster = formatClusterDetails(
                            cassandraPersists.getStrNodelst());
                    if (!cassandradb && "cassandra".equalsIgnoreCase(
                            querySinkDetails.getStrPersitTo())) {
                        cassandradb = true;
                        strCode += "cluster_"
                                + querySinkDetails.getStrPersitTo()
                                + " = Cluster([" + strCluster + "])\n\t";
                        strCode += "session_"
                                + querySinkDetails.getStrPersitTo()
                                + " = cluster_"
                                + querySinkDetails.getStrPersitTo()
                                + ".connect()\n\t";
                    } else if (!elassandradb && "elassandra".equalsIgnoreCase(
                            querySinkDetails.getStrPersitTo())) {
                        elassandradb = true;
                        strCode += "cluster_"
                                + querySinkDetails.getStrPersitTo()
                                + " = Cluster([" + strCluster + "])\n\t";
                        strCode += "session_"
                                + querySinkDetails.getStrPersitTo()
                                + " = cluster_"
                                + querySinkDetails.getStrPersitTo()
                                + ".connect()\n\t";
                    }
                    strCode += "session_" + querySinkDetails.getStrPersitTo()
                            + ".execute(\" CREATE KEYSPACE IF NOT EXISTS "
                            + cassandraPersists.getStrKeySpace()
                            + " WITH replication = { 'class': '"
                            + cassandraPersists.getStrTopology() + "', 'DC1': '"
                            + cassandraPersists.getInDcReplicationFactor()
                            + "' } AND durable_writes = true \" )\n\t";
                    strDBName = cassandraPersists.getStrKeySpace();
                    strTimetoLive = cassandraPersists.getTimeToLive();
                    strPersistTo = querySinkDetails.getStrPersitTo();
                    strPrimaryKey = cassandraPersists.getPrimaryKey();
                    strIndexKey = cassandraPersists.getIndexKey();
                    strPersistTo = querySinkDetails.getStrPersitTo();
                }

                strCode += createTableQry(strDBName, strPersistTo,
                        strPrimaryKey, objBSql,strTimetoLive);

                if (strIndexKey != null) {
                    strCode += createIndexQry(strDBName, strPersistTo,
                            strIndexKey, objBSql);
                }
            }

            pipeline.setInterpreterProcess("$CREATE_TABLE_FUNC$", strCode);
            bRet = true;

        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in genSinkTableLogic function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : genSinkTableLogic function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method to create field name string with comma
     * @param sourceSchemaObj
     * @return
     */
    private String getFieldNames(final Object sourceSchemaObj){

        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : getFieldNames function : "
                + Constants.START_STATUS);
        String strRet = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            String sourceSchemaJson = gson.toJson(sourceSchemaObj);
            List<ColumnMetadataDto> tableColumnProp = mapper.readValue(
                    sourceSchemaJson,
                    new TypeReference<List<ColumnMetadataDto>>() {
                    });

            if (tableColumnProp.size() > 0) {

                int i = 0;
                for (ColumnMetadataDto schemaObj : tableColumnProp) {
                    String fieldName = schemaObj.getColumnName();
                    strRet +="\""+fieldName+"\"";

                    if (tableColumnProp.size() - 1 != i) {
                        strRet += ",";
                    }
                    i++;
                }
            }

        }catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in getFieldNames function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : getFieldNames function : "
                + Constants.END_STATUS);
        return strRet;

    }

    /**
     * Method to create data schema object for unstructured log/msg data.
     *
     * @param sourceSchemaObj
     *            the source schema obj
     * @return String
     */
    private String getDataSchema(final Object sourceSchemaObj) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : getDataSchema function : "
                + Constants.START_STATUS);
        String strRet = "";
        String schemaFieldType;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            String sourceSchemaJson = gson.toJson(sourceSchemaObj);
            List<ColumnMetadataDto> tableColumnProp = mapper.readValue(
                    sourceSchemaJson,
                    new TypeReference<List<ColumnMetadataDto>>() {
                    });

            Map<String, String> schemaTypeMap = Util.getSchemaMap();

            if (tableColumnProp.size() > 0) {
                strRet += "StructType([StructField('uid',StringType(), True),";

                int i = 0;
                for (ColumnMetadataDto schemaObj : tableColumnProp) {

                    String fieldName = schemaObj.getColumnName();
                    String fieldType = schemaObj.getColumnDataType();

                    strRet += "StructField('" + fieldName + "',"
                            + schemaTypeMap.get(fieldType) + ", True)";

                    if (tableColumnProp.size() - 1 != i) {
                        strRet += ",";
                    }
                    i++;
                }
                strRet += "])";
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in getDataSchema function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : getDataSchema function : "
                + Constants.END_STATUS);
        return strRet;
    }

    /**
     * Method to Create Rdd Type mapping.
     *
     * @param sourceSchemaObj
     *            the source schema obj
     * @return the RDD type
     */
    private String getRDDType(final Object sourceSchemaObj) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : getRDDType function : "
                + Constants.START_STATUS);
        String strRet = "";
        String schemaFieldType;
        try {
            //strRet += ".map(lambda dt : Row(";
            //Test Code
            strRet += ".map(lambda dt : Row(str(uuid.uuid4())[:5],";
            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            String sourceSchemaJson = gson.toJson(sourceSchemaObj);
            List<ColumnMetadataDto> tableColumnProp = mapper.readValue(
                    sourceSchemaJson,
                    new TypeReference<List<ColumnMetadataDto>>() {
                    });

            int i = 0;
            for (ColumnMetadataDto schemaObj : tableColumnProp) {

                // String fieldName = schemaObj.getColumnName();
                String fieldType = schemaObj.getColumnDataType();
                if (fieldType.equals("int")) {
                    strRet += "int(dt[" + i + "])";

                } else if (fieldType.equals("long")) {
                    strRet += "long(dt[" + i + "])";

                } else if (fieldType.equals("double")) {
                    strRet += "double(dt[" + i + "])";

                } else if (fieldType.equals("float")) {
                    strRet += "float(dt[" + i + "])";

                } else if ((fieldType.equals("String")
                        || fieldType.equals("string")
                        || fieldType.equals("text"))) {
                    strRet += "dt[" + i + "]";
                }
                if (tableColumnProp.size() - 1 != i) {
                    strRet += ",";
                }
                i++;
            }
            strRet += "))";
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  KafkaSparkELCsvInterpreter: in getRDDType function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : getRDDType function : "
                + Constants.END_STATUS);
        return strRet;
    }
    /**
     * Function to Generate Create Table Script for Audit Data
     * @return
     */
    public boolean buildCreateAuditTableLogic() {

        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : genCreateAuditTableLogic function : "
                + Constants.START_STATUS);

        String strCode = "";
        String auditKeySpace = "";
        boolean bRet = false;
        String strCassandraCluster = "";
        try {

            strCassandraCluster = PropReader.getPropertyValue(Constants.CASSANDRA_CLUSTER_NODES);
            auditKeySpace = "audit_" + pipelineConf.getStrPipelineName();


            strCode = "def createAuditTables():\n\t";
            strCode += "cluster_Cassandra"
                    + " = Cluster([" + strCassandraCluster + "])\n\t";
            strCode += "session_Cassandra = cluster_Cassandra.connect()\n\t";

            //keyspace
            strCode += "session_Cassandra"
                    + ".execute(\" CREATE KEYSPACE IF NOT EXISTS "
                    + auditKeySpace
                    + " WITH replication = { 'class': '"
                    + "NetworkTopologyStrategy" + "', 'DC1': '"
                    + "3"
                    + "' } AND durable_writes = true \" )\n\t";

            //Raw Data Table
            strCode += "session_Cassandra"
                    + ".execute(\" CREATE TABLE IF NOT EXISTS " + auditKeySpace + ".tbl_raw_source (raw_id text,raw_created_time timestamp,source_data text,raw_index_id bigint,PRIMARY KEY(raw_id,raw_created_time))\" )\n\t";
            strCode += "session_Cassandra"
                    + ".execute(\"CREATE INDEX IF NOT EXISTS ON " + auditKeySpace +".tbl_raw_source(raw_index_id)\" )\n\t";

            //Process Details

            strCode += "session_Cassandra"
                    + ".execute(\" CREATE TABLE IF NOT EXISTS " + auditKeySpace + ".tbl_process_info (pr_id text,pr_time timestamp,pr_rule text,pr_query text,pr_count bigint,pr_index_id bigint,PRIMARY KEY(pr_id,pr_time,pr_rule))\" )\n\t";

            strCode += "session_Cassandra"
                    + ".execute(\"CREATE INDEX IF NOT EXISTS ON " + auditKeySpace +".tbl_process_info(pr_index_id)\" )\n\t";

            //Error Details
            strCode += "session_Cassandra"
                    + ".execute(\"CREATE TABLE IF NOT EXISTS " + auditKeySpace + ".tbl_error_info (er_id text,er_time timestamp,raw_data text,error_message text,er_index_id bigint, PRIMARY KEY(er_id,er_time))\" )\n\t";

            strCode += "session_Cassandra"
                    + ".execute(\"CREATE INDEX IF NOT EXISTS ON " + auditKeySpace +".tbl_error_info(er_index_id)\" )\n\t";

        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  StreamFlowInterpreter: in genCreateAuditTableCode function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " StreamFlowInterpreter : genCreateAuditTableCode function : "
                + Constants.START_STATUS);

        pipeline.setInterpreterProcess("$CREATE_AUDIT_TABLE_FUNC$", strCode);
        bRet = true;

        return bRet;

    }
    /**
     * Function to Generate Raw Data Save Logic
     * @return
     */
    public boolean buildSaveRawDataLogic() {

        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : buildSaveRawDataLogic function : "
                + Constants.START_STATUS);

        boolean bRet = false;
        String strCode = "";
        String schemafields = "";
        String auditKeySpace = "";
        try {

            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);

            Object sourceSchemaObj =pipelineConf.getStrDataSchema()
                    .getObjSchema();
            schemafields = "\",\""+","+getFieldNames(sourceSchemaObj);



            auditKeySpace = "audit_" + pipelineConf.getStrPipelineName();
            strCode = "def saverawdata(rdd):\n\t";
            strCode += "if(rdd.count() > 0):\n\t\t";
            strCode += "try:\n\t\t\t";
            strCode += "sqlContext = getSqlContextInstance(rdd.context)\n\t\t\t";
            strCode += "srcDf = sqlContext.createDataFrame(rdd, prototype)\n\t\t\t";
            strCode += "created_time = time.strftime(\"%Y-%m-%d %H:%M:%S\",time.localtime())\n\t\t\t";
            strCode += "srcDf = srcDf.withColumn('raw_created_time',lit(created_time))\n\t\t\t";
            strCode += "srcDf = srcDf.withColumn('source_data',concat_ws("+schemafields+"))\n\t\t\t";
            strCode += "srcDf = srcDf.withColumn('raw_index_id',lit(str(random.randint(1, 20))))\n\t\t\t";
            strCode += "srcDf.registerTempTable('rawtable')\n\t\t\t";
            strCode += "rawData = sqlContext.sql(\"select uid as raw_id,raw_created_time,source_data,raw_index_id from rawtable\")\n\n\t\t\t";
            strCode += "rawData.write.format(\"org.apache.spark.sql.cassandra\").mode('append').options(table=\"tbl_raw_source\", keyspace=\""+auditKeySpace+"\").save()\n\t\t\t";
            strCode += "\n\t\texcept Exception as e:\n\t\t\t";
            strCode += "print(\"Exception occurred while saving source data\")\n\t\t\t";
            strCode += "print(e.message)\n\t\t\t";
            strCode += "saveerrordata(rdd,'Error in Source Data Save Layer :'+e.message)\n\t\t\t";
            strCode += "\n\t\tfinally:\n\t\t\t";
            strCode += "print(\"Final loop\")\n\t\t\t";



        }catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  StreamFlowInterpreter: in buildSaveRawDataLogic function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " StreamFlowInterpreter : buildSaveRawDataLogic function : "
                + Constants.START_STATUS);

        pipeline.setInterpreterProcess("$AUDIT_RAW_DATA_SAVE_FUNC$", strCode);
        bRet = true;
        return bRet;
    }

    /**
     * Function to Generate Process Information Fetch Logic
     * @return
     */
    public boolean buildProcessInfoFetchCode() {

        return false;
    }


    /**
     * Function to Generate Process Information Fetch Logic
     * @return
     */
    public String genProcessInfoFetchCode(String strQuery,String fetchRule) {

        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : buildSaveRawDataLogic function : "
                + Constants.START_STATUS);

        boolean bRet = false;
        String strCode = "";
        String schemafields = "";
        String auditKeySpace = "";
        try {
            auditKeySpace = "audit_" + pipelineConf.getStrPipelineName();
            strCode = "prDf = sqlContext.sql('select uid from "+pipelineConf.getStrInitialTableName()+"')\n\t\t\t";
            strCode = "tempid = str(uuid.uuid4())[:5]\n\t\t\t";
            strCode = "fetch_rule_name = '"+fetchRule+"'\n\t\t\t";
            strCode = "process_query = '"+strQuery+"'\n\t\t\t";
            strCode = "records_processed = df1.count()\n\t\t\t";
            strCode = "prDf = prDf.withColumn('pr_time',lit(created_time))\n\t\t\t";
            strCode = "prDf = prDf.withColumn('pr_time',lit(created_time))\n\t\t\t";
            strCode = "prDf = prDf.withColumn('pr_rule',lit(fetch_rule_name))\n\t\t\t";
            strCode = "prDf = prDf.withColumn('pr_query',lit(process_query))\n\t\t\t";
            strCode = "prDf = prDf.withColumn('pr_count',lit(records_processed))\n\t\t\t";
            strCode = "prDf = prDf.withColumn('pr_id',lit(tempid))\n\t\t\t";
            strCode += "prDf.write.format(\"org.apache.spark.sql.cassandra\").mode('append').options(table=\"tbl_process_info\", keyspace=\"auditKeySpace\").save()\n\t\t\t";

        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  StreamFlowInterpreter: in buildSaveRawDataLogic function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " StreamFlowInterpreter : buildSaveRawDataLogic function : "
                + Constants.START_STATUS);

        return strCode;
    }
    /**
     * Function to Generate Error Information Fetch Logic
     * @return
     */

    public boolean buildErrorInfoFetchLogic() {

        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : buildErrorInfoFetchLogic function : "
                + Constants.START_STATUS);

        boolean bRet = false;
        String strCode = "";
        String schemafields = "";
        String auditKeySpace = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);

            Object sourceSchemaObj =pipelineConf.getStrDataSchema()
                    .getObjSchema();

            auditKeySpace = "audit_" + pipelineConf.getStrPipelineName();
            schemafields = "\",\""+","+getFieldNames(sourceSchemaObj);
            strCode = "def saveerrordata(rdd,msg):\n\t";
            strCode += "if(rdd.count() > 0):\n\t\t";
            strCode += "try:\n\t\t\t";
            strCode += "sqlContext = getSqlContextInstance(rdd.context)\n\t\t\t";
            strCode += "df = sqlContext.createDataFrame(rdd, prototype)\n\t\t\t";
            strCode += "created_time = time.strftime(\"%Y-%m-%d %H:%M:%S\",time.localtime())\n\t\t\t";
            strCode += "df = df.withColumn('er_time',lit(created_time))\n\t\t\t";
            strCode += "df = df.withColumn('raw_data',concat_ws("+schemafields+"))\n\t\t\t";
            strCode += "df = df.withColumn('er_index_id',lit(str(random.randint(1, 20))))\n\t\t\t";
            strCode += "df.registerTempTable(\"ertable\")\n\t\t\t";
            strCode += "errorDf = sqlContext.sql(\"select uid as er_id,er_time,raw_data,error_message,er_index_id from ertable\")\n\n\t\t\t";
            strCode += "errorDf.write.format(\"org.apache.spark.sql.cassandra\").mode('append').options(table=\"tbl_error_info\", keyspace=\""+auditKeySpace+"\").save()\n\t\t\t";
            strCode += "\n\t\texcept Exception as e:\n\t\t\t";
            strCode += "print(\"Exception occurred while saving source data\")\n\t\t\t";
            strCode += "print(e.message)";
            strCode += "\n\t\tfinally:\n\t\t\t";
            strCode += "print(\"Final loop\")\n\t\t\t";



        }catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  StreamFlowInterpreter: in buildErrorInfoFetchLogic function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " StreamFlowInterpreter : buildErrorInfoFetchLogic function : "
                + Constants.START_STATUS);

        pipeline.setInterpreterProcess("$AUDIT_ERROR_SAVE_FUNC$", strCode);
        bRet = true;
        return bRet;
    }


    /**
     * Function to Generate  Logic for creating Elastic Search Index
     * @return
     */
    public boolean buildCreateELIndexLogic()  {

        //$CREATE_EL_INDEX_LOGIC$
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " KafkaSparkELCsvInterpreter : buildCreateELIndexLogic function : "
                + Constants.START_STATUS);

        boolean bRet = false;
        String strCode = "";
        String auditKeySpace = "";
        String elUrl = "";
        try {
            auditKeySpace = "audit_" + pipelineConf.getStrPipelineName();
            elUrl =  PropReader.getPropertyValue(Constants.ELASTICSEARCH_URL)+"/"+auditKeySpace;
            strCode = "def creatElIndex():\n\t";
            strCode += "rawUrl = \""+elUrl+"_rawindex\"\n\t";
            strCode += "prUrl = \""+elUrl+"_prindex\"\n\t";
            strCode += "erUrl = \""+elUrl+"erindex\"\n\t";
            strCode += "raw_settings = {\"settings\":{\"keyspace\":\""+auditKeySpace+"\"},\"mappings\":{\"tbl_raw_source\": {\"discover\": \"raw_.*\"}}}\n\t";
            strCode += "process_settings = {\"settings\":{\"keyspace\":\""+auditKeySpace+"\"},\"mappings\":{\"tbl_process_info\": {\"discover\": \"pr_.*\"}}}\n\t";
            strCode += "error_settings = {\"settings\":{\"keyspace\":\""+auditKeySpace+"\"},\"mappings\":{\"tbl_error_info\": {\"discover\": \"er_.*\"}}}\n\t";
            strCode += "rawindexreq = requests.post(rawUrl, data=json.dumps(raw_settings))\n\t";
            strCode += "proindexreq = requests.post(prUrl, data=json.dumps(process_settings))\n\t";
            strCode += "erindexreq = requests.post(erUrl, data=json.dumps(error_settings))\n\t";
            strCode += "print(str(rawindexreq)+'|'+str(proindexreq)+'|'+str(erindexreq))\n\t";

        }catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  StreamFlowInterpreter: in buildCreateELIndexLogic function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " StreamFlowInterpreter : buildCreateELIndexLogic function : "
                + Constants.START_STATUS);

        pipeline.setInterpreterProcess("$CREATE_EL_INDEX_LOGIC$", strCode);
        bRet = true;
        return bRet;
    }

    /**
     * Method to get all the user functions available
     * @return true, if successful
     */
    private boolean buildUserFunctionLogic() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "FlumeSparkELJsonInterpreter : buildUserFunctionLogic function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        String strCodeBase = "";
        String strRegisterMethod = "";
        try {
            List<UserFunctionDto> userFunctionDtos  = pipeline
                    .getUserFunctionDetails();
            for(UserFunctionDto userFunctionDto : userFunctionDtos){
                strCodeBase += String.valueOf(userFunctionDto
                        .getObjCodeBase())+"\n";
                strRegisterMethod += "sqlContext" +
                        ".registerFunction(\""+userFunctionDto
                        .getStrUfName()+"\", "+userFunctionDto
                        .getStrRegisterMethod()+" ) \n\t\t";
            }
            pipeline.setInterpreterProcess("$DEFINE_USER_FUNC", strCodeBase);
            pipeline.setInterpreterProcess("$REGISTER_USER_FUNC", strRegisterMethod);
        }catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  "
                            + "FlumeSparkELJsonInterpreter: in buildUserFunctionLogic function : "
                            + "",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "FlumeSparkELJsonInterpreter : buildUserFunctionLogic function : "
                + Constants.START_STATUS);
        return bRet;
    }


    /**
     * Function to Generate logic for creating Grafana Dashboard
     * @return
     */
    public boolean buildCreateGrafanaDashbordLogic()  {
        return false;
    };
}
