package com.example.lifetrack.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.navigation.fragment.findNavController
import com.example.lifetrack.R
import com.example.lifetrack.databinding.DialogRegularBinding
import com.example.lifetrack.databinding.FragmentCreateTaskDataBinding
import com.example.lifetrack.fragments.models.TaskModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CreateTaskData : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCreateTaskDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateTaskDataBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheet = view.parent as View
        bottomSheet.backgroundTintMode = PorterDuff.Mode.CLEAR
        bottomSheet.backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
        bottomSheet.setBackgroundColor(Color.TRANSPARENT)

        initClicker()

    }

    private fun initClicker() {
        binding.btnDate.setOnClickListener {
            val datePickerFragment = DatePickerFragment()
            requireActivity().supportFragmentManager.setFragmentResultListener(
                "myKey",
                viewLifecycleOwner
            ) { resultKey, bundle ->
                if (resultKey == "myKey") {
                    val date = bundle.getString("key")
                    binding.btnDate.text = date
                }
            }
            datePickerFragment.show(requireActivity().supportFragmentManager, "TAG")
        }
        binding.btnRegular.setOnClickListener {
            showRegularDialog()
        }
        binding.btnApply.setOnClickListener{
            val bundle = Bundle()
            val model = TaskModel(
                binding.etTask.text.toString(),
                binding.btnDate.text.toString(),
                binding.btnRegular.text.toString())
            bundle.putSerializable("model", model)
            findNavController().navigate(R.id.homeFragment, bundle)
            dismiss()
        }
    }

    @SuppressLint("InflateParams")
    private fun showRegularDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        val binding = DialogRegularBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        with(binding) {
            btnEveryday.setOnClickListener{
                this@CreateTaskData.binding.btnRegular.text = btnEveryday.text
            }
            btnEveryWeek.setOnClickListener{
                this@CreateTaskData.binding.btnRegular.text = btnEveryWeek.text
            }
            btnEveryMonth.setOnClickListener{
                this@CreateTaskData.binding.btnRegular.text = btnEveryMonth.text
            }
            btnEveryYear.setOnClickListener{
                this@CreateTaskData.binding.btnRegular.text = btnEveryYear.text
            }
            btnCancel.setOnClickListener {
                dialog.dismiss()
            }
        }



        dialog.show()


    }
}