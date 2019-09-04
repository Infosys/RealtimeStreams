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
 * The type Pipeline execution dto.
 */
public class PipelineExecutionDto implements Serializable {

    /** The in pipeline id. */
    private int inPipelineId;

    /** The in exec pipeline U rl. */
    private String inExecPipelineURl;

    /** The in exec pipeline id. */
    private String inExecPipelineId;

    /** The obj exec details. */
    private Object objExecDetails;

    /** The str pipeline conf file. */
    private String strPipelineConfFile;

    /** The str pipeline prop file. */
    private String strPipelinePropFile;

    /** The str source type. */
    private String strSourceType;

    /** The str process type. */
    private String strProcessType;

    /** The str sink type. */
    private String strSinkType;

    /**
     * Gets in pipeline id.
     *
     * @return the in pipeline id
     */
    public int getInPipelineId() {
        return inPipelineId;
    }

    /**
     * Sets in pipeline id.
     *
     * @param inPipelineId the in pipeline id
     */
    public void setInPipelineId(final int inPipelineId) {
        this.inPipelineId = inPipelineId;
    }

    /**
     * Gets in exec pipeline u rl.
     *
     * @return the in exec pipeline u rl
     */
    public String getInExecPipelineURl() {
        return inExecPipelineURl;
    }

    /**
     * Sets in exec pipeline u rl.
     *
     * @param inExecPipelineURl the in exec pipeline u rl
     */
    public void setInExecPipelineURl(final String inExecPipelineURl) {
        this.inExecPipelineURl = inExecPipelineURl;
    }

    /**
     * Gets in exec pipeline id.
     *
     * @return the in exec pipeline id
     */
    public String getInExecPipelineId() {
        return inExecPipelineId;
    }

    /**
     * Sets in exec pipeline id.
     *
     * @param inExecPipelineId the in exec pipeline id
     */
    public void setInExecPipelineId(final String inExecPipelineId) {
        this.inExecPipelineId = inExecPipelineId;
    }

    /**
     * Gets obj exec details.
     *
     * @return the obj exec details
     */
    public Object getObjExecDetails() {
        return objExecDetails;
    }

    /**
     * Sets obj exec details.
     *
     * @param objExecDetails the obj exec details
     */
    public void setObjExecDetails(final Object objExecDetails) {
        this.objExecDetails = objExecDetails;
    }

    /**
     * Gets str pipeline conf file.
     *
     * @return the str pipeline conf file
     */
    public String getStrPipelineConfFile() {
        return strPipelineConfFile;
    }

    /**
     * Sets str pipeline conf file.
     *
     * @param strPipelineConfFile the str pipeline conf file
     */
    public void setStrPipelineConfFile(final String strPipelineConfFile) {
        this.strPipelineConfFile = strPipelineConfFile;
    }

    /**
     * Gets str pipeline prop file.
     *
     * @return the str pipeline prop file
     */
    public String getStrPipelinePropFile() {
        return strPipelinePropFile;
    }

    /**
     * Sets str pipeline prop file.
     *
     * @param strPipelinePropFile the str pipeline prop file
     */
    public void setStrPipelinePropFile(final String strPipelinePropFile) {
        this.strPipelinePropFile = strPipelinePropFile;
    }

    /**
     * Gets str source type.
     *
     * @return the str source type
     */
    public String getStrSourceType() {
        return strSourceType;
    }

    /**
     * Sets str source type.
     *
     * @param strSourceType the str source type
     */
    public void setStrSourceType(final String strSourceType) {
        this.strSourceType = strSourceType;
    }

    /**
     * Gets str process type.
     *
     * @return the str process type
     */
    public String getStrProcessType() {
        return strProcessType;
    }

    /**
     * Sets str process type.
     *
     * @param strProcessType the str process type
     */
    public void setStrProcessType(final String strProcessType) {
        this.strProcessType = strProcessType;
    }

    /**
     * Gets str sink type.
     *
     * @return the str sink type
     */
    public String getStrSinkType() {
        return strSinkType;
    }

    /**
     * Sets str sink type.
     *
     * @param strSinkType the str sink type
     */
    public void setStrSinkType(final String strSinkType) {
        this.strSinkType = strSinkType;
    }
}
