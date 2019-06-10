package com.livestreamfails.streams.presentation

import com.livestreamfails.streams.model.StreamResult
import com.livestreamfails.streams.ui.StreamItemUI

class StreamDataToUiMapper {
    fun toUi(model: StreamResult.Success) = StreamItemUI(
        model.title,
        model.url
    )
}


