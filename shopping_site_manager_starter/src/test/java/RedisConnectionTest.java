import com.example.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class RedisConnectionTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedisConnection() {
        // Set a value in Redis
        stringRedisTemplate.opsForValue().set("testKey", "testValue");

        // Get the value from Redis
        String value = stringRedisTemplate.opsForValue().get("testKey");

        // Assert that the value is correct
        assertThat(value).isEqualTo("testValue");
    }
}