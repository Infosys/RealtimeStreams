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
package org.streamconnect.dss.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The type Role dto.
 */
public class RoleDto implements Serializable {

    /** The in role id. */
    private int inRoleId;

    /** The str role name. */
    private String strRoleName;

    /** The str role desc. */
    private String strRoleDesc;

    /** The date created. */
    private Date dateCreated;

    /** The created/updated user id. */
    private int inUserId;

    /** The created user. */
    private String strCreatedUser;

    /** The users. */
    private List<UserDto> users;

    /** The access level dto. */
    private AccessLevelDto accessLevelDto;

    /** The is active. */
    private boolean isActive;

    /**
     * Gets in role id.
     *
     * @return the in role id
     */
    public int getInRoleId() {
        return inRoleId;
    }

    /**
     * Sets in role id.
     *
     * @param inRoleId the in role id
     */
    public void setInRoleId(final int inRoleId) {
        this.inRoleId = inRoleId;
    }

    /**
     * Gets str role name.
     *
     * @return the str role name
     */
    public String getStrRoleName() {
        return strRoleName;
    }

    /**
     * Sets str role name.
     *
     * @param strRoleName the str role name
     */
    public void setStrRoleName(final String strRoleName) {
        this.strRoleName = strRoleName;
    }

    /**
     * Gets str role desc.
     *
     * @return the str role desc
     */
    public String getStrRoleDesc() {
        return strRoleDesc;
    }

    /**
     * Sets str role desc.
     *
     * @param strRoleDesc the str role desc
     */
    public void setStrRoleDesc(final String strRoleDesc) {
        this.strRoleDesc = strRoleDesc;
    }

    /**
     * Gets date created.
     *
     * @return the date created
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets date created.
     *
     * @param dateCreated the date created
     */
    public void setDateCreated(final Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Gets the user who created/updated the role
     *
     * @return the user who created the role
     */
    public int getInUserId() {
        return inUserId;
    }

    /**
     * Sets the user who created/updated the role
     *
     * @param inUserId
     *            the user who created the role
     */
    public void setInUserId(int inUserId) {
        this.inUserId = inUserId;
    }

    /**
     * Gets the user who created the role
     *
     * @return the user who created the role
     */
    public String getStrCreatedUser() {
        return strCreatedUser;
    }

    /**
     * Sets the user who created the role
     *
     * @param strCreatedUser
     *            the user who created the role
     */
    public void setStrCreatedUser(String strCreatedUser) {
        this.strCreatedUser = strCreatedUser;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public List<UserDto> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(final List<UserDto> users) {
        this.users = users;
    }

    /**
     * Gets access level dto.
     *
     * @return the access level dto
     */
    public AccessLevelDto getAccessLevelDto() {
        return accessLevelDto;
    }

    /**
     * Sets access level dto.
     *
     * @param accessLevelDto the access level dto
     */
    public void setAccessLevelDto(final AccessLevelDto accessLevelDto) {
        this.accessLevelDto = accessLevelDto;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(final boolean active) {
        isActive = active;
    }
}
