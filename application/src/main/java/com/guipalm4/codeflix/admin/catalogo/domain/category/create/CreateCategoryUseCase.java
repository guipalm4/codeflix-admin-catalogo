package com.guipalm4.codeflix.admin.catalogo.domain.category.create;

import com.guipalm4.codeflix.admin.catalogo.UseCase;
import com.guipalm4.codeflix.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
    extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {
}
