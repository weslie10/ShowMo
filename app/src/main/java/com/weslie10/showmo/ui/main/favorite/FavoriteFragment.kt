package com.weslie10.showmo.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.weslie10.showmo.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayout()
    }

    private fun setUpTabLayout() {
        val sectionsPagerAdapter =
            SectionsPagerFavoriteAdapter(requireActivity(), childFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setViewPager(binding.viewPager)
    }
}