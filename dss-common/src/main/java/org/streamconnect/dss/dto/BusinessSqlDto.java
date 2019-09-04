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

import java.io.Serializable;
import java.util.List;

/**
 * The type Business sql dto.
 */
public class BusinessSqlDto implements Serializable {

    /** The in predecessor. */
    private int inPredecessor;

    /** The is cache. */
    private boolean isCache;

    /** The is save. */
    private boolean isSave;

    /** The str query. */
    private String strQuery;

    /** The str table name. */
    private String strTableName;

    /** The window period. */
    private Long windowPeriod;

    /** The sliding interval. */
    private Long slidingInterval;

    /** The schema list. */
    private List<EntitySchema> schemaList;

    /** The query sink details. */
    private QuerySinkDto querySinkDetails;

    /** The str business rule. */
    private String strBusinessRule;

    /** The window name. */
    private String windowName;

    /**
     * Gets in predecessor.
     *
     * @return the in predecessor
     */
    public int getInPredecessor() {
        return inPredecessor;
    }

    /**
     * Sets in predecessor.
     *
     * @param inPredecessor the in predecessor
     */
    public void setInPredecessor(final int inPredecessor) {
        this.inPredecessor = inPredecessor;
    }

    /**
     * Is cache boolean.
     *
     * @return the boolean
     */
    public boolean isCache() {
        return isCache;
    }

    /**
     * Sets cache.
     *
     * @param cache the cache
     */
    public void setCache(final boolean cache) {
        isCache = cache;
    }

    /**
     * Is save boolean.
     *
     * @return the boolean
     */
    public boolean isSave() {
        return isSave;
    }

    /**
     * Sets save.
     *
     * @param save the save
     */
    public void setSave(final boolean save) {
        isSave = save;
    }

    /**
     * Gets str query.
     *
     * @return the str query
     */
    public String getStrQuery() {
        return strQuery;
    }

    /**
     * Sets str query.
     *
     * @param strQuery the str query
     */
    public void setStrQuery(final String strQuery) {
        this.strQuery = strQuery;
    }

    /**
     * Gets str table name.
     *
     * @return the str table name
     */
    public String getStrTableName() {
        return strTableName;
    }

    /**
     * Sets str table name.
     *
     * @param strTableName the str table name
     */
    public void setStrTableName(final String strTableName) {
        this.strTableName = strTableName;
    }

    /**
     * Gets window period.
     *
     * @return the window period
     */
    public Long getWindowPeriod() {
        return windowPeriod;
    }

    /**
     * Sets window period.
     *
     * @param windowPeriod the window period
     */
    public void setWindowPeriod(final Long windowPeriod) {
        this.windowPeriod = windowPeriod;
    }

    /**
     * Gets schema list.
     *
     * @return the schema list
     */
    public List<EntitySchema> getSchemaList() {
        return schemaList;
    }

    /**
     * Sets schema list.
     *
     * @param schemaList the schema list
     */
    public void setSchemaList(final List<EntitySchema> schemaList) {
        this.schemaList = schemaList;
    }

    /**
     * Gets query sink details.
     *
     * @return the query sink details
     */
    public QuerySinkDto getQuerySinkDetails() {
        return querySinkDetails;
    }

    /**
     * Sets query sink details.
     *
     * @param querySinkDetails the query sink details
     */
    public void setQuerySinkDetails(final QuerySinkDto querySinkDetails) {
        this.querySinkDetails = querySinkDetails;
    }

    /**
     * Gets sliding interval.
     *
     * @return the sliding interval
     */
    public Long getSlidingInterval() {
        return slidingInterval;
    }

    /**
     * Sets sliding interval.
     *
     * @param slidingInterval the sliding interval
     */
    public void setSlidingInterval(final Long slidingInterval) {
        this.slidingInterval = slidingInterval;
    }

    /**
     * Gets str business rule.
     *
     * @return the str business rule
     */
    public String getStrBusinessRule() {
        return strBusinessRule;
    }

    /**
     * Sets str business rule.
     *
     * @param strBusinessRule the str business rule
     */
    public void setStrBusinessRule(final String strBusinessRule) {
        this.strBusinessRule = strBusinessRule;
    }

    /**
     * Gets window name.
     *
     * @return the window name
     */
    public String getWindowName() {
        return windowName;
    }

    /**
     * Sets window name.
     *
     * @param windowName the window name
     */
    public void setWindowName(final String windowName) {
        this.windowName = windowName;
    }

}


