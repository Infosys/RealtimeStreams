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
package org.streamconnect.dss.access.cache.impl;

import org.streamconnect.dss.access.cache.ICacheService;
import org.streamconnect.dss.dto.UserSessionDto;
import org.streamconnect.dss.logger.DSSLogger;
import org.springframework.stereotype.Service;

import com.hazelcast.core.IMap;

/**
 * Cache Service for creating the Authentication Cache and Token Object for
 * distributed User cache object.
 *
 * @version 1.0
 */
@Service
public class CacheService implements ICacheService {

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(CacheService.class);

    /** The user map. */
    private IMap<String, UserSessionDto> userMap;

    /**
     * Instantiates a new cache service.
     */
    public CacheService() {
    }

    /**
     * Instantiates a new cache service.
     *
     * @param usrMap
     *            the user map
     */
    public CacheService(final IMap<String, UserSessionDto> usrMap) {
        setUserMap(usrMap);
    }

    /**
     * Gets the user map.
     *
     * @return the user map
     */
    public IMap<String, UserSessionDto> getUserMap() {
        return userMap;
    }

    /**
     * Sets the user map.
     *
     * @param userMap the user map
     */
    public void setUserMap(final IMap<String, UserSessionDto> userMap) {
        this.userMap = userMap;
    }

    /**
     * Mathod for adding user to session.
     *
     * @param key
     *            the key
     * @param user
     *            the user
     */
    public void addToCache(final String key, final UserSessionDto user) {
        getUserMap().put(key, user);
    }

    /**
     * Method for deleting user from session.
     *
     * @param key
     *            the key
     */
    public void deleteFromCache(final String key) {
        getUserMap().remove(key);
    }

    /**
     * Method for getting saved user information from cache.
     *
     * @return the cache
     */
    public IMap<String, UserSessionDto> getCache() {
        return getUserMap();
    }
}
