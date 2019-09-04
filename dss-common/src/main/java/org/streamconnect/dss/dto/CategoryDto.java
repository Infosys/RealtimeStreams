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

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;


/**
 * The type Category dto.
 */
@XmlRootElement
public class CategoryDto {

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
    private List<IdNameDto> kpiList;

    /** The kpis. */
    private List<KpiDto> kpis;

    /** The in user id. */
    private int inUserId;
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
     * Instantiates a new Category dto.
     */
    public CategoryDto() {
        super();
    }

    /**
     * Gets in category id.
     *
     * @return the inCategoryId
     */
    public int getInCategoryId() {
        return inCategoryId;
    }

    /**
     * Sets in category id.
     *
     * @param inCategoryId the inCategoryId to set
     */
    public void setInCategoryId(final int inCategoryId) {
        this.inCategoryId = inCategoryId;
    }

    /**
     * Gets str category name.
     *
     * @return the strCategoryName
     */
    public String getStrCategoryName() {
        return strCategoryName;
    }

    /**
     * Sets str category name.
     *
     * @param strCategoryName the strCategoryName to set
     */
    public void setStrCategoryName(final String strCategoryName) {
        this.strCategoryName = strCategoryName;
    }

    /**
     * Gets str category desc.
     *
     * @return the str category desc
     */
    public String getStrCategoryDesc() {
        return strCategoryDesc;
    }

    /**
     * Sets str category desc.
     *
     * @param strCategoryDesc the str category desc
     */
    public void setStrCategoryDesc(final String strCategoryDesc) {
        this.strCategoryDesc = strCategoryDesc;
    }

    /**
     * Gets date from.
     *
     * @return the date from
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * Sets date from.
     *
     * @param dateFrom the date from
     */
    public void setDateFrom(final Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * Gets date to.
     *
     * @return the date to
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * Sets date to.
     *
     * @param dateTo the date to
     */
    public void setDateTo(final Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * Gets kpi list.
     *
     * @return the kpi list
     */
    public List<IdNameDto> getKpiList() {
        return kpiList;
    }

    /**
     * Sets kpi list.
     *
     * @param kpiList the kpi list
     */
    public void setKpiList(final List<IdNameDto> kpiList) {
        this.kpiList = kpiList;
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
