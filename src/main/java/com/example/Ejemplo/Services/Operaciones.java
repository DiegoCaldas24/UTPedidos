package com.example.Ejemplo.Services;

public interface Operaciones<T> {
    public void add(T data);
    public void update(T data);
    public void delete(int id);
    public T find(int id);
}
