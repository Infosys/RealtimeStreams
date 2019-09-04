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

import java.io.Serializable;


/**
 * The type Entity schema.
 */
public class EntitySchema implements Serializable {

    /** The name. */
    private String name;

    /** The type. */
    private String type;

    /** The initial name. */
    private String initialName;

    /** The selected. */
    private boolean selected;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets initial name.
     *
     * @return the initial name
     */
    public String getInitialName() {
        return initialName;
    }

    /**
     * Sets initial name.
     *
     * @param initialName the initial name
     */
    public void setInitialName(final String initialName) {
        this.initialName = initialName;
    }

    /**
     * Is selected boolean.
     *
     * @return the boolean
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets selected.
     *
     * @param selected the selected
     */
    public void setSelected(final boolean selected) {
        this.selected = selected;
    }
}


