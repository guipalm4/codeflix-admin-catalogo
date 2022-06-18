package com.guipalm4.codeflix.admin.catalogo.domain.category.retrieve.get;

import com.guipalm4.codeflix.admin.catalogo.domain.category.Category;

import java.time.Instant;

public record CategoryOutput(
        String id,
        String name,
        String description,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static CategoryOutput from(Category aCategory) {
        return new CategoryOutput(
                aCategory.getId().getValue(),
                aCategory.getName(),
                aCategory.getDescription(),
                aCategory.isActive(),
                aCategory.getCreatedAt(),
                aCategory.getUpdatedAt(),
                aCategory.getDeletedAt()
        );
    }
}
