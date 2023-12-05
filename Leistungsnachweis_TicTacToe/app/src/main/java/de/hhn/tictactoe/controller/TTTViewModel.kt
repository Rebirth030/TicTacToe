package de.hhn.tictactoe.controller

import androidx.lifecycle.ViewModel
import de.hhn.tictactoe.model.Field
import de.hhn.tictactoe.model.GameModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TTTViewModel : ViewModel() {
    private val _gameModel = MutableStateFlow(GameModel())
    val gameModel : StateFlow<GameModel> = _gameModel.asStateFlow()
    fun resetGame() {}
    fun selectField(field: Field) {
    }
    fun checkEndingGame() {}
}