package com.sobolev.calculatorapp_info_tecs_25.di

import android.content.Context
import androidx.room.Room
import com.sobolev.calculatorapp_info_tecs_25.data.local.CalculatorDatabase
import com.sobolev.calculatorapp_info_tecs_25.data.local.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {


    companion object {

        @Singleton
        @Provides
        fun provideDataBase(
            @ApplicationContext context: Context
        ): CalculatorDatabase {
            return  Room.databaseBuilder(
                context = context,
                klass = CalculatorDatabase::class.java,
                name = "calculator.db"
            ).build()
        }

        @Singleton
        @Provides
        fun provideHistoryDao(
            dataBase: CalculatorDatabase
        ): HistoryDao {
            return dataBase.historyDao()
        }
    }
}