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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Source.
 *
 * @version 1.0
 */
@Table(name = "tbl_source")
@Entity
public class Source implements java.io.Serializable {

    /** The in source id. */
    @Id
    @GeneratedValue
    @Column(name = "src_id")
    private int inSourceId;

    /** The str source config name. */
    @Column(name = "src_config_name")
    private String strSourceConfigName;

    /** The str source type. */
    @Column(name = "src_type")
    private String strSourceType;

    /** The str source config details. */
    @Lob
    @Column(name = "src_config_details")
    private String strSourceConfigDetails;

    /** The date source. */
    @Column(name = "src_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSource;

    /** The delete status. */
    @Column(name = "src_delete_status")
    private int deleteStatus;

    /** The date updated source. */
    @Column(name = "src_date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdatedSource;

    /** The created by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",referencedColumnName = "user_id")
    private User createdBy;

    /** The updated by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by",referencedColumnName = "user_id")
    private User updatedBy;

    /**
     * Instantiates a new source.
     */
    public Source() {
        super();
    }

    /**
     * Gets the in source id.
     *
     * @return the inSourceId
     */
    public int getInSourceId() {
        return inSourceId;
    }

    /**
     * Sets the in source id.
     *
     * @param inSourceId
     *            the inSourceId to set
     */
    public void setInSourceId(final int inSourceId) {
        this.inSourceId = inSourceId;
    }

    /**
     * Gets the str source config name.
     *
     * @return the strSourceConfigName
     */
    public String getStrSourceConfigName() {
        return strSourceConfigName;
    }

    /**
     * Sets the str source config name.
     *
     * @param strSourceConfigName
     *            the strSourceConfigName to set
     */
    public void setStrSourceConfigName(final String strSourceConfigName) {
        this.strSourceConfigName = strSourceConfigName;
    }

    /**
     * Gets the str source type.
     *
     * @return the strSourceType
     */
    public String getStrSourceType() {
        return strSourceType;
    }

    /**
     * Sets the str source type.
     *
     * @param strSourceType
     *            the strSourceType to set
     */
    public void setStrSourceType(final String strSourceType) {
        this.strSourceType = strSourceType;
    }

    /**
     * Gets the str source config details.
     *
     * @return the strSourceConfigDetails
     */
    public String getStrSourceConfigDetails() {
        return strSourceConfigDetails;
    }

    /**
     * Sets the str source config details.
     *
     * @param strSourceConfigDetails
     *            the strSourceConfigDetails to set
     */
    public void setStrSourceConfigDetails(final String strSourceConfigDetails) {
        this.strSourceConfigDetails = strSourceConfigDetails;
    }

    /**
     * Gets the date source.
     *
     * @return the date source
     */
    public Date getDateSource() {
        return dateSource;
    }

    /**
     * Sets the date source.
     *
     * @param dateSource
     *            the new date source
     */
    public void setDateSource(final Date dateSource) {
        this.dateSource = dateSource;
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
     * Gets the date updated source.
     *
     * @return the date updated source
     */
    public Date getDateUpdatedSource() {
        return dateUpdatedSource;
    }

    /**
     * Sets the date updated source.
     *
     * @param dateUpdatedSource
     *            the new date updated source
     */
    public void setDateUpdatedSource(final Date dateUpdatedSource) {
        this.dateUpdatedSource = dateUpdatedSource;
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

}
