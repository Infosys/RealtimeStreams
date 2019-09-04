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
 * The Class FeatureAccess.
 *
 * @version 1.0
 */
public class FeatureAccess implements Serializable {

    /** The in feature access id. */
    private int inFeatureAccessId;

    /** The in feature id. */
    private int inFeatureId;

    /** The str feature name. */
    private String strFeatureName;

    /** The feature view enabled. */
    private boolean featureViewEnabled;

    /** The feature edit enabled. */
    private boolean featureEditEnabled;

    /** The feature delete enabled. */
    private boolean featureDeleteEnabled;

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
     * Gets the in feature id.
     *
     * @return the in feature id
     */
    public int getInFeatureId() {
        return inFeatureId;
    }

    /**
     * Sets the in feature id.
     *
     * @param inFeatureId
     *            the new in feature id
     */
    public void setInFeatureId(final int inFeatureId) {
        this.inFeatureId = inFeatureId;
    }

    /**
     * Gets the str feature name.
     *
     * @return the str feature name
     */
    public String getStrFeatureName() {
        return strFeatureName;
    }

    /**
     * Sets the str feature name.
     *
     * @param strFeatureName
     *            the new str feature name
     */
    public void setStrFeatureName(final String strFeatureName) {
        this.strFeatureName = strFeatureName;
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
}
