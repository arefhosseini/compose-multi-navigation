package com.fearefull.multinavigation.data.model.local

import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

@Immutable
@Serializable
data class User(
    val email: String,
)