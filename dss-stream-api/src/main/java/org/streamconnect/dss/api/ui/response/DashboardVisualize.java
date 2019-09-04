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

import java.io.Serializable;

/**
 * The Class DashboardVisualize.
 *
 * @version 1.0
 */
public class DashboardVisualize implements Serializable {

    /** The id. */
    private int id;

    /** The in category id. */
    private int inCategoryId;

    /** The str category name. */
    private String strCategoryName;

    /** The in kpi id. */
    private int inKpiId;

    /** The str kpi name. */
    private String strKpiName;

    /** The in visualize id. */
    private int inVisualizeId;

    /** The str visualize name. */
    private String strVisualizeName;

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
     * Gets the in category id.
     *
     * @return the in category id
     */
    public int getInCategoryId() {
        return inCategoryId;
    }

    /**
     * Sets the in category id.
     *
     * @param inCategoryId
     *            the new in category id
     */
    public void setInCategoryId(final int inCategoryId) {
        this.inCategoryId = inCategoryId;
    }

    /**
     * Gets the str category name.
     *
     * @return the str category name
     */
    public String getStrCategoryName() {
        return strCategoryName;
    }

    /**
     * Sets the str category name.
     *
     * @param strCategoryName
     *            the new str category name
     */
    public void setStrCategoryName(final String strCategoryName) {
        this.strCategoryName = strCategoryName;
    }

    /**
     * Gets the in kpi id.
     *
     * @return the in kpi id
     */
    public int getInKpiId() {
        return inKpiId;
    }

    /**
     * Sets the in kpi id.
     *
     * @param inKpiId
     *            the new in kpi id
     */
    public void setInKpiId(final int inKpiId) {
        this.inKpiId = inKpiId;
    }

    /**
     * Gets the str kpi name.
     *
     * @return the str kpi name
     */
    public String getStrKpiName() {
        return strKpiName;
    }

    /**
     * Sets the str kpi name.
     *
     * @param strKpiName
     *            the new str kpi name
     */
    public void setStrKpiName(final String strKpiName) {
        this.strKpiName = strKpiName;
    }

    /**
     * Gets the in visualize id.
     *
     * @return the in visualize id
     */
    public int getInVisualizeId() {
        return inVisualizeId;
    }

    /**
     * Sets the in visualize id.
     *
     * @param inVisualizeId
     *            the new in visualize id
     */
    public void setInVisualizeId(final int inVisualizeId) {
        this.inVisualizeId = inVisualizeId;
    }

    /**
     * Gets the str visualize name.
     *
     * @return the str visualize name
     */
    public String getStrVisualizeName() {
        return strVisualizeName;
    }

    /**
     * Sets the str visualize name.
     *
     * @param strVisualizeName
     *            the new str visualize name
     */
    public void setStrVisualizeName(final String strVisualizeName) {
        this.strVisualizeName = strVisualizeName;
    }
}
