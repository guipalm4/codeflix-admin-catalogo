package com.guipalm4.codeflix.admin.catalogo.application.category.delete;

import com.guipalm4.codeflix.admin.catalogo.application.category.CategoryGateway;
import com.guipalm4.codeflix.admin.catalogo.application.category.CategoryID;

import java.util.Objects;

public class DefaultDeleteCategoryUseCase extends DeleteCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultDeleteCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public void execute(String anIn) {
        categoryGateway.deleteById(CategoryID.from(anIn));
    }
}
