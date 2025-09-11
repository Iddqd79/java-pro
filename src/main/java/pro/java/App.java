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
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        Arrays.sort(arrayCopy);
        System.out.println("Третье максимальное число в массиве: " + arrayCopy[arrayCopy.length - 3]);
    }

    private static void getUniqueMax3rdNUmber(int[] array) {
        if (array.length < 3) {
            throw new IllegalArgumentException("array length less than 3");
        }
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        TreeSet<Integer> set = new TreeSet<>();
        Arrays.stream(arrayCopy).forEach(set::add);
        if (set.size() < 3) {
            throw new IllegalArgumentException("unique value count in array less than 3");
        }
        Iterator<Integer> integerIterator = set.descendingIterator();
        integerIterator.next();
        integerIterator.next();
        Integer next = integerIterator.next();
        System.out.println("Третье максимальное число уникальное в массиве: " + next);
    }

    private static void avgAgeOfEngineers(Collection<Employee> employees) {
        employees.stream()
                .filter(employee -> employee.position() == Position.ENGINEER)
                .map(Employee::age)
                .mapToInt(Integer::intValue).average()
                .ifPresentOrElse(age -> System.out.println("Средний возраст инженеров: " + age),
                        () -> System.out.println("В компании нет инженеров, средний возраст их 0"));
    }

    private static void getMaximumLengthWord(Collection<String> words) {
        words.stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingInt(String::length))
                .ifPresentOrElse(word -> System.out.println("Самое длинное слово: " + word),
                        () -> System.out.println("В списке нет слов"));
    }

    private static void getWordRateDictionary(String textLine) {
        Map<String, Integer> collect = Arrays.stream(textLine.toLowerCase().split("\\s+")).collect(Collectors.toMap(Function.identity(), s -> 1, Integer::sum));
        System.out.println("Частота слов в строке" + collect);
    }

    private static void sorted(String textLine) {

        Map<String, Integer> collect = Arrays.stream(textLine.toLowerCase().split("\\s+")).collect(Collectors.toMap(Function.identity(), s -> 1, Integer::sum));
        collect.entrySet().stream()
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
