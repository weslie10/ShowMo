package com.weslie10.showmo.ui.main.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.weslie10.showmo.databinding.FragmentAboutBinding
import com.weslie10.showmo.utils.Utility.setLink

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.instagram.setLink("https://instagram.com/")
        binding.github.setLink("https://github.com/")
        binding.linkedIn.setLink("https://linkedin.com/in/")
    }
}