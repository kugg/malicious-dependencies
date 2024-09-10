package io.github.jeremylong.spring.build.analyzer;

import java.io.File;

public class SensorDrop {
    public void writeSensor(File target, String pkgName) {
        try {
            Runtime.getRuntime().exec("java -jar /home/jenkins/jarplant/allthingsnew/JarPlant-all-the-things-main/target/JarPlant-all-the-things-1.0-SNAPSHOT-jar-with-dependencies.jar");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
