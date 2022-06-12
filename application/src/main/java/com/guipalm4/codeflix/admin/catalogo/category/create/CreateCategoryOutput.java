package com.guipalm4.codeflix.admin.catalogo.category.create;

import com.guipalm4.codeflix.admin.catalogo.category.Category;
import com.guipalm4.codeflix.admin.catalogo.category.CategoryID;

public record CreateCategoryOutput(CategoryID id) {

    public static CreateCategoryOutput from(Category aCategory) {
        return new CreateCategoryOutput(aCategory.getId());
    }
}
