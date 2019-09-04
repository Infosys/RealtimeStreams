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

import java.util.List;

/**
 * The type Kafka stream src dto.
 */

public class KafkaStreamSrcDto implements java.io.Serializable {

    /** The str client id. */
    private String strClientId;

    /** The str group name. */
    private String strGroupName;

    /** The str topic name. */
    private String strTopicName;

    /** The str brokerslst. */
    private String strBrokerslst;

    /** The str zookeeperlst. */
    private String strZookeeperlst;

    /** The str offset. */
    private String strOffset;

    /** The str key serializer. */
    private String strKeySerializer;

    /** The str val serializer. */
    private String strValSerializer;

    /** The num thread. */
    private int numThread;

    /** The n session tmout. */
    private long nSessionTmout;

    /** The n conn tmout. */
    private long nConnTmout;

    /** The partition details. */
    private long partitionDetails;

    /** The addl params. */
    private List<KeyValueDto> addlParams;

    /**
     * Gets str client id.
     *
     * @return the str client id
     */
    public String getStrClientId() {
        return strClientId;
    }

    /**
     * Sets str client id.
     *
     * @param strClientId the str client id
     */
    public void setStrClientId(final String strClientId) {
        this.strClientId = strClientId;
    }

    /**
     * Gets str group name.
     *
     * @return the str group name
     */
    public String getStrGroupName() {
        return strGroupName;
    }

    /**
     * Sets str group name.
     *
     * @param strGroupName the str group name
     */
    public void setStrGroupName(final String strGroupName) {
        this.strGroupName = strGroupName;
    }

    /**
     * Gets str topic name.
     *
     * @return the str topic name
     */
    public String getStrTopicName() {
        return strTopicName;
    }

    /**
     * Sets str topic name.
     *
     * @param strTopicName the str topic name
     */
    public void setStrTopicName(final String strTopicName) {
        this.strTopicName = strTopicName;
    }

    /**
     * Gets str brokerslst.
     *
     * @return the str brokerslst
     */
    public String getStrBrokerslst() {
        return strBrokerslst;
    }

    /**
     * Sets str brokerslst.
     *
     * @param strBrokerslst the str brokerslst
     */
    public void setStrBrokerslst(final String strBrokerslst) {
        this.strBrokerslst = strBrokerslst;
    }

    /**
     * Gets str zookeeperlst.
     *
     * @return the str zookeeperlst
     */
    public String getStrZookeeperlst() {
        return strZookeeperlst;
    }

    /**
     * Sets str zookeeperlst.
     *
     * @param strZookeeperlst the str zookeeperlst
     */
    public void setStrZookeeperlst(final String strZookeeperlst) {
        this.strZookeeperlst = strZookeeperlst;
    }

    /**
     * Gets str offset.
     *
     * @return the str offset
     */
    public String getStrOffset() {
        return strOffset;
    }

    /**
     * Sets str offset.
     *
     * @param strOffset the str offset
     */
    public void setStrOffset(final String strOffset) {
        this.strOffset = strOffset;
    }

    /**
     * Gets str key serializer.
     *
     * @return the str key serializer
     */
    public String getStrKeySerializer() {
        return strKeySerializer;
    }

    /**
     * Sets str key serializer.
     *
     * @param strKeySerializer the str key serializer
     */
    public void setStrKeySerializer(final String strKeySerializer) {
        this.strKeySerializer = strKeySerializer;
    }

    /**
     * Gets str val serializer.
     *
     * @return the str val serializer
     */
    public String getStrValSerializer() {
        return strValSerializer;
    }

    /**
     * Sets str val serializer.
     *
     * @param strValSerializer the str val serializer
     */
    public void setStrValSerializer(final String strValSerializer) {
        this.strValSerializer = strValSerializer;
    }

    /**
     * Gets num thread.
     *
     * @return the num thread
     */
    public int getNumThread() {
        return numThread;
    }

    /**
     * Sets num thread.
     *
     * @param numThread the num thread
     */
    public void setNumThread(final int numThread) {
        this.numThread = numThread;
    }

    /**
     * Gets session tmout.
     *
     * @return the session tmout
     */
    public long getnSessionTmout() {
        return nSessionTmout;
    }

    /**
     * Sets session tmout.
     *
     * @param nSessionTmout the n session tmout
     */
    public void setnSessionTmout(final long nSessionTmout) {
        this.nSessionTmout = nSessionTmout;
    }

    /**
     * Gets conn tmout.
     *
     * @return the conn tmout
     */
    public long getnConnTmout() {
        return nConnTmout;
    }

    /**
     * Sets conn tmout.
     *
     * @param nConnTmout the n conn tmout
     */
    public void setnConnTmout(final long nConnTmout) {
        this.nConnTmout = nConnTmout;
    }

    /**
     * Gets partition details.
     *
     * @return the partition details
     */
    public long getPartitionDetails() {
        return partitionDetails;
    }

    /**
     * Sets partition details.
     *
     * @param partitionDetails the partition details
     */
    public void setPartitionDetails(final long partitionDetails) {
        this.partitionDetails = partitionDetails;
    }

    /**
     * Gets addl params.
     *
     * @return the addl params
     */
    public List<KeyValueDto> getAddlParams() {
        return addlParams;
    }

    /**
     * Sets addl params.
     *
     * @param addlParams the addl params
     */
    public void setAddlParams(final List<KeyValueDto> addlParams) {
        this.addlParams = addlParams;
    }

    /**
     * Instantiates a new Kafka stream src dto.
     */
    public KafkaStreamSrcDto() {
        super();
    }

}
