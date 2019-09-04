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
 * The type Flume stream src dto.
 *POJO class for storing flume source and agent details
 */

public class FlumeStreamSrcDto implements java.io.Serializable {

    /** flume agent source details. */
    private Object objFlumeAgentSrcDtls;

    /** flume agent sink details. */

    private Object objFlumeAgentDstDtls;


    /**
     * Gets obj flume agent src dtls.
     *
     * @return the obj flume agent src dtls
     */
    public Object getObjFlumeAgentSrcDtls() {
        return objFlumeAgentSrcDtls;
    }

    /**
     * Sets flume agent src dtls.
     *
     * @param objFlumeAgentSrcDtls the obj flume agent src dtls
     */
    public void setobjFlumeAgentSrcDtls(final Object objFlumeAgentSrcDtls) {
        this.objFlumeAgentSrcDtls = objFlumeAgentSrcDtls;
    }

    /**
     * Gets obj flume agent dst dtls.
     *
     * @return the obj flume agent dst dtls
     */
    public Object getObjFlumeAgentDstDtls() {
        return objFlumeAgentDstDtls;
    }

    /**
     * Sets obj flume agent dst dtls.
     *
     * @param objFlumeAgentDstDtls the obj flume agent dst dtls
     */
    public void setObjFlumeAgentDstDtls(final Object objFlumeAgentDstDtls) {
        this.objFlumeAgentDstDtls = objFlumeAgentDstDtls;
    }


    /**
     * Instantiates a new Flume stream src dto.
     */
    public FlumeStreamSrcDto() {
        super();
    }

}
