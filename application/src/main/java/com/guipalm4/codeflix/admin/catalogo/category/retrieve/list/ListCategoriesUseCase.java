package com.guipalm4.codeflix.admin.catalogo.category.retrieve.list;

import com.guipalm4.codeflix.admin.catalogo.UseCase;
import com.guipalm4.codeflix.admin.catalogo.category.CategorySearchQuery;
import com.guipalm4.codeflix.admin.catalogo.pagination.Pagination;

public abstract class ListCategoriesUseCase
        extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
