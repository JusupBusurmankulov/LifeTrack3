package com.example.lifetrack.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lifetrack.adapters.TaskAdapter
import com.example.lifetrack.databinding.FragmentHomeBinding
import com.example.lifetrack.fragments.CreateTaskData
import com.example.lifetrack.fragments.models.TaskModel
import java.util.ArrayList


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    var taskModel: TaskModel? = null
    val list = arrayListOf<TaskModel>()
    var adapter= TaskAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()
        if (arguments!= null){
            taskModel = arguments?.getSerializable("model") as TaskModel
            Log.e("", "onViewCreated: ${taskModel.toString()} ${taskModel!!.task }")
            list.add(taskModel!!)
        }
        adapter = TaskAdapter(list)
        binding.recyclerTask.adapter = adapter

    }

    private fun initClicker() {
        binding.btnAddTask.setOnClickListener {
            val dialog = CreateTaskData()
            dialog.show(requireActivity().supportFragmentManager, "")
        }
    }
}