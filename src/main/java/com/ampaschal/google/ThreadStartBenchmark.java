package com.ampaschal.google;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Fork(value = 3)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class ThreadStartBenchmark {

    @Benchmark
    public void measureThreadStart() {

        Thread thread = new Thread(() -> System.out.print(""));

        thread.start();
    }
}
