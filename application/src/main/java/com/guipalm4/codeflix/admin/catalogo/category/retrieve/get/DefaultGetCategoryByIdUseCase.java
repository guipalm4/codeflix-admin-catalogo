package com.guipalm4.codeflix.admin.catalogo.category.retrieve.get;

import com.guipalm4.codeflix.admin.catalogo.category.CategoryGateway;
import com.guipalm4.codeflix.admin.catalogo.category.CategoryID;
import com.guipalm4.codeflix.admin.catalogo.exceptions.DomainException;
import com.guipalm4.codeflix.admin.catalogo.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase{

    private final CategoryGateway gateway;

    public DefaultGetCategoryByIdUseCase(final CategoryGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public CategoryOutput execute(String anId) {
        final var aCategoryID = CategoryID.from(anId);

        return gateway.findById(aCategoryID)
                .map(CategoryOutput::from)
                .orElseThrow(notFound(aCategoryID));
    }

    private Supplier<DomainException> notFound(final CategoryID anID) {
        return () -> DomainException.with(
                new Error("Category with ID %s was not found".formatted(anID.getValue()))
        );
    }
}
