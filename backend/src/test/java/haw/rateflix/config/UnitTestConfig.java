package haw.rateflix.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test configuration that mocks Redis components for unit tests.
 * This allows tests to run without requiring a real Redis instance.
 */
@TestConfiguration
public class UnitTestConfig {

    @Bean
    @Primary
    public RedisConnectionFactory redisConnectionFactory() {
        return mock(RedisConnectionFactory.class);
    }

    @Bean
    @Primary
    @SuppressWarnings("unchecked")
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate template = mock(StringRedisTemplate.class);
        ValueOperations<String, String> valueOps = mock(ValueOperations.class);
        
        // Configure the mock to return the ValueOperations mock
        when(template.opsForValue()).thenReturn(valueOps);
        
        // Configure setIfAbsent to return true (indicating successful set)
        when(valueOps.setIfAbsent(anyString(), anyString())).thenReturn(true);
        
        // Configure other common operations as needed
        when(valueOps.get(anyString())).thenReturn("0");
        when(valueOps.increment(anyString())).thenReturn(1L);
        
        return template;
    }

    @Bean
    @Primary
    public RedisCacheInitializer redisCacheInitializer() {
        // Return a no-op implementation for unit tests
        return new RedisCacheInitializer(null, null) {
            @Override
            public void run(String... args) throws Exception {
                // Do nothing in unit tests
            }
        };
    }
}
