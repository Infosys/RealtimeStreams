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

import org.streamconnect.dss.access.service.IAccessService;
import org.streamconnect.dss.dto.FeatureDto;
import org.streamconnect.dss.dto.IdNameDto;
import org.streamconnect.dss.dto.PortalDto;
import org.streamconnect.dss.dto.RoleDto;
import org.streamconnect.dss.dto.UserDto;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.metadata.connection.tx.DashboardTransaction;
import org.streamconnect.dss.metadata.connection.tx.PortalTransaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for listing and managing user, role, access level
 * adding, editing and mapping.
 *
 * @version 1.0
 */
@Service
public class AccessServiceImpl implements IAccessService {

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(AccessServiceImpl.class);

    /** The dashboard transaction. */
    @Autowired
    private DashboardTransaction dashboardTransaction;

    /** The portal transaction. */
    @Autowired
    private PortalTransaction portalTransaction;

    /**
     * Method for getting the users.
     *
     * @return String
     */
    public List<IdNameDto> getUserList() {
        return dashboardTransaction.getUserList();
    }

    /**
     * Method for saving Role against users.
     *
     * @param roleDto
     *            the role dto
     * @return String
     */
    public boolean saveRole(final RoleDto roleDto) {
        return dashboardTransaction.saveRole(roleDto);
    }

    /**
     * Method for deleting a role.
     *
     * @param inRoleId
     *            the in role id
     * @return String
     */
    public boolean deleteRole(final int inRoleId) {
        return dashboardTransaction.deleteRole(inRoleId);
    }

    /**
     * Method for mapping a user to role.
     *
     * @param userDto
     *            the user dto
     * @return String
     */
    public boolean mapUserRole(final UserDto userDto) {
        return dashboardTransaction.mapUserRole(userDto);
    }

    /**
     * Method for getting the roles.
     *
     * @return String
     */
    public List<RoleDto> getRoleList() {
        return dashboardTransaction.getRoleList();
    }

    /**
     * Method for getting roles corresponding to the user.
     *
     * @param username
     *            the username
     * @return Response
     */
    public UserDto getUserRoleDetails(final String username) {
        return dashboardTransaction.getUserRoleDetails(username);
    }

    /**
     * Method for getting all roles corresponding to the user.
     *
     * @return Response
     */
    public List<UserDto> listUserRoleDetails() {
        return dashboardTransaction.listUserRoleDetails();
    }

    /**
     * Method for getting the features.
     *
     * @return List
     */
    public List<FeatureDto> getFeatureList() {
        return dashboardTransaction.getFeatureList();
    }

    /**
     * Method for removing the user role mapping.
     *
     * @param inUserId
     *            the in user id
     * @return Response
     */
    public boolean removeMappedUserRole(final int inUserId) {
        return dashboardTransaction.removeMappedUserRole(inUserId);
    }

    /**
     * Method for mapping role and access levels.
     *
     * @param roleDto
     *            the role dto
     * @return String
     */
    public boolean mapRoleAccess(final RoleDto roleDto) {
        return dashboardTransaction.mapRoleAccess(roleDto);
    }

    /**
     * Method for getting the access levels.
     *
     * @param inRoleId
     *            the in role id
     * @return String
     */
    public RoleDto getRoleAccessDetails(final int inRoleId) {
        return dashboardTransaction.getRoleAccessDetails(inRoleId);
    }

    /**
     * Method for getting portal dashboard visualize tree.
     *
     * @return portalDtoList
     */
    public List<PortalDto> getPortalDashboardCategoryKpiVisualizeTree() {
        return portalTransaction.getPortalDashboardCategoryKpiVisualizeTree();
    }

    /**
     * Check role exist or not.
     *
     * @param inRoleId the role id
     * @param roleName the role name
     * @param userName the user name
     * @return true, if successful
     */
    public boolean checkRoleExistOrNot(int inRoleId, String roleName, String
            userName) {
        return dashboardTransaction.checkRoleExistOrNot(inRoleId, roleName, userName);
    }
}
