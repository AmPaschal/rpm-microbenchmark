package com.ampaschal.google;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Fork(value = 3)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class RuntimeExecBenchmark {

    @Param({"ls -l", "whoami"})
    String command;

    @Benchmark
    public void measureShellExec() {

        Runtime runtime = Runtime.getRuntime();

        try {
            runtime.exec(command);
        } catch (IOException e) {
            // Handle
        }
    }
}
