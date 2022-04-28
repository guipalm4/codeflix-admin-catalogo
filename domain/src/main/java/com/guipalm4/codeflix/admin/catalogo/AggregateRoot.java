package com.guipalm4.codeflix.admin.catalogo;

import com.guipalm4.codeflix.admin.catalogo.validation.ValidationHandler;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    protected AggregateRoot(final ID id) {
        super(id);
    }

}
