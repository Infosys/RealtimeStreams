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

import javax.persistence.*;
import java.util.Date;

/**
 * The class User Function
 */
@Table(name = "tbl_user_function")
@Entity
public class UserFunction implements java.io.Serializable {

    /**The user defined function id*/
    @Id
    @GeneratedValue
    @Column(name = "uf_id")
    private int inUfId;

    /**The user defined function name*/
    @Column(name = "uf_name")
    private String strUfName;

    /**The user defined function's register method*/
    @Column(name = "uf_register_method")
    private String strRegisterMethod;

    /**The user defined function's code base*/
    @Lob
    @Column(name = "uf_code_base")
    private String strCodeBase;

    /**The user defined function config name*/
    @Column(name = "uf_config_name")
    private String strUfConfigName;

    /**The user defined function description*/
    @Column(name = "uf_desc")
    private String strUfDesc;

    /** The delete status. */
    @Column(name = "uf_delete_status")
    private int deleteStatus;

    /** The user function created  date.*/
    @Column(name = "uf_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreatedUf;

    /** The user function updated  date.*/
    @Column(name = "uf_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdatedUf;

    /** The created by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",referencedColumnName = "user_id")
    private User createdBy;

    /** The updated by. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by",referencedColumnName = "user_id")
    private User updatedBy;

    /**
     * Instantiates a new User Function.
     */
    public UserFunction(){
        super();
    }

    /**
     * Gets the user function id.
     *
     * @return the user function id
     */
    public int getInUfId() {
        return inUfId;
    }

    /**
     * Sets the user function id.
     *
     * @param inUfId
     *            the new user function id
     */
    public void setInUfId(int inUfId) {
        this.inUfId = inUfId;
    }

    /**
     * Gets the str user function name.
     *
     * @return the str user function name
     */
    public String getStrUfName() {
        return strUfName;
    }

    /**
     * Sets the str user function name.
     *
     * @param strUfName
     *            the new str user function name.
     */
    public void setStrUfName(String strUfName) {
        this.strUfName = strUfName;
    }

    /**
     * Gets the str user function register method.
     *
     * @return the str user function register method.
     */
    public String getStrRegisterMethod() {
        return strRegisterMethod;
    }

    /**
     * Sets the str user function register method.
     *
     * @param strRegisterMethod
     *            the new str user function register method.
     */
    public void setStrRegisterMethod(String strRegisterMethod) {
        this.strRegisterMethod = strRegisterMethod;
    }

    /**
     * Gets the str user function code base.
     *
     * @return the str user function code base.
     */
    public String getStrCodeBase() {
        return strCodeBase;
    }

    /**
     * Sets the str user function code base.
     *
     * @param strCodeBase
     *            the new str user function code base.
     */
    public void setStrCodeBase(String strCodeBase) {
        this.strCodeBase = strCodeBase;
    }

    /**
     * Gets the str user function config name
     *
     * @return the str user function config name
     */
    public String getStrUfConfigName() {
        return strUfConfigName;
    }

    /**
     * Sets the str user function config name
     *
     * @param strUfConfigName
     *            the new str user function config name
     */
    public void setStrUfConfigName(String strUfConfigName) {
        this.strUfConfigName = strUfConfigName;
    }

    /**
     * Gets the str user function description
     *
     * @return the str user function desc
     */
    public String getStrUfDesc() {
        return strUfDesc;
    }

    /**
     * Sets the str user function description
     *
     * @param strUfDesc
     *            the new str user function main class name.
     */
    public void setStrUfDesc(String strUfDesc) {
        this.strUfDesc = strUfDesc;
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
    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * Gets the date created user function.
     *
     * @return the date created user function
     */
    public Date getDateCreatedUf() {
        return dateCreatedUf;
    }

    /**
     * Sets the date created user function.
     *
     * @param dateCreatedUf
     *            the new date created user function
     */
    public void setDateCreatedUf(Date dateCreatedUf) {
        this.dateCreatedUf = dateCreatedUf;
    }

    /**
     * Gets the date updated user function.
     *
     * @return the date updated user function
     */
    public Date getDateUpdatedUf() {
        return dateUpdatedUf;
    }

    /**
     * Sets the date updated user function.
     *
     * @param dateUpdatedUf
     *            the new date updated user function
     */
    public void setDateUpdatedUf(Date dateUpdatedUf) {
        this.dateUpdatedUf = dateUpdatedUf;
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
}
