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
 * The type Visualization dto.
 */
public class VisualizationDto implements Serializable {

    /** The in V entity id. */
    private int inVEntityId;

    /** The str V entity name. */
    private String strVEntityName;

    /** The str parent type. */
    private String strParentType;

    /** The str type. */
    private String strType;

    /** The obj V entity details. */
    private Object objVEntityDetails;

    /**
     * Instantiates a new Visualization dto.
     */
    public VisualizationDto() {
        super();
    }

    /**
     * Gets in v entity id.
     *
     * @return the in v entity id
     */
    public int getInVEntityId() {
        return inVEntityId;
    }

    /**
     * Sets in v entity id.
     */
    public void setInVEntityId() {
        setInVEntityId();
    }

    /**
     * Sets in v entity id.
     *
     * @param inVEntityId the in v entity id
     */
    public void setInVEntityId(final int inVEntityId) {
        this.inVEntityId = inVEntityId;
    }

    /**
     * Gets str v entity name.
     *
     * @return the str v entity name
     */
    public String getStrVEntityName() {
        return strVEntityName;
    }

    /**
     * Sets str v entity name.
     *
     * @param strVEntityName the str v entity name
     */
    public void setStrVEntityName(final String strVEntityName) {
        this.strVEntityName = strVEntityName;
    }

    /**
     * Gets str parent type.
     *
     * @return the str parent type
     */
    public String getStrParentType() {
        return strParentType;
    }

    /**
     * Sets str parent type.
     *
     * @param strParentType the str parent type
     */
    public void setStrParentType(final String strParentType) {
        this.strParentType = strParentType;
    }

    /**
     * Gets str type.
     *
     * @return the str type
     */
    public String getStrType() {
        return strType;
    }

    /**
     * Sets str type.
     *
     * @param strType the str type
     */
    public void setStrType(final String strType) {
        this.strType = strType;
    }

    /**
     * Gets obj v entity details.
     *
     * @return the obj v entity details
     */
    public Object getObjVEntityDetails() {
        return objVEntityDetails;
    }

    /**
     * Sets obj v entity details.
     *
     * @param objVEntityDetails the obj v entity details
     */
    public void setObjVEntityDetails(final Object objVEntityDetails) {
        this.objVEntityDetails = objVEntityDetails;
    }
}


