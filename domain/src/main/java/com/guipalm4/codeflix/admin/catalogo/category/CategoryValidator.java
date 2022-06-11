package com.guipalm4.codeflix.admin.catalogo.category;

import com.guipalm4.codeflix.admin.catalogo.validation.Error;
import com.guipalm4.codeflix.admin.catalogo.validation.ValidationHandler;
import com.guipalm4.codeflix.admin.catalogo.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkName();
    }

    private void checkName() {

        var name = this.category.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }
        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        var length = name.trim().length();
        if (length > 255 || length < 3) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
