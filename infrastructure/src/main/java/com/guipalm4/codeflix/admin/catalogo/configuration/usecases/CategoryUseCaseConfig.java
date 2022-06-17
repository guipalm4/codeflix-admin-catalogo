package com.guipalm4.codeflix.admin.catalogo.configuration.usecases;

import com.guipalm4.codeflix.admin.catalogo.category.CategoryGateway;
import com.guipalm4.codeflix.admin.catalogo.category.create.CreateCategoryUseCase;
import com.guipalm4.codeflix.admin.catalogo.category.create.DefaultCreateCategoryUseCase;
import com.guipalm4.codeflix.admin.catalogo.category.delete.DefaultDeleteCategoryUseCase;
import com.guipalm4.codeflix.admin.catalogo.category.delete.DeleteCategoryUseCase;
import com.guipalm4.codeflix.admin.catalogo.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import com.guipalm4.codeflix.admin.catalogo.category.retrieve.get.GetCategoryByIdUseCase;
import com.guipalm4.codeflix.admin.catalogo.category.retrieve.list.DefaultListCategoriesUseCase;
import com.guipalm4.codeflix.admin.catalogo.category.retrieve.list.ListCategoriesUseCase;
import com.guipalm4.codeflix.admin.catalogo.category.update.DefaultUpdateCategoryUseCase;
import com.guipalm4.codeflix.admin.catalogo.category.update.UpdateCategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryUseCaseConfig {

    private final CategoryGateway categoryGateway;

    public CategoryUseCaseConfig(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }

    @Bean
    public ListCategoriesUseCase listCategoriesUseCase() {
        return new DefaultListCategoriesUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }
}