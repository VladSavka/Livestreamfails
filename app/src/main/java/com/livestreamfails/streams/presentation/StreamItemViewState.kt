package com.livestreamfails.streams.presentation

import com.livestreamfails.streams.ui.StreamItemUI

sealed class StreamItemViewState {
    data class Form(val streamItemUI: StreamItemUI) : StreamItemViewState()
    object StartPlaying : StreamItemViewState()
    object PausePlaying : StreamItemViewState()
    data class Error(val errorMassage: String) : StreamItemViewState()
}
