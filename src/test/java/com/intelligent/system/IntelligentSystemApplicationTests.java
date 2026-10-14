package com.intelligent.system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.datasource.url=jdbc:h2:mem:applicationtests;DB_CLOSE_DELAY=-1")
class IntelligentSystemApplicationTests {

    @Test
    void contextLoads() {
    }
}
