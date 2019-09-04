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

/**
 * The Class Process.
 *
 * @version 1.0
 */
public class Process {

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

    /** The str date process. */
    private String strDateProcess;

    /**
     * Gets the in process id.
     *
     * @return the in process id
     */
    public int getInProcessId() {
        return inProcessId;
    }

    /**
     * Sets the in process id.
     *
     * @param inProcessId
     *            the new in process id
     */
    public void setInProcessId(final int inProcessId) {
        this.inProcessId = inProcessId;
    }

    /**
     * Gets the str process config name.
     *
     * @return the str process config name
     */
    public String getStrProcessConfigName() {
        return strProcessConfigName;
    }

    /**
     * Sets the str process config name.
     *
     * @param strProcessConfigName
     *            the new str process config name
     */
    public void setStrProcessConfigName(final String strProcessConfigName) {
        this.strProcessConfigName = strProcessConfigName;
    }

    /**
     * Gets the str process type.
     *
     * @return the str process type
     */
    public String getStrProcessType() {
        return strProcessType;
    }

    /**
     * Sets the str process type.
     *
     * @param strProcessType
     *            the new str process type
     */
    public void setStrProcessType(final String strProcessType) {
        this.strProcessType = strProcessType;
    }

    /**
     * Gets the str process query.
     *
     * @return the str process query
     */
    public String getStrProcessQuery() {
        return strProcessQuery;
    }

    /**
     * Sets the str process query.
     *
     * @param strProcessQuery
     *            the new str process query
     */
    public void setStrProcessQuery(final String strProcessQuery) {
        this.strProcessQuery = strProcessQuery;
    }

    /**
     * Gets the str date process.
     *
     * @return the str date process
     */
    public String getStrDateProcess() {
        return strDateProcess;
    }

    /**
     * Sets the str date process.
     *
     * @param strDateProcess
     *            the new str date process
     */
    public void setStrDateProcess(final String strDateProcess) {
        this.strDateProcess = strDateProcess;
    }

    /**
     * Gets the obj config details.
     *
     * @return the obj config details
     */
    public Object getObjConfigDetails() {
        return objConfigDetails;
    }

    /**
     * Sets the obj config details.
     *
     * @param objConfigDetails
     *            the new obj config details
     */
    public void setObjConfigDetails(final Object objConfigDetails) {
        this.objConfigDetails = objConfigDetails;
    }

}
