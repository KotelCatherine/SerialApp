package ru.geekbrains;

import ru.geekbrains.PersonData.Person;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Person p = new Person("Ivan", "Ivanov", 20);
        String fileName = p.getClass().getName() + "_" + UUID.randomUUID();

        WritingAndReadingData.writeToFile(p, fileName);
        WritingAndReadingData.readFromFile(fileName);
    }
}
