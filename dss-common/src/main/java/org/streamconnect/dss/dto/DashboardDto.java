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
import java.util.Date;
import java.util.List;


/**
 * The type Dashboard dto.
 */
public class DashboardDto implements Serializable {

    /** The dashboard id. */
    private int dashboardId;

    /** The str dashboardl name. */
    private String strDashboardlName;

    /** The str dashboard desc. */
    private String strDashboardDesc;

    /** The str created user. */
    private String strCreatedUser;

    /** The str updated user. */
    private String strUpdatedUser;

    /** The created date. */
    private Date createdDate;

    /** The updated date. */
    private Date updatedDate;

    /** The delete status. */
    private int deleteStatus;

    /** The visualizations. */
    private List<DashboardVisualizeDto> visualizations;

    /** The portal id. */
    private int portalId;

    /** The visualize dto list. */
    private List<VisualizeDto> visualizeDtoList;

    /** The category kpi visualize dto list. */
    private List<CategoryKpiVisualizeDto> categoryKpiVisualizeDtoList;

    /** The is dashboard view enabled. */
    private boolean isDashboardViewEnabled;

    /** The in user id. */
    private int inUserId;

    /**
     * Gets dashboard id.
     *
     * @return the dashboard id
     */
    public int getDashboardId() {
        return dashboardId;
    }

    /**
     * Sets dashboard id.
     *
     * @param dashboardId the dashboard id
     */
    public void setDashboardId(final int dashboardId) {
        this.dashboardId = dashboardId;
    }

    /**
     * Gets str dashboardl name.
     *
     * @return the str dashboardl name
     */
    public String getStrDashboardlName() {
        return strDashboardlName;
    }

    /**
     * Sets str dashboardl name.
     *
     * @param strDashboardlName the str dashboardl name
     */
    public void setStrDashboardlName(final String strDashboardlName) {
        this.strDashboardlName = strDashboardlName;
    }

    /**
     * Gets str dashboard desc.
     *
     * @return the str dashboard desc
     */
    public String getStrDashboardDesc() {
        return strDashboardDesc;
    }

    /**
     * Sets str dashboard desc.
     *
     * @param strDashboardDesc the str dashboard desc
     */
    public void setStrDashboardDesc(final String strDashboardDesc) {
        this.strDashboardDesc = strDashboardDesc;
    }

    /**
     * Gets str created user.
     *
     * @return the str created user
     */
    public String getStrCreatedUser() {
        return strCreatedUser;
    }

    /**
     * Sets str created user.
     *
     * @param strCreatedUser the str created user
     */
    public void setStrCreatedUser(final String strCreatedUser) {
        this.strCreatedUser = strCreatedUser;
    }

    /**
     * Gets str updated user.
     *
     * @return the str updated user
     */
    public String getStrUpdatedUser() {
        return strUpdatedUser;
    }

    /**
     * Sets str updated user.
     *
     * @param strUpdatedUser the str updated user
     */
    public void setStrUpdatedUser(final String strUpdatedUser) {
        this.strUpdatedUser = strUpdatedUser;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate(final Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * Gets delete status.
     *
     * @return the delete status
     */
    public int getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * Sets delete status.
     *
     * @param deleteStatus the delete status
     */
    public void setDeleteStatus(final int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * Gets visualizations.
     *
     * @return the visualizations
     */
    public List<DashboardVisualizeDto> getVisualizations() {
        return visualizations;
    }

    /**
     * Sets visualizations.
     *
     * @param visualizations the visualizations
     */
    public void setVisualizations(final List<DashboardVisualizeDto> visualizations) {
        this.visualizations = visualizations;
    }

    /**
     * Gets portal id.
     *
     * @return the portal id
     */
    public int getPortalId() {
        return portalId;
    }

    /**
     * Sets portal id.
     *
     * @param portalId the portal id
     */
    public void setPortalId(final int portalId) {
        this.portalId = portalId;
    }

    /**
     * Gets visualize dto list.
     *
     * @return the visualize dto list
     */
    public List<VisualizeDto> getVisualizeDtoList() {
        return visualizeDtoList;
    }

    /**
     * Sets visualize dto list.
     *
     * @param visualizeDtoList the visualize dto list
     */
    public void setVisualizeDtoList(final List<VisualizeDto> visualizeDtoList) {
        this.visualizeDtoList = visualizeDtoList;
    }

    /**
     * Gets category kpi visualize dto list.
     *
     * @return the category kpi visualize dto list
     */
    public List<CategoryKpiVisualizeDto> getCategoryKpiVisualizeDtoList() {
        return categoryKpiVisualizeDtoList;
    }

    /**
     * Sets category kpi visualize dto list.
     *
     * @param categoryKpiVisualizeDtoList the category kpi visualize dto list
     */
    public void setCategoryKpiVisualizeDtoList(final List<CategoryKpiVisualizeDto> categoryKpiVisualizeDtoList) {
        this.categoryKpiVisualizeDtoList = categoryKpiVisualizeDtoList;
    }

    /**
     * Is dashboard view enabled boolean.
     *
     * @return the boolean
     */
    public boolean isDashboardViewEnabled() {
        return isDashboardViewEnabled;
    }

    /**
     * Sets dashboard view enabled.
     *
     * @param dashboardViewEnabled the dashboard view enabled
     */
    public void setDashboardViewEnabled(final boolean dashboardViewEnabled) {
        isDashboardViewEnabled = dashboardViewEnabled;
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

