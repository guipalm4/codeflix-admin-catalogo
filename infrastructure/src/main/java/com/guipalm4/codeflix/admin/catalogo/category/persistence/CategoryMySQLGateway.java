package com.guipalm4.codeflix.admin.catalogo.category.persistence;

import com.guipalm4.codeflix.admin.catalogo.category.Category;
import com.guipalm4.codeflix.admin.catalogo.category.CategoryGateway;
import com.guipalm4.codeflix.admin.catalogo.category.CategoryID;
import com.guipalm4.codeflix.admin.catalogo.category.CategorySearchQuery;
import com.guipalm4.codeflix.admin.catalogo.pagination.Pagination;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryMySQLGateway implements CategoryGateway {

    private final CategoryRepository repository;

    public CategoryMySQLGateway(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(Category aCategory) {
        return null;
    }

    @Override
    public Category update(Category aCategory) {
        return null;
    }

    @Override
    public Optional<Category> findById(CategoryID anId) {
        return Optional.empty();
    }

    @Override
    public void deleteById(CategoryID anId) {

    }

    @Override
    public Pagination<Category> findAll(CategorySearchQuery aQuery) {
        return null;
    }
}
