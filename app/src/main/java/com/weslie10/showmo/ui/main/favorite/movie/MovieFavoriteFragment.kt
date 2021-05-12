package com.weslie10.showmo.ui.main.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.weslie10.showmo.R
import com.weslie10.showmo.databinding.FragmentMovieFavoriteBinding
import com.weslie10.showmo.ui.main.detail.DetailMovieActivity
import com.weslie10.showmo.ui.main.movie.MovieCallback
import com.weslie10.showmo.ui.main.movie.MovieViewModel
import com.weslie10.showmo.utils.Utility.notFound
import com.weslie10.showmo.viewmodel.ViewModelFactory

class MovieFavoriteFragment : Fragment(), MovieCallback {
    private lateinit var binding: FragmentMovieFavoriteBinding
    private lateinit var movieAdapter: MovieFavoriteAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvMovies)

        if (activity != null) {
            movieAdapter = MovieFavoriteAdapter(this)

            val viewModelFactory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]

            viewModel.getFavorite().observe(viewLifecycleOwner, { movie ->
                if (movie.isNotEmpty()) {
                    binding.movieNotFound.notFound(false)
                    movieAdapter.submitList(movie)
                    movieAdapter.notifyDataSetChanged()

                } else binding.movieNotFound.notFound(true)

            })
            with(binding.rvMovies) {
                this.layoutManager = LinearLayoutManager(context)
                this.setHasFixedSize(true)
                this.adapter = movieAdapter
            }
        }
    }

    override fun onMovieClick(id: Int) {
        val moveIntent = Intent(activity, DetailMovieActivity::class.java)
        moveIntent.putExtra(DetailMovieActivity.EXTRA_ID, id)
        startActivity(moveIntent)
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.bindingAdapterPosition
                val movieEntity = movieAdapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavorite(it) }
                Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG).apply {
                    setAction(R.string.message_ok) { movieEntity?.let { viewModel.setFavorite(it) } }
                    show()
                }
            }
        }
    })
}