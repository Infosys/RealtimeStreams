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

/**
 * The Class PortalAccess.
 *
 * @version 1.0
 */
public class PortalAccess implements Serializable {

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
     * Gets the in portal access id.
     *
     * @return the in portal access id
     */
    public int getInPortalAccessId() {
        return inPortalAccessId;
    }

    /**
     * Sets the in portal access id.
     *
     * @param inPortalAccessId
     *            the new in portal access id
     */
    public void setInPortalAccessId(final int inPortalAccessId) {
        this.inPortalAccessId = inPortalAccessId;
    }

    /**
     * Gets the in portal id.
     *
     * @return the in portal id
     */
    public int getInPortalId() {
        return inPortalId;
    }

    /**
     * Sets the in portal id.
     *
     * @param inPortalId
     *            the new in portal id
     */
    public void setInPortalId(final int inPortalId) {
        this.inPortalId = inPortalId;
    }

    /**
     * Gets the str portal name.
     *
     * @return the str portal name
     */
    public String getStrPortalName() {
        return strPortalName;
    }

    /**
     * Sets the str portal name.
     *
     * @param strPortalName
     *            the new str portal name
     */
    public void setStrPortalName(final String strPortalName) {
        this.strPortalName = strPortalName;
    }

    /**
     * Gets the in dashboard id.
     *
     * @return the in dashboard id
     */
    public int getInDashboardId() {
        return inDashboardId;
    }

    /**
     * Sets the in dashboard id.
     *
     * @param inDashboardId
     *            the new in dashboard id
     */
    public void setInDashboardId(final int inDashboardId) {
        this.inDashboardId = inDashboardId;
    }

    /**
     * Gets the str dashboard name.
     *
     * @return the str dashboard name
     */
    public String getStrDashboardName() {
        return strDashboardName;
    }

    /**
     * Sets the str dashboard name.
     *
     * @param strDashboardName
     *            the new str dashboard name
     */
    public void setStrDashboardName(final String strDashboardName) {
        this.strDashboardName = strDashboardName;
    }

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
     * Gets the in visualize id.
     *
     * @return the in visualize id
     */
    public int getInVisualizeId() {
        return inVisualizeId;
    }

    /**
     * Sets the in visualize id.
     *
     * @param inVisualizeId
     *            the new in visualize id
     */
    public void setInVisualizeId(final int inVisualizeId) {
        this.inVisualizeId = inVisualizeId;
    }

    /**
     * Gets the str visualize name.
     *
     * @return the str visualize name
     */
    public String getStrVisualizeName() {
        return strVisualizeName;
    }

    /**
     * Sets the str visualize name.
     *
     * @param strVisualizeName
     *            the new str visualize name
     */
    public void setStrVisualizeName(final String strVisualizeName) {
        this.strVisualizeName = strVisualizeName;
    }

    /**
     * Checks if is portal view enabled.
     *
     * @return true, if is portal view enabled
     */
    public boolean isPortalViewEnabled() {
        return portalViewEnabled;
    }

    /**
     * Sets the portal view enabled.
     *
     * @param portalViewEnabled
     *            the new portal view enabled
     */
    public void setPortalViewEnabled(final boolean portalViewEnabled) {
        this.portalViewEnabled = portalViewEnabled;
    }

    /**
     * Checks if is portal edit enabled.
     *
     * @return true, if is portal edit enabled
     */
    public boolean isPortalEditEnabled() {
        return portalEditEnabled;
    }

    /**
     * Sets the portal edit enabled.
     *
     * @param portalEditEnabled
     *            the new portal edit enabled
     */
    public void setPortalEditEnabled(final boolean portalEditEnabled) {
        this.portalEditEnabled = portalEditEnabled;
    }

    /**
     * Checks if is portal delete enabled.
     *
     * @return true, if is portal delete enabled
     */
    public boolean isPortalDeleteEnabled() {
        return portalDeleteEnabled;
    }

    /**
     * Sets the portal delete enabled.
     *
     * @param portalDeleteEnabled
     *            the new portal delete enabled
     */
    public void setPortalDeleteEnabled(final boolean portalDeleteEnabled) {
        this.portalDeleteEnabled = portalDeleteEnabled;
    }

    /**
     * Checks if is dashboard view enabled.
     *
     * @return true, if is dashboard view enabled
     */
    public boolean isDashboardViewEnabled() {
        return dashboardViewEnabled;
    }

    /**
     * Sets the dashboard view enabled.
     *
     * @param dashboardViewEnabled
     *            the new dashboard view enabled
     */
    public void setDashboardViewEnabled(final boolean dashboardViewEnabled) {
        this.dashboardViewEnabled = dashboardViewEnabled;
    }

    /**
     * Checks if is dashboard edit enabled.
     *
     * @return true, if is dashboard edit enabled
     */
    public boolean isDashboardEditEnabled() {
        return dashboardEditEnabled;
    }

    /**
     * Sets the dashboard edit enabled.
     *
     * @param dashboardEditEnabled
     *            the new dashboard edit enabled
     */
    public void setDashboardEditEnabled(final boolean dashboardEditEnabled) {
        this.dashboardEditEnabled = dashboardEditEnabled;
    }

    /**
     * Checks if is dashboard delete enabled.
     *
     * @return true, if is dashboard delete enabled
     */
    public boolean isDashboardDeleteEnabled() {
        return dashboardDeleteEnabled;
    }

    /**
     * Sets the dashboard delete enabled.
     *
     * @param dashboardDeleteEnabled
     *            the new dashboard delete enabled
     */
    public void setDashboardDeleteEnabled(final boolean dashboardDeleteEnabled) {
        this.dashboardDeleteEnabled = dashboardDeleteEnabled;
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

    /**
     * Checks if is category edit enabled.
     *
     * @return true, if is category edit enabled
     */
    public boolean isCategoryEditEnabled() {
        return categoryEditEnabled;
    }

    /**
     * Sets the category edit enabled.
     *
     * @param categoryEditEnabled
     *            the new category edit enabled
     */
    public void setCategoryEditEnabled(final boolean categoryEditEnabled) {
        this.categoryEditEnabled = categoryEditEnabled;
    }

    /**
     * Checks if is category delete enabled.
     *
     * @return true, if is category delete enabled
     */
    public boolean isCategoryDeleteEnabled() {
        return categoryDeleteEnabled;
    }

    /**
     * Sets the category delete enabled.
     *
     * @param categoryDeleteEnabled
     *            the new category delete enabled
     */
    public void setCategoryDeleteEnabled(final boolean categoryDeleteEnabled) {
        this.categoryDeleteEnabled = categoryDeleteEnabled;
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

    /**
     * Checks if is kpi edit enabled.
     *
     * @return true, if is kpi edit enabled
     */
    public boolean isKpiEditEnabled() {
        return kpiEditEnabled;
    }

    /**
     * Sets the kpi edit enabled.
     *
     * @param kpiEditEnabled
     *            the new kpi edit enabled
     */
    public void setKpiEditEnabled(final boolean kpiEditEnabled) {
        this.kpiEditEnabled = kpiEditEnabled;
    }

    /**
     * Checks if is kpi delete enabled.
     *
     * @return true, if is kpi delete enabled
     */
    public boolean isKpiDeleteEnabled() {
        return kpiDeleteEnabled;
    }

    /**
     * Sets the kpi delete enabled.
     *
     * @param kpiDeleteEnabled
     *            the new kpi delete enabled
     */
    public void setKpiDeleteEnabled(final boolean kpiDeleteEnabled) {
        this.kpiDeleteEnabled = kpiDeleteEnabled;
    }

    /**
     * Checks if is visualize view enabled.
     *
     * @return true, if is visualize view enabled
     */
    public boolean isVisualizeViewEnabled() {
        return visualizeViewEnabled;
    }

    /**
     * Sets the visualize view enabled.
     *
     * @param visualizeViewEnabled
     *            the new visualize view enabled
     */
    public void setVisualizeViewEnabled(final boolean visualizeViewEnabled) {
        this.visualizeViewEnabled = visualizeViewEnabled;
    }

    /**
     * Checks if is visualize edit enabled.
     *
     * @return true, if is visualize edit enabled
     */
    public boolean isVisualizeEditEnabled() {
        return visualizeEditEnabled;
    }

    /**
     * Sets the visualize edit enabled.
     *
     * @param visualizeEditEnabled
     *            the new visualize edit enabled
     */
    public void setVisualizeEditEnabled(final boolean visualizeEditEnabled) {
        this.visualizeEditEnabled = visualizeEditEnabled;
    }

    /**
     * Checks if is visualize delete enabled.
     *
     * @return true, if is visualize delete enabled
     */
    public boolean isVisualizeDeleteEnabled() {
        return visualizeDeleteEnabled;
    }

    /**
     * Sets the visualize delete enabled.
     *
     * @param visualizeDeleteEnabled
     *            the new visualize delete enabled
     */
    public void setVisualizeDeleteEnabled(final boolean
                                                  visualizeDeleteEnabled) {
        this.visualizeDeleteEnabled = visualizeDeleteEnabled;
    }
}
