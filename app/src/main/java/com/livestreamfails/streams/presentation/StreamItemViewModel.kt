package com.livestreamfails.streams.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.livestreamfails.streams.model.StreamRepository
import com.livestreamfails.streams.model.StreamResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StreamItemViewModel(
    private val repository: StreamRepository,
    private val streamDataToUiMapper: StreamDataToUiMapper
) : ViewModel() {

    private var position: Int = 0
    private val _stateLiveData = MutableLiveData<StreamItemViewState>()
    val state: LiveData<StreamItemViewState> = _stateLiveData

    fun init(position: Int) {
        this.position = position
        loadStream()
    }

    private fun loadStream() {
        viewModelScope.launch {
            val streamResult = fetchStream()
            when (streamResult) {
                is StreamResult.Success ->
                    _stateLiveData.value = StreamItemViewState.Form(streamDataToUiMapper.toUi(streamResult))
                is StreamResult.Error ->
                    _stateLiveData.value = StreamItemViewState.Error(streamResult.errorMassage)
            }
        }
    }

    private suspend fun fetchStream() = withContext(Dispatchers.IO) {
        repository.getStreamById(position)
    }

    fun onScreenVisible() {
        if (state.value !is StreamItemViewState.Error)
            _stateLiveData.value = StreamItemViewState.StartPlaying
    }

    fun onScreenGone() {
        if (state.value !is StreamItemViewState.Error)
            _stateLiveData.value = StreamItemViewState.PausePlaying
    }

    fun onVideoClick(isCorrectlyPlaying: Boolean) {
        _stateLiveData.value = if (isCorrectlyPlaying) {
            StreamItemViewState.PausePlaying
        } else {
            StreamItemViewState.StartPlaying
        }
    }
}
