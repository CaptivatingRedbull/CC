package haw.rateflix;

import haw.rateflix.categories.UnitTest;
import haw.rateflix.config.UnitTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@UnitTest
@Import(UnitTestConfig.class)
@ActiveProfiles("test")
class RateflixApplicationTests {

    @Test
    void contextLoads() {
    }

}
