package pro.java;

import pro.java.clazz.TestCorrect;
import pro.java.clazz.TestMoreThenSingleAnnotationOnStaticMethod;

/**
 * Home task-1
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        TestRunner testRunner = new TestRunner();

        testRunner.runTests(TestCorrect.class);
        testRunner.runTests(TestMoreThenSingleAnnotationOnStaticMethod.class);

    }
}
