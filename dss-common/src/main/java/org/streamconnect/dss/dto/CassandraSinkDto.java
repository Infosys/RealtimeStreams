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
 * The type Cassandra sink dto.
 */
public class CassandraSinkDto implements java.io.Serializable {

    /** The str nodelst. */
    private String strNodelst;

    /** The str topology. */
    private String strTopology;

    /** The in dc replication factor. */
    private int inDcReplicationFactor;

    /** The addl params. */
    private List<KeyValueDto> addlParams;

    /**
     * Instantiates a new Cassandra sink dto.
     */
    public CassandraSinkDto() {
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
     * Gets str topology.
     *
     * @return the str topology
     */
    public String getStrTopology() {
        return strTopology;
    }

    /**
     * Sets str topology.
     *
     * @param strTopology the str topology
     */
    public void setStrTopology(final String strTopology) {
        this.strTopology = strTopology;
    }

    /**
     * Gets in dc replication factor.
     *
     * @return the in dc replication factor
     */
    public int getInDcReplicationFactor() {
        return inDcReplicationFactor;
    }

    /**
     * Sets in dc replication factor.
     *
     * @param inDcReplicationFactor the in dc replication factor
     */
    public void setInDcReplicationFactor(final int inDcReplicationFactor) {
        this.inDcReplicationFactor = inDcReplicationFactor;
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
