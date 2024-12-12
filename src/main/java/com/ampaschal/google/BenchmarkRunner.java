package com.ampaschal.google;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {
    
    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
               .include(FileReadBenchmark.class.getSimpleName())
               .include(FileWriteBenchmark.class.getSimpleName())
               .include(RuntimeExecBenchmark.class.getSimpleName())
               .include(ThreadStartBenchmark.class.getSimpleName())
               .include(SocketConnectBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
