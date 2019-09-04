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
package org.streamconnect.dss.access.api.response;

import java.util.List;

/**
 * The Class Kpi.
 *
 * @version 1.0
 */
public class Kpi {

    /** The in kpi id. */
    private int inKpiId;

    /** The str kpi name. */
    private String strKpiName;

    /** The str kpi desc. */
    private String strKpiDesc;

    /** The categories. */
    private List<Category> categories;

    /** The visualizations. */
    private List<Visualize> visualizations;

    /** The kpi view enabled. */
    private boolean kpiViewEnabled;

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
     * Gets the str kpi desc.
     *
     * @return the str kpi desc
     */
    public String getStrKpiDesc() {
        return strKpiDesc;
    }

    /**
     * Sets the str kpi desc.
     *
     * @param strKpiDesc
     *            the new str kpi desc
     */
    public void setStrKpiDesc(final String strKpiDesc) {
        this.strKpiDesc = strKpiDesc;
    }

    /**
     * Gets the categories.
     *
     * @return the categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Sets the categories.
     *
     * @param categories
     *            the new categories
     */
    public void setCategories(final List<Category> categories) {
        this.categories = categories;
    }

    /**
     * Gets the visualizations.
     *
     * @return the visualizations
     */
    public List<Visualize> getVisualizations() {
        return visualizations;
    }

    /**
     * Sets the visualizations.
     *
     * @param visualizations
     *            the new visualizations
     */
    public void setVisualizations(final List<Visualize> visualizations) {
        this.visualizations = visualizations;
    }

    /**
     * Checks if is kpi view enabled.
     *
     * @return true, if is kpi view enabled
     */
    public boolean isKpiViewEnabled() {
        return kpiViewEnabled;
    }

    /**
     * Sets the kpi view enabled.
     *
     * @param kpiViewEnabled
     *            the new kpi view enabled
     */
    public void setKpiViewEnabled(final boolean kpiViewEnabled) {
        this.kpiViewEnabled = kpiViewEnabled;
    }
}
