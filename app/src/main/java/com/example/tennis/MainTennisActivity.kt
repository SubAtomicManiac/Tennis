package com.example.tennis

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tennis.databinding.CustomTennisGameScoreBinding
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.activity_main_tennis.*
import kotlinx.android.synthetic.main.custom_tennis_game_score.*

class MainTennisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tennis)
    }

    override fun onStart() {
        super.onStart()
        val binding = CustomTennisGameScoreBinding.inflate(layoutInflater)
        val model : TennisViewModel by viewModels()
        val controller : GameController by viewModels()
        binding.game = model
        game_container.addView(binding.root)
        player_one_button.setOnClickListener { controller.addPointForPlayerOne() }
        player_two_button.setOnClickListener { controller.addPointForPlayerTwo() }

    }
}
