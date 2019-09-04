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
 * The type Portal access dto.
 */
public class PortalAccessDto implements Serializable {
    /** The in portal access id. */
    private int inPortalAccessId;

    /** The in portal id. */
    private int inPortalId;

    /** The str portal name. */
    private String strPortalName;

    /** The in dashboard id. */
    private int inDashboardId;

    /** The str dashboard name. */
    private String strDashboardName;

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

    /** The portal view enabled. */
    private boolean portalViewEnabled;

    /** The portal edit enabled. */
    private boolean portalEditEnabled;

    /** The portal delete enabled. */
    private boolean portalDeleteEnabled;

    /** The dashboard view enabled. */
    private boolean dashboardViewEnabled;

    /** The dashboard edit enabled. */
    private boolean dashboardEditEnabled;

    /** The dashboard delete enabled. */
    private boolean dashboardDeleteEnabled;

    /** The category view enabled. */
    private boolean categoryViewEnabled;

    /** The category edit enabled. */
    private boolean categoryEditEnabled;

    /** The category delete enabled. */
    private boolean categoryDeleteEnabled;

    /** The kpi view enabled. */
    private boolean kpiViewEnabled;

    /** The kpi edit enabled. */
    private boolean kpiEditEnabled;

    /** The kpi delete enabled. */
    private boolean kpiDeleteEnabled;

    /** The visualize view enabled. */
    private boolean visualizeViewEnabled;

    /** The visualize edit enabled. */
    private boolean visualizeEditEnabled;

    /** The visualize delete enabled. */
    private boolean visualizeDeleteEnabled;

    /**
     * Gets in portal access id.
     *
     * @return the in portal access id
     */
    public int getInPortalAccessId() {
        return inPortalAccessId;
    }

    /**
     * Sets in portal access id.
     *
     * @param inPortalAccessId the in portal access id
     */
    public void setInPortalAccessId(final int inPortalAccessId) {
        this.inPortalAccessId = inPortalAccessId;
    }

    /**
     * Gets in portal id.
     *
     * @return the in portal id
     */
    public int getInPortalId() {
        return inPortalId;
    }

    /**
     * Sets in portal id.
     *
     * @param inPortalId the in portal id
     */
    public void setInPortalId(final int inPortalId) {
        this.inPortalId = inPortalId;
    }

    /**
     * Gets str portal name.
     *
     * @return the str portal name
     */
    public String getStrPortalName() {
        return strPortalName;
    }

    /**
     * Sets str portal name.
     *
     * @param strPortalName the str portal name
     */
    public void setStrPortalName(final String strPortalName) {
        this.strPortalName = strPortalName;
    }

    /**
     * Gets in dashboard id.
     *
     * @return the in dashboard id
     */
    public int getInDashboardId() {
        return inDashboardId;
    }

    /**
     * Sets in dashboard id.
     *
     * @param inDashboardId the in dashboard id
     */
    public void setInDashboardId(final int inDashboardId) {
        this.inDashboardId = inDashboardId;
    }

    /**
     * Gets str dashboard name.
     *
     * @return the str dashboard name
     */
    public String getStrDashboardName() {
        return strDashboardName;
    }

    /**
     * Sets str dashboard name.
     *
     * @param strDashboardName the str dashboard name
     */
    public void setStrDashboardName(final String strDashboardName) {
        this.strDashboardName = strDashboardName;
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
     * Is portal view enabled boolean.
     *
     * @return the boolean
     */
    public boolean isPortalViewEnabled() {
        return portalViewEnabled;
    }

    /**
     * Sets portal view enabled.
     *
     * @param portalViewEnabled the portal view enabled
     */
    public void setPortalViewEnabled(final boolean portalViewEnabled) {
        this.portalViewEnabled = portalViewEnabled;
    }

    /**
     * Is portal edit enabled boolean.
     *
     * @return the boolean
     */
    public boolean isPortalEditEnabled() {
        return portalEditEnabled;
    }

    /**
     * Sets portal edit enabled.
     *
     * @param portalEditEnabled the portal edit enabled
     */
    public void setPortalEditEnabled(final boolean portalEditEnabled) {
        this.portalEditEnabled = portalEditEnabled;
    }

    /**
     * Is portal delete enabled boolean.
     *
     * @return the boolean
     */
    public boolean isPortalDeleteEnabled() {
        return portalDeleteEnabled;
    }

    /**
     * Sets portal delete enabled.
     *
     * @param portalDeleteEnabled the portal delete enabled
     */
    public void setPortalDeleteEnabled(final boolean portalDeleteEnabled) {
        this.portalDeleteEnabled = portalDeleteEnabled;
    }

    /**
     * Is dashboard view enabled boolean.
     *
     * @return the boolean
     */
    public boolean isDashboardViewEnabled() {
        return dashboardViewEnabled;
    }

    /**
     * Sets dashboard view enabled.
     *
     * @param dashboardViewEnabled the dashboard view enabled
     */
    public void setDashboardViewEnabled(final boolean dashboardViewEnabled) {
        this.dashboardViewEnabled = dashboardViewEnabled;
    }

    /**
     * Is dashboard edit enabled boolean.
     *
     * @return the boolean
     */
    public boolean isDashboardEditEnabled() {
        return dashboardEditEnabled;
    }

    /**
     * Sets dashboard edit enabled.
     *
     * @param dashboardEditEnabled the dashboard edit enabled
     */
    public void setDashboardEditEnabled(final boolean dashboardEditEnabled) {
        this.dashboardEditEnabled = dashboardEditEnabled;
    }

    /**
     * Is dashboard delete enabled boolean.
     *
     * @return the boolean
     */
    public boolean isDashboardDeleteEnabled() {
        return dashboardDeleteEnabled;
    }

    /**
     * Sets dashboard delete enabled.
     *
     * @param dashboardDeleteEnabled the dashboard delete enabled
     */
    public void setDashboardDeleteEnabled(final boolean
                                                  dashboardDeleteEnabled) {
        this.dashboardDeleteEnabled = dashboardDeleteEnabled;
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

    /**
     * Is category edit enabled boolean.
     *
     * @return the boolean
     */
    public boolean isCategoryEditEnabled() {
        return categoryEditEnabled;
    }

    /**
     * Sets category edit enabled.
     *
     * @param categoryEditEnabled the category edit enabled
     */
    public void setCategoryEditEnabled(final boolean categoryEditEnabled) {
        this.categoryEditEnabled = categoryEditEnabled;
    }

    /**
     * Is category delete enabled boolean.
     *
     * @return the boolean
     */
    public boolean isCategoryDeleteEnabled() {
        return categoryDeleteEnabled;
    }

    /**
     * Sets category delete enabled.
     *
     * @param categoryDeleteEnabled the category delete enabled
     */
    public void setCategoryDeleteEnabled(final boolean categoryDeleteEnabled) {
        this.categoryDeleteEnabled = categoryDeleteEnabled;
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
     * @param kpiViewEnabled the kpi view enabled
     */
    public void setKpiViewEnabled(final boolean kpiViewEnabled) {
        this.kpiViewEnabled = kpiViewEnabled;
    }

    /**
     * Is kpi edit enabled boolean.
     *
     * @return the boolean
     */
    public boolean isKpiEditEnabled() {
        return kpiEditEnabled;
    }

    /**
     * Sets kpi edit enabled.
     *
     * @param kpiEditEnabled the kpi edit enabled
     */
    public void setKpiEditEnabled(final boolean kpiEditEnabled) {
        this.kpiEditEnabled = kpiEditEnabled;
    }

    /**
     * Is kpi delete enabled boolean.
     *
     * @return the boolean
     */
    public boolean isKpiDeleteEnabled() {
        return kpiDeleteEnabled;
    }

    /**
     * Sets kpi delete enabled.
     *
     * @param kpiDeleteEnabled the kpi delete enabled
     */
    public void setKpiDeleteEnabled(final boolean kpiDeleteEnabled) {
        this.kpiDeleteEnabled = kpiDeleteEnabled;
    }

    /**
     * Is visualize view enabled boolean.
     *
     * @return the boolean
     */
    public boolean isVisualizeViewEnabled() {
        return visualizeViewEnabled;
    }

    /**
     * Sets visualize view enabled.
     *
     * @param visualizeViewEnabled the visualize view enabled
     */
    public void setVisualizeViewEnabled(final boolean visualizeViewEnabled) {
        this.visualizeViewEnabled = visualizeViewEnabled;
    }

    /**
     * Is visualize edit enabled boolean.
     *
     * @return the boolean
     */
    public boolean isVisualizeEditEnabled() {
        return visualizeEditEnabled;
    }

    /**
     * Sets visualize edit enabled.
     *
     * @param visualizeEditEnabled the visualize edit enabled
     */
    public void setVisualizeEditEnabled(final boolean visualizeEditEnabled) {
        this.visualizeEditEnabled = visualizeEditEnabled;
    }

    /**
     * Is visualize delete enabled boolean.
     *
     * @return the boolean
     */
    public boolean isVisualizeDeleteEnabled() {
        return visualizeDeleteEnabled;
    }

    /**
     * Sets visualize delete enabled.
     *
     * @param visualizeDeleteEnabled the visualize delete enabled
     */
    public void setVisualizeDeleteEnabled(final boolean
                                                  visualizeDeleteEnabled) {
        this.visualizeDeleteEnabled = visualizeDeleteEnabled;
    }
}
