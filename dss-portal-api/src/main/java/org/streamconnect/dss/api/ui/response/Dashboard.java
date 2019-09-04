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
import java.util.Date;
import java.util.List;

/**
 * The Class Dashboard.
 *
 * @version 1.0
 */
public class Dashboard implements Serializable {

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
    private List<DashboardVisualize> visualizations;

    /** The portal id. */
    private int portalId;

    /** The selected. */
    private boolean selected;

    /** The visualize list. */
    private List<Visualize> visualizeList;

    /** The category kpi visualize list. */
    private List<CategoryKpiVisualize> categoryKpiVisualizeList;

    /** The is dashboard view enabled. */
    private boolean isDashboardViewEnabled;

    /**
     * Gets the dashboard id.
     *
     * @return the dashboard id
     */
    public int getDashboardId() {
        return dashboardId;
    }

    /**
     * Sets the dashboard id.
     *
     * @param dashboardId
     *            the new dashboard id
     */
    public void setDashboardId(final int dashboardId) {
        this.dashboardId = dashboardId;
    }

    /**
     * Gets the str dashboardl name.
     *
     * @return the str dashboardl name
     */
    public String getStrDashboardlName() {
        return strDashboardlName;
    }

    /**
     * Sets the str dashboardl name.
     *
     * @param strDashboardlName
     *            the new str dashboardl name
     */
    public void setStrDashboardlName(final String strDashboardlName) {
        this.strDashboardlName = strDashboardlName;
    }

    /**
     * Gets the str dashboard desc.
     *
     * @return the str dashboard desc
     */
    public String getStrDashboardDesc() {
        return strDashboardDesc;
    }

    /**
     * Sets the str dashboard desc.
     *
     * @param strDashboardDesc
     *            the new str dashboard desc
     */
    public void setStrDashboardDesc(final String strDashboardDesc) {
        this.strDashboardDesc = strDashboardDesc;
    }

    /**
     * Gets the str created user.
     *
     * @return the str created user
     */
    public String getStrCreatedUser() {
        return strCreatedUser;
    }

    /**
     * Sets the str created user.
     *
     * @param strCreatedUser
     *            the new str created user
     */
    public void setStrCreatedUser(final String strCreatedUser) {
        this.strCreatedUser = strCreatedUser;
    }

    /**
     * Gets the str updated user.
     *
     * @return the str updated user
     */
    public String getStrUpdatedUser() {
        return strUpdatedUser;
    }

    /**
     * Sets the str updated user.
     *
     * @param strUpdatedUser
     *            the new str updated user
     */
    public void setStrUpdatedUser(final String strUpdatedUser) {
        this.strUpdatedUser = strUpdatedUser;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate
     *            the new created date
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets the updated date.
     *
     * @param updatedDate
     *            the new updated date
     */
    public void setUpdatedDate(final Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * Gets the delete status.
     *
     * @return the delete status
     */
    public int getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * Sets the delete status.
     *
     * @param deleteStatus
     *            the new delete status
     */
    public void setDeleteStatus(final int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * Gets the visualizations.
     *
     * @return the visualizations
     */
    public List<DashboardVisualize> getVisualizations() {
        return visualizations;
    }

    /**
     * Sets the visualizations.
     *
     * @param visualizations
     *            the new visualizations
     */
    public void setVisualizations(final List<DashboardVisualize> visualizations) {
        this.visualizations = visualizations;
    }

    /**
     * Gets the portal id.
     *
     * @return the portal id
     */
    public int getPortalId() {
        return portalId;
    }

    /**
     * Sets the portal id.
     *
     * @param portalId
     *            the new portal id
     */
    public void setPortalId(final int portalId) {
        this.portalId = portalId;
    }

    /**
     * Checks if is selected.
     *
     * @return true, if is selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets the selected.
     *
     * @param selected
     *            the new selected
     */
    public void setSelected(final boolean selected) {
        this.selected = selected;
    }

    /**
     * Gets the visualize list.
     *
     * @return the visualize list
     */
    public List<Visualize> getVisualizeList() {
        return visualizeList;
    }

    /**
     * Sets the visualize list.
     *
     * @param visualizeList
     *            the new visualize list
     */
    public void setVisualizeList(final List<Visualize> visualizeList) {
        this.visualizeList = visualizeList;
    }

    /**
     * Gets the category kpi visualize list.
     *
     * @return the category kpi visualize list
     */
    public List<CategoryKpiVisualize> getCategoryKpiVisualizeList() {
        return categoryKpiVisualizeList;
    }

    /**
     * Sets the category kpi visualize list.
     *
     * @param categoryKpiVisualizeList
     *            the new category kpi visualize list
     */
    public void setCategoryKpiVisualizeList(
            final List<CategoryKpiVisualize> categoryKpiVisualizeList) {
        this.categoryKpiVisualizeList = categoryKpiVisualizeList;
    }

    /**
     * Checks if is dashboard view enabled.
     *
     * @return true, if is dashboard view enabled
     */
    public boolean isDashboardViewEnabled() {
        return isDashboardViewEnabled;
    }

    /**
     * Sets the dashboard view enabled.
     *
     * @param dashboardViewEnabled
     *            the new dashboard view enabled
     */
    public void setDashboardViewEnabled(final boolean dashboardViewEnabled) {
        isDashboardViewEnabled = dashboardViewEnabled;
    }
}
