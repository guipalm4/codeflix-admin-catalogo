package com.guipalm4.codeflix.admin.catalogo.domain.category.retrieve.list;

import com.guipalm4.codeflix.admin.catalogo.UseCase;
import com.guipalm4.codeflix.admin.catalogo.domain.category.CategorySearchQuery;
import com.guipalm4.codeflix.admin.catalogo.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
        extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
