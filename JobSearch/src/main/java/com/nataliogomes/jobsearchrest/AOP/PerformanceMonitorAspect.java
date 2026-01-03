package com.nataliogomes.jobsearchrest.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Aspect
@Component
public class PerformanceMonitorAspect {

    private static final String LOG_FILE_PATH = "LogData/performance_logs.txt";

    @Around("execution(* com.nataliogomes.jobsearchrest.Controllers.JobController.*(..))")
    public Object monitorJobControllerPerformance(ProceedingJoinPoint jp) throws Throwable {
        return monitorPerformance(jp, "JobController");
    }

    @Around("execution(* com.nataliogomes.jobsearchrest.services.JobService.*(..))")
    public Object monitorJobServicePerformance(ProceedingJoinPoint jp) throws Throwable {
        return monitorPerformance(jp, "JobService");
    }

    @Around("execution(* com.nataliogomes.jobsearchrest.repositories.JobRepo.*(..))")
    public Object monitorJobRepoPerformance(ProceedingJoinPoint jp) throws Throwable {
        return monitorPerformance(jp, "JobRepo");
    }

    private Object monitorPerformance(ProceedingJoinPoint jp, String layer) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = jp.proceed();
        long end = System.currentTimeMillis();
        String methodName = jp.getSignature().getName();
        String logMessage = String.format("Time taken by %s method '%s' in %s: %d ms at %s",
                layer, methodName, jp.getSignature().getDeclaringTypeName(), (end - start), LocalDateTime.now());

        writeLogToFile(logMessage);

        return result;
    }

    private void writeLogToFile(String logMessage) {
        try {
            // Ensure the directory exists
            File logFile = new File(LOG_FILE_PATH);
            File directory = logFile.getParentFile();

            if (directory != null && !directory.exists()) {
                boolean dirsCreated = directory.mkdirs();
                if (dirsCreated) {
                    System.out.println("Directories created: " + directory.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directories: " + directory.getAbsolutePath());
                }
            } else if (directory != null) {
                System.out.println("Directories already exist: " + directory.getAbsolutePath());
            }

            // Append log to the file
            try (FileWriter fw = new FileWriter(logFile, true);
                 PrintWriter pw = new PrintWriter(fw)) {
                pw.println(logMessage);
                System.out.println("Log written to file: " + logFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
