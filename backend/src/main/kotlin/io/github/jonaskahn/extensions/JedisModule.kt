package io.github.jonaskahn.extensions

import io.jooby.Extension
import io.jooby.Jooby
import redis.clients.jedis.JedisPooled

class JedisModule : Extension {

    override fun install(application: Jooby) {
        val redisHost = application.config.getString("redis.host")
        val redisPort = application.config.getInt("redis.port")
        val redis = JedisPooled(redisHost, redisPort)
        val registry = application.services
        registry.put(JedisPooled::class.java, redis)
        application.onStop(redis::close)
    }
}