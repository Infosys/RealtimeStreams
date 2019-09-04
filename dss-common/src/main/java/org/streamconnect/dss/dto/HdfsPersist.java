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
 * The type Hdfs persist.
 */

public class HdfsPersist implements java.io.Serializable {

    /** The str file path. */
    private String strFilePath;

    /** The str file type. */
    private String strFileType;

    /**
     * Instantiates a new Hdfs persist.
     */
    public HdfsPersist() {
        super();
    }

    /**
     * Gets str file path.
     *
     * @return the str file path
     */
    public String getStrFilePath() {
        return strFilePath;
    }

    /**
     * Sets str file path.
     *
     * @param strFilePath the str file path
     */
    public void setStrFilePath(final String strFilePath) {
        this.strFilePath = strFilePath;
    }

    /**
     * Gets str file type.
     *
     * @return the str file type
     */
    public String getStrFileType() {
        return strFileType;
    }

    /**
     * Sets str file type.
     *
     * @param strFileType the str file type
     */
    public void setStrFileType(final String strFileType) {
        this.strFileType = strFileType;
    }

}
