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

import java.util.List;

/**
 * The Class Pipeline.
 *
 * @version 1.0
 */
public class Pipeline implements java.io.Serializable {

    /** The in pipeline id. */
    private int inPipelineId;

    /** The str pipeline name. */
    private String strPipelineName;

    /** The in kpi id. */
    private int inKpiId;

    /** The in category id. */
    private int inCategoryId;

    /** The obj pipeline config details. */
    private Object nodes;

    /** The str connectors. */
    private Object edges;

    /** The str pipeline exe URL. */
    private String strPipelineExeURL;

    /** The str kpi name. */
    private String strKpiName;

    /** The str category name. */
    private String strCategoryName;

    /** The str date created. */
    private String strDateCreated;

    /** The str ppl status. */
    private String strPplStatus;

    /** The category set. */
    private List<Category> categorySet;

    /** The deleted visualizations. */
    private Object deletedVisualizations;

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
     * @param inPipelineId the inPipelineId to set
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
     * @param strPipelineName the strPipelineName to set
     */
    public void setStrPipelineName(final String strPipelineName) {
        this.strPipelineName = strPipelineName;
    }


    /**
     * Gets the in category id.
     *
     * @return the in category id
     */
    public int getInCategoryId() {
        return inCategoryId;
    }

    /**
     * Sets the in category id.
     *
     * @param inCategoryId the new in category id
     */
    public void setInCategoryId(final int inCategoryId) {
        this.inCategoryId = inCategoryId;
    }

    /**
     * Gets the in kpi id.
     *
     * @return the in kpi id
     */
    public int getInKpiId() {
        return inKpiId;
    }

    /**
     * Sets the in kpi id.
     *
     * @param inKpiId the new in kpi id
     */
    public void setInKpiId(final int inKpiId) {
        this.inKpiId = inKpiId;
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
     * @param strPipelineExeURL the new str pipeline exe URL
     */
    public void setStrPipelineExeURL(final String strPipelineExeURL) {
        this.strPipelineExeURL = strPipelineExeURL;
    }

    /**
     * Gets the str kpi name.
     *
     * @return the str kpi name
     */
    public String getStrKpiName() {
        return strKpiName;
    }

    /**
     * Sets the str kpi name.
     *
     * @param strKpiName the new str kpi name
     */
    public void setStrKpiName(final String strKpiName) {
        this.strKpiName = strKpiName;
    }

    /**
     * Gets the str category name.
     *
     * @return the str category name
     */
    public String getStrCategoryName() {
        return strCategoryName;
    }

    /**
     * Sets the str category name.
     *
     * @param strCategoryName the new str category name
     */
    public void setStrCategoryName(final String strCategoryName) {
        this.strCategoryName = strCategoryName;
    }

    /**
     * Gets the str date created.
     *
     * @return the str date created
     */
    public String getStrDateCreated() {
        return strDateCreated;
    }

    /**
     * Sets the str date created.
     *
     * @param strDateCreated the new str date created
     */
    public void setStrDateCreated(final String strDateCreated) {
        this.strDateCreated = strDateCreated;
    }

    /**
     * Gets the str ppl status.
     *
     * @return the str ppl status
     */
    public String getStrPplStatus() {
        return strPplStatus;
    }

    /**
     * Sets the str ppl status.
     *
     * @param strPplStatus the new str ppl status
     */
    public void setStrPplStatus(final String strPplStatus) {
        this.strPplStatus = strPplStatus;
    }

    /**
     * Gets the category set.
     *
     * @return the category set
     */
    public List<Category> getCategorySet() {
        return categorySet;
    }

    /**
     * Sets the category set.
     *
     * @param categorySet the new category set
     */
    public void setCategorySet(final List<Category> categorySet) {
        this.categorySet = categorySet;
    }

    /**
     * Gets the deleted visualizations.
     *
     * @return the deleted visualizations
     */
    public Object getDeletedVisualizations() {
        return deletedVisualizations;
    }

    /**
     * Sets the deleted visualizations.
     *
     * @param deletedVisualizations the new deleted visualizations
     */
    public void setDeletedVisualizations(final Object deletedVisualizations) {
        this.deletedVisualizations = deletedVisualizations;
    }

    /**
     * Gets edges.
     *
     * @return the edges
     */
    public Object getEdges() {
        return edges;
    }

    /**
     * Sets edges.
     *
     * @param edges the edges
     */
    public void setEdges(Object edges) {
        this.edges = edges;
    }

    /**
     * Gets nodes.
     *
     * @return the nodes
     */
    public Object getNodes() {
        return nodes;
    }

    /**
     * Sets nodes.
     *
     * @param nodes the nodes
     */
    public void setNodes(Object nodes) {
        this.nodes = nodes;
    }
}
