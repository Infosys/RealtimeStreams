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
package org.streamconnect.dss.access.api.filter;

import org.streamconnect.dss.access.api.builder.BaseResponseBuilder;
import org.streamconnect.dss.access.cache.impl.CacheService;
import org.streamconnect.dss.dto.UserSessionDto;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.enums.Messages;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Stream Platform filter proxy class to filter all the HTTP request . This
 * platform filter can filter the request with basically checking the
 * authentication token which is expected to come with every request.
 *
 * @version 1.0
 * @see CacheService
 */
@Component
@Scope("singleton")
public class PlatformFilter extends BaseResponseBuilder
        implements ContainerRequestFilter {

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(PlatformFilter.class);

    /** The Constant FILTER_URL1. */
    public static final String FILTER_URL1 = "/db/";

    /** The Constant FILTER_URL2. */
    public static final String FILTER_URL2 = "/vm/";

    /** The Constant FILTER_URL3. */
    public static final String FILTER_URL3 = "/pa/";

    /** The Constant FILTER_URL4. */
    public static final String FILTER_URL4 = "/ua/";

    /** The Constant FILTER_URL4. */
    public static final String FILTER_URL5 = "/uf/";

    /** The Constant TOKEN. */
    public static final String TOKEN = "token";

    /** The cache service. */
    @Autowired
    private CacheService cacheService;

    /**
     * Filter.
     *
     * @param request
     *            the request
     * @return the container request
     */
    public ContainerRequest filter(final ContainerRequest request) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + "PlatformFilter : filter function : "
                + Constants.START_STATUS);
        final URI absolutePath = request.getAbsolutePath();
        final String path = absolutePath.getPath();
        if (path.contains(FILTER_URL1) || path.contains(FILTER_URL2)
                || path.contains(FILTER_URL3) || path.contains(FILTER_URL4)
                || path.contains(FILTER_URL5)) {
            if (request.getHeaderValue(TOKEN) == null) {
                // Token is NA
                Response response = webException();
                LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " PlatformFilter : filter function : "
                        + Constants.END_STATUS);
                throw new WebApplicationException(response);

            } else {
                try {
                    final UserSessionDto userSession = cacheService.getUserMap()
                            .get(request.getHeaderValue(TOKEN));
                    // User already logged in
                    if (userSession.getUserName() == null
                            || "".equals(userSession.getUserName())) {
                        Response response = webException();
                        throw new WebApplicationException(response);
                    }
                } catch (Exception e) {
                    Response response = webException();
                    throw new WebApplicationException(response);
                }
                LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                        + " PlatformFilter : filter function : "
                        + Constants.END_STATUS);
                return request;
            }
        } else {
            // URLS Doesn't need authentication
            LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                    + "PlatformFilter : filter function : "
                    + Constants.END_STATUS);
            return request;
        }
    }

    /**
     * Access control exception message.
     *
     * @return Response
     */
    private Response webException() {
        // TODO Vulnerability Test Check ( Response.ok)
        Response response = Response.ok(authFailedErrorMessage())
                .header(Messages.ACCESS_CONTROL, Messages.COMPLETE_ACCESS)
                .build();

        return response;
    }

    /**
     * Platform Error Builders Messages for authorization failed.
     *
     * @return String
     */
    private String authFailedErrorMessage() {
        LOGGER.error(ErrorMessageEnum.AUTH_FAILED_MESSAGE.getMessage());
        return getContentBuildingError(
                ErrorMessageEnum.AUTH_FAILED_MESSAGE.getMessage(),
                ErrorMessageEnum.AUTH_FAILED_MESSAGE.getCode().toString());
    }

}
