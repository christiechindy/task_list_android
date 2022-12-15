package com.d121201077.task

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import com.d121201077.task.Data.TaskViewModel
import com.d121201077.task.Data.Model
import com.d121201077.task.R

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.task_item.*
import java.util.*
import kotlinx.coroutines.launch


class AddTaskActivity: AppCompatActivity(){
    lateinit var adapter: ArrayAdapter<CharSequence>
    lateinit var taskViewModel: TaskViewModel

    lateinit var addTaskTitle: TextInputEditText
    lateinit var addTaskDetail: TextInputEditText
    lateinit var addTaskButton: Button
    lateinit var addTaskImportance: Spinner
    lateinit var addTaskUrgencity: Spinner

    lateinit var type: String

    var taskID = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        addTaskTitle = findViewById(R.id.addTaskTitle)
        addTaskDetail = findViewById(R.id.addTaskDetail)

        addTaskImportance = findViewById(R.id.spinnerImportance)
        addTaskUrgencity = findViewById(R.id.spinnerUrgencity)

        addTaskButton = findViewById(R.id.addTaskButton)

        type = intent.getStringExtra("type").toString()

        taskViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application))[TaskViewModel::class.java]


        if (type.equals("Edit")) {
            val taskTitle = intent.getStringExtra("taskTitle").toString()
            val taskDetail = intent.getStringExtra("taskDetail").toString()

            taskID = intent.getLongExtra("taskID", 0)
            addTaskTitle.setText(taskTitle)
            addTaskDetail.setText(taskDetail)

            addTaskButton.setText("Simpan")
        }

        addTaskButton.setOnClickListener {
            addTask(taskID)
        }

        setUpSpinnerImportance()
        setUpSpinnerUrgencity()
    }

    private fun setUpSpinnerImportance() {
        adapter = ArrayAdapter.createFromResource(this, R.array.taskImportance, android.R
            .layout.simple_list_item_1)
        addTaskImportance.adapter = adapter
    }

    private fun setUpSpinnerUrgencity() {
        adapter = ArrayAdapter.createFromResource(this, R.array.taskUrgencity, android.R
            .layout.simple_list_item_1)
        addTaskUrgencity.adapter = adapter
    }

    private fun addTask(id: Long) {
        val importance = addTaskImportance.selectedItem.toString()
        val urgencity = addTaskUrgencity.selectedItem.toString()

        val title = addTaskTitle.text.toString()
        val detail = addTaskDetail.text.toString()
        type = intent.getStringExtra("type").toString()

        if(title.isEmpty()) {
            Toast.makeText(this@AddTaskActivity, "Isilah judul", Toast.LENGTH_LONG).show()
            addTaskTitle.requestFocus()
            return
        }

        if(type.equals("Edit")) {
            val updateTask = Model(title, detail, importance, urgencity, id = id)
            lifecycleScope.launch {
                taskViewModel.updateTask(updateTask)
                Toast.makeText(this@AddTaskActivity, "Tugas Diubah", Toast.LENGTH_LONG).show()
            }
        }else {
            lifecycleScope.launch{
                val addTask = Model(title, detail, importance, urgencity)
                taskViewModel.insertTask(addTask)
                Toast.makeText(this@AddTaskActivity, "Tugas Ditambahkan", Toast.LENGTH_LONG).show()
            }
        }
        startActivity(Intent(applicationContext, MainActivity::class.java))
        this.finish()

    }
}

