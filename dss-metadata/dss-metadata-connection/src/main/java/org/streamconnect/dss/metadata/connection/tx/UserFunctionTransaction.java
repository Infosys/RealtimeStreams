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

import com.google.gson.Gson;
import org.streamconnect.dss.dto.IdNameDto;
import org.streamconnect.dss.dto.UserFunctionDto;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.StreamTxnException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.metadata.connection.dao.DashboardDataAccess;
import org.streamconnect.dss.metadata.entities.User;
import org.streamconnect.dss.metadata.entities.UserFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.streamconnect.dss.util.CommonUtil;
import org.streamconnect.dss.util.Constants;
import org.streamconnect.dss.util.FileUtil;

import java.util.*;

/**
 * This is a transaction class for the Data Persistence Layer. Define the TX
 * policies based on the Transaction Type that define.
 *
 * @version 1.0
 */
@Service
public class UserFunctionTransaction {

    /** The dashboard user function data access. */
    @Autowired
    private DashboardDataAccess<UserFunction> dashboardUserFunctionDataAccess;

    /** The dashboard user data access. */
    @Autowired
    private DashboardDataAccess<User> dashboardUserDataAccess;

    /** The file util. */
    @Autowired
    private FileUtil fileUtil;

    /** The common util. */
    @Autowired
    private CommonUtil commonUtil;

    /** The logger. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(UserFunctionTransaction.class);

    /**
     * Method for saving user function details.
     *
     * @param userFunctionDto
     *            the user function dto
     * @return true, if successful
     */
    @Transactional
    public boolean saveOrUpdateUserFunction(final UserFunctionDto
                                                    userFunctionDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveOrUpdateUserFunction function : "
                + Constants.START_STATUS);
        boolean saveStatus = false;
        try {
            UserFunction userFunction = null;
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("inUserId", userFunctionDto.getInUserId());
            User user = dashboardUserDataAccess.findById("from User user"
                            + " where user.inUserId = :inUserId",
                    paramMap);
            if (userFunctionDto.getInUfId() == 0) {
                userFunction = new UserFunction();
                userFunction.setDeleteStatus(1);
                userFunction.setDateCreatedUf(new Date());
                userFunction.setCreatedBy(user);
            } else {
                userFunction = dashboardUserFunctionDataAccess.findById(new UserFunction(),
                        userFunctionDto.getInUfId());
                userFunction.setDateUpdatedUf(new Date());
                userFunction.setUpdatedBy(user);
            }
            if (userFunction != null) {
                String tempUdfLocation = "";
                userFunction.setInUfId(userFunctionDto.getInUfId());
                userFunction.setStrUfName(userFunctionDto.getStrUfName());
                userFunction.setStrRegisterMethod(userFunctionDto.getStrRegisterMethod());
                Gson gson = new Gson();
                String strCodeBase = gson
                        .toJson(userFunctionDto.getObjCodeBase());
                userFunction.setStrCodeBase(strCodeBase);
                userFunction.setStrUfConfigName(userFunctionDto
                        .getStrUfConfigName());
                userFunction.setStrUfDesc(userFunctionDto.getStrUfDesc());
                saveStatus = dashboardUserFunctionDataAccess.saveOrUpdate
                        (userFunction);

                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveOrUpdateUserFunction function : "
                        + "User function is : User function id = " +
                        userFunction.getInUfId()
                        + ", User function name = " + userFunction.getStrUfName
                        ());
                //saveStatus = dashboardUserFunctionDataAccess.saveOrUpdate
                // (userFunction);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : saveOrUpdateUserFunction function : "
                        + "Udf is : null");
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
                            + "DashboardTransaction : in saveOrUpdateUserFunction function " +
                            ": ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : saveOrUpdateUserFunction function : "
                + Constants.END_STATUS);
        return saveStatus;
    }

    /**
     * Check user function exist or not.
     *
     * @param inUfId  the in source id
     * @param ufName  the source name
     * @param userName the user name
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean checkUserFunctionExistOrNot(int inUfId, String ufName,
                                               String userName) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " DashboardTransaction : checkUserFunctionExistOrNot function : "
                + Constants.START_STATUS);
        boolean status = false;
        try {
            if (ufName != null && !"".equals(ufName.trim())
                    && userName != null && !"".equals(userName.trim())) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deleteStatus", 1);
                paramMap.put("ufName", ufName);
                //paramMap.put("userName",userName);
                UserFunction userFunction = dashboardUserFunctionDataAccess.findById(
                        "from UserFunction uf where "
                                + "uf.strUfName = :ufName " +
                                "and "
                                + "uf.deleteStatus = :deleteStatus",
                        paramMap);
                if (userFunction != null && inUfId != userFunction.getInUfId()) {
                    LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                            .getMessage()
                            + " DashboardTransaction : checkUserFunctionExistOrNot " +
                            "function:"
                            + " User function is : User function id = " +
                            userFunction.getInUfId()
                            + ", User function name = " + userFunction.getStrUfName());
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
                            + "DashboardTransaction : in checkUserFunctionExistOrNot "
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
     * Method for getting Userfunction list.
     *
     * @return listUdfDto
     */
    @Transactional(readOnly = true)
    public List<IdNameDto> getUserFunctionList() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getUserFunctionList function : "
                + Constants.START_STATUS);
        List<IdNameDto> listUdfDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<UserFunction> listUserFunction = dashboardUserFunctionDataAccess
                    .listAll(
                            "from" + " UserFunction uf where uf.deleteStatus = " +
                                    ":deleteStatus order "
                                    + "by uf.dateUpdatedUf desc",
                            parameterList);
            if (listUserFunction != null) {
                listUdfDto = new ArrayList<IdNameDto>();
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getUserFunctionList function :"
                        + " User Function List = " + listUserFunction);
                for (UserFunction userFunction : listUserFunction) {
                    IdNameDto ufDto = new IdNameDto();
                    ufDto.setId(userFunction.getInUfId());
                    ufDto.setName(userFunction.getStrUfName());
                    listUdfDto.add(ufDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getUserFunctionList function :"
                        + " User Function Dto List = " + listUdfDto);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getUserFunctionList function :"
                        + " User Function Dto List Size = 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " in "
                            + "DashboardTransaction : getUserFunctionList function " +
                            ": ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getUserFunctionList function : "
                + Constants.END_STATUS);
        return listUdfDto;
    }

    /**
     * Method for getting Userfunction list.
     *
     * @return listUdfDto
     */
    @Transactional(readOnly = true)
    public List<UserFunctionDto> getUserFunctions() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getUserFunctionList function : "
                + Constants.START_STATUS);
        List<UserFunctionDto> userFunctionDtoList = null;
        List<UserFunction> listUserFunction = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            listUserFunction = dashboardUserFunctionDataAccess
                    .listAll(
                            "from" + " UserFunction uf where uf.deleteStatus = " +
                                    ":deleteStatus order "
                                    + "by uf.dateUpdatedUf desc",
                            parameterList);

            if (listUserFunction != null) {
                userFunctionDtoList = new ArrayList<UserFunctionDto>();
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getUserFunctionList function :"
                        + " User Function List = " + listUserFunction);
                for (UserFunction userFunction : listUserFunction) {
                    UserFunctionDto ufDto = new UserFunctionDto();
                    ufDto.setInUfId(userFunction.getInUfId());
                    ufDto.setStrUfName(userFunction.getStrUfName());
                    ufDto.setStrUfConfigName(
                            userFunction.getStrUfConfigName());
                    ufDto.setObjCodeBase(userFunction.getStrCodeBase());
                    ufDto.setStrRegisterMethod(userFunction.getStrRegisterMethod());
                    ufDto.setStrUfDesc(userFunction.getStrUfDesc());

                    userFunctionDtoList.add(ufDto);
                }
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getUserFunctionList function :"
                        + " User Function Dto List = " + userFunctionDtoList);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getUserFunctionList function :"
                        + " User Function Dto List Size = 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " in "
                            + "DashboardTransaction : getUserFunctionList function " +
                            ": ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getUserFunctionList function : "
                + Constants.END_STATUS);
        return userFunctionDtoList;
    }


    /**
     * Method for getting Userfunction details by passing userfunction id.
     *
     * @param ufId
     *            the uf id
     * @return the UDF data
     */
    @Transactional(readOnly = true)
    public UserFunctionDto getUserFunctionData(final int ufId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getUserFunctionData function : "
                + Constants.START_STATUS);
        UserFunctionDto userFunctionDto = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("inUfId", ufId);
            parameterList.put("deleteStatus", 1);
            UserFunction userFunction = dashboardUserFunctionDataAccess.findById("from UserFunction uf "
                    + "where uf.inUfId = :inUfId and uf.deleteStatus = "
                    + ":deleteStatus", parameterList);
            if (userFunction != null) {
                userFunctionDto = new UserFunctionDto();
                userFunctionDto.setInUfId(userFunction.getInUfId());
                userFunctionDto.setStrUfName(userFunction.getStrUfName());
                userFunctionDto.setStrRegisterMethod(userFunction.getStrRegisterMethod());
                userFunctionDto.setObjCodeBase(userFunction.getStrCodeBase());
                userFunctionDto.setStrUfConfigName(userFunction
                        .getStrUfConfigName());
                userFunctionDto.setStrUfDesc(userFunction.getStrUfDesc());
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getUserFunctionData function :"
                        + " Userfunction is : Userfunction id = " + userFunction.getInUfId()
                        + ", Userfunction name = " + userFunction.getStrUfName());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : getUserFunctionData function :"
                        + " Userfunction is : null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " in "
                            + "DashboardTransaction : getUserFunctionData function " +
                            ": ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : getUserFunctionData function : "
                + Constants.END_STATUS);
        return userFunctionDto;
    }

    /**
     * Method for deleting a UDF by passing uf id.
     *
     * @param ufId
     *            the user function id
     * @return boolean
     */
    @Transactional
    public boolean deleteUserFunction(final int ufId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteUserFunction function : "
                + Constants.START_STATUS);
        boolean deleteStatus = false;
        try {
            UserFunction userFunction = dashboardUserFunctionDataAccess.findById(new UserFunction(),
                    ufId);
            if (userFunction != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteUserFunction function : "
                        + "Deleted Userfunction is : Userfunction id = "
                        + userFunction.getInUfId() + ", Userfunction name = "
                        + userFunction.getStrUfName());
                userFunction.setDeleteStatus(0);
                deleteStatus = dashboardUserFunctionDataAccess.saveOrUpdate(userFunction);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " DashboardTransaction : deleteUserFunction function : "
                        + "Userfunction is : null");
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
                            + "DashboardTransaction : in deleteUserFunction " +
                            "function : ",
                    e);
            throw new StreamTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "DashboardTransaction : deleteUserFunction function : "
                + Constants.END_STATUS);
        return deleteStatus;
    }


}
