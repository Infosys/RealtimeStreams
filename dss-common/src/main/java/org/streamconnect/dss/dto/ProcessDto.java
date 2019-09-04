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
 * The type Process dto.
 */
public class ProcessDto {
    /** The in process id. */
    private int inProcessId;

    /** The str process config name. */
    private String strProcessConfigName;

    /** The str process type. */
    private String strProcessType;

    /** The str process query. */
    private String strProcessQuery;

    /** The obj config details. */
    private Object objConfigDetails;

    /** The date process. */
    private Date dateProcess;

    /** The mini batch interval. */
    private Long miniBatchInterval;

    /** The in user id. */
    private int inUserId;


    /**
     * Instantiates a new Process dto.
     */
    public ProcessDto() {
        super();
    }

    /**
     * Gets in process id.
     *
     * @return the inProcessId
     */
    public int getInProcessId() {
        return inProcessId;
    }

    /**
     * Sets in process id.
     *
     * @param inProcessId the inProcessId to set
     */
    public void setInProcessId(final int inProcessId) {
        this.inProcessId = inProcessId;
    }

    /**
     * Gets str process config name.
     *
     * @return the strProcessConfigName
     */
    public String getStrProcessConfigName() {
        return strProcessConfigName;
    }

    /**
     * Sets str process config name.
     *
     * @param strProcessConfigName the strProcessConfigName to set
     */
    public void setStrProcessConfigName(final String strProcessConfigName) {
        this.strProcessConfigName = strProcessConfigName;
    }

    /**
     * Gets str process type.
     *
     * @return the strProcessType
     */
    public String getStrProcessType() {
        return strProcessType;
    }

    /**
     * Sets str process type.
     *
     * @param strProcessType the strProcessType to set
     */
    public void setStrProcessType(final String strProcessType) {
        this.strProcessType = strProcessType;
    }

    /**
     * Gets obj config details.
     *
     * @return the objConfigDetails
     */
    public Object getObjConfigDetails() {
        return objConfigDetails;
    }

    /**
     * Sets obj config details.
     *
     * @param objConfigDetails the objConfigDetails to set
     */
    public void setObjConfigDetails(final Object objConfigDetails) {
        this.objConfigDetails = objConfigDetails;
    }

    /**
     * Gets str process query.
     *
     * @return the str process query
     */
    public String getStrProcessQuery() {
        return strProcessQuery;
    }

    /**
     * Sets str process query.
     *
     * @param strProcessQuery the str process query
     */
    public void setStrProcessQuery(final String strProcessQuery) {
        this.strProcessQuery = strProcessQuery;
    }

    /**
     * Gets date process.
     *
     * @return the date process
     */
    public Date getDateProcess() {
        return dateProcess;
    }

    /**
     * Sets date process.
     *
     * @param dateProcess the date process
     */
    public void setDateProcess(final Date dateProcess) {
        this.dateProcess = dateProcess;
    }

    /**
     * Gets mini batch interval.
     *
     * @return the mini batch interval
     */
    public Long getMiniBatchInterval() {
        return miniBatchInterval;
    }

    /**
     * Sets mini batch interval.
     *
     * @param miniBatchInterval the mini batch interval
     */
    public void setMiniBatchInterval(final Long miniBatchInterval) {
        this.miniBatchInterval = miniBatchInterval;
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
