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
 * @desc Controller for Pipeline screen and popups inside pipeline.
 * Logic for query section in Process popup is moved to another
 *     controller WFPopupCtrl in workflow-popup.js file.
 */
/*global
app, console, serviceURL, msg, enjoyhint_instance, angular
 */
app.controller("PipelineCtrl", ['$scope', '$rootScope', 'ngDialog', '$route', '$http', '$uibModal', '$templateCache', 'uiGridConstants', '$filter', '$timeout', 'pipelineService', 'configService', 'fileUpload','$sce',
	function ($scope, $rootScope, ngDialog, $route, $http, $uibModal, $templateCache, $uiGridConstants, $filter, $timeout, pipelineService, configService, fileUpload, $sce) {
	"use strict";
	$scope.$on('$viewContentLoaded', function () {
		$scope.loadedHTML = $route.current.templateUrl;
		//alert($scope.loadedHTML);
		if ($scope.loadedHTML.indexOf("pipeline.html") !== -1) {
			if (!$rootScope.modulesMain.pipeline.showBlock) {
				$scope.openConfirmLogin();
			} else {
				// Calling service to list all pipelines on load of pipeline screen.
				$scope.getPipelineDetails();
			}
			enjoyhint_instance.stop();
		} else if ($scope.loadedHTML.indexOf("execution.html") !== -1) {
			if (!$rootScope.modulesMain.execution.showBlock) {
				$scope.openConfirmLogin();
			} else {
				// Calling service to list all pipelines on load of pipeline screen.
				$scope.getPipelineDetails();
			}
			enjoyhint_instance.stop();
		}
	});
	//Scope variable to identify view/edit/create a pipeline
	$scope.monitoring = false;
	$scope.pipelineAction = "";
	$scope.showQuerySection = true;
	$scope.disableQuery = false;
	$scope.predecessors = [0];
	$scope.sourceSchemaData = [];
	$scope.sinkSchemaData = [];
	$scope.tempVisStep = 0;
	$scope.stepId = 0;

	$rootScope.existingSources = [];
	$rootScope.existingProcess = [];
	$rootScope.existingSink = [];
	$rootScope.existingKPI = [];
	var token = localStorage.getItem('loginToken'), visSinkSel = "", processSinkSel = "";
	console.log(token);

	$scope.windowNames = ["NoWindow", "New Window"];
	$scope.windowOptions = [{"name" : "NoWindow", "windowPeriod" : "", "slidingInterval" : ""},
		{"name" : "New Window", "windowPeriod" : "", "slidingInterval" : ""}];
	$scope.graphTypes =  [ {
		"name" : "Grafana"
	}];

	$scope.graphSubTypes =  [ {
		"name" : "Area"
	}, {
		"name" : "Line"
	}, {
		"name" : "Bar"
	}, {
		"name" : "Stacked Area"
	}];

	$scope.persistLocations = [{"name": "Elassandra"}];

	$scope.HDFSfiles = [{"name": "Parquet"}, {"name": "ORC"}, {"name": "text"}];

	$scope.sourcefileTypes = [{"name": "JSON"}, {"name": "Delimited"}, {"name": "Unstructured"}];

	$scope.configDetails = {
			"lstsource": [{
				"name": "kafka",
				"config": ""
			}, {
				"name": "flume",
				"config": ""
			}],
			"lstprocess": [{
				"name": "Spark Streaming",
				"config": ""
			}],
			"lstsink": [{
				"name": "Elassandra",
				"config": ""
			}]
	};
	$scope.sourceTypes = $scope.configDetails.lstsource;
	$scope.processTypes = $scope.configDetails.lstprocess;
	$scope.sinkTypes = $scope.configDetails.lstsink;

	/**
	 *  Method to list all pipelines
	 */
	$scope.getPipelineDetails = function () {
		token = localStorage.getItem('loginToken');
		return pipelineService.getExistingPipelines(token).then(
				function (data) {
					if (data.success === true) {
						$scope.existingPipelines = data.root;
						console.log(JSON.stringify($scope.existingPipelines));
						$scope.totalItems = $scope.existingPipelines.length;
						$scope.currentPage = 1;
						$scope.numPerPage = 10;
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if (error.errorCode === "3006") {
							$scope.openConfirmLogin();
						}
						$scope.existingPipelines = "";
					}
				}
		);
	};
	// Calling service to list all pipelines on load of pipeline screen.
	// $scope.getPipelineDetails();

	$scope.paginate = function(value) {
		  var begin, end, index;
		  begin = ($scope.currentPage - 1) * $scope.numPerPage;
		  end = begin + $scope.numPerPage;
		  index = $scope.existingPipelines.indexOf(value);
		  return (begin <= index && index < end);
		};
	/**
	 *  Method to list all Source names
	 */
	$scope.getSourceDetails = function () {
		token = localStorage.getItem('loginToken');
		return configService.getExistingSourceNames(token).then(
				function (data) {
					if (data.success === true) {
						$rootScope.existingSources = data.root;
						console.log($rootScope.existingSources);
						console.log("Inside success");
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if (error.errorCode === "3006") {
							$scope.openConfirmLogin();
						}
						$rootScope.existingSources = "";
					}
				}
		);
	};
	/**
	 *  Method to list all Process names
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
						console.log(error.errorMessage);
						if (error.errorCode === "3006") {
							$scope.openConfirmLogin();
						}
						$rootScope.existingProcess = "";
					}
				}
		);
	};
	/**
	 *  Method to list all Sink names
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
						console.log(error.errorMessage);
						if (error.errorCode === "3006") {
							$scope.openConfirmLogin();
						}
						$rootScope.existingSink = "";
					}
				}
		);
	};
	/**
	 *  Method to list all KPI names
	 */
	$scope.getKPIDetails = function () {
		token = localStorage.getItem('loginToken');
		return configService.getExistingKPINames(token).then(
				function (data) {
					if (data.success === true) {
						$rootScope.existingKPI = data.root;
						$rootScope.existingKPI.selected = {};
						console.log($rootScope.existingKPI);
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if (error.errorCode === "3006") {
							$scope.openConfirmLogin();
						}
						$rootScope.existingKPI = "";

					}
				}
		);
	};
	/**
	 *  Method to open Pipeline screen to create a pipeline
	 */
	$scope.openNewPipeline = function () {
		token = localStorage.getItem('loginToken');
		if (token) {
			$scope.pipelineAction = "add";
			$scope.tempVisStep = 0;
			$scope.selectedPipelineRecord = "";
			$scope.deletedVisList = [];
			$scope.newPipelinePopUp = $uibModal.open({
				templateUrl : 'app/views/pipeline-iframe.html',
				backdrop  : 'static',
				keyboard  : false,
				scope : $scope
			});
		} else {
			$scope.openConfirmLogin();
		}
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
     *  Method to open Save Pipeline Page to enter Pipeline name.
     *  #savePipeline button (inside WORKFLOW IFRAME) click event is also triggered
     *     inside this function to set the JSON data in required format.
     */
     $scope.savePipeline = function () {
            console.log("savePipeline Function >>>");
            var pipelineData, stepsArray = [], tempObj, sourceName, targetName, saveErrorFlag = false;
            //document.getElementById("workflow").contentWindow.window.$("#savePipeline").click();

            //Get the pipeline name from WORKFLOW IFRAME while editing, else clear the name.
            if (document.getElementById("workflow").contentWindow.window.pipelineName) {
                   $scope.pipelineName = document.getElementById("workflow").contentWindow.window.pipelineName;
                   $scope.pipelineId = document.getElementById("workflow").contentWindow.window.pipelineId;
                   $scope.stepAddEdit = "Update";
            } else {
                   $scope.pipelineName = "";
            }
/*            //Connector Validation
            document.getElementById("stepMessageSection").style.visibility = "hidden";
            if (document.getElementById("workflow").contentWindow.window.data1) {
                   pipelineData = document.getElementById("workflow").contentWindow.window.data1;
                   if (pipelineData.objPipelineConfigDetails.length > 0) {
                         angular.forEach(pipelineData.objPipelineConfigDetails, function(val, k) {
                                tempObj = {};
                                tempObj.type = val.command_type;
                                tempObj.id = val.Id;
                                stepsArray.push(tempObj);
                                if (!val.properties) {
            						saveErrorFlag = true;
            						$scope.notifyMessage(msg.msgInvalidComponent,true);
            					}
                         });
                   }
                   if (pipelineData.strConnectors.length > 0) {
                         angular.forEach(pipelineData.strConnectors, function(connObj, k) {
                                sourceName = "";
                                targetName = "";
                                angular.forEach(stepsArray, function(stepObjSrc, k) {
                                       if (connObj.sourceId == stepObjSrc.id) {
                                              console.log(stepObjSrc.type);
                                              sourceName = stepObjSrc.type;
                                              angular.forEach(stepsArray, function(stepObjTarget, k) {
                                                     if (connObj.targetId == stepObjTarget.id) {
                                                            console.log(stepObjTarget.type);
                                                            targetName = stepObjTarget.type;
                                                     }
                                              });
                                       }
                                });
                                if ((sourceName === "source" && targetName !== "process") || (sourceName === "process" && targetName !== "sink") || (sourceName === "sink" && targetName !== "visualize") || (sourceName === "visualize")) {
                                       //alert("Invalid connector");
                                       saveErrorFlag = true;
                                       $scope.notifyMessage(msg.msgInvalidConnector,true);
                                }
                         });
                   }
                   if (pipelineData.strConnectors.length !== (stepsArray.length - 1 )) {
                         //alert("Connector missing");
                         saveErrorFlag = true;
                         $scope.notifyMessage(msg.msgMissingConnector,true);
                   }
            }*/
            //Open the save pipeline popup screen.
            if (!saveErrorFlag) {
                   $scope.savePipelinePopup = $uibModal.open({
                         templateUrl : 'app/views/workflow-save-pipeline.html',
                         backdrop  : 'static',
                         keyboard  : false,
                         scope : $scope
                   });
            }
     };
	/**
	 * Method to open pipeline screen in IFRAME to view selected pipeline.
	 * @param pipelineRecord
	 *//*
	$scope.viewSelectedPipeline = function (pipelineRecord) {
		token = localStorage.getItem('loginToken');
		if (token) {
			$scope.pipelineAction = "view";
			$scope.selectedPipelineRecord = pipelineRecord;
			$scope.tempVisStep = 0;
			$scope.newPipelinePopUp = $uibModal.open({
				templateUrl : 'app/views/pipeline-iframe.html',
				backdrop  : 'static',
				keyboard  : false,
				scope : $scope
			});
		} else {
			$scope.openConfirmLogin();
		}
	};*/
	/**
	 *  Method to open pipeline screen in IFRAME to edit selected pipeline.
	 */
	$scope.editSelectedPipeline = function (pipelineRecord,action) {
		token = localStorage.getItem('loginToken');
		$scope.pipelineModel = {};
		if (token) {
			if (action == "view") {
				$scope.pipelineAction = "view";
				$scope.tempVisStep = 0;
			}
			else if (action == "edit") {
				$scope.pipelineAction = "edit";
				//Method to get max value of visualize entity id for a pipeline
				$scope.tempVisStep = $scope.getVisualizationMaxId(pipelineRecord.inPipelineId);
				$scope.maxVisId = $scope.tempVisStep;
			}
			$scope.selectedPipelineRecord = pipelineRecord;
			$scope.deletedVisList = [];
			var token = localStorage.getItem('loginToken');
			//   var pipelineRecord = window.parent.angular.element(window.frameElement).scope().selectedPipelineRecord
			//   var pipelineId = pipelineRecord.inPipelineId;
			   var pipelineRecordInp = {};
			   pipelineRecordInp.categoryId = pipelineRecord.inCategoryId;
			   pipelineRecordInp.kpiId = pipelineRecord.inKpiId;
				pipelineRecordInp.pipelineId = pipelineRecord.inPipelineId;
				console.log(JSON.stringify(pipelineRecordInp));
				pipelineService.getSelectedPipelineDetails(pipelineRecordInp, token).then(
					   function (data) {
						   if (data.success === true) {
							   var pipelineData = data.root;
							   var nodeObj = JSON.parse(pipelineData.nodes);
							   //pipelineRecord.nodes = pipelineData.nodes;
							   //$scope.pipelineModel.nodes = JSON.parse(pipelineData.nodes);
							   $scope.pipelineModel.nodeDetails = nodeObj.nodeDetails;
							   $scope.pipelineModel.addlDetails = nodeObj.addlDetails;
							   $scope.pipelineModel.edges = JSON.parse(pipelineData.edges);
							   $scope.pipelineModel.inPipelineId = pipelineData.inPipelineId;
							   $scope.pipelineModel.strPipelineName = pipelineData.strPipelineName;

							   $scope.newPipelinePopUp = $uibModal.open({
									templateUrl : 'app/views/pipeline-iframe.html',
									backdrop  : 'static',
									keyboard  : false,
									scope : $scope
								});

						   } else {
							   error = data.errors;
							   console.log(data);

						   }
					   });

			/*$scope.newPipelinePopUp = $uibModal.open({
				templateUrl : 'app/views/pipeline-iframe.html',
				backdrop  : 'static',
				keyboard  : false,
				scope : $scope
			});*/


		} else {
			$scope.openConfirmLogin();
		}
	};
	/**
	 *  Method to show the confirmation dialog before deleting a pipeline.
	 */
	$scope.openConfirm = function (data, callback) {
		ngDialog.openConfirm({
			template : 'modalDialogId',
			className : 'ngdialog-theme-default'
		}).then(
				function (value) {
					callback(data);
				},
				function (reason) {
					console.log('Modal promise rejected. Reason: ', reason);
				}
		);
	};
	/**
	 *  Method to call delete pipeline service when user confirms.
	 */
	$scope.deleteSelectedPipeline = function (pipelineRecord) {
		var error, dialog, pipelineID = pipelineRecord.inPipelineId;
		//Calling service to delete a pipeline
		token = localStorage.getItem('loginToken');
		pipelineService.deletePipeline(pipelineID, token).then(
				function (data) {
					if (data.success === true) {
						console.log(JSON.stringify(data.successDetails.successMessage));
						dialog = ngDialog.open({
							template: data.successDetails.successMessage,
							plain: true,
							className: 'ngdialog-theme-success'
						});

						//Calling service to get all pipelines to refresh the pipeline table
						$scope.getPipelineDetails();

					} else {
						error = data.errors;
						console.log(error.errorMessage);
						if (error.errorCode === "3006") {
							$scope.openConfirmLogin();
						}
						dialog = ngDialog.open({
							template: data.successDetails.successMessage,
							plain: true,
							className: 'ngdialog-theme-error'
						});
					}
				}
		);
	};
	/**
	 *  Method to get the max value of visualize entity id of a pipeline .
	 */
	$scope.getVisualizationMaxId = function (pipelineId) {
		var obj = {}, error;
		obj.pipelineId = pipelineId;
		token = localStorage.getItem('loginToken');
		pipelineService.getVisualizationMaxId(obj, token).then(
				function (data) {
					if (data.success === true) {
						$scope.tempVisStep = data.root;
						$scope.maxVisId = $scope.tempVisStep;
						console.log($scope.tempVisStep);
					} else {
						error = data.errors;
						console.log(error.errorMessage);
						if (error.errorCode === "3006") {
							$scope.openConfirmLogin();
						}
						$scope.tempVisStep = 0;
					}
				}
		);
	};
	/**
	 *  Method to clear values in pipeline popup screens based on the type.
	 */
	$scope.clearWFPopUp = function (idName) {
		if (idName === "source") {
			$scope.existingSources.selected = {};
            $scope.fileData = "";
            $scope.schemaFileContent = "";
            $scope.schemaUpdated = false;
            $scope.uploadSchema = true;
            $scope.schemaName = false;
            $scope.fileTypeError = false;
            document.getElementById("initialTableName").value = "";
            document.getElementById("initialColumnName").value = "";
            $scope.delimiter = "";
            $scope.headerExists = false;
            $scope.sourceTypes.selected = {};
            $scope.sourcefileTypes.selected = {};
            $scope.sourceRecordW = {};
            $scope.showSchemaGrid = false;
            $scope.sourceSchemaData = [];
            angular.element("input[type='file']").val(null);
		} else if (idName === "process") {
			//Logic to clear fields in PROCESS popup is included in workflow-popup.js
			console.log("process");
		} else if (idName === "sink") {
			$scope.sinkTypes.selected = {};
			$scope.existingSink.selected = {};
			$scope.sinkRecordW = "";
			//$scope.targetTableName = "";
			//$scope.sinkKeyspace = "";
			//$scope.sinkQuery = "";
			//$scope.selectedProcessIdentifier = {};
			//$scope.queries.selected = {};
			//$scope.selectedColumns = [];
			//$scope.showSchemaGridSink = false;
			$scope.sinkQuery = "";
			//$scope.targetTableName = "";
			//$scope.sinkKeyspace = "";

		} else if (idName === "visualize") {
			document.getElementById("entityName").value = "";
			document.getElementById("entityDesc").value = "";
			document.getElementById("titleName").value = "";
			document.getElementById("dim1TitleName").value = "";
			document.getElementById("dim2TitleName").value = "";
			document.getElementById("visSinkTypesID").value = "";
			document.getElementById("visKeyspace").value = "";
			document.getElementById("strSinkTable").value = "";
			document.getElementById("queryOrUrl").value = "";

			$scope.visCassSinkType = false;
			$scope.strSinkTable = "";
			$scope.graphTypes.selected = {};
			$scope.graphSubTypes.selected = {};
			$scope.entityName = "";
			$scope.entityDesc = "";
			$scope.titleName = "";
			$scope.visKeyspace = "";
			$scope.dim1TitleName = "";
			$scope.dim2TitleName = "";
			$scope.visSinkTypes = "";
			$scope.visSinkTypes.selected = "";
			$scope.strSinkTable = "";
			$scope.queryOrUrl = "";
			$scope.existingKPI.selected = [];
		} else if (idName === "algorithm") {
			//TODO : logic for algorithm
			console.log("algorithm");
		}
	};

	/**
	 *  Method to close pipeline popup screens based on type.
	 */
	$scope.closeWFPopUp = function (idName) {
		$scope.nodeData = "";
		$scope.pipelineName = "";
		if (idName === "source") {
			$scope.sourcePopUp.close();
		} else if (idName === "process") {
			$scope.processPopUp.close();
		} else if (idName === "sink") {
			$scope.sinkPopUp.close();
		} else if (idName === "visualize") {
			$scope.visPopUp.close();
		}
	};
	$scope.closepopup = function (pipelineRecord) {
		var stepsData, dialog, sourceExists = false, processExists = false, sinkExists = false;
		$scope.savePipelinePopup.close();
		$scope.newPipelinePopUp.close();
		$scope.getPipelineDetails();
		stepsData = pipelineRecord.nodes.nodeDetails;
		angular.forEach(stepsData, function (val, k) {
			if (angular.lowercase(val.name) === "source"){
				sourceExists = true;
			} else if (angular.lowercase(val.name) === "process") {
				processExists = true;
			} else if (angular.lowercase(val.name) === "sink") {
				sinkExists = true;
			}
		});
		document.getElementById("loader1").style.visibility = "hidden";
		if (sourceExists && processExists && sinkExists) {
			ngDialog.openConfirm({
				template : 'executeModalDialog',
				className : 'ngdialog-theme-default'
			}).then(
					function (value) {
						$scope.executeSelectedPipeline(pipelineRecord);
					},
					function (reason) {
						console.log('Modal promise rejected. Reason: ', reason);
					}
			);
		}
		else {
			dialog = ngDialog.open({
				template: msg.msgSavePipeline,
				plain: true,
				className: 'ngdialog-theme-success'
			});
		}
	};
	$scope.failurePopup = function (errorMsg, errorCode) {
		var message;
		if (errorCode) {
			message = errorCode + " : ";
		}
		message = message + errorMsg;
		ngDialog.openConfirm({
			template: message,
			plain: true,
			className: 'ngdialog-theme-error'
		});
	};
	$scope.notifyMessage = function (msg,error) {
		//var msg = "Pipeline creation flow: Source > Process > Sink > Visualize ";
		$("#stepMessageSection").text(msg);
		if (error) {
			 document.getElementById("stepMessageSection").style.border = "2px solid #ce4242";
			 document.getElementById("stepMessageSection").style.borderLeft = "8px solid #ce4242";
		} else {
			document.getElementById("stepMessageSection").style.border = "2px solid #5cb85c";
			document.getElementById("stepMessageSection").style.borderLeft = "8px solid #5cb85c";
		}
		document.getElementById("stepMessageSection").style.visibility = "visible";
	};
	/**
	 *  Method to show messages in pipeline popup screens based on type.
	 */
	$scope.showMessage = function (stepAction, stepType) {
		if (stepType === "source") {
			if (stepAction === "Save") {
				$("#stepMessageSection").text(msg.msgSaveSourceStep);
			} else if (stepAction === "Update") {
				$("#stepMessageSection").text(msg.msgUpdateSourceStep);
			}
		} else if (stepType === "process") {
			if (stepAction === "Save") {
				$("#stepMessageSection").text(msg.msgSaveProcessStep);
			} else if (stepAction === "Update") {
				$("#stepMessageSection").text(msg.msgUpdateProcessStep);
			}
		} else if (stepType === "sink") {
			if (stepAction === "Save") {
				$("#stepMessageSection").text(msg.msgSaveSinkStep);
			} else if (stepAction === "Update") {
				$("#stepMessageSection").text(msg.msgUpdateSinkStep);
			}
		} else if (stepType === "visualize") {
			if (stepAction === "Save") {
				$("#stepMessageSection").text(msg.msgSaveVisualizeStep);
			} else if (stepAction === "Update") {
				$("#stepMessageSection").text(msg.msgUpdateVisualizeStep);
			}
		}
		document.getElementById("stepMessageSection").style.border = "2px solid #5cb85c";
		document.getElementById("stepMessageSection").style.borderLeft = "8px solid #5cb85c";
		document.getElementById("stepMessageSection").style.visibility = "visible";
	};
	/**
	 *  Method to close the Pipeline IFRAME popup page.
	 */
	$scope.cancel = function (popUpInstance) {
		console.log("Close >> " + popUpInstance);
		$scope.selectedPipelineRecord = "";
		$scope.pipelineAction = "";
		popUpInstance.close();
	};
	$scope.cancelPopup = function (popUpInstance) {
		console.log("Close >> " + popUpInstance);
		popUpInstance.close();
	};
	$scope.getClassForExecute =  function (pipeline) {
		var className, status = angular.lowercase(pipeline.strPplStatus);
		if (status === "in progress" || status === "running" || status === "starting") {
			className = "disableExecuteButton";
			return (className);
		}
	};
	/**
	 *
	 * @param pipeline
	 */
	$scope.getClassForStop = function (pipeline) {
		var className, status = angular.lowercase(pipeline.strPplStatus);
		if (status !== "in progress" && status !== "running" && status !== "starting") {
			className = "disableStopButton";
			return (className);
		}
	};
	$scope.localArray = [];
	/**
	 *
	 * @param pipelineRecord
	 */
	$scope.confirmExecute = function (pipelineRecord) {
		$scope.pipelineName = pipelineRecord.strPipelineName;
		ngDialog.openConfirm({
			template : 'confirmExecuteModalDialog',
			className : 'ngdialog-theme-default',
			scope: $scope
		}).then(
				function (value) {
					$scope.executeSelectedPipeline(pipelineRecord);
				},
				function (reason) {
					console.log('Modal promise rejected. Reason: ', reason);
				}
		);
	};
	/**
	 *
	 * @param pipelineRecord
	 */
	$scope.confirmStopExecute = function (pipelineRecord) {
		$scope.pipelineName = pipelineRecord.strPipelineName;
		ngDialog.openConfirm({
			template : 'confirmStopModalDialog',
			className : 'ngdialog-theme-default'
		}).then(
				function (value) {
					$scope.stopSelectedPipeline(pipelineRecord);
				},
				function (reason) {
					console.log('Modal promise rejected. Reason: ', reason);
				}
		);
	};

	/**
	 * Method to executing a selected pipeline.
	 * @param pipelineRecord
	 */
	$scope.executeSelectedPipeline = function (pipelineRecord) {
		$scope.pipelineAction = "execute";
		var localObject, dialog, error, pipelineID = pipelineRecord.inPipelineId, pipelineExeURL = pipelineRecord.strPipelineExeURL;
		if(!pipelineExeURL || pipelineExeURL == ""){
			dialog = ngDialog.open({
				template: msg.msgExecutionFailure,
				plain: true,
				className: 'ngdialog-theme-error'
			});
			return;
		}
		//Calling service to delete a pipeline
		token = localStorage.getItem('loginToken');
		pipelineService.executePipeline(pipelineID, pipelineExeURL, token).then(
				function (data) {
					if (data.success === true) {
						$scope.localArray.push((data.root));
						localObject = $scope.localArray;
						localStorage.setItem('localObject', JSON.stringify(localObject));
						dialog = ngDialog.open({
							template: "Pipeline execution started",
							plain: true,
							className: 'ngdialog-theme-success'
						});

						//Calling service to get all pipelines to refresh the pipeline table
						$scope.getPipelineDetails();

					} else {
						error = data.errors;
						console.log(error.errorMessage);
						if (error.errorCode === "3006") {
							$scope.openConfirmLogin();
						}
						dialog = ngDialog.open({
							template: error.errorMessage,
							plain: true,
							className: 'ngdialog-theme-error'
						});
					}
				}
		);
	};
	/**
	 * Method to stop selected a pipeline.
	 * @param pipelineRecord
	 */
	$scope.stopSelectedPipeline = function (pipelineRecord) {
		$scope.pipelineAction = "stop";
		var pipelineID = pipelineRecord.inPipelineId, execId = "", retrievedObject, obj, dialog, error;
		retrievedObject = localStorage.getItem('localObject');
		obj = JSON.parse(retrievedObject);
		angular.forEach(obj, function (val, k) {
			if (val.inPipelineId === pipelineID) {
				execId = val.inExecPipelineId;
			}
		});
		//Calling service to delete a pipeline
		token = localStorage.getItem('loginToken');
		pipelineService.stopExecutePipeline(pipelineID, token).then(
				function (data) {
					if (data.success === true) {
						/*console.log(JSON.stringify(data.successDetails.successMessage));*/
						dialog = ngDialog.open({
							template: "Pipeline execution stopped",
							plain: true,
							className: 'ngdialog-theme-success'
						});
						//Calling pipeline listing service to refresh pipeline table
						$scope.getPipelineDetails();

					} else {
						error = data.errors;
						console.log(error.errorMessage);
						if (error.errorCode === "3006") {
							$scope.openConfirmLogin();
						}
						dialog = ngDialog.open({
							template: error.errorMessage,
							plain: true,
							className: 'ngdialog-theme-error'
						});
					}
				}
		);
	};
	/**
	 *  Method called after loading the workflow code in IFRAME
	 *     to pass the selected pipeline details while edit/view.
	 */
	window.loadedIframe = function () {
		console.log("$scope.selectedPipelineRecord >> " + JSON.stringify($scope.selectedPipelineRecord));
		if ($scope.selectedPipelineRecord !== "") {
			document.getElementById("workflow").contentWindow.postMessage($scope.selectedPipelineRecord, '*');
		}
		$scope.notifyMessage(msg.msgCreatePipeline);
	};
	//$scope.initialTableName = "";
	$scope.stepAddEdit = "Save";
	//var visSinkSel = "", processSinkSel = "";
	/**
	 * Method called on changing the source type in source popup.
	 *  This will clear the data in selected source configuration
	 * @param sourceType
	 */
	$scope.sourceTypeOnSelect = function (sourceType) {
		if ($scope.existingSources.selected.type) {
			if (sourceType.name.toLowerCase() !== $scope.existingSources.selected.type.toLowerCase()) {
				$scope.existingSources.selected = {};
			}
		}
	};
	/**
	 * Method called on changing the process type in process popup.
	 * @param sparkType
	 */
	$scope.processTypeOnChange = function (sparkType) {
		if (sparkType === true) {
			$scope.changedProcessType = "spark";
		} else {
			$scope.changedProcessType = "flink";
		}
		$scope.existingProcess.selected = {};
	};
	/**
	 * Method called on selecting sink type in sink popup.
	 * @param type
	 */
	$scope.selectSinkType = function (type) {
		/*var sinkTypeOnChange = ""
                sinkTypeOnChange = type.name.toLowerCase();
            var sinkQuerytemp = [];
            $scope.sinkQueries = [];
            angular.forEach($scope.queries, function (val, k) {
                if (val.querySinkDetails.strPersitTo.toLowerCase() == sinkTypeOnChange) {
                    $scope.sinkQueries.push(val);

                }
            });*/
		$scope.existingSink.selected = {};
		$scope.sinkConfigSelected = false;
	};
	/**
	 * Method called on selecting sink configuration in sink popup.
	 * @param rec
	 * @param type
	 */
	$scope.sinkConfigOnSelect = function (rec, type) {
		var sinkConfigIdOnChange = "", sinkObj = {}, sinkTypeOnChange, error, sinkQuerytemp;
		sinkConfigIdOnChange = rec.id;
		sinkObj.sinkId = sinkConfigIdOnChange;
		token = localStorage.getItem('loginToken');
		configService.getExistingSinkbyId(sinkObj, token).then(
				function (data) {
					if (data.success === true) {
						$scope.sinkRecordW = data.root;
						$scope.sinkRecordW = JSON.parse(JSON.stringify($scope.sinkRecordW));
						$scope.sinkRecordW.formatDetails = JSON.parse($scope.sinkRecordW.objSinkConfigDetails);

						sinkTypeOnChange = "";
						sinkTypeOnChange = type.name.toLowerCase();
						$scope.sinkQueries = [];
						angular.forEach($scope.queries, function (val, k) {
							if ((val.querySinkDetails.strPersitTo.toLowerCase() === sinkTypeOnChange) && (val.querySinkDetails.sinkConfigID === sinkConfigIdOnChange)) {
								$scope.sinkQueries.push(val);
							}
						});
						$scope.sinkConfigSelected = true;
					} else {
						error = data.errors;
						console.log(error.errorMessage);
						if (error.errorCode === "3006") {
							$scope.openConfirmLogin();
						}
						$scope.sinkRecordW = "";
					}
				}
		);
		sinkQuerytemp = [];
		$scope.sinkQueries = [];
		angular.forEach($scope.queries, function (val, k) {
			if (val.querySinkDetails.sinkConfigID === sinkConfigIdOnChange) {
				$scope.sinkQueries.push(val);
			}
		});
	};
	/**
	 *
	 */
	$scope.enableSchemaUpload = function () {
		$scope.uploadSchema = true;
		$scope.schemaName = false;
		document.getElementById("schemaFile").disabled = false;
	};
	/**
	 *
	 */
	$scope.addVisulization = function () {
		$scope.visDetails = true;
		$scope.viSaveBtn = "Save";
	};
	$scope.visList = [];
	$scope.visEdit = false;
	/**
	 *
	 */
	$scope.saveVisualization = function () {
		var visJSONData = {}, tempEntityObj = {}, visStep, graphSubType;
		$scope.selectedKpis = [];
		$scope.success = true ;

		if ($scope.visEdit === true) {
			visStep = parseInt($scope.tempEditVisStep);
		} else {
			//$scope.tempVisStep += 1;
			$scope.tempVisStep = parseInt($scope.tempVisStep) + 1;
			visStep = parseInt($scope.tempVisStep);
		}
		angular.forEach($scope.existingKPI.selected, function (val, k) {
			$scope.selectedKpis.push({
				"inKpiId" : val.id,
				"strKpiName" : val.name
			});
		});

		if(!$scope.selectedKpis || $scope.selectedKpis.length == 0){
        			$scope.kpiError = true;
        			return;
        		}

		tempEntityObj.strTitle = document.getElementById("titleName").value;
		tempEntityObj.strDimension1Title = document.getElementById("dim1TitleName").value;
		tempEntityObj.strDimension2Title = document.getElementById("dim2TitleName").value;
		tempEntityObj.strSinkType = $scope.visSinkSel;
		tempEntityObj.strSinkTable = document.getElementById("strSinkTable").value;
		tempEntityObj.strSinkQuery = document.getElementById("queryOrUrl").value;

		graphSubType = "";
		if ($scope.graphSubTypes.selected) {
			graphSubType = $scope.graphSubTypes.selected.name;
		}
		visJSONData = {
				"inVisualizeId" : parseInt($scope.stepId),
				"inVisualizeEntityId" : visStep,
				"strVisualizeName" : document.getElementById("entityName").value,
				"strVisualizeDesc" : document.getElementById("entityDesc").value,
				"strVisualizeParentType" : $scope.graphTypes.selected.name,
				"strVisualizeSubType" : graphSubType,
				"strKeySpace" : document.getElementById("visKeyspace").value,
				"strVisualizeConfigDetails" : tempEntityObj,
				"kpiList" : $scope.selectedKpis
		};
		console.log(JSON.stringify(visJSONData));

		//Method to validate visualization name.
		var pipelineId = 0;
		if(document.getElementById("workflow").contentWindow.window.pipelineId) {
			pipelineId = document.getElementById("workflow").contentWindow.window.pipelineId;
		};
		var tempObj = {
				"inPipelineId" : pipelineId,
				"inVisualizeEntityId" : visStep,
				"strVisualizeName" : document.getElementById("entityName").value,
				"kpiList" : $scope.selectedKpis
		}
		var token = localStorage.getItem('loginToken');
		pipelineService.validateVisualizationName(tempObj,token).then(
			function(data) {
				$scope.success = data.success;
				if (data.success == true) {
					//Duplicate name check for visulaization name with list of names in UI table.
					var existingList=$scope.visList;
		        	var visObjectArray = $filter('filter')(existingList,  function (item) {
		        		/*if(action == "edit"){
		        			return ((item.name.toLowerCase().trim() == (document.getElementById("kpiName").value).toLowerCase().trim()) && (item.id != configId));
		        		}
		        		else{*/
		        			return ((item.strVisualizeName.toLowerCase().trim() == (document.getElementById("entityName").value).toLowerCase().trim()) && (item.inVisualizeEntityId != visStep));
		        		/*}*/
		        	},true);
		        	if(visObjectArray!=undefined && visObjectArray.length>0){
		        		//kpiObj.editform.kpiName.$setValidity('unique',false);
		        		$scope.success = false;
		        		$scope.errorMessageVis = msg.msgVisualizationNameError;
						$scope.disableSave = true;
		        		return;
		        	}else{
		        		if ($scope.visEdit === true) {
							angular.forEach($scope.visList, function (value, index) {
								if ($scope.tempEditVisStep === value.inVisualizeEntityId) {
									$scope.visList[index] = visJSONData;
									$scope.visEdit = false;
								}
							});
						} else {
							$scope.visList.push(visJSONData);
							$scope.visAdded = true;
						}
						if ($scope.stepAddEdit === "Update" && $scope.viSaveBtn === "Update") {
							$scope.visDetails = false;
						}
						$scope.disableSave = false;
						$scope.viSaveBtn = "Save";
						$scope.clearVisualization();
		        	}
				} else {
					var error = data.errors;
					console.log(error.errorMessage);
					if(error.errorCode=="3006"){
						$scope.openConfirmLogin();
					}
					$scope.errorMessageVis = error.errorMessage;
					$scope.disableSave = true;
				}
			}
		);

	};
	/**
	 *
	 * @param vis
	 */
	$scope.editVisRow = function (vis) {
		//saved step
		$scope.visEdit = true;
		$scope.visAdded = true;
		$scope.visDetails = true;
		$scope.viSaveBtn = "Update";
		if (vis.inVisualizeEntityId) {
			$scope.tempEditVisStep = vis.inVisualizeEntityId;
		}

		$scope.graphTypes.selected = {};
		$scope.graphSubTypes.selected = {};
		document.getElementById("entityName").value = vis.strVisualizeName;
		document.getElementById("entityDesc").value = vis.strVisualizeDesc;

		angular.forEach($scope.graphTypes, function (val, k) {
			if (val.name === vis.strVisualizeParentType) {
				$scope.graphTypes.selected = val;

			}
		});
		angular.forEach($scope.graphSubTypes, function (val, k) {
			if (val.name === vis.strVisualizeSubType) {
				$scope.graphSubTypes.selected = val;

			}
		});
		$scope.visSinkTypes = $scope.sinkTypes;

		if (vis.strVisualizeConfigDetails.strSinkType) {
			$scope.strSinkType = vis.strVisualizeConfigDetails.strSinkType;
			angular.forEach($scope.visSinkTypes, function (val, k) {
				if ($scope.strSinkType === val.name) {
					$scope.visSinkTypes.selected = val;
				}
			});
			if ($scope.strSinkType === "Elassandra") {
				$scope.visCassSinkType = true;
				document.getElementById("visKeyspace").value = vis.strKeySpace;
			}
		}
		document.getElementById("strSinkTable").value = vis.strVisualizeConfigDetails.strSinkTable;
		document.getElementById("queryOrUrl").value = vis.strVisualizeConfigDetails.strSinkQuery;

		if (vis.strVisualizeParentType === "D3JS") {
			document.getElementById("titleName").value = vis.strVisualizeConfigDetails.strTitle;
			document.getElementById("dim1TitleName").value = vis.strVisualizeConfigDetails.strDimension1Title;
			document.getElementById("dim2TitleName").value = vis.strVisualizeConfigDetails.strDimension2Title;
		}
		$scope.getSinkDetails().then(
				function (data) {
					$scope.existingSink.selected = {};
				}
		);

		$scope.getKPIDetails().then(
				function (data) {
					var kpiListArray = vis.kpiList;
					$scope.existingKPI.selected = [];
					angular.forEach($scope.existingKPI, function (val, k) {

						angular.forEach(kpiListArray, function (val2, k) {

							if (val.id === val2.inKpiId) {
								$scope.existingKPI.selected.push(val);

							}
						});
					});
				}
		);
	};
	/**
	 *
	 * @param vis
	 */
	$scope.openVisBox = function (vis) {
		$scope.visData = vis;
		$scope.visDataPopup = $uibModal.open({
			templateUrl : 'workflow-vis-modal.html',
			backdrop  : 'static',
			keyboard  : false,
			scope : $scope,
			size : 'small'
		});
	};
	/**
	 *
	 * @param query
	 */
	$scope.openQueryBox = function (query) {
		$scope.queryInProcess = query;
		$scope.queryPopup = $uibModal.open({
			templateUrl : 'workflow-query-modal.html',
			backdrop  : 'static',
			keyboard  : false,
			scope : $scope,
			size : 'small'
		});
	};
	/**
	 *
	 * @param index
	 */
	$scope.removeVisRow = function (index) {
		var deletedId = $scope.visList[index].inVisualizeEntityId;
		$scope.deletedVisList.push({
			"inVisualizeEntityId" : deletedId
		});
		$scope.visList.splice(index, 1);
		if ($scope.visList.length === 0) {
			$scope.visAdded = false;
			$scope.visDetails = true;
			$scope.viSaveBtn = "Save";
			if ($scope.stepAddEdit === "Update") {
				$scope.disableSave = false;
			} else {
				$scope.disableSave = true;
			}
		}
	};
	/**
	 *
	 */
	$scope.clearVisualization = function () {
		document.getElementById("entityName").value = "";
		document.getElementById("entityDesc").value = "";
		document.getElementById("titleName").value = "";
		document.getElementById("dim1TitleName").value = "";
		document.getElementById("dim2TitleName").value = "";
		document.getElementById("visSinkTypesID").value = "";
		document.getElementById("visKeyspace").value = "";
		document.getElementById("strSinkTable").value = "";
		document.getElementById("queryOrUrl").value = "";
		$scope.visCassSinkType = false;
		$scope.strSinkTable = "";
		$scope.graphTypes.selected = {};
		$scope.graphSubTypes.selected = {};
		$scope.entityName = "";
		$scope.entityDesc = "";
		$scope.titleName = "";
		$scope.visKeyspace = "";
		$scope.dim1TitleName = "";
		$scope.dim2TitleName = "";
		$scope.visSinkTypes.selected = "";
		$scope.strSinkTable = "";
		$scope.queryOrUrl = "";
		$scope.existingKPI.selected = [];
	};
	/**
	 *
	 */
	$scope.cancelVisualization = function () {
		$scope.clearVisualization();
		$scope.visEdit = false;
		if ($scope.stepAddEdit === "Update") {
			$scope.visDetails = false;
		} else {
			if ($scope.visList.length !== 0) {
				$scope.visDetails = false;
			}
		}
	};
	/**
	 *  Method called on click of source, process, sink, visualize link in IFRAME
	 *     to open the respective popup screens.
	 *  Method is invoked from showPopUp() function in workflow-custom.js in WORKFLOW iframe.
	 * @param idType - to identify source/process/sink/visualize popup.
	 * @param stepAction - save / update action
	 * @param pipelineId - to get the id of existing pipeline.
	 * @param stepId - get the step id of the popup
	 * @param record - Details of saved popup if present. Else it is identified as a new popup.
	 */
	//$scope.openWFPopup = function (idType, stepAction, pipelineId, stepId, record) {
	$scope.openWFPopup = function (idType, stepAction, pipelineId, stepId, node, model, record) {
		console.log("openWFPopup Function >>>" + idType + ">>" + stepAction + ">>" + stepId);
		var sourceId, sourceType, sourcefileType, processId, savedWindowName, sinkId, strSinkType, sinkQuerytemp, localVisList, localVisMaxId, retrievedVisList, retrievedVisMaxId, temp;
		$scope.stepAddEdit = stepAction;
		$scope.stepId = stepId;
		console.log(JSON.stringify(node));
		$scope.nodeData = node ;
		$scope.modelData = model;



		if (idType.toLowerCase() === "source") {
			//$scope.getSourceDetails();
			if (record) {
				//Saved step
				$scope.stepAddEdit = "Update";
				$scope.schemaUpdated = false;
				$scope.uploadSchema = false;
				$scope.fileTypeError = false;
				$scope.schemaName = true;
				sourceId = record.source_id;
				$scope.getSourceDetails().then(
						function (data) {
							angular.forEach($scope.existingSources, function (val, k) {
								if (val.id === sourceId) {
									$scope.existingSources.selected = val;
								}
							});
						}
				);
				sourceType = record.source_type;
				angular.forEach($scope.sourceTypes, function (val, k) {
					if (val.name === sourceType) {
						$scope.sourceTypes.selected = val;
					}
				});

				sourcefileType = record.source_schema_type;
				angular.forEach($scope.sourcefileTypes, function (val, k) {
					if (val.name === sourcefileType) {
						$scope.sourcefileTypes.selected = val;
					}
				});

				$scope.sourceRecordW = {};
				//$scope.sourceRecordW.strSourceType = record.source_type;
				$scope.sourceRecordW.formatDetails = record.source_config_details;
				$scope.sourceSchemaData = record.source_schema;
				$scope.showSchemaGrid = true;
				$scope.initialTableName = record.initialTableName;
				$scope.initialColumnName = record.initialColumnName;
				$scope.tempInitialColumnName = record.initialColumnName;
				$scope.delimiter = record.delimiter;
				$scope.tempDelimiter = record.delimiter;
				$scope.headerExists = record.headerExists;
				$scope.schemaFileName = record.schema_file_name;
				$scope.schemaFileContent = record.schema_file_Content;
			} else {
				//new step
				$scope.fileData = "";
				$scope.schemaFileContent = "";
				$scope.schemaUpdated = false;
				$scope.uploadSchema = true;
				$scope.schemaName = false;
				$scope.fileTypeError = false;
				$scope.getSourceDetails().then(
						function (data) {
							$scope.existingSources.selected = {};
						}
				);
				$scope.initialTableName = "";
				$scope.initialColumnName = "";
				$scope.delimiter = "";
				$scope.headerExists = false;
				$scope.sourceTypes.selected = {};
				$scope.sourcefileTypes.selected = {};
				$scope.sourceRecordW = {};
				$scope.showSchemaGrid = false;
				$scope.sourceSchemaData = [];
				angular.element("input[type='file']").val(null);
			}
			$scope.sourcePopUp = $uibModal.open({
				templateUrl : 'app/views/workflow-source-popup.html',
				backdrop  : 'static',
				keyboard  : false,
				scope : $scope
			});
		} else if (idType.toLowerCase() === "process") {
			$scope.getSinkDetails();
			if (record) {
				//saved step
				processId = record.process_id;
				$scope.getProcessDetails().then(
						function (data) {

							angular.forEach($scope.existingProcess, function (val, k) {
								if (val.id === processId) {
									$scope.existingProcess.selected = val;
								}
							});
						}
				);
				$scope.stepAddEdit = "Update";
				$scope.predecessors = [0];
				if (record.process_type.toLowerCase() === "spark") {
					$scope.sparkProcessType = true;
				} else {
					$scope.sparkProcessType = false;
				}
				if ($scope.sourceUpdatedProcess === true) {
					$scope.getNewStep('process');
				} else {
					$scope.processRecordW = {};
					$scope.processRecordW.formatDetails = record.process_details;
					$scope.processRecordW.strProcessType = record.process_type;
					$scope.queries = record.process_transform_queries;
					$scope.schemaData = record.process_source_schema;
					$scope.queryAdded = true;
					$scope.showQuerySection = false;
					$scope.windowNames = record.process_window_names;
					$scope.windowOptions = record.process_window_options;

					//to the set the queries in process page
					pipelineService.setQueryPopUpValues($scope.queries);
					pipelineService.setWindowNamePopUpValues($scope.windowNames);
					pipelineService.setWindowOptionPopUpValues($scope.windowOptions);
					savedWindowName = record.process_transform_queries.windowName;
					angular.forEach($scope.windowOptions, function (val, k) {
						if (val.name === savedWindowName) {
							$scope.windowOptions.selected = val;
						}
					});
					$scope.selectWindowName = $scope.windowOptions.selected;

					if ($scope.queries.length === 0) {
						$scope.disableSave = true;
					} else {
						$scope.disableSave = false;
					}
					angular.forEach($scope.queries, function (val, k) {
						$scope.predecessors.push(val.id);
					});

					if ($scope.sourcefileTypes.selected) {
						if ($scope.sourcefileTypes.selected.name === "Unstructured") {
							$scope.disableQuery = true;
						} else {
							$scope.disableQuery = false;
						}
					} else {
						$scope.disableQuery = false;
					}
				}
			} else {
				//new step
				$scope.getNewStep('process');
			}
			$scope.processPopUp = $uibModal.open({
				templateUrl : 'app/views/workflow-process-popup.html',
				backdrop  : 'static',
				keyboard  : false,
				scope : $scope
			});
		} else if (idType.toLowerCase() === "sink") {
			if (record) {
				//saved step
				$scope.stepAddEdit = "Update";
				if ($scope.sourceUpdatedSink === true) {
					$scope.getNewStep('sink');
				} else {
					$scope.getSinkDetails().then(
							function (data) {
								sinkId = record.sink_id;
								angular.forEach($scope.existingSink, function (val, k) {
									if (val.id === sinkId) {
										$scope.existingSink.selected = val;
									}
								});
							}
					);
					strSinkType = record.sink_type;
					angular.forEach($scope.sinkTypes, function (val, k) {
						if (strSinkType.toLowerCase() === val.name.toLowerCase()) {
							$scope.sinkTypes.selected = $scope.sinkTypes[k];
						}
					});
					$scope.sinkRecordW = {};
					//$scope.selectedProcessIdentifier = {};
					$scope.sinkRecordW.formatDetails = record.sink_details;
					$scope.sinkRecordW.strSinkType = record.sink_type;

					sinkQuerytemp = [];
					$scope.sinkQueries = [];
					if (!$scope.queries) {
						sinkQuerytemp = record.sink_queries;
					} else {
						sinkQuerytemp = $scope.queries;
					}
					$scope.queries = sinkQuerytemp;
					angular.forEach($scope.queries, function (val, k) {
						if (val.querySinkDetails.strPersitTo) {
							if (val.querySinkDetails.strPersitTo === record.sink_type) {
								$scope.sinkQueries.push(val);
							}
						}
						//if (val.querySinkDetails.strPersitTo === record.sink_type) {
						//	$scope.selectedQuery = val
						//}
					});
					$scope.sinkConfigSelected = true;
					/*angular.forEach($scope.queries, function(val, k) {
	                        if (val.id === record.sink_selected_identifier) {
	                            $scope.selectedQuery = val
	                        }
	                    });
	                    if (!$scope.selectedQuery) {
	                        $scope.queries.selected = $scope.queries[0];
	                    } else {
	                        $scope.queries.selected = $scope.selectedQuery
	                    }

	                    $scope.sinkQuery = $scope.queries.selected.query;
	                    $scope.targetTableName = $scope.queries.selected.table;
	                    if($scope.queries.selected.querySinkDetails) {
	                        $scope.sinkKeyspace = $scope.queries.selected.querySinkDetails.persitConfig.strKeySpace;
	                    }


	                    $scope.sinkSchemaData = record.sink_selected_schema;
	                    $scope.showSchemaGridSink = true;
					 */
				}
			} else {
				$scope.getSinkDetails().then(
						function (data) {
							$scope.existingSink.selected = {};
						}
				);
				$scope.getNewStep('sink');
			}
			$scope.sinkPopUp = $uibModal.open({
				templateUrl : 'app/views/workflow-sink-popup.html',
				backdrop  : 'static',
				keyboard  : false,
				scope : $scope
			});
		} else if (idType.toLowerCase() === "visualize") {
			//$scope.getSinkDetails();
			//$scope.getKPIDetails();
			if (record && record.length !== 0) {
				$scope.stepAddEdit = "Update";
				if ($scope.sourceUpdatedVisualize === true) {
					$scope.getNewStep('visualize');
				} else {
					//saved step
					localVisList = record;
					localStorage.setItem('localVisList', JSON.stringify(localVisList));
					localVisMaxId = $scope.maxVisId;
					localStorage.setItem('localVisMaxId', JSON.stringify(localVisMaxId));
					$scope.getKPIDetails().then(
							function (data) {
								$scope.existingKPI.selected = {};
							}
					);
					$scope.visAdded = true;
					$scope.visDetails = false;
					retrievedVisList = localStorage.getItem('localVisList');
					$scope.visList = JSON.parse(retrievedVisList);
					retrievedVisMaxId = localStorage.getItem('localVisMaxId');
					$scope.tempVisStep = retrievedVisMaxId;
					if ($scope.visList.length === 0 || !$scope.visList) {
						$scope.disableSave = true;
					} else {
						$scope.disableSave = false;
					}
					$scope.visSinkTypes = $scope.sinkTypes;
					temp = "";
					angular.forEach($scope.visList, function (val, k) {
						temp = val.inVisualizeEntityId;
					});
					//$scope.tempVisStep = Math.max.apply(Math,$scope.visList.map(function(item) {return item.inVisualizeEntityId;}));
					//$scope.tempVisStep++;
					/*if(record.inVisualizeEntityId) {
	                        $scope.tempVisStep = record.inVisualizeEntityId;
	                    }

	                    $scope.graphTypes.selected = {};
	                    $scope.graphSubTypes.selected = {};
	                    $scope.entityName = record.strVisualizeName;
	                    $scope.entityDesc = record.strVisualizeDesc;


	                    angular.forEach($scope.graphTypes, function(val, k) {
	                        if (val.name == record.strVisualizeParentType) {
	                            $scope.graphTypes.selected = val;
	                            $scope.$apply();
	                        }
	                    });
	                    angular.forEach($scope.graphSubTypes, function(val, k) {
	                        if (val.name == record.strVisualizeSubType) {
	                            $scope.graphSubTypes.selected = val;
	                            $scope.$apply();
	                        }
	                    });

	                    $scope.visSinkTypes = $scope.sinkTypes;
	                    $scope.$apply();
	                    if(record.strVisualizeConfigDetails.strSinkType) {
	                        $scope.strSinkType = record.strVisualizeConfigDetails.strSinkType;
	                        angular.forEach($scope.sinkTypes,function(val, k) {
	                            if($scope.strSinkType.toLowerCase() == val.name.toLowerCase()) {
	                                $scope.visSinkTypes.selected = $scope.sinkTypes[k];
	                            }
	                        });
	                        if($scope.strSinkType.toLowerCase() =="elassandra") {
	                            $scope.visCassSinkType = true;
	                            $scope.visKeyspace = record.strKeySpace;
	                            }
	                    }

	                    $scope.strSinkTable = record.strVisualizeConfigDetails.strSinkTable;
	                    $scope.queryOrUrl = record.strVisualizeConfigDetails.strSinkQuery;

	                    if(record.strVisualizeParentType.toLowerCase() == "d3js") {
	                        $scope.titleName = record.strVisualizeConfigDetails.strTitle;
	                        $scope.dim1TitleName = record.strVisualizeConfigDetails.strDimension1Title;
	                        $scope.dim2TitleName = record.strVisualizeConfigDetails.strDimension2Title;
	                    }
	                    $scope.getSinkDetails().then(
	                            function(data) {
	                                $scope.existingSink.selected = {};
	                            });

	                    $scope.getKPIDetails().then(
	                            function(data) {
	                                var kpiListArray = record.kpiList;
	                                $scope.existingKPI.selected = [];
	                                angular.forEach($scope.existingKPI, function(val, k) {

	                                    angular.forEach(kpiListArray, function(val2, k) {

	                                        if (val.id == val2.inKpiId) {
	                                            $scope.existingKPI.selected.push(val);
	                                            $scope.$apply();
	                                        }
	                                    });
	                                });
	                            });*/
				}

			} else {
				//new step
				//while editing a pipeline and deleting all the vis entities and opening the popup
				if (record) {
					if (record.length === 0) {
						$scope.viSaveBtn = "Save";
					}
				} else {
					$scope.viSaveBtn = $scope.stepAddEdit;
				}
				$scope.getNewStep('visualize');
			}
			$scope.visPopUp = $uibModal.open({
				templateUrl : 'app/views/workflow-visualize-popup.html',
				backdrop  : 'static',
				keyboard  : false,
				scope : $scope
			});
		}
	};
	/**
	 * Function called for opening new step in pipeline
	 * @param stepName
	 */
	$scope.getNewStep = function(stepName) {
		var localVisMaxId, retrievedVisMaxId ;
		if (stepName === "process"){
			$scope.getProcessDetails().then(
					function (data) {
						$scope.existingProcess.selected = {};
					}
			);
			$scope.windowNames = ["NoWindow", "New Window"];
			$scope.windowOptions = [{"name" : "NoWindow", "windowPeriod" : "", "slidingInterval" : ""},
				{"name" : "New Window", "windowPeriod" : "", "slidingInterval" : ""}];
			$scope.processRecordW = {};
			$scope.selectWindowName = "";
			$scope.windowName = "";
			$scope.windowPeriod = "";
			$scope.slidingInterval = "";
			$scope.predecessors = [0];
			//$scope.predecessor = $scope.predecessors[0];
			$scope.businessRule = "";
			if ($scope.sourcefileTypes.selected) {
				if ($scope.sourcefileTypes.selected.name === "Unstructured") {
					$scope.disableQuery = true;
					$scope.query = "select * from " + $scope.initialTableName;
					$scope.primaryKey = "Id";
					$scope.indexKey = "IndexId";
				} else {
					$scope.disableQuery = false;
					$scope.query = "";
					$scope.primaryKey = "";
					$scope.indexKey = "";
				}
			} else {
				$scope.disableQuery = false;
				$scope.query = "";
				$scope.primaryKey = "";
				$scope.indexKey = "";
			}
			$scope.targetTable = "";
			$scope.persistEnabled = false;
			$scope.strPersitTo = undefined;
			$scope.processKeyspace = "";
			$scope.HDFSfile = undefined;
			$scope.selectedSink = undefined;
			$scope.queries = [];
			$scope.queryAdded = false;
			$scope.showQuerySection = true;
			$scope.sparkProcessType = true;
			$scope.schemaInProcess = [];
		} else if (stepName === "sink") {
			$scope.sinkTypes.selected = {};

			$scope.sinkRecordW = "";
			$scope.sinkConfigSelected = false;
		} else if (stepName === "visualize") {
			$scope.visList = [];
			$scope.visAdded = false;
			$scope.visDetails = true;
			$scope.disableSave = true;
			localVisMaxId = 0;
			localStorage.setItem('localVisMaxId', JSON.stringify(localVisMaxId));
			retrievedVisMaxId = localStorage.getItem('localVisMaxId');
			$scope.tempVisStep = retrievedVisMaxId;
			$scope.visCassSinkType = false;
			$scope.strSinkTable = "";
			$scope.graphTypes.selected = {};
			$scope.graphSubTypes.selected = {};
			$scope.entityName = "";
			$scope.entityDesc = "";
			$scope.titleName = "";
			$scope.visKeyspace = "";
			$scope.dim1TitleName = "";
			$scope.dim2TitleName = "";
			$scope.visSinkTypes = $scope.sinkTypes;
			$scope.visSinkTypes.selected = "";
			$scope.strSinkTable = "";
			$scope.queryOrUrl = "";
			$scope.getKPIDetails().then(
					function (data) {
						$scope.existingKPI.selected = {};
					}
			);
		}
		$scope.visEdit = false;

	}
	/**
	 *
	 * @param popUpInstance
	 */
	$scope.cancelWFPopup = function (popUpInstance) {
		console.log("Close >> " + popUpInstance);
		$scope.nodeData = "";
		$scope.pipelineName = "";
		popUpInstance.close();
	};
	/**
	 * Function called on selecting a source type in Source Popup in Workflow
	 * @param rec
	 */
	$scope.typeOnSelection = function (rec) {
		console.log(JSON.stringify(rec));
		var sourceObj = {};
		sourceObj.sourceId = rec.id;
		token = localStorage.getItem('loginToken');
		configService.getExistingSourcebyId(sourceObj, token).then(
				function (data) {
					if (data.success === true) {
						$scope.sourceRecordW = data.root;
						$scope.sourceRecordW = JSON.parse(JSON.stringify($scope.sourceRecordW));
						$scope.sourceRecordW.formatDetails = JSON.parse($scope.sourceRecordW.objSourceConfigDetails);

					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if (error.errorCode === "3006") {
							$scope.openConfirmLogin();
						}
						$scope.sourceRecord = "";
					}
				}
		);
	};
	$scope.gridOptions = {
			// data : $scope.updatedColumns,
			selectedItems: $scope.mySelections,
			enableColumnMenus: false,
			enableSorting: false,
			enableSelectAll : true,
			enableFiltering: true,
			enableRowSelection: true,
			selectionRowHeaderWidth: 40,
			columnDefs : [{
				name : 'Column Name',
				field : 'name',
				width : '50%'
			}, {
				name : 'Datatype',
				field : 'type',
				width : '50%'
			} ]
	};
	$scope.gridOptions.multiSelect = true;
	$scope.gridOptions.data = 'sourceSchemaData';
	/**
	 *
	 * @param selectedFileType
	 */
	$scope.select = function (selectedFileType) {
		$scope.fileTypeError = false;
		if (selectedFileType.name) {
			document.getElementById("schemaFile").disabled = false;
		}
		$scope.enableSchemaUpload();
		$scope.showSchemaGrid = false;
		$scope.delimiter = "";
		$scope.headerExists = false;
		$scope.initialColumnName = "";
		angular.element("input[type='file']").val(null);
	};
	/**
	 *
	 */
	$scope.uploadSchemaFile = function () {
		var schemaFile, selectedFileType, fileType, delimiter, headerExists, schemaUploadUrl;
		schemaFile = event.target.files[0];
		$scope.schemaFileName = schemaFile.name;
		selectedFileType = $scope.schemaFileName.substr(($scope.schemaFileName.indexOf('.') + 1));
		fileType = $scope.sourcefileTypes.selected.name;
		delimiter = document.getElementById("delimiter").value;
		$scope.fileTypeError = false;
		if (((fileType.toLowerCase() === "delimited") && (selectedFileType.toLowerCase() === "psv" || selectedFileType.toLowerCase() === "tsv" || selectedFileType.toLowerCase() === "csv" || selectedFileType.toLowerCase() === "txt"))
				|| (fileType.toLowerCase() === "json" && selectedFileType.toLowerCase() === "json")) {
			headerExists = document.getElementById("headerExists").checked;
			schemaUploadUrl = serviceURL.getMetaData;
			$scope.sourceSchemaData = [];
			$scope.sinkSchemaData = [];
			$scope.showSchemaGrid = false;
			$scope.showSchemaGridSink = false;
			token = localStorage.getItem('loginToken');
			fileUpload.uploadSchemaFileToUrl(schemaFile, fileType, delimiter, headerExists, schemaUploadUrl, token).then(
					function (data) {
						if (data.success === true) {
							$scope.sourceSchemaData = data.root;
							$scope.showSchemaGrid = true;
							$scope.showSchemaGridSink = true;
							if ($scope.stepAddEdit === "Update") {
								$scope.schemaUpdated = true;
							}
						} else {
							var error = data.errors;
							console.log(error.errorMessage);
							if (error.errorCode === "3006") {
								$scope.openConfirmLogin();
							}
						}
					}
			);
			$scope.sinkSchemaData = $scope.sourceSchemaData;
			angular.forEach($rootScope.sinkSchemaData, function (val, k) {
				if (val.type === "string") {
					val.type = "text";
				}
			});
			$scope.$apply();
		} else {
			$scope.fileTypeError = true;
			angular.element("input[type='file']").val(null);
		}
	};
	/**
	 *
	 * @param rec
	 */
	$scope.sinkInProcessOnSelect = function (rec) {
		var sinkObj = {};
		sinkObj.sinkId = rec.id;
		processSinkSel = rec.name;
		//sinkObj.sinkId = $scope.selectedSink.id;
		token = localStorage.getItem('loginToken');
		configService.getExistingSinkbyId(sinkObj, token).then(
				function (data) {
					if (data.success === true) {
						$scope.sinkRecordInProcess = data.root;
						$scope.sinkRecordInProcess = JSON.parse(JSON.stringify($scope.sinkRecordInProcess));
						$scope.sinkRecordInProcess.formatDetails = JSON.parse($scope.sinkRecordInProcess.objSinkConfigDetails);

					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if (error.errorCode === "3006") {
							$scope.openConfirmLogin();
						}
						$scope.sinkRecordInProcess = "";
					}
				}
		);
	};
	/**
	 *
	 * @param rec
	 */
	$scope.processOnSelect = function (rec) {
		var processObj = {};
		processObj.processId = rec.id;
		token = localStorage.getItem('loginToken');
		configService.getExistingProcessbyId(processObj, token).then(
				function (data) {
					if (data.success === true) {
						$scope.processRecordW = data.root;
						$scope.processRecordW = JSON.parse(JSON.stringify($scope.processRecordW));
						$scope.processRecordW.formatDetails = JSON.parse($scope.processRecordW.objConfigDetails);
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if (error.errorCode === "3006") {
							$scope.openConfirmLogin();
						}
						$scope.processRecordW = "";
					}
				}
		);
	};
	/**
	 *
	 */
	$scope.windowNameChange = function () {
		var index, name;
		index = document.getElementById("selectWindowName").selectedIndex  - 1;
		name = $scope.windowOptions[index].name;
		if (name === "New Window") {
			document.getElementById("windowName").value = "";
			document.getElementById("windowPeriod").value = "";
			document.getElementById("slidingInterval").value = "";
		}
		angular.forEach($scope.windowOptions, function (val, k) {
			if (val.name === name) {
				document.getElementById("windowPeriod").value = val.windowPeriod;
				document.getElementById("slidingInterval").value = val.slidingInterval;
			}
		});
	};
	$scope.disableSave = true;
	/**
	 *
	 * @param selectedItem
	 */
	$scope.processIdOnSelection = function (selectedItem) {

		$scope.selectedProcessIdentifier = selectedItem;
		$scope.sinkSchemaData = selectedItem.columns;
		angular.forEach($scope.sinkSchemaData, function (val, k) {
			if (val.type === "string") {
				val.type = "text";
			}
		});
		console.log($scope.sinkSchemaData);
		$scope.targetTableName = selectedItem.table;
		$scope.sinkKeyspace = selectedItem.querySinkDetails.persitConfig.strKeySpace;
		$scope.sinkQuery = selectedItem.query;
	};
	//Logic to handle ui-grid for column details in database source popup.
	$templateCache.put('ui-grid/selectionRowHeaderButtons',
			"<div   ><input style=\"margin: 0; vertical-align: middle\" type=\"checkbox\" ng-model=\"row.entity.checkedstats\"  ng-true-value=\"\'Y\'\" ng-false-value=\"\'N\'\" ng-click=\"row.isSelected=!row.isSelected;selectButtonClick(row, $event);grid.appScope.updateRow(row.entity.checkedstats,grid.appScope,grid.selection.selectAll);\">&nbsp;</div>"
	);
	$templateCache.put('ui-grid/selectionSelectAllButtons',
			"<div  ng-if=\"grid.options.enableSelectAll\"><input style=\"margin: 0; vertical-align: middle\" type=\"checkbox\" ng-model=\"grid.selection.selectAll\" ng-click=\"grid.selection.selectAll=!grid.selection.selectAll;headerButtonClick($event);grid.appScope.updateAllRows($event,grid.selection.selectAll,grid.appScope);\"></div>"
	);
	$scope.gridOptionsSink = {
			enableRowSelection: true,
			enableColumnMenus : false,
			columnDefs : [{
				name : 'Column',
				field : 'name',
				width : '50%'
			}, {
				name : 'Datatype',
				field : 'type',
				width : '50%'
			} ]
	};
	$scope.gridOptionsSink.multiSelect = true;
	//$scope.sinkSchemaData = $scope.queries.selected.name
	$scope.gridOptionsSink.data = 'sinkSchemaData';
	//$scope.gridOptionsSink.data = 'test';
	/**
	 *
	 * @param selectedItem
	 */
	$scope.selectSinkInVisualize = function (selectedItem) {
		if (selectedItem.name.toLowerCase() === "elassandra") {
			$scope.visCassSinkType = true;
		}
		$scope.visSinkSel = selectedItem.name;
		$scope.visSinkTypes = $scope.sinkTypes;
	};
	/**
	 *
	 * @param allText
	 * @param delimiter
	 */
	$scope.processData = function (allText, delimiter) {
		// split content based on new line
		var allTextLines, headers, lines, data;
		allTextLines = allText.split(/\r\n|\n\|/);
		headers = allTextLines[0].split(',');
		lines = [];

		angular.forEach(allTextLines, function (val) {
			if (delimiter === ",") {
				/*jslint regexp: true*/
				data = val.match(/(".*?"|[^",\s]+)(?=\s*,|\s*$)/g);
				/*jslint regexp: false*/
			} else {
				data = val.split(",");
			}
			if (data) {
				if (data.length <= headers.length) {
					var tarr = [];
					angular.forEach(headers, function (val1, index) {
						tarr.push(data[index]);
					});
					lines.push(tarr);
				}
			}
		});
		$scope.data = lines;
	};
	/**
	 *
	 * @param fileContent
	 */
	$scope.schemaPreview = function (fileContent) {
		if (fileContent) {
			$scope.fileData = fileContent;
		} else {
			$scope.fileData = $scope.schemaFileContent;
		}
		var delimiter = document.getElementById("delimiter").value;
		$scope.fileExtn = $scope.sourcefileTypes.selected.name;
		if ($scope.fileExtn === "Delimited") {
			$scope.processData($scope.fileData, delimiter);
		}
		$scope.schemaPreviewPopup = $uibModal.open({
			templateUrl : 'schema-preview-modal.html',
			backdrop  : 'static',
			keyboard  : false,
			scope : $scope,
			size : 'small'
		});
	};
	/**
	 *
	 * @param query
	 */
	$scope.openQueryBoxSink = function (query) {
		$scope.queryInSink = query;
		$scope.queryPopup = $uibModal.open({
			templateUrl : 'workflow-query-modal.html',
			backdrop  : 'static',
			keyboard  : false,
			scope : $scope,
			size : 'small'
		});
	};
	/**
	 *
	 */
	window.loadedSqlInSinkIframe = function () {
		if ($scope.queryInSink !== "") {
			document.getElementById("sinkQueryIframe").contentWindow.postMessage($scope.queryInSink, '*');
		}
	};
	/**
	 *
	 * @param columns
	 */
	$scope.openSchemaBoxSink = function (columns) {
		$scope.schemaInSink = [];
		angular.forEach(columns, function (val) {
			if (val.selected === true) {
				$scope.schemaInSink.push(val);
			}
		});
		console.log(JSON.stringify($scope.schemaInSink));
		$scope.queryPopup = $uibModal.open({
			templateUrl : 'workflow-schema-sink-modal.html',
			backdrop  : 'static',
			keyboard  : false,
			scope : $scope,
			size : 'small'
		});
	};
	/**
	 * Function called on click of save button in popup screens - Source/Sink/Process pop-ups.
	 * @param idName
	 * @param sourceFileContent
	 */
	$scope.savePopUpPipeline = function (idName, sourceFileContent) {
		console.log("$scope.savePopUp function");
		$scope.flowchart = {};
		var sourceJSONData, tempColumnObj, processJSONData, visJSONData, tempQueryObj, querySinkDetails, persitConfig, tempCol, strPersitTo, sinkConfigID, tempObj2, sinkJSONData, tempColObj, stepData;
		if (idName === "source") {
			//sourceStepId = $('#stepId').val();
			//console.log(sourceStepId);
			sourceJSONData = {};
			console.log($scope.initialTableName);
			console.log(document.getElementById("initialTableName").value);
			console.log($scope.sourceSchemaData);

			if (sourceFileContent) {
				$scope.schemaFileContent = sourceFileContent;
			}
			$scope.initialTableName = document.getElementById("initialTableName").value;
			$scope.initialColumnName = document.getElementById("initialColumnName").value;
			$scope.delimiter = document.getElementById("delimiter").value;
			$scope.headerExists = document.getElementById("headerExists").checked;
			//Getting schema details
			$scope.schemaData = [];
			angular.forEach($scope.sourceSchemaData, function (val, k) {
				tempColumnObj = {
						"columnName": val.columnName,
						"columnDataType": val.columnDataType
				};
				$scope.schemaData.push(tempColumnObj);
			});
			if ($scope.sourcefileTypes.selected.name === "Unstructured") {
				$scope.schemaData = [{"columnName" : $scope.initialColumnName, "columnDataType" : ""}];
			}
			sourceJSONData = {
					"source_id": $scope.existingSources.selected.id,
					"source_name": $scope.existingSources.selected.name,
					"source_type": $scope.sourceTypes.selected.name,
					"schema_file_type": $scope.sourcefileTypes.selected.name,
					"initialTableName": $scope.initialTableName,
					"initialColumnName": $scope.initialColumnName,
					"delimiter": $scope.delimiter,
					"headerExists": $scope.headerExists,
					"source_config_details": $scope.sourceRecordW.formatDetails,
					"source_schema_type": $scope.sourcefileTypes.selected.name,
					"source_delimitter": $scope.delimiter,
					"source_schema": JSON.parse(angular.toJson($scope.schemaData)),
					"schema_file_name": $scope.schemaFileName,
					"schema_file_Content": $scope.schemaFileContent
			};
			console.log(JSON.stringify(sourceJSONData));
			$scope.stepsExists = false;
			if($scope.pipelineModel){
				stepData = $scope.pipelineModel.nodeDetails ;
				angular.forEach(stepData, function (val, k) {
					if (val.name == ("Process") || ("Sink") || ("Visualize")) {
						$scope.stepsExists = true;
					}
				});
			}

			if ($scope.stepAddEdit == "Update" && $scope.stepsExists) {
				if ($scope.sourcefileTypes.selected.name === "Unstructured") {
					if ($scope.tempInitialColumnName !== $scope.initialColumnName) {
						$scope.sourceUpdatedProcess = true;
						$scope.sourceUpdatedSink = true;
						$scope.sourceUpdatedVisualize = true;
						$scope.openSourceUpdateConfirm(idName, sourceJSONData, $scope.saveSource);
					} else {
						$scope.saveSource(idName, sourceJSONData);
					}
				} else if ($scope.sourcefileTypes.selected.name === "Delimited") {
					if ($scope.schemaUpdated || $scope.tempDelimiter !== $scope.delimiter) {
						$scope.sourceUpdatedProcess = true;
						$scope.sourceUpdatedSink = true;
						$scope.sourceUpdatedVisualize = true;
						$scope.openSourceUpdateConfirm(idName, sourceJSONData, $scope.saveSource);
					}
					else {
						$scope.saveSource(idName, sourceJSONData);
					}
				} else if ($scope.sourcefileTypes.selected.name === "JSON") {
					if ($scope.schemaUpdated) {
						$scope.sourceUpdatedProcess = true;
						$scope.sourceUpdatedSink = true;
						$scope.sourceUpdatedVisualize = true;
						$scope.openSourceUpdateConfirm(idName, sourceJSONData, $scope.saveSource);
					} else {
						$scope.saveSource(idName, sourceJSONData);
					}
				}
			} else {
				$scope.saveSource(idName, sourceJSONData);
			}

		}
		if (idName === "process") {
			var miniBatch = parseInt(document.getElementById("miniBatch").value);
			if(!$scope.existingProcess.selected || !miniBatch || !angular.isNumber(miniBatch)){
				return;
			}
			//processStepId = $('#stepId').val();
			//console.log(processStepId);

			processJSONData = {};
			$scope.processType = "";
			$scope.processQueries = [];

			//Getting values from WFPopupCtrl which was set using service written in pipeline.js
			$scope.queries = pipelineService.queriesFromPopup();
			console.log($scope.queries);
			$scope.windowNames = pipelineService.windowNamesFromPopup();
			$scope.windowOptions = pipelineService.windowOptionsFromPopup();
			angular.forEach($scope.queries, function (val, k) {
				tempQueryObj = {};
				querySinkDetails = {};
				persitConfig = {};
				tempCol = [];
				strPersitTo = "";
				sinkConfigID = "";
				tempQueryObj.id = parseInt(val.id);
				tempQueryObj.windowName = val.windowName;
				tempQueryObj.businessRule = val.businessRule;
				tempQueryObj.query = val.query;
				tempQueryObj.windowPeriod = parseInt(val.windowPeriod);
				tempQueryObj.slidingInterval = parseInt(val.slidingInterval);
				tempQueryObj.table = val.table;
				tempQueryObj.persistEnabled = val.persistEnabled;
				tempQueryObj.predecessor = parseInt(val.predecessor);

				if (tempQueryObj.persistEnabled === true) {
					strPersitTo = val.querySinkDetails.strPersitTo;
					if (strPersitTo !== "LookupTable") {
						persitConfig = val.querySinkDetails.persitConfig;
					}
					if (strPersitTo === "Elassandra" || strPersitTo === "Cassandra" || strPersitTo === "OpenTSDB") {
						sinkConfigID = val.querySinkDetails.sinkConfigID;
					}
				}
				querySinkDetails.strPersitTo = strPersitTo;
				querySinkDetails.sinkConfigID = sinkConfigID;
				querySinkDetails.persitConfig = persitConfig;

				tempQueryObj.querySinkDetails = querySinkDetails;

				angular.forEach(val.columns, function (val2, k) {
					tempObj2 = {
							"initialName": val2.initialName,
							"name": val2.name,
							"type": val2.type,
							"selected": val2.selected
					};
					tempCol.push(tempObj2);
				});
				tempQueryObj.columns = tempCol;
				$scope.processQueries.push(tempQueryObj);
			});
			processJSONData = {
					"process_id": $scope.existingProcess.selected.id,
					"process_name": $scope.existingProcess.selected.name,
					"process_details": $scope.processRecordW.formatDetails,
					"process_type": $scope.processRecordW.strProcessType,
					"process_transform_queries": JSON.parse(angular.toJson($scope.processQueries)),
					"process_source_schema": JSON.parse(angular.toJson($scope.schemaData)),
					"process_window_names": $scope.windowNames,
					"process_window_options": $scope.windowOptions
			};
			console.log(JSON.stringify(processJSONData));
			$scope.flowchart.popup = processJSONData;
			$scope.flowchart.node = $scope.nodeData;
			//document.getElementById("workflow").contentWindow.angular.element("#workflowDiv").scope().savePopUp(idName, processJSONData);
			document.getElementById("workflow").contentWindow.document.getElementById('saveFlowchartStep').click();
			$scope.showMessage($scope.stepAddEdit, "process");
			$scope.sourceUpdatedProcess = false;
			$scope.processPopUp.close();
		}
		if (idName === "sink") {
			//sinkStepId = $('#stepId').val();
			//console.log(sinkStepId);

			sinkJSONData = {};

			//Getting selected column names in array
			$scope.selectedColumns = [];
			angular.forEach($scope.sinkSchemaData, function (val, k) {
				tempColObj = {
						"name": val.name,
						"type": val.type
				};
				$scope.selectedColumns.push(tempColObj);
			});

			sinkJSONData = {
					"sink_id": $scope.existingSink.selected.id,
					"sink_name": $scope.existingSink.selected.name,
					"sink_details": $scope.sinkRecordW.formatDetails,
					"sink_type": $scope.sinkTypes.selected.name,
					"sink_queries": $scope.sinkQueries
			};
			console.log(JSON.stringify(sinkJSONData));
			$scope.flowchart.popup = sinkJSONData;
			$scope.flowchart.node = $scope.nodeData;
			//document.getElementById("workflow").contentWindow.angular.element("#workflowDiv").scope().savePopUp(idName, sinkJSONData);
			document.getElementById("workflow").contentWindow.document.getElementById('saveFlowchartStep').click();
			$scope.showMessage($scope.stepAddEdit, "sink");
			$scope.sourceUpdatedSink = false;
			$scope.sinkPopUp.close();
		}
		if (idName === "visualize") {
			//visStepId = $('#stepId').val();
			//console.log(visStepId);

			/*var visJSONData = {};
                var tempEntityObj = {};
                $scope.selectedKpis = [];
                angular.forEach($scope.existingKPI.selected, function (val, k) {
                    $scope.selectedKpis.push({
                        "inKpiId" : val.id,
                    });
                });

                tempEntityObj.strTitle = document.getElementById("titleName").value;
                tempEntityObj.strDimension1Title = document.getElementById("dim1TitleName").value;
                tempEntityObj.strDimension2Title = document.getElementById("dim2TitleName").value;
                tempEntityObj.strSinkType = $scope.visSinkSel;
                tempEntityObj.strSinkTable = document.getElementById("strSinkTable").value;
                tempEntityObj.strSinkQuery = document.getElementById("queryOrUrl").value;

                visJSONData = {
                        "inVisualizeId": parseInt($scope.stepId),
                        "inVisualizeEntityId" : parseInt($scope.tempVisStep),
                        "strVisualizeName":document.getElementById("entityName").value,
                        "strVisualizeDesc" :document.getElementById("entityDesc").value,
                        "strVisualizeParentType":$scope.graphTypes.selected.name,
                        "strVisualizeSubType":$scope.graphSubTypes.selected.name,
                        "strKeySpace": document.getElementById("visKeyspace").value,
                        "strVisualizeConfigDetails":tempEntityObj,
                        "kpiList" : $scope.selectedKpis
                };*/
			visJSONData = [];
			visJSONData = $scope.visList;
			$scope.maxVisId = $scope.tempVisStep;
			console.log(JSON.stringify(visJSONData));
			$scope.flowchart.popup = visJSONData;
			$scope.flowchart.node = $scope.nodeData;
			//document.getElementById("workflow").contentWindow.angular.element("#workflowDiv").scope().savePopUp(idName, visJSONData);
			document.getElementById("workflow").contentWindow.document.getElementById('saveFlowchartStep').click();
			$scope.showMessage($scope.stepAddEdit, "visualize");
			$scope.sourceUpdatedVisualize = false;
			$scope.visPopUp.close();
		}
	};
	/**
	 *  Method to show confirmation dialog for Update source
	 */
	$scope.openSourceUpdateConfirm = function (idName, sourceJSONData, callback) {
		ngDialog.openConfirm({
			template : 'sourceUpdate',
			className : 'ngdialog-theme-default'
		}).then(
				function (value) {
					callback(idName, sourceJSONData);
				},
				function (reason) {
					console.log('Modal promise rejected. Reason: ', reason);
				}
		);
	};
	/**
	 *  Method to save source pop up
	 */
	$scope.saveSource = function(idName, sourceJSONData) {
		$scope.flowchart = {};
		$scope.flowchart.popup = sourceJSONData;
		$scope.flowchart.node = $scope.nodeData;
		document.getElementById("workflow").contentWindow.document.getElementById('saveFlowchartStep').click();
		$scope.showMessage($scope.stepAddEdit, "source");
		//document.getElementById("workflow").contentWindow.angular.element("#flowchart").scope().save(idName, sourceJSONData);
		/*document.getElementById("workflow").contentWindow.angular.element("#workflowDiv").scope().savePopUp(idName, sourceJSONData);*/
		$scope.sourcePopUp.close();
	}
	/**
	 *  Method to validate window values in Process popup
	 */
	$scope.windowDataChange = function() {
		var windowPeriod, slidingInterval;
		slidingInterval = parseInt(document.getElementById("slidingInterval").value);
		windowPeriod = parseInt(document.getElementById("windowPeriod").value);
		this.processform.slidingInterval.$setValidity('windowValidity',true);
		if (windowPeriod && slidingInterval) {
			if (windowPeriod < slidingInterval) {
				this.processform.slidingInterval.$setValidity('windowValidity',false);
				return;
			} else {
				this.processform.slidingInterval.$setValidity('windowValidity',true);
				return;
			}
		} else {
			this.processform.slidingInterval.$setValidity('windowValidity',true);
			return;
		}
	}
	/**
	 *  Method to validate primary and index key in process popup
	 */
	$scope.keyChange = function() {
		var primaryKey, indexKey;
		primaryKey = document.getElementById("primaryKey").value;
		indexKey = document.getElementById("indexKey").value;
		this.processform.indexKey.$setValidity('keyValidity',true);
		if (primaryKey && indexKey) {
			if (primaryKey === indexKey) {
				this.processform.indexKey.$setValidity('keyValidity',false);
				return;
			} else {
				this.processform.indexKey.$setValidity('keyValidity',true);
				return;
			}
		} else {
			this.processform.indexKey.$setValidity('keyValidity',true);
			return;
		}
	}
	/**
    	 *  Method to validate Kpi in Visualize pop up
    	 */
    	$scope.kpiChange = function(){
    		if($scope.existingKPI){
    			if(!$scope.existingKPI.selected || $scope.existingKPI.selected.length == 0){
    				$scope.kpiError = true;
    			}
    			else{
    				$scope.kpiError = false;
    			}
    		}
    	}
	/**
	 *
	 * @param pipelineName
	 */
	$scope.saveDataFlowPipeline = function (pipelineName) {
		//alert("save in pipeline");
		//document.getElementById("workflow").contentWindow.angular.element("#workflowDiv").scope().saveDataFlow(pipelineName);
		$scope.pipelineName = document.getElementById("workflow-name").value;
		document.getElementById("workflow").contentWindow.document.getElementById('saveFlowchart').click();
	};
	$scope.checkName = function ()	{

		var obj = this;
		obj.workflowForm.pipelineName.$setValidity('unique', true);
		var existingPipelineArray = $scope.existingPipelines;
		var pipelineObjectArray = $filter('filter')(existingPipelineArray,  function (item) {
			if ($scope.stepAddEdit == "Update"){
				return ((item.strPipelineName.toLowerCase() == (document.getElementById("workflow-name").value).toLowerCase()) && (item.inPipelineId != $scope.pipelineId));
			} else {
				return item.strPipelineName.toLowerCase() == (document.getElementById("workflow-name").value).toLowerCase();
			}
		},true);
		if (pipelineObjectArray!=undefined && pipelineObjectArray.length>0){
			obj.workflowForm.pipelineName.$setValidity('unique', false);
			return;
		} else {
			return;
		}
	};

	$scope.showSummary = function(pipelineRecord) {
		$scope.pipelineModel = {};
		$scope.monitoring = false;
		var token = localStorage.getItem('loginToken');
		if (token) {
			$scope.pipelineAction = "monitor";
			$scope.tempVisStep = $scope.getVisualizationMaxId(pipelineRecord.inPipelineId);
			$scope.maxVisId = $scope.tempVisStep;
			$scope.selectedPipelineRecord = pipelineRecord;
			$scope.deletedVisList = [];
			var pipelineRecordInp = {};
			pipelineRecordInp.categoryId = pipelineRecord.inCategoryId;
			pipelineRecordInp.kpiId = pipelineRecord.inKpiId;
			pipelineRecordInp.pipelineId = pipelineRecord.inPipelineId;
			console.log(JSON.stringify(pipelineRecordInp));
			pipelineService.getSelectedPipelineDetails(pipelineRecordInp, token).then(
					function (data) {
						if (data.success === true) {
							var pipelineData = data.root;
						    var nodeObj = JSON.parse(pipelineData.nodes);
						    $scope.pipelineModel.nodeDetails = nodeObj.nodeDetails;
						    $scope.pipelineModel.addlDetails = nodeObj.addlDetails;
						    $scope.pipelineModel.edges = JSON.parse(pipelineData.edges);
						    $scope.pipelineModel.inPipelineId = pipelineData.inPipelineId;
						    $scope.pipelineModel.strPipelineName = pipelineData.strPipelineName;
							$scope.monitoring = true;
						} else {
							error = data.errors;
							console.log(data);
						}
					});
		} else {
			$scope.openConfirmLogin();
		}
	}

	$scope.openProcessDetails = function(node){
		$scope.processNodeDetails = node
		$scope.processNodePopup = $uibModal.open({
			templateUrl : 'process-Node-Details.html',
			backdrop  : 'static',
			keyboard  : false,
			scope : $scope,
			size : 'small',
			resolve : {
				items : function () {
					return $scope.processNodeDetails;
				}
			}
		});
	}

	$scope.trustSrc = function(url) {
		$scope.url =  url
		return $sce.trustAsResourceUrl($scope.url);
	};

	$scope.openSparklint = function() {
		//spark Lint url to be placed
		$scope.sparklintUrl =  "http://<ip>:9094/cluster"
		$scope.openNewGraph = $uibModal.open({
			templateUrl : 'app/views/sparklint-iframe.html',
			backdrop: 'static',
			keyboard: false,
			scope: $scope,
			resolve: {
				items: function () {
					return $scope.sparklintUrl;
				}
			}
		});
	}

	$scope.transmittedSummaryDetails = "http://<ip>:3000/dashboard-solo/db/transmitted?orgId=1&panelId=1&from=now-3d&to=now&refresh=5s";
	$scope.rejectedSummaryDetails = "http://<ip>:3000/dashboard-solo/db/rejected?orgId=1&panelId=1&from=now-3d&to=now&refresh=5s"
	$scope.processedSummaryDetails = "http://<ip>:3000/dashboard-solo/db/processed?orgId=1&panelId=1&from=now-3d&to=now&refresh=5s";

	$scope.graphana1 = "http://<ip>:3000/dashboard-solo/db/source-throughput?panelId=1&orgId=1&from=now-3h&to=now&refresh=5s";
	$scope.grahana2 = "http://<ip>:3000/dashboard-solo/db/processed-throughput?refresh=5s&orgId=1&from=now-3h&to=now&refresh=5s";
	$scope.graphana3 = "http://<ip>:3000/dashboard-solo/db/processed?orgId=1&panelId=1&from=now-3d&to=now&refresh=5s";
}]);
