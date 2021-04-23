package com.example.room.dao

import androidx.room.*
import com.example.room.entity.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers(): List<User>

    @Query("select * from user where age > :age")
    fun loadUsersOlderThan(age: Int): List<User>

    @Delete
    fun deleteUser(user: User):Int

    @Query("delete from User where lastName = :lastName")
    fun deleteUserByLastName(lastName: String): Int

}