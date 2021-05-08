package com.weslie10.showmo.utils

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.weslie10.showmo.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Utility {

    private const val BASE_URL = "https://image.tmdb.org/t/p/w500"
    private const val LOW_SCORE = 40
    private const val MEDIUM_SCORE = 70
    private const val HIGH_SCORE = 100

    fun ImageView.loadImage(path: String) {
        Glide.with(context)
            .load("${BASE_URL}/${path}")
            .apply(
                RequestOptions
                    .placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(this)
    }


    fun String.convertEmpty(): String {
        return if (this != "") this else "-"
    }

    fun Int.convertToTime(): String {
        return "${if (this / 60 > 0) "${this / 60}h " else ""}${this % 60}m"
    }

    fun String.convertToDate(): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
        val date = LocalDate.parse(this)
        return date.format(formatter)
    }

    fun ProgressBar.changeProgressBar(scoreProgressBar: Int?) {
        val score = scoreProgressBar as Int
        progressDrawable.colorFilter =
            BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                when (score) {
                    in 1 until LOW_SCORE -> Color.RED
                    in LOW_SCORE until MEDIUM_SCORE -> Color.YELLOW
                    in MEDIUM_SCORE until HIGH_SCORE -> Color.GREEN
                    else -> Color.GRAY
                }, BlendModeCompat.SRC_IN
            )
        progress = if (score != 0) score else 100
    }

    private fun view(state: Boolean): Int {
        return if (state) View.VISIBLE else View.GONE
    }

    fun ConstraintLayout.loadingDetail(state: Boolean) {
        visibility = view(state)
    }

    fun ConstraintLayout.notFound(state: Boolean) {
        visibility = view(state)
    }

    fun CardView.loading(state: Boolean) {
        visibility = view(state)
    }

    fun ChipGroup.addChip(context: Context, label: String) {
        Chip(context).apply {
            id = View.generateViewId()
            text = label
            isClickable = false
            isCheckable = false
            isFocusable = false
            chipBackgroundColor =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.orange))
            addView(this)
        }
    }

    fun TextView.setLink(uri: String) {
        paintFlags =
            paintFlags or Paint.UNDERLINE_TEXT_FLAG

        setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uri + text)
            context.startActivity(intent)
        }
    }

    fun FloatingActionButton.setFavoriteState(state: Boolean) {
        if (state) {
            setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_favorite_fill
                )
            )
            setColorFilter(Color.RED)
        } else {
            setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_favorite_empty
                )
            )
            setColorFilter(Color.BLACK)
        }
    }
}