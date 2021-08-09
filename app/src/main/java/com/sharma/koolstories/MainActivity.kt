package com.sharma.koolstories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.sharma.koolstories.adapter.VideoAdapter
import com.sharma.koolstories.databinding.ActivityMainBinding
import com.sharma.koolstories.model.VideoModel

class MainActivity : AppCompatActivity() {
    lateinit var mainActivity: ActivityMainBinding
    var videoList:ArrayList<VideoModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        createData()
        setAdapter()
    }

    private fun setAdapter() {
      mainActivity.viewPager.adapter=VideoAdapter(this,videoList)
    }

    private fun createData() {
        val obj1 = VideoModel("https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4","Title1","\"This should be the accepted answer. Finally someone like Google Material came up with a rounded corners imageVview solution that kills all the badly written third party libraries people still use.  1")
        videoList.add(obj1)
        val obj2 = VideoModel("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4","Title2","\"This should be the accepted answer. Finally someone like Google Material came up with a rounded corners imageVview solution that kills all the badly written third party libraries people still use.  2")
        videoList.add(obj2)
        val obj3 = VideoModel("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4","Title3","\"This should be the accepted answer. Finally someone like Google Material came up with a rounded corners imageVview solution that kills all the badly written third party libraries people still use.  3")
        videoList.add(obj3)
        val obj4 = VideoModel("https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4","Title4","Description 4")
        videoList.add(obj4)
        val obj5 = VideoModel("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4","Title5","Description 5")
        videoList.add(obj5)
        val obj6 = VideoModel("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4","Title6","Description 6")
        videoList.add(obj6)
    }
}