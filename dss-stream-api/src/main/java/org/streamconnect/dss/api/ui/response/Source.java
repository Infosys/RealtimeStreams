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

/**
 * The Class Source.
 *
 * @version 1.0
 */
public class Source implements java.io.Serializable {

    /** The in source id. */
    private int inSourceId;

    /** The str source config name. */
    private String strSourceConfigName;

    /** The str source type. */
    private String strSourceType;

    /** The obj source config details. */
    private Object objSourceConfigDetails;

    /** The str date source. */
    private String strDateSource;

    /**
     * Instantiates a new source.
     */
    public Source() {
        super();
    }

    /**
     * Gets the in source id.
     *
     * @return the in source id
     */
    public int getInSourceId() {
        return inSourceId;
    }

    /**
     * Sets the in source id.
     *
     * @param inSourceId
     *            the new in source id
     */
    public void setInSourceId(final int inSourceId) {
        this.inSourceId = inSourceId;
    }

    /**
     * Gets the str source config name.
     *
     * @return the str source config name
     */
    public String getStrSourceConfigName() {
        return strSourceConfigName;
    }

    /**
     * Sets the str source config name.
     *
     * @param strSourceConfigName
     *            the new str source config name
     */
    public void setStrSourceConfigName(final String strSourceConfigName) {
        this.strSourceConfigName = strSourceConfigName;
    }

    /**
     * Gets the str source type.
     *
     * @return the str source type
     */
    public String getStrSourceType() {
        return strSourceType;
    }

    /**
     * Sets the str source type.
     *
     * @param strSourceType
     *            the new str source type
     */
    public void setStrSourceType(final String strSourceType) {
        this.strSourceType = strSourceType;
    }

    /**
     * Gets the obj source config details.
     *
     * @return the obj source config details
     */
    public Object getObjSourceConfigDetails() {
        return objSourceConfigDetails;
    }

    /**
     * Sets the obj source config details.
     *
     * @param objSourceConfigDetails
     *            the new obj source config details
     */
    public void setObjSourceConfigDetails(final Object objSourceConfigDetails) {
        this.objSourceConfigDetails = objSourceConfigDetails;
    }

    /**
     * Gets the str date source.
     *
     * @return the str date source
     */
    public String getStrDateSource() {
        return strDateSource;
    }

    /**
     * Sets the str date source.
     *
     * @param strDateSource
     *            the new str date source
     */
    public void setStrDateSource(final String strDateSource) {
        this.strDateSource = strDateSource;
    }
}
