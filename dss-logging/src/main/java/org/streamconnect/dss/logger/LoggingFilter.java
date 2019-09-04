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

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Filter Class for LoggingBean with Custom validation can be done in future for
 * DSSLogger, that supplies information to resource classes and other providers.
 *
 * @see DSSLogger
 * @see LoggingBean
 * @see LoggingFilter
 */
public class LoggingFilter extends Filter {

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.log4j.spi.Filter#decide(org.apache.log4j.spi.LoggingEvent)
     */
    @SuppressWarnings("static-access")
    @Override
    public int decide(final LoggingEvent event) {
        /**
         * The log event must be logged immediately without consulting with the
         * remaining filters, if any, in the chain.
         */
        int result = this.ACCEPT;
        // obtaining the message object passed through DSSLogger
        Object message = event.getMessage();
        // checking if the message object is of correct type
        if (message instanceof LoggingBean) {
            @SuppressWarnings("unused")
            LoggingBean loggingBean = (LoggingBean) message;
            // TODO Custom Action needed Can be Done here in future
            result = this.NEUTRAL;
        }
        return result;
    }

}
