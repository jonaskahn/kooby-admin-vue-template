package io.github.jonaskahn

import io.github.jonaskahn.assistant.JacksonMapper
import io.github.jonaskahn.assistant.Language
import io.github.jonaskahn.assistant.Response
import io.github.jonaskahn.controllers.HealthController
import io.github.jonaskahn.controllers.TestRoleController
import io.github.jonaskahn.controllers.auth.AuthController
import io.github.jonaskahn.controllers.user.UserController
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
import io.jooby.kt.AfterContext
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
    install(JacksonModule(JacksonMapper.INSTANCE))

    install(GuiceModule())

    install(JedisModule())
    install(ValidatorModule())

    install(HikariModule())
    install(FlywayModule())
    install(HibernateModule().scan("io.github.jonaskahn.entities"))

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

    use(TransactionalRequest().enabledByDefault(true))
    setContextAsService(true)

}

fun Kooby.decorate() {
    after {
        val acceptLanguage: String? = ctx.header("Accept-Language").valueOrNull()
        ctx.setDefaultResponseType(MediaType.json)
        if (failure == null) {
            handleSuccess(acceptLanguage)
        } else {
            log.error("Something went wrong, detail", failure as Throwable)
            handleFailure(acceptLanguage)
        }
    }
}

private fun AfterContext.handleSuccess(acceptLanguage: String?) {
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
    return
}

private fun AfterContext.handleFailure(acceptLanguage: String?) {
    val ex = failure as Throwable
    val (statusCode, data) = getStatusCodeAndMessage(ex, acceptLanguage)
    ctx.responseCode = statusCode
    ctx.render(data)
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
    mount("/api", RouteDefinition())
}

private class RouteDefinition : Kooby({
    install(JacksonModule(JacksonMapper.INSTANCE))
    mvc(HealthController::class.java)
    mvc(AuthController::class.java)
    mvc(UserController::class.java)
    mvc(TestRoleController::class.java)
})

fun Kooby.web() {
    assets("/*", "static")

    get("/uikit*") {
        ctx.forward("/")
    }
    get("/blocks*") {
        ctx.forward("/")
    }
    get("/utilities*") {
        ctx.forward("/")
    }
    get("/page*") {
        ctx.forward("/")
    }
    get("/landing") {
        ctx.forward("/")
    }
    get("/documentation") {
        ctx.forward("/")
    }

}

fun main(args: Array<String>) {
    runApp(args, ::App)
}
