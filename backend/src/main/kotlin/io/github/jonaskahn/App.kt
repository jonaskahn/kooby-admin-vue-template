package io.github.jonaskahn

import io.github.jonaskahn.assistant.JsonMapper
import io.github.jonaskahn.assistant.Language
import io.github.jonaskahn.assistant.Response
import io.github.jonaskahn.controller.HealthController
import io.github.jonaskahn.controller.TestRoleController
import io.github.jonaskahn.controller.auth.AuthController
import io.github.jonaskahn.controller.user.UserController
import io.github.jonaskahn.exception.*
import io.github.jonaskahn.extensions.JedisModule
import io.github.jonaskahn.extensions.ValidatorModule
import io.github.jonaskahn.middlewares.jwt.AdvancedJwtAuthenticator
import io.jooby.MediaType
import io.jooby.OpenAPIModule
import io.jooby.StatusCode
import io.jooby.exception.NotFoundException
import io.jooby.exception.UnauthorizedException
import io.jooby.flyway.FlywayModule
import io.jooby.guice.GuiceModule
import io.jooby.hibernate.HibernateModule
import io.jooby.hibernate.TransactionalRequest
import io.jooby.hikari.HikariModule
import io.jooby.jackson.JacksonModule
import io.jooby.kt.Kooby
import io.jooby.kt.runApp
import io.jooby.netty.NettyServer
import io.jooby.pac4j.Pac4jModule
import jakarta.persistence.NoResultException
import org.pac4j.http.client.direct.HeaderClient
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration
import redis.clients.jedis.JedisPooled

class App : Kooby({
    setting()
    decorate()
    routes()
    web()
})

fun Kooby.setting() {
    install(NettyServer())
    install(OpenAPIModule())
    install(JacksonModule())

    install(GuiceModule())

    install(JedisModule())
    install(ValidatorModule())

    install(HikariModule())
    install(FlywayModule())
    install(HibernateModule().scan("io.github.jonaskahn.entities"))
    use(TransactionalRequest().enabledByDefault(true))


    install(
        Pac4jModule()
            .client("/api/secure/*") {
                HeaderClient(
                    "Authorization",
                    "Bearer ",
                    AdvancedJwtAuthenticator(
                        require(JedisPooled::class.java),
                        SecretSignatureConfiguration(it.getString("jwt.salt"))
                    )
                )
            }
    )

    setContextAsService(true)
}

fun Kooby.decorate() {
    after {
        val acceptLanguage: String? = ctx.header("Accept-Language").valueOrNull()
        ctx.setDefaultResponseType(MediaType.json)
        if (failure == null) {
            ctx.responseCode = StatusCode.OK
            when (result) {
                is Response<*> -> {
                    ctx.render(result as Response<*>)
                }

                is StatusCode -> {
                    ctx.render(Response.ok(Language.of(acceptLanguage, "app.common.message.success")))
                }

                else -> {
                    ctx.render(Response.ok(result))
                }
            }
            return@after
        }
        val ex = failure as Throwable
        log.error("Error: \n", ex)
        val (statusCode, data) = getStatusCodeAndMessage(ex, acceptLanguage)
        ctx.responseCode = statusCode
        ctx.render(data)
    }
}

private fun getStatusCodeAndMessage(ex: Throwable, acceptLanguage: String?): Pair<StatusCode, Response<*>> {
    val code: StatusCode?
    val res: Response<*>?
    when (ex) {
        is LogicException -> {
            code = StatusCode.BAD_REQUEST
            res = Response.fail(code = code, message = Language.of(acceptLanguage, ex.message, ex.variables))
        }

        is ValidationException -> {
            code = StatusCode.PRECONDITION_FAILED
            res = Response.fail(code = code, payload = ex.data)
        }

        is NotFoundException -> {
            code = StatusCode.NOT_FOUND
            res = Response.fail(code = code, message = Language.of(acceptLanguage, "app.common.exception.notfound"))
        }

        is AuthenticationException -> {
            code = StatusCode.BAD_REQUEST
            res = Response.fail(code = code, message = Language.of(acceptLanguage, ex.message))
        }

        is AuthorizationException, is UnauthorizedException -> {
            code = StatusCode.UNAUTHORIZED
            res = Response.fail(
                code = code,
                message = Language.of(acceptLanguage, "app.common.exception.AuthorizationException")
            )
        }

        is ForbiddenAccessException -> {
            code = StatusCode.FORBIDDEN
            res = Response.fail(code = code, message = Language.of(acceptLanguage, "app.common.exception.forbidden"))
        }

        is NoResultException -> {
            code = StatusCode.BAD_REQUEST
            res = Response.fail(code = code, message = Language.of(acceptLanguage, "app.common.exception.no-data"))
        }

        is Exception -> {
            code = StatusCode.SERVER_ERROR
            res = Response.fail(code = code, message = Language.of(acceptLanguage, "app.common.exception.server-error"))
        }

        else -> {
            code = StatusCode.SERVER_ERROR
            res =
                Response.fail(code = code, message = Language.of(acceptLanguage, "app.common.exception.unknown-error"))
        }
    }
    return Pair(code, res)
}

fun Kooby.routes() {

    mount("/api", object : Kooby({
        install(JacksonModule(JsonMapper.instance))
        mvc(HealthController::class.java)
        mvc(AuthController::class.java)
        mvc(UserController::class.java)
        mvc(TestRoleController::class.java)
    }) {})
}

fun Kooby.web() {

    assets("/*", "static")

    get("/uikit*") {
        ctx.forward("/")
    }
    get("/blocks") {
        ctx.forward("/")
    }
    get("/utilities*") {
        ctx.forward("/")
    }
    get("/documentation") {
        ctx.forward("/")
    }
}

fun main(args: Array<String>) {
    runApp(args, ::App)
}
