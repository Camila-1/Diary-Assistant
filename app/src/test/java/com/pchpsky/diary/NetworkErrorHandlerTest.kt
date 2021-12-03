package com.pchpsky.diary

import arrow.core.Left
import com.apollographql.apollo.api.ApolloExperimental
import com.apollographql.apollo.api.Error
import com.apollographql.apollo.api.Response
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.diary.exceptions.handlers.NetworkErrorHandler
import com.pchpsky.schema.CurrentUserQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.sql.Struct
import java.util.jar.Attributes

class NetworkErrorHandlerTest {

    private val errorHandler = NetworkErrorHandler()

    fun createError(attributes: Map<String, Any>): List<Error> = listOf(
        Error("Error", listOf(Error.Location(1L, 1L)), attributes)
    )

    fun createFields(): Map<String, ArrayList<String>> {
        return mapOf("fields" to arrayListOf())
    }

    @ApolloExperimental
    fun createResponse(errors: List<Error>): Response<Any> =
        Response<Any>(Response.builder(CurrentUserQuery())).copy(errors = errors)

    private fun errorHandlerResult(response: Response<Any>) =
        runBlocking(Dispatchers.Unconfined) { errorHandler.withErrorHandler { response } }

    @ApolloExperimental
    @Test
    fun withErrorHandler_WhenError400_ReturnsLeftServerError() {

        val error400 = createError(mapOf("code" to 400))
        val response = createResponse(error400)
        val withErrorHandlerResult = errorHandlerResult(response)

        assertEquals(Left(NetworkError.ServerError), withErrorHandlerResult)
    }

    @ApolloExperimental
    @Test
    fun withErrorHandler_WhenError401_ReturnsLeftAuthenticationError() {
        val error401 = createError(mapOf("code" to 401))
        val response = createResponse(error401)
        val withErrorHandlerResult = errorHandlerResult(response)

        assertEquals(Left(NetworkError.AuthenticationError("Error")), withErrorHandlerResult)
    }

    @ApolloExperimental
    @Test
    fun withErrorHandler_WhenError422_ReturnsLeftValidationError() {
        val error422 = createError(mapOf("code" to 422, "fields" to mapOf<String, ArrayList<String>>()))
        val response = Response<Any>(Response.builder(CurrentUserQuery()))
            .copy(
                errors = error422,

            )
        val withErrorHandlerResult = errorHandlerResult(response)

        assertEquals(Left(NetworkError.ValidationError(mapOf())), withErrorHandlerResult)
    }

}