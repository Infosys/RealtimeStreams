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
 * The Class Portal.
 *
 * @version 1.0
 */
public class Portal implements Serializable {

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

    /** The dashboards. */
    private List<Dashboard> dashboards;

    /** The is portal view enabled. */
    private boolean isPortalViewEnabled;

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
     * Gets the str portal logo.
     *
     * @return the str portal logo
     */
    public String getStrPortalLogo() {
        return strPortalLogo;
    }

    /**
     * Sets the str portal logo.
     *
     * @param strPortalLogo
     *            the new str portal logo
     */
    public void setStrPortalLogo(final String strPortalLogo) {
        this.strPortalLogo = strPortalLogo;
    }

    /**
     * Gets the str portal css.
     *
     * @return the str portal css
     */
    public String getStrPortalCss() {
        return strPortalCss;
    }

    /**
     * Sets the str portal css.
     *
     * @param strPortalCss
     *            the new str portal css
     */
    public void setStrPortalCss(final String strPortalCss) {
        this.strPortalCss = strPortalCss;
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
     * Gets the dashboards.
     *
     * @return the dashboards
     */
    public List<Dashboard> getDashboards() {
        return dashboards;
    }

    /**
     * Sets the dashboards.
     *
     * @param dashboards
     *            the new dashboards
     */
    public void setDashboards(final List<Dashboard> dashboards) {
        this.dashboards = dashboards;
    }

    /**
     * Checks if is portal view enabled.
     *
     * @return true, if is portal view enabled
     */
    public boolean isPortalViewEnabled() {
        return isPortalViewEnabled;
    }

    /**
     * Sets the portal view enabled.
     *
     * @param portalViewEnabled
     *            the new portal view enabled
     */
    public void setPortalViewEnabled(final boolean portalViewEnabled) {
        isPortalViewEnabled = portalViewEnabled;
    }
}
