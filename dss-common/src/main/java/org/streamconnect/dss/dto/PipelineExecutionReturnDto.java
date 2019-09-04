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
 * The type Pipeline execution return dto.
 */
public class PipelineExecutionReturnDto implements Serializable {

    /** The in pipeline id. */
    private int inPipelineId;

    /** The in exec pipeline id. */
    private String inExecPipelineId;

    /** The bln exec status. */
    private boolean blnExecStatus;

    /** The pipeline execution details dto. */
    private PipelineExecutionDetailsDto pipelineExecutionDetailsDto;

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
     * Is bln exec status boolean.
     *
     * @return the boolean
     */
    public boolean isBlnExecStatus() {
        return blnExecStatus;
    }

    /**
     * Sets bln exec status.
     *
     * @param blnExecStatus the bln exec status
     */
    public void setBlnExecStatus(final boolean blnExecStatus) {
        this.blnExecStatus = blnExecStatus;
    }

    /**
     * Gets pipeline execution details dto.
     *
     * @return the pipeline execution details dto
     */
    public PipelineExecutionDetailsDto getPipelineExecutionDetailsDto() {
        return pipelineExecutionDetailsDto;
    }

    /**
     * Sets pipeline execution details dto.
     *
     * @param pipelineExecutionDetailsDto the pipeline execution details dto
     */
    public void setPipelineExecutionDetailsDto(final PipelineExecutionDetailsDto pipelineExecutionDetailsDto) {
        this.pipelineExecutionDetailsDto = pipelineExecutionDetailsDto;
    }

}
