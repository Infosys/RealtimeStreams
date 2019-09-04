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
package org.streamconnect.dss.access.api.response;

import java.io.Serializable;
import java.util.List;

/**
 * This is the mocked user session Object for the distributed Systems.
 *
 * @version 1.0
 */
public class UserSession implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1856862670651243395L;

    /** The user name. */
    private String userName;

    /** The token. */
    private String token;

    /** The roles. */
    private List<Role> roles;

    /** The Default role. */
    private int defaultRole;

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName
     *            the new user name
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * Gets the token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token.
     *
     * @param token
     *            the new token
     */
    public void setToken(final String token) {
        this.token = token;
    }

    /**
     * Gets the roles.
     *
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the roles.
     *
     * @param roles
     *            the new roles
     */
    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Gets the default role.
     *
     * @return the default role
     */
    public int getDefaultRole() {
        return defaultRole;
    }

    /**
     * Sets the default role.
     *
     * @param defaultRole
     *            the new default role
     */
    public void setDefaultRole(int defaultRole) {
        this.defaultRole = defaultRole;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserSession other = (UserSession) obj;
        if (userName == null) {
            if (other.userName != null) {
                return false;
            }
        } else if (!userName.equals(other.userName)) {
            return false;
        }
        return true;
    }

}
