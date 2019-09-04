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
 * Data Object for Query Plan.
 *
 * @version 1.0
 */
public class QueryPlan {

    /** The process type. */
    private String processType;

    /** The process query. */
    private String processQuery;

    /**
     * Gets the process type.
     *
     * @return the process type
     */
    public String getProcessType() {
        return processType;
    }

    /**
     * Sets the process type.
     *
     * @param processType
     *            the new process type
     */
    public void setProcessType(final String processType) {
        this.processType = processType;
    }

    /**
     * Gets the process query.
     *
     * @return the process query
     */
    public String getProcessQuery() {
        return processQuery;
    }

    /**
     * Sets the process query.
     *
     * @param processQuery
     *            the new process query
     */
    public void setProcessQuery(final String processQuery) {
        this.processQuery = processQuery;
    }
}
