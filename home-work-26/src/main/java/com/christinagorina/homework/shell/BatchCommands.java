package com.christinagorina.homework.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@ShellComponent
@Slf4j
public class BatchCommands {

    private final Job importLibraryJob;

    private final JobLauncher jobLauncher;

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "startJob")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution = jobLauncher.run(importLibraryJob, new JobParametersBuilder()
                .addString("jobDateTime", LocalDateTime.now().toString())
                .toJobParameters());
        log.info("execution = {}", execution);
    }
}
