package com.example.studykotlin.domain.auth.servise

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisService(
    @Autowired
    private var redisTemplate: RedisTemplate<String, String>
) {

    fun save(key:String, value:String, expires:Long){
        redisTemplate.opsForValue().set(key,value,expires)
    }

    fun getValueByKey(key:String): String?{
        return redisTemplate.opsForValue().get(key)
    }

    fun getExpireByKey(key:String): Long?{
        return redisTemplate.getExpire(key)
    }

    fun deleteByKey(key:String){
        redisTemplate.delete(key)
    }




    // 로그인 -> 토큰발급 -> 리프레시를 레디스에 저장 -> 로그아웃 -> 엑세스와 리프레시를 레디스에서 블랙리스트로 지정
    // -> 엑세스 토큰으로 요청 들올시 만약 블랙리스트에 있는 엑세스면 요청막음 -> 리프레시로 재발급 요청 올시 만약 블랙시스트에 지정되어
    //있는 리프레시면 요청 막음.


}