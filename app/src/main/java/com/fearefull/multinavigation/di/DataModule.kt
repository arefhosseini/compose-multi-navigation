package com.fearefull.multinavigation.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.fearefull.multinavigation.data.local.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    @ViewModelScoped
    fun provideSharedPreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(
            context.getSharedPreferences(AppPreferences.PREFERENCES_NAME, MODE_PRIVATE)
        )
    }
}