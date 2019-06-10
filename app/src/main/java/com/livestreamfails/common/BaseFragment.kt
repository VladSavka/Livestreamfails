package com.livestreamfails.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragment()
        observeState()
    }

    override fun onDestroyView() {
        unObserveState()
        super.onDestroyView()
    }

    abstract fun observeState()
    abstract fun unObserveState()
    abstract fun setUpFragment()

}