package com.guipalm4.codeflix.admin.catalogo.category.update;

import com.guipalm4.codeflix.admin.catalogo.UseCase;
import com.guipalm4.codeflix.admin.catalogo.category.create.CreateCategoryCommand;
import com.guipalm4.codeflix.admin.catalogo.category.create.CreateCategoryOutput;
import com.guipalm4.codeflix.admin.catalogo.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
    extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
