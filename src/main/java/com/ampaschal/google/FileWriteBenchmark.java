package com.ampaschal.google;

import org.openjdk.jmh.annotations.*;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Fork(value = 3)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class FileWriteBenchmark {

    String outputFileName = "/home/pamusuo/research/permissions-manager/rpm-microbenchmark/src/main/java/com/ampaschal/google/files/outputFileName.txt";

    @Param({"/home/pamusuo/research/permissions-manager/rpm-microbenchmark/src/main/java/com/ampaschal/google/files/testfile.txt",
            "/home/pamusuo/research/permissions-manager/rpm-microbenchmark/src/main/java/com/ampaschal/google/files/testfilecontents.txt"})
    String inputFileName;

    String content;

    @Setup
    public void setup() {
        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(inputFileName));
            content = reader.readLine();
            reader.close();
        } catch (IOException e) {
            // Handle the exception or rethrow it if needed
        }
    }


    @Benchmark
    public void measureFileWrite() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            writer.write(content);
            writer.close();
        } catch (IOException ex) {
            // Nothing
        }
    }
}
