package ru.geekbrains.ExceptionClass;

public class WriteToFileException extends RuntimeException {
    public WriteToFileException(String message) {
        super(message);
    }
}
