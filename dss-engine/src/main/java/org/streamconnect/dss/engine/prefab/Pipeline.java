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
package org.streamconnect.dss.engine.prefab;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.streamconnect.dss.dto.UserFunctionDto;
import org.streamconnect.dss.metadata.connection.tx.UserFunctionTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.streamconnect.dss.dto.PipelineDetails;
import org.streamconnect.dss.dto.TemplateDto;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.StreamTxnException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.metadata.connection.tx.DashboardTransaction;
import org.streamconnect.dss.util.Constants;
import org.streamconnect.dss.util.HdfsFileSystemUtil;
import org.streamconnect.dss.util.PropReader;

/**
 * Basic object that streamconnect framework used for building the usecase with
 * various combination of sources , process , sink etc.
 */

@Service
public class Pipeline {

    /** The buf pipeline data. */
    private StringBuffer bufPipelineData = null;

    /** The features. */
    private List features = new ArrayList();

    /** The pipeline components. */
    private Map<String, String> pipelineComponents = new HashMap<String, String>();

    /** The dashboard transaction. */
    @Autowired
    private DashboardTransaction dashboardTransaction;

    /** The dashboard transaction. */
    @Autowired
    private UserFunctionTransaction userFunctionTransaction;

    /** The logger. */
    private static final DSSLogger LOGGER = DSSLogger.getLogger(Pipeline.class);

    /**
     * Instantiates a new pipeline.
     */
    public Pipeline() {
    }

    /**
     * @param newBufPipelineData
     */
    public Pipeline(final StringBuffer newBufPipelineData) {
        this.bufPipelineData = newBufPipelineData;
    }

    /**
     * Sets the interpreter template.
     *
     * @param objInterpreterType the obj interpreter type
     * @return true, if successful
     */
    public boolean setInterpreterTemplate(final InterpreterType objInterpreterType) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "Pipeline: setInterpreterTemplate function : "
                + Constants.START_STATUS);
        boolean bRet = false;
        try {
            // objectMapperEngine.generateJsonSchema(null);
            TemplateDto templateDto = dashboardTransaction.getTemplateFile(
                    objInterpreterType.getStrSourceType(),
                    objInterpreterType.getStrProcessType(),
                    objInterpreterType.getStrSinkType(),
                    objInterpreterType.getStrDataType());
            if (templateDto != null) {
                LOGGER.debug(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                        + " Pipeline: setInterpreterTemplate function : "
                        + "Template File : Template id = "
                        + templateDto.getInTemplateId());
                byte[] templateArray = templateDto.getBytesTemplateFileOne();
                String strTemplateDtls = new String(templateArray, "UTF-8");
                if ((null != strTemplateDtls) && !strTemplateDtls.isEmpty()) {
                    bufPipelineData = new StringBuffer(strTemplateDtls);
                    bRet = true;
                } else {
                    bRet = false;
                }
            } else {
                LOGGER.debug(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                        + " Pipeline: setInterpreterTemplate function : "
                        + "Template File is null");
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + " Pipeline : in "
                            + "setInterpreterTemplate function : ",
                    e);
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + " Pipeline : in "
                            + "setInterpreterTemplate function : ",
                    e);
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "Pipeline: setInterpreterTemplate function : "
                + Constants.END_STATUS);
        return bRet;
    }

    /**
     * Sets the buf pipeline data.
     *
     * @param bufPipelineData the new buf pipeline data
     */
    public void setBufPipelineData(final StringBuffer bufPipelineData) {
        this.bufPipelineData = bufPipelineData;
    }

    /**
     * Gets the buf pipeline data.
     *
     * @return the buf pipeline data
     */
    public StringBuffer getBufPipelineData() {
        return bufPipelineData;
    }

    /**
     * Sets the interpreter process.
     *
     * @param key the key
     * @param value the value
     */
    public void setInterpreterProcess(final String key, final String value) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "Pipeline: setInterpreterProcess function : "
                + Constants.START_STATUS);
        try {
            pipelineComponents.put(key, value);
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + " Pipeline : in "
                            + "setInterpreterProcess function : ",
                    e);
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "Pipeline: setInterpreterProcess function : "
                + Constants.END_STATUS);
    }

    /**
     * to generate data pipeline.
     *
     * @param strPipelineName the str pipeline name
     * @return the pipeline details
     */
    public PipelineDetails generatePipeline(final String strPipelineName) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "Pipeline: generatePipeline function : "
                + Constants.START_STATUS);
        PipelineDetails retPipeDtls = new PipelineDetails();
        String strPipelineUrl = "";
        try {
            for (Map.Entry<String, String> entry : pipelineComponents
                    .entrySet()) {
                replaceStr(entry.getKey(), entry.getValue());
            }
            SimpleDateFormat sdf1 = new SimpleDateFormat("MMddyyyyhhmmss");
            String date1 = sdf1.format(new Date());
            String strFStype = PropReader.getPropertyValue(Constants.FS_TYPE);

            if (strFStype.equalsIgnoreCase("hdfs")) {
                strPipelineUrl = HdfsFileSystemUtil.createHdfsFile(
                        bufPipelineData.toString(),
                        strPipelineName + "_" + date1 + ".py");
            } else if (strFStype.equalsIgnoreCase("s3")) {
                strPipelineUrl = "";
            } else if (strFStype.equalsIgnoreCase("adls")) {
                strPipelineUrl = "";

            } else if (strFStype.equalsIgnoreCase("maprfs")) {
                strPipelineUrl = "";
            } else {
                strPipelineUrl = "";
            }

            retPipeDtls.setStrPipelineURl(strPipelineUrl);
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + " Pipeline : in "
                            + "generatePipeline function : ",
                    e);
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "Pipeline: generatePipeline function : "
                + Constants.END_STATUS);
        return retPipeDtls;
    }

    public List<UserFunctionDto> getUserFunctionDetails(){
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "Pipeline: getUserFunctionDetails function : "
                + Constants.START_STATUS);
        List<UserFunctionDto> userFunctionDtos = null;
        try {
            userFunctionDtos = userFunctionTransaction
                    .getUserFunctions();
            /*Gson gson = new Gson();
            strUserFunctions = gson.toJson(userFunctionDtos);*/
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage() + " Pipeline : in "
                            + "getUserFunctionDetails function : ", e);
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "Pipeline: getUserFunctionDetails function : "
                + Constants.END_STATUS);
        return userFunctionDtos;
    }

    /**
     * Logging the Features passed as arguments.
     *
     * @return the string
     */
    public String toString() {
        StringBuffer ff = new StringBuffer();
        return ff.toString();
    }

    /**
     * Replace str.
     *
     * @param strSource
     *            the str source
     * @param strDest
     *            the str dest
     */
    private void replaceStr(final String strSource, final String strDest) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "Pipeline: replaceStr function : " + Constants.START_STATUS);
        try {
            if ((null != bufPipelineData) && (null != strSource)
                    && (null != strDest)) {
                int nStart = bufPipelineData.lastIndexOf(strSource);
                if (-1 != nStart) {
                    bufPipelineData.replace(nStart, nStart + strSource.length(),
                            strDest);
                }
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + " Pipeline : in " + "replaceStr function : ",
                    e);
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " Pipeline: replaceStr function : " + Constants.END_STATUS);
    }
}
