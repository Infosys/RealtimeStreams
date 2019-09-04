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

import java.io.Serializable;

/**
 * The Class ColumnMetadata.
 *
 * @version 1.0
 */
public class ColumnMetadata implements Serializable {

    /** The column name. */
    private String columnName;

    /** The column data type. */
    private String columnDataType;

    /**
     * Gets the column name.
     *
     * @return the column name
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Sets the column name.
     *
     * @param columnName
     *            the new column name
     */
    public void setColumnName(final String columnName) {
        this.columnName = columnName;
    }

    /**
     * Gets the column data type.
     *
     * @return the column data type
     */
    public String getColumnDataType() {
        return columnDataType;
    }

    /**
     * Sets the column data type.
     *
     * @param columnDataType
     *            the new column data type
     */
    public void setColumnDataType(final String columnDataType) {
        this.columnDataType = columnDataType;
    }
}
