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
package org.streamconnect.dss.api.service;

import org.streamconnect.dss.dto.IdNameDto;
import org.streamconnect.dss.dto.UserFunctionDto;

import java.util.List;

/**
 * The Interface for User Function
 */
public interface IUserFunctionService {

    /**
     * Method for saving User function details.
     *
     * @param userFunctionDto the user function dto
     * @return boolean
     */
    boolean saveOrUpdateUserFunction(UserFunctionDto userFunctionDto);

    /**
     * Check user function exist or not.
     *
     * @param inUfId the in source id
     * @param ufName the source name
     * @param userName the user name
     * @return true, if successful
     */
    boolean checkUserFunctionExistOrNot(int inUfId, String ufName,
                                        String userName);

    /**
     * Method for getting user function list.
     *
     * @return listUfDto
     */
    List<IdNameDto> getUserFunctionList();

    /**
     * Method for getting user function details by passing uf id.
     *
     * @param ufId
     *            the user function id
     * @return the User Function data
     */
    UserFunctionDto getUserFunctionData(int ufId);

    /**
     * Method for deleting a User Function by passing user function id.
     *
     * @param ufId
     *            the user function id
     * @return boolean
     */
    boolean deleteUserFunction(int ufId);

}
