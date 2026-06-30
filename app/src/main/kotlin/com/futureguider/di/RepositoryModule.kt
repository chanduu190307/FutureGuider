package com.futureguider.di

import com.futureguider.data.repository.CareerRepositoryImpl
import com.futureguider.data.repository.SavedPathRepositoryImpl
import com.futureguider.data.repository.UserRepositoryImpl
import com.futureguider.domain.usecase.CareerRepository
import com.futureguider.domain.usecase.SavedPathRepository
import com.futureguider.domain.usecase.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds @Singleton abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
    @Binds @Singleton abstract fun bindCareerRepository(impl: CareerRepositoryImpl): CareerRepository
    @Binds @Singleton abstract fun bindSavedPathRepository(impl: SavedPathRepositoryImpl): SavedPathRepository
}
