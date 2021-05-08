package com.weslie10.showmo.ui.main.favorite.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.weslie10.showmo.data.source.local.entities.MovieEntity
import com.weslie10.showmo.databinding.ItemGridBinding
import com.weslie10.showmo.ui.main.movie.MovieCallback
import com.weslie10.showmo.utils.Utility.convertToDate
import com.weslie10.showmo.utils.Utility.loadImage

class MovieFavoriteAdapter(private val callback: MovieCallback) :
    PagedListAdapter<MovieEntity, MovieFavoriteAdapter.MoviesViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemGridBinding =
            ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemGridBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    inner class MoviesViewHolder(private val binding: ItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                itemTitle.text = movie.title
                itemDate.text = movie.releaseDate.convertToDate()
                itemOverview.text = movie.overview
                itemPoster.loadImage(movie.posterPath)
                itemView.setOnClickListener { callback.onMovieClick(movie.id) }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}