package com.guipalm4.codeflix.admin.catalogo.application.category.update;

import com.guipalm4.codeflix.admin.catalogo.domain.category.Category;
import com.guipalm4.codeflix.admin.catalogo.domain.category.CategoryID;

public record UpdateCategoryOutput(CategoryID id) {

    public static UpdateCategoryOutput from(Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId());
    }
}
