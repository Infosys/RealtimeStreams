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
 * Description : Service for file upload.
 */

app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(action,tokenValue,cssFile,logoFile,uploadUrl,updatePortalObj){
    	var fd = new FormData();
    	if(action == "new"){
	       fd.append('strPortalCss', cssFile);
	       fd.append('strPortalCssDetails', cssFile);
	       fd.append('strPortalLogo', logoFile);
	       fd.append('strPortalLogoDetails', logoFile);
	       fd.append('portalId',0);
	       fd.append('strPortalName',document.getElementById("nameOfPortal").value);
	       fd.append('strPortalTitle',document.getElementById("portalTitle").value);
	       fd.append('strPortalUrl',document.getElementById("portalURL").value);
       }
       else if(action == "update"){
    	   fd.append('strPortalCss', cssFile);
	       fd.append('strPortalCssDetails', cssFile);
	       fd.append('strPortalLogo', logoFile);
	       fd.append('strPortalLogoDetails', logoFile);
	       fd.append('portalId',updatePortalObj.portalId);
	       fd.append('strPortalName',updatePortalObj.strPortalName);
	       fd.append('strPortalTitle',updatePortalObj.strPortalTitle);
	       fd.append('strPortalUrl',updatePortalObj.strPortalUrl);
       }
       return $http.post(uploadUrl, fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined, 'token': tokenValue}
			
       }).then(function(response) {
    	   if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgDriverSaveError;
				return res;
			}
			
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgDriverSaveError;
			return res;
       });
    }
    
    this.uploadSchemaFileToUrl = function(schemaFile,fileType,delimiter,headerExists,schemaUploadUrl,tokenValue){
    	var fd = new FormData();
    
       fd.append('strSchemaInput', schemaFile);
       fd.append('strFileType', fileType);
       fd.append('strDelimiter', delimiter);
       fd.append('isHeaderExists', headerExists);
       return $http.post(schemaUploadUrl, fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined, 'token': tokenValue }
       }).then(function(response) {
    	   if (typeof response.data === 'object') {
    		   return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgDriverSaveError;
				return res;
			}
			
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgDriverSaveError;
			return res;
       });
    }
 }]);