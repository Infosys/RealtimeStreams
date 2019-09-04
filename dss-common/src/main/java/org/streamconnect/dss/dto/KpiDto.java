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

import java.util.List;

/**
 * The type Kpi dto.
 */
public class KpiDto {

    /** The in kpi id. */
    private int inKpiId;

    /** The str kpi name. */
    private String strKpiName;

    /** The str kpi desc. */
    private String strKpiDesc;

    /** The categories. */
    private List<CategoryDto> categories;

    /** The visualizations. */
    private List<VisualizeDto> visualizations;

    /** The kpi view enabled. */
    private boolean kpiViewEnabled;

    /** The in user id. */
    private int inUserId;

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
     * @param inKpiId
     *            the in kpi id
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
     * @param strKpiName
     *            the str kpi name
     */
    public void setStrKpiName(final String strKpiName) {
        this.strKpiName = strKpiName;
    }

    /**
     * Gets str kpi desc.
     *
     * @return the str kpi desc
     */
    public String getStrKpiDesc() {
        return strKpiDesc;
    }

    /**
     * Sets str kpi desc.
     *
     * @param strKpiDesc
     *            the str kpi desc
     */
    public void setStrKpiDesc(final String strKpiDesc) {
        this.strKpiDesc = strKpiDesc;
    }

    /**
     * Gets categories.
     *
     * @return the categories
     */
    public List<CategoryDto> getCategories() {
        return categories;
    }

    /**
     * Sets categories.
     *
     * @param categories
     *            the categories
     */
    public void setCategories(final List<CategoryDto> categories) {
        this.categories = categories;
    }

    /**
     * Gets visualizations.
     *
     * @return the visualizations
     */
    public List<VisualizeDto> getVisualizations() {
        return visualizations;
    }

    /**
     * Sets visualizations.
     *
     * @param visualizations
     *            the visualizations
     */
    public void setVisualizations(final List<VisualizeDto> visualizations) {
        this.visualizations = visualizations;
    }

    /**
     * Is kpi view enabled boolean.
     *
     * @return the boolean
     */
    public boolean isKpiViewEnabled() {
        return kpiViewEnabled;
    }

    /**
     * Sets kpi view enabled.
     *
     * @param kpiViewEnabled
     *            the kpi view enabled
     */
    public void setKpiViewEnabled(final boolean kpiViewEnabled) {
        this.kpiViewEnabled = kpiViewEnabled;
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
