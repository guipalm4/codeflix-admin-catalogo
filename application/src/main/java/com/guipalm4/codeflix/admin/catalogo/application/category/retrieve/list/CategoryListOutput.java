package com.guipalm4.codeflix.admin.catalogo.application.category.retrieve.list;

import com.guipalm4.codeflix.admin.catalogo.application.category.Category;
import com.guipalm4.codeflix.admin.catalogo.application.category.CategoryID;

import java.time.Instant;

public record CategoryListOutput(
        CategoryID id,
        String name,
        String description,
        boolean isActive,
        Instant createdAt,
        Instant deletedAt
) {

    public static CategoryListOutput from(final Category aCategory) {
        return new CategoryListOutput(
                aCategory.getId(),
                aCategory.getName(),
                aCategory.getDescription(),
                aCategory.isActive(),
                aCategory.getCreatedAt(),
                aCategory.getDeletedAt()
        );
    }
}
