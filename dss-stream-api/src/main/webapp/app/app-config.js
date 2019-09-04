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
 * Description : Configuration file.
 */
app.config(function($routeProvider) {
	$routeProvider
	.when("/pipeline", {
		templateUrl : "app/views/pipeline.html",
		controller : "PipelineCtrl"              
	})
	.when("/execute", {
		templateUrl : "app/views/execution.html",
		controller : "PipelineCtrl"
	})
	.when("/config", {
		templateUrl : "app/views/configuration.html",
		controller : "ConfigCtrl"
	})
	.when("/visualize", {
		templateUrl : "app/views/visualization.html",
		controller : "VisualizationCtrl"
	})
	.when("/ml", {
		templateUrl : "app/views/ml.html",
		controller : "VisualizationCtrl"
	})
	.when("/stream", {
		templateUrl : "app/views/stream.html",
		controller : "VisualizationCtrl"
	})
	.when("/visualizemanager", {
		templateUrl : "app/views/visualize-manager.html",
		controller : "visualizeManagerCtrl"
	})
	.when("/usermanager", {
		templateUrl : "app/views/user-manager.html",
		controller : "UserCtrl"
	})
	.when("/uiwizard", {
		templateUrl : "app/views/ui-wizard.html",
		controller : "UiWizardCtrl"
	})
	.when("/home", {
		templateUrl : "app/views/home.html",
		controller : "MainCtrl"              
	})
	.otherwise({
		redirectTo: '/home'
	});

});
