package com.guipalm4.codeflix.admin.catalogo;

import com.guipalm4.codeflix.admin.catalogo.category.Category;

public class UseCase {

    public Category execute() {
        return Category.newCategory("Filmes", null, true);
    }
}
