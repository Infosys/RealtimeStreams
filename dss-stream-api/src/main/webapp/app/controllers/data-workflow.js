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
 * Description : Controller for workflow.
 */
/*global
app, console, serviceURL, msg, enjoyhint_instance, angular
*/
var stepCount, data1, pipelineId = 0, pipelineName = "";


wfapp.controller("wfController", ['$scope', '$rootScope', 'ngDialog', '$http', '$uibModal', 'wfService', '$templateCache', 'uiGridConstants', '$filter', '$sce', '$timeout', function ($scope, $rootScope, ngDialog, $http, $uibModal, wfService, $templateCache, $uiGridConstants, $filter, $sce, $timeout) {
	"use strict";
	stepCount = 0;
	$scope.stepId = stepId;
	//$scope.visualizeStepNumber = 0;
	$scope.showSchemaGrid = false;
	$scope.showSchemaGridSink = false;
	var identifier = 0, token = localStorage.getItem('loginToken'), error, tempEntityObj;
	$scope.persistEnabled = false;
	$scope.showQuerySection = true;
	$scope.deletedStepsArray = [];
	$scope.deletedVisStepsArray = [];
	$scope.tempVisArray = [];
	$scope.tempVisStep = 0;

	$rootScope.existingSources = [];
	$rootScope.existingProcess = [];
	$rootScope.existingSink = [];
	$rootScope.existingCategory = [];
	$rootScope.existingKPI = [];
	$rootScope.existingKPI.selected = {};
	$scope.windowNames = ["NoWindow", "New Window"];
	$scope.windowOptions = [{"name" : "NoWindow", "windowPeriod" : "", "slidingInterval" : ""}, {"name" : "New Window", "windowPeriod" : "", "slidingInterval" : ""}];
	/*$scope.newWindowName = "New window Name";*/

	$scope.disableWindowSelect = true;

	if (window.parent.angular.element(window.frameElement).scope().pipelineAction) {
		$scope.pipelineAction = window.parent.angular.element(window.frameElement).scope().pipelineAction;
	}

	$scope.graphTypes = [ {
		"name" : "Grafana"
	}, {
		"name" : "D3"
	}];

	$scope.graphSubTypes = [ {
		"name" : "Area"
	}, {
		"name" : "Line"
	}, {
		"name" : "Bar"
	}, {
		"name" : "Stacked Area"
	}];

	$scope.persistLocations = [{"name": "Elassandra"}, {"name": "OpenTSDB"}, {"name": "HDFS"}, {"name": "Lookup Table"}];

	$scope.HDFSfiles = [{"name": "Parquet"}, {"name": "ORC"}, {"name": "text"}];

	$scope.configDetails = {
        "lstsink" : [ {
            "name" : "Elassandra",
            "config" : ""
        }, {
            "name" : "OpenTSDB",
            "config" : ""
        } ]
	};

	$scope.sinkTypes = $scope.configDetails.lstsink;
	console.log($scope.sinkTypes);

	$scope.predecessors = [0];
	console.log(localStorage.getItem('loginToken'));


	/*$scope.sourceFilter = function(existingSources) {

	}*/
	$scope.getVisualizationMaxId = function () {
		//Service call to get the visualization id
		wfService.getVisualizationId(pipelineId).then(
			function (data) {
				if (data.success === true) {
                    $scope.tempVisStep = data.root.maxId;
                    console.log($scope.tempVisStep);
				} else {
					error = data.errors;
					console.log(error.errorMessage);
					if (error.errorCode === "3006") {
						$scope.openConfirmLogin();
					}
					$scope.tempVisStep = 0;
				}
			});
	};

	//$scope.getVisualizationMaxId();
	//Service call to get all existing Source names
//	wfService.getExistingSourceNames().then(
//			function (data) {
//				if (data.success == true) {
//					$rootScope.existingSources = data.root;
//					console.log($rootScope.existingSources);
//				} else {
//					var error = data.errors;
//					$rootScope.existingSources = "";
//					console.log(error.errorMessage);
//				}
//			});

//	//Service call to get all existing Process names
//	wfService.getExistingProcessNames().then(
//			function (data) {
//				if (data.success == true) {
//					$rootScope.existingProcess = data.root;
//					console.log($rootScope.existingProcess);
//				} else {
//					var error = data.errors;
//					$rootScope.existingProcess = "";
//					console.log(error.errorMessage);
//				}
//			});

	//Service call to get all existing Sink names
//	wfService.getExistingSinkNames().then(
//			function (data) {
//				if (data.success == true) {
//					$rootScope.existingSink = data.root;
//					console.log($rootScope.existingSink);
//				} else {
//					var error = data.errors;
//					$rootScope.existingSink = "";
//					console.log(error.errorMessage);
//				}
//			});

	//Service call to get all existing Category names
//	wfService.getExistingCategoryNames().then(
//			function (data) {
//				if (data.success == true) {
//					$rootScope.existingCategory = data.root;
//					console.log($rootScope.existingCategory);
//				} else {
//					var error = data.errors;
//					$rootScope.existingSink = "";
//					console.log(error.errorMessage);
//				}
//			});

	//Service call to get all existing KPI names
//	wfService.getExistingKPINames().then(
//			function (data) {
//				if (data.success == true) {
//					$rootScope.existingKPI = data.root;
//					$rootScope.existingKPI.selected = {};
//					console.log($rootScope.existingKPI);
//				} else {
//					var error = data.errors;
//					$rootScope.existingKPI = "";
//					console.log(error.errorMessage);
//				}
//			});

	//Function call to get the source name list
	//$scope.getSourceNameList();

//	$scope.typeOnSelection = function (rec) {
//		console.log(JSON.stringify(rec));
//		var sourceObj = {};
//		sourceObj.sourceId = rec.id;
//		wfService.getExistingSourcebyId(sourceObj).then(
//				function (data) {
//					if (data.success == true) {
//						$scope.sourceRecordW = data.root;
//						$scope.sourceRecordW = JSON.parse(JSON.stringify($scope.sourceRecordW));
//						$scope.sourceRecordW.formatDetails = JSON.parse($scope.sourceRecordW.objSourceConfigDetails);
//
//					} else {
//						var error = data.errors;
//						$scope.sourceRecord = "";
//						console.log(error.errorMessage);
//					}
//				});
//
//	}
	/*$scope.graphTypeOnSelection = function (rec){
		console.log(JSON.stringify(rec));
		if (rec.name == 'Graphana'){

		}
	}*/




	//Process Data



	//Query Section

	/*$scope.queryAdded = false;
	$scope.addQuery = function (){
		$scope.queryAdded = true;
	}*/



	/* $scope.$watch("selected", function (newValue, oldValue) {
		 angular.forEach($scope.windowOptions, function (val, k) {
			 if (val.name == $scope.selected){
				 $scope.windowPeriod = val.windowPeriod;
				 $scope.slidingInterval = val.slidingInterval;
			 }
		 })
	 });*/


	$scope.disableSave = true;
	$scope.queryAdded = false;
	$scope.queries = [];
	$scope.identifier = ($scope.queries.length + 1).toString();
	$scope.addQuery = function () {};
	$scope.showAddQuery = function () {};
	$scope.remove = function (index) {};

	$scope.setClickedRow = function (index) {};


	$scope.moveUp = function (num) {};
	$scope.moveDown = function (num) {};

	//Split the query in process
	//var sql = "SELECT pillers,Balloons, Tacks FROM the_database_file";

	function parseSql(sql) {}
	//Sink Data


	$scope.sinkOnSelect = function (rec) {};


	$scope.processIdOnSelection = function (selectedItem) {};

	$scope.editQueryRow = function (selectedQuery) {};





	//Function called on click of save button in popup screens - Source/Sink/Process pop-ups.	    
	$scope.savePopUp = function (idName, jsonData) {
		console.log("$scope.savePopUp function");
		var sourceJSONData, sourceStepId, processJSONData, processStepId, sinkJSONData, sinkStepId, visStepId, visJSONData;
		if (idName === "source") {
			sourceStepId = $('#stepId').val();
			console.log(sourceStepId);

			sourceJSONData = {};

			sourceJSONData = jsonData;

			console.log(JSON.stringify(sourceJSONData));
			savedStepsData[sourceStepId] = sourceJSONData;
			console.log(JSON.stringify(savedStepsData[sourceStepId]));
			//Showing message when source step is saved	
			window.parent.angular.element(window.frameElement).scope().showMessage($scope.stepAddEdit, "source");
			//window.parent.angular.element(window.frameElement).scope().cancelWFPopup("sourcePopUp");
			//document.getElementById("workflow").contentWindow.window.$("#savePipeline").click();
			$timeout(function () {
				window.parent.angular.element("#closeWFPopupID").triggerHandler('click');
			}, 0);


		}

		if (idName === "process") {
			processStepId = $('#stepId').val();
			console.log(processStepId);
			processJSONData = {};
			processJSONData = jsonData;
			savedStepsData[processStepId] = processJSONData;
			console.log(JSON.stringify(savedStepsData[processStepId]));

			window.parent.angular.element(window.frameElement).scope().showMessage($scope.stepAddEdit, "process");
			//window.parent.angular.element(window.frameElement).scope().cancelWFPopup("sourcePopUp");
			//document.getElementById("workflow").contentWindow.window.$("#savePipeline").click();
			$timeout(function () {
				window.parent.angular.element("#closeWFPopupID").triggerHandler('click');
			}, 0);
		}

		if (idName === "sink") {
			sinkStepId = $('#stepId').val();
			console.log(sinkStepId);

			sinkJSONData = {};

			sinkJSONData = jsonData;
			console.log(JSON.stringify(sinkJSONData));
			savedStepsData[sinkStepId] = sinkJSONData;
			console.log(JSON.stringify(savedStepsData[sinkStepId]));

			window.parent.angular.element(window.frameElement).scope().showMessage($scope.stepAddEdit, "sink");
			//window.parent.angular.element(window.frameElement).scope().cancelWFPopup("sourcePopUp");
			//document.getElementById("workflow").contentWindow.window.$("#savePipeline").click();
			$timeout(function () {
				window.parent.angular.element("#closeWFPopupID").triggerHandler('click');
			}, 0);
		}
		if (idName === "visualize") {
			visStepId = $('#stepId').val();
			console.log(visStepId);
			if (parseInt(visStepId) > Object.keys(savedStepsData).length) {
				$scope.tempVisArray.push(visStepId);
			}
			visJSONData = [];
			tempEntityObj = {};

			/*if($scope.sinkCassType == true) {
				$scope.strSinkType = "cassandra";
			}else{
				$scope.strSinkType = "OpenTSDB";
			}*/



			visJSONData = jsonData;
			console.log(JSON.stringify(visJSONData));
			savedStepsData[visStepId] = visJSONData;
			console.log(JSON.stringify(savedStepsData[visStepId]));

			window.parent.document.getElementById("stepMessageSection").style.visibility = "hidden";
			window.parent.document.getElementById("stepMessageSection").style.visibility = "visible";
			window.parent.angular.element(window.frameElement).scope().showMessage($scope.stepAddEdit,"visualize");
			$timeout(function () {
				window.parent.angular.element("#closeWFPopupID").triggerHandler('click');
			}, 0);
		}

		//$scope.identifier = 1;

		//$('#'+idName).hide();

		//$('#' + idName).toggle('slide', { direction: 'right' }, 700);
		//$("#mainDiv").removeClass("disabledbutton");
		//$("#mainDiv").addClass("enablebutton");		

		$('#' + idName + 'StepId').val("");
		//window.parent.document.getElementById("savePipelineBtn").style.visibility = "visible";
	};
	/**
	 *  Method to go to login page
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

//	Function to save the pipeline	
	$scope.saveDataFlow = function (pipelineName) {
		var sourceExists = false, processExists = false, sinkExists = false, errorFlag = false;
		console.log("Save");
		console.log(JSON.stringify(data1));
		delete data1["Name"];
		data1.strPipelineName = document.getElementById("workflow-name").value;
		data1.strPipelineName = pipelineName;

		var dataFlowJson =  data1;

		dataFlowJson.inPipelineId = pipelineId;
		//dataFlowJson.inKpiId = 4;
		//dataFlowJson.inKpiId = $scope.existingKPI.selected.id
		//dataFlowJson.inCategoryId = 1;
		//dataFlowJson.inCategoryId = $scope.existingCategory.selected.id
		dataFlowJson.strPipelineExeURL = "test"; // need to assign proper value

		//To get the visualization steps to be deleted.

		//Uncomment before demo
		//dataFlowJson.deletedVisualizations = $scope.deletedVisStepsArray;
		$scope.deletedVisList = [];
		if (window.parent.angular.element(window.frameElement).scope().deletedVisList) {
			$scope.deletedVisList = window.parent.angular.element(window.frameElement).scope().deletedVisList;
		}
		dataFlowJson.deletedVisualizations = $scope.deletedVisList;
		console.log("Del :" + JSON.stringify($scope.deletedVisStepsArray));

		console.log("Save>>>>>>>>>>>>>");

		console.log(JSON.stringify(dataFlowJson));
		window.parent.document.getElementById("loader1").style.visibility = "visible";
		token = localStorage.getItem('loginToken');
		wfService.saveDataflow(dataFlowJson, token).then(
			function (data) {
				if (data.success === true) {
						//alert(data.successDetails.successMessage);
					console.log(data.successDetails.successMessage);

					window.parent.angular.element(window.frameElement).scope().closepopup(dataFlowJson);

                		} else {
                    			$('#loader1').hide();
		                    	error = data.errors;
		                    	console.log(data);
		                    	//alert("Pipeline Save failed");
		                    	window.parent.angular.element(window.frameElement).scope().failurePopup(error.errorMessage, error.errorCode);
				}
				window.parent.document.getElementById("loader1").style.visibility = "hidden";
			}
		);


		//$('#'+idName).hide();
		//$('#' + idName).toggle('slide', { direction: 'right' }, 700);
		//$("#mainDiv").removeClass("disabledbutton");
		//$("#mainDiv").addClass("enablebutton");
		$('#' + pipelineName + 'StepId').val("");
		//window.parent.document.getElementById("savePipelineBtn").style.visibility = "visible";
	};
}]);










