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
 * @desc Controller for Pipeline Process popup screen  
 */
/*global
app, console, serviceURL, msg, enjoyhint_instance, angular
*/
app.controller("WFPopupCtrl", ['$scope', '$rootScope', '$http', '$uibModal', '$templateCache', 'uiGridConstants', '$filter', 'ngDialog', 'pipelineService', 'configService', function ($scope, $rootScope, $http, $uibModal, $templateCache, $uiGridConstants, $filter, ngDialog, pipelineService, configService) {
    "use strict";
    var token = localStorage.getItem('loginToken'), error;
	$scope.identifier = ($scope.queries.length + 1).toString();

    $scope.schemaTypes = ["text", "string", "timestamp", "long", "double", "bigint", "boolean", "int", "varchar", "decimal"];
    $scope.updateSchemaPopup = false;

	$scope.sinkInProcessOnSelect = function (rec) {
		var sinkObj = {};
		sinkObj.sinkId = $scope.selectedSink.id;
		token = localStorage.getItem('loginToken');
		configService.getExistingSinkbyId(sinkObj, token).then(
            function (data) {
                if (data.success === true) {
                    $scope.sinkRecordInProcess = data.root;
                    $scope.sinkRecordInProcess = JSON.parse(JSON.stringify($scope.sinkRecordInProcess));
                    $scope.sinkRecordInProcess.formatDetails = JSON.parse($scope.sinkRecordInProcess.objSinkConfigDetails);

                } else {
                    error = data.errors;
                    console.log(error.errorMessage);
                    if (error.errorCode === "3006") {
                        $scope.openConfirmLogin();
                    }
                    $scope.sinkRecordInProcess = "";

                }
            }
        );

	};
	$scope.saveToSink = function (rec) {		
		token = localStorage.getItem('loginToken');
		var sinkType = angular.lowercase(rec.name);
		$scope.dataTypes = "";
		pipelineService.getSinkDatatypes(sinkType, token).then(
            function (data) {
                if (data.success === true) {
                    $scope.dataTypes = JSON.parse(data.root);
                    console.log(JSON.stringify($scope.dataTypes));
                } else {
                    error = data.errors;
                    console.log(error.errorMessage);
                    if (error.errorCode === "3006") {
                        $scope.openConfirmLogin();
                    }
                    $scope.dataTypes = "";
                }
            }
        );
	};
	$scope.addQuery = function () {
		var tempQuery = {}, tempColumnsArray = {}, tempSelectArray, tempTable = "", tempTableArr = [], tempColumn = [], duplicateQueryCount, dupIndex, index, querySinkDetails, strPersitTo, winObj, windowName, processSchema, persitConfig;
		$scope.columnsArray = [];
		tempSelectArray = parseSql($scope.query);
		// if condition to check if unstructured file type and flume is selected
		angular.forEach($scope.sourceSchemaInProcess, function (val, k) {
            if (val.selected === true) {
                $scope.columnsArray.push({"initialName" : val.initialName, "name": val.name, "type" : val.type, "selected": true});
            } else {
                $scope.columnsArray.push({"initialName" : val.initialName, "name": val.name, "type" : val.type, "selected": false});
            }
        });
		if (!$scope.query) {
			ngDialog.open({
				template : 'enterQuery'
			});
			return;
		} else {
			index = $scope.queries.indexOf($scope.query);
			if (index === -1) {
				if ($scope.columnsArray.length === 0) {
					console.log("Column does not present in source schema");
				} else {
					duplicateQueryCount = 0;
					dupIndex = -1;
					angular.forEach($scope.queries, function (val, k) {
						if ($scope.identifier === val.id) {
							duplicateQueryCount++;
							dupIndex = k;
						}
					});
					$scope.queryAdded = true;
					$scope.disableSave = false;
					windowName = "";
					if ($scope.selectWindowName.name === "New Window") {
						windowName = $scope.windowName;
					} else {
						windowName = $scope.selectWindowName.name;
					}
					processSchema = $scope.columnsArray;
					tempQuery = {
                        "id" : parseInt($scope.identifier),
                        "windowName" : windowName,
                        "businessRule" : $scope.businessRule,
                        "query" : $scope.query,
                        "windowPeriod" : $scope.windowPeriod,
                        "slidingInterval" : $scope.slidingInterval,
                        "table" : $scope.targetTable,
                        "columns" : processSchema,
                        "persistEnabled" : $scope.persistEnabled,
                        "predecessor" : parseInt($scope.predecessor),
                        "primaryKey" : $scope.primaryKey,
                        "indexKey" : $scope.indexKey
					};
					var querySinkDetails = {}
					var strPersitTo = "";

					if ($scope.persistEnabled === true) {
						persitConfig = {};
						if ($scope.strPersitTo.name === "Elassandra" || $scope.strPersitTo.name === "HBase" || $scope.strPersitTo.name === "Cassandra") {
							persitConfig = $scope.sinkRecordInProcess.formatDetails;
						}
						if ($scope.strPersitTo.name === "Elassandra" || $scope.strPersitTo.name === "Cassandra") {
							persitConfig.strKeySpace = $scope.processKeyspace;
							persitConfig.timeToLive = $scope.ttl;
							
						}
						if ($scope.strPersitTo.name === "HDFS") {
							persitConfig.HDFSfile = $scope.HDFSfile;
						}
						persitConfig.primaryKey = $scope.primaryKey;
						persitConfig.indexKey = $scope.indexKey;
						var querySinkDetails = {};
						querySinkDetails.strPersitTo = $scope.strPersitTo.name;
						if ($scope.strPersitTo.name === "Elassandra" || $scope.strPersitTo.name === "HBase") {
							querySinkDetails.sinkConfigID = $scope.sinkRecordInProcess.inSinkId;
						}
						querySinkDetails.persitConfig = persitConfig;

						tempQuery.querySinkDetails = querySinkDetails;

					}

					if (duplicateQueryCount === 0) {
						$scope.queries.push(tempQuery);
						$scope.predecessors.push(tempQuery.id);
						if ($scope.selectWindowName.name === "New Window") {
							winObj = {}
							winObj.name = $scope.windowName;
							winObj.windowPeriod = $scope.windowPeriod;
							winObj.slidingInterval = $scope.slidingInterval;

							$scope.windowOptions.push(winObj);
							$scope.windowNames.push($scope.windowName);
						}
						$scope.identifier = $scope.queries.length + 1;

                    } else {
		    	winObj = {}
                        winObj.name = $scope.windowName;
                        winObj.windowPeriod = $scope.windowPeriod;
                        winObj.slidingInterval = $scope.slidingInterval;

                        index = $scope.windowNames.indexOf(winObj.name);
                        if (index === -1) {
                            $scope.windowOptions.push(winObj);
                            $scope.windowNames.push(winObj.name);
                        } else {
                            if ($scope.windowName !== "NoWindow") {
                                $scope.windowOptions[index].windowPeriod = $scope.windowPeriod;
                                $scope.windowOptions[index].slidingInterval = $scope.slidingInterval;
                            }
                        }


                        $scope.queries[dupIndex] = tempQuery;
                        $scope.identifier = $scope.queries.length + 1;
                    }
					console.log($scope.queries);
					pipelineService.setQueryPopUpValues($scope.queries);
					pipelineService.setWindowNamePopUpValues($scope.windowNames);
					pipelineService.setWindowOptionPopUpValues($scope.windowOptions);
					if ($scope.disableQuery !== true) {
						$scope.query = undefined;
						$scope.primaryKey = undefined;
						$scope.indexKey = undefined;
					}
					$scope.selectWindowName = undefined;
					$scope.windowName = undefined;
					$scope.businessRule = undefined;
					$scope.columnsArray = undefined;
					$scope.persistEnabled = undefined;
					$scope.HDFSfile = undefined;
					$scope.processKeyspace = undefined;
					$scope.ttl = undefined;
					$scope.targetTable = undefined;
					$scope.windowPeriod = undefined;
					$scope.slidingInterval = undefined;
					$scope.strPersitTo = undefined;
					$scope.selectedSink = undefined;
					$scope.updateSchemaPopup = false;
				}
			} else {
				ngDialog.open({
					template : 'queryExists'
				});

			}
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
	$scope.validateQuery = function (query, sparkProcessType) {
		var processType = "", processQuery, queryObj;
		if (sparkProcessType === true) {
			processType = "spark";
		} else {
			processType = "flink";
		}
		processQuery = query;
		queryObj = {
            "processType" : processType,
            "processQuery" : processQuery
		};
		pipelineService.validateQuery(queryObj).then(
            function (data) {
                if (data.success === true) {
                    var message = data.root.statusMessage;
                    $scope.validationMessage = message;
                    ngDialog.openConfirm({
                        template : 'queryValidationModal',
                        className : 'ngdialog-theme-default',
                        scope : $scope,
                        resolve: {
                            items: function () {
                                return $scope.validationMessage;
                            }
                        }
                    });
                    $scope.validated = true;
                } else {
                    var error = data.errors;
                    console.log(data);
                    $scope.validationMessage = "Query validation failed";
                    ngDialog.openConfirm({
                        template : 'queryValidationModal',
                        className : 'ngdialog-theme-default',
                        scope : $scope,
                        resolve: {
                            items: function () {
                                return $scope.validationMessage;
                            }
                        }
                    });
                }
            }
        );
	};
	$scope.confirmAddQuery = function () {
		var destDataType;
			if (!$scope.query) {
				ngDialog.open({
					template : 'enterQuery'
				});
			} else {
				var flag = 0;
				if (($scope.query === "select * from " + $scope.initialTableName) && !$scope.sourceSchemaInProcess) {
					$scope.sourceSchemaInProcess = [];
					angular.forEach($scope.schemaData, function (val, k) {
						if ($scope.dataTypes && $scope.dataTypes[val.columnDataType]) {
							destDataType = $scope.dataTypes[val.columnDataType];
						} else {
							destDataType = val.columnDataType;
						}
						$scope.sourceSchemaInProcess.push({"initialName" : val.columnName, "name": val.columnName, "type" : destDataType, "selected" : true});
					});
				}
				if ($scope.sourceSchemaInProcess) {
					angular.forEach($scope.sourceSchemaInProcess, function (val, k) {
						if (val.selected === true) {
							flag++;
						}
					});
					if (flag === 0) {
						ngDialog.open({
							template : 'selectSchema'
						});
					} else {
						//to disable query validation popup
						$scope.validated = true;
						if ($scope.validated !== true) {
							ngDialog.openConfirm({
								template : 'addQuery',
								className : 'ngdialog-theme-default'
							}).then(
                                function (value) {
                                    $scope.addQuery();
                                },
                                function (reason) {
                                    console.log('Modal promise rejected. Reason: ', reason);
                                }
                            );
						} else {
							$scope.addQuery();
						}
					}
				} else {
					ngDialog.open({
						template : 'selectSchema'
					});
				}
			}
		
	};
	$scope.showAddQuery = function () {
		$scope.windowPeriod = "";
		$scope.windowName = "";
		$scope.selectWindowName = "";
		$scope.slidingInterval = "";
		$scope.predecessor = $scope.predecessors[-1];
		$scope.businessRule = "";
		$scope.query = "";
		$scope.targetTable = "";
		$scope.persistEnabled = false;
		$scope.strPersitTo = undefined;
		$scope.processKeyspace = "";
		$scope.ttl = "";
		$scope.HDFSfile = undefined;
		$scope.selectedSink = undefined;
		$scope.primaryKey = undefined;
		$scope.indexKey = undefined;
		$scope.showQuerySection = true;
		$scope.identifier = $scope.queries.length + 1;
	};

	//cancelQuery - clear data and hide the query section
	$scope.cancelQuery = function () {
		$scope.windowPeriod = "";
		$scope.slidingInterval = "";
		$scope.predecessor = $scope.predecessors[-1];
		$scope.businessRule = "";
		$scope.query = "";
		$scope.targetTable = "";
		$scope.persistEnabled = false;
		$scope.strPersitTo = undefined;
		$scope.processKeyspace = "";
		$scope.ttl = "";
		$scope.HDFSfile = undefined;
		$scope.selectedSink = undefined;
		$scope.primaryKey = undefined;
		$scope.indexKey = undefined;
		$scope.showQuerySection = false;
		$scope.identifier = $scope.queries.length + 1;
	};

	//clearQuery excluding sequence
	$scope.clearQuery = function () {
		$scope.windowPeriod = "";
		$scope.slidingInterval = "";
		$scope.predecessor = $scope.predecessors[-1];
		$scope.businessRule = "";
		$scope.query = "";
		$scope.targetTable = "";
		$scope.persistEnabled = false;
		$scope.strPersitTo = undefined;
		$scope.processKeyspace = "";
		$scope.ttl = "";
		$scope.HDFSfile = undefined;
		$scope.selectedSink = undefined;
		$scope.primaryKey = undefined;
		$scope.indexKey = undefined;
		$scope.showQuerySection = true;
	};
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
	window.loadedSqlIframe = function () {
		if ($scope.queryInProcess !== "") {
			document.getElementById("queryIframe").contentWindow.postMessage($scope.queryInProcess, '*');
		}
	};
	var tempSchema = [];
	$scope.openSchemaBox = function () {
		var  pre, id, flag = 0, destDataType;
		if ($scope.disableQuery !== true) {
			$scope.columnAddition = false;
			pre = $scope.predecessor;
			id = $scope.identifier;
			angular.forEach($scope.queries, function (value) {
				if (value.id === id) {
					flag++;
				}
			});
			if (pre === 0) {
				if (!$scope.sourceSchemaInProcess) {
					$scope.sourceSchemaInProcess = [];
					angular.forEach($scope.schemaData, function (val, k) {
						//Logic to change the datatype based on sink type.	
						if ($scope.dataTypes && $scope.dataTypes[val.columnDataType]) {
							destDataType = $scope.dataTypes[val.columnDataType];
						} else {
							destDataType = val.columnDataType;
						}
						$scope.sourceSchemaInProcess.push({"initialName" : val.columnName, "name": val.columnName, "type" : destDataType});
					});
				} else if ($scope.sourceSchemaInProcess && flag === 0 && $scope.updateSchemaPopup === false) {
					$scope.sourceSchemaInProcess = [];
					angular.forEach($scope.schemaData, function (val, k) {
						//Logic to change the datatype based on sink type.	
						if ($scope.dataTypes && $scope.dataTypes[val.columnDataType]) {
							destDataType = $scope.dataTypes[val.columnDataType];
						} else {
							destDataType = val.columnDataType;
						}
						$scope.sourceSchemaInProcess.push({"initialName" : val.columnName, "name": val.columnName, "type" : destDataType});
					});
				} 
				//Condition checked when a query is edited and predecessor is set to zero
				/*else if ($scope.sourceSchemaInProcess && flag !== 0) {
					$scope.sourceSchemaInProcess = [];
					angular.forEach($scope.schemaData, function (val, k) {
					$scope.sourceSchemaInProcess.push({"initialName" : val.columnName, "name": val.columnName, "type" : val.columnDataType});
				});
			}*/
			} else if (pre !== 0) {
                if (flag === 0 && $scope.updateSchemaPopup === false) {
                    var col = [];
                    $scope.sourceSchemaInProcess = [];
                    angular.forEach($scope.queries, function (val, k) {
                        if (val.id === pre) {
                            col = val.columns;
                            angular.forEach(col, function (val1, k) {
                                if (val1.selected === true) {
                                    $scope.sourceSchemaInProcess.push({"initialName" : val1.name, "name": val1.name, "type" : val1.type});
                                }
                            });
                        }
                    });
				}
			}
			if ($scope.query === "select * from " + $scope.initialTableName) {
				$scope.selectedAllRows = false;
				//Logic to change the datatype based on sink type.				
				angular.forEach($scope.sourceSchemaInProcess, function (val, k) {
					if ($scope.dataTypes && $scope.dataTypes[val.type]) {
						val.type = $scope.dataTypes[val.type];
					}					
				});
				$scope.selectAllRows();
				$scope.updateSchemaPopup = true;
			}
			var localObject = $scope.sourceSchemaInProcess;
			localStorage.setItem('localObject', JSON.stringify(localObject));
		} else {
			if (!$scope.sourceSchemaInProcess) {
				$scope.sourceSchemaInProcess = [];
				$scope.sourceSchemaInProcess.push({"initialName" : $scope.initialColumnName, "name": $scope.initialColumnName, "type" : ""});
			}
			var localObject = $scope.sourceSchemaInProcess;
			localStorage.setItem('localObject', JSON.stringify(localObject));
		}
		$scope.processQueryPopup = $uibModal.open({
			templateUrl : 'workflow-schema-modal.html',
			backdrop  : 'static',
			keyboard  : false,
			scope : $scope,
			size : 'small',
			resolve : {
				items : function () {
					return $scope.disableQuery;
				}
			}
		});
	};
		/*//$scope.schemaInProcess = [];

		var tempColumnsArray = {};
		var tempSelectArray = parseSql($scope.query);
		var tempTable = "";
		var tempTableArr = [];
		var tempColumn = [];
		
		if ($scope.disableQuery != true) {
			angular.forEach(tempSelectArray, function (val, k) {
				if (val.word === "select") {
					tempColumnsArray = val.text.split(",");
	
					angular.forEach(tempColumnsArray, function (val3, k) {
						
	
						if (val3.indexOf(" as ") != -1) {
							var temp3 = val3.split(" as ");
							tempColumn.push(temp3[1]);
						}else {
							tempColumn = tempColumnsArray;
						}
					});
	
				}
				if (val.word === "from") {
					
					tempTable = $scope.query.substring(val.index+5);
					tempTableArr = tempTable.split(" ");
				}
			});
			
			if (tempColumn[0] === "*") {
				angular.forEach($scope.schemaData, function (val, k) {
					tempColumn.push($scope.schemaData.columnName);
				});
			}
			
			var colExistsCount = 0;
			tempSchema = $scope.schemaInProcess;
			
			angular.forEach($scope.schemaInProcess, function (value,key) {
				var exists = false;
				angular.forEach(tempColumn, function (value1, key1) {
					if (angular.equals(value.name, value1)) {
						exists = true;
					}
				});
				if (exists === false) {
					$scope.schemaInProcess.splice(key);
				}
			});
			
			console.log(JSON.stringify($scope.schemaInProcess));
			
			for(var i=0; i <tempColumn.length; i++) {
				var exists = false;
				angular.forEach($scope.schemaInProcess, function (val1,k) {
					if (angular.equals(tempColumn[i], val1.name)) {
						exists = true;
					}
				});
				if (exists === false) {
					angular.forEach($scope.schemaData, function (val2, k) {	
						if (tempColumn[i] === val2.columnName) {
							colExistsCount ++;
							$scope.schemaInProcess.push({"name" : tempColumn[i], "type" : val2.columnDataType});
						}
					});
					
					if (colExistsCount === 0) {
						alert("Column "+tempColumn +" does not exists in source schema");
						return;
					}
				}
			}			
		}
		else {
			$scope.schemaInProcess = [{"name": $scope.initialColumnName, "type" : "text"}];
		}

		console.log(JSON.stringify($scope.schemaInProcess));
		if (colExistsCount != 0 || !colExistsCount) {
			$scope.processQueryPopup = $uibModal.open({
				templateUrl : 'workflow-schema-modal.html',
				backdrop  : 'static',
				keyboard  : false,
				scope : $scope,
				size:'small'
			})
		}*/
	$scope.selectAllRows = function () {
        if (!$scope.selectedAllRows) {
            $scope.selectedAllRows = true;
        } else {
            $scope.selectedAllRows = false;
        }
        angular.forEach($scope.sourceSchemaInProcess, function (val) {
            val.selected = $scope.selectedAllRows;
        });
    };
    $scope.ShowAddColumnInSchema = function () {
        $scope.columnAddition = true;
    };
    $scope.addColumnInSchema = function (newSchemaName, newSchemaType) {
        var name, type, item;
        name = newSchemaName;
        type = newSchemaType;
        item = {"initialName" : name, "name" : name, "type" : type, "selected" : true};
        $scope.sourceSchemaInProcess.splice(0, 0, item);
        document.getElementById("newSchemaName").value = "";
        document.getElementById("newSchemaType").value = "";
    };
	$scope.updateSourceSchemaInProcess = function (schema) {
		$scope.updateSchemaPopup = true;
		console.log("updateSchemaInProcess>>");
		console.log(JSON.stringify($scope.sourceSchemaInProcess));
		$scope.sourceSchemaInProcess = schema;
		$scope.processQueryPopup.close();
	};
	$scope.closeSourceSchemaInProcess = function () {
		var retrievedSchema = localStorage.getItem('localObject');
		$scope.sourceSchemaInProcess = JSON.parse(retrievedSchema);
		$scope.processQueryPopup.close();
	};
	$scope.cancel = function (popUpInstance) {
		console.log("Close >> " + popUpInstance);
		popUpInstance.close();
	};
	$scope.remove = function (index) {
		$scope.queries.splice(index, 1);
		if ($scope.queries.length === 0) {
			$scope.queryAdded = false;
			$scope.disableSave = true;
			$scope.showQuerySection = true;
		}
		$scope.identifier = $scope.queries.length + 1;
		pipelineService.setQueryPopUpValues($scope.queries);
		pipelineService.setWindowNamePopUpValues($scope.windowNames);
	};

	$scope.setClickedRow = function (index) {
		$scope.selectedRow = index;
	};


	function parseSql(sql) {
		var found = [];

		["select", "from"].forEach(function (word) {
			var idx = sql.indexOf(word), keptIdx;

			while (idx !== -1) {
				found.push({word : word, index : idx});
				idx = sql.indexOf(word, idx + 1);
				keptIdx = idx;
			}
		});

		found.sort(function (x, y) { return x.index - y.index; });
		found.forEach(function (x, i, xs) {
			if (i < xs.length - 1) {
				x.text = sql.substring(x.index, xs[i + 1].index).replace(xs[i].word, "").trim();
			}
		});

		return found;
	}

	//Function called on editing a query.
	$scope.editQueryRow = function (selectedQuery) {
		var savedWindowName = "", persistLocationName, configID;
		$scope.showQuerySection = true;
		$scope.businessRule = selectedQuery.businessRule;
		$scope.windowPeriod = selectedQuery.windowPeriod;
		$scope.slidingInterval = selectedQuery.slidingInterval;
		angular.forEach($scope.predecessors, function (val, k) {
			if (val === selectedQuery.predecessor) {
				$scope.predecessor = val;
			}
		});
		$scope.identifier = selectedQuery.id;
		$scope.query = selectedQuery.query;
		$scope.columnsArray = selectedQuery.columns;
		$scope.sourceSchemaInProcess = selectedQuery.columns;
		/*angular.forEach($scope.sourceSchemaInProcess, function (val1) {
			angular.forEach(selectedQuery.columns, function (val2) {
				if (angular.equals(val1.intialName,val2.intialName)) {
					val1.selected = true;
				}
			})
		})*/
		$scope.persistEnabled = selectedQuery.persistEnabled;
		$scope.targetTable = selectedQuery.table;
		savedWindowName = selectedQuery.windowName;
		angular.forEach($scope.windowOptions, function (val, k) {
			if (val.name === savedWindowName) {
				$scope.windowOptions.selected = val;
			}
		});
		$scope.selectWindowName = $scope.windowOptions.selected;
		$scope.windowName = selectedQuery.windowName;

		if ($scope.persistEnabled === true) {
			persistLocationName = selectedQuery.querySinkDetails.strPersitTo;
			angular.forEach($scope.persistLocations, function (val, k) {
				if (val.name === persistLocationName) {
					$scope.persistLocations.selected = val;
				}
			});
			$scope.strPersitTo = $scope.persistLocations.selected;

			configID = selectedQuery.querySinkDetails.sinkConfigID;
			angular.forEach($scope.existingSink, function (val, k) {
				if (val.id === configID) {
					$scope.existingSink.selected = val;
				}
			});
			$scope.selectedSink = $scope.existingSink.selected;
			$scope.primaryKey = selectedQuery.querySinkDetails.persitConfig.primaryKey;
			$scope.indexKey = selectedQuery.querySinkDetails.persitConfig.indexKey;
			$scope.HDFSfile = selectedQuery.querySinkDetails.persitConfig.HDFSfile;
			$scope.processKeyspace = selectedQuery.querySinkDetails.persitConfig.strKeySpace;
			$scope.ttl = selectedQuery.querySinkDetails.persitConfig.timeToLive;
		}
		$scope.sinkInProcessOnSelect();
	};
	$scope.clearWFProcessPopUp = function (idName) {
		if (idName === "process") {
			$scope.existingProcess.selected = {};
			$scope.processRecordW = {};
			$scope.selectWindowName = "";
			$scope.windowName = "";
			$scope.windowPeriod = "";
			$scope.slidingInterval = "";
			$scope.predecessor = $scope.predecessors[0];
			$scope.businessRule = "";
			$scope.query = "";
			$scope.targetTable = "";
			$scope.persistEnabled = false;
			$scope.strPersitTo = undefined;
			$scope.processKeyspace = "";
			$scope.ttl = "";
			$scope.HDFSfile = undefined;
			$scope.selectedSink = undefined;
			$scope.queries = [];
			$scope.queryAdded = false;
			$scope.showQuerySection = true;
			$scope.sparkProcessType = true;
			$scope.primaryKey = "";
			$scope.indexKey = "";
			$scope.identifier = 1;
		}
	};

}]);
