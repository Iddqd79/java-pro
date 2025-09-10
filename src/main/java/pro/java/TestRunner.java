package pro.java;

import pro.java.annotations.AfterSuite;
import pro.java.annotations.BeforeSuite;
import pro.java.annotations.CsvSource;
import pro.java.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestRunner {
    public void runTests(Class<?> clazz) {
        checkClassHasSingleStaticMethodAnnotatedBy(clazz, BeforeSuite.class);
        checkClassHasSingleStaticMethodAnnotatedBy(clazz, AfterSuite.class);
        try {
            runStaticMethodAnnotatedBy(clazz, BeforeSuite.class);
            runSuiteMethodsAnnotatedByTest(clazz);
            runStaticMethodAnnotatedBy(clazz, AfterSuite.class);
            runCsvSourceMethod(clazz);
        } catch (ReflectiveOperationException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void checkClassHasSingleStaticMethodAnnotatedBy(Class<?> clazz, Class<? extends Annotation> annotation) {
        boolean presentAnnotation = false;
        for (Method method : clazz.getDeclaredMethods()) {
            if (!Modifier.isStatic(method.getModifiers())) {
                continue;
            }
            if (method.isAnnotationPresent(annotation)) {
                continue;
            }
            if (presentAnnotation) {
                throw new RuntimeException("Only one @%s annotation can be specified at once on static method of class".formatted(annotation.getSimpleName()));
            }
            presentAnnotation = true;
        }
    }

    private void runStaticMethodAnnotatedBy(Class<?> clazz, Class<? extends Annotation> annotation) throws ReflectiveOperationException {
        for (Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(annotation)) {
                continue;
            }
            if (method.trySetAccessible()) {
                method.setAccessible(true);
                method.invoke(null);
                return;
            }
            throw new RuntimeException("@%s is not accessible".formatted(annotation.getSimpleName()));

        }

    }

    private void runSuiteMethodsAnnotatedByTest(Class<?> clazz) throws ReflectiveOperationException {
        Object instance = null;
        List<Method> methodList = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .sorted(Comparator.comparing(method -> method.getAnnotation(Test.class).priority()))
                .toList();
        for (Method method : methodList) {
            if (!method.trySetAccessible()) {
                throw new RuntimeException("method annotated @Test is not accessible");
            }
            method.setAccessible(true);
            if (Modifier.isStatic(method.getModifiers())) {
                method.invoke(null);
                continue;
            }
            if (instance == null) {
                instance = newInstance(clazz);
            }
            method.invoke(instance);


        }
    }

    private void runCsvSourceMethod(Class<?> clazz) throws ReflectiveOperationException {
        Object instance = null;
        for (Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(CsvSource.class)) {
                continue;
            }
            if (method.trySetAccessible()) {
                method.setAccessible(true);
                if (!Modifier.isStatic(method.getModifiers())) {
                    try {
                        instance = newInstance(clazz);
                        Object[] annotationParams = getAnnotationParams(method);
                        method.invoke(instance, annotationParams);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException("Class %s hasn't constructor without params".formatted(clazz.getSimpleName()));
                    }
                    continue;
                }
            }
            throw new RuntimeException("@%s is not accessible".formatted(CsvSource.class.getSimpleName()));

        }
    }

    private Object[] getAnnotationParams(Method method) throws NoSuchMethodException, ReflectiveOperationException {
        Parameter[] parameters = method.getParameters();
        CsvSource annotation = method.getAnnotation(CsvSource.class);
        String[] paramsFromAnnotation = annotation.value().split(",");
        if (parameters.length != paramsFromAnnotation.length) {
            throw new IllegalArgumentException("@CsvSource annotation has incorrect number of parameters");
        }
        Object[] annotationParams = new Object[paramsFromAnnotation.length];
        for (int i = 0; i < paramsFromAnnotation.length; i++) {
            if (parameters[i].getType().equals(String.class)) {
                annotationParams[i] = paramsFromAnnotation[i];
                continue;
            }
            annotationParams[i] = parameters[i].getType().getDeclaredMethod("valueOf", String.class).invoke(null, paramsFromAnnotation[i]);
        }
        return annotationParams;
    }

    private Object newInstance(Class<?> clazz) throws ReflectiveOperationException {
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        return declaredConstructor.newInstance();
    }

}
