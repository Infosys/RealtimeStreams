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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Category.
 */
@Table(name = "tbl_category")
@Entity
public class Category implements Serializable {

    /** The in category id. */
    @Id
    @GeneratedValue
    @Column(name = "catry_id")
    private int inCategoryId;

    /** The str category name. */
    @Column(name = "catry_name")
    private String strCategoryName;

    /** The str category desc. */
    @Column(name = "catry_desc")
    private String strCategoryDesc;

    /** The kpis. */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private Set<Kpi> kpis = new HashSet<Kpi>(0);;

    /** The date category. */
    @Column(name = "catry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCategory;

    /** The delete status. */
    @Column(name = "catry_delete_status")
    private int deleteStatus;

    /** The date from. */
    @Column(name = "catry_from_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFrom;

    /** The date to. */
    @Column(name = "catry_to_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTo;

    /** The date updated catry. */
    @Column(name = "catry_date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdatedCatry;

    /** The created by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",referencedColumnName = "user_id")
    private User createdBy;

    /** The updated by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by",referencedColumnName = "user_id")
    private User updatedBy;

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
     * Gets the kpis.
     *
     * @return the kpis
     */
    public Set<Kpi> getKpis() {
        return kpis;
    }

    /**
     * Sets the kpis.
     *
     * @param kpis
     *            the new kpis
     */
    public void setKpis(final Set<Kpi> kpis) {
        this.kpis = kpis;
    }

    /**
     * Gets the date category.
     *
     * @return the date category
     */
    public Date getDateCategory() {
        return dateCategory;
    }

    /**
     * Sets the date category.
     *
     * @param dateCategory
     *            the new date category
     */
    public void setDateCategory(final Date dateCategory) {
        this.dateCategory = dateCategory;
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
     * Gets the date updated catry.
     *
     * @return the date updated catry
     */
    public Date getDateUpdatedCatry() {
        return dateUpdatedCatry;
    }

    /**
     * Sets the date updated catry.
     *
     * @param dateUpdatedCatry
     *            the new date updated catry
     */
    public void setDateUpdatedCatry(final Date dateUpdatedCatry) {
        this.dateUpdatedCatry = dateUpdatedCatry;
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
