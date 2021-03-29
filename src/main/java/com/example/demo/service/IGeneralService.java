package com.example.demo.service;

import com.example.demo.model.AppUser;

import java.util.List;

public interface IGeneralService<E> {
    List<E> findAll();

    E add(E e);

    boolean remove(E e);

    E update(E e);

    E findById(Long id);
}
