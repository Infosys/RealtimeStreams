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
package org.streamconnect.dss.api.ui.response;

import java.io.InputStream;

/**
 * The Class User Function
 */
public class UserFunction implements java.io.Serializable {

    /**The user defined function id*/
    private int inUfId;

    /**The user defined function name*/
    private String strUfName;

    /**The user defined function's register method*/
    private String strRegisterMethod;

    /**The user defined function's code base*/
    private Object objCodeBase;

    /**The user defined function config name*/
    private String strUfConfigName;

    /**The user defined function description*/
    private String strUfDesc;


    /**
     * Instantiates a new User Function.
     */
    public UserFunction(){
        super();
    }

    /**
     * Gets the user function id.
     *
     * @return the user function id
     */
    public int getInUfId() {
        return inUfId;
    }

    /**
     * Sets the user function id.
     *
     * @param inUfId
     *            the new user function id
     */
    public void setInUfId(int inUfId) {
        this.inUfId = inUfId;
    }

    /**
     * Gets the str user function name.
     *
     * @return the str user function name
     */
    public String getStrUfName() {
        return strUfName;
    }

    /**
     * Sets the str user function name.
     *
     * @param strUfName
     *            the new str user function name.
     */
    public void setStrUfName(String strUfName) {
        this.strUfName = strUfName;
    }

    /**
     * Gets the str user function register method.
     *
     * @return the str user function register method.
     */
    public String getStrRegisterMethod() {
        return strRegisterMethod;
    }

    /**
     * Sets the str user function register method.
     *
     * @param strRegisterMethod
     *            the new str user function register method.
     */
    public void setStrRegisterMethod(String strRegisterMethod) {
        this.strRegisterMethod = strRegisterMethod;
    }

    /**
     * Gets the str user function code base.
     *
     * @return the str user function code base.
     */
    public Object getObjCodeBase() {
        return objCodeBase;
    }

    /**
     * Sets the str user function code base.
     *
     * @param objCodeBase
     *            the new str user function code base.
     */
    public void setObjCodeBase(Object objCodeBase) {
        this.objCodeBase = objCodeBase;
    }
    /**
     * Gets the str user function config name
     *
     * @return the str user function config name
     */
    public String getStrUfConfigName() {
        return strUfConfigName;
    }

    /**
     * Sets the str user function config name
     *
     * @param strUfConfigName
     *            the new str user function config name
     */
    public void setStrUfConfigName(String strUfConfigName) {
        this.strUfConfigName = strUfConfigName;
    }

    /**
     * Gets the str user function description
     *
     * @return the str user function desc
     */
    public String getStrUfDesc() {
        return strUfDesc;
    }

    /**
     * Sets the str user function description
     *
     * @param strUfDesc
     *            the new str user function main class name.
     */
    public void setStrUfDesc(String strUfDesc) {
        this.strUfDesc = strUfDesc;
    }


}
