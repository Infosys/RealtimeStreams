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
package org.streamconnect.dss.metadata.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class PortalAccess.
 *
 * @version 1.0
 */
@Table(name = "tbl_portal_access")
@Entity
public class PortalAccess {

    /** The in portal access id. */
    @Id
    @GeneratedValue
    @Column(name = "portal_access_id")
    private int inPortalAccessId;

    /** The portal. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portal_id")
    private Portal portal;

    /** The dashboard. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dashboard_id")
    private Dashboard dashboard;

    /** The category. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    /** The kpi. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kpi_id")
    private Kpi kpi;

    /** The visualize. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visualize_id")
    private Visualize visualize;

    /** The access level. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "access_id")
    private AccessLevel accessLevel;

    /** The portal view enabled. */
    @Column(name = "portal_view")
    private boolean portalViewEnabled;

    /** The portal edit enabled. */
    @Column(name = "portal_edit")
    private boolean portalEditEnabled;

    /** The portal delete enabled. */
    @Column(name = "portal_delete")
    private boolean portalDeleteEnabled;

    /** The dashboard view enabled. */
    @Column(name = "dashboard_view")
    private boolean dashboardViewEnabled;

    /** The dashboard edit enabled. */
    @Column(name = "dashboard_edit")
    private boolean dashboardEditEnabled;

    /** The dashboard delete enabled. */
    @Column(name = "dashboard_delete")
    private boolean dashboardDeleteEnabled;

    /** The category view enabled. */
    @Column(name = "category_view")
    private boolean categoryViewEnabled;

    /** The category edit enabled. */
    @Column(name = "category_edit")
    private boolean categoryEditEnabled;

    /** The category delete enabled. */
    @Column(name = "category_delete")
    private boolean categoryDeleteEnabled;

    /** The kpi view enabled. */
    @Column(name = "kpi_view")
    private boolean kpiViewEnabled;

    /** The kpi edit enabled. */
    @Column(name = "kpi_edit")
    private boolean kpiEditEnabled;

    /** The kpi delete enabled. */
    @Column(name = "kpi_delete")
    private boolean kpiDeleteEnabled;

    /** The visualize view enabled. */
    @Column(name = "visualize_view")
    private boolean visualizeViewEnabled;

    /** The visualize edit enabled. */
    @Column(name = "visualize_edit")
    private boolean visualizeEditEnabled;

    /** The visualize delete enabled. */
    @Column(name = "visualize_delete")
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
     * Gets the portal.
     *
     * @return the portal
     */
    public Portal getPortal() {
        return portal;
    }

    /**
     * Sets the portal.
     *
     * @param portal
     *            the new portal
     */
    public void setPortal(final Portal portal) {
        this.portal = portal;
    }

    /**
     * Gets the dashboard.
     *
     * @return the dashboard
     */
    public Dashboard getDashboard() {
        return dashboard;
    }

    /**
     * Sets the dashboard.
     *
     * @param dashboard
     *            the new dashboard
     */
    public void setDashboard(final Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category
     *            the new category
     */
    public void setCategory(final Category category) {
        this.category = category;
    }

    /**
     * Gets the kpi.
     *
     * @return the kpi
     */
    public Kpi getKpi() {
        return kpi;
    }

    /**
     * Sets the kpi.
     *
     * @param kpi
     *            the new kpi
     */
    public void setKpi(final Kpi kpi) {
        this.kpi = kpi;
    }

    /**
     * Gets the visualize.
     *
     * @return the visualize
     */
    public Visualize getVisualize() {
        return visualize;
    }

    /**
     * Sets the visualize.
     *
     * @param visualize
     *            the new visualize
     */
    public void setVisualize(final Visualize visualize) {
        this.visualize = visualize;
    }

    /**
     * Gets the access level.
     *
     * @return the access level
     */
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    /**
     * Sets the access level.
     *
     * @param accessLevel
     *            the new access level
     */
    public void setAccessLevel(final AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
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
    public void setDashboardDeleteEnabled(
            final boolean dashboardDeleteEnabled) {
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
    public void setVisualizeDeleteEnabled(
            final boolean visualizeDeleteEnabled) {
        this.visualizeDeleteEnabled = visualizeDeleteEnabled;
    }
}
