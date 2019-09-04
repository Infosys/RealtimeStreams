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

package org.streamconnect.dss.executor.impl;


import org.streamconnect.dss.dto.PipelineExecutionDto;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.StreamExecutorException;
import org.streamconnect.dss.executor.IExecutor;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import org.streamconnect.dss.util.PropReader;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.UriBuilder;

/**
 * The type Livy executor.
 */
@Service
public class LivyExecutorImpl implements IExecutor {

    /**
     * Logger object for the application logging.
     */
    private static final DSSLogger LOGGER = DSSLogger.getLogger(LivyExecutorImpl
            .class);
    /**
     * Method to execute Pipeline.
     *
     * @param pipelineExecutionDto
     * @return Object
     */
    public Object executePipeline(final PipelineExecutionDto pipelineExecutionDto) {
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + "LivyExecutorImpl : executePipeline function : "
                + Constants.START_STATUS);
        Object object = null;
        try {
            String strLivyBaseAPI = PropReader.getPropertyValue(Constants.LIVY_BASEAPI);
            String strSourceDepend = "";
            if (pipelineExecutionDto.getStrSourceType().equalsIgnoreCase("kafka")) {
                strSourceDepend = PropReader.getPropertyValue(Constants.KAFKASPARK_DEPENDJAR_1);
            } else if (pipelineExecutionDto.getStrSourceType()
                    .equalsIgnoreCase("flume")) {
                strSourceDepend = PropReader.getPropertyValue(Constants.FLUMESPARK_DEPENDJAR_1);
            } else {
                return object;
            }
            String[] strDependJars = strSourceDepend.split(",");
            String strDependList = "";
            for (int nInd = 0; nInd < strDependJars.length; nInd++) {
                if (0 == nInd) {
                    strDependList = "\"" + strDependJars[nInd] + "\"";
                } else {
                    strDependList += ",\"" + strDependJars[nInd] + "\"";
                }
            }

            String strLivyParam = "{\"file\": \"" + pipelineExecutionDto.getInExecPipelineURl() + "\",\"jars\":[" + strDependList + "]}";
            object = executeRest(strLivyBaseAPI, "POST", "application/json", strLivyParam);
            LOGGER.debug(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                    + "LivyExecutorImpl : executePipeline function : object "
                    + "is " + object);
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .EXECUTOR_LAYER_EXCEPTION.getMessage()
                    + "LivyExecutorImpl : in executePipeline function : ", e);
            throw new StreamExecutorException(ErrorMessageEnum
                    .EXECUTOR_LAYER_EXCEPTION.getMessage() + " : " + e
                    .getMessage(), ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getCode().toString());
        }

        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + "LivyExecutorImpl : executePipeline function : "
                + Constants.END_STATUS);
        return object;
    }

    /**
     * Method to get the executed pipeline status.
     *
     * @param pipelineExecutionDto
     * @return Object
     */
    public Object getPipelineExecStatus(final PipelineExecutionDto
                                                pipelineExecutionDto) {
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + " LivyExecutorImpl : getPipelineExecStatus function : "
                + Constants.START_STATUS);
        Object object = null;
        try {
            String strExecId = pipelineExecutionDto.getInExecPipelineId();
            String strLivyBaseAPI = PropReader.getPropertyValue(Constants.LIVY_BASEAPI);
            object = executeRest(strLivyBaseAPI + "/" + strExecId, "GET", "application/json", "");
            LOGGER.debug(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                    + " LivyExecutorImpl : getPipelineExecStatus function : "
                    + "object is " + object);
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .EXECUTOR_LAYER_EXCEPTION.getMessage()
                    + "LivyExecutorImpl : in getPipelineExecStatus function :", e);
            throw new StreamExecutorException(ErrorMessageEnum
                    .EXECUTOR_LAYER_EXCEPTION.getMessage() + " : " + e
                    .getMessage(), ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getCode().toString());
        }
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + "LivyExecutorImpl : getPipelineExecStatus function : "
                + Constants.END_STATUS);
        return object;
    }

    /**
     *  Method tp stop Pipeline exection.
     * @param pipelineExecutionDto the pipeline execution dto
     * @return Object
     */
    public Object stopPipelineExec(final PipelineExecutionDto
                                           pipelineExecutionDto) {
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + "LivyExecutorImpl : stopPipelineExec function : "
                + Constants.START_STATUS);
        Object object = null;
        try {
            String strExecId = pipelineExecutionDto.getInExecPipelineId();
            String strLivyBaseAPI = PropReader.getPropertyValue(Constants.LIVY_BASEAPI);
            object = executeRest(strLivyBaseAPI + "/" + strExecId, "DELETE",
                    "application/json", "");
            LOGGER.debug(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                    + "LivyExecutorImpl : stopPipelineExec function : object is"
                    + object);
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getMessage()
                    + "LivyExecutorImpl : in stopPipelineExec function : ", e);
            throw new StreamExecutorException(ErrorMessageEnum
                    .EXECUTOR_LAYER_EXCEPTION.getMessage() + " : " + e
                    .getMessage(), ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getCode().toString());
        }
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + "LivyExecutorImpl : stopPipelineExec function : "
                + Constants.END_STATUS);
        return object;
    }

    /**
     * Method to execute the rest request.
     *
     * @param strUrl
     * @param strReqMethod
     * @param strReqProperty
     * @param strInput
     * @return Object
     */
    private Object executeRest(final String strUrl, final String strReqMethod,
                               final String strReqProperty, final String
                                       strInput) {
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + "LivyExecutorImpl : executeRest function : " + Constants
                .START_STATUS);
        Object object = null;
        try {
            ClientConfig config = new DefaultClientConfig();
            Client client = Client.create(config);
            WebResource webResource = client.resource(UriBuilder.fromUri(strUrl).build());
            if ("GET".equalsIgnoreCase(strReqMethod)) {
                object = executeGet(webResource, strReqProperty);
            } else if ("DELETE".equalsIgnoreCase(strReqMethod)) {
                object = executeDelete(webResource, strReqProperty);
            } else {
                object = executePost(webResource, strReqProperty, strInput);
            }
            System.out.println("Output from Server .... \n");
            LOGGER.debug(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                    + "LivyExecutorImpl : executeRest function : object is "
                    + object);
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getCode().toString() + " : "
                    + ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getMessage()
                    + " LivyExecutorImpl : in executeRest function : ", e);
        }
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + "LivyExecutorImpl : executeRest function : " + Constants
                .END_STATUS);
        return object;
    }

    /**
     * Method to execute the Post request.
     *
     * @param webResource
     * @param strReqProperty
     * @param strInput
     * @return object
     */
    private Object executePost(final WebResource webResource, final String
            strReqProperty, final String strInput) {
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + "LivyExecutorImpl : executePost function : " + Constants
                .START_STATUS);
        Object object = null;
        try {
            String input = "{\"objPipelineConfigDetails\":[{\"isStart\":true,\"Id\":1,\"name\":\"New Step 1\",\"task_id\":\"New Step 1\",\"contactType\":2,\"description\":\"\",\"userType\":1,\"template\":null,\"top\":\"80px\",\"left\":\"400px\",\"command_type\":\"process\",\"properties\":{\"process_id\":3,\"process_name\":\"spark1\",\"process_details\":{\"strSparkMaster\":\"yarn-client\",\"nBatchInterval\":\"5\",\"nExcMaxcore\":\"2\",\"nExcMaxmem\":\"1\",\"nDrvMaxmem\":\"1\"},\"process_type\":\"SPARK\",\"process_transform_queries\":[{\"id\":\"1\",\"query\":\"select a,b from test\",\"persistEnabled\":true,\"table\":\"test\"}]}}],\"strConnectors\":[],\"strPipelineName\":\"ppro1\",\"inPipelineId\":0,\"inKpiId\":1,\"inCategoryId\":1,\"strPipelineExeURL\":\"test\"}";
            ClientResponse response = webResource.type(strReqProperty)
                    .post(ClientResponse.class, strInput);
            System.out.println("Output from POST Server .... \n");
            object = response.getEntity(Object.class);
            LOGGER.debug(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                    + "LivyExecutorImpl : executePost function : object is " + object);
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getMessage()
                    + "LivyExecutorImpl : in executePost function : ", e);
        }
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + " LivyExecutorImpl : executePost function : " + Constants.END_STATUS);
        return object;
    }

    /**
     * Method to execute the Get request.
     *
     * @param webResource
     * @param strReqProperty
     * @return object
     */
    private Object executeGet(final WebResource webResource, final String
            strReqProperty) {
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + " LivyExecutorImpl : executeGet function : "
                + Constants.START_STATUS);
        Object object = null;
        try {
            ClientResponse response = webResource.accept(strReqProperty).type(strReqProperty)
                    .get(ClientResponse.class);
            System.out.println("Output from GET Server .... \n");
            object = response.getEntity(Object.class);
            LOGGER.debug(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                    + " LivyExecutorImpl : executeGet function : object is "
                    + object);
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getMessage()
                    + " LivyExecutorImpl : in executeGet function : ", e);
        }
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + " LivyExecutorImpl : executeGet function : " + Constants.END_STATUS);
        return object;
    }

    /**
     * Method to execute the Delete request.
     *
     * @param webResource
     * @param strReqProperty
     * @return object
     */
    private Object executeDelete(final WebResource webResource, final String strReqProperty) {
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + " LivyExecutorImpl : executeDelete function : " + Constants
                .START_STATUS);
        Object object = null;
        try {
            ClientResponse response = webResource.accept(strReqProperty)
                    .delete(ClientResponse.class);
            System.out.println("Output from DELETE Server .... \n");
            object = response.getEntity(Object.class);
            LOGGER.debug(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                    + "LivyExecutorImpl : executeDelete function : object is "
                    + object);
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.EXECUTOR_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .EXECUTOR_LAYER_EXCEPTION.getMessage()
                    + " LivyExecutorImpl : in executeDelete function : ", e);
        }
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + " LivyExecutorImpl : executeDelete function : "
                + Constants.END_STATUS);
        return object;
    }
}



