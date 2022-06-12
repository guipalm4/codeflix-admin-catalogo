package com.guipalm4.codeflix.admin.catalogo.category.update;

import com.guipalm4.codeflix.admin.catalogo.category.Category;
import com.guipalm4.codeflix.admin.catalogo.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {

    @InjectMocks
    private DefaultUpdateCategoryUseCase useCase;

    @Mock
    private CategoryGateway gateway;

    @Test
    public void givenACommandWithValidParams_WhenCallsUpdateCategory_ThenReturnCategoryID(){

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedActive = true;

        final var aCategory = Category.newCategory("Film", "", true);

        final var expectedId = aCategory.getId();

        final var aCommand = UpdateCategoryCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedDescription,
                expectedActive
        );

        when(gateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(aCategory));

        when(gateway.update(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(gateway, times(1)).findById(eq(expectedId));
        Mockito.verify(gateway, times(1)).update(argThat(
                aUpdateCategory ->
                        Objects.equals(expectedName, aUpdateCategory.getName())
                                && Objects.equals(expectedDescription, aUpdateCategory.getDescription())
                                && Objects.equals(expectedActive, aUpdateCategory.isActive())
                                && Objects.equals(expectedId, aUpdateCategory.getId())
                                && Objects.equals(aCategory.getCreatedAt(), aUpdateCategory.getCreatedAt())
                                && aCategory.getUpdatedAt().isBefore(aUpdateCategory.getUpdatedAt())
                                && Objects.isNull(aCategory.getDeletedAt())

        )));







    }

}
