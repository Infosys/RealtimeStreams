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
 * The Class PipelineLineage.
 *
 * @version 1.0
 * @param <T>
 *            the generic type
 */
public class PipelineLineage<T> {

    /** The in pipeline id. */
    private int inPipelineId;

    /** The str pipeline name. */
    private String strPipelineName;

    /** The str pipeline exe URL. */
    private String strPipelineExeURL;

    /** The str connectors. */
    private Object edges;

    /** The obj pipeline config details. */
    private Object nodes;

    /** The node details. */
    private Object nodeDetails;

    /** The addl details. */
    private Object addlDetails;

    /**
     * Instantiates a new pipeline config.
     */
    public PipelineLineage() {
        super();
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

    /**
     * Gets edges.
     *
     * @return the edges
     */
    public Object getEdges() {
        return edges;
    }

    /**
     * Sets edges.
     *
     * @param edges the edges
     */
    public void setEdges(Object edges) {
        this.edges = edges;
    }

    /**
     * Gets nodes.
     *
     * @return the nodes
     */
    public Object getNodes() {
        return nodes;
    }

    /**
     * Sets nodes.
     *
     * @param nodes the nodes
     */
    public void setNodes(Object nodes) {
        this.nodes = nodes;
    }
}
