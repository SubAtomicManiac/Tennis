package com.example.tennis.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tennis.R

class CustomTennisGameScore(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs){
    init {
        inflate(context, R.layout.custom_tennis_game_score, this)
    }
}
