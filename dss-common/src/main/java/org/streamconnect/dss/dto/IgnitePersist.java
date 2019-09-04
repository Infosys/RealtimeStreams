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
package org.streamconnect.dss.dto;

/**
 * The type Ignite persist.
 */

public class IgnitePersist implements java.io.Serializable {

    /** The str nodelst. */
    private String strNodelst;

    /** The str tablename. */
    private String strTablename;


    /**
     * Instantiates a new Ignite persist.
     */
    public IgnitePersist() {
        super();
    }

    /**
     * Gets str nodelst.
     *
     * @return the str nodelst
     */
    public String getStrNodelst() {
        return strNodelst;
    }

    /**
     * Sets str nodelst.
     *
     * @param strNodelst the str nodelst
     */
    public void setStrNodelst(final String strNodelst) {
        this.strNodelst = strNodelst;
    }

    /**
     * Gets str tablename.
     *
     * @return the str tablename
     */
    public String getStrTablename() {
        return strTablename;
    }

    /**
     * Sets str tablename.
     *
     * @param strTablename the str tablename
     */
    public void setStrTablename(final String strTablename) {
        this.strTablename = strTablename;
    }


}
