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

/**
 * The type Feature access dto.
 */
public class FeatureAccessDto implements Serializable {

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
     * Gets in feature access id.
     *
     * @return the in feature access id
     */
    public int getInFeatureAccessId() {
        return inFeatureAccessId;
    }

    /**
     * Sets in feature access id.
     *
     * @param inFeatureAccessId the in feature access id
     */
    public void setInFeatureAccessId(final int inFeatureAccessId) {
        this.inFeatureAccessId = inFeatureAccessId;
    }

    /**
     * Gets in feature id.
     *
     * @return the in feature id
     */
    public int getInFeatureId() {
        return inFeatureId;
    }

    /**
     * Sets in feature id.
     *
     * @param inFeatureId the in feature id
     */
    public void setInFeatureId(final int inFeatureId) {
        this.inFeatureId = inFeatureId;
    }

    /**
     * Gets str feature name.
     *
     * @return the str feature name
     */
    public String getStrFeatureName() {
        return strFeatureName;
    }

    /**
     * Sets str feature name.
     *
     * @param strFeatureName the str feature name
     */
    public void setStrFeatureName(final String strFeatureName) {
        this.strFeatureName = strFeatureName;
    }

    /**
     * Is feature view enabled boolean.
     *
     * @return the boolean
     */
    public boolean isFeatureViewEnabled() {
        return featureViewEnabled;
    }

    /**
     * Sets feature view enabled.
     *
     * @param featureViewEnabled the feature view enabled
     */
    public void setFeatureViewEnabled(final boolean featureViewEnabled) {
        this.featureViewEnabled = featureViewEnabled;
    }

    /**
     * Is feature edit enabled boolean.
     *
     * @return the boolean
     */
    public boolean isFeatureEditEnabled() {
        return featureEditEnabled;
    }

    /**
     * Sets feature edit enabled.
     *
     * @param featureEditEnabled the feature edit enabled
     */
    public void setFeatureEditEnabled(final boolean featureEditEnabled) {
        this.featureEditEnabled = featureEditEnabled;
    }

    /**
     * Is feature delete enabled boolean.
     *
     * @return the boolean
     */
    public boolean isFeatureDeleteEnabled() {
        return featureDeleteEnabled;
    }

    /**
     * Sets feature delete enabled.
     *
     * @param featureDeleteEnabled the feature delete enabled
     */
    public void setFeatureDeleteEnabled(final boolean featureDeleteEnabled) {
        this.featureDeleteEnabled = featureDeleteEnabled;
    }
}
