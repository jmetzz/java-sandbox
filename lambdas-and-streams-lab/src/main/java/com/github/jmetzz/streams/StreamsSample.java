package com.github.jmetzz.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class StreamsSample {

    public static void main(String[] args) {

        List<Person> people = createPeople();

        System.out.println(
            people.stream()
                .filter(person -> person.getGender() == Gender.MALE)
                .filter(person -> person.getAge() > 25)
                .collect(toMap(
                            person -> person.getFirstName()  + " " + person.getLastName(),
                            person -> person
                        )
                )
        );

        System.out.println(
                people.stream()
                        .sorted(Comparator.comparing(person -> person.getAge()))
                        .collect(toList())
        );

        System.out.println(
                people.stream()
                        .collect(groupingBy(Person::getFirstName))
        );

        /*
        Function fusion example: nothing is evaluated until
        the "reduce" terminal function is called, in this case
        the forEach function.
        If it is not called, nothing is done.
         */
        people.stream()
                .filter(person -> person.getGender() == Gender.MALE) // intermediate function
                .filter(person -> person.getAge() >= 30) // intermediate function
                .map(Person::getFirstName) // intermediate function
                .map(String::toUpperCase) // intermediate function
                .forEach(System.out::println); // terminal function


        /*
        Infinite stream vs imperative computing.
        Check how cumbersome and error prone imperative programming is.
        Compare it with the functional version and how easy it is to
        understand the purpose of each instruction.
         */
        System.out.println("Imperative calculation: " + computePrimeSqrtRangeImperative(51, 101));
        System.out.println("Functional calculation: " + computePrimeSqrtRangeFunctional(51, 101));


        // TODO parallel streams
    }

    private static double computePrimeSqrtRangeImperative(int n, int k) {
        int index=  n;
        int count = 0;
        double total = 0.0;

        while(count < k){
            if(isPrime(index)){
                total += Math.sqrt(index);
                count++;
            }
            index++;
        }
        return total;

        /*
        Aspects you have to take into account when during thisk method implementation:
        1. the while condition should be based on < or <= ?
        2. where should I put the increment instruction of count variable?
        3. where should I put the increment instruction of index variable?
        4. return the result
         */

    }

    private static double computePrimeSqrtRangeFunctional(int n, int k){
        return Stream.iterate(n, e -> e + 1)
                .filter(StreamsSample::isPrime)
                .limit(k)
                .map(Math::sqrt)
                .reduce(0.0, Double::sum);
    }


    public static boolean isPrime(int number){
        return number > 1 && IntStream.range(2, number).noneMatch(i -> number % i == 0);
    }


    public static List<Person> createPeople(){
        return Arrays.asList(
          new Person("John", "Doe", Gender.MALE, 25),
          new Person("Isaac", "Newton", Gender.MALE, 50),
          new Person("Isaac", "Asimov", Gender.MALE, 45),
          new Person("Alan", "Turing", Gender.MALE, 30),
          new Person("Ada", "Lovelace", Gender.FEMALE, 30)
        );
    }

}
