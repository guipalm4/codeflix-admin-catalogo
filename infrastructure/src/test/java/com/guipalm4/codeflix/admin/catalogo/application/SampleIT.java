package com.guipalm4.codeflix.admin.catalogo.application;

import com.guipalm4.codeflix.admin.catalogo.application.category.create.CreateCategoryUseCase;
import com.guipalm4.codeflix.admin.catalogo.infrastructure.category.persistence.CategoryRepository;
import com.guipalm4.codeflix.admin.catalogo.IntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class SampleIT {

    @Autowired
    private CreateCategoryUseCase useCase;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testInjects() {
        Assertions.assertNotNull(useCase);
        Assertions.assertNotNull(categoryRepository);
    }
}
