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
package org.streamconnect.dss.engine.audit;

/**
 * Interface for the Audit- For Generating Metainformation Code
 * for fetching source/process/error/details
 */
public interface IAuditProcesser {

    /**
     * Function to Generate Create Table Script for Audit Data
     * @return
     */
    boolean buildCreateAuditTableLogic();

    /**
     * Function to Generate Save Raw Data Logic
     * @return
     */
    boolean buildSaveRawDataLogic();

    /**
     * Function to Generate Error Information Fetch Logic
     * @return
     */

    boolean buildErrorInfoFetchLogic();

    /**
     * Function to Generate Process Information Fetch Logic
     * @return
     */
    boolean buildProcessInfoFetchCode();

    /**
     * Function to Generate  Logic for creating Elastic Search Index
     * @return
     */
    boolean buildCreateELIndexLogic();

    /**
     * Function to Generate logic for creating Grafana Dashboard
     * @return
     */
    boolean buildCreateGrafanaDashbordLogic();
}
