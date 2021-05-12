package com.weslie10.showmo.ui.main.favorite.tvshow

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
import com.weslie10.showmo.databinding.FragmentTvShowFavoriteBinding
import com.weslie10.showmo.ui.main.detail.DetailTvShowActivity
import com.weslie10.showmo.ui.main.tvshow.TvShowCallback
import com.weslie10.showmo.ui.main.tvshow.TvShowViewModel
import com.weslie10.showmo.utils.Utility.notFound
import com.weslie10.showmo.viewmodel.ViewModelFactory

class TvShowFavoriteFragment : Fragment(), TvShowCallback {
    private lateinit var binding: FragmentTvShowFavoriteBinding
    private lateinit var tvShowAdapter: TvShowFavoriteAdapter
    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvTvShows)

        if (activity != null) {
            val viewModelFactory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, viewModelFactory)[TvShowViewModel::class.java]
            tvShowAdapter = TvShowFavoriteAdapter(this)

            viewModel.getFavorite().observe(viewLifecycleOwner, { tvShow ->
                if (tvShow.isNotEmpty()) {
                    binding.tvshowNotFound.notFound(false)
                    tvShowAdapter.submitList(tvShow)
                    tvShowAdapter.notifyDataSetChanged()
                } else binding.tvshowNotFound.notFound(true)
            })
            with(binding.rvTvShows) {
                this.layoutManager = LinearLayoutManager(context)
                this.setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }
    }

    override fun onTvShowClick(id: Int) {
        val moveIntent = Intent(activity, DetailTvShowActivity::class.java)
        moveIntent.putExtra(DetailTvShowActivity.EXTRA_ID, id)
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
                val tvShowEntity = tvShowAdapter.getSwipedData(swipedPosition)
                tvShowEntity?.let { viewModel.setFavorite(it) }
                Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG).apply {
                    setAction(R.string.message_ok) { tvShowEntity?.let { viewModel.setFavorite(it) } }
                    show()
                }
            }
        }
    })
}