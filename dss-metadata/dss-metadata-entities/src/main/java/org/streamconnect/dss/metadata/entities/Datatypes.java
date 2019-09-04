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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Datatypes.
 *
 * @version 1.0
 */
@Table(name = "tbl_datatypes")
@Entity
public class Datatypes implements Serializable {

    /** The id. */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    /** The str sink type. */
    @Column(name = "sink_type")
    private String strSinkType;

    /** The str sink data types. */
    @Column(name = "sink_data_types")
    private String strSinkDataTypes;

    /** The date created. */
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    /** The date updated. */
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    /**
     * Instantiates a new datatypes.
     */
    public Datatypes() {
        super();
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets the str sink type.
     *
     * @return the str sink type
     */
    public String getStrSinkType() {
        return strSinkType;
    }

    /**
     * Sets the str sink type.
     *
     * @param strSinkType
     *            the new str sink type
     */
    public void setStrSinkType(final String strSinkType) {
        this.strSinkType = strSinkType;
    }

    /**
     * Gets the str sink data types.
     *
     * @return the str sink data types
     */
    public String getStrSinkDataTypes() {
        return strSinkDataTypes;
    }

    /**
     * Sets the str sink data types.
     *
     * @param strSinkDataTypes
     *            the new str sink data types
     */
    public void setStrSinkDataTypes(final String strSinkDataTypes) {
        this.strSinkDataTypes = strSinkDataTypes;
    }

    /**
     * Gets the date created.
     *
     * @return the date created
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the date created.
     *
     * @param dateCreated
     *            the new date created
     */
    public void setDateCreated(final Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Gets the date updated.
     *
     * @return the date updated
     */
    public Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * Sets the date updated.
     *
     * @param dateUpdated
     *            the new date updated
     */
    public void setDateUpdated(final Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
