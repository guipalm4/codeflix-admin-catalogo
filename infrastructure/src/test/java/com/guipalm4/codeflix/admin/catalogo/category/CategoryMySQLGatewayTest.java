package com.guipalm4.codeflix.admin.catalogo.category;

import com.guipalm4.codeflix.admin.catalogo.category.persistence.CategoryMySQLGateway;
import com.guipalm4.codeflix.admin.catalogo.category.persistence.CategoryRepository;
import com.guipalm4.codeflix.admin.catalogo.config.MySQLGatewayTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@MySQLGatewayTest
public class CategoryMySQLGatewayTest {

    @Autowired
    private CategoryMySQLGateway categoryGateway;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testInjectedDependencies() {
        Assertions.assertNotNull(categoryGateway);
        Assertions.assertNotNull(categoryRepository);
    }

}
