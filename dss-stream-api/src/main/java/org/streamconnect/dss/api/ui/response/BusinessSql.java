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
 * The Class BusinessSql.
 *
 * @version 1.0
 */
public class BusinessSql {

    /** The in sql order. */
    private int inSqlOrder;

    /** The is cache. */
    private boolean isCache;

    /** The is save. */
    private boolean isSave;

    /** The str query. */
    private String strQuery;

    /**
     * Instantiates a new business sql.
     */
    public BusinessSql() {
        super();
    }

    /**
     * Gets the in sql order.
     *
     * @return the inSqlOrder
     */
    public int getInSqlOrder() {
        return inSqlOrder;
    }

    /**
     * Sets the in sql order.
     *
     * @param inSqlOrder
     *            the inSqlOrder to set
     */
    public void setInSqlOrder(final int inSqlOrder) {
        this.inSqlOrder = inSqlOrder;
    }

    /**
     * Checks if is cache.
     *
     * @return the isCache
     */
    public boolean isCache() {
        return isCache;
    }

    /**
     * Sets the cache.
     *
     * @param isCache
     *            the isCache to set
     */
    public void setCache(final boolean isCache) {
        this.isCache = isCache;
    }

    /**
     * Checks if is save.
     *
     * @return the isSave
     */
    public boolean isSave() {
        return isSave;
    }

    /**
     * Sets the save.
     *
     * @param isSave
     *            the isSave to set
     */
    public void setSave(final boolean isSave) {
        this.isSave = isSave;
    }

    /**
     * Gets the str query.
     *
     * @return the strQuery
     */
    public String getStrQuery() {
        return strQuery;
    }

    /**
     * Sets the str query.
     *
     * @param strQuery
     *            the strQuery to set
     */
    public void setStrQuery(final String strQuery) {
        this.strQuery = strQuery;
    }

}
