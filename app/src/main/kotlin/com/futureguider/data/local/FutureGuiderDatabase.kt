package com.futureguider.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.futureguider.data.local.dao.*
import com.futureguider.data.local.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        UserEntity::class,
        CareerNodeEntity::class,
        CareerDetailEntity::class,
        SkillEntity::class,
        CertificationEntity::class,
        ProjectEntity::class,
        SavedPathEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class FutureGuiderDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun careerNodeDao(): CareerNodeDao
    abstract fun careerDetailDao(): CareerDetailDao
    abstract fun savedPathDao(): SavedPathDao

    companion object {
        fun createCallback(scope: CoroutineScope, database: () -> FutureGuiderDatabase) =
            object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    scope.launch(Dispatchers.IO) {
                        SampleDataSeeder.seed(database())
                    }
                }
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    scope.launch(Dispatchers.IO) {
                        SampleDataSeeder.seed(database())
                    }
                }
            }
    }
}
