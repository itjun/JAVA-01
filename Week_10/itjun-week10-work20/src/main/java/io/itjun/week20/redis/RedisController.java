package io.itjun.week20.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    StringRedisTemplate redisTemplate;

    // wrk -t1 -c1 -d1s http://127.0.0.1:8080/redis/incr
    @GetMapping("incr")
    public Long incr() {
        long value = redisTemplate.opsForValue().increment("911001.OC.2021-01-30");
        return value;
    }

    @GetMapping("set/{key}/{value}")
    public String set(@PathVariable("key") String key, @PathVariable("value") String value) {
        redisTemplate.opsForValue().set(key, value);
        return key + "," + value;
    }

    @GetMapping("setex/{key}/{value}/{timeout}")
    public String setex(@PathVariable("key") String key, @PathVariable("value") String value,
                        @PathVariable("timeout") long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        return key + "," + value + "," + timeout;
    }

    @GetMapping("get/{key}")
    public String get(@PathVariable("key") String key) {
        return "key=" + key + ",value=" + redisTemplate.opsForValue().get(key);
    }

    @GetMapping("delete/{key}")
    public Boolean delete(@PathVariable("key") String key) {
        return redisTemplate.delete(key);
    }

    @GetMapping("keys/{pattern}")
    public Set<String> list(@PathVariable("pattern") String pattern) {
        return redisTemplate.keys(pattern);
    }

    @GetMapping("lpush/{key}/{value}")
    public Long lPush(@PathVariable("key") String key, @PathVariable("value") String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @GetMapping("rpop/{key}")
    public String rPop(@PathVariable("key") String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @GetMapping("ttl/{key}")
    public Long ttl(@PathVariable("key") String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.ttl(key.getBytes()));
    }

    @RequestMapping("pipeline")
    public Long pipeline() {
        long start = System.currentTimeMillis();
        redisTemplate.executePipelined((RedisCallback<List>) connection -> {
            for (int i = 0; i < 100000; i++) {
                String key = "pipeline_" + i;
                String value = "value" + i;
                connection.set(key.getBytes(), value.getBytes());
                connection.get(key.getBytes());
            }
            return null;
        });

        return System.currentTimeMillis() - start;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static void main(String[] args) {
        System.out.println(randInt(5, 10));
    }
}