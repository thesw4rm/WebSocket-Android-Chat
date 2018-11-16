package com.example.ytpillai.cmsc_355_proj.security

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE alias LIKE :alias")
    fun findByAlias(alias: String): User?
    @Insert
    fun insertAll(vararg users: User?)
    @Delete
    fun delete(user: User?)
}