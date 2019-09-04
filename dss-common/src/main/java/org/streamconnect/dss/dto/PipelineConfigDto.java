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


import java.util.List;


/**
 * The type Pipeline config dto.
 *
 * @param <T> the type parameter
 */
public class PipelineConfigDto<T> {

    /** The obj source. */
    private T objSource;

    /** The obj process. */
    private T objProcess;

    /** The obj sink. */
    private T objSink;

    /** The str source type. */
    private String strSourceType;

    /** The str process type. */
    private String strProcessType;

    /** The str sink type. */
    private String strSinkType;

    /** The str data schema. */
    private SchemaDto strDataSchema;

    /** The obj business sql dto. */
    private List<BusinessSqlDto> objBusinessSqlDto;

    /** The obj target table. */
    private BusinessSqlDto objTargetTable;

    /** The in pipeline id. */
    private int inPipelineId;

    /** The str pipeline name. */
    private String strPipelineName;

    /** The in kpi id. */
    private int inKpiId;

    /** The in category id. */
    private int inCategoryId;

    /** The str pipeline exe URL. */
    private String strPipelineExeURL;

    /** The str initial table name. */
    private String strInitialTableName;

    /** The obj visual entity list. */
    private List<VisualizationDto> objVisualEntityList;

    /**
     * Instantiates a new Pipeline config dto.
     */
    public PipelineConfigDto() {
        super();
    }

    /**
     * Gets obj source.
     *
     * @return the obj source
     */
    public T getObjSource() {
        return objSource;
    }

    /**
     * Sets obj source.
     *
     * @param objSource the obj source
     */
    public void setObjSource(final T objSource) {
        this.objSource = objSource;
    }

    /**
     * Gets obj process.
     *
     * @return the obj process
     */
    public T getObjProcess() {
        return objProcess;
    }

    /**
     * Sets obj process.
     *
     * @param objProcess the obj process
     */
    public void setObjProcess(final T objProcess) {
        this.objProcess = objProcess;
    }

    /**
     * Gets obj sink.
     *
     * @return the obj sink
     */
    public T getObjSink() {
        return objSink;
    }

    /**
     * Sets obj sink.
     *
     * @param objSink the obj sink
     */
    public void setObjSink(final T objSink) {
        this.objSink = objSink;
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
     * Gets str data schema.
     *
     * @return the str data schema
     */
    public SchemaDto getStrDataSchema() {
        return strDataSchema;
    }

    /**
     * Sets str data schema.
     *
     * @param strDataSchema the str data schema
     */
    public void setStrDataSchema(final SchemaDto strDataSchema) {
        this.strDataSchema = strDataSchema;
    }

    /**
     * Gets obj business sql dto.
     *
     * @return the obj business sql dto
     */
    public List<BusinessSqlDto> getObjBusinessSqlDto() {
        return objBusinessSqlDto;
    }

    /**
     * Sets obj business sql dto.
     *
     * @param objBusinessSqlDto the obj business sql dto
     */
    public void setObjBusinessSqlDto(final List<BusinessSqlDto> objBusinessSqlDto) {
        this.objBusinessSqlDto = objBusinessSqlDto;
    }

    /**
     * Gets obj target table.
     *
     * @return the obj target table
     */
    public BusinessSqlDto getObjTargetTable() {
        return objTargetTable;
    }

    /**
     * Sets obj target table.
     *
     * @param objTargetTable the obj target table
     */
    public void setObjTargetTable(final BusinessSqlDto objTargetTable) {
        this.objTargetTable = objTargetTable;
    }


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
     * Gets str pipeline name.
     *
     * @return the str pipeline name
     */
    public String getStrPipelineName() {
        return strPipelineName;
    }

    /**
     * Sets str pipeline name.
     *
     * @param strPipelineName the str pipeline name
     */
    public void setStrPipelineName(final String strPipelineName) {
        this.strPipelineName = strPipelineName;
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
     * Gets obj visual entity list.
     *
     * @return the obj visual entity list
     */
    public List<VisualizationDto> getObjVisualEntityList() {
        return objVisualEntityList;
    }

    /**
     * Sets obj visual entity list.
     *
     * @param objVisualEntityList the obj visual entity list
     */
    public void setObjVisualEntityList(final List<VisualizationDto> objVisualEntityList) {
        this.objVisualEntityList = objVisualEntityList;
    }

    /**
     * Gets str initial table name.
     *
     * @return the str initial table name
     */
    public String getStrInitialTableName() {
        return strInitialTableName;
    }

    /**
     * Sets str initial table name.
     *
     * @param strInitialTableName the str initial table name
     */
    public void setStrInitialTableName(final String strInitialTableName) {
        this.strInitialTableName = strInitialTableName;
    }
}
