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


/**
 * The type Dashboard visualize dto.
 */
public class DashboardVisualizeDto implements Serializable {

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

    /** The is visualize view enabled. */
    private boolean isVisualizeViewEnabled;

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
     * Gets in category id.
     *
     * @return the in category id
     */
    public int getInCategoryId() {
        return inCategoryId;
    }

    /**
     * Sets in category id.
     *
     * @param inCategoryId the in category id
     */
    public void setInCategoryId(final int inCategoryId) {
        this.inCategoryId = inCategoryId;
    }

    /**
     * Gets str category name.
     *
     * @return the str category name
     */
    public String getStrCategoryName() {
        return strCategoryName;
    }

    /**
     * Sets str category name.
     *
     * @param strCategoryName the str category name
     */
    public void setStrCategoryName(final String strCategoryName) {
        this.strCategoryName = strCategoryName;
    }

    /**
     * Gets in kpi id.
     *
     * @return the in kpi id
     */
    public int getInKpiId() {
        return inKpiId;
    }

    /**
     * Sets in kpi id.
     *
     * @param inKpiId the in kpi id
     */
    public void setInKpiId(final int inKpiId) {
        this.inKpiId = inKpiId;
    }

    /**
     * Gets str kpi name.
     *
     * @return the str kpi name
     */
    public String getStrKpiName() {
        return strKpiName;
    }

    /**
     * Sets str kpi name.
     *
     * @param strKpiName the str kpi name
     */
    public void setStrKpiName(final String strKpiName) {
        this.strKpiName = strKpiName;
    }

    /**
     * Gets in visualize id.
     *
     * @return the in visualize id
     */
    public int getInVisualizeId() {
        return inVisualizeId;
    }

    /**
     * Sets in visualize id.
     *
     * @param inVisualizeId the in visualize id
     */
    public void setInVisualizeId(final int inVisualizeId) {
        this.inVisualizeId = inVisualizeId;
    }

    /**
     * Gets str visualize name.
     *
     * @return the str visualize name
     */
    public String getStrVisualizeName() {
        return strVisualizeName;
    }

    /**
     * Sets str visualize name.
     *
     * @param strVisualizeName the str visualize name
     */
    public void setStrVisualizeName(final String strVisualizeName) {
        this.strVisualizeName = strVisualizeName;
    }

    /**
     * Is visualize view enabled boolean.
     *
     * @return the boolean
     */
    public boolean isVisualizeViewEnabled() {
        return isVisualizeViewEnabled;
    }

    /**
     * Sets visualize view enabled.
     *
     * @param visualizeViewEnabled the visualize view enabled
     */
    public void setVisualizeViewEnabled(final boolean visualizeViewEnabled) {
        isVisualizeViewEnabled = visualizeViewEnabled;
    }
}

