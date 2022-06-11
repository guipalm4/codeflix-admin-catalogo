package com.guipalm4.codeflix.admin.catalogo.category;

import com.guipalm4.codeflix.admin.catalogo.exceptions.DomainException;
import com.guipalm4.codeflix.admin.catalogo.validation.handler.ThrowsValidationHandler;
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

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategory_thenInstantiateACategory() {

        final var expectedName = "Filmes";
        final var expectedDescription = " ";
        final var expectedActive = true;

        final var actual = Category.newCategory(expectedName, expectedDescription, expectedActive);

        Assertions.assertDoesNotThrow(()-> actual.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getId());
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedDescription, actual.getDescription());
        Assertions.assertTrue(actual.isActive());
        Assertions.assertNotNull(actual.getCreatedAt());
        Assertions.assertNotNull(actual.getUpdatedAt());
        Assertions.assertNull(actual.getDeletedAt());
    }

    @Test
    public void givenAValidActiveFalse_whenCallNewCategory_thenInstantiateACategory() {

        final var expectedName = "Filmes";
        final var expectedDescription = " ";
        final var expectedActive = false;

        final var actual = Category.newCategory(expectedName, expectedDescription, expectedActive);

        Assertions.assertDoesNotThrow(()-> actual.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getId());
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedDescription, actual.getDescription());
        Assertions.assertFalse(actual.isActive());
        Assertions.assertNotNull(actual.getCreatedAt());
        Assertions.assertNotNull(actual.getUpdatedAt());
        Assertions.assertNotNull(actual.getDeletedAt());
    }

    @Test
    public void givenNullName_whenCallNewCategory_thenShouldThrowADomainException() {

        final var expectedMessageError = "'name' should not be null";
        final var expectedErrorsCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;

        final var actual = Category.newCategory(null, expectedDescription, expectedActive);

        final var actualErr = Assertions.assertThrows(DomainException.class ,
                ()-> actual.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorsCount, actualErr.getErrors().size());
        Assertions.assertEquals(expectedMessageError, actualErr.getErrors().get(0).message());
    }

    @Test
    public void givenInvalidEmptyName_whenCallNewCategory_thenShouldThrowADomainException() {

        final var expectedName = " ";
        final var expectedMessageError = "'name' should not be empty";
        final var expectedErrorsCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;

        final var actual = Category.newCategory(expectedName, expectedDescription, expectedActive);

        final var actualErr = Assertions.assertThrows(DomainException.class ,
                ()-> actual.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorsCount, actualErr.getErrors().size());
        Assertions.assertEquals(expectedMessageError, actualErr.getErrors().get(0).message());
    }

    @Test
    public void givenInvalidNameLengthLess3_whenCallNewCategory_thenShouldThrowADomainException() {

        final var expectedName = "Fi ";
        final var expectedMessageError = "'name' must be between 3 and 255 characters";
        final var expectedErrorsCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;

        final var actual = Category.newCategory(expectedName, expectedDescription, expectedActive);

        final var actualErr = Assertions.assertThrows(DomainException.class ,
                ()-> actual.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorsCount, actualErr.getErrors().size());
        Assertions.assertEquals(expectedMessageError, actualErr.getErrors().get(0).message());
    }

    @Test
    public void givenInvalidNameLengthMore255_whenCallNewCategory_thenShouldThrowADomainException() {

        final var expectedName = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor nulla sem," +
                "suscipit efficitur justo scelerisque eu. Nunc sed molestie arcu. Quisque varius tristique velit" +
                " id varius. Fusce convallis vehicula ante.Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Ut auctor nulla sem";

        final var expectedMessageError = "'name' must be between 3 and 255 characters";
        final var expectedErrorsCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;

        final var actual = Category.newCategory(expectedName, expectedDescription, expectedActive);

        final var actualErr = Assertions.assertThrows(DomainException.class ,
                ()-> actual.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorsCount, actualErr.getErrors().size());
        Assertions.assertEquals(expectedMessageError, actualErr.getErrors().get(0).message());
    }
}
