package com.weslie10.showmo.ui.main.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.weslie10.showmo.data.source.local.entities.MovieResponseEntity
import com.weslie10.showmo.databinding.ItemGridBinding
import com.weslie10.showmo.utils.Utility.convertToDate
import com.weslie10.showmo.utils.Utility.loadImage

class MovieAdapter(private val callback: MovieCallback) :
    PagedListAdapter<MovieResponseEntity, MovieAdapter.MoviesViewHolder>(DIFF_CALLBACK) {
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

    inner class MoviesViewHolder(private val binding: ItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResponseEntity) {
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieResponseEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieResponseEntity,
                newItem: MovieResponseEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieResponseEntity,
                newItem: MovieResponseEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}