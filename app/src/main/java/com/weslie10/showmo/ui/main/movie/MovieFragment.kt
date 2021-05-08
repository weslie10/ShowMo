package com.weslie10.showmo.ui.main.movie

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
import com.weslie10.showmo.data.source.local.entities.MovieResponseEntity
import com.weslie10.showmo.databinding.FragmentMovieBinding
import com.weslie10.showmo.ui.main.detail.DetailMovieActivity
import com.weslie10.showmo.utils.Utility.loading
import com.weslie10.showmo.viewmodel.ViewModelFactory
import com.weslie10.showmo.vo.Resource
import com.weslie10.showmo.vo.Status

class MovieFragment : Fragment(), MovieCallback {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModelFactory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]
            movieAdapter = MovieAdapter(this)

            val item = arrayListOf("All", "Title", "Release Date")
            binding.spinner.setItems(item)

            var sort = ""
            binding.spinner.setOnItemSelectedListener { _, _, _, filter ->
                sort = filter.toString()
                viewModel.getMovie(sort).observe(viewLifecycleOwner, observer)
            }

            viewModel.getMovie(sort).observe(viewLifecycleOwner, observer)

            with(binding.rvMovies) {
                this.layoutManager = LinearLayoutManager(context)
                this.setHasFixedSize(true)
                this.adapter = movieAdapter
            }
        }
    }

    private val observer = Observer<Resource<PagedList<MovieResponseEntity>>> { movie ->
        if (movie != null) {
            when (movie.status) {
                Status.LOADING -> binding.progressBar.loading(true)
                Status.SUCCESS -> {
                    binding.progressBar.loading(false)
                    movieAdapter.submitList(movie.data)
                }
                Status.ERROR -> {
                    binding.progressBar.loading(false)
                    Toast.makeText(context, "There's an error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onMovieClick(id: Int) {
        val moveIntent = Intent(activity, DetailMovieActivity::class.java)
        moveIntent.putExtra(DetailMovieActivity.EXTRA_ID, id)
        startActivity(moveIntent)
    }
}