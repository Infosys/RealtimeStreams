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

package org.streamconnect.dss.metadata.connection.tx;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ColumnMetadata;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.TableMetadata;
import com.google.gson.Gson;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.StreamTxnException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.metadata.connection.dao.DashboardDataAccess;
import org.streamconnect.dss.metadata.connection.dao.PortalDataAccess;
import org.streamconnect.dss.metadata.entities.Process;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.streamconnect.dss.dto.*;
import org.streamconnect.dss.metadata.entities.*;
import org.streamconnect.dss.util.*;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is a transaction class for the Data Persistence Layer. Define the TX
 * policies based on the Transaction Type that define
 *
 */
@Service
public class DashboardTransaction {

    /** The dashboard source data access. */
    @Autowired
    private DashboardDataAccess<Source> dashboardSourceDataAccess;

    /** The dashboard process data access. */
    @Autowired
    private DashboardDataAccess<Process> dashboardProcessDataAccess;

    /** The dashboard sink data access. */
    @Autowired
    private DashboardDataAccess<Sink> dashboardSinkDataAccess;

    /** The dashboard category data access. */
    @Autowired
    private DashboardDataAccess<Category> dashboardCategoryDataAccess;

    /** The portal data access. */
    @Autowired
    private PortalDataAccess<Portal> portalDataAccess;

    /** The portal dashboard data access. */
    @Autowired
    private PortalDataAccess<Dashboard> portalDashboardDataAccess;

    /** The dashboard pipeline data access. */
    @Autowired
    private DashboardDataAccess<Pipeline> dashboardPipelineDataAccess;

    /** The dashboard kpi data access. */
    @Autowired
    private DashboardDataAccess<Kpi> dashboardKpiDataAccess;

    /** The dashboard template data access. */
    @Autowired
    private DashboardDataAccess<Template> dashboardTemplateDataAccess;

    /** The dashboard pipline exec data access. */
    @Autowired
    private DashboardDataAccess<PipelineExecution>
            dashboardPiplineExecDataAccess;

    /** The dashboard visualize data access. */
    @Autowired
    private DashboardDataAccess<Visualize> dashboardVisualizeDataAccess;

    /** The dashboard datatypes data access. */
    @Autowired
    private DashboardDataAccess<Datatypes> dashboardDatatypesDataAccess;

    /** The dashboard lookup data access. */
    @Autowired
    private DashboardDataAccess<Lookup> dashboardLookupDataAccess;

    /** The dashboard lookup details data access. */
    @Autowired
    private DashboardDataAccess<LookupDetails> dashboardLookupDetailsDataAccess;

    /** The dashboard user data access. */
    @Autowired
    private DashboardDataAccess<User> dashboardUserDataAccess;

    /** The dashboard role data access. */
    @Autowired
    private DashboardDataAccess<Role> dashboardRoleDataAccess;

    /** The dashboard access level data access. */
    @Autowired
    private DashboardDataAccess<AccessLevel> dashboardAccessLevelDataAccess;

    /** The dashboard portal access level data access. */
    @Autowired
    private DashboardDataAccess<PortalAccess>
            dashboardPortalAccessLevelDataAccess;

    /** The dashboard feature data access. */
    @Autowired
    private DashboardDataAccess<Feature> dashboardFeatureDataAccess;

    /** The dashboard feature access level data access. */
    @Autowired
    private DashboardDataAccess<FeatureAccess>
            dashboardFeatureAccessLevelDataAccess;

    /** The dashboard user login data access. */
    @Autowired
    private DashboardDataAccess<UserLoginDetails> dashboardUserLoginDataAccess;

    /** The dashboard user function data access. */
    @Autowired
    private DashboardDataAccess<UserFunction> dashboardUserFunctionDataAccess;

    /** The portal transaction. */
    @Autowired
    private PortalTransaction portalTransaction;

    /** The file util. */
    @Autowired
    private FileUtil fileUtil;

    /** The common util. */
    @Autowired
    private CommonUtil commonUtil;

    /** The query plan util. */
    @Autowired
    private QueryPlanUtil queryPlanUtil;

    @Autowired
    private MetadataUtil metadataUtil;

    /** The logger. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(DashboardTransaction.class);

    /**
     * Method for getting source list.
     *
     * @return listSourceDto source list
     */
    @Transactional(readOnly = true)
    public List<IdNameDto> getSourceList() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getSourceList function : "
                + Constants.START_STATUS);
        List<IdNameDto> listSourceDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Source> listSource = dashboardSourceDataAccess
                    .listAll(
                            "from" + " Source src where src.deleteStatus = " +
                                    ":deleteStatus order "
                                    + "by src.dateUpdatedSource desc",
                            parameterList);
            if (listSource != null) {
                listSourceDto = new ArrayList<IdNameDto>();
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getSourceList function :"
                        + " Source List = " + listSource);
                for (Source source : listSource) {
                    IdNameDto sourceDto = new IdNameDto();
                    sourceDto.setId(source.getInSourceId());
                    sourceDto.setName(source.getStrSourceConfigName());
                    sourceDto.setType(source.getStrSourceType());
                    listSourceDto.add(sourceDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getSourceList function :"
                        + " Source Dto List = " + listSourceDto);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getSourceList function :"
                        + " Source Dto List Size = 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " in "
                            + "DashboardTransaction : getSourceList function " +
                            ": ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getSourceList function : "
                + Constants.END_STATUS);
        return listSourceDto;
    }

    /**
     * Method for getting a source details by passing source id.
     *
     * @param sourceId the source id
     * @return the source data
     */
    @Transactional(readOnly = true)
    public SourceDto getSourceData(final int sourceId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getSourceData function : "
                + Constants.START_STATUS);
        SourceDto sourceDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("inSourceId", sourceId);
            parameterList.put("deleteStatus", 1);
            Source source = dashboardSourceDataAccess.findById("from Source "
                    + "where inSourceId = :inSourceId and deleteStatus = "
                    + ":deleteStatus", parameterList);
            if (source != null) {
                sourceDto = new SourceDto();
                sourceDto.setInSourceId(source.getInSourceId());
                sourceDto.setStrSourceConfigName(
                        source.getStrSourceConfigName());
                sourceDto.setStrSourceType(source.getStrSourceType());
                sourceDto.setObjSourceConfigDetails(
                        source.getStrSourceConfigDetails());
                sourceDto.setDateSource(source.getDateSource());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getSourceData function :"
                        + " Source is : Source id = " + source.getInSourceId()
                        + ", Source name = " + source.getStrSourceConfigName());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getSourceData function :"
                        + " Source is : null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " in "
                            + "DashboardTransaction : getSourceData function " +
                            ": ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getSourceData function : "
                + Constants.END_STATUS);
        return sourceDto;
    }

    /**
     * Method for saving source details.
     *
     * @param sourceDto the source dto
     * @return true, if successful
     */
    @Transactional
    public boolean saveSource(final SourceDto sourceDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveSource function : "
                + Constants.START_STATUS);
        boolean saveStatus = false;
        try {
            Source source = null;
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("inUserId", sourceDto.getInUserId());
            User user = dashboardUserDataAccess.findById("from User user"
                            + " where user.inUserId = :inUserId",
                    paramMap);
            if (sourceDto.getInSourceId() == 0) {
                source = new Source();
                source.setDeleteStatus(1);
                source.setDateSource(new Date());
                source.setCreatedBy(user);
            } else {
                source = dashboardSourceDataAccess.findById(new Source(),
                        sourceDto.getInSourceId());
                source.setUpdatedBy(user);
            }
            if (source != null) {
                source.setDateUpdatedSource(new Date());
                source.setInSourceId(sourceDto.getInSourceId());
                Gson gson = new Gson();
                String strConfig = gson
                        .toJson(sourceDto.getObjSourceConfigDetails());
                source.setStrSourceConfigDetails(strConfig);
                source.setStrSourceConfigName(
                        sourceDto.getStrSourceConfigName());
                source.setStrSourceType(sourceDto.getStrSourceType());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveSource function : "
                        + "Source is : Source id = " + source.getInSourceId()
                        + ", Source name = " + source.getStrSourceConfigName());
                saveStatus = dashboardSourceDataAccess.saveOrUpdate(source);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveSource function : "
                        + "Source is : null");
                saveStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in saveSource function " +
                            ": ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveSource function : "
                + Constants.END_STATUS);
        return saveStatus;
    }

    /**
     * Check source exist or not.
     *
     * @param inSourceId the in source id
     * @param sourceName the source name
     * @param userName   the user name
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean checkSourceExistOrNot(int inSourceId, String sourceName,
                                         String userName) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : checkSourceExistOrNot function : "
                + Constants.START_STATUS);
        boolean status = false;
        try {
            if (sourceName != null && !"".equals(sourceName.trim())
                    && userName != null && !"".equals(userName.trim())) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deleteStatus", 1);
                paramMap.put("sourceName", sourceName.trim());
                //paramMap.put("userName",userName);
                Source source = dashboardSourceDataAccess.findById(
                        "from Source source where "
                                + "source.strSourceConfigName = :sourceName " +
                                "and "
                                + "source.deleteStatus = :deleteStatus",
                        paramMap);
                if (source != null && inSourceId != source.getInSourceId()) {
                    LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                            .getMessage()
                            + " DashboardTransaction : checkSourceExistOrNot " +
                            "function:"
                            + " Source is : Source id = " + source
                            .getInSourceId()
                            + ", Source name = " + source
                            .getStrSourceConfigName());
                    status = true;
                }
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in checkSourceExistOrNot "
                            + "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        return status;
    }

    /**
     * Method for deleting source by passing source id.
     *
     * @param sourceId the source id
     * @return boolean boolean
     */
    @Transactional
    public boolean deleteSource(final int sourceId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteSource function : "
                + Constants.START_STATUS);
        boolean deleteStatus = false;
        try {
            Source source = dashboardSourceDataAccess.findById(new Source(),
                    sourceId);
            if (source != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteSource function : "
                        + "Deleted Source is : Source id = "
                        + source.getInSourceId() + ", Source name = "
                        + source.getStrSourceConfigName());
                source.setDeleteStatus(0);
                deleteStatus = dashboardSourceDataAccess.saveOrUpdate(source);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteSource function : "
                        + "Source is : null");
                deleteStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in deleteSource " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteSource function : "
                + Constants.END_STATUS);
        return deleteStatus;
    }

    /**
     * Method for getting process details by passing process id.
     *
     * @param processId the process id
     * @return processDto process data
     */
    @Transactional(readOnly = true)
    public ProcessDto getProcessData(final int processId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getProcessData function : "
                + Constants.START_STATUS);
        ProcessDto processDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("inProcessId", processId);
            parameterList.put("deleteStatus", 1);
            Process process = dashboardProcessDataAccess.findById(
                    "from " + "Process where inProcessId = :inProcessId and " +
                            "deleteStatus"
                            + " = :deleteStatus",
                    parameterList);
            if (process != null) {
                processDto = new ProcessDto();
                processDto.setInProcessId(process.getInProcessId());
                processDto.setStrProcessConfigName(
                        process.getStrProcessConfigName());
                processDto.setStrProcessType(process.getStrProcessType());
                processDto.setObjConfigDetails(
                        process.getStrProcessConfigDetails());
                processDto.setDateProcess(process.getDateProcess());
                processDto.setStrProcessQuery(process.getStrQueries());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getProcessData function "
                        + ": Process is : Process id = "
                        + process.getInProcessId() + ", Process name = "
                        + process.getStrProcessConfigName());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getProcessData function "
                        + ": Process is : null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " in "
                            + "DashboardTransaction : getProcessData function" +
                            " : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getProcessData function : "
                + Constants.END_STATUS);
        return processDto;
    }

    /**
     * Method for getting process list.
     *
     * @return listProcessDto process list
     */
    @Transactional(readOnly = true)
    public List<IdNameDto> getProcessList() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getProcessList function : "
                + Constants.START_STATUS);
        List<IdNameDto> listProcessDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Process> listProcess = dashboardProcessDataAccess.listAll(
                    "from Process process where process.deleteStatus = "
                            + ":deleteStatus order by process" +
                            ".dateUpdatedProcess"
                            + " desc",
                    parameterList);
            if (listProcess != null) {
                listProcessDto = new ArrayList<IdNameDto>();
                for (Process process : listProcess) {
                    IdNameDto processDto = new IdNameDto();
                    processDto.setId(process.getInProcessId());
                    processDto.setName(process.getStrProcessConfigName());
                    processDto.setType(process.getStrProcessType());
                    listProcessDto.add(processDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getProcessList function "
                        + ": Process Dto List = " + listProcessDto);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getProcessList function "
                        + ": Process Dto List Size = 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getProcessList " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getProcessList function : "
                + Constants.END_STATUS);
        return listProcessDto;
    }

    /**
     * Method for saving process details.
     *
     * @param processDto the process dto
     * @return boolean boolean
     */
    @Transactional
    public boolean saveProcess(final ProcessDto processDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveProcess function : "
                + Constants.START_STATUS);
        boolean saveStatus = false;
        try {
            Process process = null;
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("inUserId", processDto.getInUserId());
            User user = dashboardUserDataAccess.findById("from User user"
                            + " where user.inUserId = :inUserId",
                    paramMap);
            if (processDto.getInProcessId() == 0) {
                process = new Process();
                process.setDeleteStatus(1);
                process.setDateProcess(new Date());
                process.setCreatedBy(user);
            } else {
                process = dashboardProcessDataAccess.findById(new Process(),
                        processDto.getInProcessId());
                process.setUpdatedBy(user);
            }
            if (process != null) {
                process.setDateUpdatedProcess(new Date());
                process.setInProcessId(processDto.getInProcessId());
                process.setStrProcessConfigName(
                        processDto.getStrProcessConfigName());
                process.setStrProcessType(processDto.getStrProcessType());
                process.setStrQueries(processDto.getStrProcessQuery());
                Gson gson = new Gson();
                String strConfig = gson
                        .toJson(processDto.getObjConfigDetails());
                process.setStrProcessConfigDetails(strConfig);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveProcess function : "
                        + "Saved Process is : Process id = "
                        + process.getInProcessId() + ", Process name = "
                        + process.getStrProcessConfigName());
                saveStatus = dashboardProcessDataAccess.saveOrUpdate(process);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveProcess function : "
                        + "Process is : null");
                saveStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in saveProcess function" +
                            " : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveProcess function : "
                + Constants.END_STATUS);
        return saveStatus;
    }

    /**
     * Check process exist or not.
     *
     * @param inProcessId the in process id
     * @param processName the process name
     * @param userName    the user name
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean checkProcessExistOrNot(int inProcessId, String processName,
                                          String userName) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : checkProcessExistOrNot function : "
                + Constants.START_STATUS);
        boolean status = false;
        try {
            if (processName != null && !"".equals(processName.trim())
                    && userName != null && !"".equals(userName.trim())) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deleteStatus", 1);
                paramMap.put("processName", processName.trim());
                //paramMap.put("userName",userName);
                Process process = dashboardProcessDataAccess.findById(
                        "from Process process where "
                                + "process.strProcessConfigName = " +
                                ":processName and process"
                                + ".deleteStatus = :deleteStatus", paramMap);
                if (process != null && inProcessId != process.getInProcessId
                        ()) {
                    LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                            .getMessage()
                            + " DashboardTransaction : checkProcessExistOrNot" +
                            " function:"
                            + " Process is : Process id = " + process
                            .getInProcessId()
                            + ", Process name = " + process
                            .getStrProcessConfigName());
                    status = true;
                }
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in " +
                            "checkProcessExistOrNot "
                            + "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        return status;
    }

    /**
     * Method for deleting process by passing process id.
     *
     * @param processId the process id
     * @return boolean boolean
     */
    @Transactional
    public boolean deleteProcess(final int processId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteProcess function : "
                + Constants.START_STATUS);
        boolean deleteStatus = false;
        try {
            Process process = dashboardProcessDataAccess.findById(new Process(),
                    processId);
            if (process != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteProcess function :"
                        + " Deleted Process is : Process id = "
                        + process.getInProcessId() + ", Process name = "
                        + process.getStrProcessConfigName());
                process.setDeleteStatus(0);
                deleteStatus = dashboardProcessDataAccess.saveOrUpdate(process);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteProcess function :"
                        + " Process is : null");
                deleteStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in deleteProcess " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteProcess function : "
                + Constants.END_STATUS);
        return deleteStatus;
    }

    /**
     * Method for getting sink list.
     *
     * @return sinkDtoList sink list
     */
    @Transactional(readOnly = true)
    public List<IdNameDto> getSinkList() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getSinkList function : "
                + Constants.START_STATUS);
        List<IdNameDto> sinkDtoList = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Sink> sinkList = dashboardSinkDataAccess.listAll("from Sink "
                    + "sink where sink.deleteStatus = :deleteStatus order by "
                    + "sink.dateUpdatedSink desc", parameterList);
            if (sinkList != null) {
                sinkDtoList = new ArrayList<IdNameDto>();
                for (Sink sink : sinkList) {
                    IdNameDto sinkDto = new IdNameDto();
                    sinkDto.setId(sink.getInSinkId());
                    sinkDto.setName(sink.getStrSinkName());
                    sinkDto.setType(sink.getStrSinkType());
                    sinkDtoList.add(sinkDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getSinkList function : "
                        + "Sink Dto List = " + sinkDtoList);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getSinkList function : "
                        + "Sink Dto List size = 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getSinkList function" +
                            " : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getSinkList function : "
                + Constants.END_STATUS);
        return sinkDtoList;
    }

    /**
     * Method for getting sink details by passing sink id.
     *
     * @param sinkId the sink id
     * @return sinkDto sink data
     */
    @Transactional(readOnly = true)
    public SinkDto getSinkData(final int sinkId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getSinkData function : "
                + Constants.START_STATUS);
        SinkDto sinkDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("inSinkId", sinkId);
            parameterList.put("deleteStatus", 1);
            Sink sink = dashboardSinkDataAccess.findById(
                    "from Sink where "
                            + "inSinkId = :inSinkId and deleteStatus = " +
                            ":deleteStatus",
                    parameterList);
            if (sink != null) {
                sinkDto = new SinkDto();
                sinkDto.setInSinkId(sink.getInSinkId());
                sinkDto.setStrSinkName(sink.getStrSinkName());
                sinkDto.setStrSinkType(sink.getStrSinkType());
                sinkDto.setObjSinkConfigDetails(sink.getStrSinkConfigDetails());
                sinkDto.setDateSink(sink.getDateSink());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getSinkData function : "
                        + "Sink is : Sink id = " + sink.getInSinkId()
                        + ", Sink " + "name = " + sink.getStrSinkName());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getSinkData function : "
                        + "Sink is : null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getSinkData function" +
                            " : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getSinkData function : "
                + Constants.END_STATUS);
        return sinkDto;
    }

    /**
     * Method for saving sink details.
     *
     * @param sinkDto the sink dto
     * @return boolean boolean
     */
    @Transactional
    public boolean saveSink(final SinkDto sinkDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveSink function : "
                + Constants.START_STATUS);
        boolean saveStatus = false;
        try {
            Sink sink = null;
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("inUserId", sinkDto.getInUserId());
            User user = dashboardUserDataAccess.findById("from User user"
                            + " where user.inUserId = :inUserId",
                    paramMap);
            if (sinkDto.getInSinkId() == 0) {
                sink = new Sink();
                sink.setDeleteStatus(1);
                sink.setDateSink(new Date());
                sink.setCreatedBy(user);
            } else {
                sink = dashboardSinkDataAccess.findById(new Sink(),
                        sinkDto.getInSinkId());
                sink.setUpdatedBy(user);
            }
            if (sink != null) {
                sink.setDateUpdatedSink(new Date());
                sink.setInSinkId(sinkDto.getInSinkId());
                sink.setStrSinkName(sinkDto.getStrSinkName());
                sink.setStrSinkType(sinkDto.getStrSinkType());
                Gson gson = new Gson();
                String strConfig = gson
                        .toJson(sinkDto.getObjSinkConfigDetails());
                sink.setStrSinkConfigDetails(strConfig);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveSink function : "
                        + "Saved Sink is : Sink id = " + sink.getInSinkId()
                        + "," + " Sink name = " + sink.getStrSinkName());
                saveStatus = dashboardSinkDataAccess.saveOrUpdate(sink);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveSink function : Sink"
                        + " is : null");
                saveStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in saveSink function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveSink function : "
                + Constants.END_STATUS);
        return saveStatus;
    }

    /**
     * Check sink exist or not.
     *
     * @param inSinkId the in sink id
     * @param sinkName the sink name
     * @param userName the user name
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean checkSinkExistOrNot(int inSinkId, String sinkName,
                                       String userName) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : checkSinkExistOrNot function : "
                + Constants.START_STATUS);
        boolean status = false;
        try {
            if (sinkName != null && !"".equals(sinkName.trim())
                    && userName != null && !"".equals(userName.trim())) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deleteStatus", 1);
                paramMap.put("sinkName", sinkName.trim());
                //paramMap.put("userName",userName);
                Sink sink = dashboardSinkDataAccess.findById(
                        "from Sink sink where "
                                + "sink.strSinkName = :sinkName and sink"
                                + ".deleteStatus = :deleteStatus", paramMap);
                if (sink != null && inSinkId != sink.getInSinkId()) {
                    LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                            .getMessage()
                            + " DashboardTransaction : checkSinkExistOrNot " +
                            "function:"
                            + " Sink is : Sink id = " + sink.getInSinkId()
                            + ", Sink name = " + sink.getStrSinkName());
                    status = true;
                }
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in checkSinkExistOrNot "
                            + "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        return status;
    }

    /**
     * Method for deleting sink by passing sink id.
     *
     * @param sinkId the sink id
     * @return boolean boolean
     */
    @Transactional
    public boolean deleteSink(final int sinkId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteSink function : "
                + Constants.START_STATUS);
        boolean deleteStatus = false;
        try {
            Sink sink = dashboardSinkDataAccess.findById(new Sink(), sinkId);
            if (sink != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteSink function : "
                        + "Deleted Sink is : Sink id = " + sink.getInSinkId()
                        + ", Sink name = " + sink.getStrSinkName());
                sink.setDeleteStatus(0);
                deleteStatus = dashboardSinkDataAccess.saveOrUpdate(sink);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteSink function : "
                        + "Sink is : null");
                deleteStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in deleteSink function " +
                            ": ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteSink function : "
                + Constants.END_STATUS);
        return deleteStatus;
    }

    /**
     * Method for getting category details by passing category id.
     *
     * @param categoryId the category id
     * @return categoryDto category data
     */
    @Transactional(readOnly = true)
    public CategoryDto getCategoryData(final int categoryId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getCategoryData function : "
                + Constants.START_STATUS);
        CategoryDto categoryDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("inCategoryId", categoryId);
            parameterList.put("deleteStatus", 1);
            Category category = dashboardCategoryDataAccess
                    .findById(
                            "from " + "Category where inCategoryId = " +
                                    ":inCategoryId and "
                                    + "deleteStatus = :deleteStatus",
                            parameterList);
            if (category != null) {
                categoryDto = new CategoryDto();
                categoryDto.setInCategoryId(category.getInCategoryId());
                categoryDto.setStrCategoryName(category.getStrCategoryName());
                categoryDto.setStrCategoryDesc(category.getStrCategoryDesc());
                categoryDto.setDateFrom(category.getDateFrom());
                categoryDto.setDateTo(category.getDateTo());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getCategoryData function"
                        + " : Category is : Category id = "
                        + category.getInCategoryId() + ", Category name = "
                        + category.getStrCategoryName());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getCategoryData function"
                        + " : Category is : null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getCategoryData " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getCategoryData function : "
                + Constants.END_STATUS);
        return categoryDto;
    }

    /**
     * Method for getting category list.
     *
     * @return catList category list
     */
    @Transactional(readOnly = true)
    public List<IdNameDto> getCategoryList() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getCategoryList function : "
                + Constants.START_STATUS);
        List<IdNameDto> catDtoList = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Category> catList = dashboardCategoryDataAccess.listAll(
                    "from Category category where category.deleteStatus = "
                            + ":deleteStatus order by category" +
                            ".dateUpdatedCatry "
                            + "desc",
                    parameterList);
            if (catList != null) {
                catDtoList = new ArrayList<IdNameDto>();
                for (Category category : catList) {
                    IdNameDto catDto = new IdNameDto();
                    catDto.setId(category.getInCategoryId());
                    catDto.setName(category.getStrCategoryName());
                    catDtoList.add(catDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getCategoryList function"
                        + " : Category List = " + catDtoList);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getCategoryList function"
                        + " : Category List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getCategoryList " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getCategoryList function : "
                + Constants.END_STATUS);
        return catDtoList;
    }

    /**
     * Method for saving category details.
     *
     * @param categoryDto the category dto
     * @return boolean boolean
     */
    @Transactional
    public boolean saveCategory(final CategoryDto categoryDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveCategory function : "
                + Constants.START_STATUS);
        boolean saveStatus = false;
        try {
            Category category = null;
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("inUserId", categoryDto.getInUserId());
            User user = dashboardUserDataAccess.findById("from User user"
                            + " where user.inUserId = :inUserId",
                    paramMap);
            if (categoryDto.getInCategoryId() == 0) {
                category = new Category();
                category.setDeleteStatus(1);
                category.setDateCategory(new Date());
                category.setCreatedBy(user);
            } else {
                category = dashboardCategoryDataAccess.findById(new Category(),
                        categoryDto.getInCategoryId());
                category.setUpdatedBy(user);
            }
            if (category != null) {
                category.setDateUpdatedCatry(new Date());
                category.setInCategoryId(categoryDto.getInCategoryId());
                category.setStrCategoryName(categoryDto.getStrCategoryName());
                category.setStrCategoryDesc(categoryDto.getStrCategoryDesc());
                category.setDateFrom(categoryDto.getDateFrom());
                category.setDateTo(categoryDto.getDateTo());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveCategory function : "
                        + "Saved Category is : Category id = "
                        + category.getInCategoryId() + ", Category name = "
                        + category.getStrCategoryName());
                saveStatus = dashboardCategoryDataAccess.saveOrUpdate(category);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveCategory function : "
                        + "Category is : null");
                saveStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " DashboardTransaction : in saveCategory " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + "DashboardTransaction : saveCategory function : "
                + Constants.END_STATUS);
        return saveStatus;
    }

    /**
     * Check category exist or not.
     *
     * @param inCategoryId the in category id
     * @param categoryName the category name
     * @param userName     the user name
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean checkCategoryExistOrNot(int inCategoryId, String
            categoryName,
                                           String userName) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : checkCategoryExistOrNot function : "
                + Constants.START_STATUS);
        boolean status = false;
        try {
            if (categoryName != null && !"".equals(categoryName.trim())
                    && userName != null && !"".equals(userName.trim())) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deleteStatus", 1);
                //paramMap.put("userName",userName);
                paramMap.put("categoryName", categoryName.trim());
                Category category = dashboardCategoryDataAccess.findById(
                        "from Category category where "
                                + "category.strCategoryName = :categoryName " +
                                "and category"
                                + ".deleteStatus = :deleteStatus", paramMap);
                if (category != null && inCategoryId != category
                        .getInCategoryId()) {
                    LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                            .getMessage()
                            + " DashboardTransaction : " +
                            "checkCategoryExistOrNot function:"
                            + " Category is : Category id = " + category
                            .getInCategoryId()
                            + ", Category name = " + category
                            .getStrCategoryName());
                    status = true;
                }
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in " +
                            "checkCategoryExistOrNot "
                            + "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        return status;
    }

    /**
     * Method for deleting category by passing category id.
     *
     * @param categoryId the category id
     * @return boolean boolean
     */
    @Transactional
    public boolean deleteCategory(final int categoryId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteCategory function : "
                + Constants.START_STATUS);
        boolean deleteStatus = false;
        try {
            Category category = dashboardCategoryDataAccess
                    .findById(new Category(), categoryId);
            if (category != null) {
                category.setDeleteStatus(0);
                for (Kpi kpi : category.getKpis()) {
                    Set<Category> categorySet = kpi.getCategories();
                    categorySet.remove(category);
                    kpi.setCategories(categorySet);
                    dashboardKpiDataAccess.saveOrUpdate(kpi);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteCategory function "
                        + ": Deleted Category is : Category id = "
                        + category.getInCategoryId() + ", Category name = "
                        + category.getStrCategoryName());
                deleteStatus = dashboardCategoryDataAccess
                        .saveOrUpdate(category);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteCategory function "
                        + ": Category is : null");
                deleteStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in deleteCategory " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteCategory function : "
                + Constants.END_STATUS);
        return deleteStatus;
    }

    /**
     * Method for getting Kpi list.
     *
     * @return listKpiDto kpi list
     */
    @Transactional(readOnly = true)
    public List<IdNameDto> getKpiList() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getKpiList function : "
                + Constants.START_STATUS);
        List<IdNameDto> listKpiDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Kpi> listKpis = dashboardKpiDataAccess.listAll("from Kpi kpi"
                    + " where kpi.deleteStatus = :deleteStatus order by kpi"
                    + ".dateUpdatedKpi desc", parameterList);
            if (listKpis != null) {
                listKpiDto = new ArrayList<IdNameDto>();
                for (Kpi kpi : listKpis) {
                    IdNameDto kpiDto = new IdNameDto();
                    kpiDto.setId(kpi.getInKpiId());
                    kpiDto.setName(kpi.getStrKpiName());
                    listKpiDto.add(kpiDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getKpiList function : "
                        + "List of Kpi : " + listKpiDto);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getKpiList function : "
                        + "Kpi List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getKpiList function " +
                            ": ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getKpiList function : "
                + Constants.END_STATUS);
        return listKpiDto;
    }

    /**
     * Method for getting kpi details by passing kpi id.
     *
     * @param kpiId the kpi id
     * @return kpiDto kpi data
     */
    @Transactional(readOnly = true)
    public KpiDto getKpiData(final int kpiId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getKpiData function : "
                + Constants.START_STATUS);
        KpiDto kpiDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("inKpiId", kpiId);
            parameterList.put("deleteStatus", 1);
            Kpi kpi = dashboardKpiDataAccess.findById(
                    "from Kpi where inKpiId"
                            + " = :inKpiId and deleteStatus = :deleteStatus",
                    parameterList);
            if (kpi != null) {
                kpiDto = new KpiDto();
                kpiDto.setInKpiId(kpi.getInKpiId());
                kpiDto.setStrKpiName(kpi.getStrKpiName());
                kpiDto.setStrKpiDesc(kpi.getStrKpiDesc());
                List<CategoryDto> categoryDtoList = new
                        ArrayList<CategoryDto>();
                for (Category category : kpi.getCategories()) {
                    CategoryDto categoryDto = new CategoryDto();
                    categoryDto.setInCategoryId(category.getInCategoryId());
                    categoryDto
                            .setStrCategoryName(category.getStrCategoryName());
                    categoryDtoList.add(categoryDto);
                }
                kpiDto.setCategories(categoryDtoList);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getKpiData function : "
                        + "Kpi is : Kpi id = " + kpiDto.getInKpiId() + ", Kpi "
                        + "name = " + kpiDto.getStrKpiName());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getKpiData function : "
                        + "Kpi is : null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getKpiData function " +
                            ": ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getKpiData function : "
                + Constants.END_STATUS);
        return kpiDto;
    }

    /**
     * Method for saving kpi details.
     *
     * @param kpiDto the kpi dto
     * @return false boolean
     */
    @Transactional
    public boolean saveKpi(final KpiDto kpiDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : saveKpi function : "
                + Constants.START_STATUS);
        boolean saveStatus = false;
        try {
            Kpi kpi = null;
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("inUserId", kpiDto.getInUserId());
            User user = dashboardUserDataAccess.findById("from User user"
                            + " where user.inUserId = :inUserId",
                    paramMap);
            if (kpiDto.getInKpiId() == 0) {
                kpi = new Kpi();
                kpi.setDeleteStatus(1);
                kpi.setDateKpi(new Date());
                kpi.setCreatedBy(user);
            } else {
                kpi = dashboardKpiDataAccess.findById(new Kpi(),
                        kpiDto.getInKpiId());
                kpi.setDateUpdatedKpi(new Date());
                kpi.setUpdatedBy(user);
            }
            if (kpi != null) {
                kpi.setDateUpdatedKpi(new Date());
                kpi.setInKpiId(kpiDto.getInKpiId());
                kpi.setStrKpiName(kpiDto.getStrKpiName());
                kpi.setStrKpiDesc(kpiDto.getStrKpiDesc());
                Set<Category> categories = new HashSet<Category>();
                for (CategoryDto categoryDto : kpiDto.getCategories()) {
                    Category category = dashboardCategoryDataAccess.findById(
                            new Category(), categoryDto.getInCategoryId());
                    if (category != null) {
                        categories.add(category);
                    }
                }
                kpi.setCategories(categories);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveKpi function : Kpi "
                        + "is : Kpi id = " + kpi.getInKpiId() + ", Kpi name = "
                        + kpi.getStrKpiName());
                saveStatus = dashboardKpiDataAccess.saveOrUpdate(kpi);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveKpi function : Kpi "
                        + "is : null");
                saveStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in saveKpi function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : saveKpi function : "
                + Constants.END_STATUS);
        return saveStatus;
    }

    /**
     * Check kpi exist or not.
     *
     * @param inKpiId  the in kpi id
     * @param kpiName  the kpi name
     * @param userName the user name
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean checkKpiExistOrNot(int inKpiId, String kpiName, String
            userName) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : checkKpiExistOrNot function : "
                + Constants.START_STATUS);
        boolean status = false;
        try {
            if (kpiName != null && !"".equals(kpiName.trim())
                    && userName != null && !"".equals(userName.trim())) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deleteStatus", 1);
                //paramMap.put("userName",userName);
                paramMap.put("kpiName", kpiName.trim());
                Kpi kpi = dashboardKpiDataAccess.findById("from Kpi kpi where "
                        + "kpi.strKpiName = :kpiName and kpi"
                        + ".deleteStatus = :deleteStatus", paramMap);
                if (kpi != null && inKpiId != kpi.getInKpiId()) {
                    LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                            .getMessage()
                            + " DashboardTransaction : checkKpiExistOrNot " +
                            "function:"
                            + " Kpi is : Kpi id = " + kpi.getInKpiId()
                            + ", Kpi name = " + kpi.getStrKpiName());
                    status = true;
                }
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in checkKpiExistOrNot "
                            + "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        return status;
    }

    /**
     * Method for deleting kpi by passing kpi id.
     *
     * @param kpiId the kpi id
     * @return boolean boolean
     */
    @Transactional
    public boolean deleteKpi(final int kpiId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteKpi function : "
                + Constants.START_STATUS);
        boolean deleteStatus = false;
        try {
            Kpi kpi = dashboardKpiDataAccess.findById(new Kpi(), kpiId);
            if (kpi != null) {
                kpi.setDeleteStatus(0);
                kpi.setVisualizes(new HashSet<Visualize>());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteKpi function : Kpi"
                        + " is : Kpi id = " + kpi.getInKpiId() + ", Kpi name = "
                        + kpi.getStrKpiName());
                deleteStatus = dashboardKpiDataAccess.saveOrUpdate(kpi);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteKpi function : Kpi"
                        + " is : null");
                deleteStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in deleteKpi function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteKpi function : "
                + Constants.END_STATUS);
        return deleteStatus;
    }

    /**
     * Method for getting pipeline details by passing pipeline id.
     *
     * @param pipelineId the pipeline id
     * @return pipelineDto pipeline data
     */
    @Transactional(readOnly = true)
    public PipelineDto getPipelineData(final int pipelineId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getPipelineData function : "
                + Constants.START_STATUS);
        PipelineDto pipelineDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("inPipelineId", pipelineId);
            Pipeline pipeline = dashboardPipelineDataAccess.findById(
                    "from " + "Pipeline pipeline where pipeline.inPipelineId = "
                            + ":inPipelineId",
                    parameterList);
            if (pipeline != null) {
                pipelineDto = new PipelineDto();
                pipelineDto.setInPipelineId(pipeline.getInPipelineId());
                pipelineDto.setStrPipelineName(pipeline.getStrPipelineName());
                pipelineDto.setObjPipelineConfigDetails(
                        pipeline.getStrPipelineConfigDetails());
                pipelineDto.setStrConnectors(pipeline.getStrConnectors());
                pipelineDto
                        .setStrPipelineExeURL(pipeline.getStrPipelineExeURL());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelineData function"
                        + " : Pipeline is : Pipeline id = "
                        + pipeline.getInPipelineId() + ", Pipeline name = "
                        + pipeline.getStrPipelineName());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelineData function"
                        + " : Pipeline is : null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getPipelineData " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getPipelineData function : "
                + Constants.END_STATUS);
        return pipelineDto;
    }

    /**
     * Method for getting pipeline list.
     *
     * @return pipelineDtoList pipeline list
     */
    @Transactional(readOnly = true)
    public List<IdNameDto> getPipelineList() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getPipelineList function : "
                + Constants.START_STATUS);
        List<IdNameDto> pipelineDtoList = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Pipeline> pipelineList = dashboardPipelineDataAccess
                    .listAll(
                            "from Pipeline pipeline where pipeline" +
                                    ".deleteStatus = "
                                    + ":deleteStatus  order by pipeline"
                                    + ".dateUpdatedPipeline desc",
                            parameterList);
            if (pipelineList != null) {
                pipelineDtoList = new ArrayList<IdNameDto>();
                for (Pipeline pipeline : pipelineList) {
                    IdNameDto catDto = new IdNameDto();
                    catDto.setId(pipeline.getInPipelineId());
                    catDto.setName(pipeline.getStrPipelineName());
                    pipelineDtoList.add(catDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelineList function"
                        + " : Pipeline List is : " + pipelineDtoList);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelineList function"
                        + " : Pipeline List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getPipelineList " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getPipelineList function : "
                + Constants.END_STATUS);
        return pipelineDtoList;
    }

    /**
     * Method for save pipeline details.
     *
     * @param pipelineDto           the pipeline dto
     * @param visualizeDtos         the visualize dtos
     * @param deletedVisualizations the deleted visualizations
     * @return boolean boolean
     */
    @Transactional
    public boolean savePipeline(final PipelineDto pipelineDto,
                                final List<VisualizeDto> visualizeDtos,
                                final List<Integer> deletedVisualizations) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : savePipeline function : "
                + Constants.START_STATUS);
        boolean saveStatus = false;
        try {
            Pipeline pipeline = null;
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("inUserId",pipelineDto.getInUserId());
            User user = dashboardUserDataAccess.findById("from User user"
                            + " where user.inUserId = :inUserId",
                    paramMap);
            Set<Visualize> visualizes = new HashSet<Visualize>();
            if (pipelineDto.getInPipelineId() == 0) {
                pipeline = new Pipeline();
                pipeline.setDeleteStatus(1);
                pipeline.setDatepipeline(new Date());
                pipeline.setCreatedBy(user);
            } else {
                pipeline = dashboardPipelineDataAccess.findById(new Pipeline(),
                        pipelineDto.getInPipelineId());
                visualizes = pipeline.getVisualizes();
                pipeline.setUpdatedBy(user);
            }
            if (pipeline != null) {
                pipeline.setDateUpdatedPipeline(new Date());
                pipeline.setInPipelineId(pipelineDto.getInPipelineId());
                pipeline.setStrPipelineName(pipelineDto.getStrPipelineName());
                pipeline.setStrPipelineConfFile(
                        pipelineDto.getStrPipelineConfFile());
                pipeline.setStrPipelinePropFile(
                        pipelineDto.getStrPipelinePropFile());
                pipeline.setStrSourceType(pipelineDto.getStrSourceType());
                pipeline.setStrProcessType(pipelineDto.getStrProcessType());
                pipeline.setStrSinkType(pipelineDto.getStrSinkType());
                Gson gson = new Gson();
                String strConfig = gson
                        .toJson(pipelineDto.getObjPipelineConfigDetails());
                String strConnectors = gson
                        .toJson(pipelineDto.getStrConnectors());
                pipeline.setStrPipelineConfigDetails(strConfig);
                pipeline.setStrConnectors(strConnectors);
                pipeline.setStrPipelineExeURL(
                        pipelineDto.getStrPipelineExeURL());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : savePipeline function : "
                        + "Pipeline is : Pipeline id  = "
                        + pipeline.getInPipelineId() + ", Pipeline name = "
                        + pipeline.getStrPipelineName());
                Map<Kpi, Set<Visualize>> kpiVisualizeMap = new HashMap<Kpi,
                        Set<Visualize>>();
                if (visualizeDtos != null && !visualizeDtos.isEmpty()) {
                    for (VisualizeDto visualizeDto : visualizeDtos) {
                        boolean exist = false;
                        if (visualizes != null && !visualizes.isEmpty()) {
                            for (Visualize visualize : visualizes) {
                                exist = false;
                                // Visualization Updation
                                if (visualize
                                        .getInVisualizeEntityId() ==
                                        visualizeDto
                                        .getInVisualizeEntityId()
                                        && visualize.getPipeline()
                                        .getInPipelineId() == visualizeDto
                                        .getInPipelineId()) {
                                    exist = true;
                                    visualize
                                            .setInVisualizeEntityId(visualizeDto
                                                    .getInVisualizeEntityId());
                                    visualize.setStrVisualizeName(
                                            visualizeDto.getStrVisualizeName());
                                    visualize.setStrVisualizeParentType(
                                            visualizeDto
                                                    .getStrVisualizeParentType());
                                    visualize
                                            .setStrVisualizeSubType(visualizeDto
                                                    .getStrVisualizeSubType());
                                    visualize.setStrVisualizeDesc(
                                            visualizeDto.getStrVisualizeDesc());
                                    visualize.setDeleteStatus(1);
                                    visualize.setDateUpdatedVisualize(
                                            new Date());
                                    visualize.setStrKeySpace(
                                            visualizeDto.getStrKeySpace());
                                    visualize.setPipeline(pipeline);
                                    strConfig = gson.toJson(visualizeDto
                                            .getStrVisualizeConfigDetails());
                                    visualize.setStrVisualizeConfigDetails(
                                            strConfig);
                                    updateKpiMap(visualizeDto.getKpiList(),
                                            kpiVisualizeMap, visualize, exist);
                                    break;
                                }

                            }
                        }
                        // New Visualizations
                        if (!exist) {
                            Visualize visualize = new Visualize();
                            visualize.setStrVisualizeName(
                                    visualizeDto.getStrVisualizeName());
                            visualize.setInVisualizeEntityId(
                                    visualizeDto.getInVisualizeEntityId());
                            visualize.setStrVisualizeParentType(
                                    visualizeDto.getStrVisualizeParentType());
                            visualize.setStrVisualizeSubType(
                                    visualizeDto.getStrVisualizeSubType());
                            visualize.setStrVisualizeDesc(
                                    visualizeDto.getStrVisualizeDesc());
                            visualize.setDeleteStatus(1);
                            visualize.setDateVisualize(new Date());
                            visualize.setDateUpdatedVisualize(new Date());
                            visualize.setPipeline(pipeline);
                            strConfig = gson.toJson(visualizeDto
                                    .getStrVisualizeConfigDetails());
                            visualize.setStrVisualizeConfigDetails(strConfig);
                            visualize.setStrKeySpace(
                                    visualizeDto.getStrKeySpace());
                            updateKpiMap(visualizeDto.getKpiList(),
                                    kpiVisualizeMap, visualize, exist);
                            visualizes.add(visualize);
                        }
                    }
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "savePipeline function : Visualize List" +
                                    " is "
                                    + visualizes);
                }
                if (visualizes != null && !visualizes.isEmpty()
                        && deletedVisualizations != null
                        && !deletedVisualizations.isEmpty()) {
                    for (Visualize visualize : visualizes) {
                        if (deletedVisualizations
                                .contains(visualize.getInVisualizeEntityId())) {
                            visualize.setDeleteStatus(0);
                        }
                    }
                }
                pipeline.setVisualizes(visualizes);
                pipeline = dashboardPipelineDataAccess.merge(pipeline);
                updateKpi(kpiVisualizeMap, pipeline.getVisualizes());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : savePipeline function : "
                        + "Pipeline is : Pipeline id  = "
                        + pipeline.getInPipelineId() + ", Pipeline name = "
                        + pipeline.getStrPipelineName());
                saveStatus = true;
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : savePipeline function : "
                        + "Pipeline is : null");
                saveStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in savePipeline " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : savePipeline function : "
                + Constants.END_STATUS);
        return saveStatus;
    }

    /**
     * Method for composing a map for updating KPI and visualize details.
     *
     * @param kpiDtos
     *            the kpi dtos
     * @param kpiVisualizeMap
     *            the kpi visualize map
     * @param visualize
     *            the visualize
     * @param exist
     *            the exist
     */
    private void updateKpiMap(final List<KpiDto> kpiDtos,
                              final Map<Kpi, Set<Visualize>> kpiVisualizeMap,
                              final Visualize visualize, final boolean exist) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : updateKpiMap function : "
                + Constants.START_STATUS);
        if (kpiDtos != null && !kpiDtos.isEmpty()) {
            Set<Kpi> kpis = null;
            if (exist) {
                kpis = visualize.getKpis();
            }
            for (KpiDto kpiDto : kpiDtos) {
                Kpi kpi = dashboardKpiDataAccess.findById(new Kpi(),
                        kpiDto.getInKpiId());
                Set<Visualize> visualizes = null;
                if (kpiVisualizeMap.containsKey(kpi)) {
                    visualizes = kpiVisualizeMap.get(kpi);
                } else {
                    visualizes = kpi.getVisualizes();
                }
                if (visualizes == null) {
                    visualizes = new HashSet<Visualize>();
                }
                if (!visualizes.contains(visualize)) {
                    visualizes.add(visualize);
                }
                if (kpis != null && !kpis.isEmpty()) {
                    kpis.remove(kpi);
                }
                kpiVisualizeMap.put(kpi, visualizes);
            }
            // For removing deleted kpi-visualize relation
            if (kpis != null && !kpis.isEmpty()) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : updateKpiMap function : "
                        + "Kpi List is : " + kpis);
                for (Kpi tempKpi : kpis) {
                    Set<Visualize> tempForDelVisualizes = tempKpi
                            .getVisualizes();
                    tempForDelVisualizes.remove(visualize);
                    kpiVisualizeMap.put(tempKpi, tempForDelVisualizes);
                }
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : updateKpiMap function : "
                        + "Kpi List Size is 0");
            }
        } else {
            LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                    + " DashboardTransaction : updateKpiMap function : Input " +
                    "Kpi"
                    + " List Size is 0");
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : updateKpiMap function : "
                + Constants.END_STATUS);
    }

    /**
     * Method for updating Visualize details against KPI.
     *
     * @param kpiVisualizeMap
     *            the kpi visualize map
     * @param pplVisualizes
     *            the ppl visualizes
     */
    private void updateKpi(final Map<Kpi, Set<Visualize>> kpiVisualizeMap,
                           final Set<Visualize> pplVisualizes) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : updateKpi function : "
                + Constants.START_STATUS);
        for (Map.Entry<Kpi, Set<Visualize>> entry : kpiVisualizeMap
                .entrySet()) {
            Kpi kpi = entry.getKey();
            Set<Visualize> kpiVisualizes = new HashSet<Visualize>();
            for (Visualize visualize : entry.getValue()) {
                if (visualize.getInVisualizeId() == 0) {
                    visualize = getVisualize(pplVisualizes,
                            visualize.getInVisualizeEntityId());
                }
                kpiVisualizes.add(visualize);
            }
            kpi.setVisualizes(kpiVisualizes);
            dashboardKpiDataAccess.saveOrUpdate(kpi);
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : updateKpi function : "
                + Constants.END_STATUS);
    }

    /**
     * Method for replacing Visualize details for newly added Visualizations.
     *
     * @param pplVisualizes
     *            the ppl visualizes
     * @param inVisualizeEntityId
     *            the in visualize entity id
     * @return resultVisualize
     */
    private Visualize getVisualize(final Set<Visualize> pplVisualizes,
                                   final int inVisualizeEntityId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getVisualize function : "
                + Constants.START_STATUS);
        Visualize resultVisualize = null;
        if (pplVisualizes != null && !pplVisualizes.isEmpty()) {
            for (Visualize visualize : pplVisualizes) {
                if (visualize.getInVisualizeEntityId() == inVisualizeEntityId) {
                    resultVisualize = visualize;
                }
            }
            LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                    + " DashboardTransaction : getVisualize function : " +
                    "Visualize"
                    + " is :  Visualize id = "
                    + resultVisualize.getInVisualizeId() + ", Visualize name = "
                    + resultVisualize.getStrVisualizeName());
        } else {
            LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                    + " DashboardTransaction : getVisualize function : " +
                    "Visualize"
                    + " is : null");
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getVisualize function : "
                + Constants.END_STATUS);
        return resultVisualize;
    }

    /**
     * Check pipeline exist or not.
     *
     * @param inPipelineId the in pipeline id
     * @param pipelineName the pipeline name
     * @param userName     the user name
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean checkPipelineExistOrNot(int inPipelineId, String pipelineName,
                                           String userName) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : checkPipelineExistOrNot function : "
                + Constants.START_STATUS);
        boolean status = false;
        try{
            if(pipelineName != null && !"".equals(pipelineName.trim())
                    && userName != null && !"".equals(userName.trim())){
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deleteStatus",1);
                paramMap.put("pipelineName", pipelineName);
                //paramMap.put("userName",userName);
                Pipeline pipeline = dashboardPipelineDataAccess.findById(
                        "from Pipeline pipeline where "
                                + "pipeline.strPipelineName = :pipelineName and pipeline"
                                + ".deleteStatus = :deleteStatus",paramMap);
                if(pipeline != null && inPipelineId != pipeline.getInPipelineId()){
                    LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                            + " DashboardTransaction : checkPipelineExistOrNot function:"
                            + " Pipeline is : Pipeline id = " + pipeline.getInPipelineId()
                            + ", Pipeline name = " + pipeline.getStrPipelineName());
                    status = true;
                }
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + "  DashboardTransaction : in "
                            + "checkPipelineExistOrNot  function : ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        return status;
    }

    /**
     * Check visualization name exist or not.
     *
     * @param visualizeCheck the visualize check
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean checkVisualizationExistOrNot(final VisualizeCheckDto
                                                        visualizeCheck) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : checkVisualizationExistOrNot function : "
                + Constants.START_STATUS);
        boolean status = false;
        try {
            if(visualizeCheck != null && visualizeCheck
                    .getStrVisualizeName() != null
                    && !"".equals(visualizeCheck.getStrVisualizeName().trim())) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deleteStatus",1);
                paramMap.put("strVisualizeName", visualizeCheck
                        .getStrVisualizeName().trim());
                List<Visualize> visualizes = dashboardVisualizeDataAccess
                        .listAll(
                        "from Visualize visualize where "
                                + "visualize.strVisualizeName = :strVisualizeName"
                                + " and visualize.deleteStatus = " +
                                ":deleteStatus", paramMap);

                // For KPI Level Visualization Name Check
                boolean visualizeInKpi = false;
                if(visualizes != null){
                    for(Visualize visualize : visualizes) {
                        if(visualize != null && visualizeCheck.getKpiList() != null
                                && !visualizeCheck.getKpiList().isEmpty()
                                && visualize.getKpis() != null
                                && !visualize .getKpis().isEmpty()) {
                            for(KpiDto kpiDto : visualizeCheck.getKpiList()) {
                                for(Kpi kpi : visualize.getKpis()) {
                                    if(kpiDto.getInKpiId() == kpi.getInKpiId()) {
                                        visualizeInKpi = true;
                                        status = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if(visualizeInKpi && visualize != null && (visualizeCheck
                                .getInPipelineId() ==
                                visualize
                                        .getPipeline().getInPipelineId()
                                && visualizeCheck.getInVisualizeEntityId() ==
                                visualize
                                .getInVisualizeEntityId())) {
                            LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : checkVisualizationExistOrNot"
                                    + " function:  Visualize is : Visualize id = "
                                    + visualize.getInVisualizeId()
                                    + ", Visualize name = " + visualize.getStrVisualizeName());
                            status = false;
                        }
                    }
                }



            }
            return status;
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + "  DashboardTransaction : in "
                            + "checkVisualizationExistOrNot  function : ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for delete pipeline by passing pipeline id.
     *
     * @param pipelineId the pipeline id
     * @return boolean boolean
     */
    @Transactional
    public boolean deletePipeline(final int pipelineId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deletePipeline function : "
                + Constants.START_STATUS);
        boolean deleteStatus = false;
        try {
            Pipeline pipeline = dashboardPipelineDataAccess
                    .findById(new Pipeline(), pipelineId);
            if (pipeline != null) {
                pipeline.setDeleteStatus(0);
                if (pipeline.getVisualizes() != null) {
                    for (Visualize visualize : pipeline.getVisualizes()) {
                        visualize.setDeleteStatus(0);
                    }
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deletePipeline function "
                        + ": Pipeline is : Pipeline id  = "
                        + pipeline.getInPipelineId() + ", Pipeline name = "
                        + pipeline.getStrPipelineName());
                deleteStatus = dashboardPipelineDataAccess
                        .saveOrUpdate(pipeline);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deletePipeline function "
                        + ": Pipeline is : null");
                deleteStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in deletePipeline " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deletePipeline function : "
                + Constants.END_STATUS);
        return deleteStatus;
    }

    /**
     * Method for listing each category and all kpis under each category.
     *
     * @return categoryKpisDtoList category with kpis
     */
    @Transactional(readOnly = true)
    public List<CategoryKpisDto> getCategoryWithKpis() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getCategoryWithKpis function : " + ""
                + Constants.START_STATUS);
        List<CategoryKpisDto> categoryKpisDtoList = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Category> categoryList = dashboardCategoryDataAccess.listAll(
                    "from Category where deleteStatus = :deleteStatus",
                    parameterList);
            if (categoryList != null) {
                categoryKpisDtoList = new ArrayList<CategoryKpisDto>();
                for (Category category : categoryList) {
                    List<IdNameDto> idNameDtoList = new ArrayList<IdNameDto>();
                    for (Kpi kpi : category.getKpis()) {
                        IdNameDto idNameDto = new IdNameDto();
                        idNameDto.setId(kpi.getInKpiId());
                        idNameDto.setName(kpi.getStrKpiName());
                        idNameDtoList.add(idNameDto);
                    }
                    CategoryKpisDto categorykpisDto = new CategoryKpisDto();
                    categorykpisDto.setInCategoryId(category.getInCategoryId());
                    categorykpisDto
                            .setStrCategoryName(category.getStrCategoryName());
                    categorykpisDto.setKpis(idNameDtoList);
                    categoryKpisDtoList.add(categorykpisDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getCategoryWithKpis "
                        + "function : CategoryKpisDto List is : "
                        + categoryKpisDtoList);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getCategoryWithKpis "
                        + "function : CategoryKpisDto List is : null");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in getCategoryWithKpis function" +
                    " : "
                    + "", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getCategoryWithKpis function : "
                + Constants.END_STATUS);
        return categoryKpisDtoList;
    }

    /**
     * Method for listing all pipelines with category and kpi details.
     *
     * @return pipelineDtos pipelines
     */
    @Transactional(readOnly = true)
    public List<PipelineDto> getPipelines() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getPipelines function : "
                + Constants.START_STATUS);
        List<PipelineDto> pipelineDtos = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Pipeline> pipelineList = dashboardPipelineDataAccess
                    .listAll(
                            "from Pipeline pipeline where pipeline" +
                                    ".deleteStatus = "
                                    + ":deleteStatus order by pipeline"
                                    + ".dateUpdatedPipeline desc",
                            parameterList);
            if (pipelineList != null && !pipelineList.isEmpty()) {
                pipelineDtos = new ArrayList<PipelineDto>();
                for (Pipeline pipeline : pipelineList) {
                    PipelineDto pipelineDto = new PipelineDto();
                    pipelineDto.setInPipelineId(pipeline.getInPipelineId());
                    pipelineDto
                            .setStrPipelineName(pipeline.getStrPipelineName());
                    pipelineDto.setDatePipeline(pipeline.getDatepipeline());
                    pipelineDto.setStrPipelineExeURL(
                            pipeline.getStrPipelineExeURL());
                    getPipelineExecStatus(pipelineDto,
                            pipeline.getInPipelineId());
                    if (pipeline.getVisualizes() != null) {
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " DashboardTransaction : "
                                + "getPipelines function : Visualize List is : "
                                + pipeline.getVisualizes());
                        List<CategoryDto> categoryList = new
                                ArrayList<CategoryDto>();
                        Map<Integer, CategoryDto> categoryDtoMap = new
                                HashMap<Integer, CategoryDto>();
                        for (Visualize visualize : pipeline.getVisualizes()) {
                            if (visualize.getDeleteStatus() == 1) {
                                if (visualize.getKpis() != null) {
                                    LOGGER.debug(
                                            LogMessageEnum
                                                    .TRANSACTION_LAYER_INFO
                                                    .getMessage() + " "
                                                    + "DashboardTransaction : "
                                                    + "getPipelines function " +
                                                    ": Kpi List "
                                                    + "is : "
                                                    + visualize.getKpis());
                                    for (Kpi kpi : visualize.getKpis()) {
                                        IdNameDto kpiDto = new IdNameDto();
                                        kpiDto.setId(kpi.getInKpiId());
                                        kpiDto.setName(kpi.getStrKpiName());
                                        if (kpi.getCategories() != null) {
                                            for (Category category : kpi
                                                    .getCategories()) {
                                                List<IdNameDto> kpiList = null;
                                                CategoryDto categoryDto = null;
                                                if (categoryDtoMap.containsKey(
                                                        category.getInCategoryId())) {
                                                    categoryDto = categoryDtoMap
                                                            .get(category
                                                                    .getInCategoryId());
                                                    kpiList = categoryDto
                                                            .getKpiList();
                                                    boolean exist = false;
                                                    for (IdNameDto idNameDto
                                                            : categoryDto
                                                            .getKpiList()) {
                                                        if (kpi.getInKpiId()
                                                                == idNameDto
                                                                .getId()) {
                                                            exist = true;
                                                        }
                                                    }
                                                    if (!exist) {
                                                        kpiList.add(kpiDto);
                                                        sortKpi(kpiList);
                                                        categoryDto.setKpiList(
                                                                kpiList);
                                                    }
                                                } else {
                                                    categoryDto = new
                                                            CategoryDto();
                                                    categoryDto.setInCategoryId(
                                                            category.getInCategoryId());
                                                    categoryDto
                                                            .setStrCategoryName(
                                                                    category.getStrCategoryName());
                                                    kpiList = new
                                                            ArrayList<IdNameDto>();
                                                    kpiList.add(kpiDto);
                                                    sortKpi(kpiList);
                                                    categoryDto.setKpiList(
                                                            kpiList);
                                                    categoryDtoMap.put(
                                                            category.getInCategoryId(),
                                                            categoryDto);
                                                }
                                            }
                                        } else {
                                            LOGGER.debug(
                                                    LogMessageEnum
                                                            .TRANSACTION_LAYER_INFO
                                                            .getMessage() + " "
                                                            +
                                                            "DashboardTransaction : "
                                                            + "getPipelines " +
                                                            "function : "
                                                            + "Category List " +
                                                            "Size is 0");
                                        }
                                    }
                                } else {
                                    LOGGER.debug(
                                            LogMessageEnum
                                                    .TRANSACTION_LAYER_INFO
                                                    .getMessage() + " "
                                                    + "DashboardTransaction : "
                                                    + "getPipelines function " +
                                                    ": Kpi List "
                                                    + "Size is 0");
                                }
                            }
                        }
                        for (Map.Entry<Integer, CategoryDto> entry :
                                categoryDtoMap
                                .entrySet()) {
                            categoryList.add(entry.getValue());
                        }
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " DashboardTransaction : "
                                + "getPipelines function : Category List is : "
                                + categoryList);
                        pipelineDto.setCategoryList(categoryList);
                    } else {
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " DashboardTransaction : "
                                + "getPipelines function : Visualize List Size "
                                + "is 0");
                    }
                    pipelineDtos.add(pipelineDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelines function : "
                        + "Pipeline List is : " + pipelineDtos);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelines function : "
                        + "Pipeline List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getPipelines " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getPipelines function : "
                + Constants.END_STATUS);
        return pipelineDtos;
    }

    /**
     * Method for sorting KPI list based on KPI id.
     *
     * @param kpiList
     *            the kpi list
     */
    private void sortKpi(final List kpiList) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : sortKpi function : "
                + Constants.START_STATUS);
        Collections.sort(kpiList, new Comparator<IdNameDto>() {
            public int compare(final IdNameDto first, final IdNameDto next) {
                return String.valueOf(first.getId())
                        .compareTo(String.valueOf(next.getId()));
            }
        });
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : sortKpi function : "
                + Constants.END_STATUS);
    }

    /**
     * Method for getting saved pipeline execution status and pipeline execution
     * id.
     *
     * @param pipelineDto
     *            the pipeline dto
     * @param pipelineId
     *            the pipeline id
     */
    private void getPipelineExecStatus(final PipelineDto pipelineDto,
                                       final int pipelineId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : getPipelineExecStatus function : "
                + Constants.START_STATUS);
        try {
            PipelineExecution pipelineExecution = getPipelineExecution
                    (pipelineId);
            if (pipelineExecution != null) {
                pipelineDto.setInExecPipelineId(
                        pipelineExecution.getInExecPipelineId());
                pipelineDto.setInId(pipelineExecution.getInId());
                pipelineDto.setStrPplStatus(
                        pipelineExecution.getStrExecPipelineStatus());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelineExecStatus " +
                        "function : "
                        + "Pipeline Execution Status is "
                        + pipelineExecution.getStrExecPipelineStatus());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelineExecStatus "
                        + "function : Pipeline Execution status is null");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " DashboardTransaction : in getPipelineExecStatus "
                    + "function : ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getPipelineExecStatus function : "
                + Constants.END_STATUS);
    }

    /**
     * Method for getting saved pipeline execution data
     *
     * @param pipelineId
     *            the pipeline id
     * @return PipelineExecution
     *
     */
    private PipelineExecution getPipelineExecution(final int pipelineId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + "DashboardTransaction : getPipelineExecution function : "
                + Constants.START_STATUS);
        PipelineExecution pipelineExecution = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("inPipelineId", pipelineId);
            pipelineExecution = dashboardPiplineExecDataAccess
                    .findById(
                            "from PipelineExecution pplExe where pplExe" +
                                    ".pipeline"
                                    + ".inPipelineId = :inPipelineId",
                            parameterList);
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in getPipelineExecution function "
                    + ": ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + "DashboardTransaction : getPipelineExecution function : "
                + Constants.END_STATUS);
        return pipelineExecution;
    }

    /**
     * Method for getting Pipeline execution id.
     *
     * @param pipelineId the pipeline id
     * @return String pipeline execution id
     */
    @Transactional(readOnly = true)
    public String getPipelineExecutionId(final int pipelineId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + "DashboardTransaction : getPipelineExecutionId function : "
                + Constants.START_STATUS);
        String executionId = null;
        try {
            PipelineExecution pipelineExecution = getPipelineExecution
                    (pipelineId);
            if (pipelineExecution != null) {
                executionId = pipelineExecution.getInExecPipelineId();
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelineExecutionId " +
                        "function : "
                        + "Pipeline Execution id is "
                        + pipelineExecution.getInExecPipelineId());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelineExecutionId "
                        + "function : Pipeline Execution is null");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " DashboardTransaction : in getPipelineExecutionId "
                    + "function : ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : getPipelineExecutionId function : "
                + Constants.END_STATUS);
        return executionId;
    }

    /**
     * Method for getting max value of inVisualizeEntityId for a particular
     * pipeline.
     *
     * @param pipelineId the pipeline id
     * @return int max visualization count
     */
    @Transactional(readOnly = true)
    public int getMaxVisualizationCount(final int pipelineId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getMaxVisualizationCount function : "
                + "" + Constants.START_STATUS);
        int maxCount = 0;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("inPipelineId", pipelineId);
            List visualizeList = dashboardVisualizeDataAccess.listAll(
                    "select" + " max(visualize.inVisualizeEntityId) from " +
                            "Visualize "
                            + "visualize where visualize.pipeline" +
                            ".inPipelineId = "
                            + ":inPipelineId",
                    parameterList);
            if (visualizeList.get(0) != null) {
                maxCount = (Integer) visualizeList.get(0);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getMaxVisualizationCount"
                        + " function : Visualization Count is : " + maxCount);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getMaxVisualizationCount"
                        + " function : Visualization Count is 0");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in getMaxVisualizationCount "
                    + "function : ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getMaxVisualizationCount function : "
                + Constants.END_STATUS);
        return maxCount;
    }

    /**
     * Method for getting the execution template details.
     *
     * @param strSourceType  the str source type
     * @param strProcessType the str process type
     * @param strSinkType    the str sink type
     * @param strDataType    the str data type
     * @return pipelineDtos template file
     */
    @Transactional(readOnly = true)
    public TemplateDto getTemplateFile(final String strSourceType,
                                       final String strProcessType, final
                                           String strSinkType,
                                       final String strDataType) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getTemplateFile function : "
                + Constants.START_STATUS);
        TemplateDto templateDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("strSourceType", strSourceType.toLowerCase());
            parameterList.put("strProcessType", strProcessType.toLowerCase());
            parameterList.put("strSinkType", strSinkType.toLowerCase());
            parameterList.put("strDataType", strDataType.toLowerCase());
            Template template = dashboardTemplateDataAccess.findById(
                    "from " + "Template template where template.strSourceType" +
                            " = "
                            + ":strSourceType and template.strProcessType = "
                            + ":strProcessType and template.strSinkType = " +
                            ":strSinkType "
                            + "and template.strDataType = :strDataType",
                    parameterList);
            if (template != null) {
                templateDto = new TemplateDto();
                templateDto.setBytesTemplateFileOne(
                        template.getBytesTemplateFileOne());
                templateDto.setBytesTemplateFileTwo(
                        template.getBytesTemplateFileTwo());
                templateDto.setBytesTemplateFileThree(
                        template.getBytesTemplateFileThree());
                templateDto.setBytesTemplateFileFour(
                        template.getBytesTemplateFileFour());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getTemplateFile function"
                        + " : Template File : Template id = "
                        + template.getInTemplateId());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getTemplateFile function"
                        + " : No Template file found");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getTemplate function" +
                            " : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getTemplateFile function : "
                + Constants.END_STATUS);
        return templateDto;
    }

    /**
     * Method for listing all visualizations with category and kpi details.
     *
     * @return categoryList visualizations
     */
    @Transactional(readOnly = true)
    public List<CategoryDto> getVisualizations() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getVisualizations function : " + ""
                + Constants.START_STATUS);
        List<CategoryDto> categoryList = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Category> categories = dashboardCategoryDataAccess
                    .listAll("from Category cat where cat.deleteStatus = "
                            + ":deleteStatus", parameterList);
            if (categories != null && !categories.isEmpty()) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getVisualizations "
                        + "function : Category List Size is : "
                        + categories.size());
                categoryList = new ArrayList<CategoryDto>();
                for (Category category : categories) {
                    CategoryDto categoryDto = new CategoryDto();
                    categoryDto.setInCategoryId(category.getInCategoryId());
                    categoryDto
                            .setStrCategoryName(category.getStrCategoryName());
                    if (category.getKpis() != null
                            && !category.getKpis().isEmpty()) {
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " DashboardTransaction : "
                                + "getVisualizations function : Kpi List Size" +
                                " is"
                                + " : " + category.getKpis().size());
                        List<KpiDto> kpiDtos = new ArrayList<KpiDto>();
                        for (Kpi kpi : category.getKpis()) {
                            if (kpi.getDeleteStatus() == 1) {
                                KpiDto kpiDto = new KpiDto();
                                kpiDto.setInKpiId(kpi.getInKpiId());
                                kpiDto.setStrKpiName(kpi.getStrKpiName());
                                if (kpi.getVisualizes() != null
                                        && !kpi.getVisualizes().isEmpty()) {
                                    LOGGER.debug(
                                            LogMessageEnum
                                                    .TRANSACTION_LAYER_INFO
                                                    .getMessage() + " "
                                                    + "DashboardTransaction : "
                                                    + "getVisualizations " +
                                                    "function : "
                                                    + "Visualize List Size is" +
                                                    " : "
                                                    + kpi.getVisualizes()
                                                    .size());
                                    List<VisualizeDto> visualizeDtos = new
                                            ArrayList<VisualizeDto>();
                                    for (Visualize visualize : kpi
                                            .getVisualizes()) {
                                        if (visualize.getDeleteStatus() == 1) {
                                            VisualizeDto visualizeDto = new
                                                    VisualizeDto();
                                            visualizeDto
                                                    .setInVisualizeId(visualize
                                                            .getInVisualizeId
                                                                    ());
                                            visualizeDto.setStrVisualizeName(
                                                    visualize
                                                            .getStrVisualizeName());
                                            visualizeDto
                                                    .setStrVisualizeParentType(
                                                            visualize
                                                                    .getStrVisualizeParentType());
                                            visualizeDto.setStrVisualizeSubType(
                                                    visualize
                                                            .getStrVisualizeSubType());
                                            visualizeDto
                                                    .setStrVisualizeConfigDetails(
                                                            visualize
                                                                    .getStrVisualizeConfigDetails());
                                            visualizeDtos.add(visualizeDto);
                                        }
                                    }
                                    kpiDto.setVisualizations(visualizeDtos);
                                } else {
                                    LOGGER.debug(
                                            LogMessageEnum
                                                    .TRANSACTION_LAYER_INFO
                                                    .getMessage() + " "
                                                    + "DashboardTransaction : "
                                                    + "getVisualizations " +
                                                    "function : "
                                                    + "Visualize List Size is" +
                                                    " 0");
                                }
                                kpiDtos.add(kpiDto);
                            }
                        }
                        categoryDto.setKpis(kpiDtos);
                    } else {
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " DashboardTransaction : "
                                + "getVisualizations function : Kpi List Size" +
                                " is"
                                + " 0");
                    }
                    categoryList.add(categoryDto);
                }
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getVisualizations "
                        + "function : Category List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getVisualizations " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getVisualizations function : "
                + Constants.END_STATUS);
        return categoryList;
    }

    /**
     * Method to save/update pipline status.
     *
     * @param pplId         the ppl id
     * @param execPplId     the exec ppl id
     * @param pplExecStatus the ppl exec status
     * @return boolean boolean
     */
    @Transactional
    public boolean savePipelineStatus(final int pplId, final String execPplId,
                                      final String pplExecStatus) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : savePipelineStatus function : " + ""
                + Constants.START_STATUS);
        boolean saveStatus = false;
        try {
            Pipeline pipeline = dashboardPipelineDataAccess
                    .findById(new Pipeline(), pplId);
            if (pipeline != null) {
                Map<String, Object> parameterList = new HashMap<String,
                        Object>();
                parameterList.put("pplId", pplId);
                PipelineExecution pipelineExecution =
                        dashboardPiplineExecDataAccess
                        .findById(
                                "from " + "PipelineExecution pplExe where " +
                                        "pplExe"
                                        + ".pipeline.inPipelineId = :pplId",
                                parameterList);
                if (pipelineExecution == null) {
                    pipelineExecution = new PipelineExecution();
                    pipelineExecution.setDatePipelineStart(new Date());
                }
                pipelineExecution.setDatePipelineUpdated(new Date());
                pipelineExecution.setPipeline(pipeline);
                pipelineExecution.setStrExecPipelineStatus(pplExecStatus);
                pipelineExecution.setInExecPipelineId(execPplId);
                pipelineExecution.setDatePipelineUpdated(new Date());
                pipelineExecution.setStrExecPipelineRemark("");
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : savePipelineStatus "
                        + "function : Pipeline Executed is : Pipeline id = "
                        + pipelineExecution.getPipeline().getInPipelineId()
                        + "," + " Pipeline name = "
                        + pipelineExecution.getPipeline().getStrPipelineName());
                saveStatus = dashboardPiplineExecDataAccess
                        .saveOrUpdate(pipelineExecution);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : savePipelineStatus "
                        + "function : Pipeline Executed is : null");
                saveStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in savePipelineStatus function : "
                    + "", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : savePipelineStatus function : "
                + Constants.END_STATUS);
        return saveStatus;
    }

    /**
     * Method to get Pipeline Execution Url.
     *
     * @param pipelineId the pipeline id
     * @return boolean pipeline execution url
     */
    @Transactional
    public String getPipelineExecutionUrl(final int pipelineId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getPipelineExecutionUrl function : "
                + "" + Constants.START_STATUS);
        String strExecUrl = "";
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("inPipelineId", pipelineId);
            Pipeline pipeline = dashboardPipelineDataAccess.findById(
                    "from " + "Pipeline pipeline where pipeline.inPipelineId = "
                            + ":inPipelineId",
                    parameterList);
            if (pipeline != null && pipeline.getStrPipelineExeURL() != null) {
                strExecUrl = pipeline.getStrPipelineExeURL();
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelineExecutionUrl "
                        + "function : Pipeline Execution URL is : "
                        + strExecUrl);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelineExecutionUrl "
                        + "function : Pipeline Execution URL is : null");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " " + "DashboardTransaction : in getPipelineExecutionUrl "
                    + "function : ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getPipelineExecutionUrl function : "
                + Constants.END_STATUS);
        return strExecUrl;
    }

    /**
     * Method to get Pipeline Execution Url.
     *
     * @param pipelineId the pipeline id
     * @return boolean pipeline execution data
     */
    @Transactional(readOnly = true)
    public PipelineExecutionDto getPipelineExecutionData(final int pipelineId) {
        PipelineExecutionDto pipelineExecutionDto = new PipelineExecutionDto();
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getPipelineExecutionData function : "
                + Constants.START_STATUS);
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("inPipelineId", pipelineId);
            Pipeline pipeline = dashboardPipelineDataAccess.findById(
                    "from " + "Pipeline pipeline where pipeline.inPipelineId = "
                            + ":inPipelineId",
                    parameterList);
            if (pipeline != null && pipeline.getStrPipelineExeURL() != null) {
                // strExecUrl = pipeline.getStrPipelineExeURL();
                pipelineExecutionDto
                        .setInPipelineId(pipeline.getInPipelineId());
                pipelineExecutionDto
                        .setInExecPipelineURl(pipeline.getStrPipelineExeURL());
                pipelineExecutionDto.setStrPipelineConfFile(
                        pipeline.getStrPipelineConfFile());
                pipelineExecutionDto.setStrPipelinePropFile(
                        pipeline.getStrPipelineConfFile());
                pipelineExecutionDto
                        .setStrSourceType(pipeline.getStrSourceType());
                pipelineExecutionDto
                        .setStrProcessType(pipeline.getStrProcessType());
                pipelineExecutionDto.setStrSinkType(pipeline.getStrSinkType());
                String executionId = getPipelineExecutionId(pipelineId);
                pipelineExecutionDto.setInExecPipelineId(executionId);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelineExecutionData"
                        + " function : Pipeline Executed is : Pipeline id = "
                        + pipeline.getInPipelineId() + ", Pipeline name = "
                        + pipeline.getStrPipelineName());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPipelineExecutionData"
                        + " function : Pipeline Executed is : null");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in getPipelineExecutionData "
                    + "function : ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getPipelineExecutionData function : "
                + Constants.END_STATUS);
        return pipelineExecutionDto;
    }

    /**
     * Method to get sink datatypes.
     *
     * @param strSinkType the str sink type
     * @return String sink datatypes
     */
    @Transactional
    public String getSinkDatatypes(final String strSinkType) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getSinkDatatypes function : "
                + Constants.START_STATUS);
        String strSinkDataTypes = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("strSinkType", strSinkType.toLowerCase());
            Datatypes datatypes = dashboardDatatypesDataAccess.findById(
                    "from" + " Datatypes datatype where datatype.strSinkType = "
                            + ":strSinkType",
                    parameterList);
            if (datatypes != null) {
                strSinkDataTypes = datatypes.getStrSinkDataTypes();
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getSinkDatatypes "
                        + "function : Sink Datatype is : " + strSinkDataTypes);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getSinkDatatypes "
                        + "function : Sink Datatype is : null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getSinkDatatypes " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getSinkDatatypes function : "
                + Constants.END_STATUS);
        return strSinkDataTypes;
    }

    /**
     * Method for save or update lookup basic configuration.
     *
     * @param lookupDetailsDto the lookup details dto
     * @return ResponseDto response dto
     */
    @Transactional
    public ResponseDto saveOrUpdateLookupBasicDetails(
            final LookupDetailsDto lookupDetailsDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveOrUpdateLookupBasicDetails "
                + "function : " + Constants.START_STATUS);
        ResponseDto responseDto = new ResponseDto();
        try {
            Lookup lookup = null;
            if (lookupDetailsDto.getInLookupId() == 0) {
                lookup = new Lookup();
                lookup.setCreatedDate(new Date());
                lookup.setUpdatedDate(new Date());
            } else {
                lookup = dashboardLookupDataAccess.findById(new Lookup(),
                        lookupDetailsDto.getInLookupId());
                lookup.setUpdatedDate(new Date());
            }
            if (lookup != null) {
                lookup.setUpdatedDate(new Date());
                lookup.setDeleteStatus(1);
                lookup.setStrLookupConfigName(
                        lookupDetailsDto.getLookupConfigName());
                lookup.setStrLookupType(lookupDetailsDto.getLookupType());
                Gson gson = new Gson();
                String lookupConfig = gson
                        .toJson(lookupDetailsDto.getLookupConfigDetails());
                lookup.setStrLookupConfigDetails(lookupConfig);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : "
                        + "saveOrUpdateLookupBasicDetails function : Lookup "
                        + "Details : Lookup Id = " + lookup.getInLookupId()
                        + "," + " Lookup name = "
                        + lookup.getStrLookupConfigName());
                lookup = dashboardLookupDataAccess.merge(lookup);
                if(lookup != null){
                    responseDto.setId(lookup.getInLookupId());
                    responseDto.setStatus(true);
                } else {
                    responseDto.setStatus(false);
                }
            } else {
                responseDto.setStatus(false);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : "
                        + "saveOrUpdateLookupBasicDetails function : Lookup "
                        + "Details : null");
            }

        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in " +
                    "saveOrUpdateLookupBasicDetails "
                    + "function : ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveOrUpdateLookupBasicDetails "
                + "function : " + Constants.END_STATUS);
        return responseDto;
    }

    /**
     * Check lookup exist or not.
     *
     * @param inLookupId       the in lookup id
     * @param lookupConfigName the lookup config name
     * @param userName         the user name
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean checkLookupExistOrNot(int inLookupId, String lookupConfigName,
                                         String userName) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : checkLookupExistOrNot function : "
                + Constants.START_STATUS);
        boolean status = false;
        try {
            if (lookupConfigName != null && !"".equals(lookupConfigName.trim())
                    && userName != null && !"".equals(userName.trim())) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deleteStatus", 1);
                //paramMap.put("userName",userName);
                paramMap.put("lookupConfigName", lookupConfigName.trim());
                Lookup lookup = dashboardLookupDataAccess.findById("from "
                        + "Lookup lookup where lookup.strLookupConfigName = "
                        + ":lookupConfigName and lookup"
                        + ".deleteStatus = :deleteStatus", paramMap);
                if (lookup != null && inLookupId != lookup.getInLookupId()) {
                    LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                            .getMessage()
                            + " DashboardTransaction : checkLookupExistOrNot "
                            + "function:"
                            + " Lookup is : Lookup id = " + lookup.getInLookupId()
                            + ", Lookup name = " + lookup.getStrLookupConfigName());
                    status = true;
                }
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in checkLookupExistOrNot "
                            + "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        return status;
    }

    /**
     * Method for save or update lookup Advanced configurations.
     *
     * @param lookupAdvancedDetailsDto *
     * @return boolean boolean
     */
    @Transactional
    public boolean saveOrUpdateLookupAdvancedDetails(
            final LookupAdvancedDetailsDto lookupAdvancedDetailsDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveOrUpdateLookupAdvancedDetails "
                + "function : " + Constants.START_STATUS);
        boolean saveStatus = false;
        try {
            LookupDetails lookupDetails = null;
            if (lookupAdvancedDetailsDto.getId() == 0) {
                lookupDetails = new LookupDetails();
                lookupDetails.setCreatedDate(new Date());
                lookupDetails.setStrCreatedUser("");
                lookupDetails.setStrUpdatedUser("");
                lookupDetails.setUpdatedDate(new Date());
            } else {
                lookupDetails = dashboardLookupDetailsDataAccess.findById(
                        new LookupDetails(), lookupAdvancedDetailsDto.getId());
                lookupDetails.setStrUpdatedUser("");
                lookupDetails.setUpdatedDate(new Date());
            }
            if (lookupDetails != null) {
                lookupDetails.setDeleteStatus(1);
                lookupDetails.setStrUpdatedUser("");
                lookupDetails.setUpdatedDate(new Date());
                lookupDetails.setStrSourceType(
                        lookupAdvancedDetailsDto.getStrSourceType());
                if (lookupAdvancedDetailsDto.getSinkId() > 0) {
                    Sink sink = dashboardSinkDataAccess.findById(new Sink(),
                            lookupAdvancedDetailsDto.getSinkId());
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "saveOrUpdateLookupAdvancedDetails " +
                                    "function : Sink"
                                    + " Details : Sink id = "
                                    + sink.getInSinkId() + ", " + "Sink name = "
                                    + sink.getStrSinkName());
                    lookupDetails.setSink(sink);
                    lookupDetails.setStrKeySpaceName(
                            lookupAdvancedDetailsDto.getStrKeySpaceName());
                    lookupDetails.setStrtableName(
                            lookupAdvancedDetailsDto.getStrtableName());
                }
                String tempCsvLocation = "";
                if (lookupAdvancedDetailsDto.getUploadedFile() != null) {
                    tempCsvLocation = PropReader.getPropertyValue(
                            Constants.LOOKUP_TEMP_CSV_LOCATION) + File.separator
                            + lookupAdvancedDetailsDto.getStrUploadedFileName();
                    fileUtil.createFile(tempCsvLocation, commonUtil.toByteArray(
                            lookupAdvancedDetailsDto.getUploadedFile()));
                    lookupDetails.setStrUploadedFile(tempCsvLocation);
                }
                Lookup lookup = dashboardLookupDataAccess.findById(new Lookup(),
                        lookupAdvancedDetailsDto.getInLookupId());
                lookupDetails.setLookup(lookup);
                Set<LookupDetails> lookupDetailsSet = null;
                if (lookup.getLookupDetails() != null) {
                    lookupDetailsSet = lookup.getLookupDetails();
                } else {
                    lookupDetailsSet = new HashSet<LookupDetails>();
                }
                lookupDetailsSet.add(lookupDetails);
                lookup.setLookupDetails(lookupDetailsSet);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : "
                        + "saveOrUpdateLookupAdvancedDetails function : Lookup "
                        + "Details : Lookup Id = " + lookup.getInLookupId()
                        + "," + " Lookup name = "
                        + lookup.getStrLookupConfigName());
                saveStatus = dashboardLookupDataAccess.saveOrUpdate(lookup);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : "
                        + "saveOrUpdateLookupAdvancedDetails function : Lookup "
                        + "Details : null");
                saveStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " " + "DashboardTransaction : in "
                            + "saveOrUpdateLookupAdvancedDetails function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveOrUpdateLookupAdvancedDetails "
                + "function : " + Constants.END_STATUS);
        return saveStatus;
    }

    /**
     * Method for getting lookup basic and advancedDetails.
     *
     * @param inLookupId the in lookup id
     * @return lookupDetailsDto lookup details
     */
    @Transactional(readOnly = true)
    public LookupDetailsDto getLookupDetails(final int inLookupId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getLookupDetails function : " + ""
                + Constants.START_STATUS);
        LookupDetailsDto lookupDetailsDto = null;
        try {
            Lookup lookup = dashboardLookupDataAccess.findById(new Lookup(),
                    inLookupId);
            if (lookup != null) {
                lookupDetailsDto = new LookupDetailsDto();
                lookupDetailsDto.setInLookupId(lookup.getInLookupId());
                lookupDetailsDto.setLookupConfigDetails(
                        lookup.getStrLookupConfigDetails());
                lookupDetailsDto
                        .setLookupConfigName(lookup.getStrLookupConfigName());
                lookupDetailsDto.setLookupType(lookup.getStrLookupType());
                lookupDetailsDto.setCreatedDate(lookup.getCreatedDate());
                lookupDetailsDto.setUpdatedDate(lookup.getUpdatedDate());
                if (lookup.getLookupDetails() != null) {
                    List<LookupAdvancedDetailsDto> lookupAdvancedDetailsDtos
                            = new ArrayList<LookupAdvancedDetailsDto>();
                    for (LookupDetails lookupDetails : lookup
                            .getLookupDetails()) {
                        if (lookupDetails.getDeleteStatus() == 1) {
                            LookupAdvancedDetailsDto lookupAdvancedDetailsDto
                                    = new LookupAdvancedDetailsDto();
                            lookupAdvancedDetailsDto
                                    .setId(lookupDetails.getId());
                            lookupAdvancedDetailsDto.setInLookupId(
                                    lookupDetails.getLookup().getInLookupId());
                            lookupAdvancedDetailsDto.setStrSourceType(
                                    lookupDetails.getStrSourceType());
                            if (lookupDetails.getSink() != null) {
                                lookupAdvancedDetailsDto.setSinkId(
                                        lookupDetails.getSink().getInSinkId());
                                lookupAdvancedDetailsDto
                                        .setSinkName(lookupDetails.getSink()
                                                .getStrSinkName());
                                lookupAdvancedDetailsDto.setStrKeySpaceName(
                                        lookupDetails.getStrKeySpaceName());
                                lookupAdvancedDetailsDto.setStrtableName(
                                        lookupDetails.getStrtableName());
                            }
                            lookupAdvancedDetailsDto.setCreatedDate(
                                    lookupDetails.getCreatedDate());
                            lookupAdvancedDetailsDto.setUpdatedDate(
                                    lookupDetails.getUpdatedDate());
                            lookupAdvancedDetailsDto.setStrUploadedFileName(
                                    lookupDetails.getStrUploadedFile());
                            lookupAdvancedDetailsDto.setDeleteStatus(
                                    lookupDetails.getDeleteStatus());
                            lookupAdvancedDetailsDtos
                                    .add(lookupAdvancedDetailsDto);
                        }
                    }
                    lookupDetailsDto.setLookupAdvancedDetailsDtos(
                            lookupAdvancedDetailsDtos);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getLookupDetails "
                        + "function : Lookup Details : Lookup Id = "
                        + lookup.getInLookupId() + ", Lookup name = "
                        + lookup.getStrLookupConfigName());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getLookupDetails "
                        + "function : Lookup Details : null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getLookupDetails " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getLookupDetails function : "
                + Constants.END_STATUS);
        return lookupDetailsDto;
    }

    /**
     * Method for deleting Lookup by passing lookup id.
     *
     * @param inLookupId the in lookup id
     * @return boolean boolean
     */
    @Transactional
    public boolean deleteLookup(final int inLookupId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteLookup function : "
                + Constants.START_STATUS);
        boolean deleteStatus = false;
        try {
            Lookup lookup = dashboardLookupDataAccess.findById(new Lookup(),
                    inLookupId);
            if (lookup != null) {
                lookup.setDeleteStatus(0);
                lookup.setUpdatedDate(new Date());
                if (lookup.getLookupDetails() != null
                        && !lookup.getLookupDetails().isEmpty()) {
                    for (LookupDetails lookupDetails : lookup
                            .getLookupDetails()) {
                        lookupDetails.setDeleteStatus(0);
                        lookupDetails.setStrUpdatedUser("");
                        lookupDetails.setUpdatedDate(new Date());
                    }
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteLookup function : "
                        + "Lookup Details : Lookup Id = "
                        + lookup.getInLookupId() + ", Lookup name = "
                        + lookup.getStrLookupConfigName());
                deleteStatus = dashboardLookupDataAccess.saveOrUpdate(lookup);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteLookup function : "
                        + "Lookup Details : null");
                deleteStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in deleteLookup " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteLookup function : "
                + Constants.END_STATUS);
        return deleteStatus;
    }

    /**
     * Method for deleting LookupDetails by passing lookupDetails Id.
     *
     * @param lookupDetailsId the lookup details id
     * @return boolean boolean
     */
    @Transactional
    public boolean deleteLookupDetails(final int lookupDetailsId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteLookupDetails function : " + ""
                + Constants.START_STATUS);
        boolean deleteStatus = false;
        try {
            LookupDetails lookupDetails = dashboardLookupDetailsDataAccess
                    .findById(new LookupDetails(), lookupDetailsId);
            if (lookupDetails != null) {
                lookupDetails.setDeleteStatus(0);
                lookupDetails.setStrUpdatedUser("");
                lookupDetails.setUpdatedDate(new Date());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteLookupDetails "
                        + "function : Lookup Details : LookupDetails Id = "
                        + lookupDetails.getId() + ", Lookup name = "
                        + lookupDetails.getLookup().getStrLookupConfigName());
                deleteStatus = dashboardLookupDetailsDataAccess
                        .saveOrUpdate(lookupDetails);
            } else {
                deleteStatus = false;
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteLookupDetails "
                        + "function : LookupDetails : null");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in deleteLookupDetails function" +
                    " : "
                    + "", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteLookupDetails function : "
                + Constants.END_STATUS);
        return deleteStatus;
    }

    /**
     * Method for listing all lookups.
     *
     * @return lookupDetailsDto all lookups
     */
    @Transactional(readOnly = true)
    public List<LookupDetailsDto> getAllLookups() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getAllLookups function : "
                + Constants.START_STATUS);
        List<LookupDetailsDto> lookupDetailsDtos = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Lookup> lookupList = dashboardLookupDataAccess.listAll(
                    "from" + " Lookup lookup where lookup.deleteStatus = "
                            + ":deleteStatus",
                    parameterList);
            if (lookupList != null && !lookupList.isEmpty()) {
                lookupDetailsDtos = new ArrayList<LookupDetailsDto>();
                for (Lookup lookup : lookupList) {
                    LookupDetailsDto lookupDetailsDto = new LookupDetailsDto();
                    lookupDetailsDto.setInLookupId(lookup.getInLookupId());
                    lookupDetailsDto.setLookupConfigName(
                            lookup.getStrLookupConfigName());
                    lookupDetailsDto.setLookupType(lookup.getStrLookupType());
                    lookupDetailsDto.setLookupConfigDetails(
                            lookup.getStrLookupConfigDetails());
                    lookupDetailsDto.setCreatedDate(lookup.getCreatedDate());
                    lookupDetailsDto.setUpdatedDate(lookup.getUpdatedDate());
                    lookupDetailsDto.setStrUpdatedUser("");
                    lookupDetailsDto.setStrCreatedUser("");
                    lookupDetailsDtos.add(lookupDetailsDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getAllLookups function :"
                        + " LookupDetails List Size is " + lookupList.size());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getAllLookups function :"
                        + " LookupDetails List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getAllLookups " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getAllLookups function : "
                + Constants.END_STATUS);
        return lookupDetailsDtos;
    }

    /**
     * Method for testing redis connection status.
     *
     * @param redisHost the redis host
     * @param redisPort the redis port
     * @return boolean boolean
     */
    public boolean testRedisConnection(final String redisHost,
                                       final int redisPort) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : testRedisConnection function : "
                + Constants.START_STATUS);
        boolean status = false;
        try {
            Jedis jedis = new Jedis(redisHost, redisPort);
            String runningStatus = jedis.ping();
            if (PropReader.getPropertyValue(Constants.REDIS_RUNNING_STATUS)
                    .equals(runningStatus)) {
                status = true;
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in testRedisConnection function" +
                    " : "
                    + "", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : testRedisConnection function : "
                + Constants.END_STATUS);
        return status;
    }

    /**
     * Method for getting the list of users.
     *
     * @return List user list
     */
    @Transactional
    public List<IdNameDto> getUserList() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getUserList function : "
                + Constants.START_STATUS);
        List<IdNameDto> listUserDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            parameterList.put("isActive", true);
            List<User> listUser = dashboardUserDataAccess.listAll("from User "
                    + "user where user.deleteStatus = :deleteStatus and user"
                    + ".isActive = :isActive", parameterList);
            if (listUser != null) {
                listUserDto = new ArrayList<IdNameDto>();
                for (User user : listUser) {
                    IdNameDto userDto = new IdNameDto();
                    userDto.setId(user.getInUserId());
                    userDto.setName(user.getStrUserName());
                    listUserDto.add(userDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getUserList function : "
                        + "User List Size is " + listUser.size());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getUserList function : "
                        + "User List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getUserList function" +
                            " : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getUserList function : "
                + Constants.END_STATUS);
        return listUserDto;
    }

    /**
     * Method for saving or updating a role.
     *
     * @param roleDto the role dto
     * @return boolean boolean
     */
    @Transactional
    public boolean saveRole(final RoleDto roleDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveRole function : "
                + Constants.START_STATUS);
        Role role = null;
        boolean saveStatus = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("inUserId", roleDto.getInUserId());
        User user = dashboardUserDataAccess.findById("from User user"
                        + " where user.inUserId = :inUserId",
                paramMap);
        try {
            if (roleDto.getInRoleId() == 0) {
                role = new Role();
                role.setDeleteStatus(1);
                role.setDateCreated(new Date());
                role.setActive(true);
                role.setCreatedBy(user);
            } else {
                role = dashboardRoleDataAccess.findById(new Role(),
                        roleDto.getInRoleId());
                role.setUpdatedBy(user);
            }
            if (role != null) {
                role.setDateUpdated(new Date());
                role.setInRoleId(roleDto.getInRoleId());
                role.setStrRoleName(roleDto.getStrRoleName());
                role.setStrRoleDesc(roleDto.getStrRoleDesc());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveRole function : Role"
                        + " Saved is : Role id = " + role.getInRoleId() + ", "
                        + "Role name = " + role.getStrRoleName());
                saveStatus = dashboardRoleDataAccess.saveOrUpdate(role);
            } else {
                saveStatus = false;
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveRole function : Role"
                        + " Saved is : null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in saveRole function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveRole function : "
                + Constants.END_STATUS);
        return saveStatus;
    }

    /**
     * Check Role exist or not.
     *
     * @param inRoleId the in source id
     * @param roleName the source name
     * @param userName the user name
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean checkRoleExistOrNot(int inRoleId, String roleName, String
            userName){
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : checkRoleExistOrNot function : "
                + Constants.START_STATUS);
        boolean status = false;
        try {
            if (roleName != null && !"".equals(roleName.trim())
                    && userName != null && !"".equals(userName.trim())) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deleteStatus", 1);
                paramMap.put("roleName", roleName);
                //paramMap.put("userName",userName);
                Role role = dashboardRoleDataAccess.findById(
                        "from Role role where "
                                + "role.strRoleName = :roleName " +
                                "and "
                                + "role.deleteStatus = :deleteStatus",
                        paramMap);
                if (role != null && inRoleId != role.getInRoleId()) {
                    LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                            .getMessage()
                            + " DashboardTransaction : checkRoleExistOrNot " +
                            "function:"
                            + " Role is : Role id = " + role.getInRoleId()
                            + ", Role name = " + role.getStrRoleName());
                    status = true;
                }
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in checkRoleExistOrNot "
                            + "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        return status;
    }

    /**
     * Method for getting the role list.
     *
     * @return List role list
     */
    @Transactional
    public List<RoleDto> getRoleList() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getRoleList function : "
                + Constants.START_STATUS);
        List<RoleDto> listRoleDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            parameterList.put("isActive", true);
            List<Role> listRole = dashboardRoleDataAccess.listAll(
                    "from Role "
                            + "role where role.deleteStatus = :deleteStatus " +
                            "and role"
                            + ".isActive = :isActive order by role" +
                            ".dateUpdated desc",
                    parameterList);
            if (listRole != null) {
                listRoleDto = new ArrayList<RoleDto>();
                for (Role role : listRole) {
                    RoleDto roleDto = new RoleDto();
                    roleDto.setInRoleId(role.getInRoleId());
                    roleDto.setStrRoleName(role.getStrRoleName());
                    roleDto.setStrRoleDesc(role.getStrRoleDesc());
                    roleDto.setDateCreated(role.getDateCreated());
                    if(role.getCreatedBy() != null) {
                        roleDto.setStrCreatedUser(role.getCreatedBy().
                                getStrUserName());
                    }
                    AccessLevel accessLevel = role.getAccessLevel();
                    if (accessLevel != null) {
                        AccessLevelDto accessLevelDto = new AccessLevelDto();
                        accessLevelDto
                                .setInAccessId(accessLevel.getInAccessId());
                        roleDto.setAccessLevelDto(accessLevelDto);
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " DashboardTransaction : "
                                + "getRoleList function : Access Level for the "
                                + "Role " + role.getInRoleId() + " is : "
                                + accessLevel.getInAccessId());
                    } else {
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " DashboardTransaction : "
                                + "getRoleList function : Access Level for the "
                                + "Role " + role.getInRoleId() + " is : null");
                    }
                    listRoleDto.add(roleDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getRoleList function : "
                        + "Role List Size is : " + listRole.size());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getRoleList function : "
                        + "Role List Size is : 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getRoleList function" +
                            " : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getRoleList function : "
                + Constants.END_STATUS);
        return listRoleDto;
    }

    /**
     * Method for deleting a role.
     *
     * @param inRoleId the in role id
     * @return boolean boolean
     */
    @Transactional
    public boolean deleteRole(final int inRoleId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteRole function : "
                + Constants.START_STATUS);
        boolean deleteStatus = false;
        try {
            Role role = dashboardRoleDataAccess.findById(new Role(), inRoleId);
            if (role != null) {
                role.setDeleteStatus(0);
                role.setActive(false);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteRole function : "
                        + "Role is : Role id = " + role.getInRoleId()
                        + ", Role " + "name = " + role.getStrRoleName());
                deleteStatus = dashboardRoleDataAccess.saveOrUpdate(role);
            } else {
                deleteStatus = false;
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteRole function : "
                        + "Role is : null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in deleteRole function " +
                            ": ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteRole function : "
                + Constants.END_STATUS);
        return deleteStatus;
    }

    /**
     * Method for mapping a user and a role.
     *
     * @param userDto the user dto
     * @return boolean boolean
     */
    @Transactional
    public boolean mapUserRole(final UserDto userDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : mapUserRole function : "
                + Constants.START_STATUS);
        boolean mapStatus = false;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("username", userDto.getStrUserName());
            parameterList.put("deleteStatus", 1);
            User user = dashboardUserDataAccess.findById("from User user "
                    + "where user.strUserName = :username and user" +
                    ".deleteStatus "
                    + "= :deleteStatus", parameterList);
            if (user != null) {
                Set<Role> roles = null;
                if (userDto.getRoles() != null) {
                    roles = new HashSet<Role>();
                    Map<String, Object> parameterListRole = new
                            HashMap<String, Object>();
                    parameterListRole.put("deleteStatus", 1);
                    for (RoleDto roleDto : userDto.getRoles()) {
                        parameterListRole.put("inRoleId",
                                roleDto.getInRoleId());
                        Role role = dashboardRoleDataAccess.findById(
                                "from " + "Role role where role.inRoleId = " +
                                        ":inRoleId and"
                                        + " role.deleteStatus = :deleteStatus",
                                parameterListRole);
                        roles.add(role);
                    }
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "mapUserRole function : Role List Size " +
                                    "for the "
                                    + "user " + user.getStrUserName() + " is "
                                    + roles.size());
                } else {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "mapUserRole function : Role List Size " +
                                    "for the "
                                    + "user " + user.getStrUserName()
                                    + " is 0");
                }
                user.setRoles(roles);
                user.setInUserId(userDto.getInUserId());
                user.setStrUserName(userDto.getStrUserName());
                user.setStrMappedUser(userDto.getStrMappedUser());
                user.setDateRoleMapped(new Date());
                Role role = new Role();
                role.setInRoleId(userDto.getDefaultRole());
                user.setDefaultRole(role);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : mapUserRole function : "
                        + "User is " + user.getStrUserName());
                mapStatus = dashboardUserDataAccess.saveOrUpdate(user);
            } else {
                mapStatus = false;
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : mapUserRole function : "
                        + "User is null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in mapUserRole function" +
                            " : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : mapUserRole function : "
                + Constants.END_STATUS);
        return mapStatus;
    }

    /**
     * Method for mapping a user and a role.
     *
     * @param inUserId the in user id
     * @return boolean boolean
     */
    @Transactional
    public boolean removeMappedUserRole(final int inUserId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : removeMappedUserRole function : "
                + Constants.START_STATUS);
        boolean mapStatus = false;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("inUserId", inUserId);
            parameterList.put("deleteStatus", 1);
            User user = dashboardUserDataAccess.findById("from User user "
                    + "where user.inUserId = :inUserId and user.deleteStatus = "
                    + ":deleteStatus", parameterList);
            if (user != null) {
                Set<Role> roleList = user.getRoles();
                if (roleList != null) {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "removeMappedUserRole function : Role " +
                                    "List Size "
                                    + "for the user " + user.getStrUserName()
                                    + " is " + roleList.size());
                    roleList.clear();
                } else {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "removeMappedUserRole function : Role " +
                                    "List Size "
                                    + "for the user " + user.getStrUserName()
                                    + " is 0");
                }
                user.setRoles(roleList);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : removeMappedUserRole "
                        + "function : User is " + user.getStrUserName());
                mapStatus = dashboardUserDataAccess.saveOrUpdate(user);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : removeMappedUserRole "
                        + "function : User is null");
                mapStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in removeMappedUserRole " +
                    "function :"
                    + " ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : removeMappedUserRole function : "
                + Constants.END_STATUS);
        return mapStatus;
    }

    /**
     * Method for getting user role details.
     *
     * @param username the username
     * @return userRoleDto user role details
     */
    @Transactional
    public UserDto getUserRoleDetails(final String username) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getUserRoleDetails function : "
                + Constants.START_STATUS);
        UserDto userDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("username", username);
            User user = dashboardUserDataAccess.findById(
                    "from User user " + "where user.strUserName = :username",
                    parameterList);
            if (user != null) {
                userDto = new UserDto();
                userDto.setInUserId(user.getInUserId());
                userDto.setStrUserName(user.getStrUserName());
                userDto.setStrMappedUser(user.getStrMappedUser());
                userDto.setDateRoleMapped(user.getDateRoleMapped());
                if(user.getDefaultRole() != null) {
                    userDto.setDefaultRole(user.getDefaultRole().getInRoleId());
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getUserRoleDetails "
                        + "function : User is " + user.getStrUserName());
                List<RoleDto> roleDtos = null;
                if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                    roleDtos = new ArrayList<RoleDto>();
                    for (Role role : user.getRoles()) {
                        RoleDto roleDto = new RoleDto();
                        roleDto.setInRoleId(role.getInRoleId());
                        roleDto.setStrRoleName(role.getStrRoleName());
                        roleDto.setStrRoleDesc(role.getStrRoleDesc());
                        roleDto.setActive(role.isActive());
                        roleDto.setDateCreated(role.getDateCreated());
                        roleDto.setStrCreatedUser(role.getCreatedBy().getStrUserName());
                        roleDtos.add(roleDto);
                    }
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "getUserRoleDetails function : Role " +
                                    "List Size for "
                                    + "the user " + user.getStrUserName()
                                    + " is " + user.getRoles().size());
                } else {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "getUserRoleDetails function : Role " +
                                    "List Size for "
                                    + "the user " + user.getStrUserName()
                                    + " is 0");
                }
                userDto.setRoles(roleDtos);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getUserRoleDetails "
                        + "function : User is null");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in getUserRoleDetails function : "
                    + "", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getUserRoleDetails function : "
                + Constants.END_STATUS);
        return userDto;
    }

    /**
     * Method for listing all user role details.
     *
     * @return userRoleDto list
     */
    @Transactional
    public List<UserDto> listUserRoleDetails() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : listUserRoleDetails function : "
                + Constants.START_STATUS);
        List<UserDto> userDtoList = null;
        UserDto userDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<User> userList = dashboardUserDataAccess.listAll(
                    "from User "
                            + "user where user.deleteStatus = :deleteStatus",
                    parameterList);
            if (userList != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : listUserRoleDetails "
                        + "function : User List Size is " + userList.size());
                userDtoList = new ArrayList<UserDto>();
                for (User user : userList) {
                    userDto = new UserDto();
                    userDto.setInUserId(user.getInUserId());
                    userDto.setStrUserName(user.getStrUserName());
                    userDto.setStrMappedUser(user.getStrMappedUser());
                    userDto.setDateRoleMapped(user.getDateRoleMapped());
                    userDto.setDeleteStatus(user.getDeleteStatus());
                    userDto.setActive(user.isActive());
                    if(user.getDefaultRole() != null) {
                        userDto.setDefaultRole(user.getDefaultRole().getInRoleId());
                    }
                    List<RoleDto> roleDtos = new ArrayList<RoleDto>();
                    Set<Role> roleSet = user.getRoles();
                    if (roleSet != null && !roleSet.isEmpty()) {
                        for (Role role : roleSet) {
                            if (role.getDeleteStatus() == 1
                                    && role.isActive()) {
                                RoleDto roleDto = new RoleDto();
                                roleDto.setInRoleId(role.getInRoleId());
                                roleDto.setStrRoleName(role.getStrRoleName());
                                roleDto.setStrRoleDesc(role.getStrRoleDesc());
                                roleDto.setActive(role.isActive());
                                roleDto.setDateCreated(role.getDateCreated());
                                roleDtos.add(roleDto);
                            }
                        }
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " DashboardTransaction : "
                                + "listUserRoleDetails function : Role List " +
                                "Size"
                                + " for the user " + user.getStrUserName() + " "
                                + "is " + roleDtos.size());
                        userDto.setRoles(roleDtos);
                        userDtoList.add(userDto);
                    } else {
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " DashboardTransaction : "
                                + "listUserRoleDetails function : Role List " +
                                "Size"
                                + " for the user is 0");
                    }
                }
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : listUserRoleDetails "
                        + "function : User List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in listUserRoleDetails function" +
                    " : "
                    + "", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : listUserRoleDetails function : "
                + Constants.END_STATUS);
        return userDtoList;
    }

    /**
     * Method for saving feature details.
     *
     * @param featureDto the feature dto
     * @return true, if successful
     */
    @Transactional
    public boolean saveFeature(final FeatureDto featureDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveFeature function : "
                + Constants.START_STATUS);
        boolean saveStatus = false;
        try {
            Feature feature = null;
            if (featureDto.getInFeatureId() == 0) {
                feature = new Feature();
                feature.setDeleteStatus(1);
            } else {
                feature = dashboardFeatureDataAccess.findById(new Feature(),
                        featureDto.getInFeatureId());
            }
            feature.setInFeatureId(featureDto.getInFeatureId());
            feature.setStrFeatureName(featureDto.getStrFeatureName());
            saveStatus = dashboardFeatureDataAccess.saveOrUpdate(feature);
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in saveFeature function" +
                            " : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveFeature function : "
                + Constants.END_STATUS);
        return saveStatus;
    }

    /**
     * Method for listing the features.
     *
     * @return the feature list
     */
    @Transactional
    public List<FeatureDto> getFeatureList() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getFeatureList function : "
                + Constants.START_STATUS);
        List<FeatureDto> featureDtoList = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Feature> featureList = dashboardFeatureDataAccess.listAll(
                    "from Feature feature where feature.deleteStatus = "
                            + ":deleteStatus",
                    parameterList);
            if (featureList != null) {
                featureDtoList = new ArrayList<FeatureDto>();
                for (Feature feature : featureList) {
                    FeatureDto featureDto = new FeatureDto();
                    featureDto.setInFeatureId(feature.getInFeatureId());
                    featureDto.setStrFeatureName(feature.getStrFeatureName());
                    featureDtoList.add(featureDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getFeatureList function "
                        + ": Feature List Size is " + featureDtoList.size());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getFeatureList function "
                        + ": Feature List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in getFeatureList " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getFeatureList function : "
                + Constants.END_STATUS);
        return featureDtoList;
    }

    /**
     * Method for mapping a role and an access level.
     *
     * @param roleDto the role dto
     * @return boolean boolean
     */
    @Transactional
    public boolean mapRoleAccess(final RoleDto roleDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : mapRoleAccess function : "
                + Constants.START_STATUS);
        boolean mapStatus = false;
        try {
            AccessLevel accessLevel = null;
            Set<FeatureAccess> featureAccessSet = null;
            FeatureAccess featureAccess = null;
            Set<PortalAccess> portalAccessSet = null;
            List<PortalAccess> portalAccessList = null;
            PortalAccess portalAccess = null;
            Role role = dashboardRoleDataAccess.findById(new Role(),
                    roleDto.getInRoleId());
            if (role != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : mapRoleAccess function "
                        + ":" + " Role is : Role id = " + role.getInRoleId()
                        + ", " + "Role" + " name = " + role.getStrRoleName());
                AccessLevelDto accessLevelDto = roleDto.getAccessLevelDto();
                if (accessLevelDto.getInAccessId() == 0) {
                    accessLevel = new AccessLevel();
                    accessLevel.setDateCreated(new Date());
                } else {
                    accessLevel = role.getAccessLevel();
                    Map<String, Object> parameterList = new HashMap<String, Object>();
                    parameterList.put("accessId", accessLevel.getInAccessId());
                }
                accessLevel.setDateUpdated(new Date());
                accessLevel.setStrAccessLevelName(
                        accessLevelDto.getStrAccessLevelName());
                if (accessLevelDto.getFeatureAccesses() != null) {
                    featureAccessSet = new HashSet<FeatureAccess>();
                    for (FeatureAccessDto featureAccessDto : accessLevelDto
                            .getFeatureAccesses()) {
                        if (featureAccessDto.getInFeatureAccessId() == 0) {
                            featureAccess = new FeatureAccess();
                        } else {
                            featureAccess =
                                    dashboardFeatureAccessLevelDataAccess
                                    .findById(new FeatureAccess(),
                                            featureAccessDto
                                                    .getInFeatureAccessId());
                        }
                        Feature feature = dashboardFeatureDataAccess.findById(
                                new Feature(),
                                featureAccessDto.getInFeatureId());
                        featureAccess.setFeature(feature);
                        featureAccess.setAccessLevel(accessLevel);
                        featureAccess.setFeatureViewEnabled(
                                featureAccessDto.isFeatureViewEnabled());
                        featureAccess.setFeatureEditEnabled(
                                featureAccessDto.isFeatureEditEnabled());
                        featureAccess.setFeatureDeleteEnabled(
                                featureAccessDto.isFeatureDeleteEnabled());
                        featureAccessSet.add(featureAccess);
                    }
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "mapRoleAccess function : Feature " +
                                    "Access List "
                                    + "Size" + " is "
                                    + featureAccessSet.size());
                    accessLevel.setFeatureAccesses(featureAccessSet);
                } else {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "mapRoleAccess function : Feature " +
                                    "Access List "
                                    + "Size" + " is 0");
                }

                if (accessLevelDto.getPortalAccesses() != null) {
                    portalAccessSet = new HashSet<PortalAccess>();
                    for (PortalAccessDto portalAccessDto : accessLevelDto
                            .getPortalAccesses()) {
                        if (portalAccessDto.getInPortalAccessId() == 0) {
                            portalAccess = new PortalAccess();
                        } else {
                            portalAccess = dashboardPortalAccessLevelDataAccess
                                    .findById(new PortalAccess(),
                                            portalAccessDto
                                                    .getInPortalAccessId());
                        }
                        portalAccess.setAccessLevel(accessLevel);
                        Portal portal = portalDataAccess.findById(new Portal(),
                                portalAccessDto.getInPortalId());
                        portalAccess.setPortal(portal);
                        portalAccess.setPortalViewEnabled(
                                portalAccessDto.isPortalViewEnabled());
                        portalAccess.setPortalEditEnabled(
                                portalAccessDto.isPortalEditEnabled());
                        portalAccess.setPortalDeleteEnabled(
                                portalAccessDto.isPortalDeleteEnabled());

                        Dashboard dashboard = portalDashboardDataAccess
                                .findById(new Dashboard(),
                                        portalAccessDto.getInDashboardId());
                        portalAccess.setDashboard(dashboard);
                        portalAccess.setDashboardViewEnabled(
                                portalAccessDto.isDashboardViewEnabled());
                        portalAccess.setDashboardEditEnabled(
                                portalAccessDto.isDashboardEditEnabled());
                        portalAccess.setDashboardDeleteEnabled(
                                portalAccessDto.isDashboardDeleteEnabled());

                        Category category = dashboardCategoryDataAccess
                                .findById(new Category(),
                                        portalAccessDto.getInCategoryId());
                        portalAccess.setCategory(category);
                        portalAccess.setCategoryViewEnabled(
                                portalAccessDto.isCategoryViewEnabled());
                        portalAccess.setCategoryEditEnabled(
                                portalAccessDto.isCategoryEditEnabled());
                        portalAccess.setCategoryDeleteEnabled(
                                portalAccessDto.isCategoryDeleteEnabled());

                        Kpi kpi = dashboardKpiDataAccess.findById(new Kpi(),
                                portalAccessDto.getInKpiId());
                        portalAccess.setKpi(kpi);
                        portalAccess.setKpiViewEnabled(
                                portalAccessDto.isKpiViewEnabled());
                        portalAccess.setKpiEditEnabled(
                                portalAccessDto.isKpiEditEnabled());
                        portalAccess.setKpiDeleteEnabled(
                                portalAccessDto.isKpiDeleteEnabled());

                        Visualize visualize = dashboardVisualizeDataAccess
                                .findById(new Visualize(),
                                        portalAccessDto.getInVisualizeId());
                        portalAccess.setVisualize(visualize);
                        portalAccess.setVisualizeViewEnabled(
                                portalAccessDto.isVisualizeViewEnabled());
                        portalAccess.setVisualizeEditEnabled(
                                portalAccessDto.isVisualizeEditEnabled());
                        portalAccess.setVisualizeDeleteEnabled(
                                portalAccessDto.isVisualizeDeleteEnabled());

                        portalAccessSet.add(portalAccess);
                    }
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "mapRoleAccess function : Portal Access" +
                                    " List Size"
                                    + " is " + portalAccessSet.size());
                    accessLevel.getPortalAccesses().clear();
                    accessLevel.getPortalAccesses().addAll(portalAccessSet);
                } else {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "mapRoleAccess function : Portal Access" +
                                    " List Size"
                                    + " is 0");
                    accessLevel.getPortalAccesses().clear();
                }

                role.setAccessLevel(accessLevel);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : mapRoleAccess function "
                        + ": Access Level mapped for the Role : Role id = "
                        + role.getInRoleId() + ", Role name = "
                        + role.getStrRoleName() + " is "
                        + accessLevel.getInAccessId());
                mapStatus = dashboardRoleDataAccess.saveOrUpdate(role);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : mapRoleAccess function "
                        + ": Role is : null");
                mapStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " DashboardTransaction : in mapRoleAccess " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : mapRoleAccess function : "
                + Constants.END_STATUS);
        return mapStatus;
    }

    /**
     * Method for getting role access details.
     *
     * @param inRoleId the in role id
     * @return accessLevelDto role access details
     */
    @Transactional(readOnly = true)
    public RoleDto getRoleAccessDetails(final int inRoleId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getRoleAccessDetails function : "
                + Constants.START_STATUS);
        RoleDto roleDto = null;
        AccessLevelDto accessLevelDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("roleId", inRoleId);
            Role role = dashboardRoleDataAccess.findById(new Role(), inRoleId);
            if (role != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getRoleAccessDetails "
                        + "function : Role is : Role id = " + role.getInRoleId()
                        + ", Role name = " + role.getStrRoleName());
                accessLevelDto = new AccessLevelDto();
                roleDto = new RoleDto();
                roleDto.setInRoleId(role.getInRoleId());
                roleDto.setStrRoleName(role.getStrRoleName());
                roleDto.setStrRoleDesc(role.getStrRoleDesc());
                roleDto.setActive(role.isActive());
                int inAccessId = role.getAccessLevel().getInAccessId();
                String strAccessLevelName = role.getAccessLevel()
                        .getStrAccessLevelName();
                Map<String, Object> parameterListAccess = new HashMap<String,
                        Object>();
                parameterListAccess.put("inAccessId", inAccessId);
                Set<PortalAccess> portalAccessList = role.getAccessLevel()
                        .getPortalAccesses();
                List<PortalDto> portalDtoList = getPortalDtoListDetails(
                        portalAccessList);
                Set<FeatureAccess> featureAccessList = role.getAccessLevel()
                        .getFeatureAccesses();
                List<FeatureAccessDto> featureAccessDtoList = null;
                if (featureAccessList != null && !featureAccessList.isEmpty()) {
                    featureAccessDtoList = new ArrayList<FeatureAccessDto>();
                    for (FeatureAccess featureAccess : featureAccessList) {
                        FeatureAccessDto featureAccessDto = new
                                FeatureAccessDto();
                        featureAccessDto.setInFeatureAccessId(
                                featureAccess.getInFeatureAccessId());
                        featureAccessDto.setInFeatureId(
                                featureAccess.getFeature().getInFeatureId());
                        featureAccessDto.setStrFeatureName(
                                featureAccess.getFeature().getStrFeatureName());
                        featureAccessDto.setFeatureViewEnabled(
                                featureAccess.isFeatureViewEnabled());
                        featureAccessDto.setFeatureEditEnabled(
                                featureAccess.isFeatureEditEnabled());
                        featureAccessDto.setFeatureDeleteEnabled(
                                featureAccess.isFeatureDeleteEnabled());
                        featureAccessDtoList.add(featureAccessDto);
                    }
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "getRoleAccessDetails function : " +
                                    "FeatureAccess "
                                    + "List Size is "
                                    + featureAccessDtoList.size());
                } else {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : "
                                    + "getRoleAccessDetails function : " +
                                    "FeatureAccess "
                                    + "List Size is 0");
                }
                accessLevelDto.setInAccessId(inAccessId);
                accessLevelDto.setStrAccessLevelName(strAccessLevelName);
                accessLevelDto.setPortalDtoList(portalDtoList);
                accessLevelDto.setFeatureAccesses(featureAccessDtoList);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : mapRoleAccess function "
                        + ": Access Level mapped for the Role : Role id = "
                        + role.getInRoleId() + ", Role name = "
                        + role.getStrRoleName() + " is " + inAccessId);
                roleDto.setAccessLevelDto(accessLevelDto);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getRoleAccessDetails "
                        + "function : Role is : null");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in getRoleAccessDetails function "
                    + ": ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getRoleAccessDetails function : "
                + Constants.END_STATUS);
        return roleDto;
    }

    /**
     * Method for getting portal dto list.
     *
     * @param portalAccessList
     *            the portal access list
     * @return List
     */
    private List<PortalDto> getPortalDtoListDetails(
            final Set<PortalAccess> portalAccessList) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getPortalDtoListDetails function : "
                + Constants.START_STATUS);
        List<PortalDto> portalDtoList = new ArrayList<PortalDto>();
        try {
            portalDtoList = portalTransaction
                    .getPortalDashboardCategoryKpiVisualizeTree();
            if (portalDtoList != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPortalDtoListDetails"
                        + " function : Portal List Size is "
                        + portalDtoList.size());
                for (PortalAccess portalAccess : portalAccessList) {
                    for (PortalDto portalDto : portalDtoList) {
                        if (portalAccess.getPortal().getPortalId() == portalDto
                                .getPortalId()) {
                            portalDto.setPortalViewEnabled(
                                    portalAccess.isPortalViewEnabled());
                            if (portalDto.getDashboards() != null) {
                                LOGGER.debug(LogMessageEnum
                                        .TRANSACTION_LAYER_INFO
                                        .getMessage() + " " +
                                        "DashboardTransaction : "
                                        + "getPortalDtoListDetails function :" +
                                        " Dashboard"
                                        + " List Size under the portal : " +
                                        "Portal Id = "
                                        + portalDto.getPortalId() + ", Portal" +
                                        " name = "
                                        + "" + portalDto.getStrPortalName() +
                                        " is "
                                        + portalDto.getDashboards().size());
                                for (DashboardDto dashboardDto : portalDto
                                        .getDashboards()) {
                                    if (portalAccess.getDashboard()
                                            .getDashboardId() == dashboardDto
                                            .getDashboardId()) {
                                        dashboardDto.setDashboardViewEnabled(
                                                portalAccess
                                                        .isDashboardViewEnabled());
                                        if (dashboardDto
                                                .getCategoryKpiVisualizeDtoList() != null) {
                                            LOGGER.debug(
                                                    LogMessageEnum
                                                            .TRANSACTION_LAYER_INFO
                                                            .getMessage()
                                                            + " DashboardTransaction : "
                                                            +
                                                            "getPortalDtoListDetails function : "
                                                            + "Category List " +
                                                            "Size under the "
                                                            + "Dashboard : " +
                                                            "Dashboard Id = "
                                                            + dashboardDto
                                                            .getDashboardId()
                                                            + ", " +
                                                            "Dashboard name = "
                                                            + dashboardDto
                                                            .getStrDashboardlName()
                                                            + " is "
                                                            + dashboardDto
                                                            .getCategoryKpiVisualizeDtoList()
                                                            .size());
                                            for (CategoryKpiVisualizeDto
                                                    categoryKpiVisualizeDto :
                                                    dashboardDto
                                                    .getCategoryKpiVisualizeDtoList()) {
                                                if (portalAccess.getCategory()
                                                        .getInCategoryId() ==
                                                        categoryKpiVisualizeDto
                                                        .getInCategoryId()) {
                                                    categoryKpiVisualizeDto
                                                            .setCategoryViewEnabled(
                                                                    portalAccess
                                                                            .isCategoryViewEnabled());
                                                    if (categoryKpiVisualizeDto
                                                            .getKpis() !=
                                                            null) {
                                                        LOGGER.debug(
                                                                LogMessageEnum.TRANSACTION_LAYER_INFO
                                                                        .getMessage() + " "
                                                                        +
                                                                        "DashboardTransaction : "
                                                                        +
                                                                        "getPortalDtoListDetails "
                                                                        +
                                                                        "function : KPI List Size "
                                                                        +
                                                                        "under the Category : "
                                                                        +
                                                                        "Category Id = "
                                                                        +
                                                                        categoryKpiVisualizeDto
                                                                        .getInCategoryId()
                                                                        + ", " +
                                                                        "Category name = "
                                                                        +
                                                                        categoryKpiVisualizeDto
                                                                        .getStrCategoryName()
                                                                        + " is "
                                                                        +
                                                                        categoryKpiVisualizeDto
                                                                        .getKpis()
                                                                        .size());
                                                        for (KpiDto kpiDto :
                                                                categoryKpiVisualizeDto
                                                                .getKpis()) {
                                                            if (portalAccess
                                                                    .getKpi()
                                                                    .getInKpiId() == kpiDto
                                                                    .getInKpiId()) {
                                                                kpiDto.setKpiViewEnabled(
                                                                        portalAccess
                                                                                .isKpiViewEnabled());
                                                                if (kpiDto
                                                                        .getVisualizations() != null) {
                                                                    LOGGER.debug(
                                                                            LogMessageEnum.TRANSACTION_LAYER_INFO
                                                                                    .getMessage()
                                                                                    + " DashboardTransaction : getPortalDtoListDetails function : Visualize List Size under the KPI : KPI Id = "
                                                                                    + kpiDto.getInKpiId()
                                                                                    + ", KPI name = "
                                                                                    + kpiDto.getStrKpiName()
                                                                                    + " is "
                                                                                    + kpiDto.getVisualizations()
                                                                                    .size());
                                                                    for (VisualizeDto visualizeDto : kpiDto
                                                                            .getVisualizations()) {
                                                                        if (portalAccess
                                                                                .getVisualize()
                                                                                .getInVisualizeId() == visualizeDto
                                                                                .getInVisualizeId()) {
                                                                            visualizeDto
                                                                                    .setVisualizeViewEnabled(
                                                                                            portalAccess
                                                                                                    .isVisualizeViewEnabled());
                                                                        }
                                                                    }
                                                                } else {
                                                                    LOGGER.debug(
                                                                            LogMessageEnum.TRANSACTION_LAYER_INFO
                                                                                    .getMessage()
                                                                                    + " DashboardTransaction : getPortalDtoListDetails function : Visualize List Size under the KPI : KPI Id = "
                                                                                    + kpiDto.getInKpiId()
                                                                                    + ", KPI name = "
                                                                                    + kpiDto.getStrKpiName()
                                                                                    + " is 0");
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        LOGGER.debug(
                                                                LogMessageEnum.TRANSACTION_LAYER_INFO
                                                                        .getMessage() + " "
                                                                        +
                                                                        "DashboardTransaction : "
                                                                        +
                                                                        "getPortalDtoListDetails "
                                                                        +
                                                                        "function : KPI List Size "
                                                                        +
                                                                        "under the Category : "
                                                                        +
                                                                        "Category Id = "
                                                                        +
                                                                        categoryKpiVisualizeDto
                                                                        .getInCategoryId()
                                                                        + ", " +
                                                                        "Category name = "
                                                                        +
                                                                        categoryKpiVisualizeDto
                                                                        .getStrCategoryName()
                                                                        + " is 0");
                                                    }
                                                }
                                            }
                                        } else {
                                            LOGGER.debug(
                                                    LogMessageEnum
                                                            .TRANSACTION_LAYER_INFO
                                                            .getMessage()
                                                            + " DashboardTransaction : "
                                                            +
                                                            "getPortalDtoListDetails function : "
                                                            + "Category List " +
                                                            "Size under the "
                                                            + "Dashboard : " +
                                                            "Dashboard Id = "
                                                            + dashboardDto
                                                            .getDashboardId()
                                                            + ", " +
                                                            "Dashboard name = "
                                                            + dashboardDto
                                                            .getStrDashboardlName()
                                                            + " is 0");
                                        }
                                    }
                                }
                            } else {
                                LOGGER.debug(LogMessageEnum
                                        .TRANSACTION_LAYER_INFO
                                        .getMessage() + " " +
                                        "DashboardTransaction : "
                                        + "getPortalDtoListDetails function :" +
                                        " Dashboard"
                                        + " List Size under the portal : " +
                                        "Portal Id = "
                                        + portalDto.getPortalId() + ", Portal" +
                                        " name = "
                                        + "" + portalDto.getStrPortalName() +
                                        " is 0");
                            }
                        }
                    }
                }
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getPortalDtoListDetails"
                        + " function : Portal List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " " + "DashboardTransaction : n getPortalDtoListDetails "
                    + "function : ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getPortalDtoListDetails function : "
                + Constants.END_STATUS);
        return portalDtoList;
    }

    /**
     * Method for getting the status of process query validation.
     *
     * @param queryPlanDto the query plan dto
     * @return the query plan status
     */
    @Transactional(readOnly = true)
    public QueryPlanResultDto getQueryPlanStatus(
            final QueryPlanDto queryPlanDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getQueryPlanStatus function : "
                + Constants.START_STATUS);
        QueryPlanResultDto queryPlanResultDto = new QueryPlanResultDto();
        String status = "";
        String command = "";
        String remoteHost = PropReader.getPropertyValue(Constants.REMOTE_HOST);
        String remoteUser = PropReader
                .getPropertyValue(Constants.REMOTE_USER_NAME);
        String userPswd = PropReader
                .getPropertyValue(Constants.REMOTE_USER_PASSWORD);
        String processType = queryPlanDto.getProcessType();
        if (processType.equals("spark")) {
            String sparkCmd = PropReader
                    .getPropertyValue(Constants.SPARK_SQL_CMD);
            String sparkQuery = queryPlanDto.getProcessQuery();
            command = sparkCmd + " " + sparkQuery + "\"";
        } else if (processType.equals("flink")) {

            // TO DO Query Validation for Flink Process
        }
        try {
            Session session = queryPlanUtil.connect(remoteHost, remoteUser,
                    userPswd);
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            InputStream in = channel.getInputStream();
            channel.connect();
            String queryResult = queryPlanUtil.getStringFromInputStream(in,
                    channel);
            status = queryPlanUtil.getStatus(queryResult);
            String[] result = status.split("\\|");
            queryPlanResultDto.setIsValid(result[0]);
            queryPlanResultDto.setStatusMessage(result[1]);
            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in getQueryPlanStatus function : "
                    + "", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getQueryPlanStatus function : "
                + Constants.END_STATUS);
        return queryPlanResultDto;
    }

    /**
     * Method for validating the Sink Table details.
     *
     * @param sinkValidationDto the sink validation dto
     * @return true, if is valid sink table
     */
    @Transactional
    public boolean isValidSinkTable(final SinkValidationDto sinkValidationDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : isValidSinkTable function : "
                + Constants.START_STATUS);
        boolean isValid = true;
        int sinkTypeId = sinkValidationDto.getSinkID();
        Sink sinkDetails = dashboardSinkDataAccess.findById(new Sink(),
                sinkTypeId);
        String sinkType = sinkDetails.getStrSinkType();
        try {
            if (sinkType.equals("Elassandra")) {
                String cassandraKeySpace = sinkValidationDto
                        .getSinkSchemaName();
                String cassandraTableName = sinkValidationDto
                        .getSinkTableName();
                String sinkConf = sinkDetails.getStrSinkConfigDetails();
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(
                        DeserializationConfig.Feature
                                .FAIL_ON_UNKNOWN_PROPERTIES,
                        false);
                CassandraSinkDto cassandraSinkDto = mapper.readValue(sinkConf,
                        CassandraSinkDto.class);
                final String nodes = cassandraSinkDto.getStrNodelst();
                String[] nodeArray = nodes.split(",");
                Cluster cluster = Cluster.builder()
                        .addContactPoint(nodeArray[0]).build();
                KeyspaceMetadata cassandraMetadata = cluster.getMetadata()
                        .getKeyspace(cassandraKeySpace);
                TableMetadata cassandraTable = cassandraMetadata
                        .getTable(cassandraTableName);
                // Keyspace exists or Not Found
                if (cassandraTable == null) {
                    // System.out.println("Valid Table");
                    isValid = true;
                } else {
                    isValid = false;
                    // System.out.println("Table already exists");
                }
            } else if (sinkType.equals("")) {
                // TO DO for handling other sink types
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in isValidSinkTable " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : isValidSinkTable function : "
                + Constants.END_STATUS);
        return isValid;
    }

    /**
     * Method for login - checking whether the user exist or not by passing
     * userid and password.
     *
     * @param userName the user name
     * @param password the password
     * @return userDto user dto
     */
    @Transactional
    public UserDto logIn(final String userName, final String password) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : logIn function : "
                + Constants.START_STATUS);
        UserDto userDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            parameterList.put("strUserName", userName);
            parameterList.put("isActive", true);
            /*JSch jsch = new JSch();
            InetAddress ipAddr = InetAddress.getLocalHost();
            JSch.setConfig("StrictHostKeyChecking", "no");
            Session session = jsch.getSession(userName,
                    String.valueOf(ipAddr.getHostAddress()),
                    Constants.SFTP_PORT_NUMBER);
            LOGGER.info(
                    "Server IP = " + String.valueOf(ipAddr.getHostAddress()));
            session.setPassword(password);
            session.connect(Constants.NUMBER_THIRTY_THOUSAND);
            Channel channel = session.openChannel("shell");
            channel.connect(Constants.NUMBER_THREE * Constants.NUMBER_THOUSAND);
            if (channel.isConnected()) {*/
                User user = dashboardUserDataAccess.findById("from User user "
                        + "where user.strUserName = :strUserName and user"
                        + ".deleteStatus = :deleteStatus and user.isActive = "
                        + ":isActive", parameterList);
                if (user == null) {
                    user = new User();
                    user.setStrUserName(userName);
                    user.setDeleteStatus(1);
                    user.setActive(true);
                    user.setDateCreated(new Date());
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " DashboardTransaction : logIn "
                                    + "function : User is " + userName);
                    dashboardUserDataAccess.saveOrUpdate(user);
                } else {
                    if (user.isActive()) {
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " DashboardTransaction : "
                                + "logIn function : The User " + userName + " "
                                + "is Active");
                        if (user.getRoles() != null) {
                            LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                    .getMessage() + " "
                                    + "DashboardTransaction : logIn function : "
                                    + "The User " + userName + " is given "
                                    + user.getRoles().size() + " roles");
                            userDto = new UserDto();
                            List<RoleDto> roles = new ArrayList<RoleDto>();
                            for (Role role : user.getRoles()) {
                                if (role.getDeleteStatus() == 1) {
                                    RoleDto roleDto = new RoleDto();
                                    roleDto.setInRoleId(role.getInRoleId());
                                    roleDto.setStrRoleName(
                                            role.getStrRoleName());
                                    roles.add(roleDto);
                                }
                            }
                            userDto.setInUserId(user.getInUserId());
                            userDto.setStrUserName(user.getStrUserName());
                            if(user.getDefaultRole() != null) {
                                userDto.setDefaultRole(user.getDefaultRole().getInRoleId());
                            }
                            userDto.setRoles(roles);
                        } else {
                            LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                    .getMessage() + " "
                                    + "DashboardTransaction : logIn function : "
                                    + "The User " + userName + " is given no "
                                    + "roles");
                        }
                    } else {
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " DashboardTransaction : "
                                + "logIn function : The User " + userName + " "
                                + "is Not Active");
                    }
                }
//               channel.disconnect();
//            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " " + "DashboardTransaction : in logIn function : ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : logIn function : "
                + Constants.END_STATUS);
        return userDto;
    }

    /**
     * Method for adding user login details.
     *
     * @param userDto the user dto
     * @return loginDetailsId int
     */
    @Transactional
    public int addLoginDetails(final UserDto userDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : addLoginDetails function : "
                + Constants.START_STATUS);
        int loginDetailsId = 0;
        try {
            User user = dashboardUserDataAccess.findById(new User(),
                    userDto.getInUserId());
            if (user != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : addLoginDetails "
                        + "function : User logged in is "
                        + user.getStrUserName());
                UserLoginDetails userLoginDetails = new UserLoginDetails();
                userLoginDetails.setLoginDate(new Date());
                userLoginDetails.setUser(user);
                userLoginDetails = dashboardUserLoginDataAccess
                        .merge(userLoginDetails);
                loginDetailsId = userLoginDetails.getId();
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : addLoginDetails "
                        + "function : User loginDetailsId " + loginDetailsId);
            } else {
                loginDetailsId = 0;
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : addLoginDetails "
                        + "function : User loginDetailsId " + loginDetailsId);
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "DashboardTransaction : in addLoginDetails " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : addLoginDetails function : "
                + Constants.END_STATUS);
        return loginDetailsId;
    }

    /**
     * Method for updating user logout details.
     *
     * @param loginDetailsId the login details id
     * @return boolean boolean
     */
    @Transactional
    public boolean updateLogoutDetails(final int loginDetailsId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : updateLogoutDetails function : "
                + Constants.START_STATUS);
        boolean updateStatus = false;
        try {
            UserLoginDetails userLoginDetails = dashboardUserLoginDataAccess
                    .findById(new UserLoginDetails(), loginDetailsId);
            if (userLoginDetails != null) {
                userLoginDetails.setLogoutDate(new Date());
                updateStatus = dashboardUserLoginDataAccess
                        .saveOrUpdate(userLoginDetails);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : updateLogoutDetails "
                        + "function : Login Update Status is " + updateStatus);
            } else {
                updateStatus = false;
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : updateLogoutDetails "
                        + "function : Login Update Status is " + updateStatus);
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in updateLogoutDetails function :"
                    + " ", e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : addLoginDetails function : "
                + Constants.END_STATUS);
        return updateStatus;
    }

    /**
     * Method for geting Cassandra Table and Uploaded CSV file Schema details,
     * Table data and CSV data for preview  by passing lookupDetails Id and
     * data required status.
     *
     * @param lookupDetailsId the lookup details id
     * @param isDataRequired  the is data required
     * @return PreviewDataDto preview data dto
     */
    @Transactional(readOnly = true)
    public PreviewDataDto getSchemaAndContent(int lookupDetailsId, boolean isDataRequired){
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : getSchemaAndContent function : "
                + Constants.START_STATUS);
        PreviewDataDto previewDataDto = null;
        try{
            LookupDetails lookupDetails = dashboardLookupDetailsDataAccess.findById(new LookupDetails(), lookupDetailsId);
            if(lookupDetails == null){
                return previewDataDto;
            }
            if(lookupDetails.getStrUploadedFile() != null && !"".equals(lookupDetails.getStrUploadedFile())){
                previewDataDto = getCsvData(lookupDetails.getStrUploadedFile(), isDataRequired);
            }
            else if(lookupDetails.getSink() != null && lookupDetails.getSink().getInSinkId() > 0){
                previewDataDto = getCassandraData(lookupDetails, isDataRequired);
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "DashboardTransaction : in getSchemaAndContent function :",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : getSchemaAndContent function : "
                + Constants.END_STATUS);
        return previewDataDto;
    }

    /**
     * Method for getting csv data and csv schema details in json format
     *
     * @param fileDetails
     * @param isDataRequired
     * @return previewDataDto
     */
    private PreviewDataDto getCsvData(String fileDetails, boolean isDataRequired)
    {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : getCsvData function : "
                + Constants.START_STATUS);
        PreviewDataDto previewDataDto = null;
        try{
            File file = new File(fileDetails);
            // create FileInputStream object
            FileInputStream fin = new FileInputStream(file);
            byte fileContent[] = new byte[(int)file.length()];
            // Reads up to certain bytes of data from this input stream into an array of bytes.
            fin.read(fileContent);
            //create string from byte array
            String stringContent = new String(fileContent);
            Reader reader = new BufferedReader(new FileReader(fileDetails));
            List<ColumnMetadataDto> columnMetadataDtos = metadataUtil
                    .getColumnMetadataDtoList(stringContent, "delimited",
                            "," , true);
            if(columnMetadataDtos == null) {
                return previewDataDto;
            }
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withTrim());
            if(csvParser == null) {
                return previewDataDto;
            }
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            if(csvRecords != null) {
                previewDataDto = new PreviewDataDto();
                int header = 0;
                List<Object> schemaList = new ArrayList<Object>();
                List<Object> dataList = new ArrayList<Object>();
                String columnType = "";
                List<Object> tempSchemaList = null;
                for (CSVRecord csvRecord : csvRecords) {
                    Map<String, String> dataMap = new HashMap<String, String>();
                    if(!isDataRequired && header!= 0){
                        break;
                    }
                    for(int i=0; i < csvRecord.size();i++){
                        if(header == 0){
                            columnType = columnMetadataDtos.get(i).getColumnDataType();
                            dataMap.put(csvRecord.get(i), columnType);
                        }
                        else{
                            dataMap.put((String)tempSchemaList.get(i), csvRecord.get(i));
                        }
                    }
                    if(header == 0){
                        schemaList.add(dataMap);
                        tempSchemaList = Arrays.asList(dataMap.keySet().toArray());
                    }else{
                        dataList.add(dataMap);
                    }
                    header++;
                }
                if(isDataRequired) {
                    previewDataDto.setDataList(dataList);
                }
                previewDataDto.setSchemaList(schemaList);
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " DashboardTransaction : in getCsvData function :" , e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : getCsvData function : "
                + Constants.END_STATUS);
        return previewDataDto;
    }

    /**
     * Method for getting cassandra table data and schema details in json format
     *
     * @param lookupDetails
     * @param isDataRequired
     * @return previewDataDto
     */
    private PreviewDataDto getCassandraData(LookupDetails lookupDetails, boolean isDataRequired)
    {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : getCassandraData function : "
                + Constants.START_STATUS);
        PreviewDataDto previewDataDto = null;
        Cluster cluster = null;
        try{
            if(lookupDetails == null) {
                return previewDataDto;
            }
            String sinkObject = null;
            if(lookupDetails.getSink() != null){
                sinkObject = lookupDetails.getSink().getStrSinkConfigDetails();
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                CassandraSinkDto cassandraSinkDto =  mapper.readValue(sinkObject, CassandraSinkDto.class);
                if(cassandraSinkDto == null || cassandraSinkDto.getStrNodelst
                        () == null){
                    return previewDataDto;
                }
                final String nodes = cassandraSinkDto.getStrNodelst();
                String[] nodeArray = nodes.split(",");
                //Gets Cassandra Pooling Configurations
                PoolingOptions poolingOptions = getCassandraPoolingOptions();
                //Creating Cluster object
                cluster = Cluster.builder()
                        .addContactPoint(nodeArray[0])
                        .withPoolingOptions(poolingOptions).build();
                if(cluster == null) {
                    return previewDataDto;
                }
                Map<String, Object> schemaMap = getCassandraTableSchema(lookupDetails, cluster);
                if(schemaMap != null && !schemaMap.isEmpty()){
                    previewDataDto = new PreviewDataDto();
                    List<Object> schemaList = new ArrayList<Object>();
                    schemaList.add(schemaMap);
                    previewDataDto.setSchemaList(schemaList);
                }
                if(previewDataDto != null && isDataRequired){
                    //Creating Session object
                    com.datastax.driver.core.Session session = cluster.connect(lookupDetails.getStrKeySpaceName());
                    if(session == null) {
                        return previewDataDto;
                    }
                    //queries
                    String  query = "SELECT json * FROM " +lookupDetails.getStrKeySpaceName()+"."+lookupDetails.getStrtableName()+" limit 10";
                    //Getting the ResultSet
                    ResultSet resultSet = session.execute(query);
                    if(resultSet != null){
                        List<Object> dataList = new ArrayList<Object>();
                        Gson gson = new Gson();
                        for (Row row : resultSet) {
                            Map<String, Object> dataMap = mapper.readValue(row.getString(0), new TypeReference<Map<String, Object>>() {});
                            dataList.add(dataMap);
                        }
                        previewDataDto.setDataList(dataList);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " DashboardTransaction : in getCassandraData function :" , e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        } finally {
            if(cluster != null) {
                cluster.close();
            }
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : getCassandraData function : "
                + Constants.END_STATUS);
        return previewDataDto;
    }

    /**
     * Method for getting cassandra table schema details in json format
     *
     * @param lookupDetails
     * @param cluster
     * @return map
     */
    private Map<String, Object> getCassandraTableSchema(LookupDetails lookupDetails,
                                                        Cluster cluster) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : getCassandraTableSchema function : "
                + Constants.START_STATUS);
        Map<String, Object> schemaMap = null;
        try{
            KeyspaceMetadata keyspaceMetadata = cluster.getMetadata().getKeyspace(
                    lookupDetails.getStrKeySpaceName());
            if(keyspaceMetadata == null){
                return schemaMap;
            }
            TableMetadata tableMetadata = keyspaceMetadata.getTable(
                    lookupDetails.getStrtableName());
            if(tableMetadata == null){
                return schemaMap;
            }
            List<ColumnMetadata> columnMetadataList = tableMetadata.getColumns();
            if(columnMetadataList != null && !columnMetadataList.isEmpty()){
                schemaMap = new HashMap<String, Object>();
                for(ColumnMetadata columnMetadata : columnMetadataList){
                    schemaMap.put(columnMetadata.getName(), columnMetadata.getType().getName());
                }
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " DashboardTransaction : in getCassandraTableSchema function :" , e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : getCassandraTableSchema function : "
                + Constants.END_STATUS);
        return schemaMap;
    }

    /**
     * Gets cassandra pooling options.
     *
     * @return the cassandra pooling options
     */
    public PoolingOptions getCassandraPoolingOptions() {
        PoolingOptions  poolingOptions = new PoolingOptions();
        poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL,
                Integer.valueOf(PropReader.getPropertyValue(Constants
                                .CASSANDRA_CORE_CONNECTION_PER_HOST_LOCAL)));
        poolingOptions.setMaxConnectionsPerHost( HostDistance.LOCAL,
                Integer.valueOf(PropReader.getPropertyValue(Constants
                        .CASSANDRA_MAX_CONNECTION_PER_HOST_LOCAL)));
        poolingOptions.setCoreConnectionsPerHost(HostDistance.REMOTE,
                Integer.valueOf(PropReader.getPropertyValue(Constants
                        .CASSANDRA_CORE_CONNECTION_PER_HOST_REMOTE)));
        poolingOptions.setMaxConnectionsPerHost( HostDistance.REMOTE,
                Integer.valueOf(PropReader.getPropertyValue(Constants
                        .CASSANDRA_MAX_CONNECTION_PER_HOST_REMOTE)));
        poolingOptions.setMaxRequestsPerConnection(HostDistance.LOCAL,
                Integer.valueOf(PropReader.getPropertyValue(Constants
                        .CASSANDRA_MAX_REQUESTS_PER_CONNECTION_LOCAL)));
        poolingOptions.setMaxRequestsPerConnection(HostDistance.REMOTE,
                Integer.valueOf(PropReader.getPropertyValue(Constants
                        .CASSANDRA_MAX_REQUESTS_PER_CONNECTION_REMOTE)));
        return poolingOptions;
    }

    /**
     * Method for getting Lookup configurations ( id, name and type).
     *
     * @return listlookups list
     */
    @Transactional(readOnly = true)
    public List<IdNameDto> getLookupConfigurations(){
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : getLookupConfigurations function : "
                + Constants.START_STATUS);
        List<IdNameDto> listlookups = null;
        try{
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Lookup> lookupList = dashboardLookupDataAccess.listAll(
                    "from Lookup lookup where lookup.deleteStatus = "
                            + ":deleteStatus",parameterList);
            if(lookupList != null && !lookupList.isEmpty()){
                listlookups = new ArrayList<IdNameDto>();
                for(Lookup lookup: lookupList)
                {
                    IdNameDto lookupDto = new IdNameDto();
                    lookupDto.setId(lookup.getInLookupId());
                    lookupDto.setName(lookup.getStrLookupConfigName());
                    lookupDto.setType(lookup.getStrLookupType());
                    listlookups.add(lookupDto);
                }
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " DashboardTransaction : in getLookupConfigurations function :" , e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : getLookupConfigurations function : "
                + Constants.END_STATUS);
        return listlookups;
    }
}
