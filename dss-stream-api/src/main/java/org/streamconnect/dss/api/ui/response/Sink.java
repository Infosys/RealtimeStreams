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
 * The Class Sink.
 *
 * @version 1.0
 */
public class Sink implements java.io.Serializable {

    /** The in sink id. */
    private int inSinkId;

    /** The str sink name. */
    private String strSinkName;

    /** The str sink type. */
    private String strSinkType;

    /** The obj sink config details. */
    private Object objSinkConfigDetails;

    /** The str date sink. */
    private String strDateSink;

    /**
     * Instantiates a new sink.
     */
    public Sink() {
        super();
    }

    /**
     * Gets the in sink id.
     *
     * @return the inSinkId
     */
    public int getInSinkId() {
        return inSinkId;
    }

    /**
     * Sets the in sink id.
     *
     * @param inSinkId
     *            the inSinkId to set
     */
    public void setInSinkId(final int inSinkId) {
        this.inSinkId = inSinkId;
    }

    /**
     * Gets the str sink name.
     *
     * @return the strSinkName
     */
    public String getStrSinkName() {
        return strSinkName;
    }

    /**
     * Sets the str sink name.
     *
     * @param strSinkName
     *            the strSinkName to set
     */
    public void setStrSinkName(final String strSinkName) {
        this.strSinkName = strSinkName;
    }

    /**
     * Gets the str sink type.
     *
     * @return the strSinkType
     */
    public String getStrSinkType() {
        return strSinkType;
    }

    /**
     * Sets the str sink type.
     *
     * @param strSinkType
     *            the strSinkType to set
     */
    public void setStrSinkType(final String strSinkType) {
        this.strSinkType = strSinkType;
    }

    /**
     * Gets the obj sink config details.
     *
     * @return the objSinkConfigDetails
     */
    public Object getObjSinkConfigDetails() {
        return objSinkConfigDetails;
    }

    /**
     * Sets the obj sink config details.
     *
     * @param objSinkConfigDetails
     *            the objSinkConfigDetails to set
     */
    public void setObjSinkConfigDetails(final Object objSinkConfigDetails) {
        this.objSinkConfigDetails = objSinkConfigDetails;
    }

    /**
     * Gets the str date sink.
     *
     * @return the str date sink
     */
    public String getStrDateSink() {
        return strDateSink;
    }

    /**
     * Sets the str date sink.
     *
     * @param strDateSink
     *            the new str date sink
     */
    public void setStrDateSink(final String strDateSink) {
        this.strDateSink = strDateSink;
    }
}
