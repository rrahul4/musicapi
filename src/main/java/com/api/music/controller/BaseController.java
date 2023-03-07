package com.api.music.controller;

import java.util.Set;

public interface BaseController <T> {
	public Set<T> getAll();
	public T getById(Long id);
	public Set<T> getAllByIdList(Set<Long> ids);
	public T add(T obj);
	public T update(T obj);
	public void delete(Long id);
}
