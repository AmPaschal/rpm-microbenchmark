package com.ampaschal.google;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Fork;
import java.util.concurrent.TimeUnit;

@Fork(value = 3)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class SocketConnectBenchmark {

    SocketAddress address;

    @Setup
    public void setup() {
        address = new InetSocketAddress("localhost", 8080);
    }

    @Benchmark
    public void measureSocketConnect() {
        try (Socket socket = new Socket()) {
            socket.connect(address);
        } catch (IOException ex) {
            // Nothing
        }
    }
}
