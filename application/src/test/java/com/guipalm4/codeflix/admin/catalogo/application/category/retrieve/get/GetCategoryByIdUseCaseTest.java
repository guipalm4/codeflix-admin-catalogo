package com.guipalm4.codeflix.admin.catalogo.application.category.retrieve.get;

import com.guipalm4.codeflix.admin.catalogo.domain.category.Category;
import com.guipalm4.codeflix.admin.catalogo.domain.category.CategoryGateway;
import com.guipalm4.codeflix.admin.catalogo.domain.category.CategoryID;
import com.guipalm4.codeflix.admin.catalogo.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetCategoryByIdUseCaseTest {

    @InjectMocks
    private DefaultGetCategoryByIdUseCase useCase;

    @Mock
    private CategoryGateway gateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    void givenAValidId_WhenCallsGetCategory_ThenReturnCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedActive);
        final var expectedId = aCategory.getId();

        when(gateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(Category.clone(aCategory)));

        final var actualCategory = useCase.execute(expectedId.getValue());

        Mockito.verify(gateway, times(1)).findById(expectedId);
        Assertions.assertEquals(expectedId.getValue(), actualCategory.id());
        Assertions.assertEquals(expectedName, actualCategory.name());
        Assertions.assertEquals(expectedDescription, actualCategory.description());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.createdAt());
        Assertions.assertEquals(aCategory.getUpdatedAt(), actualCategory.updatedAt());
        Assertions.assertEquals(aCategory.getDeletedAt(), actualCategory.deletedAt());
    }

    @Test
    void givenAnInvalidId_WhenCallsGetCategory_ThenShouldReturnNotFound() {
        final var expectedId = CategoryID.from("123");
        final var expectedErrorMessage = "Category with ID 123 was not found";
        final var expectedErrorsCount = 1;

        when(gateway.findById(eq(expectedId)))
                .thenReturn(Optional.empty());

        final var expectedException = Assertions.assertThrows(
                DomainException.class,
                () -> useCase.execute(expectedId.getValue()));

        Mockito.verify(gateway, times(1)).findById(expectedId);
        Assertions.assertEquals(expectedErrorsCount, expectedException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, expectedException.getErrors().get(0).message());
    }

    @Test
    void givenAValid_WhenGatewayThrowsException_ThenReturnException() {
        final var aCategory = Category.newCategory(
                "Filme", "A Categoria mais assistida", true
        );
        final var expectedId = aCategory.getId();
        final var expectedErrorMessage = "Gateway error";

        when(gateway.findById(expectedId))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(IllegalStateException.class,
                () -> useCase.execute(expectedId.getValue()));

        Mockito.verify(gateway, times(1)).findById(expectedId);
        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
