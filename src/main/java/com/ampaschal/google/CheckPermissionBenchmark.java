package com.ampaschal.google;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Fork(value = 3)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class CheckPermissionBenchmark {

    @Param({"/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_0.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_1.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_3.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_5.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_10.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_20.json",
        "/home/pamusuo/research/permissions-manager/PPMProfiler/permission_files/permission_file_40.json"
    })
    String permFile;

    @Param({"0", "5", "10", "15", "20"})
    int index;

    Set<String> subjects;

    String resourceToAccess = "/home/pamusuo/research/permissions-manager/rpm-microbenchmark/src/main/java/com/ampaschal/google/files/testfile.txt";

    @Setup
    public void setup() {
        PermissionsManager.setup(permFile, null);

        subjects = getMockSubjectPath(index);
    }

    @Benchmark
    public void measurePermissionVerification() {

        try {
            PermissionsManager.checkPermissionEval(2, 2, resourceToAccess, subjects);
        } catch (IOException e) {
            // Update
        } 
    }

    @Benchmark
    public void measurePermissionVerificationReflection() {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callerName = stackTrace[2].getClassName(); // 2 represents the index of the class calling the class's constructor

//        TODO: Does this skip classes loaded by reflection?
        if (!callerName.startsWith("jdk.internal.loader")) {
            Class<?> permManagerClass;
            try {
                permManagerClass = ClassLoader.getSystemClassLoader().loadClass("com.ampaschal.google.PermissionsManager");
                Method logMethod = permManagerClass.getMethod("checkPermissionEval", int.class, int.class, String.class, Set.class);
                logMethod.invoke(null, 0, 0, resourceToAccess, subjects);
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                // Update
            }
        }
    }

    private static Set<String> getMockSubjectPath(int count) {
        List<String> default_paths = new ArrayList<>();
        // default_paths.add("com.ampaschal.google");
        // default_paths.add("org.apache.tomcat");
        default_paths.add("org.apache.commons");

        Set<String> mockPaths = new HashSet<>();
        for (String path: default_paths) {
            mockPaths.add(path + ".TestClass");

            for (int i = 0; i < count - 1; i++) {
                String classname = path + "_" + i + ".TestClass";
                mockPaths.add(classname);
            }
        }

        return mockPaths;

    }

}
