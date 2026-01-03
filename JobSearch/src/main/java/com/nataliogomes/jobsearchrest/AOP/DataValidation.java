package com.nataliogomes.jobsearchrest.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Aspect
public class DataValidation {

    private static final String LOG_FILE_PATH = "LogData/performance_logs.txt";

    // Validate and update jobId for findById method
    @Around("execution(* com.nataliogomes.jobsearchrest.services.JobService.findById(..)) && args(jobId)")
    public Object validateFindById(ProceedingJoinPoint jp, int jobId) throws Throwable {
        if (jobId < 0) {
            String logMessage = String.format("findById - Job ID is negative, updating it to positive: %d", -jobId);
            writeLogToFile(logMessage);
            jobId = -jobId;
        }
        return jp.proceed(new Object[]{jobId});
    }

    // Validate and update jobId for deleteById method
    @Around("execution(* com.nataliogomes.jobsearchrest.services.JobService.deleteById(..)) && args(jobId)")
    public Object validateDeleteById(ProceedingJoinPoint jp, int jobId) throws Throwable {
        if (jobId < 0) {
            String logMessage = String.format("deleteById - Job ID is negative, updating it to positive: %d", -jobId);
            writeLogToFile(logMessage);
            jobId = -jobId;
        }
        return jp.proceed(new Object[]{jobId});
    }

    // Validate and update jobId for any other methods that require jobId
    @Around("execution(* com.nataliogomes.jobsearchrest.services.JobService.*(..)) && args(jobId)")
    public Object validateGenericJobId(ProceedingJoinPoint jp, int jobId) throws Throwable {
        if (jobId < 0) {
            String logMessage = String.format("Generic - Job ID is negative, updating it to positive: %d", -jobId);
            writeLogToFile(logMessage);
            jobId = -jobId;
        }
        return jp.proceed(new Object[]{jobId});
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
