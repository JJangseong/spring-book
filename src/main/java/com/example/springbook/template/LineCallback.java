package com.example.springbook.template;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
