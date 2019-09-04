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
package org.streamconnect.dss.api.ui.builder;

import com.google.gson.Gson;
import org.streamconnect.dss.access.cache.impl.CacheService;
import org.streamconnect.dss.api.service.IDashboardService;
import org.streamconnect.dss.api.ui.response.Process;
import org.streamconnect.dss.engine.InterpreterFactory;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.StreamEngineException;
import org.streamconnect.dss.exception.StreamExecutorException;
import org.streamconnect.dss.exception.StreamTxnException;
import org.streamconnect.dss.executor.factory.ExecutorFactory;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.CommonUtil;
import org.streamconnect.dss.util.Constants;
import com.sun.jersey.core.header.FormDataContentDisposition;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.streamconnect.dss.api.ui.response.*;
import org.streamconnect.dss.dto.*;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class DashboardBuilder.
 *
 */
@Component
public class DashboardBuilder extends BaseResponseBuilder {

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(DashboardBuilder.class);

    /** The dashboard service. */
    @Autowired
    private IDashboardService dashboardService;

    /** The executor factory. */
    @Autowired
    private ExecutorFactory executorFactory;

    /** The common util. */
    @Autowired
    private CommonUtil commonUtil;

    /** The interpreter factory. */
    @Autowired
    private InterpreterFactory interpreterFactory;

    /** The cache service. */
    @Autowired
    private CacheService cacheService;

    /**
     * Method for getting a source details by passing source id.
     *
     * @param sourceId
     *            the source id
     * @return String
     */
    public String getSourceData(final int sourceId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getSourceData function : "
                + Constants.START_STATUS);
        try {
            Source source = null;
            SourceDto sourceDto = dashboardService.getSourceData(sourceId);
            if (sourceDto != null) {
                source = new Source();
                source.setInSourceId(sourceDto.getInSourceId());
                source.setStrSourceConfigName(
                        sourceDto.getStrSourceConfigName());
                source.setStrSourceType(sourceDto.getStrSourceType());
                source.setObjSourceConfigDetails(
                        sourceDto.getObjSourceConfigDetails());
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " DashboardBuilder : getSourceData function : "
                        + Constants.END_STATUS);
                return toStringJsonify(source);
            } else {
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " DashboardBuilder : getSourceData function : "
                        + Constants.END_STATUS);
                return getContentBuildingError(Constants.SOURCE_FETCH_ERROR,
                        ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.SOURCE_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceData function : ",
                    e);
            return getContentBuildingError(Constants.SOURCE_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting source list.
     *
     * @return String
     */
    public String getSourceList() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getSourceList function : "
                + Constants.START_STATUS);
        try {
            List<IdNameVO> listSource = new ArrayList<IdNameVO>();
            List<IdNameDto> sourceDtoList = dashboardService.getSourceList();
            if (sourceDtoList != null) {
                for (IdNameDto sourceDto : sourceDtoList) {
                    IdNameVO idNameSource = new IdNameVO();
                    idNameSource.setId(sourceDto.getId());
                    idNameSource.setName(sourceDto.getName());
                    idNameSource.setType(sourceDto.getType());
                    listSource.add(idNameSource);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getSourceList function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(listSource);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.SOURCE_LIST_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.SOURCE_LIST_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for saving source details.
     *
     * @param source
     *            the source
     * @param token the token
     * @return String
     */
    public String saveSource(final Source source, final String token) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : saveSource function : "
                + Constants.START_STATUS);
        try {
            final UserSessionDto userSession = cacheService.getUserMap().get(token);
            boolean status = checkSourceExistOrNot(source.getInSourceId(), source
                    .getStrSourceConfigName(), userSession.getUserName());
            if(!status) {
                SourceDto sourceDto = new SourceDto();
                sourceDto.setInSourceId(source.getInSourceId());
                sourceDto.setStrSourceConfigName(source.getStrSourceConfigName());
                sourceDto.setStrSourceType(source.getStrSourceType());
                sourceDto.setObjSourceConfigDetails(
                        source.getObjSourceConfigDetails());
                sourceDto.setInUserId(userSession.getInUserId());
                status = dashboardService.saveSource(sourceDto);
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " DashboardBuilder : saveSource function : "
                        + Constants.END_STATUS);
                if (status) {
                    return getContentBuildingSuccessMessage(
                            Constants.SOURCE_SAVE_SUCCESS);
                } else {
                    return getContentBuildingError(Constants.SOURCE_SAVE_ERROR,
                            ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                    .toString());
                }
            } else {
                return getContentBuildingError(Constants.SOURCE_SAVE_ERROR
                        + ErrorMessageEnum
                                .ENTITY_NAME_EXIST_ERROR.getMessage(),
                        ErrorMessageEnum.ENTITY_NAME_EXIST_ERROR.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.SOURCE_SAVE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : saveSource function : ",
                    e);
            return getContentBuildingError(Constants.SOURCE_SAVE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Check source exist or not.
     *
     * @param inSourceId the in source id
     * @param sourceName the source name
     * @param userName the user name
     * @return true, if successful
     */
    private boolean checkSourceExistOrNot(int inSourceId, String sourceName,
                                         String userName) {
        boolean status = false;
        if(sourceName != null && !"".equals(sourceName.trim())
                && userName != null && !"".equals(userName.trim())){
            status = dashboardService.checkSourceExistOrNot(inSourceId,
                    sourceName.trim(), userName.trim());
        }
        return status;
    }

    /**
     * Method for getting sink details by passing sink id.
     *
     * @param sinkId
     *            the sink id
     * @return String
     */
    public String getSinkData(final int sinkId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getSinkData function : "
                + Constants.START_STATUS);
        try {
            SinkDto sinkDto = dashboardService.getSinkData(sinkId);
            Sink sink = new Sink();
            sink.setInSinkId(sinkDto.getInSinkId());
            sink.setStrSinkName(sinkDto.getStrSinkName());
            sink.setStrSinkType(sinkDto.getStrSinkType());
            sink.setObjSinkConfigDetails(sinkDto.getObjSinkConfigDetails());
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getSinkData function : "
                    + Constants.END_STATUS);
            return toStringJsonify(sink);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.SINK_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSinkData function : ",
                    e);
            return getContentBuildingError(Constants.SINK_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting sink list.
     *
     * @return String
     */
    public String getSinkList() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getSinkList function : "
                + Constants.START_STATUS);
        try {
            List<IdNameVO> listSink = new ArrayList<IdNameVO>();
            List<IdNameDto> sinkDtoList = dashboardService.getSinkList();
            if (sinkDtoList != null) {
                for (IdNameDto sinkDto : sinkDtoList) {
                    IdNameVO idNameSink = new IdNameVO();
                    idNameSink.setId(sinkDto.getId());
                    idNameSink.setName(sinkDto.getName());
                    idNameSink.setType(sinkDto.getType());
                    listSink.add(idNameSink);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getSinkList function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(listSink);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.SINK_LIST_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSinkList function : ",
                    e);
            return getContentBuildingError(Constants.SINK_LIST_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for saving sink details.
     *
     * @param sink
     *            the sink
     * @param token the token
     * @return String
     */
    public String saveSink(final Sink sink, final String token  ) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : saveSink function : "
                + Constants.START_STATUS);
        try {
            final UserSessionDto userSession = cacheService.getUserMap().get(token);
            boolean status = checkSinkExistOrNot(sink.getInSinkId(), sink
                    .getStrSinkName(), userSession.getUserName());
            if(!status) {
                SinkDto sinkDto = new SinkDto();
                sinkDto.setInSinkId(sink.getInSinkId());
                sinkDto.setStrSinkName(sink.getStrSinkName());
                sinkDto.setStrSinkType(sink.getStrSinkType());
                sinkDto.setObjSinkConfigDetails(sink.getObjSinkConfigDetails());
                sinkDto.setInUserId(userSession.getInUserId());
                status = dashboardService.saveSink(sinkDto);
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " DashboardBuilder : saveSink function : "
                        + Constants.END_STATUS);
                if (status) {
                    return getContentBuildingSuccessMessage(
                            Constants.SINK_SAVE_SUCCESS);
                } else {
                    return getContentBuildingError(Constants.SINK_SAVE_ERROR,
                            ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                    .toString());
                }
            } else {
                return getContentBuildingError(Constants.SINK_SAVE_ERROR
                        + ErrorMessageEnum
                                .ENTITY_NAME_EXIST_ERROR.getMessage(),
                        ErrorMessageEnum.ENTITY_NAME_EXIST_ERROR.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.SINK_SAVE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getMessage()
                    + " in DashboardBuilder : saveSink function : ", e);
            return getContentBuildingError(Constants.SINK_SAVE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Check sink exist or not.
     *
     * @param inSinkId the in sink id
     * @param sinkName the sink name
     * @param userName the user name
     * @return true, if successful
     */
    private boolean checkSinkExistOrNot(int inSinkId, String sinkName,
                                       String userName) {
        boolean status = false;
        if(sinkName != null && !"".equals(sinkName.trim())
                && userName != null && !"".equals(userName.trim())){
            status = dashboardService.checkSinkExistOrNot(inSinkId,
                    sinkName.trim(), userName.trim());
        }
        return status;
    }
    /**
     * Method for getting process details by passing process id.
     *
     * @param processId
     *            the process id
     * @return String
     */
    public String getProcessData(final int processId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getProcessData function : "
                + Constants.START_STATUS);
        try {
            ProcessDto processDto = dashboardService.getProcessData(processId);
            Process process = new Process();
            process.setInProcessId(processDto.getInProcessId());
            process.setStrProcessConfigName(
                    processDto.getStrProcessConfigName());
            process.setStrProcessType(processDto.getStrProcessType());
            process.setObjConfigDetails(processDto.getObjConfigDetails());
            process.setStrProcessQuery(processDto.getStrProcessQuery());
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getProcessData function : "
                    + Constants.END_STATUS);
            return toStringJsonify(process);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.PROCESS_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getProcessData function : ",
                    e);
            return getContentBuildingError(Constants.PROCESS_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting process list.
     *
     * @return String
     */
    public String getProcessList() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getProcessList function : "
                + Constants.START_STATUS);
        try {
            List<IdNameVO> listProcess = new ArrayList<IdNameVO>();
            List<IdNameDto> processDtoList = dashboardService.getProcessList();
            if (processDtoList != null) {
                for (IdNameDto processDto : processDtoList) {
                    IdNameVO idNameProcess = new IdNameVO();
                    idNameProcess.setId(processDto.getId());
                    idNameProcess.setName(processDto.getName());
                    idNameProcess.setType(processDto.getType());
                    listProcess.add(idNameProcess);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getProcessList function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(listProcess);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.PROCESS_LIST_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getProcessList function : ",
                    e);
            return getContentBuildingError(Constants.PROCESS_LIST_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for saving process details.
     *
     * @param process
     *            the process
     * @param token the token
     * @return String
     */
    public String saveProcess(final Process process, final String token) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : saveProcess function : "
                + Constants.START_STATUS);
        try {
            final UserSessionDto userSession = cacheService.getUserMap().get(token);
            boolean status = checkProcessExistOrNot(process.getInProcessId(), process
                    .getStrProcessConfigName(), userSession.getUserName());
            if(!status) {
                ProcessDto processDto = new ProcessDto();
                processDto.setInProcessId(process.getInProcessId());
                processDto
                        .setStrProcessConfigName(process.getStrProcessConfigName());
                processDto.setStrProcessQuery(process.getStrProcessQuery());
                processDto.setStrProcessType(process.getStrProcessType());
                processDto.setObjConfigDetails(process.getObjConfigDetails());
                processDto.setInUserId(userSession.getInUserId());
                status = dashboardService.saveProcess(processDto);
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " DashboardBuilder : saveProcess function : "
                        + Constants.END_STATUS);
                if (status) {
                    return getContentBuildingSuccessMessage(
                            Constants.PROCESS_SAVE_SUCCESS);
                } else {
                    return getContentBuildingError(Constants.PROCESS_SAVE_ERROR,
                            ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                    .toString());
                }
            } else {
                return getContentBuildingError(Constants.PROCESS_SAVE_ERROR
                        + ErrorMessageEnum
                                .ENTITY_NAME_EXIST_ERROR.getMessage(),
                        ErrorMessageEnum.ENTITY_NAME_EXIST_ERROR.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.PROCESS_SAVE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : saveProcess function : ",
                    e);
            return getContentBuildingError(Constants.PROCESS_SAVE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Check process exist or not.
     *
     * @param inProcessId the in process id
     * @param processName the process name
     * @param userName the user name
     * @return true, if successful
     */
    private boolean checkProcessExistOrNot(int inProcessId, String processName,
                                          String userName) {
        boolean status = false;
        if(processName != null && !"".equals(processName.trim())
                && userName != null && !"".equals(userName.trim())){
            status = dashboardService.checkProcessExistOrNot(inProcessId,
                    processName.trim(), userName.trim());
        }
        return status;
    }

    /**
     * Method for getting category details by passing category id.
     *
     * @param categoryId
     *            the category id
     * @return String
     */
    public String getCategoryData(final int categoryId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getCategoryData function : "
                + Constants.START_STATUS);
        try {
            CategoryDto categoryDto = dashboardService
                    .getCategoryData(categoryId);
            Category category = new Category();
            category.setInCategoryId(categoryDto.getInCategoryId());
            category.setStrCategoryName(categoryDto.getStrCategoryName());
            category.setStrCategoryDesc(categoryDto.getStrCategoryDesc());
            category.setDateFrom(categoryDto.getDateFrom());
            category.setDateTo(categoryDto.getDateTo());
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getCategoryData function : "
                    + Constants.END_STATUS);
            return toStringJsonify(category);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.CATEGORY_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getCategoryData function : ",
                    e);
            return getContentBuildingError(Constants.CATEGORY_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting category list.
     *
     * @return String
     */
    public String getCategoryList() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getCategoryList function : "
                + Constants.START_STATUS);
        try {
            List<IdNameVO> listCategory = new ArrayList<IdNameVO>();
            List<IdNameDto> categoryDtoList = dashboardService
                    .getCategoryList();
            if (categoryDtoList != null) {
                for (IdNameDto categoryDto : categoryDtoList) {
                    IdNameVO idNameProcess = new IdNameVO();
                    idNameProcess.setId(categoryDto.getId());
                    idNameProcess.setName(categoryDto.getName());
                    listCategory.add(idNameProcess);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getCategoryList function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(listCategory);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.CATEGORY_LIST_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getCategoryList function : ",
                    e);
            return getContentBuildingError(Constants.CATEGORY_LIST_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for saving category details.
     *
     * @param category
     *            the category
     * @param token the token
     * @return String
     */
    public String saveCategory(final Category category, final String token) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : saveCategory function : "
                + Constants.START_STATUS);
        try {
            final UserSessionDto userSession = cacheService.getUserMap().get(token);
            boolean status = checkCategoryExistOrNot(category.getInCategoryId(),
                    category.getStrCategoryName(), userSession.getUserName());
            if(!status) {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setInCategoryId(category.getInCategoryId());
                categoryDto.setStrCategoryName(category.getStrCategoryName());
                categoryDto.setStrCategoryDesc(category.getStrCategoryDesc());
                categoryDto.setDateFrom(category.getDateFrom());
                categoryDto.setDateTo(category.getDateTo());
                categoryDto.setInUserId(userSession.getInUserId());
                status = dashboardService.saveCategory(categoryDto);
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " DashboardBuilder : saveCategory function : "
                        + Constants.END_STATUS);
                if (status) {
                    return getContentBuildingSuccessMessage(
                            Constants.CATEGORY_SAVE_SUCCESS);
                } else {
                    return getContentBuildingError(Constants.CATEGORY_SAVE_ERROR,
                            ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                    .toString());
                }
            } else {
                return getContentBuildingError(Constants.CATEGORY_SAVE_ERROR
                        + ErrorMessageEnum
                                .ENTITY_NAME_EXIST_ERROR.getMessage(),
                        ErrorMessageEnum.ENTITY_NAME_EXIST_ERROR.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.CATEGORY_SAVE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : saveCategory function : ",
                    e);
            return getContentBuildingError(Constants.CATEGORY_SAVE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Check category exist or not.
     *
     * @param inCategoryId the in category id
     * @param categoryName the category name
     * @param userName the user name
     * @return true, if successful
     */
    private boolean checkCategoryExistOrNot(int inCategoryId, String categoryName,
                                            String userName) {
        boolean status = false;
        if(categoryName != null && !"".equals(categoryName.trim())
                && userName != null && !"".equals(userName.trim())){
            status = dashboardService.checkCategoryExistOrNot(inCategoryId,
                    categoryName.trim(), userName.trim());
        }
        return status;
    }

    /**
     * Method for getting pipeline details by passing pipeline id.
     *
     * @param pipelineId
     *            the pipeline id
     * @return String
     */
    public String getPipelineData(final int pipelineId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getPipelineData function : "
                + Constants.START_STATUS);
        try {
            PipelineDto pipelineDto = dashboardService
                    .getPipelineData(pipelineId);
            Pipeline pipeline = new Pipeline();
            pipeline.setInPipelineId(pipelineDto.getInPipelineId());
            pipeline.setStrPipelineName(pipelineDto.getStrPipelineName());
            pipeline.setNodes(
                    pipelineDto.getObjPipelineConfigDetails());
            pipeline.setInKpiId(pipelineDto.getInKpiId());
            pipeline.setInCategoryId(pipelineDto.getInCategoryId());
            pipeline.setEdges(pipelineDto.getStrConnectors());
            pipeline.setStrPipelineExeURL(pipelineDto.getStrPipelineExeURL());
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getPipelineData function : "
                    + Constants.END_STATUS);
            return toStringJsonify(pipeline);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getPipelineData function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getPipelineData function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting pipeline list.
     *
     * @return String
     */
    public String getPipelineList() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getPipelineList function : "
                + Constants.START_STATUS);
        try {
            List<IdNameVO> listPipeline = new ArrayList<IdNameVO>();
            List<IdNameDto> pipelineDtoList = dashboardService
                    .getPipelineList();
            if (pipelineDtoList != null) {
                for (IdNameDto pipelineDto : pipelineDtoList) {
                    IdNameVO idNamePipeline = new IdNameVO();
                    idNamePipeline.setId(pipelineDto.getId());
                    idNamePipeline.setName(pipelineDto.getName());
                    listPipeline.add(idNamePipeline);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getPipelineList function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(listPipeline);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_LIST_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getPipelineList function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_LIST_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for save pipeline details.
     *
     * @param pipeline
     *            the pipeline
     * @param token the token
     * @return String
     */
    public String savePipeline(final Pipeline pipeline, final String token) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : savePipeline function : "
                + Constants.START_STATUS);
        try {
            final UserSessionDto userSession = cacheService.getUserMap().get(token);
            boolean status = checkPipelineExistOrNot(pipeline.getInPipelineId(),
                    pipeline.getStrPipelineName(), userSession.getUserName());
            if(!status) {
                PipelineDto pipelineDto = new PipelineDto();
                pipelineDto.setInPipelineId(pipeline.getInPipelineId());
                pipelineDto.setStrPipelineName(pipeline.getStrPipelineName());
                pipelineDto.setInKpiId(pipeline.getInKpiId());
                pipelineDto.setInCategoryId(pipeline.getInCategoryId());
                pipelineDto.setObjPipelineConfigDetails(
                        pipeline.getNodes());
                pipelineDto.setStrConnectors(pipeline.getEdges());
                pipelineDto.setStrPipelineExeURL("");
                pipelineDto.setInUserId(userSession.getInUserId());
                PipelineConfigDto pipelineConfigDto = new PipelineConfigDto<Object>();
                pipelineConfigDto.setInCategoryId(pipeline.getInCategoryId());
                pipelineConfigDto.setInKpiId(pipeline.getInKpiId());
                pipelineConfigDto.setStrPipelineName(
                        removeSpecialCharacters(pipeline.getStrPipelineName()));
                pipelineConfigDto.setInPipelineId(pipeline.getInPipelineId());
                List<VisualizeDto> visualizeDtos = new ArrayList<VisualizeDto>();
                ArrayList<Integer> deletedVisualizations = new ArrayList<Integer>();
                getPipelineDataForExecution(pipelineConfigDto,
                        pipeline.getNodes(), visualizeDtos);
                composeDeletedVisualizationList(pipeline.getDeletedVisualizations(),
                        deletedVisualizations);
                PipelineDetails objPipeDtls = null;
                if (pipelineConfigDto.getStrSourceType() != null
                        && pipelineConfigDto.getStrProcessType() != null
                        && pipelineConfigDto.getStrSinkType() != null
                        && !"".equals(pipelineConfigDto.getStrSourceType())
                        && !"".equals(pipelineConfigDto.getStrProcessType())
                        && !"".equals(pipelineConfigDto.getStrSinkType())) {
                    objPipeDtls = interpreterFactory
                            .getDataPipeline(pipelineConfigDto);
                    if ("".equals(objPipeDtls.getStrPipelineURl())) {
                        return getContentBuildingError(Constants.PIPELINE_SAVE_ERROR,
                                ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                        .toString());
                    }
                    pipelineDto
                            .setStrPipelineExeURL(objPipeDtls.getStrPipelineURl());
                }
                pipelineDto
                        .setStrSourceType(pipelineConfigDto.getStrSourceType());
                pipelineDto.setStrProcessType(
                        pipelineConfigDto.getStrProcessType());
                pipelineDto.setStrSinkType(pipelineConfigDto.getStrSinkType());

                status = dashboardService.savePipeline(pipelineDto,
                        visualizeDtos, deletedVisualizations);
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " DashboardBuilder : savePipeline function : "
                        + Constants.END_STATUS);
                if (status) {
                    return getContentBuildingSuccessMessage(
                            Constants.PIPELINE_SAVE_SUCCESS);
                } else {
                    return getContentBuildingError(
                            Constants.PIPELINE_SAVE_ERROR,
                            ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                                    .getCode().toString());
                }
            } else {
                return getContentBuildingError(Constants.PIPELINE_SAVE_ERROR
                        + ErrorMessageEnum
                                .ENTITY_NAME_EXIST_ERROR.getMessage(),
                        ErrorMessageEnum.ENTITY_NAME_EXIST_ERROR.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_SAVE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (StreamEngineException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_SAVE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : savePipeline function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_SAVE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }
    /**
     * Check pipeline exist or not.
     *
     * @param inPipelineId the in pipeline id
     * @param pipelineName the pipeline name
     * @param userName the user name
     * @return true, if successful
     */
    private boolean checkPipelineExistOrNot(int inPipelineId, String pipelineName,
                                            String userName) {
        boolean status = false;
        if (pipelineName != null && !"".equals(pipelineName.trim()) && userName != null
                && !"".equals(userName.trim())) {
            status = dashboardService.checkPipelineExistOrNot(inPipelineId,
                    pipelineName.trim(), userName.trim());
        }
        return status;
    }

    /**
     * Method for remove special characters from a given string.
     *
     * @param data
     *            the data
     * @return String
     */
    private String removeSpecialCharacters(final String data) {
        String tempData = data.replaceAll("[^a-zA-Z0-9_]", "");
        return tempData;
    }

    /**
     * Check visualization exist or not.
     *
     * @param visualizeCheck the visualize check
     * @return true, if successful
     */
    public String checkVisualizationExistOrNot(final VisualizeCheck
                                                        visualizeCheck) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : checkVisualizationExistOrNot function : "
                + Constants.START_STATUS);
        try {
            boolean status = false;
            if(visualizeCheck != null && visualizeCheck
                    .getStrVisualizeName() != null
                    && !"".equals(visualizeCheck.getStrVisualizeName().trim())) {
                VisualizeCheckDto visualizeCheckDto = new VisualizeCheckDto();
                visualizeCheckDto.setInPipelineId(visualizeCheck.getInPipelineId());
                visualizeCheckDto.setInVisualizeEntityId(
                        visualizeCheck.getInVisualizeEntityId());
                visualizeCheckDto.setStrVisualizeName(
                        visualizeCheck.getStrVisualizeName());
                List<KpiDto> kpiDtoList = null;
                if(visualizeCheck.getKpiList() != null) {
                    kpiDtoList = new ArrayList<KpiDto>();
                    for(Kpi kpi : visualizeCheck.getKpiList()) {
                        KpiDto kpiDto = new KpiDto();
                        kpiDto.setInKpiId(kpi.getInKpiId());
                        kpiDto.setStrKpiName(kpi.getStrKpiName());
                        kpiDtoList.add(kpiDto);
                    }
                    visualizeCheckDto.setKpiList(kpiDtoList);
                }
                status = dashboardService.checkVisualizationExistOrNot
                        (visualizeCheckDto);
            }
            if(!status) {
                return getContentBuildingSuccessMessage(
                        Constants.VALID_VISUALIZATION);
            } else {
                return getContentBuildingError(Constants.VISUALIZATION_SAVE_ERROR
                                + ErrorMessageEnum
                                .ENTITY_NAME_EXIST_ERROR.getMessage(),
                        ErrorMessageEnum.ENTITY_NAME_EXIST_ERROR.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : checkVisualizationExistOrNot"
                            + " function : ", e);
            return getContentBuildingError(
                    Constants.VISUALIZATION_NAME_CHECK_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : checkVisualizationExistOrNot"
                            + " function : ", e);
            return getContentBuildingError(Constants.VISUALIZATION_NAME_CHECK_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting Kpi list.
     *
     * @return String
     */
    public String getKpiList() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getKpiList function : "
                + Constants.START_STATUS);
        try {
            List<IdNameVO> listKpi = new ArrayList<IdNameVO>();
            List<IdNameDto> kpiDtoList = dashboardService.getKpiList();
            if (kpiDtoList != null) {
                for (IdNameDto kpiDto : kpiDtoList) {
                    IdNameVO idNamevo = new IdNameVO();
                    idNamevo.setId(kpiDto.getId());
                    idNamevo.setName(kpiDto.getName());
                    listKpi.add(idNamevo);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getKpiList function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(listKpi);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.KPI_LIST_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getKpiList function : ",
                    e);
            return getContentBuildingError(Constants.KPI_LIST_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting kpi details by passing kpi id.
     *
     * @param inKpiId
     *            the in kpi id
     * @return String
     */
    public String getKpiData(final int inKpiId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getKpiData function : "
                + Constants.START_STATUS);
        try {
            KpiDto kpiDto = dashboardService.getKpiData(inKpiId);
            Kpi kpi = new Kpi();
            kpi.setInKpiId(kpiDto.getInKpiId());
            kpi.setStrKpiName(kpiDto.getStrKpiName());
            kpi.setStrKpiDesc(kpiDto.getStrKpiDesc());
            List<Category> categoryList = new ArrayList<Category>();
            for (CategoryDto categoryDto : kpiDto.getCategories()) {
                Category category = new Category();
                category.setInCategoryId(categoryDto.getInCategoryId());
                category.setStrCategoryName(categoryDto.getStrCategoryName());
                categoryList.add(category);
            }
            kpi.setCategories(categoryList);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getKpiData function : "
                    + Constants.END_STATUS);
            return toStringJsonify(kpi);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.KPI_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getKpiData function : ",
                    e);
            return getContentBuildingError(Constants.KPI_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for saving kpi details.
     *
     * @param kpi
     *            the kpi
     * @param token the token
     * @return String
     */
    public String saveKpi(final Kpi kpi, final String token) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : saveKpi function : "
                + Constants.START_STATUS);
        try {
            final UserSessionDto userSession = cacheService.getUserMap().get(token);
            boolean status = checkKpiExistOrNot(kpi.getInKpiId(), kpi
                            .getStrKpiName(), userSession.getUserName());
            if(!status){
                KpiDto kpiDto = new KpiDto();
                kpiDto.setInKpiId(kpi.getInKpiId());
                kpiDto.setStrKpiName(kpi.getStrKpiName());
                kpiDto.setStrKpiDesc(kpi.getStrKpiDesc());
                List<CategoryDto> categories = new ArrayList<CategoryDto>();
                CategoryDto categoryDto = null;
                for (Category category : kpi.getCategories()) {
                    categoryDto = new CategoryDto();
                    categoryDto.setInCategoryId(category.getInCategoryId());
                    categories.add(categoryDto);
                }

                kpiDto.setCategories(categories);
                kpiDto.setInUserId(userSession.getInUserId());
                status = dashboardService.saveKpi(kpiDto);
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " DashboardBuilder : saveKpi function : "
                        + Constants.END_STATUS);
                if (status) {
                    return getContentBuildingSuccessMessage(
                            Constants.KPI_SAVE_SUCCESS);
                } else {
                    return getContentBuildingError(Constants.KPI_SAVE_ERROR,
                            ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                    .toString());
                }
            } else {
                return getContentBuildingError(Constants.KPI_SAVE_ERROR
                                + ErrorMessageEnum
                                .ENTITY_NAME_EXIST_ERROR.getMessage(),
                        ErrorMessageEnum.ENTITY_NAME_EXIST_ERROR.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.KPI_SAVE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getMessage()
                    + " in DashboardBuilder : saveKpi function : ", e);
            return getContentBuildingError(Constants.KPI_SAVE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }
    /**
     * Check kpi exist or not.
     *
     * @param inKpiId  the in kpi id
     * @param kpiName the kpi name
     * @param userName the user name
     * @return true, if successful
     */
    private boolean checkKpiExistOrNot(int inKpiId, String kpiName, String userName){
        boolean status = false;
        if(kpiName != null && !"".equals(kpiName.trim())
                && userName != null && !"".equals(userName.trim())){
            status = dashboardService.checkKpiExistOrNot(inKpiId, kpiName.trim(),
                    userName.trim());
        }
        return status;
    }
    /**
     * Method for listing each category and all kpis under each category.
     *
     * @return String
     */
    public String getCategoryWithKpis() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getCategoryWithKpis function : "
                + Constants.START_STATUS);
        try {
            List<CategoryKpisDto> categoryKpisDtos = dashboardService
                    .getCategoryWithKpis();
            List<CategoryKpisVo> categoryKpisVos = new ArrayList<CategoryKpisVo>();
            if (categoryKpisDtos != null) {
                for (CategoryKpisDto categoryKpisDto : categoryKpisDtos) {
                    CategoryKpisVo categoryKpisVo = new CategoryKpisVo();
                    categoryKpisVo
                            .setInCategoryId(categoryKpisDto.getInCategoryId());
                    categoryKpisVo.setStrCategoryName(
                            categoryKpisDto.getStrCategoryName());
                    categoryKpisVo.setKpis(categoryKpisDto.getKpis());
                    categoryKpisVos.add(categoryKpisVo);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getCategoryWithKpis function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(categoryKpisVos);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.CATEGORY_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getCategoryWithKpis function : ",
                    e);
            return getContentBuildingError(Constants.CATEGORY_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for deleting source by passing source id.
     *
     * @param sourceId
     *            the source id
     * @return String
     */
    public String deleteSource(final int sourceId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : deleteSource function : "
                + Constants.START_STATUS);
        try {
            boolean status = dashboardService.deleteSource(sourceId);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : deleteSource function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(
                        Constants.SOURCE_DELETE_SUCCESS);
            } else {
                return getContentBuildingError(Constants.SOURCE_DELETE_ERROR,
                        ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.SOURCE_DELETE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : deleteSource function : ",
                    e);
            return getContentBuildingError(Constants.SOURCE_DELETE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for deleting sink by passing sink id.
     *
     * @param sinkId
     *            the sink id
     * @return String
     */
    public String deleteSink(final int sinkId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : deleteSink function : "
                + Constants.START_STATUS);
        try {
            boolean status = dashboardService.deleteSink(sinkId);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : deleteSink function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(
                        Constants.SINK_DELETE_SUCCESS);
            } else {
                return getContentBuildingError(Constants.SINK_DELETE_ERROR,
                        ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.SINK_DELETE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : deleteSink function : ",
                    e);
            return getContentBuildingError(Constants.SINK_DELETE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for deleting process by passing process id.
     *
     * @param processId
     *            the process id
     * @return String
     */
    public String deleteProcess(final int processId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : deleteProcess function : "
                + Constants.START_STATUS);
        try {
            boolean status = dashboardService.deleteProcess(processId);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : deleteProcess function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(
                        Constants.PROCESS_DELETE_SUCCESS);
            } else {
                return getContentBuildingError(Constants.PROCESS_DELETE_ERROR,
                        ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.PROCESS_DELETE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : deleteProcess function : ",
                    e);
            return getContentBuildingError(Constants.PROCESS_DELETE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for deleting category by passing category id.
     *
     * @param categoryId
     *            the category id
     * @return String
     */
    public String deleteCategory(final int categoryId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : deleteCategory function : "
                + Constants.START_STATUS);
        try {
            boolean status = dashboardService.deleteCategory(categoryId);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : deleteCategory function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(
                        Constants.CATEGORY_DELETE_SUCCESS);
            } else {
                return getContentBuildingError(Constants.CATEGORY_DELETE_ERROR,
                        ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.CATEGORY_DELETE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : deleteCategory function : ",
                    e);
            return getContentBuildingError(Constants.CATEGORY_DELETE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for deleting kpi by passing kpi id.
     *
     * @param kpiId
     *            the kpi id
     * @return String
     */
    public String deleteKpi(final int kpiId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : deleteKpi function : "
                + Constants.START_STATUS);
        try {
            boolean status = dashboardService.deleteKpi(kpiId);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : deleteKpi function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(
                        Constants.KPI_DELETE_SUCCESS);
            } else {
                return getContentBuildingError(Constants.KPI_DELETE_ERROR,
                        ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.KPI_DELETE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : deleteKpi function : ",
                    e);
            return getContentBuildingError(Constants.KPI_DELETE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for delete pipeline by passing pipeline id.
     *
     * @param pipelineId
     *            the pipeline id
     * @return String
     */
    public String deletePipeline(final int pipelineId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : deletePipeline function : "
                + Constants.START_STATUS);
        try {
            boolean status = dashboardService.deletePipeline(pipelineId);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : deletePipeline function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(
                        Constants.PIPELINE_DELETE_SUCCESS);
            } else {
                return getContentBuildingError(Constants.PIPELINE_DELETE_ERROR,
                        ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_DELETE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : deletePipeline function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_DELETE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting pipeline details by passing pipeline id.
     *
     * @param pipelineId
     *            the pipeline id
     * @return String
     */
    public String getPipelineDataForMonitoring(final int pipelineId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getPipelineDataForMonitoring function : "
                + Constants.START_STATUS);
        try {
            PipelineDto pipelineDto = dashboardService
                    .getPipelineData(pipelineId);
            Pipeline pipeline = new Pipeline();
            pipeline.setInPipelineId(pipelineDto.getInPipelineId());
            pipeline.setStrPipelineName(pipelineDto.getStrPipelineName());
            pipeline.setNodes(
                    pipelineDto.getObjPipelineConfigDetails());
            pipeline.setInKpiId(pipelineDto.getInKpiId());
            pipeline.setInCategoryId(pipelineDto.getInCategoryId());
            pipeline.setEdges(pipelineDto.getStrConnectors());
            pipeline.setStrPipelineExeURL(pipelineDto.getStrPipelineExeURL());

            PipelineLineage pipelineLineage = new PipelineLineage();
            pipelineLineage.setInPipelineId(pipeline.getInPipelineId());
            pipelineLineage.setStrPipelineName(pipeline.getStrPipelineName());
            pipelineLineage.setStrPipelineExeURL(pipeline.getStrPipelineExeURL());

            Gson gson = new Gson();
            Object objPipelineDetails = gson.fromJson
                    (String.valueOf(pipeline.getNodes()),
                            Object.class);
            String pipelineData = gson.toJson(objPipelineDetails);
            objectMapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            PipelineWorkflow pipelineWorkflow = objectMapper.readValue
                    (pipelineData, PipelineWorkflow.class);
            String nodeDetails = gson.toJson(pipelineWorkflow.getNodeDetails());
            List<PipelineWorkflowDetails> listPipelineWorkflowDetails = objectMapper
                    .readValue(nodeDetails,
                            new TypeReference<List<PipelineWorkflowDetails>>() {
                            });
            List<PipelineWorkflowDetails> listPipelineWorkflowDetailsTemp =
                    new ArrayList<PipelineWorkflowDetails>();

            Object objEdges = gson.fromJson
                    (String.valueOf(pipeline.getEdges()),
                            Object.class);
            String edges = gson.toJson(objEdges);
            objectMapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            List<PipelineEdge> pipelineEdgesList = objectMapper
                    .readValue(edges, new
                            TypeReference<List<PipelineEdge>>() {});
            List<PipelineEdge> pipelineEdgesListSink = new ArrayList
                    <PipelineEdge>();

            int connectorTop = 20000;
            int connectorBottom = 30000;
            int stepId = 40000;
            String  topConnector ="topConnector";
            String  bottomConnector ="bottomConnector";
            List<PipelineConnector> processToSinkConnectorsList = null;
            int processBottomConnectorId = 0;
            for (PipelineWorkflowDetails pipelineWorkflowDetails : listPipelineWorkflowDetails) {
                Map<String, Object> dataMap = new HashMap<String, Object>();
                String properties = gson
                        .toJson(pipelineWorkflowDetails.getProperties());
                if (!"visualize".equalsIgnoreCase(
                        pipelineWorkflowDetails.getName())) {
                    dataMap = objectMapper.readValue(properties,
                            new TypeReference<Map<String, Object>>() {
                            });
                }
                if (dataMap.get("process_type") != null) {
                    String connectors = gson
                            .toJson(pipelineWorkflowDetails
                                    .getConnectors());
                    processToSinkConnectorsList = objectMapper
                            .readValue(connectors,
                                    new TypeReference<List<PipelineConnector>>() {
                                    });
                    for(PipelineConnector pipelineConnector : processToSinkConnectorsList){
                        if("bottomConnector".equalsIgnoreCase(pipelineConnector.getType())){
                            processBottomConnectorId = pipelineConnector
                                    .getId();
                        }
                    }
                    properties = gson
                            .toJson(dataMap.get("process_transform_queries"));
                    List<QueryProcessDto> queryProcessDtoList = objectMapper
                            .readValue(properties,
                                    new TypeReference<List<QueryProcessDto>>() {
                                    });
                    PipelineWorkflowDetails pipelineWork = null;
                    for(QueryProcessDto queryProcessDto :queryProcessDtoList){
                        pipelineWork = new PipelineWorkflowDetails();
                        pipelineWork.setName("BusinessRule");
                        pipelineWork.setX(pipelineWorkflowDetails.getX());
                        pipelineWork.setY(pipelineWorkflowDetails.getY());
                        pipelineWork.setProperties(queryProcessDto);
                        stepId++;
                        pipelineWork.setId(stepId);
                        List<PipelineConnector> processToBqConnectorsList = new
                                ArrayList<PipelineConnector>();
                        PipelineConnector pipelineConnector = new
                                PipelineConnector();
                        connectorTop++;
                        pipelineConnector.setId(connectorTop);
                        pipelineConnector.setType(topConnector);
                        processToBqConnectorsList.add(pipelineConnector);
                        pipelineConnector = new PipelineConnector();
                        connectorBottom++;
                        pipelineConnector.setId(connectorBottom);
                        pipelineConnector.setType(bottomConnector);
                        processToBqConnectorsList.add(pipelineConnector);
                        pipelineWork.setConnectors(processToBqConnectorsList);
                        listPipelineWorkflowDetailsTemp.add(pipelineWork);
                        PipelineEdge pipelineEdge = new PipelineEdge();
                        if(queryProcessDto.getPredecessor() == 0) {
                            pipelineEdge.setSource(processBottomConnectorId);
                            pipelineEdge.setDestination(connectorTop);
                        } else {
                            pipelineEdge.setSource(connectorBottom - 1);
                            pipelineEdge.setDestination(connectorTop);
                        }
                        pipelineEdgesList.add(pipelineEdge);
                        if(queryProcessDto.isPersistEnabled()){
                            pipelineEdge = new PipelineEdge();
                            pipelineEdge.setSource(connectorBottom);
                            pipelineEdgesListSink.add(pipelineEdge);
                        }
                    }
                }
            }

            listPipelineWorkflowDetails.addAll(listPipelineWorkflowDetailsTemp);

            pipelineLineage.setNodes(listPipelineWorkflowDetails);

            int sinkTop = 0;
            for(PipelineEdge pipelineEdge : pipelineEdgesList){
                if(pipelineEdge.getSource() == processBottomConnectorId){
                    pipelineEdgesList.remove(pipelineEdge);
                    sinkTop = pipelineEdge.getDestination();
                    break;
                }
            }

            for(PipelineEdge pipelineEdge : pipelineEdgesListSink){
                pipelineEdge.setDestination(sinkTop);
            }

            pipelineEdgesList.addAll(pipelineEdgesListSink);
            pipelineLineage.setEdges(pipelineEdgesList);

            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getPipelineDataForMonitoring function : "
                    + Constants.END_STATUS);
            return toStringJsonify(pipelineLineage);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getPipelineDataForMonitoring function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getPipelineDataForMonitoring function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for get pipeline data for execution.
     *
     * @param pipelineConfigDto
     *            the pipeline config dto
     * @param pipelineDetails
     *            the pipeline details
     * @param visualizeDtos
     *            the visualize dtos
     * @return pipelineConfigDto
     */
    public PipelineConfigDto getPipelineDataForExecution(
            final PipelineConfigDto pipelineConfigDto,
            final Object pipelineDetails,
            final List<VisualizeDto> visualizeDtos) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getPipelineDataForExecution function : "
                + Constants.START_STATUS);
        try {
            Gson gson = new Gson();
            String pipelineData = gson.toJson(pipelineDetails);
            objectMapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            PipelineWorkflow pipelineWorkflow = objectMapper.readValue
                    (pipelineData, PipelineWorkflow.class);
            String nodeDetails = gson.toJson(pipelineWorkflow.getNodeDetails());
            List<PipelineWorkflowDetails> listPipelineWorkflowDetails = objectMapper
                    .readValue(nodeDetails,
                            new TypeReference<List<PipelineWorkflowDetails>>() {
                            });
            for (PipelineWorkflowDetails pipelineWorkflowDetails : listPipelineWorkflowDetails) {
                Map<String, Object> dataMap = new HashMap<String, Object>();
                String properties = gson
                        .toJson(pipelineWorkflowDetails.getProperties());
                if ("visualize".equalsIgnoreCase(
                        pipelineWorkflowDetails.getName())) {
                    List<VisualizeDto> visualizeList = objectMapper.readValue(
                            properties,
                            new TypeReference<List<VisualizeDto>>() {
                            });
                    if (visualizeList != null && !visualizeList.isEmpty()) {
                        for (VisualizeDto visualizeDto : visualizeList) {
                            visualizeDto.setInPipelineId(
                                    pipelineConfigDto.getInPipelineId());
                            visualizeDtos.add(visualizeDto);
                        }
                    }
                } else {
                    dataMap = objectMapper.readValue(properties,
                            new TypeReference<Map<String, Object>>() {
                            });
                }
                if (dataMap.get("source_type") != null) {
                    SourceDto source = new SourceDto();
                    source.setInSourceId(Integer
                            .valueOf((Integer) dataMap.get("source_id")));
                    source.setObjSourceConfigDetails(
                            dataMap.get("source_config_details"));
                    source.setStrSourceConfigName(
                            (String) dataMap.get("source_name"));
                    source.setStrSourceType(
                            (String) dataMap.get("source_type"));
                    pipelineConfigDto.setObjSource(source);
                    pipelineConfigDto.setStrSourceType(
                            (String) dataMap.get("source_type"));
                    pipelineConfigDto.setStrInitialTableName(
                            (String) dataMap.get("initialTableName"));
                    composeSchema(pipelineConfigDto, dataMap);
                }
                if (dataMap.get("process_type") != null) {
                    ProcessDto process = new ProcessDto();
                    process.setInProcessId(Integer
                            .valueOf((Integer) dataMap.get("process_id")));
                    process.setObjConfigDetails(dataMap.get("process_details"));
                    process.setStrProcessConfigName(
                            (String) dataMap.get("process_name"));
                    process.setStrProcessType(
                            (String) dataMap.get("process_type"));
                    // process.setMiniBatchInterval((Long)dataMap.get("batch_interval"));
                    pipelineConfigDto.setObjProcess(process);
                    pipelineConfigDto.setStrProcessType(
                            (String) dataMap.get("process_type"));
                    properties = gson
                            .toJson(dataMap.get("process_transform_queries"));
                    List<QueryProcessDto> queryProcessDto = objectMapper
                            .readValue(properties,
                                    new TypeReference<List<QueryProcessDto>>() {
                                    });
                    composeBusinessSqls(pipelineConfigDto, queryProcessDto,
                            gson);

                }
                if (dataMap.get("sink_type") != null) {
                    SinkDto sink = new SinkDto();
                    sink.setInSinkId(
                            Integer.valueOf((Integer) dataMap.get("sink_id")));
                    sink.setObjSinkConfigDetails(dataMap.get("sink_details"));
                    sink.setStrSinkName((String) dataMap.get("sink_name"));
                    sink.setStrSinkType((String) dataMap.get("sink_type"));
                    pipelineConfigDto.setObjSink(sink);
                    pipelineConfigDto
                            .setStrSinkType((String) dataMap.get("sink_type"));
                    composeSinkSchemaDetails(pipelineConfigDto, dataMap, gson);
                }

            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getPipelineDataForExecution function : "
                    + Constants.END_STATUS);
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getPipelineDataForExecution function : ",
                    e);
        }
        return pipelineConfigDto;
    }

    /**
     * Method for composing business sqls.
     *
     * @param pipelineConfigDto
     *            the pipeline config dto
     * @param queryProcessDtos
     *            the query process dtos
     * @param gson
     *            the gson
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private void composeBusinessSqls(final PipelineConfigDto pipelineConfigDto,
                                     final List<QueryProcessDto> queryProcessDtos, final Gson gson)
            throws IOException {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : composeBusinessSqls function : "
                + Constants.START_STATUS);
        try {
            if (queryProcessDtos != null) {
                List<BusinessSqlDto> businessSqlDtos = new ArrayList<BusinessSqlDto>();
                for (QueryProcessDto queryProcess : queryProcessDtos) {
                    String columns = gson.toJson(queryProcess.getColumns());
                    List<EntitySchema> entitySchemas = objectMapper.readValue(
                            columns, new TypeReference<List<EntitySchema>>() {
                            });
                    BusinessSqlDto businessSqlDto = new BusinessSqlDto();
                    businessSqlDto
                            .setInPredecessor(queryProcess.getPredecessor());
                    businessSqlDto.setSave(queryProcess.isPersistEnabled());
                    businessSqlDto.setStrQuery(queryProcess.getQuery());
                    businessSqlDto.setStrTableName(queryProcess.getTable());
                    businessSqlDto
                            .setWindowPeriod(queryProcess.getWindowPeriod());
                    businessSqlDto.setSlidingInterval(
                            queryProcess.getSlidingInterval());
                    businessSqlDto.setQuerySinkDetails(
                            queryProcess.getQuerySinkDetails());
                    businessSqlDto
                            .setStrBusinessRule(queryProcess.getBusinessRule());
                    businessSqlDto.setWindowName(queryProcess.getWindowName());
                    businessSqlDto.setSchemaList(entitySchemas);
                    businessSqlDtos.add(businessSqlDto);
                }
                pipelineConfigDto.setObjBusinessSqlDto(businessSqlDtos);
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : composeBusinessSqls function : "
                    + Constants.END_STATUS);
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : composeBusinessSqls function : ",
                    e);
        }

    }

    /**
     * Method for composing source schema.
     *
     * @param pipelineConfigDto
     *            the pipeline config dto
     * @param dataMap
     *            the data map
     */
    private void composeSchema(final PipelineConfigDto pipelineConfigDto,
                               final Map<String, Object> dataMap) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : composeSchema function : "
                + Constants.START_STATUS);
        try {
            SchemaDto schemaDto = new SchemaDto();
            schemaDto.setObjSchema(dataMap.get("source_schema"));
            schemaDto.setStrSchemaType(
                    (String) dataMap.get("source_schema_type"));
            if (dataMap.get("source_delimitter") != null) {
                schemaDto.setStrSchemaDelimitor(
                        (String) dataMap.get("source_delimitter"));
            }
            if (dataMap.get("schema_file_Content") != null) {
                schemaDto.setObjSampleData(
                        (String) dataMap.get("schema_file_Content"));
            }
            pipelineConfigDto.setStrDataSchema(schemaDto);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : composeSchema function : "
                    + Constants.END_STATUS);
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : composeSchema function : ",
                    e);
        }
    }

    /**
     * Method for composing sink schema details.
     *
     * @param pipelineConfigDto
     *            the pipeline config dto
     * @param dataMap
     *            the data map
     * @param gson
     *            the gson
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private void composeSinkSchemaDetails(
            final PipelineConfigDto pipelineConfigDto,
            final Map<String, Object> dataMap, final Gson gson)
            throws IOException {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : composeSinkSchemaDetails function : "
                + Constants.START_STATUS);
        try {
            BusinessSqlDto businessSqlDto = new BusinessSqlDto();
            if (dataMap.get("schemaList") != null) {
                String columns = gson.toJson(dataMap.get("schemaList"));
                List<EntitySchema> tableSchemas = objectMapper.readValue(
                        columns, new TypeReference<List<EntitySchema>>() {
                        });
                businessSqlDto.setSchemaList(tableSchemas);
                businessSqlDto.setStrTableName(
                        (String) dataMap.get("sink_target_table"));
                businessSqlDto.setStrQuery(
                        (String) dataMap.get("sink_selected_query"));
                pipelineConfigDto.setObjTargetTable(businessSqlDto);
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : composeSinkSchemaDetails function : "
                    + Constants.START_STATUS);
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : composeSinkSchemaDetails function : ",
                    e);
        }
    }

    /**
     * Method for listing all pipelines with category and kpi details.
     *
     * @return pipelineDtos
     */
    public String getPipelines() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getPipelines function : "
                + Constants.START_STATUS);
        try {
            List<Pipeline> pipelines = new ArrayList<Pipeline>();
            List<PipelineDto> pipelineDtos = dashboardService.getPipelines();
            if (pipelineDtos != null) {
                for (PipelineDto pipelineDto : pipelineDtos) {
                    Pipeline pipeline = new Pipeline();
                    pipeline.setInPipelineId(pipelineDto.getInPipelineId());
                    pipeline.setStrPipelineName(
                            pipelineDto.getStrPipelineName());
                    pipeline.setInKpiId(pipelineDto.getInKpiId());
                    pipeline.setStrKpiName(pipelineDto.getStrKpiName());
                    pipeline.setInCategoryId(pipelineDto.getInCategoryId());
                    pipeline.setStrCategoryName(
                            pipelineDto.getStrCategoryName());
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    String createdDate = df
                            .format(pipelineDto.getDatePipeline());
                    pipeline.setStrDateCreated(createdDate);
                    pipeline.setStrPipelineExeURL(
                            pipelineDto.getStrPipelineExeURL());
                    List<Category> categoryList = new ArrayList<Category>();
                    for (CategoryDto categoryDto : pipelineDto
                            .getCategoryList()) {
                        Category category = new Category();
                        category.setInCategoryId(categoryDto.getInCategoryId());
                        category.setStrCategoryName(
                                categoryDto.getStrCategoryName());
                        List<IdNameVO> kpiList = new ArrayList<IdNameVO>();
                        for (IdNameDto kpiDto : categoryDto.getKpiList()) {
                            IdNameVO idNameVO = new IdNameVO();
                            idNameVO.setId(kpiDto.getId());
                            idNameVO.setName(kpiDto.getName());
                            kpiList.add(idNameVO);
                        }
                        category.setKpiList(kpiList);
                        categoryList.add(category);
                    }
                    pipeline.setCategorySet(categoryList);
                    Object state = getPplStatus(pipelineDto.getInPipelineId(),
                            pipelineDto.getInExecPipelineId());
                    if (state != null) {
                        pipeline.setStrPplStatus(String.valueOf(state));
                    } else {
                        pipeline.setStrPplStatus(
                                Constants.PIPELINE_TERMINATE_STATUS);
                    }
                    pipelines.add(pipeline);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getPipelines function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(pipelines);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getPipelines function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method to get the Visualizations.
     *
     * @return the visualizations
     */
    public String getVisualizations() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getVisualizations function : "
                + Constants.START_STATUS);
        try {
            List<Category> categories = new ArrayList<Category>();
            List<CategoryDto> categoryDtoList = dashboardService
                    .getVisualizations();
            if (categoryDtoList != null && !categoryDtoList.isEmpty()) {
                for (CategoryDto categoryDto : categoryDtoList) {
                    Category category = new Category();
                    category.setInCategoryId(categoryDto.getInCategoryId());
                    category.setStrCategoryName(
                            categoryDto.getStrCategoryName());
                    if (categoryDto.getKpis() != null
                            && !categoryDto.getKpis().isEmpty()) {
                        List<Kpi> kpis = new ArrayList<Kpi>();
                        for (KpiDto kpiDto : categoryDto.getKpis()) {
                            Kpi kpi = new Kpi();
                            kpi.setInKpiId(kpiDto.getInKpiId());
                            kpi.setStrKpiName(kpiDto.getStrKpiName());
                            if (kpiDto.getVisualizations() != null
                                    && !kpiDto.getVisualizations().isEmpty()) {
                                List<Visualize> visualizations = new ArrayList<Visualize>();
                                for (VisualizeDto visualizeDto : kpiDto
                                        .getVisualizations()) {
                                    Visualize visualize = new Visualize();
                                    visualize.setInVisualizeId(
                                            visualizeDto.getInVisualizeId());
                                    visualize.setStrVisualizeName(
                                            visualizeDto.getStrVisualizeName());
                                    visualize
                                            .setStrVisualizeSubType(visualizeDto
                                                    .getStrVisualizeSubType());
                                    visualize.setStrVisualizeParentType(
                                            visualizeDto
                                                    .getStrVisualizeParentType());
                                    visualize.setStrVisualizeConfigDetails(
                                            visualizeDto
                                                    .getStrVisualizeConfigDetails());
                                    visualizations.add(visualize);
                                }
                                kpi.setVisualizations(visualizations);
                            }
                            kpis.add(kpi);
                        }
                        category.setKpis(kpis);
                    }
                    categories.add(category);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getVisualizations function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(categories);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.VISUALIZE_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getVisualizations function : ",
                    e);
            return getContentBuildingError(Constants.VISUALIZE_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method to execute pipeline.
     *
     * @param pipelineId
     *            the pipeline id
     * @return the string
     */
    public String executePipeline(final int pipelineId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : executePipeline function : "
                + Constants.START_STATUS);
        try {
            PipelineExecution pipelineExecution = new PipelineExecution();
            PipelineExecutionDto pipelineExecutionDto = dashboardService
                    .getPipelineExecutionData(pipelineId);
            Object objStatus = null;
            if(pipelineExecutionDto.getInExecPipelineId() != null) {
                objStatus = getPplStatus(pipelineExecutionDto.getInPipelineId(),
                    pipelineExecutionDto.getInExecPipelineId());
            }
             if (objStatus == null || "dead".equals(String.valueOf(objStatus))
                    || Constants.PIPELINE_TERMINATE_STATUS.equals(
                            String.valueOf(objStatus))) {
                objStatus = executorFactory
                        .executeDataPipeline(pipelineExecutionDto);
                Map<String, Object> dataMap = composeJsonToMap(objStatus);
                Object execId = dataMap.get("id");
                Object state = dataMap.get("state");
                if (state != null && execId != null) {
                    pipelineExecution.setInPipelineId(pipelineId);
                    pipelineExecution.setInExecPipelineId(String.valueOf(execId));
                    pipelineExecution
                            .setStrExecPipelineStatus(String.valueOf(state));
                    boolean status = dashboardService.savePipelineStatus(pipelineId,
                            String.valueOf(execId), String.valueOf(state));
                }
            } else {
                pipelineExecution.setInPipelineId(pipelineId);
                pipelineExecution.setInExecPipelineId(pipelineExecutionDto.getInExecPipelineId());
                pipelineExecution
                        .setStrExecPipelineStatus(String.valueOf(objStatus));
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : executePipeline function : "
                    + Constants.END_STATUS);
            return toStringJsonify(pipelineExecution);
        } catch (StreamExecutorException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_EXECUTION_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_EXECUTION_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : executePipeline function : ",
                    e);
            return getContentBuildingError(Constants.PIPELINE_EXECUTION_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method to stop Pipeline execution.
     *
     * @param pipelineId
     *            the pipeline id
     * @return the string
     */
    public String stopExecutePipeline(final int pipelineId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : stopExecutePipeline function : "
                + Constants.START_STATUS);
        try {
            PipelineExecutionDto pipelineExecutionDto = new
                    PipelineExecutionDto();
            String executionId = dashboardService
                    .getPipelineExecutionId(pipelineId);
            pipelineExecutionDto.setInExecPipelineId(executionId);
            Object objStatus = executorFactory
                    .stopDataPipeline(pipelineExecutionDto);
            Map<String, Object> dataMap = composeJsonToMap(objStatus);
            Object state = null;
            if (!dataMap.isEmpty()) {
                state = dataMap.get("msg");
            } else {
                state = Constants.PIPELINE_TERMINATE_STATUS;
            }
            PipelineExecution pipelineExecution = new PipelineExecution();
            if (state != null) {
                pipelineExecution.setInPipelineId(pipelineId);
                pipelineExecution.setInExecPipelineId(executionId);
                pipelineExecution
                        .setStrExecPipelineStatus(String.valueOf(state));
                boolean status = dashboardService.savePipelineStatus(pipelineId,
                        executionId, String.valueOf(state));
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : stopExecutePipeline function : "
                    + Constants.END_STATUS);
            return toStringJsonify(pipelineExecution);
        } catch (StreamExecutorException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(
                    Constants.PIPELINE_EXECUTION_STOP_ERROR + ". "
                            + Constants.CHECK_LOG, e.getErrorCode());
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(
                    Constants.PIPELINE_EXECUTION_STOP_ERROR + ". "
                            + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : stopExecutePipeline function : ",
                    e);
            return getContentBuildingError(
                    Constants.PIPELINE_EXECUTION_STOP_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method to get Pipeline Execution Status.
     *
     * @param pipelineId
     *            the pipeline id
     * @param execId
     *            the exec id
     * @return the pipeline execution status
     */
    public String getPipelineExecutionStatus(final int pipelineId,
                                             final String execId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getPipelineExecutionStatus function : "
                + Constants.START_STATUS);
        try {
            PipelineExecutionDto pipelineExecutionDto = new PipelineExecutionDto();
            pipelineExecutionDto.setInExecPipelineId(execId);
            Object state = getPplStatus(pipelineId, execId);
            PipelineExecution pipelineExecution = new PipelineExecution();
            if (state != null) {
                pipelineExecution.setInPipelineId(pipelineId);
                pipelineExecution.setInExecPipelineId(execId);
                pipelineExecution
                        .setStrExecPipelineStatus(String.valueOf(state));
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getPipelineExecutionStatus function : "
                    + Constants.END_STATUS);
            return toStringJsonify(pipelineExecution);
        } catch (StreamExecutorException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(
                    Constants.PIPELINE_EXECUTION_STATUS_ERROR + ". "
                            + Constants.CHECK_LOG, e.getErrorCode());
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(
                    Constants.PIPELINE_EXECUTION_STATUS_ERROR + ". "
                            + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getPipelineExecutionStatus function : ",
                    e);
            return getContentBuildingError(
                    Constants.PIPELINE_EXECUTION_STATUS_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting max value of inVisualizeEntityId for a particular
     * pipeline.
     *
     * @param pipelineId
     *            the pipeline id
     * @return String
     */
    public String getMaxVisualizationCount(final int pipelineId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getMaxVisualizationCount function : "
                + Constants.START_STATUS);
        try {
            int inMaxCount = dashboardService
                    .getMaxVisualizationCount(pipelineId);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getMaxVisualizationCount function : "
                    + Constants.END_STATUS);
            return toStringJsonify(inMaxCount);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(
                    Constants.VISUALIZE_COUNT_FETCH_ERROR + ". "
                            + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getMaxVisualizationCount function : ",
                    e);
            return getContentBuildingError(
                    Constants.VISUALIZE_COUNT_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method to compose Pipeline Execution Status.
     *
     * @param objStatus
     *            the obj status
     * @return dataMap
     */
    private Map<String, Object> composeJsonToMap(final Object objStatus) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : composeJsonToMap function : "
                + Constants.START_STATUS);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
            Gson gson = new Gson();
            String statusData = gson.toJson(objStatus);
            objectMapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            dataMap = objectMapper.readValue(statusData,
                    new TypeReference<Map<String, Object>>() {
                    });
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : composeJsonToMap function : ",
                    e);
        }
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : composeJsonToMap function : "
                + Constants.END_STATUS);
        return dataMap;
    }

    /**
     * Method to get Pipeline Execution Status.
     *
     * @param pipelineId
     *            the pipeline id
     * @param execId
     *            the exec id
     * @return state
     */
    private Object getPplStatus(final int pipelineId, final String execId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getPplStatus function : "
                + Constants.START_STATUS);
        Object state = null;
        try {
            PipelineExecutionDto pipelineExecutionDto = new PipelineExecutionDto();
            pipelineExecutionDto.setInExecPipelineId(execId);
            Object objStatus = executorFactory
                    .getDataPipelineStatus(pipelineExecutionDto);
            Map<String, Object> dataMap = composeJsonToMap(objStatus);
            if (!dataMap.isEmpty()) {
                state = dataMap.get("state");
            } else {
                state = Constants.PIPELINE_TERMINATE_STATUS;
            }
            boolean status = dashboardService.savePipelineStatus(pipelineId,
                    execId, String.valueOf(state));
        } catch (StreamExecutorException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(
                    Constants.PIPELINE_EXECUTION_STATUS_ERROR + ". "
                            + Constants.CHECK_LOG, e.getErrorCode());
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(
                    Constants.PIPELINE_EXECUTION_STATUS_ERROR + ". "
                            + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getPplStatus function : ",
                    e);
        }
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getPplStatus function : "
                + Constants.END_STATUS);
        return state;
    }

    /**
     * Method for composing deleted visualiztion list.
     *
     * @param delVisualizationsObj
     *            the del visualizations obj
     * @param deletedVisualizations
     *            the deleted visualizations
     */
    private void composeDeletedVisualizationList(
            final Object delVisualizationsObj,
            final List<Integer> deletedVisualizations) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : composeDeletedVisualizationList function : "
                + Constants.START_STATUS);
        try {
            Gson gson = new Gson();
            String pipelineData = gson.toJson(delVisualizationsObj);
            objectMapper.configure(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            List<VisualizeDto> delVisualizeDtoList = objectMapper.readValue(
                    pipelineData, new TypeReference<List<VisualizeDto>>() {
                    });
            for (VisualizeDto visualizeDto : delVisualizeDtoList) {
                deletedVisualizations
                        .add(visualizeDto.getInVisualizeEntityId());
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : composeDeletedVisualizationList function : ",
                    e);
        }
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : composeDeletedVisualizationList function : "
                + Constants.END_STATUS);
    }

    /**
     * Method to get sink datatypes.
     *
     * @param strSinkType
     *            the str sink type
     * @return String
     */
    public String getSinkDatatypes(final String strSinkType) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getSinkDatatypes function : "
                + Constants.START_STATUS);
        try {
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getSinkDatatypes function : "
                    + Constants.END_STATUS);
            return toStringJsonify(
                    dashboardService.getSinkDatatypes(strSinkType));
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants
                    .SINK_DATATYPES_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSinkDatatypes " +
                            "function : ", e);
            return getContentBuildingError(Constants.SINK_DATATYPES_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting the metadata of uploaded source schema.
     *
     * @param inputStream
     *            the input stream
     * @param strFileType
     *            the str file type
     * @param strDelimiter
     *            the str delimiter
     * @param isHeaderExists
     *            the is header exists
     * @return String
     */
    public String getMetadata(final InputStream inputStream,
                              final String strFileType, final String strDelimiter,
                              final boolean isHeaderExists) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getMetadata function : "
                + Constants.START_STATUS);
        try {
            byte[] inputData = commonUtil.toByteArray(inputStream);
            String inputString = new String(inputData, "UTF-8");
            List<ColumnMetadataDto> columnMetadataDtoList = dashboardService
                    .getColumnMetadataList(inputString, strFileType,
                            strDelimiter, isHeaderExists);
            List<ColumnMetadata> columnMetadataList = new ArrayList<ColumnMetadata>();
            for (ColumnMetadataDto columnMetadataDto : columnMetadataDtoList) {
                ColumnMetadata columnMetadata = new ColumnMetadata();
                columnMetadata.setColumnName(columnMetadataDto.getColumnName());
                columnMetadata.setColumnDataType(
                        columnMetadataDto.getColumnDataType());
                columnMetadataList.add(columnMetadata);
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getMetadata function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(columnMetadataList);
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getMetadata function : ",
                    e);
            return getContentBuildingError(Constants.METADATA_FIND_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for save or update lookup basic configuration.
     *
     * @param lookupDetails
     *            the lookup details
     * @return String
     */
    public String saveOrUpdateLookupBasicDetails(
            final LookupDetails lookupDetails, String token) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : saveOrUpdateLookupBasicDetails function : "
                + Constants.START_STATUS);
        try {

            final UserSessionDto userSession = cacheService.getUserMap().get(token);
            boolean status = checkLookupExistOrNot(lookupDetails.getInLookupId(),
                    lookupDetails.getLookupConfigName(), userSession.getUserName());
            if(!status) {
                LookupDetailsDto lookupDetailsDto = new LookupDetailsDto();
                lookupDetailsDto.setInLookupId(lookupDetails.getInLookupId());
                lookupDetailsDto
                        .setLookupConfigName(lookupDetails.getLookupConfigName());
                lookupDetailsDto.setLookupType(lookupDetails.getLookupType());
                lookupDetailsDto.setLookupConfigDetails(
                        lookupDetails.getLookupConfigDetails());
                ResponseDto responseDto = dashboardService
                        .saveOrUpdateLookupBasicDetails(lookupDetailsDto);
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " DashboardBuilder : saveOrUpdateLookupBasicDetails function : "
                        + Constants.END_STATUS);
                if (responseDto != null && responseDto.isStatus()) {
                    ResponseVO responseVO = new ResponseVO();
                    responseVO.setId(responseDto.getId());
                    responseVO.setMessage(Constants.LOOKUP_DETAILS_SAVE_SUCCESS);
                    return toStringJsonify(responseVO);
                } else {
                    return getContentBuildingError(
                            Constants.LOOKUP_DETAILS_SAVE_ERROR,
                            ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                    .toString());
                }
            }  else {
                return getContentBuildingError(Constants.LOOKUP_DETAILS_SAVE_ERROR
                                + ErrorMessageEnum
                                .ENTITY_NAME_EXIST_ERROR.getMessage(),
                        ErrorMessageEnum.ENTITY_NAME_EXIST_ERROR.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants
                    .LOOKUP_DETAILS_SAVE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : " +
                            "saveOrUpdateLookupBasicDetails function : ", e);
            return getContentBuildingError(Constants.LOOKUP_DETAILS_SAVE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Check lookup exist or not.
     *
     * @param inLookupId the in lookup id
     * @param lookupConfigName the lookup config name
     * @param userName the user name
     * @return true, if successful
     */
    public boolean checkLookupExistOrNot(int inLookupId, String lookupConfigName,
                                         String userName) {
        boolean status = false;
        if (lookupConfigName != null && !"".equals(lookupConfigName.trim())
                && userName != null && !"".equals(userName.trim())) {
            status = dashboardService.checkLookupExistOrNot(inLookupId,
                    lookupConfigName.trim(), userName.trim());
        }
        return status;
    }

    /**
     * Method for save or update lookup Advanced configurations.
     *
     * @param lookupId
     *            the lookup id
     * @param id
     *            the id
     * @param strSourceType
     *            the str source type
     * @param uploadedFile
     *            the uploaded file
     * @param strUploadedFileName
     *            the str uploaded file name
     * @param sinkId
     *            the sink id
     * @param strKeySpaceName
     *            the str key space name
     * @param strtableName
     *            the strtable name
     * @return String
     */
    public String saveOrUpdateLookupAdvancedDetails(final int lookupId,
                                                    final int id, final String strSourceType,
                                                    final InputStream uploadedFile,
                                                    final FormDataContentDisposition strUploadedFileName,
                                                    final int sinkId, final String strKeySpaceName,
                                                    final String strtableName) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : saveOrUpdateLookupAdvancedDetails function : "
                + Constants.START_STATUS);
        try {
            LookupAdvancedDetailsDto lookupAdvancedDetailsDto = new LookupAdvancedDetailsDto();
            lookupAdvancedDetailsDto.setInLookupId(lookupId);
            lookupAdvancedDetailsDto.setId(id);
            if (strUploadedFileName != null) {
                lookupAdvancedDetailsDto.setStrUploadedFileName(
                        strUploadedFileName.getFileName());
            }
            if (uploadedFile != null) {
                lookupAdvancedDetailsDto.setUploadedFile(uploadedFile);
            }
            lookupAdvancedDetailsDto.setSinkId(sinkId);
            lookupAdvancedDetailsDto.setStrSourceType(strSourceType);
            lookupAdvancedDetailsDto.setStrKeySpaceName(strKeySpaceName);
            lookupAdvancedDetailsDto.setStrtableName(strtableName);
            boolean status = dashboardService.saveOrUpdateLookupAdvancedDetails(
                    lookupAdvancedDetailsDto);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : saveOrUpdateLookupAdvancedDetails function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(
                        Constants.LOOKUP_DETAILS_SAVE_SUCCESS);
            } else {
                return getContentBuildingError(
                        Constants.LOOKUP_DETAILS_SAVE_ERROR,
                        ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants
                    .LOOKUP_DETAILS_SAVE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : saveOrUpdateLookupAdvancedDetails function : ",
                    e);
            return getContentBuildingError(Constants.LOOKUP_DETAILS_SAVE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting lookup basic and advancedDetails.
     *
     * @param inLookupId
     *            the in lookup id
     * @return String
     */
    public String getLookupDetails(final int inLookupId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getLookupDetails function : "
                + Constants.START_STATUS);
        try {
            LookupDetailsDto lookupDetailsDto = dashboardService
                    .getLookupDetails(inLookupId);
            LookupDetails lookupDetails = null;
            if (lookupDetailsDto != null) {
                lookupDetails = new LookupDetails();
                lookupDetails.setInLookupId(lookupDetailsDto.getInLookupId());
                lookupDetails.setLookupConfigName(
                        lookupDetailsDto.getLookupConfigName());
                lookupDetails.setLookupType(lookupDetailsDto.getLookupType());
                lookupDetails.setLookupConfigDetails(
                        lookupDetailsDto.getLookupConfigDetails());
                lookupDetails.setCreatedDate(lookupDetailsDto.getCreatedDate());
                lookupDetails.setUpdatedDate(lookupDetailsDto.getUpdatedDate());
                if (lookupDetailsDto.getLookupAdvancedDetailsDtos() != null) {
                    List<LookupAdvancedDetails> lookupAdvancedDetailsList = new ArrayList<LookupAdvancedDetails>();
                    for (LookupAdvancedDetailsDto lookup : lookupDetailsDto
                            .getLookupAdvancedDetailsDtos()) {
                        LookupAdvancedDetails lookupAdvancedDetails = new LookupAdvancedDetails();
                        lookupAdvancedDetails.setId(lookup.getId());
                        lookupAdvancedDetails
                                .setInLookupId(lookup.getInLookupId());
                        lookupAdvancedDetails
                                .setStrSourceType(lookup.getStrSourceType());
                        lookupAdvancedDetails.setSinkId(lookup.getSinkId());
                        lookupAdvancedDetails.setSinkName(lookup.getSinkName());
                        lookupAdvancedDetails.setStrKeySpaceName(
                                lookup.getStrKeySpaceName());
                        lookupAdvancedDetails
                                .setStrtableName(lookup.getStrtableName());
                        lookupAdvancedDetails.setStrUploadedFileName(
                                lookup.getStrUploadedFileName());
                        lookupAdvancedDetails
                                .setCreatedDate(lookup.getCreatedDate());
                        lookupAdvancedDetails
                                .setUpdatedDate(lookup.getUpdatedDate());
                        lookupAdvancedDetails
                                .setDeleteStatus(lookup.getDeleteStatus());
                        lookupAdvancedDetailsList.add(lookupAdvancedDetails);
                    }
                    lookupDetails.setLookupAdvancedDetails(
                            lookupAdvancedDetailsList);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getLookupDetails function : "
                    + Constants.END_STATUS);
            return toStringJsonify(lookupDetails);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function " +
                            ": ", e);
            return getContentBuildingError(Constants.LOOKUP_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getLookupDetails function : ",
                    e);
            return getContentBuildingError(Constants.LOOKUP_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for deleting Lookup by passing lookup id.
     *
     * @param inLookupId
     *            the in lookup id
     * @return String
     */
    public String deleteLookup(final int inLookupId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : deleteLookup function : "
                + Constants.START_STATUS);
        try {
            boolean status = dashboardService.deleteLookup(inLookupId);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : deleteLookup function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(
                        Constants.LOOKUP_DELETE_SUCCESS);
            } else {
                return getContentBuildingError(Constants.LOOKUP_DELETE_ERROR,
                        ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.LOOKUP_DELETE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : deleteLookup function : ",
                    e);
            return getContentBuildingError(Constants.LOOKUP_DELETE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for deleting LookupDetails by passing lookupDetails Id.
     *
     * @param lookupDetailsId
     *            the lookup details id
     * @return String
     */
    public String deleteLookupDetails(final int lookupDetailsId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : deleteLookupDetails function : "
                + Constants.START_STATUS);
        try {
            boolean status = dashboardService
                    .deleteLookupDetails(lookupDetailsId);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : deleteLookupDetails function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(
                        Constants.LOOKUP_DETAILS_DELETE_SUCCESS);
            } else {
                return getContentBuildingError(
                        Constants.LOOKUP_DETAILS_DELETE_ERROR,
                        ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(
                    Constants.LOOKUP_DETAILS_DELETE_ERROR + ". "
                            + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : deleteLookupDetails function : ",
                    e);
            return getContentBuildingError(
                    Constants.LOOKUP_DETAILS_DELETE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for listing all lookups.
     *
     * @return String
     */
    public String getAllLookups() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getAllLookups function : "
                + Constants.START_STATUS);
        try {
            List<LookupDetails> lookupList = null;
            List<LookupDetailsDto> lookupDetailsDtos = dashboardService
                    .getAllLookups();
            if (lookupDetailsDtos != null && !lookupDetailsDtos.isEmpty()) {
                lookupList = new ArrayList<LookupDetails>();
                for (LookupDetailsDto lookupDetailsDto : lookupDetailsDtos) {
                    LookupDetails lookupDetails = new LookupDetails();
                    lookupDetails
                            .setInLookupId(lookupDetailsDto.getInLookupId());
                    lookupDetails.setLookupConfigName(
                            lookupDetailsDto.getLookupConfigName());
                    lookupDetails
                            .setLookupType(lookupDetailsDto.getLookupType());
                    lookupDetails.setLookupConfigDetails(
                            lookupDetailsDto.getLookupConfigDetails());
                    lookupDetails
                            .setCreatedDate(lookupDetailsDto.getCreatedDate());
                    lookupDetails
                            .setUpdatedDate(lookupDetailsDto.getUpdatedDate());
                    lookupDetails.setStrUpdatedUser("");
                    lookupDetails.setStrCreatedUser("");
                    lookupList.add(lookupDetails);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getAllLookups function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(lookupList);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.LOOKUP_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getAllLookups function : ",
                    e);
            return getContentBuildingError(Constants.LOOKUP_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for testing redis connection status.
     *
     * @param redisHost
     *            the redis host
     * @param redisPort
     *            the redis port
     * @return String
     */
    public String testRedisConnection(final String redisHost,
                                      final int redisPort) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : testRedisConnection function : "
                + Constants.START_STATUS);
        try {
            boolean status = dashboardService.testRedisConnection(redisHost,
                    redisPort);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : testRedisConnection function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(Constants.RUNNING);
            } else {
                return getContentBuildingSuccessMessage(Constants.STOPPED);
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.REDIS_CONNECTION_ERROR +
                    ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : testRedisConnection function : ",
                    e);
            return getContentBuildingError(Constants.REDIS_CONNECTION_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting the Query Plan Status.
     *
     * @param queryPlanDto
     *            the query plan dto
     * @return the query plan status
     */
    public String getQueryPlanStatus(final QueryPlanDto queryPlanDto) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getQueryPlanStatus function : "
                + Constants.START_STATUS);
        try {
            QueryPlanResultDto queryPlanResultDto = dashboardService
                    .getQueryPlanStatus(queryPlanDto);
            QueryPlanResult queryPlanResult = null;
            if (queryPlanResultDto != null) {
                queryPlanResult = new QueryPlanResult();
                queryPlanResult.setIsValid(queryPlanResultDto.getIsValid());
                queryPlanResult.setStatusMessage(
                        queryPlanResultDto.getStatusMessage());
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getQueryPlanStatus function : "
                    + Constants.END_STATUS);
            return toStringJsonify(queryPlanResult);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            // return getContentBuildingError("Error While Tesing Redis
            // Connection",
            // ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode().toString());
            return getContentBuildingError(Constants.QUERY_PLAN_STATUS_ERROR +
                    ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getQueryPlanStatus function : ",
                    e);
            // return getContentBuildingError("Error In the Query",
            // ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode().toString());
            return getContentBuildingError(Constants.QUERY_PLAN_STATUS_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for validating the Sink Table Name.
     *
     * @param sinkValidationDto
     *            the sink validation dto
     * @return the string
     */
    public String isValidSinkTable(final SinkValidationDto sinkValidationDto) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : isValidSinkTable function : "
                + Constants.START_STATUS);
        try {
            boolean isValid = dashboardService
                    .isValidSinkTable(sinkValidationDto);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : isValidSinkTable function : "
                    + Constants.END_STATUS);
            if (isValid) {
                return getContentBuildingSuccessMessage(
                        Constants.TABLE_STATUS_YES);
            } else {
                return getContentBuildingSuccessMessage(
                        Constants.TABLE_STATUS_NO);
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSourceList function : ",
                    e);
            return getContentBuildingError(Constants.SINK_TABLE_STATUS_ERROR +
                    ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : isValidSinkTable function : ",
                    e);
            return getContentBuildingError(Constants.SINK_TABLE_STATUS_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }

    }

    /**
     * Method for testing host connection status.
     *
     * @param strHost
     *            the str host
     * @param nPort
     *            the n port
     * @return String
     */
    public String testHostConnection(final String strHost, final int nPort) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : testHostConnection function : "
                + Constants.START_STATUS);
        try {
            boolean status = commonUtil.isReachable(strHost, nPort);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : testHostConnection function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(
                        Constants.SUCCESS_MESSAGE);
            } else {
                return getContentBuildingSuccessMessage(Constants.ERROR_STATUS);
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : testHostConnection function : ",
                    e);
            return getContentBuildingError(Constants.HOST_CONNECTION_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for geting Cassandra Table and Uploaded CSV file Schema details,
     * Table data and CSV data for preview  by passing lookupDetails Id and
     * data required status.
     *
     * @param lookupDetailsId
     * @param isDataRequired
     * @return String
     */
    public String getSchemaAndContent(int lookupDetailsId, boolean
            isDataRequired) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getSchemaAndContent function : "
                + Constants.START_STATUS);
        try{
            PreviewData previewData = null;
            PreviewDataDto previewDataDto = dashboardService.getSchemaAndContent(
                    lookupDetailsId, isDataRequired);
            if(previewDataDto != null){
                previewData = new PreviewData();
                previewData.setDataList(previewDataDto.getDataList());
                previewData.setSchemaList(previewDataDto.getSchemaList());
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getSchemaAndContent function : "
                    + Constants.END_STATUS);
            return toStringJsonify(previewData);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSchemaAndContent function : ",
                    e);
            return getContentBuildingError(Constants.DATA_FETCHING_ERROR
                    + ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getSchemaAndContent function : ",
                    e);
            return getContentBuildingError(Constants.DATA_FETCHING_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting Lookup configurations ( id, name and type).
     *
     * @return String
     */
    public String getLookupConfigurations(){
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getLookupConfigurations function : "
                + Constants.START_STATUS);
        try {
            List<IdNameVO> lookupConfigurations = null;
            List<IdNameDto> lookupList = dashboardService.getLookupConfigurations();
            if (lookupList != null && !lookupList.isEmpty()) {
                lookupConfigurations = new ArrayList<IdNameVO>();
                for (IdNameDto lookupDto : lookupList) {
                    IdNameVO lookup = new IdNameVO();
                    lookup.setId(lookupDto.getId());
                    lookup.setName(lookupDto.getName());
                    lookup.setType(lookupDto.getType());
                    lookupConfigurations.add(lookup);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getLookupConfigurations function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(lookupConfigurations);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getLookupConfigurations function : ",
                    e);
            return getContentBuildingError(Constants.DATA_FETCHING_ERROR +
                    ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getLookupConfigurations function : ",
                    e);
            return getContentBuildingError(Constants.DATA_FETCHING_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

}
