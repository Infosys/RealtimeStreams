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
 * Description : User Access Services .
 */
/*global
app, console, serviceURL, msg
*/
app.service('accessService', function ($http, $q) {
    "use strict";
	/**
	 *  Method to get list of existing roles.
	 */
	this.getAllRoleDetails = function (tokenValue) {
		console.log(serviceURL.getAllRoles);
		return $http({
			method : 'GET',
			url : serviceURL.getAllRoles,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgGetAllRoleDetailsError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgGetAllRoleDetailsError;
			return res;
		});
	};
	/**
     *  Method to get existing roles corresponding to the selected user.
     */
     this.getUserDetails = function (userName, tokenValue) {
        console.log(serviceURL.getUserData);
        return $http({
            method : 'GET',
            url : serviceURL.getUserData + "?username=" + userName,
            headers: {'token': tokenValue}
        }).then(function (response) {
            if (typeof response.data === 'object') {
                return response.data;
            } else {
                var res = {};
                res.errors = {};
                res.success = false;
                res.errors.errorCode = "";
                res.errors.errorMessage = msg.msgGetAllRoleDetailsError;
                return res;
            }
        }, function (response) {
            var res = {};
            res.errors = {};
            res.success = false;
            res.errors.errorCode = "";
            res.errors.errorMessage = msg.msgGetAllRoleDetailsError;
            return res;
        });
     };
	/**
	 *  Method to add role.
	 */
	this.addRoleDetails = function (roleObj, tokenValue) {
		return $http({
			method : 'POST',
			url : serviceURL.saveRoleData,
			data : roleObj,
			headers: {'token' : tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
                res.errors = {};
                res.success = false;
                res.errors.errorCode = "";
				res.errors.errorMessage = msg.execError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.execError;
			return res;
		});
	};

	/**
	 *  Method to get list of existing users.
	 */
	this.getAllUserDetails = function (tokenValue) {
		console.log(serviceURL.getAllUsers);
		return $http({
			method : 'GET',
			url : serviceURL.getAllUsers,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	/**
	 *  Method to get list of existing user-role mapping.
	 */
	this.getAllUserRoleDetails = function (tokenValue) {
		console.log(serviceURL.getAllUserRoleMapping);
		return $http({
			method : 'GET',
			url : serviceURL.getAllUserRoleMapping,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};

	/**
	 *  Method to get role details for a specific role.
	 */
	this.getRoleDetails = function (tokenValue) {
		console.log(serviceURL.getRoleDetails);
		return $http({
			method : 'GET',
			url : serviceURL.getAllRoles,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	/**
	 *  Method to delete specific role.
	 */
	this.deleteRole = function (id, tokenValue) {
		console.log(serviceURL.deleteRole);
		return $http({
			method : 'POST',
			url : serviceURL.deleteRole + "/" + id,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	/**
	 *  Method to delete roles mapped to a specific user.
	 */
	this.deleteUserRoleMapping = function (id, tokenValue) {
		console.log(serviceURL.deleteUserRole);
		return $http({
			method : 'POST',
			url : serviceURL.deleteUserRole + "/" + id,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	/**
	 *  Method to edit roles mapped to a specific user.
	 */
	this.editUserRoleMapping = function (id) {
		console.log(serviceURL.editUserRole);
		return $http({
			method : 'POST',
			url : serviceURL.editUserRole + "/" + id
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	/**
	 *  Method to delete roles mapped to a specific user.
	 */
	this.addUserRoleDetails = function (roleObj, tokenValue) {
		return $http({
			method : 'POST',
			url : serviceURL.saveUserRoleMapping,
			data : roleObj,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.execError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.execError;
			return res;
		});
	};
	/**
	 *  Method to update access levels for a role.
	 */
	this.updateRoleAccess = function (accessObj, tokenValue) {
		console.log(serviceURL.updateAccessData);
		return $http({
			method : 'POST',
			url : serviceURL.updateAccessData,
			data : accessObj,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.execError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.execError;
			return res;
		});
	};
	/**
	 *  Method to get independent visualization hierarchy to provide access levels for a role.
	 */
	this.getAllPortalDetailsforRole = function (roleObj, tokenValue) {
		console.log(serviceURL.getAllPortalDetailsForRole);
		return $http({
			method : 'GET',
			url : serviceURL.getAllPortalDetailsForRole,
			headers: {'token': tokenValue},
			params : roleObj
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	/**
	 *  Method to get visualization hierarchy with access levels for a role.
	 */
	this.getAllPortalDetailsinAccess = function (tokenValue) {
		console.log(serviceURL.getAllPortalDetailsinAccess);
		return $http({
			method : 'GET',
			url : serviceURL.getAllPortalDetailsinAccess,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
});



