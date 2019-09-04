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

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Pipeline.
 *
 * @version 1.0
 */
@Table(name = "tbl_pipeline")
@Entity
public class Pipeline implements java.io.Serializable {

    /** The in pipeline id. */
    @Id
    @GeneratedValue
    @Column(name = "ppl_id")
    private int inPipelineId;

    /** The str pipeline name. */
    @Column(name = "ppl_name")
    private String strPipelineName;

    /** The str pipeline config details. */
    @Lob
    @Column(name = "ppl_config_details")
    private String strPipelineConfigDetails;

    /** The str connectors. */
    @Lob
    @Column(name = "ppl_connectors")
    private String strConnectors;

    /** The datepipeline. */
    @Column(name = "ppl_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datepipeline;

    /** The date updated pipeline. */
    @Column(name = "ppl_date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdatedPipeline;

    /** The delete status. */
    @Column(name = "ppl_delete_status")
    private int deleteStatus;

    /** The str pipeline exe URL. */
    @Column(name = "ppl_exe_url")
    private String strPipelineExeURL;

    /** The str pipeline conf file. */
    @Column(name = "ppl_exe_dependentfile1")
    private String strPipelineConfFile;

    /** The str pipeline prop file. */
    @Column(name = "ppl_exe_dependentfile2")
    private String strPipelinePropFile;

    /** The str source type. */
    @Column(name = "ppl_source_type")
    private String strSourceType;

    /** The str process type. */
    @Column(name = "ppl_process_type")
    private String strProcessType;

    /** The str sink type. */
    @Column(name = "ppl_sink_type")
    private String strSinkType;

    /** The visualizes. */
    @OneToMany(
            fetch = FetchType.LAZY, mappedBy = "pipeline",
            cascade = CascadeType.ALL)
    private Set<Visualize> visualizes;

    /** The created by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",referencedColumnName = "user_id")
    private User createdBy;

    /** The updated by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by",referencedColumnName = "user_id")
    private User updatedBy;

    /**
     * Instantiates a new pipeline.
     */
    public Pipeline() {
        super();
    }

    /**
     * Gets the in pipeline id.
     *
     * @return the inPipelineId
     */
    public int getInPipelineId() {
        return inPipelineId;
    }

    /**
     * Sets the in pipeline id.
     *
     * @param inPipelineId
     *            the inPipelineId to set
     */
    public void setInPipelineId(final int inPipelineId) {
        this.inPipelineId = inPipelineId;
    }

    /**
     * Gets the str pipeline name.
     *
     * @return the strPipelineName
     */
    public String getStrPipelineName() {
        return strPipelineName;
    }

    /**
     * Sets the str pipeline name.
     *
     * @param strPipelineName
     *            the strPipelineName to set
     */
    public void setStrPipelineName(final String strPipelineName) {
        this.strPipelineName = strPipelineName;
    }

    /**
     * Gets the str pipeline config details.
     *
     * @return the strPipelineConfigDetails
     */
    public String getStrPipelineConfigDetails() {
        return strPipelineConfigDetails;
    }

    /**
     * Sets the str pipeline config details.
     *
     * @param strPipelineConfigDetails
     *            the strPipelineConfigDetails to set
     */
    public void setStrPipelineConfigDetails(
            final String strPipelineConfigDetails) {
        this.strPipelineConfigDetails = strPipelineConfigDetails;
    }

    /**
     * Gets the str connectors.
     *
     * @return the str connectors
     */
    public String getStrConnectors() {
        return strConnectors;
    }

    /**
     * Sets the str connectors.
     *
     * @param strConnectors
     *            the new str connectors
     */
    public void setStrConnectors(final String strConnectors) {
        this.strConnectors = strConnectors;
    }

    /**
     * Gets the datepipeline.
     *
     * @return the datepipeline
     */
    public Date getDatepipeline() {
        return datepipeline;
    }

    /**
     * Sets the datepipeline.
     *
     * @param datepipeline
     *            the new datepipeline
     */
    public void setDatepipeline(final Date datepipeline) {
        this.datepipeline = datepipeline;
    }

    /**
     * Gets the date updated pipeline.
     *
     * @return the date updated pipeline
     */
    public Date getDateUpdatedPipeline() {
        return dateUpdatedPipeline;
    }

    /**
     * Sets the date updated pipeline.
     *
     * @param dateUpdatedPipeline
     *            the new date updated pipeline
     */
    public void setDateUpdatedPipeline(final Date dateUpdatedPipeline) {
        this.dateUpdatedPipeline = dateUpdatedPipeline;
    }

    /**
     * Gets the delete status.
     *
     * @return the delete status
     */
    public int getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * Sets the delete status.
     *
     * @param deleteStatus
     *            the new delete status
     */
    public void setDeleteStatus(final int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * Gets the str pipeline exe URL.
     *
     * @return the str pipeline exe URL
     */
    public String getStrPipelineExeURL() {
        return strPipelineExeURL;
    }

    /**
     * Sets the str pipeline exe URL.
     *
     * @param strPipelineExeURL
     *            the new str pipeline exe URL
     */
    public void setStrPipelineExeURL(final String strPipelineExeURL) {
        this.strPipelineExeURL = strPipelineExeURL;
    }

    /**
     * Gets the visualizes.
     *
     * @return the visualizes
     */
    public Set<Visualize> getVisualizes() {
        return visualizes;
    }

    /**
     * Sets the visualizes.
     *
     * @param visualizes
     *            the new visualizes
     */
    public void setVisualizes(final Set<Visualize> visualizes) {
        this.visualizes = visualizes;
    }

    /**
     * Gets the str pipeline conf file.
     *
     * @return the str pipeline conf file
     */
    public String getStrPipelineConfFile() {
        return strPipelineConfFile;
    }

    /**
     * Sets the str pipeline conf file.
     *
     * @param strPipelineConfFile
     *            the new str pipeline conf file
     */
    public void setStrPipelineConfFile(final String strPipelineConfFile) {
        this.strPipelineConfFile = strPipelineConfFile;
    }

    /**
     * Gets the str pipeline prop file.
     *
     * @return the str pipeline prop file
     */
    public String getStrPipelinePropFile() {
        return strPipelinePropFile;
    }

    /**
     * Sets the str pipeline prop file.
     *
     * @param strPipelinePropFile
     *            the new str pipeline prop file
     */
    public void setStrPipelinePropFile(final String strPipelinePropFile) {
        this.strPipelinePropFile = strPipelinePropFile;
    }

    /**
     * Gets the str source type.
     *
     * @return the str source type
     */
    public String getStrSourceType() {
        return strSourceType;
    }

    /**
     * Sets the str source type.
     *
     * @param strSourceType
     *            the new str source type
     */
    public void setStrSourceType(final String strSourceType) {
        this.strSourceType = strSourceType;
    }

    /**
     * Gets the str process type.
     *
     * @return the str process type
     */
    public String getStrProcessType() {
        return strProcessType;
    }

    /**
     * Sets the str process type.
     *
     * @param strProcessType
     *            the new str process type
     */
    public void setStrProcessType(final String strProcessType) {
        this.strProcessType = strProcessType;
    }

    /**
     * Gets the str sink type.
     *
     * @return the str sink type
     */
    public String getStrSinkType() {
        return strSinkType;
    }

    /**
     * Sets the str sink type.
     *
     * @param strSinkType
     *            the new str sink type
     */
    public void setStrSinkType(final String strSinkType) {
        this.strSinkType = strSinkType;
    }

    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the new created by
     */
    public void setCreatedBy(final User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the updated by.
     *
     * @return the updated by
     */
    public User getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Sets the updated by.
     *
     * @param updatedBy the new updated by
     */
    public void setUpdatedBy(final User updatedBy) {
        this.updatedBy = updatedBy;
    }
}
