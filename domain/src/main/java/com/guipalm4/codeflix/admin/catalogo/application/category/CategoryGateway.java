package com.guipalm4.codeflix.admin.catalogo.application.category;

import com.guipalm4.codeflix.admin.catalogo.application.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {

    Category create(Category aCategory);

    Category update(Category aCategory);

    Optional<Category> findById(CategoryID anId);

    void deleteById(CategoryID anId);

    Pagination<Category> findAll(CategorySearchQuery aQuery);
}
