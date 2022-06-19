package com.guipalm4.codeflix.admin.catalogo.application.category;

public record CategorySearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}
