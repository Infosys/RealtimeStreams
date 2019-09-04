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
 * The Class IdNameVO.
 *
 */
public class IdNameVO {

    /** The id. */
    private int id;

    /** The name. */
    private String name;

    /** The type. */
    private String type;

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
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type
     *            the new type
     */
    public void setType(final String type) {
        this.type = type;
    }
}
