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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

/**
 * The Class Kpi.
 *
 * @version 1.0
 */
@Table(name = "tbl_kpi")
@Entity
public class Kpi implements java.io.Serializable {

    /** The in kpi id. */
    @Id
    @GeneratedValue
    @Column(name = "kpi_id")
    private int inKpiId;

    /** The str kpi name. */
    @Column(name = "kpi_name")
    private String strKpiName;

    /** The visualizes. */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "kpi_visualize",
            joinColumns = { @JoinColumn(name = "kpi_id", nullable = true) },
            inverseJoinColumns = {
                    @JoinColumn(name = "visualize_id", nullable = true) })
    private Set<Visualize> visualizes;

    /** The date kpi. */
    @Column(name = "kpi_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateKpi;

    /** The delete status. */
    @Column(name = "kpi_delete_status")
    private int deleteStatus;

    /** The categories. */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "kpi_catry",
            joinColumns = { @JoinColumn(name = "kpi_id", nullable = true) },
            inverseJoinColumns = {
                    @JoinColumn(name = "catry_id", nullable = true) })
    private Set<Category> categories;

    /** The str kpi desc. */
    @Column(name = "kpi_description")
    private String strKpiDesc;

    /** The date updated kpi. */
    @Column(name = "kpi_date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdatedKpi;

    /** The created by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",referencedColumnName = "user_id")
    private User createdBy;

    /** The updated by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by",referencedColumnName = "user_id")
    private User updatedBy;

    /**
     * Instantiates a new kpi.
     */
    public Kpi() {
        super();
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
     * Gets the visualizes.
     *
     * @return the visualizes
     */
    public Set<Visualize> getVisualizes() {
        return visualizes;
    }

    /**
     * Sets the visualizes.
     *
     * @param visualizes
     *            the new visualizes
     */
    public void setVisualizes(final Set<Visualize> visualizes) {
        this.visualizes = visualizes;
    }

    /**
     * Gets the date kpi.
     *
     * @return the date kpi
     */
    public Date getDateKpi() {
        return dateKpi;
    }

    /**
     * Sets the date kpi.
     *
     * @param dateKpi
     *            the new date kpi
     */
    public void setDateKpi(final Date dateKpi) {
        this.dateKpi = dateKpi;
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
     * Gets the categories.
     *
     * @return the categories
     */
    public Set<Category> getCategories() {
        return categories;
    }

    /**
     * Sets the categories.
     *
     * @param categories
     *            the new categories
     */
    public void setCategories(final Set<Category> categories) {
        this.categories = categories;
    }

    /**
     * Gets the str kpi desc.
     *
     * @return the str kpi desc
     */
    public String getStrKpiDesc() {
        return strKpiDesc;
    }

    /**
     * Sets the str kpi desc.
     *
     * @param strKpiDesc
     *            the new str kpi desc
     */
    public void setStrKpiDesc(final String strKpiDesc) {
        this.strKpiDesc = strKpiDesc;
    }

    /**
     * Gets the date updated kpi.
     *
     * @return the date updated kpi
     */
    public Date getDateUpdatedKpi() {
        return dateUpdatedKpi;
    }

    /**
     * Sets the date updated kpi.
     *
     * @param dateUpdatedKpi
     *            the new date updated kpi
     */
    public void setDateUpdatedKpi(final Date dateUpdatedKpi) {
        this.dateUpdatedKpi = dateUpdatedKpi;
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
