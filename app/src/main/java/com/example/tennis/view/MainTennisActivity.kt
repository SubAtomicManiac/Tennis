package com.example.tennis.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tennis.library.controllers
import com.example.tennis.R
import com.example.tennis.viewmodel.TennisViewModel
import com.example.tennis.controller.TennisController
import com.example.tennis.databinding.CustomTennisGameBinding
import kotlinx.android.synthetic.main.activity_main_tennis.*
import kotlinx.android.synthetic.main.custom_tennis_game.*

class MainTennisActivity : AppCompatActivity() {
    private val viewModel : TennisViewModel by viewModels()
    private val controller : TennisController by controllers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tennis)
        setupGame()
        setupClickListeners()
    }

    private fun setupGame(){
        val binding = CustomTennisGameBinding.inflate(layoutInflater)
        binding.game = viewModel
        game_container.addView(binding.root)
        controller.createGame()
    }

    private fun setupClickListeners(){
        button_player_one.setOnClickListener { controller.addPointForPlayerOne() }
        button_player_two.setOnClickListener { controller.addPointForPlayerTwo() }
        button_reset.setOnClickListener{ controller.resetGame() }
    }

}
