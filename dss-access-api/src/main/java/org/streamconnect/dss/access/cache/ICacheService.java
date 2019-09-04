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
package org.streamconnect.dss.access.cache;

import com.hazelcast.core.IMap;
import org.streamconnect.dss.dto.UserSessionDto;
import org.springframework.stereotype.Service;

/**
 * Cache Service for creating the Authentication Cache and Token Object for
 * distributed customer cache object.
 *
 * @version 1.0
 */
@Service
public interface ICacheService {

    /**
     * Mathod for adding user to session.
     *
     * @param key
     *            the key
     * @param user
     *            the user
     */
    void addToCache(String key, UserSessionDto user);

    /**
     * Method for deleting user from session.
     *
     * @param key
     *            the key
     */
    void deleteFromCache(String key);

    /**
     * Method for getting saved user information from cache.
     *
     * @return the cache
     */
    IMap<String, UserSessionDto> getCache();
}

