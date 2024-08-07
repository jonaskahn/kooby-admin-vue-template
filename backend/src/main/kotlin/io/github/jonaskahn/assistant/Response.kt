package io.github.jonaskahn.assistant

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import io.jooby.StatusCode

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder("status", "message", "payload")
class Response<T>(
    val status: Int = 0,
    val message: String? = null,
    val payload: T? = null
) {
    companion object {
        private fun <T> builder(): ResponseBuilder<T> {
            return ResponseBuilder()
        }

        class ResponseBuilder<T> internal constructor() {
            private var status = 200
            private var message: String? = null
            private var payload: T? = null

            fun status(status: Int) = apply {
                this.status = status
            }

            fun message(message: String?) = apply {
                this.message = message
            }

            fun payload(payload: T?) = apply {
                this.payload = payload
            }

            fun build(): Response<T> {
                return Response(status, message, payload)
            }

            override fun toString(): String {
                return "ResponseBuilder(status=$status, message=$message, payload=$payload)"
            }

        }

        fun ok(): Response<String> {
            return builder<String>()
                .message(Language.of("app.common.message.success"))
                .status(StatusCode.OK.value())
                .build()
        }

        fun <T> ok(payload: T?): Response<T> {
            return builder<T>()
                .message(Language.of("app.common.message.success"))
                .status(StatusCode.OK.value())
                .payload(payload)
                .build()
        }

        fun <T> ok(message: String?, payload: T?): Response<T> {
            return builder<T>()
                .message(Language.of(message))
                .status(StatusCode.OK.value())
                .payload(payload)
                .build()
        }


        fun fail(message: String?, code: StatusCode = StatusCode.BAD_REQUEST): Response<String> {
            return builder<String>()
                .status(code.value())
                .message(Language.of(message))
                .build()
        }

        fun fail(payload: Any?, code: StatusCode = StatusCode.BAD_REQUEST): Response<Any> {
            return builder<Any>()
                .status(code.value())
                .message(Language.of("app.common.message.failed"))
                .payload(payload ?: "")
                .build()
        }
    }


}