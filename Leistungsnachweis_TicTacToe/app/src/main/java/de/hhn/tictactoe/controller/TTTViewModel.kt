package de.hhn.tictactoe.controller

import androidx.lifecycle.ViewModel
import de.hhn.tictactoe.model.Field
import de.hhn.tictactoe.model.GameModel
import de.hhn.tictactoe.model.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TTTViewModel : ViewModel() {
    private val _gameModel = MutableStateFlow(GameModel())
    private val _gameField = MutableStateFlow(Array(3) { Array(3) { Field() } })
    val gameModel: StateFlow<GameModel> = _gameModel.asStateFlow()
    val gameField: StateFlow<Array<Array<Field>>> = _gameField.asStateFlow()

    /**
     * Reset the game with new game model and game field
     */
    fun resetGame() {
        _gameModel.value = GameModel()
        _gameField.value = Array(3) { Array(3) { Field() } }
    }

    /**
     * Select a field if it is empty and the game is not ended.
     * Set the field status to current player and display the text and color.
     * Make a new game field with the new field status and the old fields.
     * Call checkEndingGame() to check if the game is ending.
     * Set the current player to the next player.
     */
    fun selectField(field: Field) {
        if (field.status != Status.Empty || _gameModel.value.isGameEnding) {
            return
        }
        field.status = _gameModel.value.currentPlayer

        val newGameField = Array(3) { i ->
            Array(3) { j ->
                if (_gameField.value[i][j] == field) {
                    Field(_gameModel.value.currentPlayer, i, j)
                } else {
                    Field(_gameField.value[i][j].status, i, j)
                }
            }
        }
        _gameField.value = newGameField
        checkEndingGame()
        _gameModel.value.currentPlayer = _gameModel.value.currentPlayer.next()

    }

    /**
     * Check the cases when the game is ending.
     * If the game is ending, set the game ending to true and the winning player to the current player.
     */
    private fun checkEndingGame() {
        for (i in 0..2) {
            if (_gameField.value[i][0].status != Status.Empty &&
                _gameField.value[i][0].status == _gameField.value[i][1].status &&
                _gameField.value[i][0].status == _gameField.value[i][2].status
            ) {
                _gameModel.value.isGameEnding = true
                _gameModel.value.winningPlayer = _gameModel.value.currentPlayer
                return
            }
        }
        for (i in 0..2) {
            if (_gameField.value[0][i].status != Status.Empty &&
                _gameField.value[0][i].status == _gameField.value[1][i].status &&
                _gameField.value[0][i].status == _gameField.value[2][i].status
            ) {
                _gameModel.value.isGameEnding = true
                _gameModel.value.winningPlayer = _gameModel.value.currentPlayer
                return
            }
        }
        if (_gameField.value[0][0].status != Status.Empty &&
            _gameField.value[0][0].status == _gameField.value[1][1].status &&
            _gameField.value[0][0].status == _gameField.value[2][2].status
        ) {
            _gameModel.value.isGameEnding = true
            _gameModel.value.winningPlayer = _gameModel.value.currentPlayer
            return
        }
        if (_gameField.value[0][2].status != Status.Empty &&
            _gameField.value[0][2].status == _gameField.value[1][1].status &&
            _gameField.value[0][2].status == _gameField.value[2][0].status
        ) {
            _gameModel.value.isGameEnding = true
            _gameModel.value.winningPlayer = _gameModel.value.currentPlayer
            return
        }
    }

}