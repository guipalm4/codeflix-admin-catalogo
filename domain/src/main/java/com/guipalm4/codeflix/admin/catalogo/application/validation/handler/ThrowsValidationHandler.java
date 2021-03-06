package com.guipalm4.codeflix.admin.catalogo.application.validation.handler;

import com.guipalm4.codeflix.admin.catalogo.application.exceptions.DomainException;
import com.guipalm4.codeflix.admin.catalogo.application.validation.Error;
import com.guipalm4.codeflix.admin.catalogo.application.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error anError) {

        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(final ValidationHandler aHandler) {
        throw DomainException.with(aHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(Validation aValidation) {
        try{
            aValidation.validate();
        } catch (Exception e) {
            throw DomainException.with(new Error(e.getMessage()));
        }
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
