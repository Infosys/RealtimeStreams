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
 * Description : Controller for UI Wizard.
 */

app.controller("UiWizardCtrl", ['$scope','ngDialog', '$rootScope', '$http','$templateCache','uiGridConstants','$filter','$uibModal','$sce', function($scope,ngDialog,$rootScope,$http,$templateCache,$uiGridConstants,$filter,$uibModal,$sce){
	
	//alert("UiWizardCtrl")
	/*$scope.operationalEntities = [{"id":1,
									"name": "Yarn-Resource Manager",
									"url": "http://10.177.116.69:9094"},
									{"id":2,
									"name": "HDFS",
									"url": "http://10.177.116.69:50070"},
									{"id":3,"name": "Livy",
										"url": "http://10.177.116.81:8998/"},
									{"id":4,"name": "Spark",
										"url": "http://10.177.116.81:4040/"},
									{"id":5,"name": "Grafana",
										"url": "http://10.177.116.81:3000"}]*/
	

	
	$scope.openConfirmLogin = function() {
		ngDialog.openConfirm({
			template : 'modalDialogLogin',
			className : 'ngdialog-theme-default',
			showClose: false
		}).then(
				function(value) {
					//callback();
					window.location = 'login.html';	
				},
				function(reason) {
					console.log('Modal promise rejected. Reason: ', reason);
				});
	};	
	
	if(!localStorage.getItem('loginToken')){
		$scope.openConfirmLogin();
		$scope.url = "";
	}else{
	
	//$scope.url = "http://10.177.116.69:9094";
	$scope.url = commonURL.YRManager;
	}
	$scope.entityName = "Yarn-Resource Manager"
	$scope.activeMenu = "YRManager";
	
	$scope.changeEntity = function(entity){
		$scope.activeMenu = entity;
		if(entity == "YRManager"){
			$scope.activeMenu = "YRManager";
			$scope.entityName = "Yarn-Resource Manager"
			$scope.url = /*"http://10.177.116.69:9094"*/commonURL.YRManager;
			document.getElementById('wizardFrame').src = document.getElementById('wizardFrame').src;
		}
		else if(entity == "HDFS"){
			$scope.activeMenu = "HDFS";
			$scope.entityName = "HDFS"
			$scope.url = /*"http://10.177.116.69:50070"*/commonURL.HDFS;
			document.getElementById('wizardFrame').src = document.getElementById('wizardFrame').src;
		}
		else if(entity == "StreamExecutor"){
			$scope.activeMenu = "StreamExecutor";
			$scope.entityName = "Stream Executor"
			$scope.url = /*"http://10.177.116.81:8998/threads"*/commonURL.StreamExecutor;
			document.getElementById('wizardFrame').src = document.getElementById('wizardFrame').src;
		}
		else if(entity == "Spark"){
			$scope.activeMenu = "Spark";
			$scope.entityName = "Spark"
			$scope.url = /*"http://10.177.116.69:4040/"*/commonURL.Spark;
			document.getElementById('wizardFrame').src = document.getElementById('wizardFrame').src;
		}
		else if(entity == "Grafana"){
			$scope.activeMenu = "Grafana";
			$scope.entityName = "Grafana"
			$scope.url = /*"http://10.177.116.81:3000"*/commonURL.Grafana;
			document.getElementById('wizardFrame').src = document.getElementById('wizardFrame').src;
		}
		/*angular.forEach($scope.operationalEntities, function(val) {
			if(entity.id == val.id){
				$scope.activeMenu = entity;
				$scope.entityName = val.name,
				$scope.url = val.url
			}
		});*/
	}
	$scope.trustSrc = function(src) {
		return $sce.trustAsResourceUrl(src);
	};
}])
