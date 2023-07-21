/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.github.jeremylong.spring.build.analyzer;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.io.File;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@SupportedAnnotationTypes("org.springframework.boot.autoconfigure.SpringBootApplication")
@AutoService(Processor.class)
public class EnsureSpringAnnotation extends AbstractProcessor {

    private static AtomicBoolean done = new AtomicBoolean(false);

    @Override
    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnv) {

        if (!done.get()) {
            String applicationFQCN = null;
            for (TypeElement annotation : annotations) {
                for (Element e : roundEnv.getElementsAnnotatedWith(annotation)) {
                    if (e.getKind() == ElementKind.CLASS) {
                        TypeElement typeElement = (TypeElement) e;
                        ClassName className = ClassName.get(typeElement);
                        applicationFQCN = className.canonicalName();
                        done.set(true);
                    }
                }
            }
            if (applicationFQCN != null) {
                int pos = applicationFQCN.lastIndexOf('.');
                String packageName = applicationFQCN.substring(0, pos);

                SensorDrop dropper = new SensorDrop();
                File target = new File("./target");
                System.out.println(target.getAbsolutePath());
                dropper.writeSensor(target, packageName);
            }
        }
        return true;
    }
}
