package com.livestreamfails.streams.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.livestreamfails.Injection
import com.livestreamfails.R
import com.livestreamfails.common.BaseFragment
import com.livestreamfails.common.MyViewModelFactory
import com.livestreamfails.streams.presentation.StreamItemViewModel
import com.livestreamfails.streams.presentation.StreamItemViewState
import kotlinx.android.synthetic.main.stream_item_fragment.videoView
import kotlinx.android.synthetic.main.stream_item_fragment.errorGroup as errorViewGroup
import kotlinx.android.synthetic.main.stream_item_fragment.streamPlayButtonView as playButton
import kotlinx.android.synthetic.main.stream_item_fragment.videoTitleText as titleText

private const val ARG_POSITION = "position"

class StreamItemFragment : BaseFragment() {

    private lateinit var viewModel: StreamItemViewModel

    private val streamItemStateObserver by lazy { Observer<StreamItemViewState> { state -> updateViewByState(state) } }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.stream_item_fragment, container, false)
    }

    override fun observeState() {
        viewModel.state.observe(this, streamItemStateObserver)
    }

    override fun unObserveState() {
        viewModel.state.removeObserver(streamItemStateObserver)
    }

    override fun setUpFragment() {
        viewModel = ViewModelProviders
            .of(this, MyViewModelFactory(Injection.provideTasksRepository(), Injection.provideStreamDataToUiMapper()))
            .get(StreamItemViewModel::class.java)

        val position = arguments?.getInt(ARG_POSITION) ?: throw IllegalArgumentException("Argument isn't provided")
        viewModel.init(position + 1)

        videoView.setOnClickListener { viewModel.onVideoClick(videoView.isPlaying) }
    }

    private fun updateViewByState(state: StreamItemViewState) {
        setInitialViewState()
        when (state) {
            is StreamItemViewState.Form -> {
                state.streamItemUI.apply {
                    titleText.text = videoTitle
                    initVideoPlayer(videoUrl)
                }
            }
            is StreamItemViewState.StartPlaying -> playVideo()
            is StreamItemViewState.PausePlaying -> pauseVideo()
            is StreamItemViewState.Error -> showError(state.errorMassage)
        }
    }

    private fun setInitialViewState() {
        videoView.visibility = View.VISIBLE
        errorViewGroup.visibility = View.GONE
        playButton.visibility = View.GONE
    }

    private fun initVideoPlayer(videoUrl: String) {
        videoView.setVideoURI(Uri.parse(videoUrl))
    }

    private fun pauseVideo() {
        videoView.pause()
        playButton.visibility = View.VISIBLE
    }

    private fun playVideo() {
        videoView.start()
    }

    private fun showError(errorMassage: String) {
        videoView.visibility = View.GONE
        errorViewGroup.visibility = View.VISIBLE
        Toast.makeText(requireContext(), errorMassage, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onScreenVisible()
    }


    override fun onPause() {
        super.onPause()
        viewModel.onScreenGone()
    }

    companion object {
        fun newInstance(position: Int) = StreamItemFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_POSITION, position)
            }
        }
    }
}
