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
 * The Class User.
 *
 * @version 1.0
 */
public class User {

    /** The in user id. */
    private int inUserId;

    /** The str user name. */
    private String strUserName;

    /** The str user password. */
    private String strUserPassword;

    /** The roles. */
    private List<Role> roles;

    /** The date created. */
    private Date dateCreated;

    /** The date updated. */
    private Date dateUpdated;

    /** The delete status. */
    private int deleteStatus;

    /** The is active. */
    private boolean isActive;

    /**
     * Gets the in user id.
     *
     * @return the in user id
     */
    public int getInUserId() {
        return inUserId;
    }

    /**
     * Sets the in user id.
     *
     * @param inUserId
     *            the new in user id
     */
    public void setInUserId(final int inUserId) {
        this.inUserId = inUserId;
    }

    /**
     * Gets the str user name.
     *
     * @return the str user name
     */
    public String getStrUserName() {
        return strUserName;
    }

    /**
     * Sets the str user name.
     *
     * @param strUserName
     *            the new str user name
     */
    public void setStrUserName(final String strUserName) {
        this.strUserName = strUserName;
    }

    /**
     * Gets the str user password.
     *
     * @return the str user password
     */
    public String getStrUserPassword() {
        return strUserPassword;
    }

    /**
     * Sets the str user password.
     *
     * @param strUserPassword
     *            the new str user password
     */
    public void setStrUserPassword(final String strUserPassword) {
        this.strUserPassword = strUserPassword;
    }

    /**
     * Gets the roles.
     *
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the roles.
     *
     * @param roles
     *            the new roles
     */
    public void setRoles(final List<Role> roles) {
        this.roles = roles;
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
     * Gets the date updated.
     *
     * @return the date updated
     */
    public Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * Sets the date updated.
     *
     * @param dateUpdated
     *            the new date updated
     */
    public void setDateUpdated(final Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    /**
     * Gets the delete status.
     *
     * @return the delete status
     */
    public int getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * Sets the delete status.
     *
     * @param deleteStatus
     *            the new delete status
     */
    public void setDeleteStatus(final int deleteStatus) {
        this.deleteStatus = deleteStatus;
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
