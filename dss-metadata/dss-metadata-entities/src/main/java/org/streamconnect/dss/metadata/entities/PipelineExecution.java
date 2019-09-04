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
package org.streamconnect.dss.metadata.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class PipelineExecution.
 *
 * @version 1.0
 */
@Table(name = "tbl_pipeline_execution")
@Entity
public class PipelineExecution implements Serializable {

    /** The in id. */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int inId;

    /** The pipeline. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ppl_id")
    private Pipeline pipeline;

    /** The in exec pipeline id. */
    @Column(name = "exec_ppl_id")
    private String inExecPipelineId;

    /** The date pipeline start. */
    @Column(name = "exec_start_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePipelineStart;

    /** The date pipeline updated. */
    @Column(name = "exec_last_update_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePipelineUpdated;

    /** The str exec pipeline status. */
    @Column(name = "exec_ppl_status")
    private String strExecPipelineStatus;

    /** The str exec pipeline remark. */
    @Column(name = "exec_ppl_remark")
    private String strExecPipelineRemark;

    /**
     * Gets the in id.
     *
     * @return the in id
     */
    public int getInId() {
        return inId;
    }

    /**
     * Sets the in id.
     *
     * @param inId
     *            the new in id
     */
    public void setInId(final int inId) {
        this.inId = inId;
    }

    /**
     * Gets the pipeline.
     *
     * @return the pipeline
     */
    public Pipeline getPipeline() {
        return pipeline;
    }

    /**
     * Sets the pipeline.
     *
     * @param pipeline
     *            the new pipeline
     */
    public void setPipeline(final Pipeline pipeline) {
        this.pipeline = pipeline;
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
     * Gets the date pipeline start.
     *
     * @return the date pipeline start
     */
    public Date getDatePipelineStart() {
        return datePipelineStart;
    }

    /**
     * Sets the date pipeline start.
     *
     * @param datePipelineStart
     *            the new date pipeline start
     */
    public void setDatePipelineStart(final Date datePipelineStart) {
        this.datePipelineStart = datePipelineStart;
    }

    /**
     * Gets the date pipeline updated.
     *
     * @return the date pipeline updated
     */
    public Date getDatePipelineUpdated() {
        return datePipelineUpdated;
    }

    /**
     * Sets the date pipeline updated.
     *
     * @param datePipelineUpdated
     *            the new date pipeline updated
     */
    public void setDatePipelineUpdated(final Date datePipelineUpdated) {
        this.datePipelineUpdated = datePipelineUpdated;
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

    /**
     * Gets the str exec pipeline remark.
     *
     * @return the str exec pipeline remark
     */
    public String getStrExecPipelineRemark() {
        return strExecPipelineRemark;
    }

    /**
     * Sets the str exec pipeline remark.
     *
     * @param strExecPipelineRemark
     *            the new str exec pipeline remark
     */
    public void setStrExecPipelineRemark(final String strExecPipelineRemark) {
        this.strExecPipelineRemark = strExecPipelineRemark;
    }
}
