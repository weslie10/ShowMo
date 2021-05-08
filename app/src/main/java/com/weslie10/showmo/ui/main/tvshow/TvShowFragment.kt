package com.weslie10.showmo.ui.main.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.weslie10.showmo.data.source.local.entities.TvShowResponseEntity
import com.weslie10.showmo.databinding.FragmentTvShowBinding
import com.weslie10.showmo.ui.main.detail.DetailTvShowActivity
import com.weslie10.showmo.utils.SortUtils
import com.weslie10.showmo.utils.Utility.loading
import com.weslie10.showmo.viewmodel.ViewModelFactory
import com.weslie10.showmo.vo.Resource
import com.weslie10.showmo.vo.Status

class TvShowFragment : Fragment(), TvShowCallback {
    private lateinit var binding: FragmentTvShowBinding
    private lateinit var tvShowAdapter: TvShowAdapter
    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModelFactory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, viewModelFactory)[TvShowViewModel::class.java]
            tvShowAdapter = TvShowAdapter(this)

            val item = arrayListOf("All", "Title", "Release Date")
            binding.spinner.setItems(item)

            var sort = ""
            binding.spinner.setOnItemSelectedListener { _, _, _, filter ->
                when (filter) {
                    "All" -> sort = SortUtils.ALL
                    "Title" -> sort = SortUtils.TITLE
                    "Release Date" -> sort = SortUtils.DATE
                }
                viewModel.getTvShow(sort).observe(viewLifecycleOwner, observer)
            }
            viewModel.getTvShow(sort).observe(viewLifecycleOwner, observer)

            with(binding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    private val observer = Observer<Resource<PagedList<TvShowResponseEntity>>> { tvShow ->
        if (tvShow != null) {
            when (tvShow.status) {
                Status.LOADING -> binding.progressBar.loading(true)
                Status.SUCCESS -> {
                    binding.progressBar.loading(false)
                    tvShowAdapter.submitList(tvShow.data)
                }
                Status.ERROR -> {
                    binding.progressBar.loading(false)
                    Toast.makeText(context, "There's an error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onTvShowClick(id: Int) {
        val moveIntent = Intent(activity, DetailTvShowActivity::class.java)
        moveIntent.putExtra(DetailTvShowActivity.EXTRA_ID, id)
        startActivity(moveIntent)
    }
}