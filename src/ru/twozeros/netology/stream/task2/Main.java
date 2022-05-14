package ru.twozeros.netology.stream.task2;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
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
        //Получаем количество несовершеннолетних
        Long countYoung = persons.stream().filter(x -> x.getAge() < 18).count();
        System.out.println(countYoung);
        //список фамилий призывников (т.е. мужчин от 18 и до 27 лет)
        List<String> recruitList = persons.stream().filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() >= 18 && x.getAge() < 27).map(Person::getFamily).toList();
        //System.out.println(recruitList); Их слишком много
        //отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке
        // (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин)
        List<Person> workerList = persons.stream().filter(x -> x.getEducation() == Education.HIGHER)
                .filter(person -> person.getSex() == Sex.MAN ? (person.getAge() >= 18 && person.getAge() < 65) :
                        (person.getAge() >= 18 && person.getAge() < 60)).sorted(Comparator.comparing(Person::getFamily)).toList();
        //System.out.println(workerList); Их слишком много

    }

}
