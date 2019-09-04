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
 * The type Column metadata dto.
 */
public class ColumnMetadataDto implements Serializable {
    /**
     * The Column name.
     */
    private String columnName;
    /**
     * The Column data type.
     */
    private String columnDataType;

    /**
     * Gets column name.
     *
     * @return the column name
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Sets column name.
     *
     * @param columnName the column name
     */
    public void setColumnName(final String columnName) {
        this.columnName = columnName;
    }

    /**
     * Gets column data type.
     *
     * @return the column data type
     */
    public String getColumnDataType() {
        return columnDataType;
    }

    /**
     * Sets column data type.
     *
     * @param columnDataType the column data type
     */
    public void setColumnDataType(final String columnDataType) {
        this.columnDataType = columnDataType;
    }
}
