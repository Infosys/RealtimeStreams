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
package org.streamconnect.dss.engine.prefab;

/**
 * The Class InterpreterType.
 */
public class InterpreterType implements java.io.Serializable {

    /** The str source type. */
    private String strSourceType;

    /** The str process type. */
    private String strProcessType;

    /** The str sink type. */
    private String strSinkType;

    /** The str data type. */
    private String strDataType;

    /** The str lookup type. */
    private String strLookupType;

    /** The str algorithm type. */
    private String strAlgorithmType;

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
     * @param strSourceType the new str source type
     */
    public void setStrSourceType(final String strSourceType) {
        this.strSourceType = strSourceType;
    }

    /**
     * Gets the str process type.
     *
     * @return the str process type
     */
    public String getStrProcessType() {
        return strProcessType;
    }

    /**
     * Sets the str process type.
     *
     * @param strProcessType the new str process type
     */
    public void setStrProcessType(final String strProcessType) {
        this.strProcessType = strProcessType;
    }

    /**
     * Gets the str sink type.
     *
     * @return the str sink type
     */
    public String getStrSinkType() {
        return strSinkType;
    }

    /**
     * Sets the str sink type.
     *
     * @param strSinkType the new str sink type
     */
    public void setStrSinkType(final String strSinkType) {
        this.strSinkType = strSinkType;
    }

    /**
     * Gets the str data type.
     *
     * @return the str data type
     */
    public String getStrDataType() {
        return strDataType;
    }

    /**
     * Sets the str data type.
     *
     * @param strDataType the new str data type
     */
    public void setStrDataType(final String strDataType) {
        this.strDataType = strDataType;
    }

    /**
     * Gets the str lookup type.
     *
     * @return the str lookup type
     */
    public String getStrLookupType() {
        return strLookupType;
    }

    /**
     * Sets the str lookup type.
     *
     * @param strLookupType the new str lookup type
     */
    public void setStrLookupType(final String strLookupType) {
        this.strLookupType = strLookupType;
    }

    /**
     * Gets the str algorithm type.
     *
     * @return the str algorithm type
     */
    public String getStrAlgorithmType() {
        return strAlgorithmType;
    }

    /**
     * Sets the str algorithm type.
     *
     * @param strAlgorithmType the new str algorithm type
     */
    public void setStrAlgorithmType(final String strAlgorithmType) {
        this.strAlgorithmType = strAlgorithmType;
    }

    /**
     * Instantiates a new interpreter type.
     */
    public InterpreterType() {
        super();
    }

}


