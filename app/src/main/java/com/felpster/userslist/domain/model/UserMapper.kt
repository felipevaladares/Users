package com.felpster.userslist.domain.model

import com.felpster.userslist.data.model.UserResponse

fun UserResponse.toDomain(): User =
    User(
        id = this.id,
        name = this.name,
        username = this.username,
        email = this.email,
        website = this.website,
    )
