package com.guipalm4.codeflix.admin.catalogo.category.retrieve.list;

import com.guipalm4.codeflix.admin.catalogo.category.CategoryGateway;
import com.guipalm4.codeflix.admin.catalogo.category.CategorySearchQuery;
import com.guipalm4.codeflix.admin.catalogo.pagination.Pagination;

import java.util.Objects;

public class DefaultListCategoriesUseCase extends ListCategoriesUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultListCategoriesUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Pagination<CategoryListOutput> execute(final CategorySearchQuery aQuery) {
        return this.categoryGateway.findAll(aQuery)
                .map(CategoryListOutput::from);
    }
}
