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
        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.FetchType;
        import javax.persistence.GeneratedValue;
        import javax.persistence.Id;
        import javax.persistence.JoinColumn;
        import javax.persistence.Lob;
        import javax.persistence.OneToOne;
        import javax.persistence.Table;
        import javax.persistence.Temporal;
        import javax.persistence.TemporalType;

/**
 * The Class Process.
 *
 * @version 1.0
 */
@Table(name = "tbl_process")

@Entity
public class Process implements Serializable {

    /** The in process id. */
    @Id
    @GeneratedValue
    @Column(name = "prcs_id")
    private int inProcessId;

    /** The str process config name. */
    @Column(name = "prcs_config_name")
    private String strProcessConfigName;

    /** The str process type. */
    @Column(name = "prcs_type")
    private String strProcessType;

    /** The str process config details. */
    @Lob
    @Column(name = "prcs_config_details")
    private String strProcessConfigDetails;

    /** The str queries. */
    @Column(name = "prcs_query")
    private String strQueries;

    /** The date process. */
    @Column(name = "prcs_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateProcess;

    /** The delete status. */
    @Column(name = "prcs_delete_status")
    private int deleteStatus;

    /** The date updated process. */
    @Column(name = "prcs_date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdatedProcess;

    /** The created by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",referencedColumnName = "user_id")
    private User createdBy;

    /** The updated by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by",referencedColumnName = "user_id")
    private User updatedBy;


    /**
     * Instantiates a new process.
     */
    public Process() {
        super();
    }

    /**
     * Gets the in process id.
     *
     * @return the inProcessId
     */
    public int getInProcessId() {
        return inProcessId;
    }

    /**
     * Sets the in process id.
     *
     * @param inProcessId
     *            the inProcessId to set
     */
    public void setInProcessId(final int inProcessId) {
        this.inProcessId = inProcessId;
    }

    /**
     * Gets the str process config name.
     *
     * @return the strProcessConfigName
     */
    public String getStrProcessConfigName() {
        return strProcessConfigName;
    }

    /**
     * Sets the str process config name.
     *
     * @param strProcessConfigName
     *            the strProcessConfigName to set
     */
    public void setStrProcessConfigName(final String strProcessConfigName) {
        this.strProcessConfigName = strProcessConfigName;
    }

    /**
     * Gets the str process type.
     *
     * @return the strProcessType
     */
    public String getStrProcessType() {
        return strProcessType;
    }

    /**
     * Sets the str process type.
     *
     * @param strProcessType
     *            the strProcessType to set
     */
    public void setStrProcessType(final String strProcessType) {
        this.strProcessType = strProcessType;
    }

    /**
     * Gets the str process config details.
     *
     * @return the objConfigDetails
     */
    public String getStrProcessConfigDetails() {
        return strProcessConfigDetails;
    }

    /**
     * Sets the str process config details.
     *
     * @param strProcessConfigDetails
     *            the objConfigDetails to set
     */
    public void setStrProcessConfigDetails(
            final String strProcessConfigDetails) {
        this.strProcessConfigDetails = strProcessConfigDetails;
    }

    /**
     * Gets the str queries.
     *
     * @return the objQueries
     */
    public String getStrQueries() {
        return strQueries;
    }

    /**
     * Sets the str queries.
     *
     * @param strQueries
     *            the ObjQueries to set
     */
    public void setStrQueries(final String strQueries) {
        this.strQueries = strQueries;
    }

    /**
     * Gets the date process.
     *
     * @return the date process
     */
    public Date getDateProcess() {
        return dateProcess;
    }

    /**
     * Sets the date process.
     *
     * @param dateProcess
     *            the new date process
     */
    public void setDateProcess(final Date dateProcess) {
        this.dateProcess = dateProcess;
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
     * Gets the date updated process.
     *
     * @return the date updated process
     */
    public Date getDateUpdatedProcess() {
        return dateUpdatedProcess;
    }

    /**
     * Sets the date updated process.
     *
     * @param dateUpdatedProcess
     *            the new date updated process
     */
    public void setDateUpdatedProcess(final Date dateUpdatedProcess) {
        this.dateUpdatedProcess = dateUpdatedProcess;
    }


    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the new created by
     */
    public void setCreatedBy(final User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the updated by.
     *
     * @return the updated by
     */
    public User getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Sets the updated by.
     *
     * @param updatedBy the new updated by
     */
    public void setUpdatedBy(final User updatedBy) {
        this.updatedBy = updatedBy;
    }


    /** The in user id. */
    private int inUserId;

    /**
     * Gets in user id.
     *
     * @return the in user id
     */
    public int getInUserId() {
        return inUserId;
    }
}
