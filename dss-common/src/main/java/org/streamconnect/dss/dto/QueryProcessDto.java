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


/**
 * The type Query process dto.
 */
public class QueryProcessDto {

    /** The id. */
    private int id;

    /** The persist enabled. */
    private boolean persistEnabled;

    /** The table. */
    private String table;

    /** The query. */
    private String query;

    /** The columns. */
    private Object columns;

    /** The query sink details. */
    private QuerySinkDto querySinkDetails;

    /** The window period. */
    private Long windowPeriod;

    /** The sliding interval. */
    private Long slidingInterval;

    /** The business rule. */
    private String businessRule;

    /** The predecessor. */
    private int predecessor;

    /** The window name. */
    private String windowName;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Is persist enabled boolean.
     *
     * @return the boolean
     */
    public boolean isPersistEnabled() {
        return persistEnabled;
    }

    /**
     * Sets persist enabled.
     *
     * @param persistEnabled the persist enabled
     */
    public void setPersistEnabled(final boolean persistEnabled) {
        this.persistEnabled = persistEnabled;
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public String getTable() {
        return table;
    }

    /**
     * Sets table.
     *
     * @param table the table
     */
    public void setTable(final String table) {
        this.table = table;
    }

    /**
     * Gets query.
     *
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets query.
     *
     * @param query the query
     */
    public void setQuery(final String query) {
        this.query = query;
    }

    /**
     * Gets columns.
     *
     * @return the columns
     */
    public Object getColumns() {
        return columns;
    }

    /**
     * Sets columns.
     *
     * @param columns the columns
     */
    public void setColumns(final Object columns) {
        this.columns = columns;
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
     * Gets business rule.
     *
     * @return the business rule
     */
    public String getBusinessRule() {
        return businessRule;
    }

    /**
     * Sets business rule.
     *
     * @param businessRule the business rule
     */
    public void setBusinessRule(final String businessRule) {
        this.businessRule = businessRule;
    }

    /**
     * Gets predecessor.
     *
     * @return the predecessor
     */
    public int getPredecessor() {
        return predecessor;
    }

    /**
     * Sets predecessor.
     *
     * @param predecessor the predecessor
     */
    public void setPredecessor(final int predecessor) {
        this.predecessor = predecessor;
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
