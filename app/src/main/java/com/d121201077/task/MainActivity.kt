package com.d121201077.task

import android.content.Intent
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.d121201077.task.Adapter.TaskAdapter
import com.d121201077.task.Data.TaskViewModel
import com.d121201077.task.Data.Model
import com.d121201077.task.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

import java.util.*

class MainActivity : AppCompatActivity(), TaskAdapter.TaskClickInterface {
    val adapter = TaskAdapter(this)
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        taskRV = findViewById(R.id.taskRv)
        taskRV.adapter = adapter

        taskRV.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager
            .VERTICAL, false)

        taskViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application))[TaskViewModel::class.java]
        taskViewModel.getAllTask.observe(this, Observer {
                task -> adapter.setData(task)
        })
    }

    override fun onNoteClick(task: Model) {
        val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
        intent.putExtra("type", "Edit")
        intent.putExtra("taskTitle", task.title)
        intent.putExtra("taskDetail", task.detail)
        intent.putExtra("taskImportance", task.importance)
        intent.putExtra("taskUrgencity", task.urgencity)
        intent.putExtra("taskID", task.id)
        startActivity(intent)
    }

    fun addNewTask(view: View) {
        val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
        intent.putExtra("type", "Add")
        startActivity(intent)
    }
}