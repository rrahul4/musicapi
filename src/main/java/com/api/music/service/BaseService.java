package com.api.music.service;

import java.util.Set;


public interface BaseService<T> {
	public Set<T> getAll();
	public T getById(long id);
	public Set<T> getByIds(Set<Long> ids);
	public T update(T obj);
	public void delete(long id);
	public T add(T obj);
}
