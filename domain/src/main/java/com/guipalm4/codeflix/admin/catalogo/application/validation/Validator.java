package com.guipalm4.codeflix.admin.catalogo.application.validation;

public abstract class Validator {

    private final ValidationHandler handler;

    protected Validator(ValidationHandler handler) {
        this.handler = handler;
    }

    public abstract void validate();

    protected ValidationHandler validationHandler() {
        return this.handler;
    }
}
