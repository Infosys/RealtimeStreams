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
import java.util.List;

/**
 * The Class AccessLevel.
 *
 * @version 1.0
 */
public class AccessLevel implements Serializable {

    /** The in access id. */
    private int inAccessId;

    /** The str access level name. */
    private String strAccessLevelName;

    /** The portal accesses. */
    private List<PortalAccess> portalAccesses;

    /** The portal list. */
    private List<Portal> portalList;

    /**
     * Gets the in access id.
     *
     * @return the in access id
     */
    public int getInAccessId() {
        return inAccessId;
    }

    /**
     * Sets the in access id.
     *
     * @param inAccessId
     *            the new in access id
     */
    public void setInAccessId(final int inAccessId) {
        this.inAccessId = inAccessId;
    }

    /**
     * Gets the str access level name.
     *
     * @return the str access level name
     */
    public String getStrAccessLevelName() {
        return strAccessLevelName;
    }

    /**
     * Sets the str access level name.
     *
     * @param strAccessLevelName
     *            the new str access level name
     */
    public void setStrAccessLevelName(final String strAccessLevelName) {
        this.strAccessLevelName = strAccessLevelName;
    }

    /**
     * Gets the portal accesses.
     *
     * @return the portal accesses
     */
    public List<PortalAccess> getPortalAccesses() {
        return portalAccesses;
    }

    /**
     * Sets the portal accesses.
     *
     * @param portalAccesses
     *            the new portal accesses
     */
    public void setPortalAccesses(final List<PortalAccess> portalAccesses) {
        this.portalAccesses = portalAccesses;
    }

    /**
     * Gets the portal list.
     *
     * @return the portal list
     */
    public List<Portal> getPortalList() {
        return portalList;
    }

    /**
     * Sets the portal list.
     *
     * @param portalList
     *            the new portal list
     */
    public void setPortalList(final List<Portal> portalList) {
        this.portalList = portalList;
    }
}
