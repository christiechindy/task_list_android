package com.d121201077.task.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Model(
    var title: String,
    var detail: String,
    var importance: String,
    var urgencity: String,
    var isFinished: Int = 0,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
)