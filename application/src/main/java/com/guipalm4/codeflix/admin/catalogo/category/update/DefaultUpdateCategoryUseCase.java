package com.guipalm4.codeflix.admin.catalogo.category.update;

import com.guipalm4.codeflix.admin.catalogo.category.Category;
import com.guipalm4.codeflix.admin.catalogo.category.CategoryGateway;
import com.guipalm4.codeflix.admin.catalogo.category.CategoryID;
import com.guipalm4.codeflix.admin.catalogo.category.create.CreateCategoryUseCase;
import com.guipalm4.codeflix.admin.catalogo.exceptions.DomainException;
import com.guipalm4.codeflix.admin.catalogo.validation.Error;
import com.guipalm4.codeflix.admin.catalogo.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.*;

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

