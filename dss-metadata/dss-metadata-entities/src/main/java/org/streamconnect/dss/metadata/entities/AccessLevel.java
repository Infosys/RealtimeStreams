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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class AccessLevel.
 *
 * @version 1.0
 */
@Table(name = "tbl_access_level")
@Entity
public class AccessLevel {

    /** The in access id. */
    @Id
    @GeneratedValue
    @Column(name = "access_id")
    private int inAccessId;

    /** The str access level name. */
    @Column(name = "access_level")
    private String strAccessLevelName;

    /** The role. */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "accessLevel")
    private Role role;

    /** The feature accesses. */
    @OneToMany(
            fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "accessLevel")
    private Set<FeatureAccess> featureAccesses;

    /** The portal accesses. */
    @OneToMany(
            fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "accessLevel", orphanRemoval = true)
    private Set<PortalAccess> portalAccesses;

    /** The date created. */
    @Column(name = "access_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    /** The date updated. */
    @Column(name = "access_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    /**
     * Gets the in access id.
     *
     * @return the in access id
     */
    public int getInAccessId() {
        return inAccessId;
    }

    /**
     * Sets the in access id.
     *
     * @param inAccessId
     *            the new in access id
     */
    public void setInAccessId(final int inAccessId) {
        this.inAccessId = inAccessId;
    }

    /**
     * Gets the str access level name.
     *
     * @return the str access level name
     */
    public String getStrAccessLevelName() {
        return strAccessLevelName;
    }

    /**
     * Sets the str access level name.
     *
     * @param strAccessLevelName
     *            the new str access level name
     */
    public void setStrAccessLevelName(final String strAccessLevelName) {
        this.strAccessLevelName = strAccessLevelName;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role
     *            the new role
     */
    public void setRole(final Role role) {
        this.role = role;
    }

    /**
     * Gets the feature accesses.
     *
     * @return the feature accesses
     */
    public Set<FeatureAccess> getFeatureAccesses() {
        return featureAccesses;
    }

    /**
     * Sets the feature accesses.
     *
     * @param featureAccesses
     *            the new feature accesses
     */
    public void setFeatureAccesses(final Set<FeatureAccess> featureAccesses) {
        this.featureAccesses = featureAccesses;
    }

    /**
     * Gets the portal accesses.
     *
     * @return the portal accesses
     */
    public Set<PortalAccess> getPortalAccesses() {
        return portalAccesses;
    }

    /**
     * Sets the portal accesses.
     *
     * @param portalAccesses
     *            the new portal accesses
     */
    public void setPortalAccesses(final Set<PortalAccess> portalAccesses) {
        this.portalAccesses = portalAccesses;
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
}


