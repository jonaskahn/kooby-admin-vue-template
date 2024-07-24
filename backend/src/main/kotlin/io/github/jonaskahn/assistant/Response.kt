package io.github.jonaskahn.assistant

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import io.jooby.StatusCode

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder("status", "timestamp", "payload")
class Response<T>(
    val status: Int = 0,
    val payload: T? = null
) {
    companion object {
        private fun <T> builder(): ResponseBuilder<T> {
            return ResponseBuilder()
        }

        class ResponseBuilder<T> internal constructor() {
            private var status = 0
            private var payload: T? = null

            fun status(status: Int) = apply {
                this.status = status
            }

            fun payload(payload: T) = apply {
                this.payload = payload
            }

            fun build(): Response<T> {
                return Response(status, payload)
            }

            override fun toString(): String {
                return "Response.ResponseBuilder(status=$status, payload=$payload)"
            }
        }

        fun <T> ok(payload: T): Response<T> {
            return builder<T>()
                .status(StatusCode.OK.value())
                .payload(payload)
                .build()
        }


        fun fail(message: String?, code: StatusCode = StatusCode.BAD_REQUEST): Response<String> {
            return builder<String>()
                .status(code.value())
                .payload(message ?: "")
                .build()
        }

        fun fail(payload: Any?, code: StatusCode = StatusCode.BAD_REQUEST): Response<Any> {
            return builder<Any>()
                .status(code.value())
                .payload(payload ?: "")
                .build()
        }
    }


}