package com.book.catalogservice.service;

import com.book.catalogservice.entity.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
}
