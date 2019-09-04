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
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import org.streamconnect.dss.dto.*;
import org.streamconnect.dss.engine.prefab.InterpreterType;
import org.streamconnect.dss.engine.prefab.StreamFlowInterpreter;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.InterpreterException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import org.streamconnect.dss.util.PropReader;

/**
 * Flume-Spark-Elassandra-Unstructured data pipeline Interpreter for Python is
 * created here
 *
 */

@Service
public class FlumeSparkELUnstructInterpreter extends StreamFlowInterpreter {

    /** The logger. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(FlumeSparkELUnstructInterpreter.class);

    /**
     * default constructor.
     */
    public FlumeSparkELUnstructInterpreter() {

        // pipeline.setType(this.getClass() + " " + features);
    }

    /**
     * parameterized constructor.
     *
     * @param features
     *            the features
     */
    public FlumeSparkELUnstructInterpreter(final String features) {
        LOGGER.info("Listed the Global Features - " + this.getClass() + "  "
                + features);
        // pipeline.setType(this.getClass() + " " + features);
    }

    /**
     * parameterized constructor.
     *
     * @param objPipeline
     *            the obj pipeline
     */
    public FlumeSparkELUnstructInterpreter(
            final PipelineConfigDto objPipeline) {
        LOGGER.info("FlumeSparkELUnstructInterpreter Constructor");
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
                + " FlumeSparkELUnstructInterpreter : getPrerequisite function : "
                + Constants.START_STATUS);
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
                            + "  FlumeSparkELUnstructInterpreter: in getPrerequisite function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : getPrerequisite function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method to create and update data pipeline source related configurations &
     * settings.
     *
     * @return true, if successful
     */
    public boolean buildSource() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : buildSource function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {

            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);

            FlumeStreamSrcDto objFlumeStreamConfig = mapper.readValue(
                    gson.toJson(((SourceDto) pipelineConf.getObjSource())
                            .getObjSourceConfigDetails()),
                    FlumeStreamSrcDto.class);
            /*
             * FlumeAgentSrcDtls objFlumeAgentSrcConfig =
             * mapper.readValue(gson.toJson(objFlumeStreamConfig.
             * getObjFlumeAgentSrcDtls()), FlumeAgentSrcDtls.class);
             */
            FlumeAgentDstDtls objFlumeAgentDstConfig = mapper.readValue(
                    gson.toJson(objFlumeStreamConfig.getObjFlumeAgentDstDtls()),
                    FlumeAgentDstDtls.class);

            pipeline.setInterpreterProcess("$FLUME_SINKHOST$",
                    objFlumeAgentDstConfig.getStrDstHost());
            pipeline.setInterpreterProcess("$FLUME_SINKPORT$",
                    Long.toString(objFlumeAgentDstConfig.getnPort()));
            bRet = true;

        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  FlumeSparkELUnstructInterpreter: in buildSource function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : buildSource function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method to create/build data pipeline engine/process related
     * configurations & settings.
     *
     * @return true, if successful
     */
    public boolean buildEngine() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : buildEngine function : "
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

            // pipeline.setInterpreterProcess("Initiated and completed the
            // Process - Spark for KafkaSparkELInterpreter ");
            bRet = true;
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  FlumeSparkELUnstructInterpreter: in buildEngine function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : buildEngine function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method to create and update data pipeline process logic.
     *
     * @return true, if successful
     */
    public boolean buildProcess() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : buildProcess function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {

            if (genParsingLogic() && genIterativeLogic() && genProcessLogic()) {
                bRet = true;
            }
            // pipeline.setInterpreterProcess("Initiated and completed the
            // Process - Spark for KafkaSparkELInterpreter ");
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  FlumeSparkELUnstructInterpreter: in buildProcess function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : buildProcess function : "
                + Constants.END_STATUS);
        return bRet;
    }

    /**
     * Method for generating process logic.
     *
     * @return boolean
     */
    public boolean genProcessLogic() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : genProcessLogic function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {

            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);

            buildUserFunctionLogic();
            String strCode = "";
            Map<String, List<BusinessSqlDto>> mapWindowLst = getWindowBasedList(
                    pipelineConf.getObjBusinessSqlDto());

            if (mapWindowLst != null && !mapWindowLst.isEmpty()) {
                for (Map.Entry<String, List<BusinessSqlDto>> entry : mapWindowLst
                        .entrySet()) {
                    strCode += "\ndef processfunc" + entry.getKey()
                            + "(time,rdd):\n\t";
                    strCode += "if(rdd.count() > 0):\n\t\t";
                    strCode += "try:\n\t\t\t";
                    strCode += "sqlContext = getSqlContextInstance(rdd.context)\n\t\t\t";
                    // strCode += "df0 = sqlContext.createDataFrame(rdd)\n\t\t";

                    if (pipelineConf.getStrDataSchema().getStrSchemaType()
                            .equalsIgnoreCase("unstructured")) {
                        strCode += "schema = "
                                + getUnstructSchema(entry.getValue().get(0))
                                + "\n\t\t\t";
                        strCode += "df0 = sqlContext.createDataFrame(rdd,schema)\n\t\t\t";
                    } else if (pipelineConf.getStrDataSchema()
                            .getStrSchemaType().equalsIgnoreCase("json")) {
                        strCode += "df0 = df_from_rdd(rdd, prototype, sqlContext)\n\t\t\t";
                    }




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

                                if (pipelineConf.getStrDataSchema()
                                        .getStrSchemaType()
                                        .equalsIgnoreCase("unstructured")) {
                                    String strRetQry = getUnstructQry(objSql,
                                            pipelineConf
                                                    .getStrInitialTableName());
                                    objSql.setStrQuery(strRetQry);
                                }

                            }

                            strCode += "df" + Integer.toString(nIndex + 1)
                                    + " = sqlContext.sql(\""
                                    + objSql.getStrQuery() + "\")\n\t\t\t";
                            strCode += "df" + Integer.toString(nIndex + 1)
                                    + ".registerTempTable(\""
                                    + objSql.getStrTableName() + "\")\n\t\t\t";

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
                    strCode += "print(e.message)";
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
                            + "  FlumeSparkELUnstructInterpreter: in genProcessLogic function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : genProcessLogic function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method for creating tables.
     *
     * @param objBSql
     *            the obj B sql
     * @return String
     */
    private String getUnstructSchema(final BusinessSqlDto objBSql) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : getUnstructSchema function : "
                + Constants.START_STATUS);
        String strRet = "";
        try {
            strRet += "StructType([StructField('"
                    + objBSql.getSchemaList().get(0).getName()
                    + "', StringType(), True),StructField('id', StringType(), True),StructField('processedtime', StringType(), True),StructField('indexid', StringType(), True)])";

        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  FlumeSparkELUnstructInterpreter: in getUnstructSchema function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : getUnstructSchema function : "
                + Constants.END_STATUS);
        return strRet;
    }

    /**
     * Method for creating tables.
     *
     * @param objBSql
     *            the obj B sql
     * @param strTableName
     *            the str table name
     * @return String
     */
    private String getUnstructQry(final BusinessSqlDto objBSql, final String strTableName) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : getUnstructQry function : "
                + Constants.START_STATUS);
        String strRet = "";
        try {
            strRet += "select " + objBSql.getSchemaList().get(0).getName()
                    + ",id,processedtime,indexid from " + strTableName;
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  FlumeSparkELUnstructInterpreter: in getUnstructQry function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : getUnstructQry function : "
                + Constants.END_STATUS);
        return strRet;
    }

    /**
     * Method for generating parsing logic.
     *
     * @return boolean
     */
    public boolean genParsingLogic() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : genParsingLogic function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            String strCode = "";

            if (pipelineConf.getStrDataSchema().getStrSchemaType()
                    .equalsIgnoreCase("unstructured")) {
                strCode += "parsedStream = flumeStream.map(lambda x: Row(x[1].encode('UTF8'),str(uuid.uuid4()),tval.strftime(\"%Y-%m-%d %H:%M:%S\", tval.localtime()), str(random.randint(1, 10)) ))\n";
            }
            pipeline.setInterpreterProcess("$PARSING_LOGIC$", strCode);

            bRet = true;
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  FlumeSparkELUnstructInterpreter: in genParsingLogic function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : genParsingLogic function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method for generating iterative logic.
     *
     * @return boolean
     */
    public boolean genIterativeLogic() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : genIterativeLogic function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            String strCode = "";
            if (pipelineConf.getStrDataSchema().getStrSchemaType()
                    .equalsIgnoreCase("unstructured")) {
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

            // m_strPipelineContent =
            // m_strPipelineContent.replaceAll("\\$ITERATIVE_LOGIC\\$",
            // strCode);
            pipeline.setInterpreterProcess("$ITERATIVE_LOGIC$", strCode);
            bRet = true;
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  FlumeSparkELUnstructInterpreter: in genIterativeLogic function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : genIterativeLogic function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method for creating tables.
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
    private String createTableQryWithDefaultCols(final String strDBName,
                                                 final String strPersistTo, final String strPrimaryKey, final BusinessSqlDto objBSql,final String strTimetoLive) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : createTableQryWithDefaultCols function : "
                + Constants.START_STATUS);
        String strRet = "";
        try {
            //String ttlValue = PropReader
                    //.getPropertyValue(Constants.CASSANDRA_TIME_TO_LIVE);
            strRet += "session_" + strPersistTo
                    + ".execute(\" CREATE TABLE IF NOT EXISTS " + strDBName
                    + "." + objBSql.getStrTableName() + " (";
            List<EntitySchema> objTableSchm = objBSql.getSchemaList();
            String strSchm = "";
            String strPriKey = "";
            for (int nSInd = 0; nSInd < objTableSchm.size(); nSInd++) {
                strSchm += objTableSchm.get(nSInd).getName() + " "
                        + objTableSchm.get(nSInd).getType() + ",";
            }

            if ("cassandra".equalsIgnoreCase(strPersistTo)
                    || "elassandra".equalsIgnoreCase(strPersistTo)) {
                strSchm += "id text,indexid text,processedtime timestamp, ";
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
                            + "  FlumeSparkELUnstructInterpreter: in createTableQryWithDefaultCols function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : createTableQryWithDefaultCols function : "
                + Constants.END_STATUS);
        return strRet;
    }

    /**
     * Method to create/build data pipeline sink part.
     *
     * @return true, if successful
     */
    public boolean buildSink() {
        // TODO business logic
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : buildSink function : "
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
                            + "  FlumeSparkELUnstructInterpreter: in buildSink function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : buildSink function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method to retrieve Data pipeline executable.
     *
     * @return the data pipeline
     */
    public PipelineDetails getDataPipeline() {
        // TODO business logic
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : getDataPipeline function : "
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
                            + "  FlumeSparkELUnstructInterpreter: in getDataPipeline function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : getDataPipeline function : "
                + Constants.END_STATUS);
        return retPipeDtls;

    }

    /**
     * Method for creating tables.
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
                + " FlumeSparkELUnstructInterpreter : createTableQry function : "
                + Constants.START_STATUS);
        String strRet = "";
        try {
            //String ttlValue = PropReader
                    //.getPropertyValue(Constants.CASSANDRA_TIME_TO_LIVE);
            strRet += "session_" + strPersistTo
                    + ".execute(\" CREATE TABLE IF NOT EXISTS " + strDBName
                    + "." + objBSql.getStrTableName() + " (";
            List<EntitySchema> objTableSchm = objBSql.getSchemaList();
            String strSchm = "";
            String strPriKey = "";
            for (int nSInd = 0; nSInd < objTableSchm.size(); nSInd++) {
                strSchm += objTableSchm.get(nSInd).getName() + " "
                        + objTableSchm.get(nSInd).getType() + ",";
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
                            + "  FlumeSparkELUnstructInterpreter: in createTableQry function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : createTableQry function : "
                + Constants.END_STATUS);
        return strRet;
    }

    /**
     * Method for creating index.
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
                + " FlumeSparkELUnstructInterpreter : createIndexQry function : "
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
                            + "  FlumeSparkELUnstructInterpreter: in createIndexQry function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : createIndexQry function : "
                + Constants.END_STATUS);
        return strRet;
    }

    /**
     * Method for generating sink table logic.
     *
     * @return boolean
     */
    public boolean genSinkTableLogic() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : genSinkTableLogic function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            String strCode = "def createTables():\n\t";
            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);

            /*
             * SinkDto objSink = (SinkDto) objPipe.getObjSink(); if(objSink !=
             * null && "cassandra".equalsIgnoreCase(objSink.getStrSinkType()) ||
             * "elassandra".equalsIgnoreCase(objSink.getStrSinkType())) {
             * CassandraSinkDto objCassSink =
             * mapper.readValue(gson.toJson(objSink.getObjSinkConfigDetails()),
             * CassandraSinkDto.class); String[] nodeArray =
             * objCassSink.getStrNodelst().split(","); String strCluster = "";
             * for(int nInd = 0; nInd < nodeArray.length ; nInd++ ) { if( 0 ==
             * nInd) strCluster = "'" + nodeArray[nInd] + "'"; else strCluster
             * += ",'" + nodeArray[nInd] + "'"; } strCode +=
             * "cluster = Cluster([" + strCluster + "])\n\t" ; strCode +=
             * "session = cluster.connect()\n\t"; }
             */

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
                    // strCode +=
                    // "session_"+querySinkDetails.getStrPersitTo()+".set_keyspace(\""
                    // + cassandraPersists.getStrKeySpace() + "\")\n\t";
                    strDBName = cassandraPersists.getStrKeySpace();
                    strTimetoLive = cassandraPersists.getTimeToLive();
                    strPersistTo = querySinkDetails.getStrPersitTo();
                    strPrimaryKey = cassandraPersists.getPrimaryKey();
                    strIndexKey = cassandraPersists.getIndexKey();
                    // objAddlParams = cassandraPersists.getAddlParams();
                    strPersistTo = querySinkDetails.getStrPersitTo();
                }

                if (pipelineConf.getStrDataSchema().getStrSchemaType()
                        .equalsIgnoreCase("unstructured")) {
                    strPrimaryKey = "id";
                    strIndexKey = "indexid";
                    strCode += createTableQryWithDefaultCols(strDBName,
                            strPersistTo, strPrimaryKey, objBSql,strTimetoLive);
                } else {
                    strCode += createTableQry(strDBName, strPersistTo,
                            strPrimaryKey, objBSql,strTimetoLive);
                }

                if (strIndexKey != null) {
                    strCode += createIndexQry(strDBName, strPersistTo,
                            strIndexKey, objBSql);
                }
            }
            // BusinessSqlDto objTargetSql = objPipe.getObjTargetTable();
            // strCode += createTableQry(objTargetSql);
            pipeline.setInterpreterProcess("$CREATE_TABLE_FUNC$", strCode);
            bRet = true;
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  FlumeSparkELUnstructInterpreter: in genSinkTableLogic function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " FlumeSparkELUnstructInterpreter : genSinkTableLogic function : "
                + Constants.END_STATUS);
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
     * Function to Generate Create Table Script for Audit Data
     * @return
     */
    public boolean buildCreateAuditTableLogic() {
        return false;
    }
    /**
     * Function to Generate Raw Data Save Logic
     * @return
     */
    public boolean buildSaveRawDataLogic() {
        return false;
    }
    /**
     * Function to Generate Process Information Fetch Logic
     * @return
     */
    public boolean buildProcessInfoFetchCode() {
        return false;
    }
    /**
     * Function to Generate Error Information Fetch Logic
     * @return
     */

    public boolean buildErrorInfoFetchLogic() {
        return false;
    }

    /**
     * Function to Generate  Logic for creating Elastic Search Index
     * @return
     */
    public boolean buildCreateELIndexLogic()  {
        return false;
    };

    /**
     * Function to Generate logic for creating Grafana Dashboard
     * @return
     */
    public boolean buildCreateGrafanaDashbordLogic()  {
        return false;
    };

}

