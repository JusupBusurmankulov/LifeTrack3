package com.example.lifetrack.fragments.models

import java.io.Serializable

data class TaskModel (
    val task:String,
    val date:String,
    val reglar:String,
        ) : Serializable