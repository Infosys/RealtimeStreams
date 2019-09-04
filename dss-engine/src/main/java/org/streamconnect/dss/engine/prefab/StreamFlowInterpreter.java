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

import com.google.gson.Gson;
import org.streamconnect.dss.dto.BusinessSqlDto;
import org.streamconnect.dss.dto.ColumnMetadataDto;
import org.streamconnect.dss.dto.PipelineConfigDto;
import org.streamconnect.dss.dto.PipelineDetails;
import org.streamconnect.dss.engine.audit.IAuditProcesser;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.InterpreterException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * StreamConnect - Basic flow Interpreter for code generation.
 *
 * @version 1.0
 *
 */


@Service
public abstract class StreamFlowInterpreter implements IAuditProcesser {

    /** The logger. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(StreamFlowInterpreter.class);

    /** The pipeline. */
    @Autowired
    public Pipeline pipeline;

    /** Instantiating the Pipeline Configuration. */
     public PipelineConfigDto pipelineConf = new PipelineConfigDto();

    /**
     * Progess flow.
     *
     * @return the string
     */
    public String showPipeline() {
        LOGGER.info("Pipe Line Information - " + pipeline.toString());
        return pipeline.toString();
    }

    /**
     * abstract function to configure prerequisites.
     *
     * @param objPipelineConf
     *            the obj pipeline conf
     * @return the prerequisite
     */
    public abstract boolean getPrerequisite(PipelineConfigDto objPipelineConf);

    /**
     * abstract function to configure data pipeline source.
     *
     * @return true, if successful
     */
    public abstract boolean buildSource();

    /**
     * abstract function to configure data pipeline engine.
     *
     * @return true, if successful
     */
    public abstract boolean buildEngine();

    /**
     * abstract function to configure data pipeline sink.
     *
     * @return true, if successful
     */
    public abstract boolean buildSink();

    /**
     * abstract function to configure data pipeline process logic.
     *
     * @return true, if successful
     */
    public abstract boolean buildProcess();

    /**
     * abstract function to create portable data pipeline executable.
     *
     * @return the data pipeline
     */
    public abstract PipelineDetails getDataPipeline();

    /**
     * function to store raw source data information.
     *
     * @return true, if successful
     */
    public boolean buildRawSrcProcess() {
        return true;
    }

    /**
     * function to store data process error related information.
     *
     * @return true, if successful
     */
    public boolean buildErrorHandleProcess() {
        return true;
    }

    /**
     * Method for formatting Cluster details.
     *
     * @param nodeDetails
     *            the node details
     * @return String
     */
    public String formatClusterDetails(final String nodeDetails) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "StreamFlowInterpreter : formatClusterDetails function : "
                + Constants.START_STATUS);
        String strCluster = "";
        try {
            String[] nodeArray = nodeDetails.split(",");
            for (int nInd = 0; nInd < nodeArray.length; nInd++) {
                if (0 == nInd) {
                    strCluster = "'" + nodeArray[nInd] + "'";
                } else {
                    strCluster += ",'" + nodeArray[nInd] + "'";
                }
            }
        } catch (Exception e) {
            LOGGER.error("Exception in StreamFlowInterpreter: formatNodList "
                    + "function:", e);
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "StreamFlowInterpreter : formatClusterDetails function : "
                + Constants.END_STATUS);
        return strCluster;
    }

    /**
     * Method for getting window based list.
     *
     * @param inpBusinessSqlLst
     *            the inp business sql lst
     * @return Map<String,List<BusinessSqlDto>>
     */
    public Map<String, List<BusinessSqlDto>> getWindowBasedList(
            final List<BusinessSqlDto> inpBusinessSqlLst) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "StreamFlowInterpreter : getWindowBasedList function : "
                + Constants.START_STATUS);
        Map<String, List<BusinessSqlDto>> groupedWindowMap = null;
        try {
            if (inpBusinessSqlLst != null && !inpBusinessSqlLst.isEmpty()) {
                LOGGER.debug(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                        + " StreamFlowInterpreter: getWindowBasedList function "
                        + ": Business SQL List Size is "
                        + inpBusinessSqlLst.size());
                groupedWindowMap = new HashMap<String, List<BusinessSqlDto>>();
                for (BusinessSqlDto businessSqlDto : inpBusinessSqlLst) {
                    if (groupedWindowMap
                            .containsKey(businessSqlDto.getWindowName())) {
                        List<BusinessSqlDto> winList = groupedWindowMap
                                .get(businessSqlDto.getWindowName());
                        winList.add(businessSqlDto);
                    } else {
                        List<BusinessSqlDto> winList = new ArrayList<BusinessSqlDto>();
                        winList.add(businessSqlDto);
                        groupedWindowMap.put(businessSqlDto.getWindowName(),
                                winList);
                    }
                }
                LOGGER.debug(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                        + " StreamFlowInterpreter: getWindowBasedList function "
                        + ": Window Map Size is " + groupedWindowMap.size());
                sortMaPInnerList(groupedWindowMap);
            } else {
                LOGGER.debug(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                        + " StreamFlowInterpreter: getWindowBasedList function "
                        + ": Business SQL List Size is 0");
                LOGGER.debug(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                        + " StreamFlowInterpreter: getWindowBasedList function "
                        + ": Window Map Size is 0");
            }

        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  "
                            + "PipelineConstruct: in getWindowBasedList function : ",
                    e);
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "StreamFlowInterpreter : getWindowBasedList function : "
                + Constants.END_STATUS);
        return groupedWindowMap;
    }

    /**
     * Method for sorting map list.
     *
     * @param groupedWindowMap the grouped window map
     */
    public void sortMaPInnerList(
            final Map<String, List<BusinessSqlDto>> groupedWindowMap) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "StreamFlowInterpreter : sortMaPInnerList function : "
                + Constants.START_STATUS);
        try {
            if (groupedWindowMap != null && !groupedWindowMap.isEmpty()) {
                LOGGER.debug(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                        + " StreamFlowInterpreter: sortMaPInnerList function : "
                        + "Window Map Size is " + groupedWindowMap.size());
                for (Map.Entry<String, List<BusinessSqlDto>> entry : groupedWindowMap
                        .entrySet()) {
                    sortBusinessSqlList(entry.getValue());
                }
            } else {
                LOGGER.debug(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                        + " StreamFlowInterpreter: sortMaPInnerList function : "
                        + "Window Map Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  "
                            + "PipelineConstruct: in sortMaPInnerList function : ",
                    e);
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "StreamFlowInterpreter : sortMaPInnerList function : "
                + Constants.END_STATUS);

    }

    /**
     * Method for sorting business sql list.
     *
     * @param businessSqlDtos
     *            the business sql dtos
     */
    public void sortBusinessSqlList(final List<BusinessSqlDto>
                                            businessSqlDtos) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "StreamFlowInterpreter : sortBusinessSqlList function : "
                + Constants.START_STATUS);
        try {
            if (businessSqlDtos != null && !businessSqlDtos.isEmpty()) {
                LOGGER.debug(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                        + " StreamFlowInterpreter: sortBusinessSqlList function"
                        + " : Business SQL Dto List Size is "
                        + businessSqlDtos.size());
                Collections.sort(businessSqlDtos,
                        new Comparator<BusinessSqlDto>() {
                            public int compare(
                                    final BusinessSqlDto objBusinessSqlDto1,
                                    final BusinessSqlDto objBusinessSqlDto2) {
                                int value = objBusinessSqlDto1.getWindowName()
                                        .compareTo(objBusinessSqlDto2
                                                .getWindowName());
                                value += String
                                        .valueOf(objBusinessSqlDto1
                                                .getInPredecessor())
                                        .compareTo(String
                                                .valueOf(objBusinessSqlDto2
                                                        .getInPredecessor()));
                                return value;
                            }
                        });
            } else {
                LOGGER.debug(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                        + " StreamFlowInterpreter: sortBusinessSqlList function"
                        + " : Business SQL Dto List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  "
                            + "PipelineConstruct: in sortBusinessSqlList function : ",
                    e);
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "StreamFlowInterpreter : sortBusinessSqlList function : "
                + Constants.END_STATUS);
    }


    /**
     * Method to create process query
     * @param dataScehmaObj
     * @return
     */
    public String genProcessQuery(final Object dataScehmaObj){
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " StreamFlowInterpreter : getProcessQuery function : "
                + Constants.START_STATUS);
        String processQuery = "";

        try{
            String initialTableName = pipelineConf.getStrInitialTableName();
            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            mapper.configure(
                   DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            String sourceSchemaJson = gson.toJson(dataScehmaObj);
            List<ColumnMetadataDto> tableColumnProp = mapper.readValue(
                    sourceSchemaJson,
                    new TypeReference<List<ColumnMetadataDto>>() {
                    });

            if (tableColumnProp.size() > 0) {

                processQuery = "select ";
                int i = 0;
                for (ColumnMetadataDto schemaObj : tableColumnProp) {

                    String fieldName = schemaObj.getColumnName();
                    processQuery+=fieldName;

                    if (tableColumnProp.size() - 1 != i) {
                        processQuery += ",";
                    }
                    i++;
                }
            }
            processQuery+= " from "+initialTableName;
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  StreamFlowInterpreter: in getProcessQuery function : ",
                    e);
            throw new InterpreterException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage()
                + " StreamFlowInterpreter : getProcessQuery function : "
                + Constants.END_STATUS);

        return processQuery;
    }


}

