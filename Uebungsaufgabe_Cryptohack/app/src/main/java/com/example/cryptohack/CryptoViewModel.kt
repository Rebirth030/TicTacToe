package com.example.cryptohack

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.cryptohack.network.Asset
import com.example.cryptohack.network.CryptoCurrency
import com.example.cryptohack.view.loadData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {
    private val _crypto = MutableStateFlow(listOf<CryptoCurrency>())
    val crypto: StateFlow<List<CryptoCurrency>> = _crypto.asStateFlow()

    fun reload() {
        loadData(_crypto)
    }
}
