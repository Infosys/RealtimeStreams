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

import org.streamconnect.dss.access.cache.impl.CacheService;
import org.streamconnect.dss.api.service.IUserFunctionService;
import org.streamconnect.dss.api.ui.response.IdNameVO;
import org.streamconnect.dss.api.ui.response.UserFunction;
import org.streamconnect.dss.dto.IdNameDto;
import org.streamconnect.dss.dto.UserFunctionDto;
import org.streamconnect.dss.dto.UserSessionDto;
import org.streamconnect.dss.engine.InterpreterFactory;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.StreamTxnException;
import org.streamconnect.dss.executor.factory.ExecutorFactory;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.CommonUtil;
import org.streamconnect.dss.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The User Function Builder
 */
@Component
public class UserFunctionBuilder extends BaseResponseBuilder{

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(DashboardBuilder.class);

    /** The user function service. */
    @Autowired
    private IUserFunctionService userFunctionService;

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
     * Method for saving User function details.
     *
     * @param userFunction
     *            the user function
     * @param token the token
     *
     * @return String
     */
    public String saveOrUpdateUserFunction(final UserFunction userFunction,
                                           final String token){
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : saveOrUpdateUserFunction function : "
                + Constants.START_STATUS);
        try {
            final UserSessionDto userSession = cacheService.getUserMap().get(token);
            boolean status = checkUserFunctionExistOrNot(userFunction.getInUfId(),
                    userFunction
                    .getStrUfName(), userSession.getUserName());
            if(!status) {
                UserFunctionDto userFunctionDto = new UserFunctionDto();
                userFunctionDto.setInUfId(userFunction.getInUfId());
                userFunctionDto.setStrUfName(userFunction.getStrUfName());
                userFunctionDto.setStrRegisterMethod(userFunction.getStrRegisterMethod());
                userFunctionDto.setStrUfConfigName(userFunction.getStrUfConfigName());
                userFunctionDto.setStrUfDesc(userFunction.getStrUfDesc());
                userFunctionDto.setObjCodeBase(userFunction.getObjCodeBase());
                userFunctionDto.setInUserId(userSession.getInUserId());
                status = userFunctionService.saveOrUpdateUserFunction(userFunctionDto);
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " DashboardBuilder : saveOrUpdateUserFunction function : "
                        + Constants.END_STATUS);
                if (status) {
                    return getContentBuildingSuccessMessage(
                            Constants.USER_FUNCTION_SAVE_SUCCESS);
                } else {
                    return getContentBuildingError(Constants.USER_FUNCTION_SAVE_ERROR,
                            ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                    .toString());
                }
            } else {
                return getContentBuildingError(Constants.USER_FUNCTION_SAVE_ERROR
                                + ErrorMessageEnum
                                .ENTITY_NAME_EXIST_ERROR.getMessage(),
                        ErrorMessageEnum.ENTITY_NAME_EXIST_ERROR.getCode()
                                .toString());
            }
        }
        catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : saveOrUpdateUserFunction function : ",
                    e);
            return getContentBuildingError(Constants.USER_FUNCTION_SAVE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Check user function exist or not.
     *
     * @param inUfId the in source id
     * @param ufName the source name
     * @param userName the user name
     * @return true, if successful
     */
    public boolean checkUserFunctionExistOrNot(int inUfId, String ufName,
                                               String userName) {
        boolean status = false;
        try {
            if (ufName != null && !"".equals(ufName.trim())
                    && userName != null && !"".equals(userName.trim())) {
                status = userFunctionService.checkUserFunctionExistOrNot(inUfId,
                        ufName.trim(), userName.trim());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : checkUserFunctionExistOrNot function : ",
                    e);
            getContentBuildingError(Constants.USER_FUNCTION_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : checkUserFunctionExistOrNot function : ",
                    e);
            getContentBuildingError(Constants.USER_FUNCTION_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        return status;
    }

    /**
     * Method for getting user function list.
     *
     * @return String
     */
    public String getUserFunctionList() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getUserFunctionList function : "
                + Constants.START_STATUS);
        try {
            List<IdNameVO> listUdf = new ArrayList<IdNameVO>();
            List<IdNameDto> udfDtoList = userFunctionService.getUserFunctionList();
            if (udfDtoList != null) {
                for (IdNameDto udfDto : udfDtoList) {
                    IdNameVO idNameUdf = new IdNameVO();
                    idNameUdf.setId(udfDto.getId());
                    idNameUdf.setName(udfDto.getName());
                    listUdf.add(idNameUdf);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : getUserFunctionList function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(listUdf);
        } catch (StreamTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getUserFunctionList function : ",
                    e);
            return getContentBuildingError(Constants.USER_FUNCTION_LIST_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getUserFunctionList function : ",
                    e);
            return getContentBuildingError(Constants.USER_FUNCTION_LIST_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting user function details by passing user function id.
     *
     * @param inUfId
     *            the udf id
     * @return the UDF data
     */
    public String getUserFunctionData(final int inUfId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : getUdfData function : "
                + Constants.START_STATUS);
        try {
            UserFunction userFunction = null;
            UserFunctionDto userFunctionDto = userFunctionService.getUserFunctionData(inUfId);
            if (userFunctionDto != null) {
                userFunction = new UserFunction();
                userFunction.setInUfId(userFunctionDto.getInUfId());
                userFunction.setStrUfName(userFunctionDto.getStrUfName());
                userFunction.setStrRegisterMethod(userFunctionDto.getStrRegisterMethod());
                userFunction.setObjCodeBase(userFunctionDto.getObjCodeBase());
                userFunction.setStrUfConfigName(userFunctionDto.getStrUfConfigName());
                userFunction.setStrUfDesc(userFunctionDto.getStrUfDesc());
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " DashboardBuilder : getUserFunctionData function : "
                        + Constants.END_STATUS);
                return toStringJsonify(userFunction);
            } else {
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " DashboardBuilder : getUserFunctionData function : "
                        + Constants.END_STATUS);
                return getContentBuildingError(Constants.USER_FUNCTION_FETCH_ERROR,
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
                            + " in DashboardBuilder : getUserFunctionData function : ",
                    e);
            return getContentBuildingError(Constants.USER_FUNCTION_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : getUserFunctionData function : ",
                    e);
            return getContentBuildingError(Constants.USER_FUNCTION_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for deleting a User Function by passing user function id.
     *
     * @param udfId
     *            the udf id
     * @return String
     */
    public String deleteUserFunction(final int udfId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardBuilder : deleteUdf function : "
                + Constants.START_STATUS);
        try {
            boolean status = userFunctionService.deleteUserFunction(udfId);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " DashboardBuilder : deleteUdf function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(
                        Constants.USER_FUNCTION_DELETE_SUCCESS);
            } else {
                return getContentBuildingError(Constants.USER_FUNCTION_DELETE_ERROR,
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
                            + " in DashboardBuilder : deleteUdf function : ",
                    e);
            return getContentBuildingError(Constants.USER_FUNCTION_DELETE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in DashboardBuilder : deleteUdf function : ",
                    e);
            return getContentBuildingError(Constants.SOURCE_DELETE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

}
