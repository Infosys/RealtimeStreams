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

import java.io.Serializable;
import java.util.List;

/**
 * The Class CategoryKpiVisualize.
 *
 * @version 1.0
 */
public class CategoryKpiVisualize implements Serializable {

    /** The in category id. */
    private int inCategoryId;

    /** The str category name. */
    private String strCategoryName;

    /** The kpis. */
    private List<Kpi> kpis;

    /** The category view enabled. */
    private boolean categoryViewEnabled;

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
     * Gets the kpis.
     *
     * @return the kpis
     */
    public List<Kpi> getKpis() {
        return kpis;
    }

    /**
     * Sets the kpis.
     *
     * @param kpis
     *            the new kpis
     */
    public void setKpis(final List<Kpi> kpis) {
        this.kpis = kpis;
    }

    /**
     * Checks if is category view enabled.
     *
     * @return true, if is category view enabled
     */
    public boolean isCategoryViewEnabled() {
        return categoryViewEnabled;
    }

    /**
     * Sets the category view enabled.
     *
     * @param categoryViewEnabled
     *            the new category view enabled
     */
    public void setCategoryViewEnabled(final boolean categoryViewEnabled) {
        this.categoryViewEnabled = categoryViewEnabled;
    }
}
