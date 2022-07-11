package com.leesunae.bebehelper_mvp.data.repository

import com.leesunae.bebehelper_mvp.data.room.entity.User

interface UserRepository {
    fun createUser(
        email: String,
        password: String,
        nickName: String,
        gender: String?,
        childGender: String?,
        ageOfChildren: String?,
        area: String?,
        image: String?,
        callback: Callback<Boolean>
    )

    fun login(
        email: String,
        password: String,
        callback: Callback<Boolean>
    )

    fun logout(callback: Callback<String>)

    fun updateUser(
        id: Int,
        email: String,
        password: String,
        nickName: String,
        gender: String,
        childGender: String,
        ageOfChildren: String,
        area: String,
        image: String,
        callback: Callback<Boolean>
    )

    fun getUser(email:String, callback: Callback<User>)

    fun getUserAll(callback: Callback<List<User>>)

    fun deleteUser(id: Int, callback: Callback<String>)
}