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
 * The type Portal dto.
 */
public class PortalDto implements Serializable {


    /** The portal id. */
    private int portalId;

    /** The str portal name. */
    private String strPortalName;

    /** The str portal title. */
    private String strPortalTitle;

    /** The str portal url. */
    private String strPortalUrl;

    /** The str portal logo. */
    private String strPortalLogo;

    /** The str portal css. */
    private String strPortalCss;

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

    /** The str portal logo location. */
    private String strPortalLogoLocation;

    /** The str portal logo file. */
    private byte[] strPortalLogoFile;

    /** The str portal css location. */
    private String strPortalCssLocation;

    /** The str portal css file. */
    private byte[] strPortalCssFile;

    /** The dashboards. */
    private List<DashboardDto> dashboards;

    /** The is portal view enabled. */
    private boolean isPortalViewEnabled;

    /** The in user id. */
    private int inUserId;
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
     * Gets str portal title.
     *
     * @return the str portal title
     */
    public String getStrPortalTitle() {
        return strPortalTitle;
    }

    /**
     * Sets str portal title.
     *
     * @param strPortalTitle the str portal title
     */
    public void setStrPortalTitle(final String strPortalTitle) {
        this.strPortalTitle = strPortalTitle;
    }

    /**
     * Gets str portal url.
     *
     * @return the str portal url
     */
    public String getStrPortalUrl() {
        return strPortalUrl;
    }

    /**
     * Sets str portal url.
     *
     * @param strPortalUrl the str portal url
     */
    public void setStrPortalUrl(final String strPortalUrl) {
        this.strPortalUrl = strPortalUrl;
    }

    /**
     * Gets str portal logo.
     *
     * @return the str portal logo
     */
    public String getStrPortalLogo() {
        return strPortalLogo;
    }

    /**
     * Sets str portal logo.
     *
     * @param strPortalLogo the str portal logo
     */
    public void setStrPortalLogo(final String strPortalLogo) {
        this.strPortalLogo = strPortalLogo;
    }

    /**
     * Gets str portal css.
     *
     * @return the str portal css
     */
    public String getStrPortalCss() {
        return strPortalCss;
    }

    /**
     * Sets str portal css.
     *
     * @param strPortalCss the str portal css
     */
    public void setStrPortalCss(final String strPortalCss) {
        this.strPortalCss = strPortalCss;
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
     * Gets dashboards.
     *
     * @return the dashboards
     */
    public List<DashboardDto> getDashboards() {
        return dashboards;
    }

    /**
     * Sets dashboards.
     *
     * @param dashboards the dashboards
     */
    public void setDashboards(final List<DashboardDto> dashboards) {
        this.dashboards = dashboards;
    }

    /**
     * Gets str portal logo location.
     *
     * @return the str portal logo location
     */
    public String getStrPortalLogoLocation() {
        return strPortalLogoLocation;
    }

    /**
     * Sets str portal logo location.
     *
     * @param strPortalLogoLocation the str portal logo location
     */
    public void setStrPortalLogoLocation(final String strPortalLogoLocation) {
        this.strPortalLogoLocation = strPortalLogoLocation;
    }

    /**
     * Get str portal logo file byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getStrPortalLogoFile() {
        return strPortalLogoFile;
    }

    /**
     * Sets str portal logo file.
     *
     * @param strPortalLogoFile the str portal logo file
     */
    public void setStrPortalLogoFile(final byte[] strPortalLogoFile) {
        this.strPortalLogoFile = strPortalLogoFile;
    }

    /**
     * Gets str portal css location.
     *
     * @return the str portal css location
     */
    public String getStrPortalCssLocation() {
        return strPortalCssLocation;
    }

    /**
     * Sets str portal css location.
     *
     * @param strPortalCssLocation the str portal css location
     */
    public void setStrPortalCssLocation(final String strPortalCssLocation) {
        this.strPortalCssLocation = strPortalCssLocation;
    }

    /**
     * Get str portal css file byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getStrPortalCssFile() {
        return strPortalCssFile;
    }

    /**
     * Sets str portal css file.
     *
     * @param strPortalCssFile the str portal css file
     */
    public void setStrPortalCssFile(final byte[] strPortalCssFile) {
        this.strPortalCssFile = strPortalCssFile;
    }

    /**
     * Is portal view enabled boolean.
     *
     * @return the boolean
     */
    public boolean isPortalViewEnabled() {
        return isPortalViewEnabled;
    }

    /**
     * Sets portal view enabled.
     *
     * @param portalViewEnabled the portal view enabled
     */
    public void setPortalViewEnabled(final boolean portalViewEnabled) {
        isPortalViewEnabled = portalViewEnabled;
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

