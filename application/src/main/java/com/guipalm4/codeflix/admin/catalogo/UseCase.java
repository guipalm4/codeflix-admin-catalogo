package com.guipalm4.codeflix.admin.catalogo;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);
}
