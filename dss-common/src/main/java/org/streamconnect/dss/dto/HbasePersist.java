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
 * The type Hbase persist.
 */
public class HbasePersist implements java.io.Serializable {

    /** The str nodelst. */
    private String strNodelst;

    /** The primary key. */
    private String primaryKey;

    /** The index key. */
    private String indexKey;

    /** The addl params. */
    private List<KeyValueDto> addlParams;

    /**
     * Instantiates a new Hbase persist.
     */
    public HbasePersist() {
        super();
    }

    /**
     * Gets str nodelst.
     *
     * @return the str nodelst
     */
    public String getStrNodelst() {
        return strNodelst;
    }

    /**
     * Sets str nodelst.
     *
     * @param strNodelst the str nodelst
     */
    public void setStrNodelst(final String strNodelst) {
        this.strNodelst = strNodelst;
    }

    /**
     * Gets primary key.
     *
     * @return the primary key
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets primary key.
     *
     * @param primaryKey the primary key
     */
    public void setPrimaryKey(final String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Gets index key.
     *
     * @return the index key
     */
    public String getIndexKey() {
        return indexKey;
    }

    /**
     * Sets index key.
     *
     * @param indexKey the index key
     */
    public void setIndexKey(final String indexKey) {
        this.indexKey = indexKey;
    }

    /**
     * Gets addl params.
     *
     * @return the addl params
     */
    public List<KeyValueDto> getAddlParams() {
        return addlParams;
    }

    /**
     * Sets addl params.
     *
     * @param addlParams the addl params
     */
    public void setAddlParams(final List<KeyValueDto> addlParams) {
        this.addlParams = addlParams;
    }
}
