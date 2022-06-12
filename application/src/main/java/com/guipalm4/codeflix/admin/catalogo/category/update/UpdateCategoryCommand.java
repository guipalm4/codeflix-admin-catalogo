package com.guipalm4.codeflix.admin.catalogo.category.update;

public record UpdateCategoryCommand(
        String id,
        String name,
        String description,
        boolean isActive
) {

    public static UpdateCategoryCommand with(
            final String anId,
            final String aName,
            final String aDescription,
            final boolean isActive) {
        return new UpdateCategoryCommand(anId, aName, aDescription, isActive);
    }
}
