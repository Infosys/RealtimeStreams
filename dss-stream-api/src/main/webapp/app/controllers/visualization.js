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
 * Description : Controller for Visualization screen.
 */

app.controller("VisualizationCtrl", ['$scope', 'ngDialog','$rootScope','$route', '$http','$templateCache','uiGridConstants','$filter','$uibModal','visualizeService','$sce', function($scope,ngDialog, $rootScope,$route,$http,$templateCache,$uiGridConstants,$filter,$uibModal,visualizeService,$sce){	
	
	$scope.$on('$viewContentLoaded', function(){
		$scope.loadedHTML= $route.current.templateUrl;
		//alert($scope.loadedHTML);
		if($scope.loadedHTML.indexOf("visualization.html") != -1){
			if(!$rootScope.modulesMain.visualization.showBlock){
				$scope.openConfirmLogin();
			}else{
				// Calling service to list all pipelines on load of pipeline screen.	
				$scope.getVisualizeDetails();
			}
			enjoyhint_instance.stop();
		}

	});
	
	//alert("VisualizationCtrl");
	$scope.existingCategories = [];
	$scope.existingKpis = [];
	$scope.categories=[];
	var categoryName = "";
	var categoryID = 1;
	var kpiName = "";
	var kpiID = 1;
	console.log(localStorage.getItem('loginToken'));

	//Service call to get all existing categories with kpi.
	var token = localStorage.getItem('loginToken');
	$scope.getVisualizeDetails = function(){
		visualizeService.getVisualizations(categoryID,kpiID,token).then(	
			function(data) {
				if (data.success == true) {
					$scope.categories = data.root;
					angular.forEach($scope.categories, function(val, k) {
						$scope.existingCategories.push({
							"name" : val.strCategoryName,
							"id": val.inCategoryId,
							"kpi":val.kpis
						});
					});
				} else {
					var error = data.errors;
					console.log(error.errorMessage);
					if(error.errorCode=="3006"){
						$scope.openConfirmLogin();
					}
					$rootScope.existingCategories = "";
					
				}
			});
	}

	/*	$scope.categoryOnSelection = function(selectedItem){
		categoryName = selectedItem.name
		categoryID = selectedItem.id
		$scope.categoryselected = true;
		var kpis = [];
		$scope.existingKpis = [];
		angular.forEach($scope.categories, function(val, k) {
			if (val.inCategoryId == categoryID){
				kpis = val.kpis
				angular.forEach(kpis, function(val, k) {
					$scope.existingKpis.push({
						"name" : val.name,
						"id" : val.id
					});
				});
			}
		});


	}*/

	/*$scope.kpiOnSelection = function(selectedItem){
		kpiName = selectedItem.name;
		kpiID = selectedItem.id;

		visualizeService.getVisualizations(categoryID,kpiID).then(
				function(data) {
					if (data.success == true) {
						var visualizations = data.root;
						$scope.existingVisualizations = [];
						angular.forEach(visualizations, function(val, k) {
							$scope.existingVisualizations.push({
								"name" : val.strVEntityName,
								"id": val.inVEntityId,
								"graphType": val.strType,
								"type" : val.strParentType,
								"url" : val.objVEntityDetails.strSinkQuery
							});
						});
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
					}
				});	
		$scope.kpiselected = true;
	};*/

	/**
	 *  Method to show confirmation dialog for login
	 */
	
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
	
	
	$scope.openGraph= function(vis){
		var visualize = JSON.parse(vis.strVisualizeConfigDetails)
		$scope.graphUrl =  visualize.strSinkQuery;;
		$scope.visualizationName = vis.strVisualizeName; 
		$scope.openNewGrpah = $uibModal.open({
			templateUrl : 'app/views/visualization-iframe.html',
			backdrop: 'static', 
			keyboard: false,
			scope: $scope,
			resolve: {
				items: function () {
					return $scope.graphUrl,$scope.visualizationName;
				}
			}
		});		
	};

	$scope.cancel = function(popUpInstance) {
		console.log("Close >> "+popUpInstance);
		popUpInstance.close();
	}
	$scope.trustSrc = function(src) {
		return $sce.trustAsResourceUrl(src);
	};


}]);