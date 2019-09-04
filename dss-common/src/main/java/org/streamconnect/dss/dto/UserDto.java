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
 * The type User dto.
 */
public class UserDto implements Serializable {

    /** The in user id. */
    private int inUserId;

    /** The str user name. */
    private String strUserName;

    /** The str user desc. */
    private String strUserDesc;

    /** The str user password. */
    private String strUserPassword;

    /** The roles. */
    private List<RoleDto> roles;

    /** The user who did the mapping. */
    private String strMappedUser;

    /** The date created. */
    private Date dateCreated;

    /** The date updated. */
    private Date dateUpdated;

    /** The date on which role(s) are mapped. */
    private Date dateRoleMapped;

    /** The Default role. */
    private int defaultRole;

    /** The delete status. */
    private int deleteStatus;

    /** The is active. */
    private boolean isActive;

    /**
     * Gets in user id.
     *
     * @return the in user id
     */
    public int getInUserId() {
        return inUserId;
    }

    /**
     * Sets in user id.
     *
     * @param inUserId the in user id
     */
    public void setInUserId(final int inUserId) {
        this.inUserId = inUserId;
    }

    /**
     * Gets str user name.
     *
     * @return the str user name
     */
    public String getStrUserName() {
        return strUserName;
    }

    /**
     * Sets str user name.
     *
     * @param strUserName the str user name
     */
    public void setStrUserName(final String strUserName) {
        this.strUserName = strUserName;
    }

    /**
     * Gets str user desc.
     *
     * @return the str user desc
     */
    public String getStrUserDesc() {
        return strUserDesc;
    }

    /**
     * Sets str user desc.
     *
     * @param strUserDesc the str user desc
     */
    public void setStrUserDesc(final String strUserDesc) {
        this.strUserDesc = strUserDesc;
    }

    /**
     * Gets str user password.
     *
     * @return the str user password
     */
    public String getStrUserPassword() {
        return strUserPassword;
    }

    /**
     * Sets str user password.
     *
     * @param strUserPassword the str user password
     */
    public void setStrUserPassword(final String strUserPassword) {
        this.strUserPassword = strUserPassword;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public List<RoleDto> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(final List<RoleDto> roles) {
        this.roles = roles;
    }

    /**
     * Gets the user who mapped the role to a user
     *
     * @return the user who mapped the role
     */
    public String getStrMappedUser() {
        return strMappedUser;
    }

    /**
     * Sets the user who mapped the role to a user
     *
     * @param strMappedUser
     *            the user who mapped the role
     */
    public void setStrMappedUser(String strMappedUser) {
        this.strMappedUser = strMappedUser;
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
     * Gets date updated.
     *
     * @return the date updated
     */
    public Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * Sets date updated.
     *
     * @param dateUpdated the date updated
     */
    public void setDateUpdated(final Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    /**
     * Gets the date on which role(s) being mapped.
     *
     * @return the date updated
     */
    public Date getDateRoleMapped() {
        return dateRoleMapped;
    }

    /**
     * Sets the date on which role(s) being mapped.
     *
     * @param dateRoleMapped
     *            the new date on which role(s) being mapped.
     */
    public void setDateRoleMapped(Date dateRoleMapped) {
        this.dateRoleMapped = dateRoleMapped;
    }

    /**
     * Gets the default role.
     *
     * @return the default role
     */
    public int getDefaultRole() {
        return defaultRole;
    }

    /**
     * Sets the default role.
     *
     * @param defaultRole
     *            the new default role
     */
    public void setDefaultRole(int defaultRole) {
        this.defaultRole = defaultRole;
    }

    /**
     * Gets delete status.
     *
     * @return the delete status
     */
    public int getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * Sets delete status.
     *
     * @param deleteStatus the delete status
     */
    public void setDeleteStatus(final int deleteStatus) {
        this.deleteStatus = deleteStatus;
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
