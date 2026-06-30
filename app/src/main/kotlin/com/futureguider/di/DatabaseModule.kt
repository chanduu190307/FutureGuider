package com.futureguider.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.futureguider.data.local.FutureGuiderDatabase
import com.futureguider.data.local.dao.CareerDetailDao
import com.futureguider.data.local.dao.CareerNodeDao
import com.futureguider.data.local.dao.SavedPathDao
import com.futureguider.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {}
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {}
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        scope: CoroutineScope
    ): FutureGuiderDatabase {
        lateinit var db: FutureGuiderDatabase
        db = Room.databaseBuilder(
            context,
            FutureGuiderDatabase::class.java,
            "future_guider.db"
        )
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .addCallback(FutureGuiderDatabase.createCallback(scope) { db })
            .build()
        return db
    }

    @Provides fun provideUserDao(db: FutureGuiderDatabase): UserDao = db.userDao()
    @Provides fun provideCareerNodeDao(db: FutureGuiderDatabase): CareerNodeDao = db.careerNodeDao()
    @Provides fun provideCareerDetailDao(db: FutureGuiderDatabase): CareerDetailDao = db.careerDetailDao()
    @Provides fun provideSavedPathDao(db: FutureGuiderDatabase): SavedPathDao = db.savedPathDao()
}
