package com.guipalm4.codeflix.admin.catalogo.category.update;

import com.guipalm4.codeflix.admin.catalogo.category.Category;
import com.guipalm4.codeflix.admin.catalogo.category.CategoryID;

public record UpdateCategoryOutput(CategoryID id) {

    public static UpdateCategoryOutput from(Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId());
    }
}
