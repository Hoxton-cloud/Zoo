package ru.zoo.presentation.tables.users.listDirectory

import ru.zoo.data.models.User

class UsersRepository {
    companion object{
        var usersTemp = ArrayList<User>()
        fun addUser(user: User) {
            if (checkedUser.any { it.id == user.id }) {
                checkedUser.removeAll { it.id == user.id }
            } else {
                checkedUser.add(user)
            }
        }
        var users = ArrayList<User>()
        var checkedUser = ArrayList<User>()

        var requestCode = 0
        var isMultiple = false

        fun clear() {
            users.clear()
            usersTemp.clear()
        }
    }
}