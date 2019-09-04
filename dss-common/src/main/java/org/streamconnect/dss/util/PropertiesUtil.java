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
package org.streamconnect.dss.util;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;


/**
 * Utility class to read an external property file and internal property file.
 * and load into a map
 */
final class PropertiesUtil  {
    /**
     *
     */
    private PropertiesUtil() {
    }

    /**
     * Load properties into a Map.
     *
     * @param fileName the file name
     * @return the map
     * @throws MalformedURLException
     */
    public static Map<String, String> loadProperties(final String fileName) {

        Map<String, String> propMap = new HashMap<String, String>();
        ResourceBundle rb = ResourceBundle.getBundle(fileName);
        Set<String> keys = rb.keySet();

        for (String key : keys) {
            propMap.put(key, rb.getString(key));
        }
        return propMap;
    }

}

