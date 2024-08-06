package com.example.common.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheUtil {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 基本的なオブジェクト（Integer、String、エンティティなど）をキャッシュします。
     *
     * @param key   キャッシュのキー
     * @param value キャッシュする値
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 基本的なオブジェクト（Integer、String、エンティティなど）をキャッシュし、タイムアウトを設定します。
     *
     * @param key      キャッシュのキー
     * @param value    キャッシュする値
     * @param timeout  タイムアウト時間
     * @param timeUnit 時間単位
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * キャッシュの有効期限を設定します。
     *
     * @param key     Redisキー
     * @param timeout タイムアウト時間
     * @return true=設定成功；false=設定失敗
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * キャッシュの有効期限を設定します。
     *
     * @param key     Redisキー
     * @param timeout タイムアウト時間
     * @param unit    時間単位
     * @return true=設定成功；false=設定失敗
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * キャッシュされた基本的なオブジェクトを取得します。
     *
     * @param key キャッシュキー
     * @return キャッシュキーに対応するデータ
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 単一のオブジェクトを削除します。
     *
     * @param key キャッシュのキー
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * コレクション内の複数のオブジェクトを削除します。
     *
     * @param collection 複数のオブジェクト
     * @return 削除されたオブジェクトの数
     */
    public long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * Listデータをキャッシュします。
     *
     * @param key      キャッシュのキー
     * @param dataList キャッシュするListデータ
     * @return キャッシュされたオブジェクトの数
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * キャッシュされたListオブジェクトを取得します。
     *
     * @param key キャッシュのキー
     * @return キャッシュキーに対応するデータのList
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * Setをキャッシュします。
     *
     * @param key     キャッシュキー
     * @param dataSet キャッシュするデータのSet
     * @return キャッシュされたデータのオブジェクト
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * キャッシュされたSetを取得します。
     *
     * @param key キャッシュのキー
     * @return キャッシュキーに対応するSet
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * Mapをキャッシュします。
     *
     * @param key     キャッシュキー
     * @param dataMap キャッシュするデータのMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * キャッシュされたMapを取得します。
     *
     * @param key キャッシュのキー
     * @return キャッシュキーに対応するMap
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * Hashにデータを追加します。
     *
     * @param key   Redisキー
     * @param hKey  Hashキー
     * @param value 値
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * Hash内のデータを取得します。
     *
     * @param key  Redisキー
     * @param hKey Hashキー
     * @return Hash内のオブジェクト
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * Hash内のデータを削除します。
     *
     * @param key  Redisキー
     * @param hKey Hashキー
     */
    public void delCacheMapValue(final String key, final String hKey) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hKey);
    }

    /**
     * 複数のHash内のデータを取得します。
     *
     * @param key   Redisキー
     * @param hKeys Hashキーのコレクション
     * @return Hashオブジェクトのコレクション
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * キャッシュされた基本的なオブジェクトのリストを取得します。
     *
     * @param pattern 文字列プレフィックス
     * @return オブジェクトのコレクション
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

}
