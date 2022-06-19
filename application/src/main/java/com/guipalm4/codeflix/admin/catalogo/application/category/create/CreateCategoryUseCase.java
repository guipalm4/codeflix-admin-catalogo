package com.guipalm4.codeflix.admin.catalogo.application.category.create;

import com.guipalm4.codeflix.admin.catalogo.application.UseCase;
import com.guipalm4.codeflix.admin.catalogo.application.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
    extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {
}
