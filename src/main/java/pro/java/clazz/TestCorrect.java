package pro.java.clazz;

import pro.java.annotations.AfterSuite;
import pro.java.annotations.BeforeSuite;
import pro.java.annotations.CsvSource;
import pro.java.annotations.Test;

public class TestCorrect {

    @BeforeSuite
    public static void staticTestBeforeAnnotation() {
        System.out.println("TestMoreThenSingleAnnotationOnStaticMethod call static method staticTestBeforeAnnotation()");
    }
    @AfterSuite
    public static void staticTestAfterAnnotation() {
        System.out.println("TestMoreThenSingleAnnotationOnStaticMethod call static method staticTestAfterAnnotation()");
    }

    @Test(priority = 1)
    public void testPriorityOne() {
        System.out.println("TestMoreThenSingleAnnotationOnStaticMethod call testPriority(1)");
    }

    @Test(priority = 8)
    public void testPriorityEight() {
        System.out.println("TestMoreThenSingleAnnotationOnStaticMethod call testPriority(8)");
    }

    @Test(priority = 10)
    public void testPriorityTen() {
        System.out.println("TestMoreThenSingleAnnotationOnStaticMethod call testPriority(10)");
    }

    @CsvSource("10, Test last, true")
    public void testCsvAnnotation(Integer number, String title, Boolean passed) {
        System.out.printf("#%d, Test: %s, Passed: %b\n", number, title, passed);
    }

}
