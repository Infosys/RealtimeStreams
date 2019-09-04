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
package org.streamconnect.dss.access.service.impl;

import org.streamconnect.dss.access.cache.impl.CacheService;
import org.streamconnect.dss.access.service.ILoginService;
import org.streamconnect.dss.dto.LoginResponse;
import org.streamconnect.dss.dto.UserDto;
import org.streamconnect.dss.dto.UserSessionDto;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.AccessLayerServiceException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.metadata.connection.tx.DashboardTransaction;
import org.streamconnect.dss.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for Login functionalities: 1. logIn 2.logOut
 * 3.Authenticate
 *
 * @version 1.0
 */
@Service
public class LoginServiceImpl implements ILoginService {

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(LoginServiceImpl.class);

    /** The cache service. */
    @Autowired
    private CacheService cacheService;

    /** The dashboard transaction. */
    @Autowired
    private DashboardTransaction dashboardTransaction;

    /**
     * Method to SignIn an User 1. Checks if token has a corresponding entry in
     * Cache. If so User is already logged In 2. If User not already loggedIn,
     * add entry to Cache
     *
     * @param userName the user name
     * @param password the password
     * @param token the token
     * @param uuId the uu id
     * @return LoginResponse
     */
    public LoginResponse logIn(final String userName, final String password,
                               final String token,
                               final String uuId) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " LoginServiceImpl : logIn function : " + Constants
                .START_STATUS);
        LoginResponse loginResponse = null;
        try {
            UserDto userDto = dashboardTransaction.logIn(userName, password);
            if (userDto == null) {
                return new LoginResponse(
                        ErrorMessageEnum.AUTH_FAILED_MESSAGE.getCode()
                                .toString(),
                        ErrorMessageEnum.AUTH_FAILED_MESSAGE.getMessage());
            }
            // session or not
            if (token != null && !"".equals(token)) {
                UserSessionDto userSession = cacheService.getUserMap()
                        .get(token);
                if (userSession == null) {
                    userSession = new UserSessionDto();
                    userSession.setInUserId(userDto.getInUserId());
                    userSession.setUserName(userDto.getStrUserName());
                    userSession.setToken(uuId);
                    if (userDto.getRoles() != null) {
                        userSession.setRoles(userDto.getRoles());
                        userSession.setDefaultRole(userDto.getDefaultRole());
                    }
                    int loginDetailsId = dashboardTransaction
                            .addLoginDetails(userDto);
                    userSession.setLoginDetailsId(loginDetailsId);
                    cacheService.addToCache(uuId, userSession);
                    loginResponse = new LoginResponse(
                            ErrorMessageEnum.USER_AUTH_SUCCESSFULL.getCode()
                                    .toString(),
                            ErrorMessageEnum.USER_AUTH_SUCCESSFULL.getMessage(),
                            userSession);
                } else {
                    loginResponse = new LoginResponse(
                            ErrorMessageEnum.USER_ALREADY_IN_SESSION.getCode()
                                    .toString(),
                            ErrorMessageEnum.USER_ALREADY_IN_SESSION
                                    .getMessage(),
                            userSession);
                }
            } else {
                UserSessionDto userSession = new UserSessionDto();
                userSession.setInUserId(userDto.getInUserId());
                userSession.setUserName(userDto.getStrUserName());
                userSession.setToken(uuId);
                if (userDto.getRoles() != null) {
                    userSession.setRoles(userDto.getRoles());
                    userSession.setDefaultRole(userDto.getDefaultRole());
                }
                int loginDetailsId = dashboardTransaction
                        .addLoginDetails(userDto);
                userSession.setLoginDetailsId(loginDetailsId);
                cacheService.addToCache(uuId, userSession);
                loginResponse = new LoginResponse(
                        ErrorMessageEnum.USER_AUTH_SUCCESSFULL.getCode()
                                .toString(),
                        ErrorMessageEnum.USER_AUTH_SUCCESSFULL.getMessage(),
                        userSession);
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " LoginServiceImpl "
                    + ": in logIn function : ", e);
            throw new AccessLayerServiceException(ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " : " + e
                    .getMessage(), ErrorMessageEnum.ACCESS_LAYER_EXCEPTION
                    .getCode().toString());
        }
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " LoginServiceImpl : logIn function : " + Constants
                .END_STATUS);
        return loginResponse;
    }

    /**
     * Method to SignOut a User from session 1. Checks if token has a
     * corresponding entry in Cache. If so User is already logged In, SignOut
     * the user, remove from Cache 2. If User not already loggedIn, return
     * appropriate message
     *
     * @param token the token
     * @return LoginResponse
     */
    public LoginResponse logout(final String token) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " LoginServiceImpl : logout function : " + Constants
                .START_STATUS);
        LoginResponse loginResponse = null;
        try {
            UserSessionDto userSession = cacheService.getCache().get(token);
            if (userSession == null) {
                loginResponse = new LoginResponse(
                        ErrorMessageEnum.USER_NOT_IN_SESSION.getCode()
                                .toString(),
                        ErrorMessageEnum.USER_NOT_IN_SESSION.getMessage());
            } else {
                cacheService.deleteFromCache(token);
                dashboardTransaction
                        .updateLogoutDetails(userSession.getLoginDetailsId());
                loginResponse = new LoginResponse(
                        ErrorMessageEnum.USER_LOG_OUT_SUCCESS.getCode()
                                .toString(),
                        ErrorMessageEnum.USER_LOG_OUT_SUCCESS.getMessage());
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " LoginServiceImpl "
                    + ": in logout function : ", e);
            throw new AccessLayerServiceException(ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " : " + e
                    .getMessage(), ErrorMessageEnum.ACCESS_LAYER_EXCEPTION
                    .getCode().toString());
        }
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " LoginServiceImpl : logout function : " + Constants
                .END_STATUS);
        return loginResponse;
    }

    /**
     * Authenticate User - Check if token has an entry in Cache. If so
     * authenticate, else fail authentication
     *
     * @param token the token
     * @return - LoginResponse If User is in session - 3008 - 'User Not in
     *         Session' else - 3009 - 'User Already in Session'
     */
    public LoginResponse authenticate(final String token) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " LoginServiceImpl : authenticate function : " + Constants
                .START_STATUS);
        try {
            if (token == null || token.equalsIgnoreCase("undefined")) {
                return new LoginResponse(
                        ErrorMessageEnum.USER_NOT_IN_SESSION.getCode()
                                .toString(),
                        ErrorMessageEnum.USER_NOT_IN_SESSION.getMessage());
            }
            // Check if User is in cache
            UserSessionDto userSession = cacheService.getCache().get(token);
            // User is already signed In
            if (userSession != null) {
                LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " LoginServiceImpl : authenticate function : "
                        + Constants.END_STATUS);
                return new LoginResponse(
                        ErrorMessageEnum.USER_ALREADY_IN_SESSION.getCode()
                                .toString(),
                        ErrorMessageEnum.USER_ALREADY_IN_SESSION.getMessage());
            } else {
                LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " LoginServiceImpl : authenticate function : "
                        + Constants.END_STATUS);
                return new LoginResponse(
                        ErrorMessageEnum.USER_NOT_IN_SESSION.getCode()
                                .toString(),
                        ErrorMessageEnum.USER_NOT_IN_SESSION.getMessage());
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " LoginServiceImpl"
                    + " : in authenticate function : ", e);
            throw new AccessLayerServiceException(ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " : " + e
                    .getMessage(), ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode().toString());
        }
    }
}
