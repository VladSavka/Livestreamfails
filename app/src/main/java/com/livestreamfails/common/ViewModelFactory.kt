package com.livestreamfails.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.livestreamfails.streams.model.StreamRepository
import com.livestreamfails.streams.presentation.StreamDataToUiMapper
import com.livestreamfails.streams.presentation.StreamItemViewModel

/**
 * Custom factory to provide needed arguments to View Model's constructor
 */
class MyViewModelFactory(
    private val repository: StreamRepository,
    private val mapper: StreamDataToUiMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StreamItemViewModel(repository, mapper) as T
    }
}
