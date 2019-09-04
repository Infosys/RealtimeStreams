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
package org.streamconnect.dss.logger;

import org.streamconnect.dss.util.Constants;
import org.apache.log4j.or.ObjectRenderer;

/**
 * Contract Class for a provider, that supplies parsed LoggingBean information
 * to resource classes and other providers.
 *
 * @see DSSLogger
 * @see LoggingBean
 * @see LoggingFilter
 */
public class LoggingRender implements ObjectRenderer {

    /**
     * Do render.
     *
     * @param obj
     *            type the class of object for which a context is desired
     * @return a context for the supplied type or <code>String</code> if a
     *         context for the supplied type is not available from this
     *         provider.
     */
    public String doRender(final Object obj) {
        StringBuffer buffer = new StringBuffer(Constants.NUMBER_FIFTY);
        LoggingBean loggingBean = null;
        String modelInstanceId = null;
        String errorCode = null;
        String hostName = null;
        String timeStamp = null;
        String messageType = null;
        String className = null;
        // check if the instance is of correct type ItagLoggerBean
        if (obj instanceof LoggingBean) {
            loggingBean = (LoggingBean) obj;
            modelInstanceId = loggingBean.getModelInstanceId();
            errorCode = loggingBean.getErrorCode();
            hostName = loggingBean.getHostName();
            timeStamp = loggingBean.getTimeStamp();
            messageType = loggingBean.getMessageType();
            className = loggingBean.getClassName();
            buffer.append(modelInstanceId);
            buffer.append(StaticResource.SEPARATOR);
            buffer.append(errorCode);
            buffer.append(StaticResource.SEPARATOR);
            buffer.append(hostName);
            buffer.append(StaticResource.SEPARATOR);
            buffer.append(timeStamp);
            buffer.append(StaticResource.SEPARATOR);
            buffer.append(messageType);
            buffer.append(StaticResource.SEPARATOR);
            buffer.append(className);
        }
        return buffer.toString();
    }

}
