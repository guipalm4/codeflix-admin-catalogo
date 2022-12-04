package com.guipalm4.codeflix.admin.catalogo.application.category.update;

import com.guipalm4.codeflix.admin.catalogo.domain.category.Category;
import com.guipalm4.codeflix.admin.catalogo.domain.category.CategoryGateway;
import com.guipalm4.codeflix.admin.catalogo.domain.category.CategoryID;
import com.guipalm4.codeflix.admin.catalogo.domain.exceptions.DomainException;
import com.guipalm4.codeflix.admin.catalogo.domain.validation.Error;
import com.guipalm4.codeflix.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(final UpdateCategoryCommand aCommand) {
        final var anID = CategoryID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var aCategory = this.categoryGateway.findById(anID)
                .orElseThrow(notFound(anID));

        final var notification = Notification.create();

        aCategory
                .update(aName,aDescription, isActive)
                .validate(notification);

        return notification.hasError() ? Left(notification) : update(aCategory);
    }

    private Supplier<DomainException> notFound(final CategoryID anID) {
        return () -> DomainException.with(
                new Error("Category with ID %s was not found".formatted(anID.getValue()))
        );
    }

    private Either<Notification, UpdateCategoryOutput> update(final Category aCategory) {
        return Try(() -> this.categoryGateway.update(aCategory))
                .toEither()
                .bimap(Notification::create, UpdateCategoryOutput::from);
    }

}

