package com.guipalm4.codeflix.admin.domain.category;

public record CategorySearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}
