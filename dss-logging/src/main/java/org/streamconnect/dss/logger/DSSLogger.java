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

import org.streamconnect.dss.enums.ErrorMessageEnum;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * There are requirements to create separate log files based on some conditions
 * , apart from root logger files . For Example, we want to log each logged in
 * user's activity in its own separate file. So , user1 will have user1.log and
 * each time if the user1 logged in to the system , his activity will be logged
 * in user1.log also these log files will be created in the same folder as the
 * root logger folder
 *
 * @version 1.0
 * @see org.apache.log4j.Logger
 */
public final class DSSLogger {

    /** The ecm logger string. */
    private static String dssLoggerString = DSSLogger.class.getName();

    /** The Jdk version. */
    private static boolean jdkVersion = false;

    /** The logger. */
    private org.apache.log4j.Logger logger;

    /** The class name. */
    private String className = null;

    /** The root appender. */
    private org.apache.log4j.Logger rootAppender;

    /** The DSS logger. */
    private static DSSLogger dssLogger;

    /**
     *
     */
    static {
        String javaVersion = System.getProperty("java.version");
        if (javaVersion != null) {
            jdkVersion = javaVersion.startsWith("1.4");
        }

        ClassLoader contextClassLoader = Thread.currentThread()
                .getContextClassLoader();
        URL resource = contextClassLoader.getResource(StaticResource.RESOURCE);
        if (null == resource) {
            System.out.println(
                    "WARNING - Unable to find " + StaticResource.RESOURCE
                            + " in the classpath. The logging feature may not work "
                            + "properly.");
        } else {
            DOMConfigurator.configure(resource.getFile());
        }
    }

    /**
     * Instantiates a new DSS logger.
     */
    private DSSLogger() {
    }

    /**
     * Instantiates a new DSS logger.
     *
     * @param loggerName
     *            the logger name
     */
    private DSSLogger(final String loggerName) {
        this.className = loggerName;
        this.logger = org.apache.log4j.Logger.getLogger(loggerName);
        org.apache.log4j.Logger.getRootLogger();
        this.rootAppender = org.apache.log4j.Logger.getLogger(loggerName);

    }

    /**
     * Instantiates a new DSS logger.
     *
     * @param clazz
     *            the clazz
     */
    private DSSLogger(final Class clazz) {
        this(clazz.getName());
    }

    /**
     * Gets the logger.
     *
     * @param <T>
     *            the generic type
     * @param class1
     *            the class 1
     * @return DSSLogger
     */
    public static <T> DSSLogger getLogger(final Class<T> class1) {
        if (dssLogger == null) {
            synchronized (DSSLogger.class) {
                if (dssLogger == null) {
                    dssLogger = new DSSLogger(class1);
                }
            }
        }
        return dssLogger;
    }

    /**
     * Gets the logger.
     *
     * @param loggerName
     *            the logger name
     * @return DSSLogger
     */
    public static DSSLogger getLogger(final String loggerName) {
        if (dssLogger == null) {
            synchronized (DSSLogger.class) {
                if (dssLogger == null) {
                    dssLogger = new DSSLogger(loggerName);
                }
            }
        }
        return dssLogger;
    }

    /**
     * Debug.
     *
     * @param instanceId
     *            the instance id
     * @param t
     *            the t
     * @param errorMessageEnum
     *            the error message enum
     */
    public void debug(final String instanceId, final Throwable t,
                      final ErrorMessageEnum errorMessageEnum) {
        LoggingBean loggingBean = createLoggerBean(instanceId, t,
                errorMessageEnum.getMessage());
        logger.log(Level.DEBUG, loggingBean, t);
        logger.log(Level.DEBUG, loggingBean);
    }

    /**
     * Info.
     *
     * @param instanceId
     *            the instance id
     * @param t
     *            the t
     * @param errorMessageEnum
     *            the error message enum
     */
    public void info(final String instanceId, final Throwable t,
                     final ErrorMessageEnum errorMessageEnum) {
        LoggingBean loggingBean = createLoggerBean(instanceId, t,
                errorMessageEnum.getMessage());
        logger.log(Level.INFO, loggingBean, t);
        logger.log(Level.INFO, loggingBean);
    }

    /**
     * Error.
     *
     * @param instanceId
     *            the instance id
     * @param t
     *            the t
     * @param errorMessageEnum
     *            the error message enum
     */
    public void error(final String instanceId, final Throwable t,
                      final ErrorMessageEnum errorMessageEnum) {
        LoggingBean loggingBean = createLoggerBean(instanceId, t,
                errorMessageEnum.getMessage());
        logger.log(Level.ERROR, loggingBean, t);
        logger.log(Level.ERROR, loggingBean);
    }

    /**
     * Warn.
     *
     * @param instanceId
     *            the instance id
     * @param t
     *            the t
     * @param errorMessageEnum
     *            the error message enum
     */
    public void warn(final String instanceId, final Throwable t,
                     final ErrorMessageEnum errorMessageEnum) {
        LoggingBean loggingBean = createLoggerBean(instanceId, t,
                errorMessageEnum.getMessage());
        logger.log(Level.WARN, loggingBean, t);
        logger.log(Level.WARN, loggingBean);
    }

    /**
     * Trace.
     *
     * @param instanceId
     *            the instance id
     * @param t
     *            the t
     * @param errorMessageEnum
     *            the error message enum
     */
    public void trace(final String instanceId, final Throwable t,
                      final ErrorMessageEnum errorMessageEnum) {
        LoggingBean loggingBean = createLoggerBean(instanceId, t,
                errorMessageEnum.getMessage());
        logger.log(Level.TRACE, loggingBean, t);
        logger.log(Level.TRACE, loggingBean);
    }

    /**
     * The <code>TRACE</code> Level designates finer-grained informational
     * events than the <code/>. Uasage
     *
     * @param message
     *            the message
     */
    public void trace(final Object message) {
        String logMessage = createLoggingMessage(message.toString(), null);
        logger.log(Level.TRACE, logMessage);
    }

    /**
     * The <code>TRACE</code> Level designates finer-grained informational
     * events than the <code/>. Usage with exception
     *
     * @param message
     *            the message
     * @param exception
     *            the exception
     */
    public void trace(final Object message, final Throwable exception) {
        String logMessage = createLoggingMessage(message.toString(), exception);
        logger.log(Level.TRACE, logMessage, exception);
    }

    /**
     * Debug.
     *
     * @param message
     *            the message
     */
    public void debug(final Object message) {
        String logMessage = createLoggingMessage(message.toString(), null);
        logger.log(Level.DEBUG, logMessage);
    }

    /**
     * The <code>DEBUG</code> Level designates finer-grained informational
     * events than the <code/>. Uasage with exception
     *
     * @param message
     *            the message
     * @param exception
     *            the exception
     */
    public void debug(final Object message, final Throwable exception) {
        String logMessage = createLoggingMessage(message.toString(), exception);
        logger.log(Level.DEBUG, logMessage, exception);

    }

    /**
     * Info.
     *
     * @param message
     *            the message
     */
    public void info(final Object message) {
        String logMessage = createLoggingMessage(message.toString(), null);
        logger.log(Level.INFO, logMessage);

    }

    /**
     * The <code>INFO</code> Level designates finer-grained informational events
     * than the <code/>. Uasage with exception
     *
     * @param message
     *            the message
     * @param exception
     *            the exception
     */
    public void info(final Object message, final Exception exception) {
        String logMessage = createLoggingMessage(message.toString(), exception);
        logger.log(Level.INFO, logMessage, exception);

    }

    /**
     * Warn.
     *
     * @param message
     *            the message
     */
    public void warn(final Object message) {
        String logMessage = createLoggingMessage(message.toString(), null);
        logger.log(Level.WARN, logMessage);
    }

    /**
     * The <code>WARN</code> Level designates finer-grained informational events
     * than the <code/>. Uasage with exception
     *
     * @param message
     *            the message
     * @param exception
     *            the exception
     */
    public void warn(final Object message, final Exception exception) {
        String logMessage = createLoggingMessage(message.toString(), exception);
        logger.log(Level.WARN, logMessage, exception);

    }

    /**
     * Error.
     *
     * @param message
     *            the message
     */
    public void error(final Object message) {
        String logMessage = createLoggingMessage(message.toString(), null);
        logger.log(Level.ERROR, logMessage);

    }

    /**
     * The <code>ERROR</code> Level designates finer-grained informational
     * events than the <code/>. Uasage with exception
     *
     * @param message
     *            the message
     * @param exception
     *            the exception
     */
    public void error(final Object message, final Throwable exception) {
        String logMessage = createLoggingMessage(message.toString(), exception);
        logger.log(Level.ERROR, logMessage, exception);

    }

    /**
     * Fatal.
     *
     * @param message
     *            the message
     */
    public void fatal(final Object message) {
        String logMessage = createLoggingMessage(message.toString(), null);
        logger.log(Level.FATAL, logMessage);

    }

    /**
     * The <code>FATAL</code> Level designates finer-grained informational
     * events than the <code/>. Uasage with exception
     *
     * @param message
     *            the message
     * @param exception
     *            the exception
     */
    public void fatal(final Object message, final Throwable exception) {
        String logMessage = createLoggingMessage(message.toString(), exception);
        logger.log(Level.FATAL, logMessage, exception);
    }

    /**
     * Creates the logger bean.
     *
     * @param instanceId
     *            the instance id
     * @param t
     *            the t
     * @param errorMessageEnum
     *            the error message enum
     * @return the logging bean
     */
    private LoggingBean createLoggerBean(final String instanceId, final Throwable t,
                                         final String errorMessageEnum) {
        java.net.InetAddress localMachine = null;
        try {
            localMachine = java.net.InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        long timeStamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(timeStamp);
        LoggingBean loggingBean = new LoggingBean(instanceId, errorMessageEnum,
                localMachine.getHostName(), sdf.format(resultdate),
                t.toString(), this.className);
        return loggingBean;
    }

    /**
     * Creates the logging message.
     *
     * @param message
     *            the message
     * @param t
     *            the t
     * @return the string
     */
    private String createLoggingMessage(final String message, final Throwable t) {
        java.net.InetAddress localMachine = null;
        try {
            localMachine = java.net.InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        long timeStamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(timeStamp);

        StringBuilder sb = new StringBuilder();
        sb.append(localMachine.getHostName()).append(StaticResource.SEPARATOR)
                .append(sdf.format(resultdate)).append(StaticResource.SEPARATOR)
                .append(this.className).append(StaticResource.SEPARATOR).append(message);

        if (null != t) {
            sb.append(StaticResource.SEPARATOR).append(t.getMessage());
        }

        return sb.toString();
    }

    /**
     * Checks if is debug enabled.
     *
     * @return true, if is debug enabled
     */
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * Checks if is trace enabled.
     *
     * @return true, if is trace enabled
     */
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    /**
     * Checks if is info enabled.
     *
     * @return true, if is info enabled
     */
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * Log nested exception.
     *
     * @param level
     *            the level
     * @param message
     *            the message
     * @param exception
     *            the exception
     */
    void logNestedException(final Level level, final Object message, final Throwable exception) {
        if (exception == null) {
            return;
        }
        Class<? extends Throwable> class1 = exception.getClass();
        Method[] method = class1.getMethods();
        Method nextThrowableMethod = null;

        for (int i = 0; i < method.length; i++) {
            if (("getCause".equals(method[i].getName()) && !jdkVersion)
                    || "getRootCause".equals(method[i].getName())
                    || "getNextException".equals(method[i].getName())
                    || "getException".equals(method[i].getName())) {
                Class<?>[] params = method[i].getParameterTypes();
                if (params == null || params.length == 0) {
                    nextThrowableMethod = method[i];
                    break;
                }
            }

        }
        if (nextThrowableMethod != null) {
            Throwable next = null;
            try {
                next = (Throwable) nextThrowableMethod.invoke(exception,
                        new Object[0]);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (next != null) {
                this.logger.log(dssLoggerString, level,
                        "Previous log " + "CONTINUED...", next);
            }
        }

    }
}
