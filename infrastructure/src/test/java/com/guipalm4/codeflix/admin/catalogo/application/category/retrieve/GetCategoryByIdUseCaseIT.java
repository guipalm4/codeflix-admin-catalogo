package com.guipalm4.codeflix.admin.catalogo.application.category.retrieve;

import com.guipalm4.codeflix.admin.catalogo.IntegrationTest;
import com.guipalm4.codeflix.admin.catalogo.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.guipalm4.codeflix.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity;
import com.guipalm4.codeflix.admin.catalogo.infrastructure.category.persistence.CategoryRepository;
import com.guipalm4.codeflix.admin.domain.category.Category;
import com.guipalm4.codeflix.admin.domain.category.CategoryGateway;
import com.guipalm4.codeflix.admin.domain.category.CategoryID;
import com.guipalm4.codeflix.admin.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

@IntegrationTest
public class GetCategoryByIdUseCaseIT {

    @Autowired
    private GetCategoryByIdUseCase useCase;

    @Autowired
    private CategoryRepository categoryRepository;

    @SpyBean
    private CategoryGateway categoryGateway;

    @Test
    public void givenAValidId_whenCallsGetCategory_shouldReturnCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var expectedId = aCategory.getId().getValue();

        save(aCategory);

        final var actualCategory = useCase.execute(expectedId);

        Assertions.assertEquals(expectedId, actualCategory.id());
        Assertions.assertEquals(expectedName, actualCategory.name());
        Assertions.assertEquals(expectedDescription, actualCategory.description());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.createdAt());
        Assertions.assertEquals(aCategory.getUpdatedAt(), actualCategory.updatedAt());
        Assertions.assertEquals(aCategory.getDeletedAt(), actualCategory.deletedAt());
    }

    @Test
    void givenAnInvalidId_WhenCallsGetCategory_ThenShouldReturnNotFound() {
        final var expectedId = CategoryID.from("123");
        final var expectedErrorMessage = "Category with ID 123 was not found";
        final var expectedErrorsCount = 1;


        final var expectedException = Assertions.assertThrows(
                DomainException.class,
                () -> useCase.execute(expectedId.getValue()));

        Mockito.verify(categoryGateway, times(1)).findById(expectedId);
        Assertions.assertEquals(expectedErrorsCount, expectedException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, expectedException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
        final var expectedErrorMessage = "Gateway error";
        final var expectedId = CategoryID.from("123");

        doThrow(new IllegalStateException(expectedErrorMessage))
                .when(categoryGateway).findById(eq(expectedId));

        final var actualException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedId.getValue())
                                                           );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    private void save(final Category... aCategory) {
        categoryRepository.saveAllAndFlush(
                Arrays.stream(aCategory)
                        .map(CategoryJpaEntity::from)
                        .toList()
                                          );
    }
}
