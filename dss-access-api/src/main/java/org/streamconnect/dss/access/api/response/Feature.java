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

/**
 * The Class Feature.
 *
 * @version 1.0
 */
public class Feature {

    /** The in feature id. */
    private int inFeatureId;

    /** The str feature name. */
    private String strFeatureName;

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
}
