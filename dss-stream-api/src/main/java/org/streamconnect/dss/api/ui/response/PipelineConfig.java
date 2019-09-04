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

/**
 * The Class PipelineConfig.
 *
 * @version 1.0
 * @param <T>
 *            the generic type
 */
public class PipelineConfig<T> {

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
    private String strDataSchema;

    /** The obj business sql. */
    private BusinessSql objBusinessSql;

    /** The str target table. */
    private String strTargetTable;

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

    /**
     * Instantiates a new pipeline config.
     */
    public PipelineConfig() {
        super();
    }

    /**
     * Gets the obj source.
     *
     * @return the obj source
     */
    public T getObjSource() {
        return objSource;
    }

    /**
     * Sets the obj source.
     *
     * @param objSource
     *            the new obj source
     */
    public void setObjSource(final T objSource) {
        this.objSource = objSource;
    }

    /**
     * Gets the obj process.
     *
     * @return the obj process
     */
    public T getObjProcess() {
        return objProcess;
    }

    /**
     * Sets the obj process.
     *
     * @param objProcess
     *            the new obj process
     */
    public void setObjProcess(final T objProcess) {
        this.objProcess = objProcess;
    }

    /**
     * Gets the obj sink.
     *
     * @return the obj sink
     */
    public T getObjSink() {
        return objSink;
    }

    /**
     * Sets the obj sink.
     *
     * @param objSink
     *            the new obj sink
     */
    public void setObjSink(final T objSink) {
        this.objSink = objSink;
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
     * Gets the str data schema.
     *
     * @return the str data schema
     */
    public String getStrDataSchema() {
        return strDataSchema;
    }

    /**
     * Sets the str data schema.
     *
     * @param strDataSchema
     *            the new str data schema
     */
    public void setStrDataSchema(final String strDataSchema) {
        this.strDataSchema = strDataSchema;
    }

    /**
     * Gets the obj business sql.
     *
     * @return the obj business sql
     */
    public BusinessSql getObjBusinessSql() {
        return objBusinessSql;
    }

    /**
     * Sets the obj business sql.
     *
     * @param objBusinessSql
     *            the new obj business sql
     */
    public void setObjBusinessSql(final BusinessSql objBusinessSql) {
        this.objBusinessSql = objBusinessSql;
    }

    /**
     * Gets the str target table.
     *
     * @return the str target table
     */
    public String getStrTargetTable() {
        return strTargetTable;
    }

    /**
     * Sets the str target table.
     *
     * @param strTargetTable
     *            the new str target table
     */
    public void setStrTargetTable(final String strTargetTable) {
        this.strTargetTable = strTargetTable;
    }

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
     * Gets the str pipeline name.
     *
     * @return the str pipeline name
     */
    public String getStrPipelineName() {
        return strPipelineName;
    }

    /**
     * Sets the str pipeline name.
     *
     * @param strPipelineName
     *            the new str pipeline name
     */
    public void setStrPipelineName(final String strPipelineName) {
        this.strPipelineName = strPipelineName;
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
     * @param inKpiId
     *            the new in kpi id
     */
    public void setInKpiId(final int inKpiId) {
        this.inKpiId = inKpiId;
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
     * @param inCategoryId
     *            the new in category id
     */
    public void setInCategoryId(final int inCategoryId) {
        this.inCategoryId = inCategoryId;
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
}
