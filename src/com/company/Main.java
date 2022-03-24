package com.company;

import java.util.*;
import java.util.stream.Collectors;
import static com.company.Education.HIGHER;
import static com.company.Sex.MAN;
import static com.company.Sex.WOMAN;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Антон", "Борис", "Елизавета", "Петр", "Екатерина", "Евгений","Станислав");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println("Общее количество несовершеннолетних - " + count);

        List<String> conscripts =persons.stream()
                .filter(value -> value.getAge() > 18 && value.getAge() < 27 )
                .filter(sex -> sex.getSex() == MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Пофамильный список призывников : " + conscripts);

        List<String> ablebodiedPopulationMan =persons.stream()
                .filter(education -> education.getEducation() == HIGHER)
                .filter(sex -> sex.getSex() == MAN)
                .filter(value -> value.getAge() > 18 && value.getAge() < 65 )
                .map(Person::getFamily)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Работоспособное мужское население : " + ablebodiedPopulationMan);

        List<String> ablebodiedPopulationWoman =persons.stream()
                .filter(education -> education.getEducation() == HIGHER)
                .filter(sex -> sex.getSex() == WOMAN)
                .filter(value -> value.getAge() > 18 && value.getAge() < 60 )
                .map(Person::getFamily)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Работоспособное женское население : " + ablebodiedPopulationWoman);
    }
}
