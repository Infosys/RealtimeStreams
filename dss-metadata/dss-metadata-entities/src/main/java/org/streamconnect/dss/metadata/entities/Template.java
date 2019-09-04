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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * The Class Template.
 *
 * @version 1.0
 */
@Table(name = "tbl_template")
@Entity
public class Template {

    /** The in template id. */
    @Id
    @GeneratedValue
    @Column(name = "tmplte_id")
    private int inTemplateId;

    /** The str template name. */
    @Column(name = "tmplte_name")
    private String strTemplateName;

    /** The str source type. */
    @Column(name = "tmplte_src_type")
    private String strSourceType;

    /** The str process type. */
    @Column(name = "tmplte_prcs_type")
    private String strProcessType;

    /** The str data type. */
    @Column(name = "tmplte_data_type")
    private String strDataType;

    /** The str lookup type. */
    @Column(name = "tmplte_lookup_type")
    private String strLookupType;

    /** The str algorithm type. */
    @Column(name = "tmplte_algorithm_type")
    private String strAlgorithmType;

    /** The str sink type. */
    @Column(name = "tmplte_sink_type")
    private String strSinkType;

    /** The bytes template file one. */
    @Lob
    @Column(name = "tmplte_file_one")
    private byte[] bytesTemplateFileOne;

    /** The bytes template file two. */
    @Lob
    @Column(name = "tmplte_file_two")
    private byte[] bytesTemplateFileTwo;

    /** The bytes template file three. */
    @Lob
    @Column(name = "tmplte_file_three")
    private byte[] bytesTemplateFileThree;

    /** The bytes template file four. */
    @Lob
    @Column(name = "tmplte_file_four")
    private byte[] bytesTemplateFileFour;

    /**
     * Gets the in template id.
     *
     * @return the in template id
     */
    public int getInTemplateId() {
        return inTemplateId;
    }

    /**
     * Sets the in template id.
     *
     * @param inTemplateId
     *            the new in template id
     */
    public void setInTemplateId(final int inTemplateId) {
        this.inTemplateId = inTemplateId;
    }

    /**
     * Gets the str template name.
     *
     * @return the str template name
     */
    public String getStrTemplateName() {
        return strTemplateName;
    }

    /**
     * Sets the str template name.
     *
     * @param strTemplateName
     *            the new str template name
     */
    public void setStrTemplateName(final String strTemplateName) {
        this.strTemplateName = strTemplateName;
    }

    /**
     * Gets the str source type.
     *
     * @return the str source type
     */
    public String getStrSourceType() {
        return strSourceType;
    }

    /**
     * Sets the str source type.
     *
     * @param strSourceType
     *            the new str source type
     */
    public void setStrSourceType(final String strSourceType) {
        this.strSourceType = strSourceType;
    }

    /**
     * Gets the str process type.
     *
     * @return the str process type
     */
    public String getStrProcessType() {
        return strProcessType;
    }

    /**
     * Sets the str process type.
     *
     * @param strProcessType
     *            the new str process type
     */
    public void setStrProcessType(final String strProcessType) {
        this.strProcessType = strProcessType;
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
     * Gets the bytes template file one.
     *
     * @return the bytes template file one
     */
    public byte[] getBytesTemplateFileOne() {
        return bytesTemplateFileOne;
    }

    /**
     * Sets the bytes template file one.
     *
     * @param bytesTemplateFileOne
     *            the new bytes template file one
     */
    public void setBytesTemplateFileOne(final byte[] bytesTemplateFileOne) {
        this.bytesTemplateFileOne = bytesTemplateFileOne;
    }

    /**
     * Gets the bytes template file two.
     *
     * @return the bytes template file two
     */
    public byte[] getBytesTemplateFileTwo() {
        return bytesTemplateFileTwo;
    }

    /**
     * Sets the bytes template file two.
     *
     * @param bytesTemplateFileTwo
     *            the new bytes template file two
     */
    public void setBytesTemplateFileTwo(final byte[] bytesTemplateFileTwo) {
        this.bytesTemplateFileTwo = bytesTemplateFileTwo;
    }

    /**
     * Gets the bytes template file three.
     *
     * @return the bytes template file three
     */
    public byte[] getBytesTemplateFileThree() {
        return bytesTemplateFileThree;
    }

    /**
     * Sets the bytes template file three.
     *
     * @param bytesTemplateFileThree
     *            the new bytes template file three
     */
    public void setBytesTemplateFileThree(final byte[] bytesTemplateFileThree) {
        this.bytesTemplateFileThree = bytesTemplateFileThree;
    }

    /**
     * Gets the bytes template file four.
     *
     * @return the bytes template file four
     */
    public byte[] getBytesTemplateFileFour() {
        return bytesTemplateFileFour;
    }

    /**
     * Sets the bytes template file four.
     *
     * @param bytesTemplateFileFour
     *            the new bytes template file four
     */
    public void setBytesTemplateFileFour(final byte[] bytesTemplateFileFour) {
        this.bytesTemplateFileFour = bytesTemplateFileFour;
    }

    /**
     * Gets the str data type.
     *
     * @return the str data type
     */
    public String getStrDataType() {
        return strDataType;
    }

    /**
     * Sets the str data type.
     *
     * @param strDataType
     *            the new str data type
     */
    public void setStrDataType(final String strDataType) {
        this.strDataType = strDataType;
    }

    /**
     * Gets the str lookup type.
     *
     * @return the str lookup type
     */
    public String getStrLookupType() {
        return strLookupType;
    }

    /**
     * Sets the str lookup type.
     *
     * @param strLookupType
     *            the new str lookup type
     */
    public void setStrLookupType(final String strLookupType) {
        this.strLookupType = strLookupType;
    }

    /**
     * Gets the str algorithm type.
     *
     * @return the str algorithm type
     */
    public String getStrAlgorithmType() {
        return strAlgorithmType;
    }

    /**
     * Sets the str algorithm type.
     *
     * @param strAlgorithmType
     *            the new str algorithm type
     */
    public void setStrAlgorithmType(final String strAlgorithmType) {
        this.strAlgorithmType = strAlgorithmType;
    }
}
