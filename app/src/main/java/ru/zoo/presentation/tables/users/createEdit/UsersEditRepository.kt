package ru.zoo.presentation.tables.users.createEdit

import ru.zoo.data.models.User

class UsersEditRepository {
    companion object{
        var userForSend = User()
        var requestCode = 0
        var employeeID : Int? = null
    }
}