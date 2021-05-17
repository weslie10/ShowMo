package com.weslie10.showmo.ui.main.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.weslie10.showmo.R
import com.weslie10.showmo.data.source.local.entities.TvShowEntity
import com.weslie10.showmo.databinding.ActivityDetailTvShowBinding
import com.weslie10.showmo.ui.main.tvshow.TvShowViewModel
import com.weslie10.showmo.utils.Utility.addChip
import com.weslie10.showmo.utils.Utility.changeProgressBar
import com.weslie10.showmo.utils.Utility.convertEmpty
import com.weslie10.showmo.utils.Utility.convertToDate
import com.weslie10.showmo.utils.Utility.convertToTime
import com.weslie10.showmo.utils.Utility.loadImage
import com.weslie10.showmo.utils.Utility.loadingDetail
import com.weslie10.showmo.utils.Utility.setFavoriteState
import com.weslie10.showmo.utils.Utility.setLink
import com.weslie10.showmo.viewmodel.ViewModelFactory
import com.weslie10.showmo.vo.Status

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTvShowBinding
    private val viewModelFactory = ViewModelFactory.getInstance(this)
    private val viewModel: TvShowViewModel by viewModels { viewModelFactory }
    private var isShrink = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = "Detail TV Show"
        }
        val id = intent.getIntExtra(EXTRA_ID, 0)
        viewModel.setId(id)
        viewModel.getSpecificTvShow().observe(this, { tvShow ->
            if (tvShow != null) {
                when (tvShow.status) {
                    Status.LOADING -> binding.loading.loadingDetail(true)
                    Status.SUCCESS -> {
                        binding.loading.loadingDetail(false)
                        if (tvShow.data != null) {
                            setData(tvShow.data)
                        }
                    }
                    Status.ERROR -> {
                        binding.loading.loadingDetail(false)
                        Toast.makeText(this, "There's an error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setData(tvShow: TvShowEntity) {
        val genre: List<String> = tvShow.genres.split(",").map { it.trim() }
        val lang: List<String> = tvShow.spokenLanguages.split(",").map { it.trim() }

        with(binding) {
            detailTvShowPoster.loadImage(tvShow.posterPath)
            detailTvShowBackdrop.loadImage(tvShow.backdropPath)

            detailTvShowTitle.text = resources.getString(
                R.string.titleDetail,
                tvShow.name,
                tvShow.firstAirDate.convertToDate().split(",")[1]
            )
            detailTvShowOriginalTitle.text = tvShow.originalName
            detailTvShowTagLine.text = tvShow.tagLine
            detailTvShowDate.text = tvShow.firstAirDate.convertToDate()
            detailTvShowLastDate.text = tvShow.lastAirDate.convertToDate()

            with(detailTvShowGenre) {
                removeAllViews()
                genre.map { addChip(this@DetailTvShowActivity, it) }
            }
            with(detailTvShowLanguage) {
                removeAllViews()
                lang.map { addChip(this@DetailTvShowActivity, it) }
            }

            detailTvShowDuration.text = resources.getString(
                R.string.durationDetail,
                tvShow.episodeRunTime.convertToTime()
            )
            val score = (tvShow.voteAverage * 10).toInt()
            detailTvShowScore.text = if (tvShow.voteAverage != 0.0) resources.getString(
                R.string.scoreDetail,
                score.toString()
            ) else "NR"

            detailTvShowStatus.text = tvShow.status.convertEmpty()
            detailTvShowType.text = tvShow.type.convertEmpty()
            detailTvShowSeason.text = tvShow.numberOfSeasons.toString()

            detailTvShowOverview.apply {
                setText(tvShow.overview.convertEmpty())
                setOnStateChangeListener {
                    isShrink = it
                }
                setText(tvShow.overview.convertEmpty())
                resetState(isShrink)
            }

            with(detailTvShowHomepage) {
                text = tvShow.homepage.convertEmpty()
                if (text != "-") setLink("")
            }

            binding.progressBar.changeProgressBar(score)

            binding.btnFavorite.apply {
                setFavoriteState(tvShow.favorite)
                setOnClickListener {
                    viewModel.setFavorite(tvShow)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_ID = ""
    }
}