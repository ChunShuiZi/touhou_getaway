package org.thcg.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.thcg.config.GlobalConfiguration;

/**
 * @author Severle
 * @date 2024-01-18 01:14:47
 * @description
 */
@Aspect
public class LogInterceptor {
    @Pointcut("execution(org.thcg.* org.apache.logging.log4j.Logger.fatal(..))")
    public void fatal() {}
    @Pointcut("execution(org.thcg.* org.apache.logging.log4j.Logger.error(..))")
    public void error() {}
    @Pointcut("execution(org.thcg.* org.apache.logging.log4j.Logger.warn(..))")
    public void warn() {}
    @Pointcut("execution(org.thcg.* org.apache.logging.log4j.Logger.info(..))")
    public void info() {}
    @Pointcut("execution(org.thcg.* org.apache.logging.log4j.Logger.debug(..))")
    public void debug() {}
    @Pointcut("execution(org.thcg.* org.apache.logging.log4j.Logger.trace(..))")
    public void trace() {}
    @Around("fatal()")
    public Object interceptFatal(ProceedingJoinPoint point) {
        if (GlobalConfiguration.LogLevel_IsFatal()) {
            try {
                return point.proceed();
            } catch (Throwable _) { }
        }
        return null;
    }
    @Around("error()")
    public Object interceptError(ProceedingJoinPoint point) {
        if (GlobalConfiguration.LogLevel_IsError()) {
            try {
                return point.proceed();
            } catch (Throwable _) { }
        }
        return null;
    }
    @Around("warn()")
    public Object interceptWarn(ProceedingJoinPoint point) {
        if (GlobalConfiguration.LogLevel_IsWarn()) {
            try {
                return point.proceed();
            } catch (Throwable _) { }
        }
        return null;
    }

    @Around("info()")
    public Object interceptInfo(ProceedingJoinPoint point) {
        if (GlobalConfiguration.LogLevel_IsInfo()) {
            try {
                return point.proceed();
            } catch (Throwable _) { }
        }
        return null;
    }

    @Around("debug()")
    public Object interceptDebug(ProceedingJoinPoint point) {
        if (GlobalConfiguration.LogLevel_IsDebug()) {
            try {
                return point.proceed();
            } catch (Throwable _) { }
        }
        return null;
    }

    @Around("trace()")
    public Object interceptTrace(ProceedingJoinPoint point) {
        if (GlobalConfiguration.LogLevel_IsTrace()) {
            try {
                return point.proceed();
            } catch (Throwable _) { }
        }
        return null;
    }
}
