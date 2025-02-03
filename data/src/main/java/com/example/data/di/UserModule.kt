package com.example.data.di

import com.example.data.usecase.LoginUseCaseImpl
import com.example.data.usecase.SignUpUseCaseImpl
import com.example.domain.usecase.login.LoginUseCase
import com.example.domain.usecase.login.SignUpUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {
    @Binds
    abstract fun bindLoginUseCase(uc: LoginUseCaseImpl): LoginUseCase

    @Binds
    abstract fun bindSignUpUseCase(uc: SignUpUseCaseImpl): SignUpUseCase

}