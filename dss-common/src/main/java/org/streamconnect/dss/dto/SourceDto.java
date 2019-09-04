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


/**
 * The type Source dto.
 */
public class SourceDto implements java.io.Serializable {

    /** The in source id. */
    private int inSourceId;

    /** The str source config name. */
    private String strSourceConfigName;

    /** The str source type. */
    private String strSourceType;

    /** The obj source config details. */
    private Object objSourceConfigDetails;

    /** The date source. */
    private Date dateSource;

    /** The in user id. */
    private int inUserId;
    /**
     * Instantiates a new Source dto.
     */
    public SourceDto() {
        super();
    }

    /**
     * Gets in source id.
     *
     * @return the inSourceId
     */
    public int getInSourceId() {
        return inSourceId;
    }

    /**
     * Sets in source id.
     *
     * @param inSourceId the inSourceId to set
     */
    public void setInSourceId(final int inSourceId) {
        this.inSourceId = inSourceId;
    }

    /**
     * Gets str source config name.
     *
     * @return the strSourceConfigName
     */
    public String getStrSourceConfigName() {
        return strSourceConfigName;
    }

    /**
     * Sets str source config name.
     *
     * @param strSourceConfigName the strSourceConfigName to set
     */
    public void setStrSourceConfigName(final String strSourceConfigName) {
        this.strSourceConfigName = strSourceConfigName;
    }

    /**
     * Gets str source type.
     *
     * @return the strSourceType
     */
    public String getStrSourceType() {
        return strSourceType;
    }

    /**
     * Sets str source type.
     *
     * @param strSourceType the strSourceType to set
     */
    public void setStrSourceType(final String strSourceType) {
        this.strSourceType = strSourceType;
    }

    /**
     * Gets obj source config details.
     *
     * @return the objSourceConfigDetails
     */
    public Object getObjSourceConfigDetails() {
        return objSourceConfigDetails;
    }

    /**
     * Sets obj source config details.
     *
     * @param objSourceConfigDetails the objSourceConfigDetails to set
     */
    public void setObjSourceConfigDetails(final Object objSourceConfigDetails) {
        this.objSourceConfigDetails = objSourceConfigDetails;
    }

    /**
     * Gets date source.
     *
     * @return the date source
     */
    public Date getDateSource() {
        return dateSource;
    }

    /**
     * Sets date source.
     *
     * @param dateSource the date source
     */
    public void setDateSource(final Date dateSource) {
        this.dateSource = dateSource;
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


