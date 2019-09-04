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
package org.streamconnect.dss.access.service;

import org.streamconnect.dss.dto.LoginResponse;
import org.springframework.stereotype.Service;

/**
 * Service for Login functionalities: 1. SignIn 2.SignOut 3.Authenticate
 *
 * @version 1.0
 */
@Service
public interface ILoginService {

    /**
     * Method to SignIn an User 1. Checks if token has a corresponding entry in
     * Cache. If so User is already logged In 2. If User not already loggedIn,
     * add entry to Cache
     *
     * @param userName
     *            the user name
     * @param password
     *            the password
     * @param token
     *            the token
     * @param uuId
     *            the uu id
     * @return LoginResponse
     */
    LoginResponse logIn(String userName, String password, String token,
            String uuId);

    /**
     * Method to SignOut a User from session 1. Checks if token has a
     * corresponding entry in Cache. If so User is already logged In, SignOut
     * the user, remove from Cache 2. If User not already loggedIn, return
     * appropriate message
     *
     * @param token
     *            the token
     * @return LoginResponse
     */
    LoginResponse logout(String token);

    /**
     * Authenticate User - Check if token has an entry in Cache. If so
     * authenticate, else fail authentication
     *
     * @param token
     *            the token
     * @return - LoginResponse If User is in session - 3008 - 'User Not in
     *         Session' else - 3009 - 'User Already in Session'
     */
    LoginResponse authenticate(String token);
}
