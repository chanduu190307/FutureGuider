package com.futureguider.data.repository

import com.futureguider.data.local.UserPreferences
import com.futureguider.data.local.dao.UserDao
import com.futureguider.data.local.entities.UserEntity
import com.futureguider.data.local.toDomain
import com.futureguider.domain.model.User
import com.futureguider.domain.usecase.UserRepository
import kotlinx.coroutines.flow.Flow
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val prefs: UserPreferences
) : UserRepository {

    private fun hash(input: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }

    override suspend fun register(name: String, email: String, password: String): Result<User> {
        return try {
            if (userDao.emailExists(email) > 0)
                return Result.failure(Exception("Email already registered"))
            val entity = UserEntity(
                name = name.trim(),
                email = email.trim().lowercase(),
                passwordHash = hash(password)
            )
            val id = userDao.insertUser(entity)
            Result.success(entity.copy(id = id.toInt()).toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(email: String, password: String): Result<User> {
        val entity = userDao.getUserByEmail(email.trim().lowercase())
            ?: return Result.failure(Exception("No account found with this email"))
        return if (entity.passwordHash == hash(password)) {
            Result.success(entity.toDomain())
        } else {
            Result.failure(Exception("Incorrect password"))
        }
    }

    override suspend fun saveSession(user: User) = prefs.saveLoginSession(user.id, user.name)
    override suspend fun logout() = prefs.clearSession()
    override fun isLoggedIn(): Flow<Boolean> = prefs.isLoggedIn
    override fun getCurrentUserId(): Flow<Int> = prefs.currentUserId
    override fun getUserName(): Flow<String> = prefs.userName
}
