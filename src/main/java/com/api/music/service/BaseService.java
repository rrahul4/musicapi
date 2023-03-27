package com.api.music.service;

import java.util.Set;

import org.springframework.data.domain.Page;

import com.api.music.model.Genre;


public interface BaseService<T> {
	public Page<T> getAll(int pageno);
	public T getById(long id);
	public Set<T> getByIds(Set<Long> ids);
	public T update(T obj);
	public void delete(long id);
	public T add(T obj);
}
