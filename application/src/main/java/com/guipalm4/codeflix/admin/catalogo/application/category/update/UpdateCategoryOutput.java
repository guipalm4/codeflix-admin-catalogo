package com.guipalm4.codeflix.admin.catalogo.application.category.update;

import com.guipalm4.codeflix.admin.catalogo.application.category.Category;
import com.guipalm4.codeflix.admin.catalogo.application.category.CategoryID;

public record UpdateCategoryOutput(CategoryID id) {

    public static UpdateCategoryOutput from(Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId());
    }
}
