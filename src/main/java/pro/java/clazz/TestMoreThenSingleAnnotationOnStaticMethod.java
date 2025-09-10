package pro.java.clazz;

import pro.java.annotations.AfterSuite;
import pro.java.annotations.BeforeSuite;
import pro.java.annotations.Test;

public class TestMoreThenSingleAnnotationOnStaticMethod {
    @BeforeSuite
    @AfterSuite
    public static void staticTestBothAnnotation() {
        System.out.println("TestMoreThenSingleAnnotationOnStaticMethod call static method staticTestBothAnnotation()");
    }
    @BeforeSuite
    public static void staticTestBeforeAnnotation() {
        System.out.println("TestMoreThenSingleAnnotationOnStaticMethod call static method staticTestBeforeAnnotation()");
    }
    @AfterSuite
    public static void staticTestAfterAnnotation() {
        System.out.println("TestMoreThenSingleAnnotationOnStaticMethod call static method staticTestAfterAnnotation()");
    }

    @Test(priority = 2)
    public void testPriorityTwo() {
        System.out.println("TestMoreThenSingleAnnotationOnStaticMethod call testPriority(1)");
    }

    @Test(priority = 8)
    public void testPriorityEight() {
        System.out.println("TestMoreThenSingleAnnotationOnStaticMethod call testPriority(8)");
    }

    @Test
    public void testPriorityFive() {
        System.out.println("TestMoreThenSingleAnnotationOnStaticMethod call testPriority(5)");
    }

}
