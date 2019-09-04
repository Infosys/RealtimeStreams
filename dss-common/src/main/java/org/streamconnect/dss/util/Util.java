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

import com.google.gson.Gson;
import java.util.Map;
import java.util.HashMap;

/**
 * The type Util.
 */
public final class Util {
    /**
     *Constructor for Util.
     */
    private Util() {
    }

    /**
     * To json string string.
     *
     * @param obj the obj
     * @return the string
     */
    public static String toJsonString(final Object obj) {
        Gson gson = new Gson();
        String strJson = gson.toJson(obj);
        return strJson;
    }


    /**
     * method for mapping the datatypes for Python DF Schema.
     *
     * @return map schema map
     */
    public static Map<String, String> getSchemaMap() {

        /**
         * Sample List of Types
         * "NullType", "StringType", "BinaryType", "BooleanType", "DateType",
         *" TimestampType", "DecimalType", "DoubleType", "FloatType",
         *  "ByteType", "IntegerType",
         *
         */
        Map<String, String> schemaTypeMap = new HashMap<String, String>();
        schemaTypeMap.put("null", "NullType()");
        schemaTypeMap.put("int", "IntegerType()");
        schemaTypeMap.put("integer", "IntegerType()");
        schemaTypeMap.put("String", "StringType()");
        schemaTypeMap.put("string", "StringType()");
        schemaTypeMap.put("float", "FloatType()");
        schemaTypeMap.put("double", "DoubleType()");
        schemaTypeMap.put("long", "LongType()");
        schemaTypeMap.put("boolean", "BooleanType()");
        schemaTypeMap.put("date", "DateType()");
        schemaTypeMap.put("timestamp", "TimestampType()");
        schemaTypeMap.put("byte", "ByteType()");
        schemaTypeMap.put("short", "ShortType()");
        return schemaTypeMap;

    }
}


