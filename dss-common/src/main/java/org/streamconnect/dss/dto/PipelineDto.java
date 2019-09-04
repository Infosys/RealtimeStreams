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

import java.util.Date;
import java.util.List;

/**
 * The type Pipeline dto.
 */
public class PipelineDto implements java.io.Serializable {

    /** The in pipeline id. */
    private int inPipelineId;

    /** The str pipeline name. */
    private String strPipelineName;

    /** The in kpi id. */
    private int inKpiId;

    /** The in category id. */
    private int inCategoryId;

    /** The obj pipeline config details. */
    private Object objPipelineConfigDetails;

    /** The str connectors. */
    private Object strConnectors;

    /** The str pipeline exe URL. */
    private String strPipelineExeURL;

    /** The str kpi name. */
    private String strKpiName;

    /** The str category name. */
    private String strCategoryName;

    /** The date pipeline. */
    private Date datePipeline;

    /** The str ppl status. */
    private String strPplStatus;

    /** The in exec pipeline id. */
    private String inExecPipelineId;

    /** The in id. */
    private int inId;

    /** The category list. */
    private List<CategoryDto> categoryList;

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

    /** The in user id. */
    private int inUserId;

    /**
     * Instantiates a new Pipeline dto.
     */
    public PipelineDto() {
        super();
    }

    /**
     * Gets in pipeline id.
     *
     * @return the inPipelineId
     */
    public int getInPipelineId() {
        return inPipelineId;
    }

    /**
     * Sets in pipeline id.
     *
     * @param inPipelineId the inPipelineId to set
     */
    public void setInPipelineId(final int inPipelineId) {
        this.inPipelineId = inPipelineId;
    }

    /**
     * Gets str pipeline name.
     *
     * @return the strPipelineName
     */
    public String getStrPipelineName() {
        return strPipelineName;
    }

    /**
     * Sets str pipeline name.
     *
     * @param strPipelineName the strPipelineName to set
     */
    public void setStrPipelineName(final String strPipelineName) {
        this.strPipelineName = strPipelineName;
    }

    /**
     * Gets in category id.
     *
     * @return the in category id
     */
    public int getInCategoryId() {
        return inCategoryId;
    }

    /**
     * Sets in category id.
     *
     * @param inCategoryId the in category id
     */
    public void setInCategoryId(final int inCategoryId) {
        this.inCategoryId = inCategoryId;
    }

    /**
     * Gets in kpi id.
     *
     * @return the in kpi id
     */
    public int getInKpiId() {
        return inKpiId;
    }

    /**
     * Sets in kpi id.
     *
     * @param inKpiId the in kpi id
     */
    public void setInKpiId(final int inKpiId) {
        this.inKpiId = inKpiId;
    }

    /**
     * Gets str connectors.
     *
     * @return the str connectors
     */
    public Object getStrConnectors() {
        return strConnectors;
    }

    /**
     * Sets str connectors.
     *
     * @param strConnectors the str connectors
     */
    public void setStrConnectors(final Object strConnectors) {
        this.strConnectors = strConnectors;
    }

    /**
     * Gets obj pipeline config details.
     *
     * @return the objPipelineConfigDetails
     */
    public Object getObjPipelineConfigDetails() {
        return objPipelineConfigDetails;
    }

    /**
     * Sets obj pipeline config details.
     *
     * @param objPipelineConfigDetails the objPipelineConfigDetails to set
     */
    public void setObjPipelineConfigDetails(final Object objPipelineConfigDetails) {
        this.objPipelineConfigDetails = objPipelineConfigDetails;
    }

    /**
     * Gets str pipeline exe url.
     *
     * @return the str pipeline exe url
     */
    public String getStrPipelineExeURL() {
        return strPipelineExeURL;
    }

    /**
     * Sets str pipeline exe url.
     *
     * @param strPipelineExeURL the str pipeline exe url
     */
    public void setStrPipelineExeURL(final String strPipelineExeURL) {
        this.strPipelineExeURL = strPipelineExeURL;
    }

    /**
     * Gets str kpi name.
     *
     * @return the str kpi name
     */
    public String getStrKpiName() {
        return strKpiName;
    }

    /**
     * Sets str kpi name.
     *
     * @param strKpiName the str kpi name
     */
    public void setStrKpiName(final String strKpiName) {
        this.strKpiName = strKpiName;
    }

    /**
     * Gets str category name.
     *
     * @return the str category name
     */
    public String getStrCategoryName() {
        return strCategoryName;
    }

    /**
     * Sets str category name.
     *
     * @param strCategoryName the str category name
     */
    public void setStrCategoryName(final String strCategoryName) {
        this.strCategoryName = strCategoryName;
    }

    /**
     * Gets date pipeline.
     *
     * @return the date pipeline
     */
    public Date getDatePipeline() {
        return datePipeline;
    }

    /**
     * Sets date pipeline.
     *
     * @param datePipeline the date pipeline
     */
    public void setDatePipeline(final Date datePipeline) {
        this.datePipeline = datePipeline;
    }

    /**
     * Gets str ppl status.
     *
     * @return the str ppl status
     */
    public String getStrPplStatus() {
        return strPplStatus;
    }

    /**
     * Sets str ppl status.
     *
     * @param strPplStatus the str ppl status
     */
    public void setStrPplStatus(final String strPplStatus) {
        this.strPplStatus = strPplStatus;
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
     * Gets in id.
     *
     * @return the in id
     */
    public int getInId() {
        return inId;
    }

    /**
     * Sets in id.
     *
     * @param inId the in id
     */
    public void setInId(final int inId) {
        this.inId = inId;
    }

    /**
     * Gets category list.
     *
     * @return the category list
     */
    public List<CategoryDto> getCategoryList() {
        return categoryList;
    }

    /**
     * Sets category list.
     *
     * @param categoryList the category list
     */
    public void setCategoryList(final List<CategoryDto> categoryList) {
        this.categoryList = categoryList;
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

    /**
     * Gets in user id.
     *
     * @return the in user id
     */
    public int getInUserId() {
        return inUserId;
    }

    /**
     * Sets in user id.
     *
     * @param inUserId the in user id
     */
    public void setInUserId(final int inUserId) {
        this.inUserId = inUserId;
    }
}
