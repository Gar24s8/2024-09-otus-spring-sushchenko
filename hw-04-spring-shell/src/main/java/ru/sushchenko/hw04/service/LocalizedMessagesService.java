package ru.sushchenko.hw04.service;

public interface LocalizedMessagesService {
    String getMessage(String code, Object... args);
}
