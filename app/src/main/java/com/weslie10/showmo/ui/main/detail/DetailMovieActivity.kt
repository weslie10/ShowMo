package com.weslie10.showmo.ui.main.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.weslie10.showmo.R
import com.weslie10.showmo.data.source.local.entities.MovieEntity
import com.weslie10.showmo.databinding.ActivityDetailMovieBinding
import com.weslie10.showmo.ui.main.movie.MovieViewModel
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
import java.text.NumberFormat
import java.util.*

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private val viewModelFactory = ViewModelFactory.getInstance(this)
    private val viewModel: MovieViewModel by viewModels { viewModelFactory }
    private var isShrink = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = "Detail Movie"
        }

        val id = intent.getIntExtra(EXTRA_ID, 0)
        viewModel.setId(id)
        viewModel.getSpecificMovie().observe(this, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> binding.loading.loadingDetail(true)
                    Status.SUCCESS -> {
                        binding.loading.loadingDetail(false)
                        if (movie.data != null) {
                            setData(movie.data)
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

    private fun setData(movie: MovieEntity) {
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))

        val genre: List<String> = movie.genres.split(",").map { it.trim() }
        val lang: List<String> = movie.spokenLanguages.split(",").map { it.trim() }

        with(binding) {
            detailMoviePoster.loadImage(movie.posterPath)
            detailMovieBackdrop.loadImage(movie.backdropPath)

            detailMovieTitle.text = resources.getString(
                R.string.titleDetail,
                movie.title,
                movie.releaseDate.convertToDate().split(",")[1]
            )
            detailMovieOriginalTitle.text = movie.originalTitle
            detailMovieTagLine.text = movie.tagline
            detailMovieDate.text = movie.releaseDate.convertToDate()

            with(detailMovieGenre) {
                removeAllViews()
                genre.map { addChip(this@DetailMovieActivity, it) }
            }
            with(detailMovieLanguage) {
                removeAllViews()
                lang.map { addChip(this@DetailMovieActivity, it) }
            }

            detailMovieDuration.text = resources.getString(
                R.string.durationDetail,
                movie.runtime.convertToTime()
            )
            val score = (movie.voteAverage * 10).toInt()
            detailMovieScore.text = if (movie.voteAverage != 0.0) resources.getString(
                R.string.scoreDetail,
                score.toString()
            ) else "NR"

            detailMovieBudget.text = format.format(movie.budget.toDouble())
            detailMovieRevenue.text = format.format(movie.revenue.toDouble())

            detailMovieOverview.apply {
                setText(movie.overview.convertEmpty())
                setOnStateChangeListener {
                    isShrink = it
                }
                setText(movie.overview.convertEmpty())
                resetState(isShrink)
            }
            detailMovieStatus.text = movie.status.convertEmpty()

            with(detailMovieHomepage) {
                text = movie.homepage.convertEmpty()
                if (text != "-") setLink("")
            }

            binding.progressBar.changeProgressBar(score)

            binding.btnFavorite.apply {
                setFavoriteState(movie.favorite)
                setOnClickListener {
                    viewModel.setFavorite(movie)
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