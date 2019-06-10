package com.livestreamfails.streams.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.livestreamfails.R
import kotlinx.android.synthetic.main.activity_stream_main.*

class StreamMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stream_main)
        viewPager.adapter = StreamItemPagerAdapter(supportFragmentManager)
    }
}
