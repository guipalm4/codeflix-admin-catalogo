package com.guipalm4.codeflix.admin.catalogo.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;

        final var actual = Category.newCategory(expectedName, expectedDescription, expectedActive);

        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getId());
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedDescription, actual.getDescription());
        Assertions.assertTrue(actual.isActive());
        Assertions.assertNotNull(actual.getCreatedAt());
        Assertions.assertNotNull(actual.getUpdatedAt());
        Assertions.assertNull(actual.getDeletedAt());
    }
}
