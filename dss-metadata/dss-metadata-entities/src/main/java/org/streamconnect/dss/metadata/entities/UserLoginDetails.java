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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class UserLoginDetails.
 *
 * @version 1.0
 */
@Table(name = "tbl_user_login_details")
@Entity
public class UserLoginDetails implements Serializable {

    /** The id. */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    /** The user. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /** The login date. */
    @Column(name = "login_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDate;

    /** The logout date. */
    @Column(name = "logout_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutDate;

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
     * Gets the user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user
     *            the new user
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * Gets the login date.
     *
     * @return the login date
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * Sets the login date.
     *
     * @param loginDate
     *            the new login date
     */
    public void setLoginDate(final Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * Gets the logout date.
     *
     * @return the logout date
     */
    public Date getLogoutDate() {
        return logoutDate;
    }

    /**
     * Sets the logout date.
     *
     * @param logoutDate
     *            the new logout date
     */
    public void setLogoutDate(final Date logoutDate) {
        this.logoutDate = logoutDate;
    }
}
