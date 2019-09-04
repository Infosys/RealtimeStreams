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
package org.streamconnect.dss.metadata.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Visualize.
 *
 * @version 1.0
 */
@Table(name = "tbl_visualize")
@Entity
public class Visualize implements Serializable {

    /** The in visualize id. */
    @Id
    @GeneratedValue
    @Column(name = "visualize_id")
    private int inVisualizeId;

    /** The str visualize name. */
    @Column(name = "visualize_name")
    private String strVisualizeName;

    /** The str visualize desc. */
    @Column(name = "visualize_description")
    private String strVisualizeDesc;

    /** The str visualize parent type. */
    @Column(name = "visualize_parent_type")
    private String strVisualizeParentType;

    /** The str visualize sub type. */
    @Column(name = "visualize_sub_type")
    private String strVisualizeSubType;

    /** The str visualize config details. */
    @Lob
    @Column(name = "visualize_config_details")
    private String strVisualizeConfigDetails;

    /** The date visualize. */
    @Column(name = "visualize_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVisualize;

    /** The date updated visualize. */
    @Column(name = "visualize_date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdatedVisualize;

    /** The delete status. */
    @Column(name = "visualize_delete_status")
    private int deleteStatus;

    /** The kpis. */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "visualizes")
    private Set<Kpi> kpis = new HashSet<Kpi>(0);

    /** The pipeline. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ppl_id", nullable = true)
    private Pipeline pipeline;

    /** The in visualize entity id. */
    @Column(name = "visualize_entity_id")
    private int inVisualizeEntityId;

    /** The str key space. */
    @Column(name = "visualize_keyspace")
    private String strKeySpace;

    /**
     * Gets the in visualize id.
     *
     * @return the in visualize id
     */
    public int getInVisualizeId() {
        return inVisualizeId;
    }

    /**
     * Sets the in visualize id.
     *
     * @param inVisualizeId
     *            the new in visualize id
     */
    public void setInVisualizeId(final int inVisualizeId) {
        this.inVisualizeId = inVisualizeId;
    }

    /**
     * Gets the str visualize name.
     *
     * @return the str visualize name
     */
    public String getStrVisualizeName() {
        return strVisualizeName;
    }

    /**
     * Sets the str visualize name.
     *
     * @param strVisualizeName
     *            the new str visualize name
     */
    public void setStrVisualizeName(final String strVisualizeName) {
        this.strVisualizeName = strVisualizeName;
    }

    /**
     * Gets the str visualize desc.
     *
     * @return the str visualize desc
     */
    public String getStrVisualizeDesc() {
        return strVisualizeDesc;
    }

    /**
     * Sets the str visualize desc.
     *
     * @param strVisualizeDesc
     *            the new str visualize desc
     */
    public void setStrVisualizeDesc(final String strVisualizeDesc) {
        this.strVisualizeDesc = strVisualizeDesc;
    }

    /**
     * Gets the str visualize parent type.
     *
     * @return the str visualize parent type
     */
    public String getStrVisualizeParentType() {
        return strVisualizeParentType;
    }

    /**
     * Sets the str visualize parent type.
     *
     * @param strVisualizeParentType
     *            the new str visualize parent type
     */
    public void setStrVisualizeParentType(final String strVisualizeParentType) {
        this.strVisualizeParentType = strVisualizeParentType;
    }

    /**
     * Gets the str visualize sub type.
     *
     * @return the str visualize sub type
     */
    public String getStrVisualizeSubType() {
        return strVisualizeSubType;
    }

    /**
     * Sets the str visualize sub type.
     *
     * @param strVisualizeSubType
     *            the new str visualize sub type
     */
    public void setStrVisualizeSubType(final String strVisualizeSubType) {
        this.strVisualizeSubType = strVisualizeSubType;
    }

    /**
     * Gets the str visualize config details.
     *
     * @return the str visualize config details
     */
    public String getStrVisualizeConfigDetails() {
        return strVisualizeConfigDetails;
    }

    /**
     * Sets the str visualize config details.
     *
     * @param strVisualizeConfigDetails
     *            the new str visualize config details
     */
    public void setStrVisualizeConfigDetails(
            final String strVisualizeConfigDetails) {
        this.strVisualizeConfigDetails = strVisualizeConfigDetails;
    }

    /**
     * Gets the date visualize.
     *
     * @return the date visualize
     */
    public Date getDateVisualize() {
        return dateVisualize;
    }

    /**
     * Sets the date visualize.
     *
     * @param dateVisualize
     *            the new date visualize
     */
    public void setDateVisualize(final Date dateVisualize) {
        this.dateVisualize = dateVisualize;
    }

    /**
     * Gets the date updated visualize.
     *
     * @return the date updated visualize
     */
    public Date getDateUpdatedVisualize() {
        return dateUpdatedVisualize;
    }

    /**
     * Sets the date updated visualize.
     *
     * @param dateUpdatedVisualize
     *            the new date updated visualize
     */
    public void setDateUpdatedVisualize(final Date dateUpdatedVisualize) {
        this.dateUpdatedVisualize = dateUpdatedVisualize;
    }

    /**
     * Gets the kpis.
     *
     * @return the kpis
     */
    public Set<Kpi> getKpis() {
        return kpis;
    }

    /**
     * Sets the kpis.
     *
     * @param kpis
     *            the new kpis
     */
    public void setKpis(final Set<Kpi> kpis) {
        this.kpis = kpis;
    }

    /**
     * Gets the pipeline.
     *
     * @return the pipeline
     */
    public Pipeline getPipeline() {
        return pipeline;
    }

    /**
     * Sets the pipeline.
     *
     * @param pipeline
     *            the new pipeline
     */
    public void setPipeline(final Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    /**
     * Gets the delete status.
     *
     * @return the delete status
     */
    public int getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * Sets the delete status.
     *
     * @param deleteStatus
     *            the new delete status
     */
    public void setDeleteStatus(final int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * Gets the in visualize entity id.
     *
     * @return the in visualize entity id
     */
    public int getInVisualizeEntityId() {
        return inVisualizeEntityId;
    }

    /**
     * Sets the in visualize entity id.
     *
     * @param inVisualizeEntityId
     *            the new in visualize entity id
     */
    public void setInVisualizeEntityId(final int inVisualizeEntityId) {
        this.inVisualizeEntityId = inVisualizeEntityId;
    }

    /**
     * Gets the str key space.
     *
     * @return the str key space
     */
    public String getStrKeySpace() {
        return strKeySpace;
    }

    /**
     * Sets the str key space.
     *
     * @param strKeySpace
     *            the new str key space
     */
    public void setStrKeySpace(final String strKeySpace) {
        this.strKeySpace = strKeySpace;
    }
}
