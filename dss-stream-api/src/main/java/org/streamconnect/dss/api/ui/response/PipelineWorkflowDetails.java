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

/**
 * The Class PipelineWorkflowDetails.
 *
 * @version 1.0
 */
public class PipelineWorkflowDetails implements Serializable {


    /** The name. */
    private String name;

    /** The display name. */
    private String displayName;

    /** The id. */
    private int id;

    /** The x. */
    private int x;

    /** The y. */
    private int y;

    /** The color. */
    private String color;

    /** The border color. */
    private String borderColor;

    /** The properties. */
    private Object properties;

    /** The connectors. */
    private Object connectors;

    /** The icon. */
    private String icon;

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
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the display name.
     *
     * @param displayName the new display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Gets border color.
     *
     * @return the border color
     */
    public String getBorderColor() {
        return borderColor;
    }

    /**
     * Sets border color.
     *
     * @param borderColor the border color
     */
    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * Gets properties.
     *
     * @return the properties
     */
    public Object getProperties() {
        return properties;
    }

    /**
     * Sets properties.
     *
     * @param properties the properties
     */
    public void setProperties(Object properties) {
        this.properties = properties;
    }

    /**
     * Gets connectors.
     *
     * @return the connectors
     */
    public Object getConnectors() {
        return connectors;
    }

    /**
     * Sets connectors.
     *
     * @param connectors the connectors
     */
    public void setConnectors(Object connectors) {
        this.connectors = connectors;
    }

    /**
     * Gets icon.
     *
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets icon.
     *
     * @param icon the icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}
