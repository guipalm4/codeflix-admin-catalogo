package com.guipalm4.codeflix.admin.catalogo.application.category.update;

import com.guipalm4.codeflix.admin.catalogo.application.UseCase;
import com.guipalm4.codeflix.admin.catalogo.application.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
    extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
