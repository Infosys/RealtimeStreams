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
 * The type Spark process dto.
 */
public class SparkProcessDto {

    /** The str spark master. */
    private String strSparkMaster;

    /** The n batch interval. */
    private long nBatchInterval;

    /** The n exc maxcore. */
    private long nExcMaxcore;

    /** The n exc maxmem. */
    private long nExcMaxmem;

    /** The n drv maxmem. */
    private long nDrvMaxmem;

    /** The local threads. */
    private long localThreads;

    /** The str spark app name. */
    private String strSparkAppName;

    /** The standalone URL. */
    private String standaloneURL;

    /** The addl params. */
    private List<KeyValueDto> addlParams;


    /**
     * Instantiates a new Spark process dto.
     */
    public SparkProcessDto() {
        super();
    }

    /**
     * Gets str spark master.
     *
     * @return the str spark master
     */
    public String getStrSparkMaster() {
        return strSparkMaster;
    }

    /**
     * Sets str spark master.
     *
     * @param strSparkMaster the str spark master
     */
    public void setStrSparkMaster(final String strSparkMaster) {
        this.strSparkMaster = strSparkMaster;
    }

    /**
     * Gets batch interval.
     *
     * @return the batch interval
     */
    public long getnBatchInterval() {
        return nBatchInterval;
    }

    /**
     * Sets batch interval.
     *
     * @param nBatchInterval the n batch interval
     */
    public void setnBatchInterval(final long nBatchInterval) {
        this.nBatchInterval = nBatchInterval;
    }

    /**
     * Gets exc maxcore.
     *
     * @return the exc maxcore
     */
    public long getnExcMaxcore() {
        return nExcMaxcore;
    }

    /**
     * Sets exc maxcore.
     *
     * @param nExcMaxcore the n exc maxcore
     */
    public void setnExcMaxcore(final long nExcMaxcore) {
        this.nExcMaxcore = nExcMaxcore;
    }

    /**
     * Gets exc maxmem.
     *
     * @return the exc maxmem
     */
    public long getnExcMaxmem() {
        return nExcMaxmem;
    }

    /**
     * Sets exc maxmem.
     *
     * @param nExcMaxmem the n exc maxmem
     */
    public void setnExcMaxmem(final long nExcMaxmem) {
        this.nExcMaxmem = nExcMaxmem;
    }

    /**
     * Gets drv maxmem.
     *
     * @return the drv maxmem
     */
    public long getnDrvMaxmem() {
        return nDrvMaxmem;
    }

    /**
     * Sets drv maxmem.
     *
     * @param nDrvMaxmem the n drv maxmem
     */
    public void setnDrvMaxmem(final long nDrvMaxmem) {
        this.nDrvMaxmem = nDrvMaxmem;
    }

    /**
     * Gets standalone url.
     *
     * @return the standalone url
     */
    public String getStandaloneURL() {
        return standaloneURL;
    }

    /**
     * Sets standalone url.
     *
     * @param standaloneURL the standalone url
     */
    public void setStandaloneURL(final String standaloneURL) {
        this.standaloneURL = standaloneURL;
    }

    /**
     * Gets local threads.
     *
     * @return the local threads
     */
    public long getLocalThreads() {
        return localThreads;
    }

    /**
     * Sets local threads.
     *
     * @param localThreads the local threads
     */
    public void setLocalThreads(final long localThreads) {
        this.localThreads = localThreads;
    }

    /**
     * Gets str spark app name.
     *
     * @return the str spark app name
     */
    public String getStrSparkAppName() {
        return strSparkAppName;
    }

    /**
     * Sets str spark app name.
     *
     * @param strSparkAppName the str spark app name
     */
    public void setStrSparkAppName(final String strSparkAppName) {
        this.strSparkAppName = strSparkAppName;
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

}

