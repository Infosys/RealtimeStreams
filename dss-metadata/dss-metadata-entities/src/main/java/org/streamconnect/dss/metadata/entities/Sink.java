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
 * The Class Sink.
 *
 * @version 1.0
 */
@Table(name = "tbl_sink")
@Entity
public class Sink implements java.io.Serializable {

    /** The in sink id. */
    @Id
    @GeneratedValue
    @Column(name = "sink_id")
    private int inSinkId;

    /** The str sink name. */
    @Column(name = "sink_name")
    private String strSinkName;

    /** The str sink type. */
    @Column(name = "sink_type")
    private String strSinkType;

    /** The str sink config details. */
    @Lob
    @Column(name = "sink_config_details")
    private String strSinkConfigDetails;

    /** The date sink. */
    @Column(name = "sink_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSink;

    /** The delete status. */
    @Column(name = "sink_delete_status")
    private int deleteStatus;

    /** The date updated sink. */
    @Column(name = "sink_date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdatedSink;

    /** The created by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",referencedColumnName = "user_id")
    private User createdBy;

    /** The updated by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by",referencedColumnName = "user_id")
    private User updatedBy;
    /**
     * Instantiates a new sink.
     */
    public Sink() {
        super();
    }

    /**
     * Gets the in sink id.
     *
     * @return the inSinkId
     */
    public int getInSinkId() {
        return inSinkId;
    }

    /**
     * Sets the in sink id.
     *
     * @param inSinkId
     *            the inSinkId to set
     */
    public void setInSinkId(final int inSinkId) {
        this.inSinkId = inSinkId;
    }

    /**
     * Gets the str sink name.
     *
     * @return the strSinkName
     */
    public String getStrSinkName() {
        return strSinkName;
    }

    /**
     * Sets the str sink name.
     *
     * @param strSinkName
     *            the strSinkName to set
     */
    public void setStrSinkName(final String strSinkName) {
        this.strSinkName = strSinkName;
    }

    /**
     * Gets the str sink type.
     *
     * @return the strSinkType
     */
    public String getStrSinkType() {
        return strSinkType;
    }

    /**
     * Sets the str sink type.
     *
     * @param strSinkType
     *            the strSinkType to set
     */
    public void setStrSinkType(final String strSinkType) {
        this.strSinkType = strSinkType;
    }

    /**
     * Gets the str sink config details.
     *
     * @return the strSinkConfigDetails
     */
    public String getStrSinkConfigDetails() {
        return strSinkConfigDetails;
    }

    /**
     * Sets the str sink config details.
     *
     * @param strSinkConfigDetails
     *            the strSinkConfigDetails to set
     */
    public void setStrSinkConfigDetails(final String strSinkConfigDetails) {
        this.strSinkConfigDetails = strSinkConfigDetails;
    }

    /**
     * Gets the date sink.
     *
     * @return the date sink
     */
    public Date getDateSink() {
        return dateSink;
    }

    /**
     * Sets the date sink.
     *
     * @param dateSink
     *            the new date sink
     */
    public void setDateSink(final Date dateSink) {
        this.dateSink = dateSink;
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
     * Gets the date updated sink.
     *
     * @return the date updated sink
     */
    public Date getDateUpdatedSink() {
        return dateUpdatedSink;
    }

    /**
     * Sets the date updated sink.
     *
     * @param dateUpdatedSink
     *            the new date updated sink
     */
    public void setDateUpdatedSink(final Date dateUpdatedSink) {
        this.dateUpdatedSink = dateUpdatedSink;
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
