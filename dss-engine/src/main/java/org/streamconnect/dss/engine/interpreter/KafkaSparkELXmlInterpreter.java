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

import org.springframework.stereotype.Service;

import org.streamconnect.dss.dto.*;
import org.streamconnect.dss.engine.prefab.InterpreterType;
import org.streamconnect.dss.engine.prefab.StreamFlowInterpreter;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.InterpreterException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;

import java.util.List;

/**
 * Kafka-Spark-Elassandra-Xml data pipeline Interpreter for Python is created
 * here
 */

@Service
public class KafkaSparkELXmlInterpreter extends StreamFlowInterpreter {

    /** The logger. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(KafkaSparkELXmlInterpreter.class);

    /**
     * default constructor.
     */
    public KafkaSparkELXmlInterpreter() {

    }

    /**
     * parameterized constructor.
     *
     * @param features
     *            the features
     */
    public KafkaSparkELXmlInterpreter(final String features) {
        LOGGER.info("Listed the Global Features - " + this.getClass() + "  "
                + features);
    }

    /**
     * parameterized constructor.
     *
     * @param objPipeline
     *            the obj pipeline
     */
    public KafkaSparkELXmlInterpreter(final PipelineConfigDto objPipeline) {
        // LOGGER.info("Listed the Global Features - "+this.getClass() + " "
        // + features);
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
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "KafkaSparkELXmlInterpreter : getPrerequisite function : "
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
                            + "  "
                            + "KafkaSparkELXmlInterpreter: in getPrerequisite function "
                            + ": ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "KafkaSparkELXmlInterpreter : getPrerequisite function : "
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
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "KafkaSparkELXmlInterpreter : buildSource function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            bRet = true;

        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  "
                            + "KafkaSparkELXmlInterpreter: in buildSource function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "KafkaSparkELXmlInterpreter : buildSource function : "
                + Constants.START_STATUS);
        return bRet;

    }

    /**
     * Method to create/build data pipeline engine/process related
     * configurations & settings.
     *
     * @return true, if successful
     */
    public boolean buildEngine() {
        // TODO business logic
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "KafkaSparkELXmlInterpreter : buildEngine function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            bRet = true;
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  "
                            + "KafkaSparkELXmlInterpreter: in buildEngine function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "KafkaSparkELXmlInterpreter : buildEngine function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method to create/build data pipeline process logic.
     *
     * @return true, if successful
     */
    public boolean buildProcess() {
        // TODO business logic
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "KafkaSparkELXmlInterpreter : buildProcess function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            bRet = true;
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  "
                            + "KafkaSparkELXmlInterpreter: in buildProcess function : "
                            + "",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "KafkaSparkELXmlInterpreter : buildProcess function : "
                + Constants.END_STATUS);
        return bRet;
    }

    /**
     * Method to create/build data pipeline sink part.
     *
     * @return true, if successful
     */
    public boolean buildSink() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "KafkaSparkELXmlInterpreter : buildSink function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            bRet = true;
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  "
                            + "KafkaSparkELXmlInterpreter: in buildSink function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "KafkaSparkELXmlInterpreter : buildSink function : "
                + Constants.END_STATUS);
        return bRet;

    }

    /**
     * Method to get the data pipeline.
     *
     * @return the data pipeline
     */
    public PipelineDetails getDataPipeline() {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "KafkaSparkELXmlInterpreter : getDataPipeline function : "
                + Constants.START_STATUS);
        PipelineDetails retPipeDtls = new PipelineDetails();
        try {

        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  "
                            + "KafkaSparkELXmlInterpreter: in getDataPipeline function "
                            + ": ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "KafkaSparkELXmlInterpreter : getDataPipeline function : "
                + Constants.END_STATUS);
        return retPipeDtls;

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

    /**
     * Method to get all the user functions available
     *
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
}
