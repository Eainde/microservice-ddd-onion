package com.eainde.ddd;

public interface Mapper<T, E> {
  T mapToDomain(E entity);

  E mapToEntity(T domain);
}
