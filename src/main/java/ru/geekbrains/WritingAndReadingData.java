package ru.geekbrains;

import ru.geekbrains.ExceptionClass.IncorrectDataException;
import ru.geekbrains.ExceptionClass.WriteToFileException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class WritingAndReadingData {
    private static Path path;

    public WritingAndReadingData() {
    }

    /**
     * Создает файл и делает запись объекта в него
     *
     * @param object   - объект, класс которого имплементирует интерфейс Serializable
     * @param fileName - сгенирированное название файла
     */
    public static void writeToFile(Object object, String fileName) {
        path = Path.of(fileName);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            objectOutputStream.writeObject(object);
        } catch (IOException exception) {
            deleteFile(path);
            throw new WriteToFileException("Ошибка при попытке записать данные в файл, возможно класс объекта не имплементирует Serializable!");
        }
    }

    /**
     * Производит чтение данных из файла, после делает запрос на удаление
     *
     * @param fileName - название файла
     */
    public static void readFromFile(String fileName) {
        path = Path.of(fileName);

        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))) {

            Object o = objectInputStream.readObject();
            if (o != null) {
                System.out.println(o);
            } else {
                deleteFile(path);
                throw new IncorrectDataException("Некорректные данные!");
            }
            deleteFile(path);

        } catch (IOException | ClassNotFoundException exception) {
            String s = exception instanceof IOException ? "Файл не найден!" : "Ошибка при попытке чтения из файла!";
            System.err.println(s);
        }

    }

    /**
     * Удаляет файл
     *
     * @param path - путь файла
     */
    private static void deleteFile(Path path) {
        try {
            Files.delete(path);
        } catch (IOException exception) {
            throw new RuntimeException("Удаление файла невозможно, либо его нет!");
        }
    }

}
