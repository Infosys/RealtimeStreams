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

import java.io.Serializable;

/**
 * The Class PipelineExecution.
 *
 * @version 1.0
 */
public class PipelineExecution implements Serializable {

    /** The in pipeline id. */
    private int inPipelineId;

    /** The in exec pipeline id. */
    private String inExecPipelineId;

    /** The str exec pipeline status. */
    private String strExecPipelineStatus;

    /**
     * Gets the in pipeline id.
     *
     * @return the in pipeline id
     */
    public int getInPipelineId() {
        return inPipelineId;
    }

    /**
     * Sets the in pipeline id.
     *
     * @param inPipelineId
     *            the new in pipeline id
     */
    public void setInPipelineId(final int inPipelineId) {
        this.inPipelineId = inPipelineId;
    }

    /**
     * Gets the in exec pipeline id.
     *
     * @return the in exec pipeline id
     */
    public String getInExecPipelineId() {
        return inExecPipelineId;
    }

    /**
     * Sets the in exec pipeline id.
     *
     * @param inExecPipelineId
     *            the new in exec pipeline id
     */
    public void setInExecPipelineId(final String inExecPipelineId) {
        this.inExecPipelineId = inExecPipelineId;
    }

    /**
     * Gets the str exec pipeline status.
     *
     * @return the str exec pipeline status
     */
    public String getStrExecPipelineStatus() {
        return strExecPipelineStatus;
    }

    /**
     * Sets the str exec pipeline status.
     *
     * @param strExecPipelineStatus
     *            the new str exec pipeline status
     */
    public void setStrExecPipelineStatus(final String strExecPipelineStatus) {
        this.strExecPipelineStatus = strExecPipelineStatus;
    }
}
