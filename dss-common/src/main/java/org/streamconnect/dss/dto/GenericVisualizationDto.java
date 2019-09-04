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
 * The type Generic visualization dto.
 */
public class GenericVisualizationDto {

    /** The gen id. */
    private int genId;

    /** The str title. */
    private String strTitle;

    /** The str dimension 1 title. */
    private String strDimension1Title;

    /** The str dimension 2 title. */
    private String strDimension2Title;

    /** The str sink type. */
    private String strSinkType;

    /** The str sink table. */
    private String strSinkTable;

    /** The str sink query. */
    private String strSinkQuery;

    /** The obj sink configuration. */
    private Object objSinkConfiguration;

    /**
     * Gets gen id.
     *
     * @return the gen id
     */
    public int getGenId() {
        return genId;
    }

    /**
     * Sets gen id.
     *
     * @param genId
     *            the gen id
     */
    public void setGenId(final int genId) {
        this.genId = genId;
    }

    /**
     * Gets str title.
     *
     * @return the str title
     */
    public String getStrTitle() {
        return strTitle;
    }

    /**
     * Sets str title.
     *
     * @param strTitle
     *            the str title
     */
    public void setStrTitle(final String strTitle) {
        this.strTitle = strTitle;
    }

    /**
     * Gets str dimension 1 title.
     *
     * @return the str dimension 1 title
     */
    public String getStrDimension1Title() {
        return strDimension1Title;
    }

    /**
     * Sets str dimension 1 title.
     *
     * @param strDimension1Title
     *            the str dimension 1 title
     */
    public void setStrDimension1Title(final String strDimension1Title) {
        this.strDimension1Title = strDimension1Title;
    }

    /**
     * Gets str dimension 2 title.
     *
     * @return the str dimension 2 title
     */
    public String getStrDimension2Title() {
        return strDimension2Title;
    }

    /**
     * Sets str dimension 2 title.
     *
     * @param strDimension2Title
     *            the str dimension 2 title
     */
    public void setStrDimension2Title(final String strDimension2Title) {
        this.strDimension2Title = strDimension2Title;
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
     * @param strSinkType
     *            the str sink type
     */
    public void setStrSinkType(final String strSinkType) {
        this.strSinkType = strSinkType;
    }

    /**
     * Gets str sink table.
     *
     * @return the str sink table
     */
    public String getStrSinkTable() {
        return strSinkTable;
    }

    /**
     * Sets str sink table.
     *
     * @param strSinkTable
     *            the str sink table
     */
    public void setStrSinkTable(final String strSinkTable) {
        this.strSinkTable = strSinkTable;
    }

    /**
     * Gets str sink query.
     *
     * @return the str sink query
     */
    public String getStrSinkQuery() {
        return strSinkQuery;
    }

    /**
     * Sets str sink query.
     *
     * @param strSinkQuery
     *            the str sink query
     */
    public void setStrSinkQuery(final String strSinkQuery) {
        this.strSinkQuery = strSinkQuery;
    }

    /**
     * Gets obj sink configuration.
     *
     * @return the obj sink configuration
     */
    public Object getObjSinkConfiguration() {
        return objSinkConfiguration;
    }

    /**
     * Sets obj sink configuration.
     *
     * @param objSinkConfiguration
     *            the obj sink configuration
     */
    public void setObjSinkConfiguration(final Object objSinkConfiguration) {
        this.objSinkConfiguration = objSinkConfiguration;
    }
}
