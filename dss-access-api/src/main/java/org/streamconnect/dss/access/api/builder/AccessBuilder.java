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

import org.streamconnect.dss.access.api.response.AccessLevel;
import org.streamconnect.dss.access.api.response.CategoryKpiVisualize;
import org.streamconnect.dss.access.api.response.Dashboard;
import org.streamconnect.dss.access.api.response.Feature;
import org.streamconnect.dss.access.api.response.FeatureAccess;
import org.streamconnect.dss.access.api.response.IdNameVO;
import org.streamconnect.dss.access.api.response.Kpi;
import org.streamconnect.dss.access.api.response.Portal;
import org.streamconnect.dss.access.api.response.PortalAccess;
import org.streamconnect.dss.access.api.response.Role;
import org.streamconnect.dss.access.api.response.User;
import org.streamconnect.dss.access.api.response.Visualize;
import org.streamconnect.dss.access.cache.impl.CacheService;
import org.streamconnect.dss.access.service.IAccessService;
import org.streamconnect.dss.dto.*;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.StreamTxnException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.CommonUtil;
import org.streamconnect.dss.util.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class contains User Role Access Level APIs (add, map, edit users, roles
 * and access levels).
 *
 * @version 1.0
 */
@Component
public class AccessBuilder extends BaseResponseBuilder {

    /**
     * The Constant LOGGER.
     */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(AccessBuilder.class);

    /**
     * The access service.
     */
    @Autowired
    private IAccessService accessService;

    /** The cache service. */
    @Autowired
    private CacheService cacheService;

    /** The common util. */
    @Autowired
    private CommonUtil commonUtil;

    /**
     * Method for getting the users.
     *
     * @return String
     */
    public String getUserList() {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                +  "AccessBuilder : getUserList function : "
                + Constants.START_STATUS);
        List<IdNameVO> users = null;
        try {
            List<IdNameDto> userDtoList = accessService.getUserList();
            if (userDtoList != null) {
                users = new ArrayList<IdNameVO>();
                for (IdNameDto userDto : userDtoList) {
                    IdNameVO user = new IdNameVO();
                    user.setId(userDto.getId());
                    user.setName(userDto.getName());
                    users.add(user);
                }
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : getUserList function :"
                        + " User List Size is " + users.size());
            } else {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : getUserList function :"
                        + " User List Size is 0");
            }
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    +  "AccessBuilder : getUserList function : "
                    + Constants.END_STATUS);
            return toStringJsonify(users);
        } catch (StreamTxnException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in getUserList function : ", e);
            return getContentBuildingError(Constants.USER_LIST_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in getUserList function : ", e);
            return getContentBuildingError(Constants.USER_LIST_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode().toString());
        }
    }

    /**
     * Method for saving Role against users.
     *
     * @param role the role
     * @return String
     */
    public String saveRole(final Role role, final String token) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                +  "AccessBuilder : saveRole function : "
                + Constants.START_STATUS);
        try {
            final UserSessionDto userSession = cacheService.getUserMap().get(token);
            boolean status = checkRoleExistOrNot(role.getInRoleId(), role
                    .getStrRoleName(), userSession.getUserName());
            if(!status) {
                RoleDto roleDto = new RoleDto();
                roleDto.setInRoleId(role.getInRoleId());
                roleDto.setStrRoleName(role.getStrRoleName());
                roleDto.setStrRoleDesc(role.getStrRoleDesc());
                boolean saveStatus = accessService.saveRole(roleDto);
                LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + "AccessBuilder : saveRole function : "
                        + Constants.END_STATUS);
                if (saveStatus) {
                    return getContentBuildingSuccessMessage(Constants
                            .ROLE_SAVE_SUCCESS);
                } else {
                    return getContentBuildingError(Constants.ROLE_SAVE_ERROR,
                            ErrorMessageEnum.ACCESS_LAYER_EXCEPTION
                                    .getCode().toString());
                }
            } else {
                return getContentBuildingError(Constants.ROLE_SAVE_ERROR
                                + ErrorMessageEnum
                                .ENTITY_NAME_EXIST_ERROR.getMessage(),
                        ErrorMessageEnum.ENTITY_NAME_EXIST_ERROR.getCode()
                                .toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in saveRole function : ", e);
            return getContentBuildingError(Constants.ROLE_SAVE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in saveRole function : ", e);
            return getContentBuildingError(Constants.ROLE_SAVE_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode().toString());
        }
    }

    /**
     * Delete role.
     *
     * @param inRoleId the in role id
     * @return the string
     */
    public String deleteRole(final int inRoleId) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                +  "AccessBuilder : deleteRole function : "
                + Constants.START_STATUS);
        try {
            boolean status = accessService.deleteRole(inRoleId);
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    +  "AccessBuilder : deleteRole function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(Constants
                        .ROLE_DELETE_SUCCESS);
            } else {
                return getContentBuildingError(Constants.ROLE_DELETE_ERROR,
                        ErrorMessageEnum.ACCESS_LAYER_EXCEPTION
                        .getCode().toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in deleteRole function : ", e);
            return getContentBuildingError(Constants.ROLE_DELETE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in deleteRole function : ", e);
            return getContentBuildingError(Constants.ROLE_DELETE_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION
                    .getCode().toString());
        }
    }


    /**
     * Method for mapping a user to role.
     *
     * @param user
     * @return String
     */
    public String mapUserRole(final User user, final String token) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessBuilder : mapUserRole function : "
                + Constants.START_STATUS);
        try {
            final UserSessionDto userSession = cacheService.getUserMap().get(token);
            String createdUser = userSession.getUserName();
            UserDto userDto = new UserDto();
            userDto.setInUserId(user.getInUserId());
            userDto.setStrUserName(user.getStrUserName());
            userDto.setStrMappedUser(createdUser);
            List<RoleDto> roleDtos = null;
            if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                roleDtos = new ArrayList<RoleDto>();
                for (Role role : user.getRoles()) {
                    RoleDto roleDto = new RoleDto();
                    roleDto.setInRoleId(role.getInRoleId());
                    roleDto.setStrRoleName(role.getStrRoleName());
                    roleDto.setStrRoleDesc(role.getStrRoleDesc());
                    roleDtos.add(roleDto);
                }
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : mapUserRole function :"
                        + " Role List Size is " + roleDtos.size());
            } else {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : mapUserRole function :"
                        + " Role List Size is 0");
            }
            userDto.setDefaultRole(user.getDefaultRole());
            userDto.setRoles(roleDtos);
            boolean status = accessService.mapUserRole(userDto);
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage() + ". "
                    + "AccessBuilder : mapUserRole function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(Constants
                        .ROLE_MAP_SUCCESS);
            } else {
                return getContentBuildingError(Constants.ROLE_MAP_ERROR,
                        ErrorMessageEnum.ACCESS_LAYER_EXCEPTION
                        .getCode().toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in deleteRole function : ", e);
            return getContentBuildingError(Constants.ROLE_MAP_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in deleteRole function : ", e);
            return getContentBuildingError(Constants.ROLE_MAP_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for removing the user role mapping.
     *
     * @param inUserId the in user id
     * @return String
     */
    public String removeMappedUserRole(final int inUserId) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                +  " AccessBuilder : removeMappedUserRole function : "
                +  Constants.START_STATUS);
        try {
            boolean status = accessService.removeMappedUserRole(inUserId);
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    + " AccessBuilder : removeMappedUserRole "
                    + "function : " + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(Constants
                        .ROLE_MAP_REMOVE_SUCCESS);
            } else {
                return getContentBuildingError(Constants
                        .ROLE_MAP_REMOVE_ERROR, ErrorMessageEnum
                        .ACCESS_LAYER_EXCEPTION.getCode().toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in removeMappedUserRole function : ", e);
            return getContentBuildingError(Constants.ROLE_MAP_REMOVE_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in removeMappedUserRole function : ", e);
            return getContentBuildingError(Constants.ROLE_MAP_REMOVE_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION
                    .getCode().toString());
        }
    }

    /**
     * Method for getting the roles.
     *
     * @return String
     */
    public String getRoleList() {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                +  " AccessBuilder : getRoleList function : "
                + Constants.START_STATUS);
        try {
            List<RoleDto> roleDtoList = accessService.getRoleList();
            List<Role> roles = null;
            if (roleDtoList != null && !roleDtoList.isEmpty()) {
                roles = new ArrayList<Role>();
                for (RoleDto roleDto : roleDtoList) {
                    Role role = new Role();
                    role.setInRoleId(roleDto.getInRoleId());
                    role.setStrRoleName(roleDto.getStrRoleName());
                    role.setStrRoleDesc(roleDto.getStrRoleDesc());
                    String createdDate = commonUtil.dateToString(roleDto
                            .getDateCreated());
                    role.setStrDateCreated(createdDate);
                    role.setStrCreatedUser(roleDto.getStrCreatedUser());
                    if (roleDto.getAccessLevelDto() != null) {
                        AccessLevel accessLevel = new AccessLevel();
                        accessLevel.setInAccessId(roleDto.getAccessLevelDto()
                                .getInAccessId());
                        role.setAccessLevel(accessLevel);
                    }
                    roles.add(role);
                }
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : getRoleList function :"
                        + " Role List Size is " + roles.size());
            } else {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : getRoleList function :"
                        + " Role List Size is 0");
            }
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    +  " AccessBuilder : getRoleList function : "
                    + Constants.END_STATUS);
            return toStringJsonify(roles);
        } catch (StreamTxnException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in getRoleList function : ", e);
            return getContentBuildingError(Constants.ROLE_LIST_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in getRoleList function : ", e);
            return getContentBuildingError(Constants.ROLE_LIST_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode().toString());
        }
    }

    /**
     * Method for getting roles corresponding to the user.
     *
     * @param username the username
     * @return Response
     */
    public String getUserRoleDetails(final String username) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessBuilder : getUserRoleDetails function : "
                + Constants.START_STATUS);
        try {
            UserDto userDto = accessService.getUserRoleDetails(username);
            User user = null;
            List<Role> roles = null;
            if (userDto != null) {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : getUserRoleDetails "
                        + "function : User is " + userDto.getStrUserName());
                user = new User();
                if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
                    LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                            + " AccessBuilder : getUserRoleDetails"
                            + " function : Role List Size is " + userDto
                            .getRoles().size());
                    roles = new ArrayList<Role>();
                    for (RoleDto roleDto : userDto.getRoles()) {
                        Role role = new Role();
                        role.setInRoleId(roleDto.getInRoleId());
                        role.setStrRoleName(roleDto.getStrRoleName());
                        role.setStrRoleDesc(roleDto.getStrRoleDesc());
                        role.setActive(roleDto.isActive());
                        String createdDate = commonUtil.dateToString(roleDto
                                .getDateCreated());
                        role.setStrDateCreated(createdDate);
                        role.setStrCreatedUser(roleDto.getStrCreatedUser());
                        roles.add(role);
                    }
                } else {
                    LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                            + " AccessBuilder : getUserRoleDetails"
                            + " function : Role List Size is 0");
                }
                user.setInUserId(userDto.getInUserId());
                user.setStrUserName(userDto.getStrUserName());
                user.setStrMappedUser(userDto.getStrMappedUser());
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                String mappedDate = df
                        .format(userDto.getDateRoleMapped());
                user.setStrDateRoleMapped(mappedDate);
                user.setDefaultRole(userDto.getDefaultRole());
                user.setRoles(roles);
            } else {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : getUserRoleDetails "
                        + "function : User is null");
            }
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    +  " AccessBuilder : getUserRoleDetails function"
                    + " : " + Constants.END_STATUS);
            return toStringJsonify(user);
        } catch (StreamTxnException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in getUserRoleDetails function : ", e);
            return getContentBuildingError(Constants.ROLE_FETCHING_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in getUserRoleDetails function : ", e);
            return getContentBuildingError(Constants.ROLE_FETCHING_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION
                    .getCode().toString());
        }
    }

    /**
     * Method for getting all roles corresponding to the user.
     *
     * @return Response
     */
    public String listUserRoleDetails() {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                +  " AccessBuilder : listUserRoleDetails function : "
                + Constants.START_STATUS);
        try {
            List<UserDto> userDtoList = accessService.listUserRoleDetails();
            List<User> userList = null;
            if (userDtoList != null && !userDtoList.isEmpty()) {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        +  " AccessBuilder : listUserRoleDetails "
                        + "function : User List Size is " + userDtoList.size());
                userList = new ArrayList<User>();
                for (UserDto userDto : userDtoList) {
                    User user = new User();
                    user.setInUserId(userDto.getInUserId());
                    user.setStrUserName(userDto.getStrUserName());
                    user.setStrMappedUser(userDto.getStrMappedUser());
                    String mappedDate = commonUtil.dateToString(userDto
                            .getDateRoleMapped());
                    user.setStrDateRoleMapped(mappedDate);
                    user.setDefaultRole(userDto.getDefaultRole());
                    List<Role> roleList = null;
                    if (userDto.getRoles() != null && !userDto.getRoles()
                            .isEmpty()) {
                        LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO
                                .getMessage() + " AccessBuilder : "
                                + "listUserRoleDetails function : Role List "
                                + "Size is " + userDto.getRoles().size());
                        roleList = new ArrayList<Role>();
                        for (RoleDto roleDto : userDto.getRoles()) {
                            Role role = new Role();
                            role.setInRoleId(roleDto.getInRoleId());
                            role.setStrRoleName(roleDto.getStrRoleName());
                            role.setStrRoleDesc(roleDto.getStrRoleDesc());
                            role.setActive(roleDto.isActive());
                            String createdDate = commonUtil.dateToString(
                                    roleDto.getDateCreated());
                            role.setStrDateCreated(createdDate);
                            roleList.add(role);
                        }
                    } else {
                        LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO
                                .getMessage() + " AccessBuilder : "
                                + "listUserRoleDetails function : Role List "
                                + "Size is 0");
                    }
                    user.setRoles(roleList);
                    userList.add(user);
                }
            } else {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : listUserRoleDetails "
                        + "function : User List Size is 0");
            }
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    +  " AccessBuilder : listUserRoleDetails "
                    + "function : " + Constants.END_STATUS);
            return toStringJsonify(userList);
        } catch (StreamTxnException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in listUserRoleDetails function : ", e);
            return getContentBuildingError(Constants.ROLE_FETCHING_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in listUserRoleDetails function : ", e);
            return getContentBuildingError(Constants.ROLE_FETCHING_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION
                    .getCode().toString());
        }
    }

    /**
     * Method for getting the features.
     *
     * @return List
     */
    public String getFeatureList() {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                +  " AccessBuilder : getFeatureList function : "
                + Constants.START_STATUS);
        try {
            List<FeatureDto> featureDtoList = accessService.getFeatureList();
            List<Feature> featureList = null;
            if (featureDtoList != null && !featureDtoList.isEmpty()) {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : getFeatureList "
                        + "function : Feature List Size is " + featureDtoList
                        .size());
                featureList = new ArrayList<Feature>();
                for (FeatureDto featureDto : featureDtoList) {
                    Feature feature = new Feature();
                    feature.setInFeatureId(featureDto.getInFeatureId());
                    feature.setStrFeatureName(featureDto.getStrFeatureName());
                    featureList.add(feature);
                }
            } else {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : getFeatureList "
                        + "function : Feature List Size is 0");
            }
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    + " AccessBuilder : getFeatureList function : "
                    + Constants.END_STATUS);
            return toStringJsonify(featureList);
        } catch (StreamTxnException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + " in getFeatureList function : ", e);
            return getContentBuildingError(Constants.FEATURE_LIST_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + " in getFeatureList function : ", e);
            return getContentBuildingError(Constants.FEATURE_LIST_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION
                    .getCode().toString());
        }
    }


    /**
     * Method for mapping a role to access level.
     *
     * @param role the role
     * @return String
     */
    public String mapRoleAccess(final Role role) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessBuilder : mapRoleAccess function : "
                + Constants.START_STATUS);
        try {
            RoleDto roleDto = new RoleDto();
            roleDto.setInRoleId(role.getInRoleId());
            roleDto.setStrRoleName(role.getStrRoleName());
            roleDto.setStrRoleDesc(role.getStrRoleDesc());
            roleDto.setActive(role.isActive());
            AccessLevel accessLevel = role.getAccessLevel();
            AccessLevelDto accessLevelDto = new AccessLevelDto();
            accessLevelDto.setInAccessId(accessLevel.getInAccessId());
            accessLevelDto.setStrAccessLevelName(accessLevel
                    .getStrAccessLevelName());
            List<FeatureAccessDto> featureAccessDtos = null;
            if (accessLevel.getFeatureAccesses() != null && !accessLevel
                    .getFeatureAccesses().isEmpty()) {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : mapRoleAccess function"
                        + " : Feature Access List Size is " + accessLevel
                        .getFeatureAccesses().size());
                featureAccessDtos = new ArrayList<FeatureAccessDto>();
                for (FeatureAccess featureAccess : accessLevel
                        .getFeatureAccesses()) {
                    FeatureAccessDto featureAccessDto = new FeatureAccessDto();
                    featureAccessDto.setInFeatureAccessId(featureAccess
                            .getInFeatureAccessId());
                    featureAccessDto.setInFeatureId(featureAccess
                            .getInFeatureId());
                    featureAccessDto.setStrFeatureName(featureAccess
                            .getStrFeatureName());
                    featureAccessDto.setFeatureViewEnabled(featureAccess
                            .isFeatureViewEnabled());
                    featureAccessDto.setFeatureEditEnabled(featureAccess
                            .isFeatureEditEnabled());
                    featureAccessDto.setFeatureDeleteEnabled(featureAccess
                            .isFeatureDeleteEnabled());
                    featureAccessDtos.add(featureAccessDto);
                }
            } else {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : mapRoleAccess function"
                        + " : Feature Access List Size is 0");
            }
            accessLevelDto.setFeatureAccesses(featureAccessDtos);
            List<PortalAccessDto> portalAccessDtos = null;
            if (accessLevel.getPortalAccesses() != null && !accessLevel
                    .getPortalAccesses().isEmpty()) {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : mapRoleAccess function"
                        + " : Portal Access List Size is " + accessLevel
                        .getPortalAccesses().size());
                portalAccessDtos = new ArrayList<PortalAccessDto>();
                for (PortalAccess portalAccess : accessLevel
                        .getPortalAccesses()) {
                    PortalAccessDto portalAccessDto = new PortalAccessDto();
                    portalAccessDto.setInPortalAccessId(portalAccess
                            .getInPortalAccessId());
                    portalAccessDto.setInPortalId(portalAccess.getInPortalId());
                    portalAccessDto.setStrPortalName(portalAccess
                            .getStrPortalName());
                    portalAccessDto.setPortalViewEnabled(portalAccess
                            .isPortalViewEnabled());
                    portalAccessDto.setPortalEditEnabled(portalAccess
                            .isPortalEditEnabled());
                    portalAccessDto.setPortalDeleteEnabled(portalAccess
                            .isPortalDeleteEnabled());
                    portalAccessDto.setInDashboardId(portalAccess
                            .getInDashboardId());
                    portalAccessDto.setStrDashboardName(portalAccess
                            .getStrDashboardName());
                    portalAccessDto.setDashboardViewEnabled(portalAccess
                            .isDashboardViewEnabled());
                    portalAccessDto.setDashboardEditEnabled(portalAccess
                            .isDashboardEditEnabled());
                    portalAccessDto.setPortalDeleteEnabled(portalAccess
                            .isDashboardDeleteEnabled());
                    portalAccessDto.setInCategoryId(portalAccess
                            .getInCategoryId());
                    portalAccessDto.setStrCategoryName(portalAccess
                            .getStrCategoryName());
                    portalAccessDto.setCategoryViewEnabled(portalAccess
                            .isCategoryViewEnabled());
                    portalAccessDto.setCategoryEditEnabled(portalAccess
                            .isCategoryEditEnabled());
                    portalAccessDto.setCategoryDeleteEnabled(portalAccess
                            .isCategoryDeleteEnabled());
                    portalAccessDto.setInKpiId(portalAccess.getInKpiId());
                    portalAccessDto.setStrKpiName(portalAccess.getStrKpiName());
                    portalAccessDto.setKpiViewEnabled(portalAccess
                            .isKpiViewEnabled());
                    portalAccessDto.setKpiEditEnabled(portalAccess
                            .isKpiEditEnabled());
                    portalAccessDto.setKpiDeleteEnabled(portalAccess
                            .isKpiDeleteEnabled());
                    portalAccessDto.setInVisualizeId(portalAccess
                            .getInVisualizeId());
                    portalAccessDto.setStrVisualizeName(portalAccess
                            .getStrVisualizeName());
                    portalAccessDto.setVisualizeViewEnabled(portalAccess
                            .isVisualizeViewEnabled());
                    portalAccessDto.setVisualizeEditEnabled(portalAccess
                            .isVisualizeEditEnabled());
                    portalAccessDto.setVisualizeDeleteEnabled(portalAccess
                            .isVisualizeDeleteEnabled());
                    portalAccessDtos.add(portalAccessDto);
                }
            } else {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : mapRoleAccess function"
                        + " : Portal Access List Size is 0");
            }
            accessLevelDto.setPortalAccesses(portalAccessDtos);
            roleDto.setAccessLevelDto(accessLevelDto);
            boolean status = accessService.mapRoleAccess(roleDto);
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    + " AccessBuilder : mapRoleAccess function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(Constants
                        .ACCESS_MAP_SUCCESS);
            } else {
                return getContentBuildingError(Constants.ACCESS_MAP_ERROR,
                        ErrorMessageEnum.ACCESS_LAYER_EXCEPTION
                        .getCode().toString());
            }
        } catch (StreamTxnException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in mapRoleAccess function : ", e);
            return getContentBuildingError(Constants.ACCESS_MAP_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in mapRoleAccess function : ", e);
            return getContentBuildingError(Constants.ACCESS_MAP_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION
                    .getCode().toString());
        }
    }

    /**
     * Method for getting the access levels.
     *
     * @param inRoleId the in role id
     * @return String
     */
    public String getRoleAccessDetails(final int inRoleId) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessBuilder : getRoleAccessDetails function :"
                + Constants.START_STATUS);
        try {
            RoleDto roleDto = accessService.getRoleAccessDetails(inRoleId);
            Role role = null;
            if (roleDto != null) {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : getRoleAccessDetails "
                        + "function : Role is : Role id = " + roleDto
                        .getInRoleId() + ", Role name = " + roleDto
                        .getStrRoleName());
                role = new Role();
                role.setInRoleId(roleDto.getInRoleId());
                role.setStrRoleName(roleDto.getStrRoleName());
                role.setStrRoleDesc(roleDto.getStrRoleDesc());
                role.setActive(roleDto.isActive());
                AccessLevelDto accessLevelDto = roleDto.getAccessLevelDto();
                AccessLevel accessLevel = new AccessLevel();
                List<FeatureAccessDto> featureAccessDtoList = accessLevelDto
                        .getFeatureAccesses();
                List<FeatureAccess> featureAccessList = null;
                if (featureAccessDtoList != null && !featureAccessDtoList
                        .isEmpty()) {
                    LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                            + " AccessBuilder :  getRoleAccessDetails "
                            + "function : Feature Access "
                            + "List Size is " + featureAccessDtoList.size());
                    featureAccessList = new ArrayList<FeatureAccess>();
                    for (FeatureAccessDto featureAccessDto
                            : featureAccessDtoList) {
                        FeatureAccess featureAccess = new FeatureAccess();
                        featureAccess.setInFeatureId(featureAccessDto
                                .getInFeatureId());
                        featureAccess.setInFeatureAccessId(featureAccessDto
                                .getInFeatureAccessId());
                        featureAccess.setStrFeatureName(featureAccessDto
                                .getStrFeatureName());
                        featureAccess.setFeatureViewEnabled(featureAccessDto
                                .isFeatureViewEnabled());
                        featureAccess.setFeatureEditEnabled(featureAccessDto
                                .isFeatureEditEnabled());
                        featureAccess.setFeatureDeleteEnabled(featureAccessDto.isFeatureDeleteEnabled());
                        featureAccessList.add(featureAccess);
                    }
                } else {
                    LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                            + " AccessBuilder : getRoleAccessDetails function :"
                            + " Feature Access List Size is 0");
                }
                List<PortalDto> portalDtoList = accessLevelDto
                        .getPortalDtoList();
                List<Portal> portalList = null;
                if (portalDtoList != null && !portalDtoList.isEmpty()) {
                    LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                            + " AccessBuilder : mapRoleAccess "
                            + "function : Portal List Size is " + portalDtoList
                            .size());
                    portalList = new ArrayList<Portal>();
                    for (PortalDto portalDto : portalDtoList) {
                        Portal portal = new Portal();
                        portal.setPortalId(portalDto.getPortalId());
                        portal.setStrPortalName(portalDto.getStrPortalName());
                        portal.setPortalViewEnabled(portalDto
                                .isPortalViewEnabled());
                        List<Dashboard> dashboardList = null;
                        if (portalDto.getDashboards() != null && !portalDto
                                .getDashboards().isEmpty()) {
                            LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO
                                    .getMessage() + " AccessBuilder : "
                                    + "mapRoleAccess function : Dashboard "
                                    + "List  Size is " + portalDto
                                    .getDashboards()
                                    .size());
                            dashboardList = new ArrayList<Dashboard>();
                            for (DashboardDto dashboardDto : portalDto
                                    .getDashboards()) {
                                Dashboard dashboard = new Dashboard();
                                dashboard.setDashboardId(dashboardDto
                                        .getDashboardId());
                                dashboard.setStrDashboardlName(dashboardDto
                                        .getStrDashboardlName());
                                dashboard.setStrDashboardDesc(dashboardDto
                                        .getStrDashboardDesc());
                                dashboard.setDashboardViewEnabled(dashboardDto.isDashboardViewEnabled());
                                List<CategoryKpiVisualize>
                                        categoryKpiVisualizeList = null;
                                if (dashboardDto
                                        .getCategoryKpiVisualizeDtoList()
                                        != null && !dashboardDto
                                        .getCategoryKpiVisualizeDtoList()
                                        .isEmpty()) {
                                    LOGGER.debug(LogMessageEnum
                                            .ACCESS_LAYER_INFO.getMessage()
                                            + " AccessBuilder : "
                                            + "mapRoleAccess function : "
                                            + "CategoryKpiVisualize List Size "
                                            + "is "
                                            + dashboardDto
                                                    .getCategoryKpiVisualizeDtoList().size());
                                    categoryKpiVisualizeList = new
                                            ArrayList<CategoryKpiVisualize>();
                                    for (CategoryKpiVisualizeDto
                                            categoryKpiVisualizeDto
                                            : dashboardDto
                                            .getCategoryKpiVisualizeDtoList()) {
                                        CategoryKpiVisualize
                                                categoryKpiVisualize = new
                                                CategoryKpiVisualize();
                                        categoryKpiVisualize.setInCategoryId(categoryKpiVisualizeDto
                                                        .getInCategoryId());
                                        categoryKpiVisualize
                                                .setStrCategoryName(categoryKpiVisualizeDto
                                                .getStrCategoryName());
                                        categoryKpiVisualize
                                                .setCategoryViewEnabled(categoryKpiVisualizeDto
                                                .isCategoryViewEnabled());
                                        List<Kpi> kpiList = null;
                                        if (categoryKpiVisualizeDto.getKpis()
                                                != null
                                                && !categoryKpiVisualizeDto
                                                .getKpis().isEmpty()) {
                                            kpiList = new ArrayList<Kpi>();
                                            LOGGER.debug(LogMessageEnum
                                                    .ACCESS_LAYER_INFO
                                                    .getMessage()
                                                    + " AccessBuilder : "
                                                    + "mapRoleAccess function"
                                                    + " : KPI List Size is "
                                                    + categoryKpiVisualizeDto
                                                            .getKpis().size());
                                            for (KpiDto kpiDto
                                                    : categoryKpiVisualizeDto
                                                            .getKpis()) {
                                                Kpi kpi = new Kpi();
                                                kpi.setInKpiId(kpiDto
                                                        .getInKpiId());
                                                kpi.setStrKpiName(kpiDto
                                                        .getStrKpiName());
                                                kpi.setKpiViewEnabled(kpiDto
                                                        .isKpiViewEnabled());
                                                List<Visualize> visualizeList
                                                        = null;
                                                if (kpiDto.getVisualizations()
                                                        != null && !kpiDto
                                                        .getVisualizations()
                                                        .isEmpty()) {
                                                    visualizeList = new
                                                            ArrayList<Visualize>();
                                                    LOGGER.debug(LogMessageEnum
                                                                    .ACCESS_LAYER_INFO.getMessage()
                                                            + " AccessBuilder"
                                                            + " : mapRoleAccess function : Visualization "
                                                            + " List Size is "
                                                            + kpiDto
                                                            .getVisualizations().size());
                                                    for (VisualizeDto
                                                            visualizeDto
                                                            : kpiDto
                                                            .getVisualizations()) {
                                                        Visualize visualize =
                                                                new Visualize();
                                                        visualize
                                                                .setInVisualizeId(visualizeDto.getInVisualizeId());
                                                        visualize
                                                                .setStrVisualizeName(visualizeDto
                                                                .getStrVisualizeName());
                                                        visualize
                                                                .setVisualizeViewEnabled(visualizeDto
                                                                .isVisualizeViewEnabled());
                                                        visualize
                                                                .setInPortalAccessId(visualizeDto
                                                                .getInPortalAccessId());
                                                        visualizeList.add(visualize);
                                                    }
                                                } else {
                                                    LOGGER.debug(LogMessageEnum
                                                            .ACCESS_LAYER_INFO.getMessage()
                                                            + " AccessBuilder "
                                                            + ":mapRoleAccess function : "
                                                            + "Visualization "
                                                            + "List Size is 0");
                                                }
                                                kpi.setVisualizations(visualizeList);
                                                kpiList.add(kpi);
                                            }
                                        } else {
                                            LOGGER.debug(LogMessageEnum
                                                    .ACCESS_LAYER_INFO
                                                    .getMessage()
                                                    + " AccessBuilder : "
                                                    + "mapRoleAccess function"
                                                    + " : KPI List Size is 0");
                                        }
                                        categoryKpiVisualize.setKpis(kpiList);
                                        categoryKpiVisualizeList.add(categoryKpiVisualize);
                                    }
                                } else {
                                    LOGGER.debug(LogMessageEnum
                                            .ACCESS_LAYER_INFO.getMessage()
                                            + " AccessBuilder : "
                                            + "mapRoleAccess function : "
                                            + "CategoryKpiVisualize List Size"
                                            + "is 0");
                                }
                                dashboard.setCategoryKpiVisualizeList(categoryKpiVisualizeList);
                                dashboardList.add(dashboard);
                            }
                        } else {
                            LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO
                                    .getMessage() + " AccessBuilder : "
                                    + "mapRoleAccess function : Dashboard "
                                    + "List Size is 0");
                        }
                        portal.setDashboards(dashboardList);
                        portalList.add(portal);
                    }
                } else {
                    LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                            + " AccessBuilder : mapRoleAccess "
                            + "function : Portal List Size is 0");
                }
                accessLevel.setInAccessId(accessLevelDto.getInAccessId());
                accessLevel.setStrAccessLevelName(accessLevelDto
                        .getStrAccessLevelName());
                accessLevel.setFeatureAccesses(featureAccessList);
                accessLevel.setPortalList(portalList);
                //accessLevel.setPortalAccesses(portalAccessList);
                role.setAccessLevel(accessLevel);
            } else {
                LOGGER.debug(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " AccessBuilder : getRoleAccessDetails "
                        + "function : Role is null");
            }
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    + " AccessBuilder : getRoleAccessDetails "
                    + "function : " + Constants.END_STATUS);
            return toStringJsonify(role);
        } catch (StreamTxnException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in getRoleAccessDetails function : ", e);
            return getContentBuildingError(Constants.ACCESS_MAP_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in getRoleAccessDetails function : ", e);
            return getContentBuildingError(Constants.ACCESS_MAP_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION
                    .getCode().toString());
        }
    }

    /**
     * Sets the portal list.
     *
     * @param accessLevel    the access level
     * @param accessLevelDto the access level dto
     */
    private void setPortalList(final AccessLevel accessLevel,
                               final AccessLevelDto accessLevelDto) {
        List<PortalDto> portalDtoList = accessLevelDto.getPortalDtoList();
        List<Portal> portalList = null;
        if (portalDtoList != null && !portalDtoList.isEmpty()) {
            portalList = new ArrayList<Portal>();
            for (PortalDto portalDto : portalDtoList) {
                Portal portal = new Portal();
                portal.setPortalId(portalDto.getPortalId());
                portal.setStrPortalName(portalDto.getStrPortalName());
                portal.setPortalViewEnabled(portalDto.isPortalViewEnabled());
                List<Dashboard> dashboardList = new ArrayList<Dashboard>();
                if (portalDto.getDashboards() != null) {
                    for (DashboardDto dashboardDto : portalDto
                            .getDashboards()) {
                        Dashboard dashboard = new Dashboard();
                        dashboard.setDashboardId(dashboardDto.getDashboardId());
                        dashboard.setStrDashboardlName(
                                dashboardDto.getStrDashboardlName());
                        dashboard.setStrDashboardDesc(
                                dashboardDto.getStrDashboardDesc());
                        dashboard.setDashboardViewEnabled(
                                dashboardDto.isDashboardViewEnabled());
                        List<CategoryKpiVisualize> categoryKpiVisualizeList =
                                new ArrayList<CategoryKpiVisualize>();
                        if (dashboardDto
                                .getCategoryKpiVisualizeDtoList() != null) {
                            for (CategoryKpiVisualizeDto
                                    categoryKpiVisualizeDto : dashboardDto
                                    .getCategoryKpiVisualizeDtoList()) {
                                CategoryKpiVisualize categoryKpiVisualize =
                                        new CategoryKpiVisualize();
                                categoryKpiVisualize
                                        .setInCategoryId(categoryKpiVisualizeDto
                                                .getInCategoryId());
                                categoryKpiVisualize.setStrCategoryName(
                                        categoryKpiVisualizeDto
                                                .getStrCategoryName());
                                categoryKpiVisualize.setCategoryViewEnabled(
                                        categoryKpiVisualizeDto
                                                .isCategoryViewEnabled());
                                List<Kpi> kpiList = new ArrayList<Kpi>();
                                if (categoryKpiVisualizeDto.getKpis() != null) {
                                    for (KpiDto kpiDto : categoryKpiVisualizeDto
                                            .getKpis()) {
                                        Kpi kpi = new Kpi();
                                        kpi.setInKpiId(kpiDto.getInKpiId());
                                        kpi.setStrKpiName(
                                                kpiDto.getStrKpiName());
                                        kpi.setKpiViewEnabled(
                                                kpiDto.isKpiViewEnabled());
                                        List<Visualize> visualizeList = new
                                                ArrayList<Visualize>();
                                        if (kpiDto
                                                .getVisualizations() != null) {
                                            for (VisualizeDto visualizeDto
                                                    : kpiDto
                                                    .getVisualizations()) {
                                                Visualize visualize = new
                                                        Visualize();
                                                visualize.setInVisualizeId(
                                                        visualizeDto
                                                                .getInVisualizeId());
                                                visualize.setStrVisualizeName(
                                                        visualizeDto
                                                                .getStrVisualizeName());
                                                visualize
                                                        .setVisualizeViewEnabled(
                                                                visualizeDto
                                                                        .isVisualizeViewEnabled());
                                                visualize.setInPortalAccessId(
                                                        visualizeDto
                                                                .getInPortalAccessId());
                                                visualizeList.add(visualize);
                                            }
                                        }
                                        kpi.setVisualizations(visualizeList);
                                        kpiList.add(kpi);
                                    }
                                }
                                categoryKpiVisualize.setKpis(kpiList);
                                categoryKpiVisualizeList
                                        .add(categoryKpiVisualize);
                            }
                        }
                        dashboard.setCategoryKpiVisualizeList(
                                categoryKpiVisualizeList);
                        dashboardList.add(dashboard);
                    }
                }
                portal.setDashboards(dashboardList);
                portalList.add(portal);
            }
        }
        accessLevel.setPortalList(portalList);
    }

    /**
     * Method for getting portal dashboard visualize tree.
     *
     * @return portalDtoList
     */
    public String getPortalDashboardCategoryKpiVisualizeTree() {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessBuilder : "
                + "getPortalDashboardCategoryKpiVisualizeTree "
                + "function : " + Constants.START_STATUS);
        List<Portal> portalList = null;
        List<Dashboard> dashboardList = null;
        try {
            List<PortalDto> portalDtoList = accessService
                    .getPortalDashboardCategoryKpiVisualizeTree();
            portalList = new ArrayList<Portal>();
            for (PortalDto portalDto : portalDtoList) {
                Portal portal = new Portal();
                portal.setPortalId(portalDto.getPortalId());
                portal.setStrPortalName(portalDto.getStrPortalName());
                dashboardList = new ArrayList<Dashboard>();
                if (portalDto.getDashboards() != null) {
                    for (DashboardDto dashboardDto : portalDto.getDashboards()) {
                        Dashboard dashboard = new Dashboard();
                        dashboard.setDashboardId(dashboardDto.getDashboardId());
                        dashboard.setStrDashboardlName(dashboardDto
                                .getStrDashboardlName());
                        List<CategoryKpiVisualizeDto>
                                categoryKpiVisualizeDtoList = dashboardDto
                                .getCategoryKpiVisualizeDtoList();
                        List<CategoryKpiVisualize> categoryKpiVisualizeList =
                                null;

                        if (categoryKpiVisualizeDtoList != null
                                && !categoryKpiVisualizeDtoList.isEmpty()) {
                            categoryKpiVisualizeList = new
                                    ArrayList<CategoryKpiVisualize>();
                            for (CategoryKpiVisualizeDto
                                    categoryKpiVisualizeDto
                                    : categoryKpiVisualizeDtoList) {
                                CategoryKpiVisualize categoryKpiVisualize =
                                        new CategoryKpiVisualize();
                                categoryKpiVisualize.setInCategoryId(categoryKpiVisualizeDto
                                                .getInCategoryId());
                                categoryKpiVisualize.setStrCategoryName(categoryKpiVisualizeDto
                                                .getStrCategoryName());
                                if (categoryKpiVisualizeDto.getKpis() != null
                                        && !categoryKpiVisualizeDto.getKpis()
                                        .isEmpty()) {
                                    List<Kpi> kpis = new ArrayList<Kpi>();
                                    for (KpiDto kpiDto
                                            : categoryKpiVisualizeDto.getKpis()) {
                                        Kpi kpi = new Kpi();
                                        kpi.setInKpiId(kpiDto.getInKpiId());
                                        kpi.setStrKpiName(kpiDto
                                                .getStrKpiName());
                                        if (kpiDto.getVisualizations()
                                                != null && !kpiDto
                                                .getVisualizations().isEmpty()) {
                                            List<Visualize> visualizes = new
                                                    ArrayList<Visualize>();
                                            for (VisualizeDto visualizeDto
                                                    : kpiDto
                                                    .getVisualizations()) {
                                                Visualize visualize = new
                                                        Visualize();
                                                visualize.setInVisualizeId(visualizeDto
                                                                .getInVisualizeId());
                                                visualize.setStrVisualizeName(visualizeDto
                                                                .getStrVisualizeName());
                                                visualizes.add(visualize);
                                            }
                                            kpi.setVisualizations(visualizes);
                                        }
                                        kpis.add(kpi);
                                    }
                                    categoryKpiVisualize.setKpis(kpis);
                                }
                                categoryKpiVisualizeList.add(categoryKpiVisualize);
                            }
                        }
                        dashboard.setCategoryKpiVisualizeList(categoryKpiVisualizeList);
                        dashboardList.add(dashboard);
                    }
                }
                portal.setDashboards(dashboardList);
                portalList.add(portal);
            }
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    + " AccessBuilder : getPortalDashboardCategoryKpiVisualizeTree"
                    + " function : " + Constants.END_STATUS);
            return toStringJsonify(portalList);
        } catch (StreamTxnException e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + " in getPortalDashboardCategoryKpiVisualizeTree "
                    + "function :", e);
            return getContentBuildingError(Constants.PORTAL_FETCH_ERROR + ". "
                    + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                    .toString() + " : " + ErrorMessageEnum
                    .ACCESS_LAYER_EXCEPTION.getMessage() + " AccessBuilder : "
                    + "in getPortalDashboardCategoryKpiVisualizeTree function"
                    + " :", e);
            return getContentBuildingError(Constants.PORTAL_FETCH_ERROR,
                    ErrorMessageEnum.ACCESS_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Check role exist or not.
     *
     * @param inRoleId the role id
     * @param roleName the role name
     * @param userName the user name
     * @return true, if successful
     */
    public boolean checkRoleExistOrNot(int inRoleId, String roleName,
                                         String userName) {
        boolean status = false;
        if(roleName != null && !"".equals(roleName.trim())
                && userName != null && !"".equals(userName.trim())){
            status = accessService.checkRoleExistOrNot(inRoleId,
                    roleName.trim(), userName.trim());
        }
        return status;
    }
}
