package com.vanard.ovotask.ui.activity.detail

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.vanard.ovotask.base.BaseViewModel
import com.vanard.ovotask.data.model.video.Video
import com.vanard.ovotask.network.getYoutubeThumbnailPath
import com.vanard.ovotask.network.getYoutubeVideoPath

class VideoViewModel : BaseViewModel() {
    private val videoThumbnail = MutableLiveData<String>()
    private val videoTitle = MutableLiveData<String>()
    private val videoKey = MutableLiveData<String>()
    private val mVideo = MutableLiveData<Video>()

    fun bind(vid: Video){
        videoThumbnail.value = getYoutubeThumbnailPath(vid.key)
        videoTitle.value = vid.name
        videoKey.value = getYoutubeVideoPath(vid.key)
        mVideo.value = vid
    }

    fun getImageThumbnail():MutableLiveData<String>{
        return videoThumbnail
    }

    fun getVideoTitle():MutableLiveData<String>{
        return videoTitle
    }

    fun getVideoLink():MutableLiveData<String>{
        return videoKey
    }

    fun onClickItem(view: View) {
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoKey.value))
        view.context.startActivity(playVideoIntent)
    }

}