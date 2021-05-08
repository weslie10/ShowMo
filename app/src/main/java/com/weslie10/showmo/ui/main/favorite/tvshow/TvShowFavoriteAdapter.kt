package com.weslie10.showmo.ui.main.favorite.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.weslie10.showmo.data.source.local.entities.TvShowEntity
import com.weslie10.showmo.databinding.ItemGridBinding
import com.weslie10.showmo.ui.main.tvshow.TvShowCallback
import com.weslie10.showmo.utils.Utility.convertToDate
import com.weslie10.showmo.utils.Utility.loadImage

class TvShowFavoriteAdapter(private val callback: TvShowCallback) :
    PagedListAdapter<TvShowEntity, TvShowFavoriteAdapter.TvShowsViewHolder>(
        DIFF_CALLBACK
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val itemGridBinding =
            ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemGridBinding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(
                oldItem: TvShowEntity,
                newItem: TvShowEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TvShowEntity,
                newItem: TvShowEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)

    inner class TvShowsViewHolder(private val binding: ItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                itemTitle.text = tvShow.name
                itemDate.text = tvShow.firstAirDate.convertToDate()
                itemOverview.text = tvShow.overview
                itemPoster.loadImage(tvShow.posterPath)
                itemView.setOnClickListener { callback.onTvShowClick(tvShow.id) }
            }
        }
    }
}