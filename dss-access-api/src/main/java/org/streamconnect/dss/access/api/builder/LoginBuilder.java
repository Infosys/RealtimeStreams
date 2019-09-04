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
package org.streamconnect.dss.access.api.builder;

import org.streamconnect.dss.dto.LoginResponse;
import org.streamconnect.dss.dto.RoleDto;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.AccessLayerServiceException;
import org.streamconnect.dss.exception.StreamTxnException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.access.api.response.LoginResponseVO;
import org.streamconnect.dss.access.api.response.Role;
import org.streamconnect.dss.access.api.response.UserSession;
import org.streamconnect.dss.access.service.ILoginService;
import org.streamconnect.dss.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains User login related APIs ( login, logout and
 * authenticate).
 *
 * @version 1.0
 *
 */
@Component
public final class LoginBuilder extends BaseResponseBuilder {

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(LoginBuilder.class);

    /** The login service. */
    @Autowired
    private ILoginService loginService;

    /**
     * Method for user login.
     *
     * @param userName
     *            the user name
     * @param password
     *            the password
     * @param token
     *            the token
     * @param uuId
     *            the uu id
     * @return the string
     */
    public String logIn(final String userName, final String password,
                        final String token, final String uuId) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + "LoginBuilder : logIn function : " + Constants.START_STATUS);
        try {
            LoginResponse loginResponse = loginService.logIn(userName, password,
                    token, uuId);
            LoginResponseVO loginResponseVO = null;
            if (loginResponse != null) {
                loginResponseVO = new LoginResponseVO();
                loginResponseVO.setStatusCode(loginResponse.getStatusCode());
                loginResponseVO
                        .setStatusMessage(loginResponse.getStatusMessage());
                if (loginResponse.getUserSession() != null) {
                    UserSession userSession = new UserSession();
                    userSession.setUserName(
                            loginResponse.getUserSession().getUserName());
                    userSession.setToken(
                            loginResponse.getUserSession().getToken());
                    if (loginResponse.getUserSession().getRoles() != null
                            && !loginResponse.getUserSession().getRoles()
                            .isEmpty()) {
                        List<Role> roleList = new ArrayList<Role>();
                        for (RoleDto roleDto : loginResponse.getUserSession()
                                .getRoles()) {
                            Role role = new Role();
                            role.setInRoleId(roleDto.getInRoleId());
                            role.setStrRoleName(roleDto.getStrRoleName());
                            roleList.add(role);
                        }
                        userSession.setRoles(roleList);
                        userSession.setDefaultRole(loginResponse
                                .getUserSession().getDefaultRole());
                    }
                    loginResponseVO.setUserSession(userSession);
                }
            }
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    +  "LoginBuilder : logIn function : " + Constants
                    .END_STATUS);
            return toStringJsonify(loginResponseVO);
        } catch (StreamTxnException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " LoginBuilder : in"
                    + " logIn function : ", e);
            return getContentBuildingError(Constants.LOGIN_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (AccessLayerServiceException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " LoginBuilder : in"
                    + " logIn function : ", e);
            return getContentBuildingError(Constants.LOGIN_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " LoginBuilder : in"
                    + " logIn function : ", e);
            return getContentBuildingError(Constants.LOGIN_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for user logout.
     *
     * @param token
     *            the token
     * @return the string
     */
    public String logout(final String token) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + "LoginBuilder : logout function : " + Constants
                .START_STATUS);
        try {
            LoginResponse loginResponse = loginService.logout(token);
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    + "LoginBuilder : logout function : " + Constants
                    .END_STATUS);
            return toStringJsonify(loginResponse);
        } catch (AccessLayerServiceException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " LoginBuilder : in"
                    + " logIn function : ", e);
            return getContentBuildingError(Constants.LOGOUT_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " LoginBuilder : in"
                    + " logout function : ", e);
            return getContentBuildingError(Constants.LOGOUT_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for checking whether user in session or not.
     *
     * @param token
     *            the token
     * @return String
     */
    public String authenticate(final String token) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + "LoginBuilder : authenticate function : " + Constants
                .START_STATUS);
        try {
            LoginResponse loginResponse = loginService.authenticate(token);
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    + "LoginBuilder : authenticate function : " + Constants
                    .END_STATUS);
            return toStringJsonify(loginResponse);
        } catch (AccessLayerServiceException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " LoginBuilder : "
                    + "in logIn function : ", e);
            return getContentBuildingError(
                    Constants.AUTHENTICATE_ERROR + "User Session" + ". "
                            + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " LoginBuilder : "
                    + "in authenticate function : ", e);
            return getContentBuildingError(
                    Constants.AUTHENTICATE_ERROR + "User  Session",
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode().toString());
        }
    }
}
