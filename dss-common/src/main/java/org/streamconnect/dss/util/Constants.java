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
package org.streamconnect.dss.util;

/**
 * The Class Constants.
 * @version 1.0
 */
public final class Constants {

    /**
     * Instantiates a new constants.
     */
    private Constants() {
        // private default constructor
    }

    /** The Constant SUCCESS_MESSAGE. */
    public static final String SUCCESS_MESSAGE = "SUCCESS";

    /** The Constant ERROR. */
    public static final String ERROR = "No Data Found";

    /** The Constant ERROR_STATUS. */
    public static final String ERROR_STATUS = "FAILED";

    /** The Constant START_STATUS. */
    public static final String START_STATUS = "START";

    /** The Constant END_STATUS. */
    public static final String END_STATUS = "END";

    /** The Constant NO_DATA_FOUND_ERROR_MESSAGE. */
    // public static final String ERROR_CODE = "2000";
    public static final String NO_DATA_FOUND_ERROR_MESSAGE = "No Data Found";

    /** The Constant NO_DATA_FOUND_ERROR_CODE. */
    public static final String NO_DATA_FOUND_ERROR_CODE = "1000";

    /** The Constant NAMENODE_URL. */
    public static final String NAMENODE_URL = "namenodeurl";

    /** The Constant FS_TYPE. */
    public static final String FS_TYPE = "fstype";

    /** The Constant FS_BASEPATH. */
    public static final String FS_BASEPATH = "fsbasepath";

    /** The Constant LIVY_BASEAPI. */
    public static final String LIVY_BASEAPI = "livybaseapi";

    /** The Constant KAFKASPARK_DEPENDJAR_1. */
    public static final String KAFKASPARK_DEPENDJAR_1 = "kafkasparkdependjar1";

    /** The Constant LOGIN_ERROR. */
    public static final String LOGIN_ERROR = "Error While Login";

    /** The Constant LOGOUT_ERROR. */
    public static final String LOGOUT_ERROR = "Error While Logout";

    /** The Constant AUTHENTICATE_ERROR. */
    public static final String AUTHENTICATE_ERROR = "Error While Authenticate ";
    /* User Access Level Constants */
    /** The Constant USER_LIST_ERROR. */
    public static final String USER_LIST_ERROR = "User Listing Failed";

    /** The Constant ROLE_SAVE_ERROR. */
    public static final String ROLE_SAVE_ERROR = "Role Save Failed";

    /** The Constant ROLE_SAVE_SUCCESS. */
    public static final String ROLE_SAVE_SUCCESS = "Role Saved Successfully";

    /** The Constant ROLE_DELETE_SUCCESS. */
    public static final String ROLE_DELETE_SUCCESS = "Role Deleted Successfully";

    /** The Constant ROLE_DELETE_ERROR. */
    public static final String ROLE_DELETE_ERROR = "Role Delete Failed";

    /** The Constant ROLE_MAP_ERROR. */
    public static final String ROLE_MAP_ERROR = "Role Mapping Failed";

    /** The Constant ROLE_MAP_SUCCESS. */
    public static final String ROLE_MAP_SUCCESS = "Role Mapped Successfully";

    /** The Constant ROLE_MAP_REMOVE_ERROR. */
    public static final String ROLE_MAP_REMOVE_ERROR = "Mapping Removal Failed";

    /** The Constant ROLE_MAP_REMOVE_SUCCESS. */
    public static final String ROLE_MAP_REMOVE_SUCCESS = "Mapping removed Successfully";

    /** The Constant ROLE_LIST_ERROR. */
    public static final String ROLE_LIST_ERROR = "Roles Listing Failed";

    /** The Constant ROLE_FETCHING_ERROR. */
    public static final String ROLE_FETCHING_ERROR = "User's Role Fetching Failed";

    /** The Constant FEATURE_LIST_ERROR. */
    public static final String FEATURE_LIST_ERROR = "Feature Listing Failed";

    /** The Constant ACCESS_MAP_ERROR. */
    public static final String ACCESS_MAP_ERROR = "Access Level Mapping Failed";

    /** The Constant ACCESS_MAP_SUCCESS. */
    public static final String ACCESS_MAP_SUCCESS = "Access Level Mapped Successfully";

    /** The Constant ACCESS_FETCH_ERROR. */
    public static final String ACCESS_FETCH_ERROR = "Access Fetching Failed";

    /** The Constant PORTAL_FETCH_ERROR. */
    public static final String PORTAL_FETCH_ERROR = "Portal Details Fetch Failed";

    /* Stream Builder Layer Constants */
    /** The Constant SOURCE_FETCH_ERROR. */
    public static final String SOURCE_FETCH_ERROR = "Source Fetching Failed";

    /** The Constant SOURCE_LIST_ERROR. */
    public static final String SOURCE_LIST_ERROR = "Source Listing Failed";

    /** The Constant SOURCE_SAVE_SUCCESS. */
    public static final String SOURCE_SAVE_SUCCESS = "Source Saved Successfully";

    /** The Constant SOURCE_SAVE_ERROR. */
    public static final String SOURCE_SAVE_ERROR = "Source Save Failed";

    /** The Constant SOURCE_SAVE_ERROR_SOURCE_EXIST. */
    public static final String SOURCE_SAVE_ERROR_SOURCE_EXIST = "Source Save Failed, "
            + " Source already exist";

    /** The Constant SINK_FETCH_ERROR. */
    public static final String SINK_FETCH_ERROR = "Sink Fetching Failed";

    /** The Constant SINK_LIST_ERROR. */
    public static final String SINK_LIST_ERROR = "Sink Listing Failed";

    /** The Constant SINK_SAVE_SUCCESS. */
    public static final String SINK_SAVE_SUCCESS = "Sink Saved Successfully";

    /** The Constant SINK_SAVE_ERROR. */
    public static final String SINK_SAVE_ERROR = "Sink Save Failed";

    /** The Constant SINK_SAVE_ERROR_SINK_EXIST. */
    public static final String SINK_SAVE_ERROR_SINK_EXIST = "Sink Save Failed, "
            + " Sink already exist";

    /** The Constant PROCESS_FETCH_ERROR. */
    public static final String PROCESS_FETCH_ERROR = "Process Fetching Failed";

    /** The Constant PROCESS_LIST_ERROR. */
    public static final String PROCESS_LIST_ERROR = "Process Listing Failed";

    /** The Constant PROCESS_SAVE_SUCCESS. */
    public static final String PROCESS_SAVE_SUCCESS = "Process Saved Successfully";

    /** The Constant PROCESS_SAVE_ERROR. */
    public static final String PROCESS_SAVE_ERROR = "Process Save Failed";

    /** The Constant PROCESS_SAVE_ERROR_PROCESS_EXIST. */
    public static final String PROCESS_SAVE_ERROR_PROCESS_EXIST = "Process Save Failed, "
            + " Process already exist";

    /** The Constant CATEGORY_FETCH_ERROR. */
    public static final String CATEGORY_FETCH_ERROR = "Category Fetching Failed";

    /** The Constant CATEGORY_LIST_ERROR. */
    public static final String CATEGORY_LIST_ERROR = "Category Listing Failed";

    /** The Constant CATEGORY_SAVE_SUCCESS. */
    public static final String CATEGORY_SAVE_SUCCESS = "Category Saved Successfully";

    /** The Constant CATEGORY_SAVE_ERROR. */
    public static final String CATEGORY_SAVE_ERROR = "Category Save Failed";

    /** The Constant CATEGORY_SAVE_ERROR_CATEGORY_EXIST. */
    public static final String CATEGORY_SAVE_ERROR_CATEGORY_EXIST = "Category Save Failed, "
            + " Category already exist";

    /** The Constant PIPELINE_FETCH_ERROR. */
    public static final String PIPELINE_FETCH_ERROR = "Pipeline Fetching Failed";

    /** The Constant PIPELINE_LIST_ERROR. */
    public static final String PIPELINE_LIST_ERROR = "Pipeline Listing Failed";

    /** The Constant PIPELINE_SAVE_SUCCESS. */
    public static final String PIPELINE_SAVE_SUCCESS = "Pipeline Saved Successfully";

    /** The Constant PIPELINE_SAVE_ERROR. */
    public static final String PIPELINE_SAVE_ERROR = "Pipeline Save Failed";

    /** The Constant VISUALIZATION_SAVE_ERROR. */
    public static final String VISUALIZATION_SAVE_ERROR = "Visualization Save Failed";

    /** The Constant VALID_VISUALIZATION. */
    public static final String VALID_VISUALIZATION = "Valid Visualization Name";

    /** The Constant PIPELINE_SAVE_ERROR_PIPELINE_EXIST. */
    public static final String PIPELINE_SAVE_ERROR_PIPELINE_EXIST = "Pipeline Save Failed, "
            + " Pipeline already exist";

    /** The Constant KPI_LIST_ERROR. */
    public static final String KPI_LIST_ERROR = "KPI Listing Failed";

    /** The Constant VISUALIZATION_NAME_CHECK_ERROR. */
    public static final String VISUALIZATION_NAME_CHECK_ERROR = "Visualization "
            + "Name Check Failed";

    /** The Constant KPI_FETCH_ERROR. */
    public static final String KPI_FETCH_ERROR = "KPI Fetching Failed";

    /** The Constant KPI_SAVE_SUCCESS. */
    public static final String KPI_SAVE_SUCCESS = "Kpi Saved Successfully";

    /** The Constant KPI_SAVE_ERROR. */
    public static final String KPI_SAVE_ERROR = "KPI Save Failed";

    /** The Constant KPI_SAVE_ERROR_KPI_EXIST. */
    public static final String KPI_SAVE_ERROR_KPI_EXIST = "KPI Save Failed, "
            + "KPI already exist";

    /** The Constant SOURCE_DELETE_SUCCESS. */
    public static final String SOURCE_DELETE_SUCCESS = "Source Deleted Successfully";

    /** The Constant SOURCE_DELETE_ERROR. */
    public static final String SOURCE_DELETE_ERROR = "Source Delete Failed";

    /** The Constant SINK_DELETE_SUCCESS. */
    public static final String SINK_DELETE_SUCCESS = "Sink Deleted Successfully";

    /** The Constant SINK_DELETE_ERROR. */
    public static final String SINK_DELETE_ERROR = "Sink Delete Failed";

    /** The Constant PROCESS_DELETE_SUCCESS. */
    public static final String PROCESS_DELETE_SUCCESS = "Process Deleted Successfully";

    /** The Constant PROCESS_DELETE_ERROR. */
    public static final String PROCESS_DELETE_ERROR = "Process Delete Failed";

    /** The Constant CATEGORY_DELETE_SUCCESS. */
    public static final String CATEGORY_DELETE_SUCCESS = "Category Deleted Successfully";

    /** The Constant CATEGORY_DELETE_ERROR. */
    public static final String CATEGORY_DELETE_ERROR = "Category Delete Failed";

    /** The Constant KPI_DELETE_SUCCESS. */
    public static final String KPI_DELETE_SUCCESS = "KPI Deleted Successfully";

    /** The Constant KPI_DELETE_ERROR. */
    public static final String KPI_DELETE_ERROR = "KPI Delete Failed";

    /** The Constant PIPELINE_DELETE_SUCCESS. */
    public static final String PIPELINE_DELETE_SUCCESS = "Pipeline Deleted Successfully";

    /** The Constant PIPELINE_DELETE_ERROR. */
    public static final String PIPELINE_DELETE_ERROR = "Pipeline Delete Failed";

    /** The Constant VISUALIZE_FETCH_ERROR. */
    public static final String VISUALIZE_FETCH_ERROR = "Visualization Fetching Failed";

    /** The Constant PIPELINE_EXECUTION_ERROR. */
    public static final String PIPELINE_EXECUTION_ERROR = "Pipeline Execution Failed";

    /** The Constant PIPELINE_EXECUTION_STOP_ERROR. */
    public static final String PIPELINE_EXECUTION_STOP_ERROR = "Pipeline Stop Failed";

    /** The Constant PIPELINE_EXECUTION_STATUS_ERROR. */
    public static final String PIPELINE_EXECUTION_STATUS_ERROR = "Pipeline Status Fetch Failed";

    /** The Constant VISUALIZE_COUNT_FETCH_ERROR. */
    public static final String VISUALIZE_COUNT_FETCH_ERROR = "Visualization Count Fetch Failed";

    /** The Constant SINK_DATATYPES_FETCH_ERROR. */
    public static final String SINK_DATATYPES_FETCH_ERROR = "Sink Datatypes not Found";

    /** The Constant METADATA_FIND_ERROR. */
    public static final String METADATA_FIND_ERROR = "Metadata Finding Failed";

    /** The Constant LOOKUP_DETAILS_SAVE_SUCCESS. */
    public static final String LOOKUP_DETAILS_SAVE_SUCCESS = "Lookup details Saved Successfully";

    /** The Constant LOOKUP_DETAILS_SAVE_ERROR. */
    public static final String LOOKUP_DETAILS_SAVE_ERROR = "Lookup details Save Failed";

    /** The Constant LOOKUP_DELETE_SUCCESS. */
    public static final String LOOKUP_DELETE_SUCCESS = "Lookup Deleted Successfully";

    /** The Constant LOOKUP_DELETE_ERROR. */
    public static final String LOOKUP_DELETE_ERROR = "Lookup Delete Failed";

    /** The Constant LOOKUP_DETAILS_DELETE_SUCCESS. */
    public static final String LOOKUP_DETAILS_DELETE_SUCCESS = "LookupDetails Deleted Successfully";

    /** The Constant LOOKUP_DETAILS_DELETE_ERROR. */
    public static final String LOOKUP_DETAILS_DELETE_ERROR = "LookupDetails Delete Failed";

    /** The Constant LOOKUP_FETCH_ERROR. */
    public static final String LOOKUP_FETCH_ERROR = "Lookup details Fetching Failed";

    /** The Constant REDIS_CONNECTION_ERROR. */
    public static final String REDIS_CONNECTION_ERROR = "Error While Tesing Redis Connection";

    /** The Constant REDIS_CONNECTION_ERROR. */
    public static final String QUERY_PLAN_STATUS_ERROR = "Query Plan Status " +
            "fetch failed";

    /** The Constant REDIS_CONNECTION_ERROR. */
    public static final String SINK_TABLE_STATUS_ERROR = "Sink Table Not Valid";

    /** The Constant USER_FUNCTION_TEMP_PATH. */
    public static final String USER_FUNCTION_TEMP_PATH = "userfunctionuploadtemppath";

    /** The Constant USER_FUNCTION_PATH. */
    public static final String USER_FUNCTION_PATH = "userfunctionuploadpath";

    /** The Constant USER_FUNCTION_DELETE_SUCCESS. */
    public static final String USER_FUNCTION_DELETE_SUCCESS = "User Function " +
            "Deleted Successfully";

    /** The Constant USER_FUNCTION_DELETE_ERROR. */
    public static final String USER_FUNCTION_DELETE_ERROR = "User Function Delete Failed";

    /** The Constant UDF_SAVE_SUCCESS. */
    public static final String USER_FUNCTION_SAVE_SUCCESS = "User Function Saved Successfully";

    /** The Constant UDF_SAVE_ERROR. */
    public static final String USER_FUNCTION_SAVE_ERROR = "User Function Save Failed";

    /** The Constant UDF_SAVE_ERROR_UDF_EXIST. */
    public static final String UDF_SAVE_ERROR_UDF_EXIST = "User Function Save Failed, "
            + " Udf already exist";

    /** The Constant UDF_FETCH_ERROR. */
    public static final String USER_FUNCTION_FETCH_ERROR = "User Function Fetching Failed";

    /** The Constant UDF_LIST_ERROR. */
    public static final String USER_FUNCTION_LIST_ERROR = "User Function Listing Failed";

    /** The Constant RUNNING. */
    public static final String RUNNING = "RUNNING";

    /** The Constant STOPPED. */
    public static final String STOPPED = "STOPPED";

    /** The Constant TABLE_STATUS_YES. */
    public static final String TABLE_STATUS_YES = "Valid Table Name";

    /** The Constant TABLE_STATUS_NO. */
    public static final String TABLE_STATUS_NO = "Table Already Exists";

    /** The Constant HOST_CONNECTION_ERROR. */
    public static final String HOST_CONNECTION_ERROR = "Error While Tesing Host Connection";

    /** The Constant PORTAL_SAVE_SUCCESS. */
    public static final String PORTAL_SAVE_SUCCESS = "Portal Saved Successfully";

    /** The Constant PORTAL_SAVE_ERROR. */
    public static final String PORTAL_SAVE_ERROR = "Portal Save Failed";

    /** The Constant DASHBOARD_SAVE_SUCCESS. */
    public static final String DASHBOARD_SAVE_SUCCESS = "Dashboard Saved Successfully";

    /** The Constant DASHBOARD_SAVE_ERROR. */
    public static final String DASHBOARD_SAVE_ERROR = "Dashboard Save Failed";

    /** The Constant DASHBOARD_FETCH_ERROR. */
    public static final String DASHBOARD_FETCH_ERROR = "Error While Fetching Dashboard Details";

    /** The Constant DASHBOARD_LIST_ERROR. */
    public static final String DASHBOARD_LIST_ERROR = "Error While Fetching Dashboard List";

    /** The Constant PORTAL_LIST_ERROR. */
    public static final String PORTAL_LIST_ERROR = "Error While Fetching Portal List";

    /** The Constant CATEGORY_TREE_COMPOSE_ERROR. */
    public static final String CATEGORY_TREE_COMPOSE_ERROR = "Error While Composing Category-KPI-Visualize Tree";

    /** The Constant PORTAL_DELETE_SUCCESS. */
    public static final String PORTAL_DELETE_SUCCESS = "Portal Deleted Successfully";

    /** The Constant PORTAL_DELETE_ERROR. */
    public static final String PORTAL_DELETE_ERROR = "Portal Delete Failed";

    /** The Constant DASHBOARD_DELETE_SUCCESS. */
    public static final String DASHBOARD_DELETE_SUCCESS = "Dashboard Deleted Successfully";

    /** The Constant DASHBOARD_DELETE_ERROR. */
    public static final String DASHBOARD_DELETE_ERROR = "Dashboard Delete Failed";

    /** The Constant PIPELINE_TERMINATE_STATUS. */
    public static final String PIPELINE_TERMINATE_STATUS = "Stopped / Killed / Finished";

    /** The Constant PIPELINE_CONSTRUCTION_STATUS. */
    public static final String PIPELINE_CONSTRUCTION_STATUS = "Pipeline construction completed from Builder";

    /** The Constant SPARK_CHECKPOINT_DIR. */
    public static final String SPARK_CHECKPOINT_DIR = "sparkcheckpointdir";

    /** The Constant SPARK_CHECKPOINT_DIR. */
    public static final String CHECK_LOG = "Please check the log " +
            "file for details";

    /* PORTAL CONSTANTS */
    /** The Constant CSS_LOCATION. */
    public static final String CSS_LOCATION = "csslocation";

    /** The Constant LOGO_LOCATION. */
    public static final String LOGO_LOCATION = "logolocation";

    /** The Constant LOOKUP_TEMP_CSV_LOCATION. */
    public static final String LOOKUP_TEMP_CSV_LOCATION = "lookup_temp_csv_location";

    /** The Constant REDIS_RUNNING_STATUS. */
    public static final String REDIS_RUNNING_STATUS = "redis_running_status";

    /** The Constant REMOTE_HOST. */
    // Query Plan CONSTANTS
    public static final String REMOTE_HOST = "remoteHost";

    /** The Constant REMOTE_USER_NAME. */
    public static final String REMOTE_USER_NAME = "remoteUserName";

    /** The Constant REMOTE_USER_PASSWORD. */
    public static final String REMOTE_USER_PASSWORD = "remoteUserPswd";

    /** The Constant SPARK_SQL_CMD. */
    public static final String SPARK_SQL_CMD = "sparkSqlCmd";

    /* FLUME AGENT NODE DETAILS */
    /** The Constant FLUME_AGENT_HOST. */
    public static final String FLUME_AGENT_HOST = "flumeagenthost";

    /** The Constant FLUME_AGENT_HOSTUSER. */
    public static final String FLUME_AGENT_HOSTUSER = "flumeagenthostuser";

    /** The Constant FLUME_AGENT_HOSTPWD. */
    public static final String FLUME_AGENT_HOSTPWD = "flumeagenthostpwd";

    /** The Constant FLUME_AGENT_CONF_TEMPATH. */
    public static final String FLUME_AGENT_CONF_TEMPATH = "flumeagentconfpath";

    /** The Constant FLUME_AGENT_LOCALCONF_TEMPATH. */
    public static final String FLUME_AGENT_LOCALCONF_TEMPATH = "flumeagentconflocaltmppath";

    /** The Constant FLUMESPARK_DEPENDJAR_1. */
    public static final String FLUMESPARK_DEPENDJAR_1 = "flumesparkdependjar1";

    /* Cassandra Table */

    /** The Constant CASSANDRA_TIME_TO_LIVE. */
    public static final String CASSANDRA_TIME_TO_LIVE = "default_time_to_live";


    public static final String DATA_FETCHING_ERROR = "Error While Fetching " +
            "Data";

    /** The Constant SUCCESS_CODE. */
    public static final int SUCCESS_CODE = 200;

    /** The Constant NUMBER_THIRTY_THOUSAND. */
    public static final int NUMBER_THIRTY_THOUSAND = 30000;

    /** The Constant SFTP_PORT_NUMBER. */
    public static final int SFTP_PORT_NUMBER = 22;

    /** The Constant NUMBER_THOUSAND. */
    public static final int NUMBER_THOUSAND = 1000;

    /** The Constant NUMBER_THREE. */
    public static final int NUMBER_THREE = 3;

    /** The Contant HDFS_FILE_BYTE_SIZE.*/
    public static final int HDFS_FILE_BYTE_SIZE = 1024;

    /** The Constant NUMBER_FOUR. */
    public static final int NUMBER_FOUR = 4;

    /** The Constant NUMBER_FIFTY. */
    public static final int NUMBER_FIFTY = 50;

    /** The Constant CASSANDRA CLUSTER. */
    public static final String  CASSANDRA_CLUSTER_NODES = "cassandra_cluster_nodes";

    /**  The Contant for Elastic Serach URL. */
    public static final String ELASTICSEARCH_URL = "elasticsearch_rul";


    /**  The Contant for Grafana URL. */
    public static final String GRAFANA_URL = "grafana_url";

    /*Cassandra Pooling Configuration Keys */

    /** The Constant CASSANDRA_CORE_CONNECTION_PER_HOST_LOCAL. */
    public static final String CASSANDRA_CORE_CONNECTION_PER_HOST_LOCAL
            = "cassandra_core_connection_per_host_local";

    /** The Constant CASSANDRA_MAX_CONNECTION_PER_HOST_LOCAL. */
    public static final String CASSANDRA_MAX_CONNECTION_PER_HOST_LOCAL
            = "cassandra_max_connection_per_host_local";

    /** The Constant CASSANDRA_CORE_CONNECTION_PER_HOST_REMOTE. */
    public static final String CASSANDRA_CORE_CONNECTION_PER_HOST_REMOTE
            = "cassandra_core_connection_per_host_remote";

    /** The Constant CASSANDRA_MAX_CONNECTION_PER_HOST_REMOTE. */
    public static final String CASSANDRA_MAX_CONNECTION_PER_HOST_REMOTE
            = "cassandra_max_connection_per_host_remote";

    /** The Constant CASSANDRA_MAX_REQUESTS_PER_CONNECTION_LOCAL. */
    public static final String CASSANDRA_MAX_REQUESTS_PER_CONNECTION_LOCAL
            = "cassandra_max_requests_per_connection_local";

    /** The Constant CASSANDRA_MAX_REQUESTS_PER_CONNECTION_REMOTE. */
    public static final String CASSANDRA_MAX_REQUESTS_PER_CONNECTION_REMOTE
            = "cassandra_max_requests_per_connection_remote";


}










