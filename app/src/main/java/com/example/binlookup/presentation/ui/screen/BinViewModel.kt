package com.example.binlookup.presentation.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlookup.domain.BinRepository
import com.example.binlookup.domain.model.BinInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BinViewModel(
    private val repository: BinRepository
) : ViewModel() {

    private val _binInfo = MutableStateFlow<BinInfo?>(null)
    val binInfo: StateFlow<BinInfo?> = _binInfo.asStateFlow()

    private val _binHistory = MutableStateFlow<List<BinInfo>>(emptyList())
    val binHistory: StateFlow<List<BinInfo>> = _binHistory.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadBinHistory()
    }

    fun lookupBin(bin: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val info = repository.getBinInfo(bin)
                _binInfo.value = info
                repository.saveBinInfo(info)
                loadBinHistory()
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun loadBinHistory() {
        viewModelScope.launch {
            _binHistory.value = repository.getBinHistory()
        }
    }
}
