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
 * POJO Class for Sink Table validation.
 *
 * @version 1.0
 */
public class SinkValidation implements Serializable {

    /** The sink ID. */
    private int sinkID;

    /** The sink schema name. */
    private String sinkSchemaName;

    /** The sink table name. */
    private String sinkTableName;

    /**
     * Gets the sink ID.
     *
     * @return the sink ID
     */
    public int getSinkID() {
        return sinkID;
    }

    /**
     * Sets the sink ID.
     *
     * @param sinkID
     *            the new sink ID
     */
    public void setSinkID(final int sinkID) {
        this.sinkID = sinkID;
    }

    /**
     * Gets the sink schema name.
     *
     * @return the sink schema name
     */
    public String getSinkSchemaName() {
        return sinkSchemaName;
    }

    /**
     * Sets the sink schema name.
     *
     * @param sinkSchemaName
     *            the new sink schema name
     */
    public void setSinkSchemaName(final String sinkSchemaName) {
        this.sinkSchemaName = sinkSchemaName;
    }

    /**
     * Gets the sink table name.
     *
     * @return the sink table name
     */
    public String getSinkTableName() {
        return sinkTableName;
    }

    /**
     * Sets the sink table name.
     *
     * @param sinkTableName
     *            the new sink table name
     */
    public void setSinkTableName(final String sinkTableName) {
        this.sinkTableName = sinkTableName;
    }
}
