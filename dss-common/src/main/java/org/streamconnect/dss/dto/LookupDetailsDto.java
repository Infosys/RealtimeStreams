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
package org.streamconnect.dss.dto;

import java.util.Date;
import java.util.List;

/**
 * The type Lookup details dto.
 *
 * @version 1.0
 */
public class LookupDetailsDto implements java.io.Serializable {


    /** The in lookup id. */
    private int inLookupId;

    /** The lookup config name. */
    private String lookupConfigName;

    /** The lookup type. */
    private String lookupType;

    /** The lookup config details. */
    private Object lookupConfigDetails;

    /** The str created user. */
    private String strCreatedUser;

    /** The str updated user. */
    private String strUpdatedUser;

    /** The created date. */
    private Date createdDate;

    /** The updated date. */
    private Date updatedDate;

    /** The lookup advanced details dtos. */
    private List<LookupAdvancedDetailsDto> lookupAdvancedDetailsDtos;

    /** The in user id. */
    private int inUserId;
    /**
     * Instantiates a new Lookup details dto.
     */
    public LookupDetailsDto() {
    }

    /**
     * Gets in lookup id.
     *
     * @return the in lookup id
     */
    public int getInLookupId() {
        return inLookupId;
    }

    /**
     * Sets in lookup id.
     *
     * @param inLookupId the in lookup id
     */
    public void setInLookupId(final int inLookupId) {
        this.inLookupId = inLookupId;
    }

    /**
     * Gets lookup config name.
     *
     * @return the lookup config name
     */
    public String getLookupConfigName() {
        return lookupConfigName;
    }

    /**
     * Sets lookup config name.
     *
     * @param lookupConfigName the lookup config name
     */
    public void setLookupConfigName(final String lookupConfigName) {
        this.lookupConfigName = lookupConfigName;
    }

    /**
     * Gets lookup type.
     *
     * @return the lookup type
     */
    public String getLookupType() {
        return lookupType;
    }

    /**
     * Sets lookup type.
     *
     * @param lookupType the lookup type
     */
    public void setLookupType(final String lookupType) {
        this.lookupType = lookupType;
    }

    /**
     * Gets lookup config details.
     *
     * @return the lookup config details
     */
    public Object getLookupConfigDetails() {
        return lookupConfigDetails;
    }

    /**
     * Sets lookup config details.
     *
     * @param lookupConfigDetails the lookup config details
     */
    public void setLookupConfigDetails(final Object lookupConfigDetails) {
        this.lookupConfigDetails = lookupConfigDetails;
    }

    /**
     * Gets str created user.
     *
     * @return the str created user
     */
    public String getStrCreatedUser() {
        return strCreatedUser;
    }

    /**
     * Sets str created user.
     *
     * @param strCreatedUser the str created user
     */
    public void setStrCreatedUser(final String strCreatedUser) {
        this.strCreatedUser = strCreatedUser;
    }

    /**
     * Gets str updated user.
     *
     * @return the str updated user
     */
    public String getStrUpdatedUser() {
        return strUpdatedUser;
    }

    /**
     * Sets str updated user.
     *
     * @param strUpdatedUser the str updated user
     */
    public void setStrUpdatedUser(final String strUpdatedUser) {
        this.strUpdatedUser = strUpdatedUser;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate(final Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * Gets lookup advanced details dtos.
     *
     * @return the lookup advanced details dtos
     */
    public List<LookupAdvancedDetailsDto> getLookupAdvancedDetailsDtos() {
        return lookupAdvancedDetailsDtos;
    }

    /**
     * Sets lookup advanced details dtos.
     *
     * @param lookupAdvancedDetailsDtos the lookup advanced details dtos
     */
    public void setLookupAdvancedDetailsDtos(final List<LookupAdvancedDetailsDto> lookupAdvancedDetailsDtos) {
        this.lookupAdvancedDetailsDtos = lookupAdvancedDetailsDtos;
    }

    /**
     * Gets in user id.
     *
     * @return the in user id
     */
    public int getInUserId() {
        return inUserId;
    }

    /**
     * Sets in user id.
     *
     * @param inUserId the in user id
     */
    public void setInUserId(final int inUserId) {
        this.inUserId = inUserId;
    }
}

