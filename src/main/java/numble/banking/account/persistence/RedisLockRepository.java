package numble.banking.account.persistence;

import lombok.RequiredArgsConstructor;
import numble.banking.account.application.LockRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisLockRepository implements LockRepository {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public Boolean lock(String key) {
        return redisTemplate.opsForValue()
            .setIfAbsent(key, "lock", Duration.ofMillis(3_000));
    }

    @Override
    public Boolean unLock(String key) {
        return redisTemplate.delete(key);
    }

}
