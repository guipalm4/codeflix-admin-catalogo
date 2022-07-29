package com.guipalm4.codeflix.admin.catalogo.application.category.create;

import com.guipalm4.codeflix.admin.domain.category.Category;
import com.guipalm4.codeflix.admin.domain.category.CategoryID;

public record CreateCategoryOutput(CategoryID id) {

    public static CreateCategoryOutput from(Category aCategory) {
        return new CreateCategoryOutput(aCategory.getId());
    }
}
