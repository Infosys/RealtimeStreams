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
 * The type Schema dto.
 */
public class SchemaDto {
    /** The str schema type. */
    private String strSchemaType;

    /** The str schema delimitor. */
    private String strSchemaDelimitor;

    /** The obj schema. */
    private Object objSchema;

    /** The obj sample data. */
    private Object objSampleData;

    /**
     * Gets obj schema.
     *
     * @return the obj schema
     */
    public Object getObjSchema() {
        return objSchema;
    }

    /**
     * Sets obj schema.
     *
     * @param objSchema the obj schema
     */
    public void setObjSchema(final Object objSchema) {
        this.objSchema = objSchema;
    }

    /**
     * Gets str schema type.
     *
     * @return the str schema type
     */
    public String getStrSchemaType() {
        return strSchemaType;
    }

    /**
     * Sets str schema type.
     *
     * @param strSchemaType the str schema type
     */
    public void setStrSchemaType(final String strSchemaType) {
        this.strSchemaType = strSchemaType;
    }

    /**
     * Gets str schema delimitor.
     *
     * @return the str schema delimitor
     */
    public String getStrSchemaDelimitor() {
        return strSchemaDelimitor;
    }

    /**
     * Sets str schema delimitor.
     *
     * @param strSchemaDelimitor the str schema delimitor
     */
    public void setStrSchemaDelimitor(final String strSchemaDelimitor) {
        this.strSchemaDelimitor = strSchemaDelimitor;
    }

    /**
     * Gets obj sample data.
     *
     * @return the obj sample data
     */
    public Object getObjSampleData() {
        return objSampleData;
    }

    /**
     * Sets obj sample data.
     *
     * @param objSampleData the obj sample data
     */
    public void setObjSampleData(final Object objSampleData) {
        this.objSampleData = objSampleData;
    }
}
