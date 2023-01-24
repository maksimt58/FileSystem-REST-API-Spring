package com.maxisoft.fileSystem.services;

import java.util.List;

public interface GenericService<T, ID> {
    T getById(ID id);

    void delete(ID id);

    T update(T t);

    List<T> getAll();

    T save (T t);
}
