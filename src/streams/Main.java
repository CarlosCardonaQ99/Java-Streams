package streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = getPeople();

        //Filter
        List<Person> getFemales = people.stream()
                .filter(person -> person.getGender()
                        .equals(Gender.FEMALE))
                .collect(Collectors.toList());
        getFemales.forEach(System.out::println);

        //sorted
        List<Person> sortedByAge = people.stream()
                .sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getGender)
                        .reversed())
                .collect(Collectors.toList());
        sortedByAge.forEach(System.out::println);

        //allMatch
        boolean allMatch = people.stream().allMatch(person -> person.getAge() > 12);
         System.out.println(allMatch);

        //anyMatch alguna persona que tenga una edad > 119 yes, la viejita Magola
        boolean anyMatch = people.stream().anyMatch(person -> person.getAge() > 119);
         System.out.println(anyMatch);

        //noneMatch consultar si no hay ninguno, si no hay, retorna true
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Magola"));
          System.out.println(noneMatch);

        //max
        people.stream().max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        people.stream().min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        //group, listar por separado tanto hombres como mujeres
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));
        groupByGender.forEach(((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        }));

        //Buscar a la mujer más anciana y devolver sólo su nombre
        Optional<String> oldestFemale = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(person -> person.getName());
        oldestFemale.ifPresent(System.out::println);
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Carlos Cardona ", 20, Gender.MALE),
                new Person("Desarrolladora Junior con 10 años de exp ", 11, Gender.FEMALE),
                new Person("Patricia Lora", 33, Gender.FEMALE),
                new Person("Camila Gutiérrez", 57, Gender.FEMALE),
                new Person("Alex", 14, Gender.MALE),
                new Person("Jaime Quintero", 99, Gender.MALE),
                new Person("Yuliana Escobar", 23, Gender.FEMALE),
                new Person("Magola", 120, Gender.FEMALE)
        );
    }
}