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
package org.streamconnect.dss.access.service;

import org.streamconnect.dss.dto.FeatureDto;
import org.streamconnect.dss.dto.IdNameDto;
import org.streamconnect.dss.dto.PortalDto;
import org.streamconnect.dss.dto.RoleDto;
import org.streamconnect.dss.dto.UserDto;
import java.util.List;

/**
 * Service for listing managing user, role, access level adding, editing and
 * mapping.
 * @version 1.0
 */
public interface IAccessService {

    /**
     * Method for getting the users.
     *
     * @return String
     */
    List<IdNameDto> getUserList();

    /**
     * Method for saving Role against users.
     *
     * @param roleDto the role dto
     * @return String
     */
    boolean saveRole(RoleDto roleDto);

    /**
     * Method for deleting a role.
     *
     * @param inRoleId the in role id
     * @return String
     */
    boolean deleteRole(int inRoleId);

    /**
     * Method for mapping a user to role.
     *
     * @param userDto the user dto
     * @return String
     */
    boolean mapUserRole(UserDto userDto);

    /**
     * Method for getting the roles.
     *
     * @return List
     */
    List<RoleDto> getRoleList();

    /**
     * Method for getting roles corresponding to the user.
     *
     * @param username the username
     * @return Response
     */
    UserDto getUserRoleDetails(String username);

    /**
     * Method for getting all roles corresponding to the user.
     *
     * @return Response
     */
    List<UserDto> listUserRoleDetails();

    /**
     * Method for removing the user role mapping.
     *
     * @param inUserId the in user id
     * @return Response
     */
    boolean removeMappedUserRole(int inUserId);


    /**
     * Method for getting the features.
     *
     * @return List
     */
    List<FeatureDto> getFeatureList();

    /**
     * Method for mapping role and access levels.
     *
     * @param roleDto the role dto
     * @return String
     */
    boolean mapRoleAccess(RoleDto roleDto);

    /**
     * Method for getting the access levels.
     *
     * @param inRoleId the in role id
     * @return String
     */
    RoleDto getRoleAccessDetails(int inRoleId);

    /**
     * Method for getting portal dashboard visualize tree.
     *
     * @return portalDtoList
     */
    List<PortalDto> getPortalDashboardCategoryKpiVisualizeTree();

    /**
     * Check role exist or not.
     *
     * @param inRoleId the role id
     * @param roleName the role name
     * @param userName the user name
     * @return true, if successful
     */
    boolean checkRoleExistOrNot(int inRoleId, String roleName,
                                String userName);
}
