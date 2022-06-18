package com.guipalm4.codeflix.admin.catalogo.domain;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);
}
