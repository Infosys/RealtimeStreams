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

import java.util.Date;
import java.util.List;

/**
 * The Class Category.
 *
 * @version 1.0
 */
public class Category {

    /** The in category id. */
    private int inCategoryId;

    /** The str category name. */
    private String strCategoryName;

    /** The str category desc. */
    private String strCategoryDesc;

    /** The date from. */
    private Date dateFrom;

    /** The date to. */
    private Date dateTo;

    /** The kpi list. */
    private List<IdNameVO> kpiList;

    /** The kpis. */
    private List<Kpi> kpis;

    /**
     * Instantiates a new category.
     */
    public Category() {
        super();
    }

    /**
     * Gets the in category id.
     *
     * @return the inCategoryId
     */
    public int getInCategoryId() {
        return inCategoryId;
    }

    /**
     * Sets the in category id.
     *
     * @param inCategoryId
     *            the inCategoryId to set
     */
    public void setInCategoryId(final int inCategoryId) {
        this.inCategoryId = inCategoryId;
    }

    /**
     * Gets the str category name.
     *
     * @return the strCategoryName
     */
    public String getStrCategoryName() {
        return strCategoryName;
    }

    /**
     * Sets the str category name.
     *
     * @param strCategoryName
     *            the strCategoryName to set
     */
    public void setStrCategoryName(final String strCategoryName) {
        this.strCategoryName = strCategoryName;
    }

    /**
     * Gets the str category desc.
     *
     * @return the str category desc
     */
    public String getStrCategoryDesc() {
        return strCategoryDesc;
    }

    /**
     * Sets the str category desc.
     *
     * @param strCategoryDesc
     *            the new str category desc
     */
    public void setStrCategoryDesc(final String strCategoryDesc) {
        this.strCategoryDesc = strCategoryDesc;
    }

    /**
     * Gets the date from.
     *
     * @return the date from
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * Sets the date from.
     *
     * @param dateFrom
     *            the new date from
     */
    public void setDateFrom(final Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * Gets the date to.
     *
     * @return the date to
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * Sets the date to.
     *
     * @param dateTo
     *            the new date to
     */
    public void setDateTo(final Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * Gets the kpi list.
     *
     * @return the kpi list
     */
    public List<IdNameVO> getKpiList() {
        return kpiList;
    }

    /**
     * Sets the kpi list.
     *
     * @param kpiList
     *            the new kpi list
     */
    public void setKpiList(final List<IdNameVO> kpiList) {
        this.kpiList = kpiList;
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
}
