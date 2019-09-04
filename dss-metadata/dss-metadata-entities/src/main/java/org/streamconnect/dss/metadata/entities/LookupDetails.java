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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class LookupDetails.
 *
 * @version 1.0
 */
@Table(name = "tbl_lookup_details")
@Entity
public class LookupDetails implements java.io.Serializable {

    /** The id. */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    /** The str source type. */
    @Column(name = "lookup_source_type")
    private String strSourceType;

    /** The str uploaded file. */
    @Column(name = "uploaded_file")
    private String strUploadedFile;

    /** The sink. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sink_id")
    private Sink sink;

    /** The str key space name. */
    @Column(name = "keyspace_name")
    private String strKeySpaceName;

    /** The strtable name. */
    @Column(name = "table_name")
    private String strtableName;

    /** The delete status. */
    @Column(name = "delete_status")
    private int deleteStatus;

    /** The str created user. */
    @Column(name = "created_by")
    private String strCreatedUser;

    /** The str updated user. */
    @Column(name = "updated_by")
    private String strUpdatedUser;

    /** The created date. */
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /** The updated date. */
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    /** The lookup. */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "lookup_id", nullable = false)
    private Lookup lookup;

    /**
     * Instantiates a new lookup details.
     */
    public LookupDetails() {
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets the str source type.
     *
     * @return the str source type
     */
    public String getStrSourceType() {
        return strSourceType;
    }

    /**
     * Sets the str source type.
     *
     * @param strSourceType
     *            the new str source type
     */
    public void setStrSourceType(final String strSourceType) {
        this.strSourceType = strSourceType;
    }

    /**
     * Gets the str uploaded file.
     *
     * @return the str uploaded file
     */
    public String getStrUploadedFile() {
        return strUploadedFile;
    }

    /**
     * Sets the str uploaded file.
     *
     * @param strUploadedFile
     *            the new str uploaded file
     */
    public void setStrUploadedFile(final String strUploadedFile) {
        this.strUploadedFile = strUploadedFile;
    }

    /**
     * Gets the sink.
     *
     * @return the sink
     */
    public Sink getSink() {
        return sink;
    }

    /**
     * Sets the sink.
     *
     * @param sink
     *            the new sink
     */
    public void setSink(final Sink sink) {
        this.sink = sink;
    }

    /**
     * Gets the str key space name.
     *
     * @return the str key space name
     */
    public String getStrKeySpaceName() {
        return strKeySpaceName;
    }

    /**
     * Sets the str key space name.
     *
     * @param strKeySpaceName
     *            the new str key space name
     */
    public void setStrKeySpaceName(final String strKeySpaceName) {
        this.strKeySpaceName = strKeySpaceName;
    }

    /**
     * Gets the strtable name.
     *
     * @return the strtable name
     */
    public String getStrtableName() {
        return strtableName;
    }

    /**
     * Sets the strtable name.
     *
     * @param strtableName
     *            the new strtable name
     */
    public void setStrtableName(final String strtableName) {
        this.strtableName = strtableName;
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
     * Gets the str created user.
     *
     * @return the str created user
     */
    public String getStrCreatedUser() {
        return strCreatedUser;
    }

    /**
     * Sets the str created user.
     *
     * @param strCreatedUser
     *            the new str created user
     */
    public void setStrCreatedUser(final String strCreatedUser) {
        this.strCreatedUser = strCreatedUser;
    }

    /**
     * Gets the str updated user.
     *
     * @return the str updated user
     */
    public String getStrUpdatedUser() {
        return strUpdatedUser;
    }

    /**
     * Sets the str updated user.
     *
     * @param strUpdatedUser
     *            the new str updated user
     */
    public void setStrUpdatedUser(final String strUpdatedUser) {
        this.strUpdatedUser = strUpdatedUser;
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
     * Gets the lookup.
     *
     * @return the lookup
     */
    public Lookup getLookup() {
        return lookup;
    }

    /**
     * Sets the lookup.
     *
     * @param lookup
     *            the new lookup
     */
    public void setLookup(final Lookup lookup) {
        this.lookup = lookup;
    }
}

