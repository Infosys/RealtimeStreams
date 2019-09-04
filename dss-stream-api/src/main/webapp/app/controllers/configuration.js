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
/** 
 * Description : Controller for Configuration screen.
 */
/*global
app, console, serviceURL, msg, enjoyhint_instance, angular, addlParams
*/
app.controller("ConfigCtrl", ['$scope', '$rootScope', '$route', '$http', '$templateCache', 'uiGridConstants', '$filter', '$uibModal', 'ngDialog', 'configService', function ($scope, $rootScope, $route, $http, $templateCache, $uiGridConstants, $filter, $uibModal, ngDialog, configService) {
    //TODO : Logic to show guided messages in the respective pages using enjoyhint.
    "use strict";
    var token, retrievedObject;
    //$scope.udfFields = true;
    $scope.$on('$viewContentLoaded', function () {
        $scope.loadedHTML = $route.current.templateUrl;
        //alert($scope.loadedHTML);
        if ($scope.loadedHTML.indexOf("configuration.html") !== -1) {
            enjoyhint_instance.stop();
        }
    });
    $scope.configDetails = {
        "lstsource" : [ {
            "name" : "kafka",
            "config" : ""
        }, {
            "name" : "flume",
            "config" : ""
        }],
        "lstprocess" : [ {
            "name" : "spark",
            "display" : "Spark Streaming",
            "config" : ""
        }],
        "lstsink" : [ {
            "name" : "Elassandra",
            "config" : ""
        } ]
    };

    $scope.sourceTypes = $scope.configDetails.lstsource;
    console.log($scope.sourceTypes);

    $scope.processTypes = $scope.configDetails.lstprocess;
    console.log($scope.processTypes);

    $scope.sinkTypes = $scope.configDetails.lstsink;
    console.log($scope.sinkTypes);

    $scope.cacheTypes = $scope.configDetails.lstcache;
    console.log($scope.categoryTypes);
    $scope.topologies = [{"name": "NetworkTopologyStrategy"}];
    $scope.Deployementmodes = [{"name": "yarn-cluster"}, {"name": "standalone"}, {"name": "local"}];
    $scope.flumeConnectionTypes = [{"name": "remote"}, {"name": "local"}];
    token = localStorage.getItem('loginToken');
    console.log(token);
    retrievedObject = localStorage.getItem('loginObject');//loginToken
    retrievedObject = JSON.parse(retrievedObject);
    console.log(retrievedObject);
    console.log(localStorage.getItem('loginToken'));
    if (!retrievedObject) {
        $scope.openConfirmLogin();
    }
   // $scope.fileTypeError = false;
    /**
     * Service call to get all existing Source names
     */
    $scope.getSourceDetails = function () {
        token = localStorage.getItem('loginToken');
        return configService.getExistingSourceNames(token).then(
            function (data) {
                if (data.success === true) {
                    $rootScope.existingSources = data.root;
                    console.log($rootScope.existingSources);
                } else {
                    var error = data.errors;
                    $rootScope.existingSources = "";
                    console.log(error.errorMessage);
                    if (error.errorCode === "3006") {
                        $scope.openConfirmLogin();
                    }
                }
            }
        );
    };
    /**
     *  Method to show confirmation dialog for login
     */
    $scope.openConfirmLogin = function () {
        ngDialog.openConfirm({
            template : 'modalDialogLogin',
            className : 'ngdialog-theme-default',
            showClose: false
        }).then(
            function (value) {
                //callback();
                window.location = 'login.html';
            },
            function (reason) {
                console.log('Modal promise rejected. Reason: ', reason);
            }
        );
    };
    /**
     *  Service call to get all existing Process names
     */
    $scope.getProcessDetails = function () {
        token = localStorage.getItem('loginToken');
        return configService.getExistingProcessNames(token).then(
            function (data) {
                if (data.success === true) {
                    $rootScope.existingProcess = data.root;
                    console.log($rootScope.existingProcess);
                } else {
                    var error = data.errors;
                    $rootScope.existingProcess = "";
                    //console.log(error.errorMessage);
                    if (error.errorCode === "3006") {
                        $scope.openConfirmLogin();
                    }
                }
            }
        );
    };
    /**
     *  Service call to get all existing Sink names
     */
    $scope.getSinkDetails = function () {
        token = localStorage.getItem('loginToken');
        return configService.getExistingSinkNames(token).then(
            function (data) {
                if (data.success === true) {
                    $rootScope.existingSink = data.root;
                    console.log($rootScope.existingSink);
                } else {
                    var error = data.errors;
                    $rootScope.existingSink = "";
                    //console.log(error.errorMessage);
                    if (error.errorCode === "3006") {
                        $scope.openConfirmLogin();
                    }
                }
            }
        );
    };
    /**
     * Service call to get all existing UDF functions
     */
    $scope.getUdfDetails = function () {
        token = localStorage.getItem('loginToken');
        return configService.getExistingUdf(token).then(
            function (data) {
                if (data.success === true) {
                    $rootScope.existingUdf = data.root;
                    console.log($rootScope.existingUdf);
                } else {
                	//$scope.success = false;
                    var error = data.errors;
                    $rootScope.existingUdf = "";
                    console.log(error.errorMessage);
                    if (error.errorCode === "3006") {
                        $scope.openConfirmLogin();
                    }
                }
            }
        );
    };
    
    /**
    * Service call to get all existing Category names
    */
    $scope.getCategoryDetails = function () {
        token = localStorage.getItem('loginToken');
        return configService.getExistingCategoryNames(token).then(
            function (data) {
                if (data.success === true) {
                    $rootScope.existingCategory = data.root;
                    console.log($rootScope.existingCategory);
                } else {
                    var error = data.errors;
                    $rootScope.existingCategory = "";
                    //console.log(error.errorMessage);
                    if (error.errorCode === "3006") {
                        $scope.openConfirmLogin();
                    }
                }
            }
        );
    };
    /**
    * Service call to get all existing KPI names
    */
    $scope.getKPIDetails = function () {
        token = localStorage.getItem('loginToken');
        return configService.getExistingKPINames(token).then(
            function (data) {
                if (data.success === true) {
                    $rootScope.existingKPI = data.root;
                    console.log($rootScope.existingKPI);
                } else {
                    var error = data.errors;
                    $rootScope.existingKPI = "";
                    //console.log(error.errorMessage);
                    if (error.errorCode === "3006") {
                        $scope.openConfirmLogin();
                    }
                }
            }
        );
    };
    /**
     * Function called on click of blocks in configuration
     * @param configType
     */
    $scope.tileOnClick = function (configType) {
        $scope.activeMenu = configType;
        $scope.activeBtn = configType;
        if (configType === 'Source') {
            $scope.getSourceDetails();
        } else if (configType === 'Process') {
            $scope.getProcessDetails();
        } else if (configType === 'Sink') {
            $scope.getSinkDetails();
        } else if (configType === 'Category') {
            $scope.getCategoryDetails();
        } else if (configType === 'KPI') {
            $scope.getCategoryDetails();
            $scope.getKPIDetails();
        } else if (configType === 'Lookup') {
            console.log("Lookup");
        } else if (configType === 'Udf') {
        	$scope.getUdfDetails();
            console.log("Udf");
        }
    };
    /**
     * Function to add additional parameter record
     * @param configType
     */
    $scope.addNewParam = function (configType) {
        if (configType === 'source') {
            $scope.addnParamsSource.push({
                "key": "",
                "value": ""
            });
        }
        if (configType === 'process') {
            $scope.addnParamsProcess.push({
                "key": "",
                "value": ""
            });
        }
        if (configType === 'sink') {
            $scope.addnParamsSink.push({
                "key": "",
                "value": ""
            });
        }
        if (configType === 'flume') {
            $scope.addnParamsFlumeSource.push({
                "paramname": "",
                "paramvalue": ""
            });
        }
    };
    /**
     * Function to remove additional parameter record
     * @param configType
     * @param delIndex
     */
    $scope.removeParam = function (configType, delIndex) {
        if (configType === 'source') {
            $scope.addnParamsSource.splice(delIndex, 1);
        }
        if (configType === 'process') {
            $scope.addnParamsProcess.splice(delIndex, 1);
        }
        if (configType === 'sink') {
            $scope.addnParamsSink.splice(delIndex, 1);
        }
        if (configType === 'flume') {
            $scope.addnParamsFlumeSource.splice(delIndex, 1);
        }
    };
    /**
     * Function called on click of Add Config Button based on types.
     * @param idName
     */
    $scope.addNewConfig = function (idName) {
        token = localStorage.getItem('loginToken');
        if (token) {
            if (idName === 'source') {
                $scope.saved = false;
                $scope.sourceTypes.selected = "";
                $scope.keySr = "org.apache.kafka.common.serialization.StringSerializer";
                $scope.valSr = "org.apache.kafka.common.serialization.StringSerializer";
                //$scope.sourceTypes.selected = {}
                $scope.addnParamsSource = [{
                    "key": "",
                    "value": ""
                }];
                $scope.openSourcePopUp = $uibModal.open({
                    templateUrl : 'app/views/source-details.html',
                    backdrop  : 'static',
                    keyboard  : false,
                    scope : $scope/*,
                    resolve: {
                        items: function () {
                            return $scope.saveMessage, $scope.saved;
                        }
                    }*/
                });
            }
            if (idName === 'process') {
                $scope.saved = false;
                if ($scope.nextBtn === true) {
                    $scope.saved = true;
                    $scope.nextBtn = false;

                }
                $scope.processTypes.selected = "";
                $scope.addnParamsProcess = [{
                    "key": "",
                    "value": ""
                }];
                $scope.openProcessPopUp = $uibModal.open({
                    templateUrl : 'app/views/process-details.html',
                    backdrop  : 'static',
                    keyboard  : false,
                    scope : $scope/*,
                    resolve: {
                        items: function () {
                            return $scope.saveMessage,$scope.saved
                        }
                    }*/
                });
            }
            if (idName === 'sink') {
                $scope.saved = false;
                if ($scope.nextBtn === true) {
                    $scope.saved = true;
                    $scope.nextBtn = false;
                }
                $scope.sinkTypes.selected = "";
                $scope.topologies.selected = "";
                $scope.addnParamsSink = [{
                    "key": "",
                    "value": ""
                }];
                $scope.openSinkPopUp = $uibModal.open({
                    templateUrl : 'app/views/sink-details.html',
                    backdrop  : 'static',
                    keyboard  : false,
                    scope : $scope/*,
                    resolve: {
                        items: function () {
                            return $scope.saveMessage,$scope.saved
                        }
                    }*/
                });
            }
            if (idName === 'udf') {
                $scope.saved = false;
                $scope.functionName = "";
                $scope.registerMethod = "";
                $scope.configurationName = "";
                $scope.description = "";
                $scope.functionName = "";
                $scope.codeBase = "";
                $scope.openUdfPopUp = $uibModal.open({
                    templateUrl : 'app/views/udf-details.html',
                    backdrop  : 'static',
                    keyboard  : false,
                    scope : $scope
                });
            }
            if (idName === 'category') {
                $scope.saved = false;
                $scope.today(); /* Sets Date as Today Initially */
                $scope.openCategoryPopUp = $uibModal.open({
                    templateUrl : 'app/views/category-details.html',
                    backdrop  : 'static',
                    keyboard  : false,
                    scope : $scope/*,
                    resolve: {
                        items: function () {
                            return $scope.saveMessage,$scope.saved
                        }
                    }*/
                });
            }
            if (idName === 'kpi') {
                $scope.saved = false;
		$scope.categoryError = false;
                if ($scope.nextBtn === true) {
                    $scope.saved = true;
                    $scope.nextBtn = false;
                }
                $scope.existingCategory.selected = [];
                $scope.openKpiPopUp = $uibModal.open({
                    templateUrl : 'app/views/kpi-details.html',
                    backdrop  : 'static',
                    keyboard  : false,
                    scope : $scope/*,
                    resolve: {
                        items: function () {
                            return $scope.existingCategory.selected,$scope.saveMessage,$scope.saved
                        }
                    }*/
                });
            }
        } else {
            $scope.openConfirmLogin();
        }

    };
    /**
     * Function called on click of close button in modal window.
     * @param popUpInstance
     * @param type
     */
    $scope.cancel = function (popUpInstance, type) {
        console.log("Close >> " + popUpInstance);
        popUpInstance.close();
        $scope.configAction = "";
        if (type === "source") {
            $scope.getSourceDetails();
            $scope.sourceTypes.selected = "";
        } else if (type === "process") {
            $scope.getProcessDetails();
            $scope.processTypes.selected = "";
            $scope.Deployementmodes.selected = "";
        } else if (type === "sink") {
            $scope.getSinkDetails();
            $scope.sinkTypes.selected = "";
            $scope.topologies.selected = "";
        } else if (type === "category") {
            $scope.getCategoryDetails();
        } else if (type === "kpi") {
            $scope.getKPIDetails();
        }
    };
    /**
     * Function called on click of edit button in configuration screen.
     * @param action
     * @param idName
     * @param record
     */
    $scope.editSelectedConfig = function (action, idName, record) {
        $scope.configAction = action;
        $scope.saved = false;
        var error, sourceObj, processObj, sinkObj, sinkType, udfObj, udfType, srcType, proType, modeType, topologyType, catObj, kpiObj;
        if (idName === "source") {
            //$scope.sourceRecord = record;
            sourceObj = {};
            sourceObj.sourceId = record.id;
            token = localStorage.getItem('loginToken');
            //$scope.editSourceName = record.name;
            configService.getExistingSourcebyId(sourceObj, token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.sourceRecord = data.root;
                        $scope.sourceRecord = JSON.parse(JSON.stringify($scope.sourceRecord));
                        $scope.sourceRecord.formatDetails = JSON.parse($scope.sourceRecord.objSourceConfigDetails);
                        srcType = $scope.sourceRecord.strSourceType;
                        angular.forEach($scope.sourceTypes, function (val, k) {
                            if (val.name === srcType) {
                                $scope.sourceTypes.selected = val;
                            }
                        });
                        if ($scope.sourceRecord.formatDetails.strOffset === "Largest") {
                            $scope.sourceRecord.formatDetails.offset = "Largest";
                        } else if ($scope.sourceRecord.formatDetails.strOffset === "Smallest") {
                            $scope.sourceRecord.formatDetails.offset = "Smallest";
                        }
                        /*if($scope.sourceTypes.selected.name == "flume"){
                            var connectionType = $scope.sourceRecord.formatDetails.objFlumeAgentSrcDtls.strSrcConnectType;
                            angular.forEach($scope.flumeConnectionTypes, function(
                                    val, k) {
                                if (val.name == connectionType) {
                                    $scope.flumeConnectionTypes.selected = val;
                                }
                            });
                            $scope.addnParamsFlumeSource = [];
                            if($scope.sourceRecord.formatDetails.objFlumeAgentSrcDtls.objAddlParams){
                                if($scope.sourceRecord.formatDetails.objFlumeAgentSrcDtls.objAddlParams.length>0){
                                    angular.forEach($scope.sourceRecord.formatDetails.objFlumeAgentSrcDtls.objAddlParams, function(val, k) {
                                        for (var key in val) {
                                            if (val[key]) {
                                                console.log(key + " -> " + val[key]);
                                                if(key != ""){
                                                    var tempParamsObj = {
                                                            "paramname" : key,
                                                            "paramvalue" : val[key]
                                                    };
                                                    $scope.addnParamsFlumeSource.push(tempParamsObj);
                                                }
                                            }
                                        }
                                    });
                                }else{
                                    $scope.addnParamsFlumeSource = [{
                                        "paramname":"",
                                        "paramvalue":""
                                    }];
                                }
                            }else{
                                $scope.addnParamsFlumeSource = [{
                                    "paramname":"",
                                    "paramvalue":""
                                }];
                            }

                        }*/
                        //Additional params
                        $scope.addnParamsSource = [];
                        if ($scope.sourceRecord.formatDetails.addlParams) {
                            if ($scope.sourceRecord.formatDetails.addlParams.length > 0) {
                                $scope.addnParamsSource = $scope.sourceRecord.formatDetails.addlParams;
                            } else {
                                $scope.addnParamsSource = [{
                                    "key": "",
                                    "value": ""
                                }];
                            }
                        } else {
                            $scope.addnParamsSource = [{
                                "key": "",
                                "value": ""
                            }];
                        }
                        $scope.editSourcePopUp = $uibModal.open({
                            templateUrl : 'app/views/edit-source-details.html',
                            backdrop  : 'static',
                            keyboard  : false,
                            scope : $scope
                        });
                    } else {
                        error = data.errors;
                        $scope.sourceRecord = "";
                        //console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                    }
                }
            );
        }
        if (idName === "process") {
            processObj = {};
            processObj.processId = record.id;
            token = localStorage.getItem('loginToken');
            configService.getExistingProcessbyId(processObj, token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.processRecord = data.root;
                        $scope.processRecord = JSON.parse(JSON.stringify($scope.processRecord));
                        $scope.processRecord.formatDetails = JSON.parse($scope.processRecord.objConfigDetails);
                        proType = $scope.processRecord.strProcessType;
                        angular.forEach($scope.processTypes, function (val, k) {
                            if (val.name === proType) {
                                $scope.processTypes.selected = val;
                            }
                        });
                        modeType = $scope.processRecord.formatDetails.strSparkMaster;
                        angular.forEach($scope.Deployementmodes, function (val, k) {
                            if (val.name === modeType) {
                                $scope.Deployementmodes.selected = val;
                            }
                        });
                        //Additional params
                        $scope.addnParamsProcess = [];
                        if ($scope.processRecord.formatDetails.addlParams) {
                            if ($scope.processRecord.formatDetails.addlParams.length > 0) {
                                $scope.addnParamsProcess = $scope.processRecord.formatDetails.addlParams;
                            } else {
                                $scope.addnParamsProcess = [{
                                    "key": "",
                                    "value": ""
                                }];
                            }
                        } else {
                            $scope.addnParamsProcess = [{
                                "key": "",
                                "value": ""
                            }];
                        }
                        $scope.editProcessPopUp = $uibModal.open({
                            templateUrl : 'app/views/edit-process-details.html',
                            backdrop  : 'static',
                            keyboard  : false,
                            scope : $scope
                        });
                    } else {
                        error = data.errors;
                        $scope.processRecord = "";
                        console.log(error.errorMessage);
                    }
                }
            );
        }
        if (idName === "sink") {
            sinkObj = {};
            sinkObj.sinkId = record.id;
            token = localStorage.getItem('loginToken');
            configService.getExistingSinkbyId(sinkObj, token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.sinkRecord = data.root;
                        $scope.sinkRecord = JSON.parse(JSON.stringify($scope.sinkRecord));
                        $scope.sinkRecord.formatDetails = JSON.parse($scope.sinkRecord.objSinkConfigDetails);
                        sinkType = $scope.sinkRecord.strSinkType;
                        angular.forEach($scope.sinkTypes, function (val, k) {
                            if (val.name === sinkType) {
                                $scope.sinkTypes.selected = val;
                            }
                        });
                        topologyType = $scope.sinkRecord.formatDetails.strTopology;
                        angular.forEach($scope.topologies, function (val, k) {
                            if (val.name === topologyType) {
                                $scope.topologies.selected = val;
                            }
                        });
                        //Additional params
                        $scope.addnParamsSink = [];
                        if ($scope.sinkRecord.formatDetails.addlParams) {
                            if ($scope.sinkRecord.formatDetails.addlParams.length > 0) {
                                $scope.addnParamsSink = $scope.sinkRecord.formatDetails.addlParams;
                            } else {
                                $scope.addnParamsSink = [{
                                    "key": "",
                                    "value": ""
                                }];
                            }
                        } else {
                            $scope.addnParamsSink = [{
                                "key": "",
                                "value": ""
                            }];
                        }
                        $scope.editSinkPopUp = $uibModal.open({
                            templateUrl : 'app/views/edit-sink-details.html',
                            backdrop  : 'static',
                            keyboard  : false,
                            scope : $scope
                        });
                    } else {
                        error = data.errors;
                        $scope.sinkRecord = "";
                        console.log(error.errorMessage);
                    }
                }
            );
        }
        if (idName === "udf") {
        	/*if($scope.configAction === "edit"){
            	$scope.success = true;
            	$scope.saveMessage = "Please upload needed jar file.";
            	$scope.saved = true;
            }*/
            udfObj = {};
            udfObj.udfId = record.id;
           // $scope.udfFields = true;
           // $scope.jarFileInput = false;
            token = localStorage.getItem('loginToken');
            configService.getExistingUdfbyId(udfObj, token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.udfRecord = data.root;
                        /*$scope.udfRecord = JSON.parse(JSON.stringify($scope.udfRecord));
                        $scope.udfRecord.formatDetails = JSON.parse($scope.udfRecord.objUdfConfigDetails);*/
                        /*udfType = $scope.udfRecord.strUdfType;
                        angular.forEach($scope.udfTypes, function (val, k) {
                            if (val.name === udfType) {
                                $scope.udfTypes.selected = val;
                            }
                        });*/
                        //functionName = $scope.udfRecord.functionName;
                        /*className = $scope.udfRecord.strMainClassName;
                        returnType = $scope.udfRecord.strReturnType;
                        description = $scope.udfRecord.strUdfDesc;*/
                        /*functionName = $scope.udfRecord.strUdfName;
                        $scope.functionName = udfObj.strUdfName;
                        $scope.className = udfObj.strMainClassName;
                        $scope.returnType = udfObj.strReturnType;
                        $scope.description = udfObj.strUdfDesc;*/
                        

                        $scope.editUdfPopUp = $uibModal.open({
                            templateUrl : 'app/views/edit-udf-details.html',
                            backdrop  : 'static',
                            keyboard  : false,
                            scope : $scope
                        });
                    } else {
                    	$scope.success = false;
                        error = data.errors;
                        $scope.udfRecord = "";
                        console.log(error.errorMessage);
                    }
                }
            );
        }
        if (idName === "category") {
            catObj = {};
            catObj.categoryId = record.id;
            token = localStorage.getItem('loginToken');
            configService.getExistingCategorybyId(catObj, token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.categoryRecord = data.root;
                        $scope.editCategoryPopUp = $uibModal.open({
                            templateUrl : 'app/views/edit-category-details.html',
                            backdrop  : 'static',
                            keyboard  : false,
                            scope : $scope
                        });
                    } else {
                        error = data.errors;
                        $scope.categoryRecord = "";
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                    }
                }
            );
        }
        if (idName === "kpi") {
	$scope.categoryError = false;
            kpiObj = {};
            kpiObj.kpiId = record.id;
            token = localStorage.getItem('loginToken');
            configService.getExistingKPIbyId(kpiObj, token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.kpiRecord = data.root;
                        $scope.existingCategory.selected = [];
                        angular.forEach(data.root.categories, function (val, k) {
                            $scope.existingCategory.selected.push({
                                "id" : val.inCategoryId,
                                "name" : val.strCategoryName
                            });
                        });
                        $scope.editKpiPopUp = $uibModal.open({
                            templateUrl : 'app/views/edit-kpi-details.html',
                            backdrop  : 'static',
                            keyboard  : false,
                            scope : $scope
                        });
                    } else {
                        error = data.errors;
                        $scope.kpiRecord = "";
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                    }
                }
            );
        }
    };
    /**
     * Confirmation dialog
     * @param data1
     * @param data2
     * @param callback
     */
    $scope.openConfirm = function (data1, data2, callback) {
        ngDialog.openConfirm({
            template : 'modalDialogId',
            className : 'ngdialog-theme-default'
        }).then(
            function (value) {
                callback(data1, data2);
            },
            function (reason) {
                console.log('Modal promise rejected. Reason: ', reason);
            }
        );
    };
    /**
     * Method to delete selected configuration.
     * @param name
     * @param record
     */
    $scope.deleteSelectedConfig = function (name, record) {
        var idName, recordId, sourceObj, dialog, error, processObj, sinkObj, udfObj, catObj, kpiObj;
        idName = name;
        recordId = record.id;
        if (idName === "source") {

            //$scope.sourceRecord = record;
            sourceObj = {};
            sourceObj.sourceId = record.id;
            //Calling service to delete a configuration
            token = localStorage.getItem('loginToken');
            configService.deleteSource(recordId, token).then(
                function (data) {
                    if (data.success === true) {
                        //Need to check the API to sent successDetails
                        //console.log(JSON.stringify(data.successDetails.successMessage));
                        dialog = ngDialog.open({
                            template: data.successDetails.successMessage,
                            //template:"Deleted successfully",
                            plain: true,
                            className: 'ngdialog-theme-success'
                        });
                        //Calling service to get all pipelines to refresh the pipeline table
                        $scope.getSourceDetails();

                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        //$scope.existingPipelines = "";
                        //console.log("Error in deletion");
                        dialog = ngDialog.open({
                            template: error.errorCode + " : " + error.errorMessage,
                            plain: true,
                            className: 'ngdialog-theme-error'
                        });
                    }
                }
            );
        }
        if (idName === "process") {
            processObj = {};
            processObj.processId = record.id;
            //Calling service to delete a configuration
            token = localStorage.getItem('loginToken');
            configService.deleteProcess(recordId, token).then(
                function (data) {
                    if (data.success === true) {
                        dialog = ngDialog.open({
                            template: data.successDetails.successMessage,
                            //template:"Deleted successfully",
                            plain: true,
                            className: 'ngdialog-theme-success'
                        });
                        //Calling service to get all pipelines to refresh the pipeline table
                        $scope.getProcessDetails();

                    } else {
                        error = data.errors;
                        //$scope.existingPipelines = "";
                        //console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log("Error in deletion");
                        dialog = ngDialog.open({
                            template: error.errorCode + " : " + error.errorMessage,
                            plain: true,
                            className: 'ngdialog-theme-error'
                        });
                    }
                }
            );
        }
        if (idName === "sink") {
            sinkObj = {};
            sinkObj.sinkId = record.id;
            //Calling service to delete a configuration
            token = localStorage.getItem('loginToken');
            configService.deleteSink(recordId, token).then(
                function (data) {
                    if (data.success === true) {
                        //console.log(JSON.stringify(data.successDetails.successMessage));
                        dialog = ngDialog.open({
                            template: data.successDetails.successMessage,
                            //template:"Deleted successfully",
                            plain: true,
                            className: 'ngdialog-theme-success'
                        });
                        //Calling service to get all pipelines to refresh the pipeline table
                        $scope.getSinkDetails();

                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        //console.log("Error in deletion");
                        dialog = ngDialog.open({
                            template: error.errorCode + " : " + error.errorMessage,
                            plain: true,
                            className: 'ngdialog-theme-error'
                        });
                    }
                }
            );
        }
        if (idName === "udf") {
            udfObj = {};
            udfObj = {
            	"inUdfId": 1
            }

            udfObj.udfId = record.id;
            //Calling service to delete a configuration
            token = localStorage.getItem('loginToken');
            configService.deleteUdf(recordId, token).then(
                function (data) {
                    if (data.success === true) {
                        //console.log(JSON.stringify(data.successDetails.successMessage));
                        dialog = ngDialog.open({
                            template: data.successDetails.successMessage,
                            //template:"Deleted successfully",
                            plain: true,
                            className: 'ngdialog-theme-success'
                        });
                        //Calling service to get all pipelines to refresh the pipeline table
                        $scope.getUdfDetails();

                    } else {
                    	$scope.success = false;
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        //console.log("Error in deletion");
                        dialog = ngDialog.open({
                            template: error.errorCode + " : " + error.errorMessage,
                            plain: true,
                            className: 'ngdialog-theme-error'
                        });
                    }
                }
            );
        }
        if (idName === "category") {
            catObj = {};
            catObj.categoryId = record.id;
            //Calling service to delete a configuration
            token = localStorage.getItem('loginToken');
            configService.deleteCategory(recordId, token).then(
                function (data) {
                    if (data.success === true) {
                        //console.log(JSON.stringify(data.successDetails.successMessage));
                        dialog = ngDialog.open({
                            template: data.successDetails.successMessage,
                            //template:"Deleted successfully",
                            plain: true,
                            className: 'ngdialog-theme-success'
                        });
                        //Calling service to get all pipelines to refresh the pipeline table
                        $scope.getCategoryDetails();

                    } else {
                        error = data.errors;
                        //$scope.existingPipelines = "";
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        //console.log("Error in deletion");
                        dialog = ngDialog.open({
                            template: error.errorCode + " : " + error.errorMessage,
                            plain: true,
                            className: 'ngdialog-theme-error'
                        });
                    }
                }
            );
        }
        if (idName === "kpi") {
            kpiObj = {};
            kpiObj.kpiId = record.id;
            //Calling service to delete a configuration
            token = localStorage.getItem('loginToken');
            configService.deleteKPI(recordId, token).then(
                function (data) {
                    if (data.success === true) {
                        //console.log(JSON.stringify(data.successDetails.successMessage));
                        dialog = ngDialog.open({
                            template: data.successDetails.successMessage,
                            //template:"Deleted successfully",
                            plain: true,
                            className: 'ngdialog-theme-success'
                        });
                        //Calling service to get all pipelines to refresh the pipeline table
                        $scope.getKPIDetails();

                    } else {
                        error = data.errors;
                        //$scope.existingPipelines = "";
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        //console.log("Error in deletion");
                        dialog = ngDialog.open({
                            template: error.errorCode + " : " + error.errorMessage,
                            plain: true,
                            className: 'ngdialog-theme-error'
                        });
                    }
                }
            );
        }
    };
    /**
     * Method to get additional params from the ui properties file and set in advanced config tab based on config type.
     * @param configName
     * @param configType
     */
    $scope.select = function (configName, configType) {
        var obj, kafkaParams, flumeParams, socketParams, sparkParams, flinkParams, elassandraParams, opentsdbParams;
        if (configName.toLowerCase() === "source") {
            $scope.addnParamsSource = [];
            if (configType.name.toLowerCase() === "kafka") {
                kafkaParams = addlParams.source.kafka;
                angular.forEach(kafkaParams, function (val, k) {
                    obj = {};
                    obj.key = val.key;
                    obj.value = val.value;
                    $scope.addnParamsSource.push(obj);
                });
            } else if (configType.name.toLowerCase() === "flume") {
                flumeParams = addlParams.source.flume;
                angular.forEach(flumeParams, function (val, k) {
                    obj = {};
                    obj.key = val.key;
                    obj.value = val.value;
                    $scope.addnParamsSource.push(obj);
                });
            } else if (configType.name.toLowerCase()  === "socket") {
                socketParams = addlParams.source.socket;
                angular.forEach(socketParams, function (val, k) {
                    obj = {};
                    obj.key = val.key;
                    obj.value = val.value;
                    $scope.addnParamsSource.push(obj);
                });
            }
        } else if (configName.toLowerCase()  === "process") {
            $scope.addnParamsProcess = [];
            if (configType.name.toLowerCase()  === "spark") {
                sparkParams = addlParams.process.sparkstreaming;
                angular.forEach(sparkParams, function (val, k) {
                    obj = {};
                    obj.key = val.key;
                    obj.value = val.value;
                    $scope.addnParamsProcess.push(obj);
                });
            } else if (configType.name.toLowerCase()  === "flink") {
                flinkParams = addlParams.process.flink;
                angular.forEach(flinkParams, function (val, k) {
                    obj = {};
                    obj.key = val.key;
                    obj.value = val.value;
                    $scope.addnParamsProcess.push(obj);
                });
            }
        } else if (configName.toLowerCase()  === "sink") {
            $scope.addnParamsSink = [];
            if (configType.name.toLowerCase()  === "elassandra") {
                elassandraParams = addlParams.sink.elassandra;
                angular.forEach(elassandraParams, function (val, k) {
                    obj = {};
                    obj.key = val.key;
                    obj.value = val.value;
                    $scope.addnParamsSink.push(obj);
                });
            } else if (configType.name.toLowerCase()  === "opentsdb") {
                opentsdbParams = addlParams.sink.opentsdb;
                angular.forEach(opentsdbParams, function (val, k) {
                    obj = {};
                    obj.key = val.key;
                    obj.value = val.value;
                    $scope.addnParamsSink.push(obj);
                });
            }
        }
    };
    $scope.nextBtn = false;
    /**
     * Method to save configuration
     * @param idName
     * @param p1
     * @param p2
     * @param p3
     */
    $scope.saveConfig = function (idName, p1, p2, p3) {
        var error, sourceJSONData, tempParams, srcObj, destObj, processJSONData, sinkJSONData,udfJSONData, categoriesJSONData, KpiJSONData;
        $scope.sourceConfigDetails = {};
        $scope.processConfigDetails = {};
        $scope.sinkConfigDetails = {};
        $scope.udfConfigDetails = {};
        $scope.categoriesConfigDetails = {};
        console.log("$scope.saveConfig function");
        if (idName === "source") {
            if (p2.currentTarget.ownerDocument.activeElement.id === "next") {
                $scope.nextBtn = true;
            }
            sourceJSONData = {};
            tempParams = [];
            if ($scope.addnParamsSource) {
                tempParams = JSON.parse(angular.toJson($scope.addnParamsSource));
            }
            if ($scope.sourceTypes.selected.name === "kafka") {
                $scope.sourceConfigDetails.strGroupName = document.getElementById("groupName").value;
                $scope.sourceConfigDetails.strTopicName = document.getElementById("topicName").value;
                $scope.sourceConfigDetails.strZookeeperlst = document.getElementById("zookeepr").value;
                $scope.sourceConfigDetails.strOffset = p1;
                $scope.sourceConfigDetails.strKeySerializer = document.getElementById("keySr").value;
                $scope.sourceConfigDetails.strValSerializer = document.getElementById("valSr").value;
                $scope.sourceConfigDetails.numThread = document.getElementById("readThreads").value;
                console.log(JSON.stringify($scope.sourceConfigDetails));
            }
            if ($scope.sourceTypes.selected.name === "flume") {
                srcObj = {};
                /*srcObj.strSrcConnectType = $scope.flumeConnectionTypes.selected.name;
                srcObj.strSrcDataFilepath = document.getElementById("sourceFilePath").value;
                if($scope.flumeConnectionTypes.selected.name == "remote"){
                    srcObj.strSrcHost = document.getElementById("flumeSourceHost").value;
                    srcObj.strSrcUser = document.getElementById("hostUser").value;
                    srcObj.strSrcPwd = document.getElementById("hostPassword").value;
                }
                var flumeTempParams = [];
                if($scope.addnParamsFlumeSource){
                    angular.forEach($scope.addnParamsFlumeSource, function(val, k) {
                        if(val.paramname != ""){
                            var flumeTempParamsObj = {};
                            flumeTempParamsObj[val.paramname] = val.paramvalue;
                            flumeTempParams.push(flumeTempParamsObj);
                        }
                    })
                }
                srcObj.objAddlParams = flumeTempParams*/
                srcObj.strSrcConnectType = "";
                srcObj.strSrcDataFilepath = "";
                srcObj.objAddlParams = [];
                destObj = {};
                destObj.strDstHost = document.getElementById("flumeDestinationHost").value;
                destObj.nPort = document.getElementById("flumeDestinationPort").value;
            }
            $scope.sourceConfigDetails.objFlumeAgentSrcDtls = srcObj;
            $scope.sourceConfigDetails.objFlumeAgentDstDtls = destObj;
            $scope.sourceConfigDetails.addlParams = tempParams;

            sourceJSONData = {
                "inSourceId" : 0,
                "strSourceConfigName": document.getElementById("sourceConfigName").value.trim(),
                "strSourceType": $scope.sourceTypes.selected.name,
                "objSourceConfigDetails": $scope.sourceConfigDetails
            };
            console.log(JSON.stringify(sourceJSONData));
            token = localStorage.getItem('loginToken');
            configService.saveSourceConfig(sourceJSONData, token).then(
                function (data) {
                    $scope.success = data.success;
                    if (data.success === true) {
                        console.log(data.successDetails.successMessage);
                        $scope.saveMessage = data.successDetails.successMessage;
                        $scope.saved = true;
                        if ($scope.nextBtn === true) {
                            $scope.openSourcePopUp.close();
                            $scope.addNewConfig("process");
                        }
                        $scope.getSourceDetails();
                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode ===  "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(data);
                        $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                        $scope.saved = true;
                    }
                }
            );
        }
        if (idName === "process") {
            if (p1.currentTarget.ownerDocument.activeElement.id === "next") {
                $scope.nextBtn = true;
            }
            processJSONData = {};
            tempParams = [];
            if ($scope.addnParamsProcess) {
                tempParams = JSON.parse(angular.toJson($scope.addnParamsProcess));
            }
            if ($scope.processTypes.selected.name === "spark") {
                $scope.processConfigDetails.nBatchInterval = document.getElementById("miniBatch").value;
                /*$scope.processConfigDetails.nExcMaxcore = document.getElementById("sparkCore").value;
                $scope.processConfigDetails.nExcMaxmem = document.getElementById("sparkexecutorMemory").value;
                $scope.processConfigDetails.nDrvMaxmem =document.getElementById("sparkdriverMemory").value;*/
                $scope.processConfigDetails.strSparkMaster = $scope.Deployementmodes.selected.name;
                if ($scope.Deployementmodes.selected.name === "standalone") {
                    $scope.processConfigDetails.standaloneURL = document.getElementById("modeURL").value;
                } else if ($scope.Deployementmodes.selected.name === "local") {
                    $scope.processConfigDetails.localThreads = document.getElementById("threads").value;
                }
                $scope.processConfigDetails.strSparkAppName = document.getElementById("processName").value;

            }
            $scope.processConfigDetails.addlParams = tempParams;

            processJSONData = {
                "inProcessId" : 0,
                "strProcessConfigName": document.getElementById("processName").value.trim(),
                "strProcessType": $scope.processTypes.selected.name,
                "objConfigDetails": $scope.processConfigDetails
            };
            console.log(JSON.stringify(processJSONData));
            token = localStorage.getItem('loginToken');
            configService.saveProcessConfig(processJSONData, token).then(
                function (data) {
                    $scope.success = data.success;
                    if (data.success === true) {
                        console.log(data.successDetails.successMessage);
                        $scope.saved = true;
                        $scope.saveMessage = data.successDetails.successMessage;
                        if ($scope.nextBtn === true) {
                            $scope.openProcessPopUp.close();
                            $scope.addNewConfig("sink");
                        }
                        $scope.getProcessDetails();

                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(data);
                        $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                        $scope.saved = true;
                    }
                }
            );
        }
        if (idName === "sink") {
            sinkJSONData = {};
            tempParams = [];
            if ($scope.addnParamsSink) {
                tempParams = JSON.parse(angular.toJson($scope.addnParamsSink));
            }
            if ($scope.sinkTypes.selected.name === "Elassandra") {
                $scope.sinkConfigDetails.strNodelst = document.getElementById("node").value;
                $scope.sinkConfigDetails.strTopology = $scope.topologies.selected.name;
                $scope.sinkConfigDetails.inDcReplicationFactor = document.getElementById("replication").value;
            }
            $scope.sinkConfigDetails.addlParams = tempParams;
            sinkJSONData = {
                "inSinkId": 0,
                "strSinkName": document.getElementById("sinkName").value.trim(),
                "strSinkType": $scope.sinkTypes.selected.name,
                "objSinkConfigDetails": $scope.sinkConfigDetails
            };
            console.log(JSON.stringify(sinkJSONData));
            token = localStorage.getItem('loginToken');
            configService.saveSinkConfig(sinkJSONData, token).then(
                function (data) {
                    $scope.success = data.success;
                    if (data.success === true) {
                        console.log(data.successDetails.successMessage);
                        $scope.saved = true;
                        $scope.saveMessage = data.successDetails.successMessage;
                        $scope.getSinkDetails();
                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(data);
                        $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                        $scope.saved = true;
                    }
                }
            );
        }
   /*     if (idName === "udf") {
            udfJSONData = {};
            udfJSONData = {
            		"inUdfId": 0,
            		"strUdfName": document.getElementById("functionName").value.trim(),
            		"strMainClassName": document.getElementById("className").value.trim(),
            		"strUdfReturnType": document.getElementById("returnType").value.trim(),
            		"strUdfDesc": document.getElementById("description").value.trim(),
            		"strServerUrl": "<server ip>/tmp"
            	}
            console.log(JSON.stringify(udfJSONData));
            token = localStorage.getItem('loginToken');
            configService.saveUdfConfig(udfJSONData, token).then(
                function (data) {
                    if (data.success === true) {
                        console.log(data.successDetails.successMessage);
                        $scope.success = data.success;
                        $scope.saved = true;
                        $scope.saveMessage = data.successDetails.successMessage;
                        $scope.getUdfDetails();
                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(data);
                        $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                        $scope.saved = true;
                    }
                }
            );
        }
        */
        if (idName === "udf") {
            udfJSONData = {};
            udfJSONData = {
            		"inUfId": 0,
            		"strUfName": document.getElementById("functionName").value.trim(),
            		"strRegisterMethod": document.getElementById("registerMethod").value.trim(),
            		"strUfConfigName": document.getElementById("configurationName").value.trim(),
            		"strUfDesc": document.getElementById("description").value.trim(),
            		"objCodeBase": document.getElementById("codeBase").value.trim()
            	}


            console.log(JSON.stringify(udfJSONData));
            //var jarFile = document.getElementById("jarFile").files[0];
            //var uploadUrl = serviceURL.saveUdfConfig;
            token = localStorage.getItem('loginToken');
            configService.saveUdfConfig('new',udfJSONData, token).then(
                function (data) {
                	$scope.success = data.success;
                    if (data.success === true) {
                        console.log(data.successDetails.successMessage);
                    	//console.log(data.root.statusMessage);
                        $scope.success = data.success;
                        $scope.saved = true;
                        $scope.saveMessage = data.successDetails.successMessage;
                        //$scope.saveMessage = data.root.statusMessage;
                        $scope.getUdfDetails();
                    } else {
                    	$scope.success = false;
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(data);
                        $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                        $scope.saved = true;
                    }
                }
            );
        }
        
        
        
        
        
        $scope.updateUdfConfig = function(udfRecord){
    		var updateUdfObj = {}
    		updateUdfObj = {
    				"inUfId" : udfRecord.ufId,
    				"strUfName" : udfRecord.strUfName,
    				"strRegisterMethod" : udfRecord.strRegisterMethod,
    				"strUfConfigName" : udfRecord.configurationName,
    				"strUfDesc" : udfRecord.strUfDesc,
    				"objCodeBase" : udfRecord.objCodeBase
    		}
    		
            /*var jarFile = document.getElementById("jarFile").files[0];
            if(!jarFile){
            	jarFile = ""
            }*/
            //var uploadUrl = serviceURL.addPortalInput;
            var token = localStorage.getItem('loginToken');
    		configService.saveUdfConfig('edit',udfJSONData, token).then(
                    function (data) {
                    	$scope.success = data.success;
                        if (data.success === true) {
                            console.log(data.successDetails.successMessage);
                        	//console.log(data.root.statusMessage);
                            $scope.success = data.success;
                            $scope.saved = true;
                            $scope.saveMessage = data.successDetails.successMessage;
                            //$scope.saveMessage = data.root.statusMessage;
                            $scope.getUdfDetails();
                        } else {
                        	$scope.success = false;
                            error = data.errors;
                            console.log(error.errorMessage);
                            if (error.errorCode === "3006") {
                                $scope.openConfirmLogin();
                            }
                            console.log(data);
                            $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                            $scope.saved = true;
                        }
                    }
                );
            /*fileUpload.uploadFileToUrl('update',token,logoFile,uploadUrl,updateUdfObj).then(
    				function(data) {
    					if (data.success == true) {
    						$scope.success= data.success;
    						$scope.saveMessage = data.successDetails.successMessage;
    						$scope.saved = true;
    						$scope.getUdfDetails();
    					} else {
    						var error = data.errors;
    						console.log(error.errorMessage);
    						if(error.errorCode=="3006"){
    							$scope.openConfirmLogin();
    						}
    						$scope.saveMessage = error.errorCode +"-"+ error.errorMessage ;
    						$scope.success = false;
    						$scope.saved = true;
    						
    					}
    				});*/
    	}
        if (idName === "category") {
            if (p3.currentTarget.ownerDocument.activeElement.id === "next") {
                $scope.nextBtn = true;
            }
            categoriesJSONData = {};
            categoriesJSONData = {
                "inCategoryId": 0,
                "strCategoryName": document.getElementById("catname").value.trim(),
                "strCategoryDesc": document.getElementById("CatDes").value,
                "dateFrom": p1,
                "dateTo": p2
            };
            console.log(JSON.stringify(categoriesJSONData));
            token = localStorage.getItem('loginToken');
            configService.saveCategoryConfig(categoriesJSONData, token).then(
                function (data) {
                    $scope.success = data.success;
                    if (data.success === true) {
                        console.log(data.successDetails.successMessage);
                        $scope.saved = true;
                        $scope.saveMessage = data.successDetails.successMessage;
                        if ($scope.nextBtn === true) {
                            $scope.openCategoryPopUp.close();
                            $scope.addNewConfig("kpi");
                        }
                        $scope.getCategoryDetails();
                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(data);
                        $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                        $scope.saved = true;
                    }
                }
            );
        }
        if (idName === "kpi") {
            KpiJSONData = {};
            $scope.categories = [];
            angular.forEach($scope.existingCategory.selected, function (val, k) {
                $scope.categories.push({
                    "inCategoryId" : val.id
                });
            });
			if(!$scope.categories || $scope.categories.length == 0){
				$scope.categoryError = true;
				return;
			}
            KpiJSONData = {
                "inKpiId": 0,
                "strKpiName": document.getElementById("kpiName").value.trim(),
                "strKpiDesc": document.getElementById("kpiDes").value,
                "categories" : $scope.categories
            };
            console.log(JSON.stringify(KpiJSONData));
            token = localStorage.getItem('loginToken');
            configService.saveKPIConfig(KpiJSONData, token).then(
                function (data) {
                    $scope.success = data.success;
                    if (data.success === true) {
                        console.log(data.successDetails.successMessage);
                        $scope.saveMessage = data.successDetails.successMessage;
                        $scope.saved = true;
                        $scope.getKPIDetails();
                    } else {
                        var error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(data);
                        $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                        $scope.saved = true;
                    }
                }
            );
        }
    };
    /*$scope.uploadJarFile = function () {
		var jarFile;
		jarFile = event.target.files[0];
		$scope.jarFileName = jarFile.name;
		$scope.fileTypeError = false;
		var selectedFileType = $scope.jarFileName.substr(($scope.jarFileName.lastIndexOf('.') + 1)).toLowerCase();
		var jFileName=$scope.jarFileName.substr(($scope.jarFileName.indexOf('.') + 1)).toLowerCase();
		/*if(selectedFileType !== "jar"){
			//$("#editFunName").hide();
			$scope.fileTypeError = true;
			angular.element("input[type='file']").val(null);
		}
		if(jFileName === "jar"){
			$scope.fileTypeError = false;
			$scope.udfFields = false;
			$scope.saved = false;
		}
		else{
			//angular.element("input[type='jarfile']").val(jarFile);
			$scope.fileTypeError = true;
			angular.element("input[type='file']").val(null);
			
		}
		console.log(selectedFileType);
	};*/
	/**
	 * Method to show a error message when no configuration selected for KPI.
	 */
	$scope.categoryChange = function(){
		if($scope.existingCategory){
			if(!$scope.existingCategory.selected || $scope.existingCategory.selected.length == 0){
				$scope.categoryError = true;
			}
			else{
				$scope.categoryError = false;
			}
		}
	}
	$scope.enableJarUpload = function(){
		$scope.jarFileInput = false;
		$("#uploadJar").hide();

		$scope.jarFileInput = !($scope.jarFileInput);
		console.log("$scope.showFileInput : " + $scope.showFileInput);
	}
    /**
     * Method to clear configuration fields.
     * @param idName
     * @param action
     * @param saveRecord
     */
    $scope.clearConfig = function (idName, action, saveRecord) {
        if (action === "edit") {
            saveRecord = {};
            if (idName === "source") {
                $scope.sourceRecord = {};
                $scope.sourceRecord.inSourceId = saveRecord.inSourceId;
                $scope.sourceTypes.selected = "";
                $scope.addnParamsSource = [{
                    "key" : "",
                    "value" : ""
                }];
            } else if (idName === "process") {
            	$scope.processRecord = {};
                $scope.processRecord = saveRecord.inProcessId;
                $scope.processTypes.selected = "";
                $scope.Deployementmodes.selected = "";
                $scope.addnParamsProcess = [{
                    "key" : "",
                    "value" : ""
                }];
            } else if (idName === "sink") {
            	$scope.sinkRecord = {};
                $scope.sinkRecord = saveRecord.inSinkId;
                $scope.sinkTypes.selected = "";
                $scope.topologies.selected = "";
                $scope.addnParamsSink = [{
                    "key" : "",
                    "value" : ""
                }];
            } else if (idName === "udf") {
            	$scope.udfRecord = {};
            	$scope.udfRecord.inUfId = saveRecord.inUfId;
                
               // $scope.udfFields = true;
               
            } else if (idName === "category") {
            	$scope.categoryRecord = {};
                $scope.categoryRecord = saveRecord.inCategoryId;
                $scope.minToDate = new Date();
            } else if (idName === "kpi") {
            	$scope.kpiRecord = {};
                $scope.kpiRecord = saveRecord.inKpiId;
                $scope.existingCategory.selected = "";
            }
        } else if (action === "new") {
            if (idName === "source") {
                document.getElementById("sourceConfigName").value = "";
		        $scope.sourceTypes.selected = "";
                document.getElementById("groupName").value = "";
                document.getElementById("topicName").value = "";
                document.getElementById("zookeepr").value = "";
                document.getElementById("offset").value = "";
                document.getElementById("keySr").value = "";
                document.getElementById("valSr").value = "";
		        document.getElementById("flumeDestinationHost").value = "";
		        document.getElementById("flumeDestinationPort").value = "";
                document.getElementById("readThreads").value = "";
                document.getElementById("partitionDetails").value = "";
                document.getElementById("CustSr").value = "";
		        /*$scope.sourceTypes.selected ="";*/
		        $scope.addnParamsSource = [{
                    "key" : "",
                    "value" : ""
                }];
            } else if (idName === "process") {
                document.getElementById("processName").value = "";
		        $scope.processTypes.selected = "";
		        $scope.Deployementmodes.selected = "";
                document.getElementById("modeURL").value = "";
                document.getElementById("threads").value = "";
                document.getElementById("miniBatch").value = "";
                /*document.getElementById("sparkCore").value = "";
                document.getElementById("sparkexecutorMemory").value = "";
                document.getElementById("sparkdriverMemory").value = "";*/
                $scope.addnParamsProcess = [{
                    "key" : "",
                    "value" : ""
                }];
            } else if (idName === "sink") {
                document.getElementById("sinkName").value = "";
                document.getElementById("node").value = "";
                document.getElementById("replication").value = "";
                $scope.sinkTypes.selected = "";
                $scope.topologies.selected = "";
                $scope.addnParamsSink = [{
                    "key" : "",
                    "value" : ""
                }];
            } else if (idName === "udf") {
                document.getElementById("functionName").value = "";
                document.getElementById("className").value = "";
                document.getElementById("configurationName").value = "";
                document.getElementById("description").value = "";
               // $scope.udfFields = true;
                angular.element("input[type='file']").val(null);
            } else if (idName === "category") {
                document.getElementById("catname").value = "";
                document.getElementById("CatDes").value = "";
                document.getElementById("dateFrom").value = "";
                document.getElementById("dateTo").value = "";
                $scope.minToDate = new Date();
            } else if (idName === "kpi") {
                document.getElementById("kpiName").value = "";
                document.getElementById("kpiDes").value = "";
		        $scope.existingCategory.selected = [];
            }
        }
    };
    /**
     * Method to save configuration while edit.
     * @param idName
     * @param saveRecord
     */
    $scope.saveEditConfig =  function (idName, saveRecord) {
        $scope.sourceConfigDetails = {};
        $scope.processConfigDetails = {};
        $scope.sinkConfigDetails = {};
        $scope.categoriesConfigDetails = {};
        var error, sourceJSONData, tempParams, srcObj, destObj, processJSONData, sinkJSONData, udfJSONData, categoriesJSONData, KpiJSONData;

        console.log("$scope.saveEditConfig function");
        if (idName === "source") {

            sourceJSONData = {};
            tempParams = [];
            if ($scope.addnParamsSource) {
                tempParams = JSON.parse(angular.toJson($scope.addnParamsSource));
            }
            if ($scope.sourceTypes.selected.name === "kafka") {
                $scope.sourceConfigDetails.strGroupName = document.getElementById("groupName").value;
                $scope.sourceConfigDetails.strTopicName = document.getElementById("topicName").value;
                $scope.sourceConfigDetails.strZookeeperlst = document.getElementById("zookeepr").value;
                $scope.sourceConfigDetails.strOffset = saveRecord.formatDetails.offset;
                $scope.sourceConfigDetails.strKeySerializer = document.getElementById("keySr").value;
                $scope.sourceConfigDetails.strValSerializer = document.getElementById("valSr").value;
                $scope.sourceConfigDetails.numThread = document.getElementById("readThreads").value;
                console.log(JSON.stringify($scope.sourceConfigDetails));
            }
            if ($scope.sourceTypes.selected.name === "flume") {
                srcObj = {};
                /*srcObj.strSrcConnectType = $scope.flumeConnectionTypes.selected.name;
                srcObj.strSrcDataFilepath = document.getElementById("sourceFilePath").value;
                if($scope.flumeConnectionTypes.selected.name == "remote"){
                    srcObj.strSrcHost = document.getElementById("flumeSourceHost").value;
                    srcObj.strSrcUser = document.getElementById("hostUser").value;
                    srcObj.strSrcPwd = document.getElementById("hostPassword").value;
                }
                var flumeTempParams = [];
                if($scope.addnParamsFlumeSource){
                    angular.forEach($scope.addnParamsFlumeSource, function(val, k) {
                        if(val.paramname != ""){
                            var flumeTempParamsObj = {};
                            flumeTempParamsObj[val.paramname] = val.paramvalue;
                            flumeTempParams.push(flumeTempParamsObj);
                        }
                    })
                }
                srcObj.objAddlParams = flumeTempParams*/
                srcObj.strSrcConnectType = "";
                srcObj.strSrcDataFilepath = "";
                srcObj.objAddlParams = [];
                destObj = {};
                destObj.strDstHost = document.getElementById("flumeDestinationHost").value;
                destObj.nPort = document.getElementById("flumeDestinationPort").value;
            }
            $scope.sourceConfigDetails.objFlumeAgentSrcDtls = srcObj;
            $scope.sourceConfigDetails.objFlumeAgentDstDtls = destObj;
            $scope.sourceConfigDetails.addlParams = tempParams;
            sourceJSONData = {
                "inSourceId" : saveRecord.inSourceId,
                "strSourceConfigName": document.getElementById("sourceConfigName").value.trim(),
                "strSourceType": $scope.sourceTypes.selected.name,
                "objSourceConfigDetails": $scope.sourceConfigDetails
            };

            console.log(JSON.stringify(sourceJSONData));
            token = localStorage.getItem('loginToken');
            configService.saveSourceConfig(sourceJSONData, token).then(
                function (data) {
                    $scope.success = data.success;
                    if (data.success === true) {
                        console.log(data.successDetails.successMessage);
                        $scope.saveMessage = data.successDetails.successMessage;
                        $scope.saved = true;
                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(data);
                        $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                        $scope.saved = true;
                    }
                }
            );
        }
        if (idName === "process") {
            processJSONData = {};
            tempParams = [];
            if ($scope.addnParamsProcess) {
                tempParams = JSON.parse(angular.toJson($scope.addnParamsProcess));
            }
            if ($scope.processTypes.selected.name === "spark") {
                $scope.processConfigDetails.nBatchInterval = document.getElementById("miniBatch").value;
                /*$scope.processConfigDetails.nExcMaxcore = document.getElementById("sparkCore").value;
                $scope.processConfigDetails.nExcMaxmem = document.getElementById("sparkexecutorMemory").value;
                $scope.processConfigDetails.nDrvMaxmem =document.getElementById("sparkdriverMemory").value;*/
                $scope.processConfigDetails.strSparkMaster = $scope.Deployementmodes.selected.name;
                if ($scope.Deployementmodes.selected.name === "standalone") {
                    $scope.processConfigDetails.standaloneURL = document.getElementById("modeURL").value;
                } else if ($scope.Deployementmodes.selected.name === "local") {
                    $scope.processConfigDetails.localThreads = document.getElementById("threads").value;
                }
                $scope.processConfigDetails.strSparkAppName = document.getElementById("processName").value;
            }
            $scope.processConfigDetails.addlParams = tempParams;

            processJSONData = {
                "inProcessId" : saveRecord.inProcessId,
                "strProcessConfigName": document.getElementById("processName").value.trim(),
                "strProcessType": $scope.processTypes.selected.name,
                "objConfigDetails": $scope.processConfigDetails

            };
            console.log(JSON.stringify(processJSONData));
            token = localStorage.getItem('loginToken');
            configService.saveProcessConfig(processJSONData, token).then(
                function (data) {
                    $scope.success = data.success;
                    if (data.success === true) {
                        console.log(data.successDetails.successMessage);
                        $scope.saveMessage = data.successDetails.successMessage;
                        $scope.saved = true;

                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(data);
                        $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                        $scope.saved = true;
                    }
                }
            );
        }
        if (idName === "sink") {
            sinkJSONData = {};
            tempParams = [];
            if ($scope.addnParamsSink) {

                tempParams = JSON.parse(angular.toJson($scope.addnParamsSink));
            }
            if ($scope.sinkTypes.selected.name === "Elassandra") {
                $scope.sinkConfigDetails.strNodelst = document.getElementById("node").value;
                $scope.sinkConfigDetails.strTopology = $scope.topologies.selected.name;
                $scope.sinkConfigDetails.inDcReplicationFactor = document.getElementById("replication").value;
            }
            $scope.sinkConfigDetails.addlParams = tempParams;
            sinkJSONData = {
                "inSinkId": saveRecord.inSinkId,
                "strSinkName": document.getElementById("sinkName").value.trim(),
                "strSinkType": $scope.sinkTypes.selected.name,
                "objSinkConfigDetails": $scope.sinkConfigDetails
            };

            console.log(JSON.stringify(sinkJSONData));
            token = localStorage.getItem('loginToken');
            configService.saveSinkConfig(sinkJSONData, token).then(
                function (data) {
                    $scope.success = data.success;
                    if (data.success === true) {
                        console.log(data.successDetails.successMessage);
                        $scope.saveMessage = data.successDetails.successMessage;
                        $scope.saved = true;
                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(data);
                        $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                        $scope.saved = true;
                    }
                }
            );
        }
        if (idName === "udf") {
        	udfJSONData = {};
            
        	udfJSONData ={
        			"inUfId" : saveRecord.inUfId,
        			"strUfName": document.getElementById("functionName").value,
            		"strRegisterMethod": document.getElementById("registerMethod").value,
            		"strUfConfigName": document.getElementById("configurationName").value,
            		"strUfDesc": document.getElementById("description").value,
            		"objCodeBase": document.getElementById("codeBase").value
        	}
          
            console.log(JSON.stringify(udfJSONData));
        	/*var jarFile = document.getElementById("jarFile").files[0];
            if(!jarFile){
            	jarFile = ""
            }*/
            token = localStorage.getItem('loginToken');
            configService.saveUdfConfig('update',udfJSONData, token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.success = data.success;
                        $scope.saveMessage = data.successDetails.successMessage;
                        //$scope.saveMessage = data.root.statusMessage;
                        console.log(data.successDetails.successMessage);
                        //console.log(data.root.statusMessage);
                        $scope.saved = true;
                        $scope.getUdfDetails();
                    } else {
                    	$scope.success = false;
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(data);
                        $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                        $scope.saved = true;
                    }
                }
            );
        }
        
        
       /* $scope.updateUdfConfig = function(udfRecord){
    		var updateUdfObj = {}
    		updateUdfObj = {
    				"inUfId" : udfRecord.ufId,
    				"strUfName" : udfRecord.strUfName,
    				"strMainClassName" : udfRecord.strMainClassName,
    				"strUfReturnType" : udfRecord.strUfReturnType,
    				"strUfDesc" : udfRecord.strUfDesc
    		}
    		
            var jarFile = document.getElementById("logo").files[0];
            if(!jarFile){
            	jarFile = ""
            }
            //var uploadUrl = serviceURL.addPortalInput;
            var token = localStorage.getItem('loginToken');
            fileUpload.uploadFileToUrl('update',token,logoFile,uploadUrl,updateUdfObj).then(
    				function(data) {
    					if (data.success == true) {
    						$scope.success= data.success;
    						$scope.saveMessage = data.successDetails.successMessage;
    						$scope.saved = true;
    						$scope.getUdfDetails();
    					} else {
    						var error = data.errors;
    						console.log(error.errorMessage);
    						if(error.errorCode=="3006"){
    							$scope.openConfirmLogin();
    						}
    						$scope.saveMessage = error.errorCode +"-"+ error.errorMessage ;
    						$scope.success = false;
    						$scope.saved = true;
    						
    					}
    				});
    	}
        */
        if (idName === "category") {
            categoriesJSONData = {};
            categoriesJSONData = {
                "inCategoryId": saveRecord.inCategoryId,
                "strCategoryName": document.getElementById("catname").value.trim(),
                "strCategoryDesc": document.getElementById("CatDes").value,
                "dateFrom": saveRecord.dateFrom,
                "dateTo": saveRecord.dateTo
            };
            console.log(JSON.stringify(categoriesJSONData));
            token = localStorage.getItem('loginToken');
            configService.saveCategoryConfig(categoriesJSONData, token).then(
                function (data) {
                    $scope.success = data.success;
                    if (data.success === true) {
                        console.log(data.successDetails.successMessage);
                        $scope.saveMessage = data.successDetails.successMessage;
                        $scope.saved = true;
                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(data);
                        $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                        $scope.saved = true;
                    }
                }
            );
        }
        if (idName === "kpi") {
            KpiJSONData = {};
            $scope.categories = [];
            angular.forEach($scope.existingCategory.selected, function (val, k) {
                $scope.categories.push({
                    "inCategoryId" : val.id
                });
            });
			if(!$scope.categories || $scope.categories.length == 0){
				$scope.categoryError = true;
				return;
			}
            KpiJSONData = {
                "inKpiId": saveRecord.inKpiId,
                "strKpiName": document.getElementById("kpiName").value.trim(),
                "strKpiDesc": document.getElementById("kpiDes").value,
                "categories" : $scope.categories
            };
            console.log(JSON.stringify(KpiJSONData));
            token = localStorage.getItem('loginToken');
            configService.saveKPIConfig(KpiJSONData, token).then(
                function (data) {
                    $scope.success = data.success;
                    if (data.success === true) {
                        console.log(data.successDetails.successMessage);
                        $scope.saveMessage = data.successDetails.successMessage;
                        $scope.saved = true;
                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(data);
                        $scope.saveMessage = error.errorCode + " : " + error.errorMessage;
                        $scope.saved = true;
                    }
                }
            );
        }
    };
    /**
     * Method to test flume connection.
     */
    $scope.testFlumeConnection = function () {
        var flumeHost, flumePort, error;
        flumeHost = document.getElementById("flumeDestinationHost").value;
        flumePort = document.getElementById("flumeDestinationPort").value;
        token = localStorage.getItem('loginToken');
        configService.testFlumeConnection(flumeHost, flumePort, token).then(
            function (data) {
                if (data.success === true) {
                    console.log(data.successDetails.successMessage);
                    if (data.successDetails.successMessage.toLowerCase() === "failed") {
                        $scope.saveMessage = data.successDetails.successMessage;
                        //$scope.success = data.success;
                        $scope.success = false;
                    } else if (data.successDetails.successMessage.toLowerCase() === "success") {
                        $scope.saveMessage = data.successDetails.successMessage;
                        $scope.success = true;
                    }
                    $scope.saved = true;
                } else {
                    error = data.errors;
                    console.log(data);
                    $scope.saveMessage = "Error in testing Connection";
                    $scope.saved = true;
                }
            }
        );
    };
    /**
     * Configurations to set the parameters for Scheduled
     */
    $scope.today = function () {
        $scope.dateFrom = new Date();
        $scope.dateTo = new Date();
        $scope.minToDate = new Date();
    };
    $scope.dateFromState = {
        opened: false
    }; /* Model to keep track if Popup is open */

    $scope.dateToState = {
        opened: false
    }; /* Model to keep track if Popup is open */
    $scope.today(); /* Sets Date as Today Initially */
    $scope.showWeeks = false; /* Hides Week Numbers */
    $scope.minDate = new Date(); /* Disables past dates */
    $scope.format = 'dd MMMM, yyyy'; /* Date Format Setting */
    $scope.dateOptions = {
        startingDay: 1,
        showWeeks: false
    }; /* to be passed in the date-options directive */


    /* FUNCTIONS */

    $scope.opendateFromPicker = function () {
        $scope.dateFromState.opened = true;
    }; /* Button Click Event to open Popup */

    $scope.opendateToPicker = function () {
        $scope.dateToState.opened = true;
    }; /* Button Click Event to open Popup */

    $scope.fromDateChange = function(action,categoryRecord){
    	var val = document.getElementById("dateFrom").value
		var dateFrom =  new Date().setDate( new Date(val).getDate());
		var val1 = document.getElementById("dateTo").value;
		var dateTo = new Date().setDate( new Date(val1).getDate());
		$scope.minToDate = val;
		if(action == "edit" && (($scope.categoryRecord.dateFrom > $scope.categoryRecord.dateTo) || (!$scope.categoryRecord.dateTo))){
			$scope.categoryRecord.dateTo = new Date().setDate( new Date(val).getDate());
		}else if(action == "new" && (dateFrom > dateTo)){
			document.getElementById("dateTo").value = val;
    	}
    }
    $scope.checkName=function(configName,action,configId)	{
    	
    	if(configName == "sourceConfigName"){
    		var sourceObj=this;
    		sourceObj.editform.sourceConfigName.$setValidity('unique',true);
        	var existingSourceArray=$rootScope.existingSources;
        	var sourceObjectArray = $filter('filter')(existingSourceArray,  function (item) {
        		if(action == "edit"){
        			return ((item.name.toLowerCase().trim() == (document.getElementById("sourceConfigName").value).toLowerCase().trim()) && (item.id != configId));
        		}
        		else{
        			return (item.name.toLowerCase().trim() == (document.getElementById("sourceConfigName").value).toLowerCase().trim());
        		}
        		
        	},true);
        	if(sourceObjectArray!=undefined && sourceObjectArray.length>0){
        		sourceObj.editform.sourceConfigName.$setValidity('unique',false);
        		return;
        	}else{
        		return;
        	}
    	}
    	if(configName == "processName"){
    		var processObj=this;
    		processObj.editform.processName.$setValidity('unique',true);
        	var existingProcessArray= $rootScope.existingProcess;
        	var processObjectArray = $filter('filter')(existingProcessArray,  function (item) {
        		if(action == "edit"){
        			return ((item.name.toLowerCase().trim() == (document.getElementById("processName").value).toLowerCase().trim()) && (item.id != configId));
        		}
        		else{
        			return item.name.toLowerCase().trim() == (document.getElementById("processName").value).toLowerCase().trim();
        		}
        	},true);
        	if(processObjectArray!=undefined && processObjectArray.length>0){
        		processObj.editform.processName.$setValidity('unique',false);
        		return;
        	}else{
        		return;
        	}
    	}
    	if(configName == "sinkName"){
    		var sinkObj=this;
    		sinkObj.editform.sinkName.$setValidity('unique',true);
        	var existingSinkArray=$rootScope.existingSink;
        	var sinkObjectArray = $filter('filter')(existingSinkArray,  function (item) {
        		if(action == "edit"){
        			return ((item.name.toLowerCase().trim() == (document.getElementById("sinkName").value).toLowerCase().trim()) && (item.id != configId));
        		}
        		else{
        			return item.name.toLowerCase().trim() == (document.getElementById("sinkName").value).toLowerCase().trim();
        		}
        	},true);
        	if(sinkObjectArray!=undefined && sinkObjectArray.length>0){
        		sinkObj.editform.sinkName.$setValidity('unique',false);
        		return;
        	}else{
        		return;
        	}
    	}
    	if(configName == "functionName"){
    		var udfObj=this;
    		udfObj.editform.functionName.$setValidity('unique',true);
        	var existingUdfArray=$rootScope.existingUdf;
        	var udfObjectArray = $filter('filter')(existingUdfArray,  function (item) {
        		if(action == "edit"){
        			return ((item.name.toLowerCase().trim() == (document.getElementById("functionName").value).toLowerCase().trim()) && (item.id != configId));
        		}
        		else{
        			return item.name.toLowerCase().trim() == (document.getElementById("functionName").value).toLowerCase().trim();
        		}
        	},true);
        	if(udfObjectArray!=undefined && udfObjectArray.length>0){
        		udfObj.editform.functionName.$setValidity('unique',false);
        		return;
        	}else{
        		return;
        	}
    	}
    	if(configName == "catname"){
    		var categoryObj=this;
    		categoryObj.editform.catname.$setValidity('unique',true);
        	var existingCategoryArray=$rootScope.existingCategory;
        	var categoryObjectArray = $filter('filter')(existingCategoryArray,  function (item) {
        		if(action == "edit"){
        			return ((item.name.toLowerCase().trim() == (document.getElementById("catname").value).toLowerCase().trim()) && (item.id != configId));
        		}
        		else{
        			return item.name.toLowerCase().trim() == (document.getElementById("catname").value).toLowerCase().trim();
        		}
        	},true);
        	if(categoryObjectArray!=undefined && categoryObjectArray.length>0){
        		categoryObj.editform.catname.$setValidity('unique',false);
        		return;
        	}else{
        		return;
        	}
    	}
    	if(configName == "kpiName"){
    		var kpiObj=this;
    		kpiObj.editform.kpiName.$setValidity('unique',true);
        	var existingKPIArray=$rootScope.existingKPI;
        	var kpiObjectArray = $filter('filter')(existingKPIArray,  function (item) {
        		if(action == "edit"){
        			return ((item.name.toLowerCase().trim() == (document.getElementById("kpiName").value).toLowerCase().trim()) && (item.id != configId));
        		}
        		else{
        			return item.name.toLowerCase().trim() == (document.getElementById("kpiName").value).toLowerCase().trim();
        		}
        	},true);
        	if(kpiObjectArray!=undefined && kpiObjectArray.length>0){
        		kpiObj.editform.kpiName.$setValidity('unique',false);
        		return;
        	}else{
        		return;
        	}
    	}
    };
}]);
