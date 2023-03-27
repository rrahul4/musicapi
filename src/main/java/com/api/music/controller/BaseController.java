package com.api.music.controller;

import java.util.Collection;
import java.util.Set;

import org.springframework.http.ResponseEntity;

public interface BaseController <T> {
	public ResponseEntity<Collection<T>> getAll(int pageno);
	public T getById(Long id);
	public ResponseEntity<Collection<T>> getAllByIdList(Set<Long> ids);
	public T add(T obj);
	public T update(T obj);
	public void delete(Long id);
}
