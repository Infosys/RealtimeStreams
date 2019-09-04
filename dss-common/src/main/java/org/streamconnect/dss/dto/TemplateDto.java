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


/**
 * The type Template dto.
 */
public class TemplateDto implements java.io.Serializable {

    /** The in template id. */
    private int inTemplateId;

    /** The str template name. */
    private String strTemplateName;

    /** The str source type. */
    private String strSourceType;

    /** The str process type. */
    private String strProcessType;

    /** The str sink type. */
    private String strSinkType;

    /** The bytes template file one. */
    private byte[] bytesTemplateFileOne;

    /** The bytes template file two. */
    private byte[]  bytesTemplateFileTwo;

    /** The bytes template file three. */
    private byte[] bytesTemplateFileThree;

    /** The bytes template file four. */
    private byte[] bytesTemplateFileFour;


    /**
     * Gets in template id.
     *
     * @return the in template id
     */
    public int getInTemplateId() {
        return inTemplateId;
    }

    /**
     * Sets in template id.
     *
     * @param inTemplateId the in template id
     */
    public void setInTemplateId(final int inTemplateId) {
        this.inTemplateId = inTemplateId;
    }

    /**
     * Gets str template name.
     *
     * @return the str template name
     */
    public String getStrTemplateName() {
        return strTemplateName;
    }

    /**
     * Sets str template name.
     *
     * @param strTemplateName the str template name
     */
    public void setStrTemplateName(final String strTemplateName) {
        this.strTemplateName = strTemplateName;
    }

    /**
     * Gets str source type.
     *
     * @return the str source type
     */
    public String getStrSourceType() {
        return strSourceType;
    }

    /**
     * Sets str source type.
     *
     * @param strSourceType the str source type
     */
    public void setStrSourceType(final String strSourceType) {
        this.strSourceType = strSourceType;
    }

    /**
     * Gets str process type.
     *
     * @return the str process type
     */
    public String getStrProcessType() {
        return strProcessType;
    }

    /**
     * Sets str process type.
     *
     * @param strProcessType the str process type
     */
    public void setStrProcessType(final String strProcessType) {
        this.strProcessType = strProcessType;
    }

    /**
     * Gets str sink type.
     *
     * @return the str sink type
     */
    public String getStrSinkType() {
        return strSinkType;
    }

    /**
     * Sets str sink type.
     *
     * @param strSinkType the str sink type
     */
    public void setStrSinkType(final String strSinkType) {
        this.strSinkType = strSinkType;
    }

    /**
     * Get bytes template file one byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getBytesTemplateFileOne() {
        return bytesTemplateFileOne;
    }

    /**
     * Sets bytes template file one.
     *
     * @param bytesTemplateFileOne the bytes template file one
     */
    public void setBytesTemplateFileOne(final byte[] bytesTemplateFileOne) {
        this.bytesTemplateFileOne = bytesTemplateFileOne;
    }

    /**
     * Get bytes template file two byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getBytesTemplateFileTwo() {
        return bytesTemplateFileTwo;
    }

    /**
     * Sets bytes template file two.
     *
     * @param bytesTemplateFileTwo the bytes template file two
     */
    public void setBytesTemplateFileTwo(final byte[] bytesTemplateFileTwo) {
        this.bytesTemplateFileTwo = bytesTemplateFileTwo;
    }

    /**
     * Get bytes template file three byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getBytesTemplateFileThree() {
        return bytesTemplateFileThree;
    }

    /**
     * Sets bytes template file three.
     *
     * @param bytesTemplateFileThree the bytes template file three
     */
    public void setBytesTemplateFileThree(final byte[] bytesTemplateFileThree) {
        this.bytesTemplateFileThree = bytesTemplateFileThree;
    }

    /**
     * Get bytes template file four byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getBytesTemplateFileFour() {
        return bytesTemplateFileFour;
    }

    /**
     * Sets bytes template file four.
     *
     * @param bytesTemplateFileFour the bytes template file four
     */
    public void setBytesTemplateFileFour(final byte[] bytesTemplateFileFour) {
        this.bytesTemplateFileFour = bytesTemplateFileFour;
    }
}
