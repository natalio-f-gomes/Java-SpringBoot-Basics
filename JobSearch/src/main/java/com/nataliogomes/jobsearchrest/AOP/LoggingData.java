package com.nataliogomes.jobsearchrest.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Aspect
@Component
public class LoggingData {

    private static final String LOG_FILE_PATH = "LogData/logging_data.txt";

    @Before("execution(* com.nataliogomes.jobsearchrest.Controllers.JobController.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String message = String.format("Method '%s' is called with arguments: %s.", methodName, joinPoint.getArgs());
        writeLogToFile(message);
    }

    @After("execution(* com.nataliogomes.jobsearchrest.Controllers.JobController.*(..))")
    public void logMethodExecuted(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String message = String.format("Method '%s' execution completed.", methodName);
        writeLogToFile(message);
    }

    @AfterThrowing(pointcut = "execution(* com.nataliogomes.jobsearchrest.Controllers.JobController.*(..))", throwing = "ex")
    public void logMethodCrashed(JoinPoint jp, Throwable ex) {
        String methodName = jp.getSignature().getName();
        String message = String.format("Method '%s' encountered an exception: %s", methodName, ex.getMessage());
        writeLogToFile(message);
    }

    @AfterReturning(pointcut = "execution(* com.nataliogomes.jobsearchrest.Controllers.JobController.*(..))", returning = "result")
    public void logMethodExecutedSuccess(JoinPoint jp, Object result) {
        String methodName = jp.getSignature().getName();
        String message = String.format("Method '%s' executed successfully and returned: %s", methodName, result);
        writeLogToFile(message);
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
