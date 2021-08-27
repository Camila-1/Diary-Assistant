package com.pchpsky.diary.screens.auth

import arrow.core.Either
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CreateSessionMutation
import com.pchpsky.schema.CreateUserMutation

interface AuthRepository {

    fun createUser(email: String, password: String): Either<NetworkError, CreateUserMutation.Data?>
    fun login(login: String, password: String): Either<NetworkError, CreateSessionMutation.Data?>
}