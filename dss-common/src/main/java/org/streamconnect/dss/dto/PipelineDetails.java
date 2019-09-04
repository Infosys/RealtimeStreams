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
 * The type Pipeline details.
 */
public class PipelineDetails implements Serializable {


    /** The str pipeline U rl. */
    private String strPipelineURl;

    /** The str pipeline conf file. */
    private String strPipelineConfFile;

    /** The str pipeline prop file. */
    private String strPipelinePropFile;

    /**
     * Gets str pipeline u rl.
     *
     * @return the str pipeline u rl
     */
    public String getStrPipelineURl() {
        return strPipelineURl;
    }

    /**
     * Sets str pipeline u rl.
     *
     * @param strPipelineURl the str pipeline u rl
     */
    public void setStrPipelineURl(final String strPipelineURl) {
        this.strPipelineURl = strPipelineURl;
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

}
