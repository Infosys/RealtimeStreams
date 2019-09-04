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
package org.streamconnect.dss.api.ui.response;

import java.util.Date;
import java.util.List;

/**
 * The Class Role.
 *
 * @version 1.0
 */
public class Role implements java.io.Serializable {

    /** The in role id. */
    private int inRoleId;

    /** The str role name. */
    private String strRoleName;

    /** The str role desc. */
    private String strRoleDesc;

    /** The date created. */
    private Date dateCreated;

    /** The users. */
    private List<User> users;

    /** The access level. */
    private AccessLevel accessLevel;

    /** The is active. */
    private boolean isActive;

    /**
     * Gets the in role id.
     *
     * @return the in role id
     */
    public int getInRoleId() {
        return inRoleId;
    }

    /**
     * Sets the in role id.
     *
     * @param inRoleId
     *            the new in role id
     */
    public void setInRoleId(final int inRoleId) {
        this.inRoleId = inRoleId;
    }

    /**
     * Gets the str role name.
     *
     * @return the str role name
     */
    public String getStrRoleName() {
        return strRoleName;
    }

    /**
     * Sets the str role name.
     *
     * @param strRoleName
     *            the new str role name
     */
    public void setStrRoleName(final String strRoleName) {
        this.strRoleName = strRoleName;
    }

    /**
     * Gets the str role desc.
     *
     * @return the str role desc
     */
    public String getStrRoleDesc() {
        return strRoleDesc;
    }

    /**
     * Sets the str role desc.
     *
     * @param strRoleDesc
     *            the new str role desc
     */
    public void setStrRoleDesc(final String strRoleDesc) {
        this.strRoleDesc = strRoleDesc;
    }

    /**
     * Gets the date created.
     *
     * @return the date created
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the date created.
     *
     * @param dateCreated
     *            the new date created
     */
    public void setDateCreated(final Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Gets the users.
     *
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets the users.
     *
     * @param users
     *            the new users
     */
    public void setUsers(final List<User> users) {
        this.users = users;
    }

    /**
     * Gets the access level.
     *
     * @return the access level
     */
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    /**
     * Sets the access level.
     *
     * @param accessLevel
     *            the new access level
     */
    public void setAccessLevel(final AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the active.
     *
     * @param active
     *            the new active
     */
    public void setActive(final boolean active) {
        isActive = active;
    }
}
