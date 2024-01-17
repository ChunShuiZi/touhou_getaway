package org.thcg.config;

/**
 * @author Severle
 * @date 2024-01-18 00:41:58
 * @description
 */
public class GlobalConfiguration {
    public static final int LOG_LEVEL_OFF = 0;
    public static final int LOG_LEVEL_FATAL = 1;
    public static final int LOG_LEVEL_ERROR = 2;
    public static final int LOG_LEVEL_WARN = 3;
    public static final int LOG_LEVEL_INFO = 4;
    public static final int LOG_LEVEL_DEBUG = 5;
    public static final int LOG_LEVEL_TRACE = 6;
    public static final int LOG_LEVEL_ALL = 7;
    public static int LOG_LEVEL = 0;
    public static boolean LogLevel_IsOff() {
        return LOG_LEVEL >= LOG_LEVEL_OFF;
    }
    public static boolean LogLevel_IsFatal() {
        return LOG_LEVEL >= LOG_LEVEL_FATAL;
    }
    public static boolean LogLevel_IsError() {
        return LOG_LEVEL >= LOG_LEVEL_ERROR;
    }
    public static boolean LogLevel_IsWarn() {
        return LOG_LEVEL >= LOG_LEVEL_WARN;
    }
    public static boolean LogLevel_IsInfo() {
        return LOG_LEVEL >= LOG_LEVEL_INFO;
    }
    public static boolean LogLevel_IsDebug() {
        return LOG_LEVEL >= LOG_LEVEL_DEBUG;
    }
    public static boolean LogLevel_IsTrace() {
        return LOG_LEVEL >= LOG_LEVEL_TRACE;
    }
    public static boolean LogLevel_IsAll() {
        return LOG_LEVEL >= LOG_LEVEL_ALL;
    }
}
