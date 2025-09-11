package pro.java;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Home task-2
 *
 */
public class App {
    public static void main(String[] args) {
        int[] array1 = {5, 2, 10, 9, 4, 3, 10, 1, 13};
        //Взять третье максимальное по значению число
        //Если по простому не алгоритмами (скользящего окна как на собесах)
        getMax3rdNUmber(array1);
        getUniqueMax3rdNUmber(array1);

        List<Employee> employeeList = new ArrayList<>() {
            {
                add(new Employee("Jack", 20, Position.DIRECTOR));
                add(new Employee("Jam", 20, Position.MANAGER));
                add(new Employee("Bob", 25, Position.MANAGER));
                add(new Employee("Stew", 45, Position.ENGINEER));
                add(new Employee("Janny", 21, Position.ENGINEER));
                add(new Employee("Larry", 38, Position.ENGINEER));
                add(new Employee("John", 53, Position.CLEANER));
            }
        };
        avgAgeOfEngineers(employeeList);
        getMaximumLengthWord(List.of("aaa", "bbbb", "cccc", "dddaaaaad", "eeeae"));
        getWordRateDictionary("a a a b cc     cc  d");
        sorted("a a a b cc     cc  d");
        String[] array2 = {"aaa aa a aaaa a", "b bb b bb bbbb", "c c c c c"};
        getMaximumLengthWordFromCollection(array2);
    }

    private static void getMax3rdNUmber(int[] array) {
        if (array.length < 3) {
            throw new IllegalArgumentException("array length less than 3");
        }
        Arrays.stream(array)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .ifPresent(x -> System.out.println("Третье максимальное число в массиве: " + x));
    }

    private static void getUniqueMax3rdNUmber(int[] array) {
        if (array.length < 3) {
            throw new IllegalArgumentException("array length less than 3");
        }
        Arrays.stream(array)
                .distinct()
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .ifPresent(x -> System.out.println("Третье уникальное максимальное число в массиве: " + x));
    }

    private static void avgAgeOfEngineers(Collection<Employee> employees) {
        Double avgValue = employees.stream()
                .filter(employee -> employee.position() == Position.ENGINEER)
                .map(Employee::age)
                .collect(Collectors.averagingInt(Integer::intValue));
        System.out.println("Средний возраст инженеров: " + avgValue);

    }

    private static void getMaximumLengthWord(Collection<String> words) {
        words.stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingInt(String::length))
                .ifPresentOrElse(word -> System.out.println("Самое длинное слово: " + word),
                        () -> System.out.println("В списке нет слов"));
    }

    private static void getWordRateDictionary(String textLine) {
        Map<String, Integer> collect = Arrays.stream(textLine.toLowerCase().split("\\s+"))
                .collect(Collectors.toMap(Function.identity(), s -> 1, Integer::sum));
        System.out.println("Частота слов в строке" + collect);
    }

    private static void sorted(String textLine) {
        Arrays.stream(textLine.toLowerCase().split("\\s+"))
                .collect(Collectors.toMap(Function.identity(), s -> 1, Integer::sum))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().thenComparing(Map.Entry.comparingByKey()))
                .forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
    }

    private static void getMaximumLengthWordFromCollection(String[] words) {
        Arrays.stream(words)
                .flatMap(textLine -> Arrays.stream(textLine.toLowerCase().split("\\s+")))
                .max(Comparator.comparingInt(String::length))
                .ifPresentOrElse(word -> System.out.println("Самое длинное слово: " + word),
                        () -> System.out.println("В списке нет слов"));
    }
}
