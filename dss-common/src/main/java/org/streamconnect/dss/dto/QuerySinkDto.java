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


/**
 * The type Query sink dto.
 */
public class QuerySinkDto implements java.io.Serializable {


    /** The sink config ID. */
    private int sinkConfigID;

    /** The str persit to. */
    private String strPersitTo;

    /** The persit config. */
    private Object persitConfig;

    /**
     * Gets sink config id.
     *
     * @return the sink config id
     */
    public int getSinkConfigID() {
        return sinkConfigID;
    }

    /**
     * Sets sink config id.
     *
     * @param sinkConfigID the sink config id
     */
    public void setSinkConfigID(final int sinkConfigID) {
        this.sinkConfigID = sinkConfigID;
    }

    /**
     * Gets str persit to.
     *
     * @return the str persit to
     */
    public String getStrPersitTo() {
        return strPersitTo;
    }

    /**
     * Sets str persit to.
     *
     * @param strPersitTo the str persit to
     */
    public void setStrPersitTo(final String strPersitTo) {
        this.strPersitTo = strPersitTo;
    }

    /**
     * Gets persit config.
     *
     * @return the persit config
     */
    public Object getPersitConfig() {
        return persitConfig;
    }

    /**
     * Sets persit config.
     *
     * @param persitConfig the persit config
     */
    public void setPersitConfig(final Object persitConfig) {
        this.persitConfig = persitConfig;
    }
}
