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

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Dashboard.
 *
 * @version 1.0
 */
@Table(name = "tbl_dashboard")
@Entity
public class Dashboard implements Serializable {

    /** The dashboard id. */
    @Id
    @GeneratedValue
    @Column(name = "dashboard_id")
    private int dashboardId;

    /** The str dashboardl name. */
    @Column(name = "dashboard_name")
    private String strDashboardlName;

    /** The str dashboard desc. */
    @Column(name = "dashboard_desc")
    private String strDashboardDesc;

    /** The created date. */
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /** The updated date. */
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    /** The delete status. */
    @Column(name = "portal_delete_status")
    private int deleteStatus;

    /** The visualizations. */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "dashboard_visualize",
            joinColumns = {
                    @JoinColumn(name = "dashboard_id", nullable = true) },
            inverseJoinColumns = { @JoinColumn(name = "id", nullable = true) })
    private Set<DashboardVisualization> visualizations;

    /** The portal. */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "portal_id", nullable = false)
    private Portal portal;

    /** The created by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",referencedColumnName = "user_id")
    private User createdBy;

    /** The updated by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by",referencedColumnName = "user_id")
    private User updatedBy;

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
    public Set<DashboardVisualization> getVisualizations() {
        return visualizations;
    }

    /**
     * Sets the visualizations.
     *
     * @param visualizations
     *            the new visualizations
     */
    public void setVisualizations(
            final Set<DashboardVisualization> visualizations) {
        this.visualizations = visualizations;
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
     * Gets the created by.
     *
     * @return the created by
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the new created by
     */
    public void setCreatedBy(final User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the updated by.
     *
     * @return the updated by
     */
    public User getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Sets the updated by.
     *
     * @param updatedBy the new updated by
     */
    public void setUpdatedBy(final User updatedBy) {
        this.updatedBy = updatedBy;
    }
}
