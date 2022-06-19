package com.guipalm4.codeflix.admin.catalogo.application.category.retrieve.list;

import com.guipalm4.codeflix.admin.catalogo.application.UseCase;
import com.guipalm4.codeflix.admin.catalogo.application.category.CategorySearchQuery;
import com.guipalm4.codeflix.admin.catalogo.application.pagination.Pagination;

public abstract class ListCategoriesUseCase
        extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
