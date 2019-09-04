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
 * POJO Class for Sink Table validation.
 */
public class SinkValidationDto implements Serializable {

    /** The sink ID. */
    private int sinkID;

    /** The sink schema name. */
    private String sinkSchemaName;

    /** The sink table name. */
    private String sinkTableName;

    /**
     * Gets sink id.
     *
     * @return the sink id
     */
    public int getSinkID() {
        return sinkID;
    }

    /**
     * Sets sink id.
     *
     * @param sinkID the sink id
     */
    public void setSinkID(final int sinkID) {
        this.sinkID = sinkID;
    }


    /**
     * Gets sink schema name.
     *
     * @return the sink schema name
     */
    public String getSinkSchemaName() {
        return sinkSchemaName;
    }

    /**
     * Sets sink schema name.
     *
     * @param sinkSchemaName the sink schema name
     */
    public void setSinkSchemaName(final String sinkSchemaName) {
        this.sinkSchemaName = sinkSchemaName;
    }

    /**
     * Gets sink table name.
     *
     * @return the sink table name
     */
    public String getSinkTableName() {
        return sinkTableName;
    }

    /**
     * Sets sink table name.
     *
     * @param sinkTableName the sink table name
     */
    public void setSinkTableName(final String sinkTableName) {
        this.sinkTableName = sinkTableName;
    }
}
