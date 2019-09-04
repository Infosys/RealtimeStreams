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
 * The type Flume agent dst dtls.
 * POJO class for storing flume agent sink details
 *
 */
public class FlumeAgentDstDtls implements java.io.Serializable {

    /**
     * flume agent sink host.
     */
    private String strDstHost;
    /**
     * flume agent sink port.
     */
    private long nPort;

    /**
     * Instantiates a new Flume agent dst dtls.
     */
    public FlumeAgentDstDtls() {
        super();
    }

    /**
     * Gets str dst host.
     *
     * @return the str dst host
     */
    public String getStrDstHost() {
        return strDstHost;
    }

    /**
     * Sets str dst host.
     *
     * @param strDstHost the str dst host
     */
    public void setStrDstHost(final String strDstHost) {
        this.strDstHost = strDstHost;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public long getnPort() {
        return nPort;
    }

    /**
     * Sets port.
     *
     * @param nPort the n port
     */
    public void setnPort(final long nPort) {
        this.nPort = nPort;
    }

}
