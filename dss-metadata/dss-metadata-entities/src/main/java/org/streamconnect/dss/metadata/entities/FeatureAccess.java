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
 * The Class FeatureAccess.
 *
 * @version 1.0
 */
@Table(name = "tbl_feature_access")
@Entity
public class FeatureAccess {

    /** The in feature access id. */
    @Id
    @GeneratedValue
    @Column(name = "feature_access_id")
    private int inFeatureAccessId;

    /** The feature. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ft_id")
    private Feature feature;

    /** The feature view enabled. */
    @Column(name = "feature_view")
    private boolean featureViewEnabled;

    /** The feature edit enabled. */
    @Column(name = "feature_edit")
    private boolean featureEditEnabled;

    /** The feature delete enabled. */
    @Column(name = "feature_delete")
    private boolean featureDeleteEnabled;

    /** The access level. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "access_id")
    private AccessLevel accessLevel;

    /**
     * Gets the in feature access id.
     *
     * @return the in feature access id
     */
    public int getInFeatureAccessId() {
        return inFeatureAccessId;
    }

    /**
     * Sets the in feature access id.
     *
     * @param inFeatureAccessId
     *            the new in feature access id
     */
    public void setInFeatureAccessId(final int inFeatureAccessId) {
        this.inFeatureAccessId = inFeatureAccessId;
    }

    /**
     * Gets the feature.
     *
     * @return the feature
     */
    public Feature getFeature() {
        return feature;
    }

    /**
     * Sets the feature.
     *
     * @param feature
     *            the new feature
     */
    public void setFeature(final Feature feature) {
        this.feature = feature;
    }

    /**
     * Checks if is feature view enabled.
     *
     * @return true, if is feature view enabled
     */
    public boolean isFeatureViewEnabled() {
        return featureViewEnabled;
    }

    /**
     * Sets the feature view enabled.
     *
     * @param featureViewEnabled
     *            the new feature view enabled
     */
    public void setFeatureViewEnabled(final boolean featureViewEnabled) {
        this.featureViewEnabled = featureViewEnabled;
    }

    /**
     * Checks if is feature edit enabled.
     *
     * @return true, if is feature edit enabled
     */
    public boolean isFeatureEditEnabled() {
        return featureEditEnabled;
    }

    /**
     * Sets the feature edit enabled.
     *
     * @param featureEditEnabled
     *            the new feature edit enabled
     */
    public void setFeatureEditEnabled(final boolean featureEditEnabled) {
        this.featureEditEnabled = featureEditEnabled;
    }

    /**
     * Checks if is feature delete enabled.
     *
     * @return true, if is feature delete enabled
     */
    public boolean isFeatureDeleteEnabled() {
        return featureDeleteEnabled;
    }

    /**
     * Sets the feature delete enabled.
     *
     * @param featureDeleteEnabled
     *            the new feature delete enabled
     */
    public void setFeatureDeleteEnabled(final boolean featureDeleteEnabled) {
        this.featureDeleteEnabled = featureDeleteEnabled;
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
}
