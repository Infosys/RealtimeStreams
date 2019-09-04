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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Portal.
 *
 * @version 1.0
 */
@Table(name = "tbl_portal")
@Entity
public class Portal implements Serializable {

    /** The portal id. */
    @Id
    @GeneratedValue
    @Column(name = "portal_id")
    private int portalId;

    /** The str portal name. */
    @Column(name = "portal_name")
    private String strPortalName;

    /** The str portal title. */
    @Column(name = "portal_title")
    private String strPortalTitle;

    /** The str portal url. */
    @Column(name = "portal_url")
    private String strPortalUrl;

    /** The str portal logo location. */
    @Column(name = "portal_logo_location")
    private String strPortalLogoLocation;

    /** The str portal logo name. */
    @Column(name = "portal_logo_name")
    private String strPortalLogoName;

    /** The str portal logo. */
    @Lob
    @Column(name = "portal_logo")
    private byte[] strPortalLogo;

    /** The str portal css location. */
    @Column(name = "portal_css_location")
    private String strPortalCssLocation;

    /** The str portal css name. */
    @Column(name = "portal_css_name")
    private String strPortalCssName;

    /** The str portal css. */
    @Lob
    @Column(name = "portal_css")
    private byte[] strPortalCss;

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

    /** The dashboards. */
    @OneToMany(
            fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "portal")
    private Set<Dashboard> dashboards;

    /** The created by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",referencedColumnName = "user_id")
    private User createdBy;

    /** The updated by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by",referencedColumnName = "user_id")
    private User updatedBy;

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
     * Gets the str portal title.
     *
     * @return the str portal title
     */
    public String getStrPortalTitle() {
        return strPortalTitle;
    }

    /**
     * Sets the str portal title.
     *
     * @param strPortalTitle
     *            the new str portal title
     */
    public void setStrPortalTitle(final String strPortalTitle) {
        this.strPortalTitle = strPortalTitle;
    }

    /**
     * Gets the str portal url.
     *
     * @return the str portal url
     */
    public String getStrPortalUrl() {
        return strPortalUrl;
    }

    /**
     * Sets the str portal url.
     *
     * @param strPortalUrl
     *            the new str portal url
     */
    public void setStrPortalUrl(final String strPortalUrl) {
        this.strPortalUrl = strPortalUrl;
    }

    /**
     * Gets the str portal logo location.
     *
     * @return the str portal logo location
     */
    public String getStrPortalLogoLocation() {
        return strPortalLogoLocation;
    }

    /**
     * Sets the str portal logo location.
     *
     * @param strPortalLogoLocation
     *            the new str portal logo location
     */
    public void setStrPortalLogoLocation(final String strPortalLogoLocation) {
        this.strPortalLogoLocation = strPortalLogoLocation;
    }

    /**
     * Gets the str portal logo name.
     *
     * @return the str portal logo name
     */
    public String getStrPortalLogoName() {
        return strPortalLogoName;
    }

    /**
     * Sets the str portal logo name.
     *
     * @param strPortalLogoName
     *            the new str portal logo name
     */
    public void setStrPortalLogoName(final String strPortalLogoName) {
        this.strPortalLogoName = strPortalLogoName;
    }

    /**
     * Gets the str portal logo.
     *
     * @return the str portal logo
     */
    public byte[] getStrPortalLogo() {
        return strPortalLogo;
    }

    /**
     * Sets the str portal logo.
     *
     * @param strPortalLogo
     *            the new str portal logo
     */
    public void setStrPortalLogo(final byte[] strPortalLogo) {
        this.strPortalLogo = strPortalLogo;
    }

    /**
     * Gets the str portal css location.
     *
     * @return the str portal css location
     */
    public String getStrPortalCssLocation() {
        return strPortalCssLocation;
    }

    /**
     * Sets the str portal css location.
     *
     * @param strPortalCssLocation
     *            the new str portal css location
     */
    public void setStrPortalCssLocation(final String strPortalCssLocation) {
        this.strPortalCssLocation = strPortalCssLocation;
    }

    /**
     * Gets the str portal css name.
     *
     * @return the str portal css name
     */
    public String getStrPortalCssName() {
        return strPortalCssName;
    }

    /**
     * Sets the str portal css name.
     *
     * @param strPortalCssName
     *            the new str portal css name
     */
    public void setStrPortalCssName(final String strPortalCssName) {
        this.strPortalCssName = strPortalCssName;
    }

    /**
     * Gets the str portal css.
     *
     * @return the str portal css
     */
    public byte[] getStrPortalCss() {
        return strPortalCss;
    }

    /**
     * Sets the str portal css.
     *
     * @param strPortalCss
     *            the new str portal css
     */
    public void setStrPortalCss(final byte[] strPortalCss) {
        this.strPortalCss = strPortalCss;
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
