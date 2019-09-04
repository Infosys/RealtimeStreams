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
package org.streamconnect.dss.metadata.entities;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;

/**
 * The Class User.
 *
 * @version 1.0
 */
@Table(name = "tbl_user")
@Entity
public class User {

    /** The in user id. */
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int inUserId;

    /** The str user name. */
    @Column(name = "user_name")
    private String strUserName;

    /** The str user desc. */
    @Column(name = "user_desc")
    private String strUserDesc;

    /** The str user password. */
    @Column(name = "user_password")
    private String strUserPassword;

    /** The roles. */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id", nullable = true) },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", nullable = true) })
    private Set<Role> roles;

    /** The user who did the mapping. */
    @Column(name = "user_mapped_role")
    private String strMappedUser;

    /** The date created. */
    @Column(name = "user_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    /** The date updated. */
    @Column(name = "user_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    /** The date on which the role(s) mapped. */
    @Column(name = "user_role_mapped_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRoleMapped;

    /** The Default role. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_role",referencedColumnName = "role_id")
    private Role defaultRole;

    /** The delete status. */
    @Column(name = "user_delete_status")
    private int deleteStatus;

    /** The is active. */
    @Column(name = "user_status", nullable = false)
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
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the roles.
     *
     * @param roles
     *            the new roles
     */
    public void setRoles(final Set<Role> roles) {
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
     * Gets the str user desc.
     *
     * @return the str user desc
     */
    public String getStrUserDesc() {
        return strUserDesc;
    }

    /**
     * Sets the str user desc.
     *
     * @param strUserDesc
     *            the new str user desc
     */
    public void setStrUserDesc(final String strUserDesc) {
        this.strUserDesc = strUserDesc;
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
    public Role getDefaultRole() {
        return defaultRole;
    }

    /**
     * Sets the default role.
     *
     * @param defaultRole
     *            the new default role
     */
    public void setDefaultRole(Role defaultRole) {
        this.defaultRole = defaultRole;
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
     * Gets the active.
     *
     * @return the active
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
