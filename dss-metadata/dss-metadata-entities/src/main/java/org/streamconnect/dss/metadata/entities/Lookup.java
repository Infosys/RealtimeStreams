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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Lookup.
 *
 * @version 1.0
 */
@Table(name = "tbl_lookup")
@Entity
public class Lookup implements java.io.Serializable {

    /** The in lookup id. */
    @Id
    @GeneratedValue
    @Column(name = "lookup_id")
    private int inLookupId;

    /** The str lookup config name. */
    @Column(name = "lookup_config_name")
    private String strLookupConfigName;

    /** The str lookup type. */
    @Column(name = "lookup_type")
    private String strLookupType;

    /** The str lookup config details. */
    @Lob
    @Column(name = "lookup_config_details")
    private String strLookupConfigDetails;

    /** The delete status. */
    @Column(name = "lookup_delete_status")
    private int deleteStatus;

    /** The created by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",referencedColumnName = "user_id")
    private User createdBy;

    /** The updated by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by",referencedColumnName = "user_id")
    private User updatedBy;

    /** The created date. */
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /** The updated date. */
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    /** The lookup details. */
    @OneToMany(
            fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "lookup")
    private Set<LookupDetails> lookupDetails;

    /**
     * Instantiates a new lookup.
     */
    public Lookup() {
    }

    /**
     * Gets the in lookup id.
     *
     * @return the in lookup id
     */
    public int getInLookupId() {
        return inLookupId;
    }

    /**
     * Sets the in lookup id.
     *
     * @param inLookupId
     *            the new in lookup id
     */
    public void setInLookupId(final int inLookupId) {
        this.inLookupId = inLookupId;
    }

    /**
     * Gets the str lookup config name.
     *
     * @return the str lookup config name
     */
    public String getStrLookupConfigName() {
        return strLookupConfigName;
    }

    /**
     * Sets the str lookup config name.
     *
     * @param strLookupConfigName
     *            the new str lookup config name
     */
    public void setStrLookupConfigName(final String strLookupConfigName) {
        this.strLookupConfigName = strLookupConfigName;
    }

    /**
     * Gets the str lookup type.
     *
     * @return the str lookup type
     */
    public String getStrLookupType() {
        return strLookupType;
    }

    /**
     * Sets the str lookup type.
     *
     * @param strLookupType
     *            the new str lookup type
     */
    public void setStrLookupType(final String strLookupType) {
        this.strLookupType = strLookupType;
    }

    /**
     * Gets the str lookup config details.
     *
     * @return the str lookup config details
     */
    public String getStrLookupConfigDetails() {
        return strLookupConfigDetails;
    }

    /**
     * Sets the str lookup config details.
     *
     * @param strLookupConfigDetails
     *            the new str lookup config details
     */
    public void setStrLookupConfigDetails(final String strLookupConfigDetails) {
        this.strLookupConfigDetails = strLookupConfigDetails;
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
     * Gets the created by.
     *
     * @return the created by
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the new created by
     */
    public void setCreatedBy(final User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the updated by.
     *
     * @return the updated by
     */
    public User getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Sets the updated by.
     *
     * @param updatedBy the new updated by
     */
    public void setUpdatedBy(final User updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate
     *            the new created date
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets the updated date.
     *
     * @param updatedDate
     *            the new updated date
     */
    public void setUpdatedDate(final Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * Gets the lookup details.
     *
     * @return the lookup details
     */
    public Set<LookupDetails> getLookupDetails() {
        return lookupDetails;
    }

    /**
     * Sets the lookup details.
     *
     * @param lookupDetails
     *            the new lookup details
     */
    public void setLookupDetails(final Set<LookupDetails> lookupDetails) {
        this.lookupDetails = lookupDetails;
    }
}
