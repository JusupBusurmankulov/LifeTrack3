package com.example.lifetrack.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.lifetrack.R
import com.example.lifetrack.`interface`.OnItemClicker
import com.example.lifetrack.adapters.OnBoardingAdapter
import com.example.lifetrack.databinding.FragmentOnBoardingBinding
import com.example.lifetrack.fragments.models.OnBoardingModel


class OnBoardingFragment : Fragment(), OnItemClicker {

    private lateinit var binding: FragmentOnBoardingBinding
    private val list = arrayListOf<OnBoardingModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.add(OnBoardingModel(R.drawable.on_boarding_1, "Экономь время", "Дальше"))
        list.add(OnBoardingModel(R.drawable.on_boarding_2, "Достигай целей", "Дальше"))
        list.add(OnBoardingModel(R.drawable.on_boarding_3, "Развивайся", "Начинаем"))
        binding.viewPager.adapter = OnBoardingAdapter(list, this)
        binding.dotsIndicator.attachTo(binding.viewPager)
        checkIsShown()
    }

    private fun checkIsShown() {
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("board_preference", Context.MODE_PRIVATE)
        val isShown = sharedPreferences.getBoolean("isShow", false)
        if (isShown) {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun onClick() {
        findNavController().navigate(R.id.clearBackStack)
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("board_preference", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isShow", true).apply()


    }

    override fun onClickNext() {
        onNext()
    }

    private fun onNext() {
        val adapter = binding.viewPager.adapter
        val currentPosition = binding.viewPager.currentItem
        val nextPosition = currentPosition + 1
        if (nextPosition < adapter?.itemCount!!) {
            binding.viewPager.currentItem = nextPosition
        } else
            binding.viewPager.currentItem = 0
    }
}

