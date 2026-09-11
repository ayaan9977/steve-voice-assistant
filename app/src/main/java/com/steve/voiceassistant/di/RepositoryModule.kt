package com.steve.voiceassistant.di

import com.steve.voiceassistant.data.repository.CommandRepositoryImpl
import com.steve.voiceassistant.domain.repository.CommandRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCommandRepository(impl: CommandRepositoryImpl): CommandRepository
}
