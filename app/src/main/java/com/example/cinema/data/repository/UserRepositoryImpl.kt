package com.example.cinema.data.repository

import com.example.cinema.data.db.AppDatabase
import com.example.cinema.domain.model.User
import com.example.cinema.domain.repository.UserRepository
import com.example.cinema.mapper.RoleMapper
import com.example.cinema.mapper.UserMapper

class UserRepositoryImpl(
    private val userMapper: UserMapper,
    private val roleMapper: RoleMapper
) : UserRepository {

    override fun loginUser(username: String, password: String): User? {
        val userEntity = AppDatabase.daos.usersDao.findUserByUsernameAndPassword(
            username = username,
            password = password
        )

        return userEntity?.let { userMapper.mapFromEntity(it) }
    }

    override fun registerUser(username: String, password: String): Boolean {
        var userEntity = AppDatabase.daos.usersDao.findUserByUsername(
            username = username
        )
        if (userEntity != null) {
            return false
        }

        val roleEntity = AppDatabase.daos.rolesDao.getRoleByName("user")
        val user = User(
            username = username,
            password = password,
            role = roleMapper.mapFromEntity(roleEntity!!)
        )
        userEntity = userMapper.mapToEntity(user)
        AppDatabase.daos.usersDao.addUser(userEntity)

        return true
    }
}