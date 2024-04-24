package com.jossidfactory.composables.di

import android.content.Context
import androidx.room.Room
import com.jossidfactory.composables.data.database.ComposablesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "composables_db"

    @Singleton
    @Provides
    fun provideRoomModule(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ComposablesDataBase::class.java, DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun provideUserDao(composablesDataBase: ComposablesDataBase) = composablesDataBase.getUserDao()
}