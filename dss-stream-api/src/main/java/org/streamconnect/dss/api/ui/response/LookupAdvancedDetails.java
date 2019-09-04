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

import java.io.InputStream;
import java.util.Date;

/**
 * The Class LookupAdvancedDetails.
 *
 * @version 1.0
 */
public class LookupAdvancedDetails implements java.io.Serializable {

    /** The in lookup id. */
    private int inLookupId;

    /** The id. */
    private int id;

    /** The str source type. */
    private String strSourceType;

    /** The str uploaded file name. */
    private String strUploadedFileName;

    /** The uploaded file. */
    private InputStream uploadedFile;

    /** The sink id. */
    private int sinkId;

    /** The sink name. */
    private String sinkName;

    /** The str key space name. */
    private String strKeySpaceName;

    /** The strtable name. */
    private String strtableName;

    /** The delete status. */
    private int deleteStatus;

    /** The str created user. */
    private String strCreatedUser;

    /** The str updated user. */
    private String strUpdatedUser;

    /** The created date. */
    private Date createdDate;

    /** The updated date. */
    private Date updatedDate;

    /**
     * Instantiates a new lookup advanced details.
     */
    public LookupAdvancedDetails() {
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
     * Gets the str uploaded file name.
     *
     * @return the str uploaded file name
     */
    public String getStrUploadedFileName() {
        return strUploadedFileName;
    }

    /**
     * Sets the str uploaded file name.
     *
     * @param strUploadedFileName
     *            the new str uploaded file name
     */
    public void setStrUploadedFileName(final String strUploadedFileName) {
        this.strUploadedFileName = strUploadedFileName;
    }

    /**
     * Gets the uploaded file.
     *
     * @return the uploaded file
     */
    public InputStream getUploadedFile() {
        return uploadedFile;
    }

    /**
     * Sets the uploaded file.
     *
     * @param uploadedFile
     *            the new uploaded file
     */
    public void setUploadedFile(final InputStream uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    /**
     * Gets the sink id.
     *
     * @return the sink id
     */
    public int getSinkId() {
        return sinkId;
    }

    /**
     * Sets the sink id.
     *
     * @param sinkId
     *            the new sink id
     */
    public void setSinkId(final int sinkId) {
        this.sinkId = sinkId;
    }

    /**
     * Gets the sink name.
     *
     * @return the sink name
     */
    public String getSinkName() {
        return sinkName;
    }

    /**
     * Sets the sink name.
     *
     * @param sinkName
     *            the new sink name
     */
    public void setSinkName(final String sinkName) {
        this.sinkName = sinkName;
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
}
