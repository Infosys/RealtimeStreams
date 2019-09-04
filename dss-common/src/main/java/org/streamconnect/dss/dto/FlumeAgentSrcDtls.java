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
 * The type Flume agent src dtls.
 * POJO class for flume agent source details
 *
 */
public class FlumeAgentSrcDtls implements java.io.Serializable {

    /**
     * Flume source connection type (host remote/local).
     */
    private String strSrcConnectType;

    /**
     * flume source host.
     */
    private String strSrcHost;

    /**
     * flume source host user.
     */
    private String strSrcUser;

    /**
     * flume source host pwd.
     */
    private String strSrcPwd;

    /**
     * flume source file path.
     */
    private String strSrcDataFilepath;
    //flume message read refresh count in case of log files
    //private int nLastReadCount;
    //flume source additional params map

    /**
     * Gets str src connect type.
     *
     * @return the str src connect type
     */
    public String getStrSrcConnectType() {
        return strSrcConnectType;
    }

    /**
     * Sets str src connect type.
     *
     * @param strSrcConnectType the str src connect type
     */
    public void setStrSrcConnectType(final String strSrcConnectType) {
        this.strSrcConnectType = strSrcConnectType;
    }

    /**
     * Gets str src host.
     *
     * @return the str src host
     */
    public String getStrSrcHost() {
        return strSrcHost;
    }

    /**
     * Sets str src host.
     *
     * @param strSrcHost the str src host
     */
    public void setStrSrcHost(final String strSrcHost) {
        this.strSrcHost = strSrcHost;
    }

    /**
     * Gets str src user.
     *
     * @return the str src user
     */
    public String getStrSrcUser() {
        return strSrcUser;
    }

    /**
     * Sets str src user.
     *
     * @param strSrcUser the str src user
     */
    public void setStrSrcUser(final String strSrcUser) {
        this.strSrcUser = strSrcUser;
    }

    /**
     * Gets str src pwd.
     *
     * @return the str src pwd
     */
    public String getStrSrcPwd() {
        return strSrcPwd;
    }

    /**
     * Sets str src pwd.
     *
     * @param strSrcPwd the str src pwd
     */
    public void setStrSrcPwd(final String strSrcPwd) {
        this.strSrcPwd = strSrcPwd;
    }

    /**
     * Gets str src data filepath.
     *
     * @return the str src data filepath
     */
    public String getStrSrcDataFilepath() {
        return strSrcDataFilepath;
    }

    /**
     * Sets str src data filepath.
     *
     * @param strSrcDataFilepath the str src data filepath
     */
    public void setStrSrcDataFilepath(final String strSrcDataFilepath) {
        this.strSrcDataFilepath = strSrcDataFilepath;
    }


    /**
     * Instantiates a new Flume agent src dtls.
     */
    public FlumeAgentSrcDtls() {
        super();
    }

}
