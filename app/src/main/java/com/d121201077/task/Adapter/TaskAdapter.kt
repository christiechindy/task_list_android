package com.d121201077.task.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView

import com.d121201077.task.R
import com.d121201077.task.MainActivity
import com.d121201077.task.AddTaskActivity
import com.d121201077.task.Data.Model

import kotlinx.android.synthetic.main.task_item.view.*
import java.util.*

class TaskAdapter(val taskClickInterface: TaskClickInterface): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var taskList = ArrayList<Model>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.task_item, parent, false)
        )
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position], taskClickInterface)
    }

    override fun getItemId(position: Int): Long {
        return taskList[position].id
    }

    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(task: Model, taskClickInterface: TaskClickInterface) {
            val colors = itemView.resources.getIntArray(R.array.colors)
            with(itemView) {
                taskTitle.text = task.title
                taskDetail.text = task.detail
                taskImportance.text = task.importance
                taskUrgencity.text = task.urgencity

                taskViewLayout.setOnClickListener {
                    taskClickInterface.onNoteClick(task)
                }
            }
        }

    }

    fun setData(task: List<Model>) {
        this.taskList.clear()
        this.taskList.addAll(task)
        notifyDataSetChanged()
    }

    interface TaskClickInterface {
        fun onNoteClick(task: Model)
    }

}