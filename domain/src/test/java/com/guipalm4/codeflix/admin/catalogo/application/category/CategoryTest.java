package com.guipalm4.codeflix.admin.catalogo.application.category;

import com.guipalm4.codeflix.admin.catalogo.application.exceptions.DomainException;
import com.guipalm4.codeflix.admin.catalogo.application.validation.handler.ThrowsValidationHandler;
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

    @Test
    public void givenAValidActiveCategory_whenCallDeactivate_thenReturnInactiveCategory() {

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = false;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, true);

        Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));

        var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var actual = aCategory.deactivate();

        Assertions.assertDoesNotThrow(()-> actual.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId() , actual.getId());
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedDescription, actual.getDescription());
        Assertions.assertEquals(expectedActive, actual.isActive());
        Assertions.assertNotNull(actual.getCreatedAt());
        Assertions.assertTrue(actual.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actual.getDeletedAt());
    }

    @Test
    public void givenAValidInactiveCategory_whenCallDeactivate_thenReturnActiveCategory() {

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, false);

        Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));

        var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getDeletedAt());

        final var actual = aCategory.activate();

        Assertions.assertDoesNotThrow(()-> actual.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId() , actual.getId());
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedDescription, actual.getDescription());
        Assertions.assertEquals(expectedActive, actual.isActive());
        Assertions.assertNotNull(actual.getCreatedAt());
        Assertions.assertTrue(actual.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actual.getDeletedAt());
    }

    @Test
    public void givenAValidParams_whenCallUpdate_thenReturnUpdatedCategory(){
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;

        final var aCategory = Category.newCategory("Filme", "A categoria", true);

        Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));

        var updatedAt = aCategory.getUpdatedAt();
        var createdAt = aCategory.getCreatedAt();

        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var actual = aCategory.update(expectedName, expectedDescription, expectedActive);

        Assertions.assertDoesNotThrow(()-> actual.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId() , actual.getId());
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedDescription, actual.getDescription());
        Assertions.assertEquals(expectedActive, actual.isActive());
        Assertions.assertEquals(createdAt, actual.getCreatedAt());
        Assertions.assertTrue(actual.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actual.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdateToInactive_thenReturnUpdatedCategory(){
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = false;

        final var aCategory = Category.newCategory("Filme", "A categoria", true);

        Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));

        var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var actual = aCategory.update(expectedName, expectedDescription, expectedActive);

        Assertions.assertDoesNotThrow(()-> actual.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId() , actual.getId());
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedDescription, actual.getDescription());
        Assertions.assertEquals(expectedActive, actual.isActive());
        Assertions.assertNotNull(actual.getCreatedAt());
        Assertions.assertTrue(actual.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actual.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdateWithInvalidParams_thenReturnUpdatedCategory(){
        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = false;

        final var aCategory = Category.newCategory("Filme", "A categoria", true);

        Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));

        var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var actual = aCategory.update(expectedName, expectedDescription, expectedActive);

        Assertions.assertEquals(aCategory.getId() , actual.getId());
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedDescription, actual.getDescription());
        Assertions.assertEquals(expectedActive, actual.isActive());
        Assertions.assertNotNull(actual.getCreatedAt());
        Assertions.assertTrue(actual.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actual.getDeletedAt());
    }
}
