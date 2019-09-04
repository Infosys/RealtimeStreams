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
 * The type Category kpi visualize dto.
 */
public class CategoryKpiVisualizeDto implements Serializable {

    /** The in category id. */
    private int inCategoryId;

    /** The str category name. */
    private String strCategoryName;

    /** The kpis. */
    private List<KpiDto> kpis;

    /** The category view enabled. */
    private boolean categoryViewEnabled;

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
     * Gets kpis.
     *
     * @return the kpis
     */
    public List<KpiDto> getKpis() {
        return kpis;
    }

    /**
     * Sets kpis.
     *
     * @param kpis the kpis
     */
    public void setKpis(final List<KpiDto> kpis) {
        this.kpis = kpis;
    }

    /**
     * Is category view enabled boolean.
     *
     * @return the boolean
     */
    public boolean isCategoryViewEnabled() {
        return categoryViewEnabled;
    }

    /**
     * Sets category view enabled.
     *
     * @param categoryViewEnabled the category view enabled
     */
    public void setCategoryViewEnabled(final boolean categoryViewEnabled) {
        this.categoryViewEnabled = categoryViewEnabled;
    }
}

