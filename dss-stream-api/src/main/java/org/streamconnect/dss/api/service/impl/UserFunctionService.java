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
package org.streamconnect.dss.api.service.impl;

import org.streamconnect.dss.api.service.IUserFunctionService;
import org.streamconnect.dss.dto.IdNameDto;
import org.streamconnect.dss.dto.UserFunctionDto;
import org.streamconnect.dss.metadata.connection.tx.UserFunctionTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The User Function Service
 */
@Service
public class UserFunctionService implements IUserFunctionService {

    /** The user function transaction. */
    @Autowired
    private UserFunctionTransaction userFunctionTransaction;

    /**
     * Method for saving user function details.
     *
     * @param userFunctionDto the user function dto
     * @return boolean
     */
    public boolean saveOrUpdateUserFunction(UserFunctionDto userFunctionDto) {
        return userFunctionTransaction.saveOrUpdateUserFunction(userFunctionDto);
    }


    /**
     * Check user function exist or not.
     *
     * @param inUfId  the in source id
     * @param ufName  the source name
     * @param userName the user name
     * @return true, if successful
     */
    public boolean checkUserFunctionExistOrNot(int inUfId, String ufName, String
            userName) {
        return userFunctionTransaction.checkUserFunctionExistOrNot(inUfId, ufName,
                userName);
    }

    /**
     * Method for getting user function list.
     *
     * @return listUfDto
     */
    public List<IdNameDto> getUserFunctionList() {
        return userFunctionTransaction.getUserFunctionList();
    }

    /**
     * Method for getting user function details by passing user function id.
     *
     * @param inUfId the uf id
     * @return the User Function data
     */
    public UserFunctionDto getUserFunctionData(int inUfId) {
        return userFunctionTransaction.getUserFunctionData(inUfId);
    }

    /**
     * Method for deleting a user function by passing uf id.
     *
     * @param ufId the uf id
     * @return boolean
     */
    public boolean deleteUserFunction(int ufId) {
        return userFunctionTransaction.deleteUserFunction(ufId);
    }
}
