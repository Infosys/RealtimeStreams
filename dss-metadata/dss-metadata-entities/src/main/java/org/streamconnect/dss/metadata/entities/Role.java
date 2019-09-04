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
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Role.
 *
 * @version 1.0
 */
@Table(name = "tbl_role")
@Entity
public class Role {

    /** The in role id. */
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private int inRoleId;

    /** The str role name. */
    @Column(name = "role_name")
    private String strRoleName;

    /** The str role desc. */
    @Column(name = "role_desc")
    private String strRoleDesc;

    /** The users. */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<User> users = new HashSet<User>(0);

    /** The date created. */
    @Column(name = "role_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    /** The date updated. */
    @Column(name = "role_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    /** The user who created the role. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",referencedColumnName = "user_id")
    private User createdBy;

    /** The user who updated the role. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by",referencedColumnName = "user_id")
    private User updatedBy;

    /** The access level. */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "access_id")
    private AccessLevel accessLevel;

    /** The delete status. */
    @Column(name = "role_delete_status")
    private int deleteStatus;

    /** The is active. */
    @Column(name = "role_status")
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
     * Gets the users.
     *
     * @return the users
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Sets the users.
     *
     * @param users
     *            the new users
     */
    public void setUsers(final Set<User> users) {
        this.users = users;
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
     * Gets the user who created the role
     *
     * @return the user who created the role
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the role
     *
     * @param createdBy
     *            the user who created the role
     */
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the user who updated the role
     *
     * @return the user who updated the role
     */
    public User getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Sets the user who updated the role
     *
     * @param updatedBy
     *            the user who updated the role
     */
    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
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
