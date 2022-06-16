package com.guipalm4.codeflix.admin.catalogo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.AbstractEnvironment;

public class MainTest {

    @Test
    public void testMain() {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "tst");
        Assertions.assertNotNull(new Main());
        Main.main(new String[]{});
    }
}
