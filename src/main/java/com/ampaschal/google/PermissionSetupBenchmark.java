package com.ampaschal.google;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(value = 3)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class PermissionSetupBenchmark {

    @Param({"/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_0.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_1.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_3.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_5.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_10.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_20.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_40.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_100.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_500.json"
    })
    String permFile;

    @Benchmark
    public void measurePermissionSetup() {

        PermissionsManager.setup(permFile, null);
    }

}
