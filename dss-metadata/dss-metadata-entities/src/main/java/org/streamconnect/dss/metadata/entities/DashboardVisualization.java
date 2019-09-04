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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class DashboardVisualization.
 *
 * @version 1.0
 */
@Table(name = "tbl_dashboard_visualize_details")
@Entity
public class DashboardVisualization implements Serializable {

    /** The id. */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    /** The category. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catry_id")
    private Category category;

    /** The kpi. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kpi_id")
    private Kpi kpi;

    /** The visualize. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visualize_id")
    private Visualize visualize;

    /** The dashboards. */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "visualizations")
    private Set<Dashboard> dashboards = new HashSet<Dashboard>();

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(final int id) {
        this.id = id;
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
     * Gets the dashboards.
     *
     * @return the dashboards
     */
    public Set<Dashboard> getDashboards() {
        return dashboards;
    }

    /**
     * Sets the dashboards.
     *
     * @param dashboards
     *            the new dashboards
     */
    public void setDashboards(final Set<Dashboard> dashboards) {
        this.dashboards = dashboards;
    }
}
