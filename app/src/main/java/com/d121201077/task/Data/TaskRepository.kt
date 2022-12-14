package com.d121201077.task.Data

import androidx.lifecycle.LiveData
import com.d121201077.task.Data.Model
import com.d121201077.task.Data.TaskDao

class TaskRepository(private val taskDao: TaskDao) {
    val getAllTask: LiveData<List<Model>> = taskDao.getAllTask()

    suspend fun insertTask(task: Model){
        taskDao.insertTask(task)
    }

    suspend fun doneTask(uid: Long){
        taskDao.doneTask(uid)
    }

    suspend fun updateTask(task: Model) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(uid: Long){
        taskDao.deleteTask(uid)
    }
}