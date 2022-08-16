package com.example.lifetrack.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lifetrack.databinding.ItemOnBoardingBinding
import com.example.lifetrack.databinding.ItemTaskBinding
import com.example.lifetrack.fragments.models.TaskModel

class TaskAdapter(private val list: ArrayList<TaskModel>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
       holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(taskModel:TaskModel){
            binding.task.text = taskModel.task
            binding.date.text = taskModel.date
            binding.regular.text = taskModel.reglar
        }
    }
}