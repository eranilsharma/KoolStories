package com.sharma.koolstories.adapter

import android.content.Context
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.sharma.koolstories.R
import com.sharma.koolstories.model.VideoModel


class VideoAdapter (val context: Context, val videoList:ArrayList<VideoModel>):
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {


    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle:TextView=itemView.findViewById(R.id.txtTitle)
        var txtDesc:TextView=itemView.findViewById(R.id.txtDesc)
        var txtTimer:TextView=itemView.findViewById(R.id.txtTimer)
        var videoView:VideoView=itemView.findViewById(R.id.videoView)
        var progressBar:ProgressBar=itemView.findViewById(R.id.progressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder =
        VideoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_video_row, parent, false))


    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) = holder.itemView.run{
        holder.txtTitle.text=videoList.get(position).videoTitle
        holder.txtDesc.text=videoList.get(position).videoDesc
        val uri:Uri=Uri.parse(videoList.get(position).videoUrl)
        val duration:String=convertMillieToHMmSs(getDuration(videoList.get(position).videoUrl))
        holder.txtTimer.text= duration
        holder.videoView.setVideoURI(uri)
        holder.videoView.start()
        holder.progressBar.visibility=View.VISIBLE
        holder.videoView.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
            override fun onPrepared(mediaPlayer: MediaPlayer?) {
                holder.progressBar.visibility=View.GONE
                mediaPlayer?.start()
                val timer = object : CountDownTimer(getDuration(videoList.get(position).videoUrl), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        holder.txtTimer.text=convertMillieToHMmSs(millisUntilFinished)
                    }

                    override fun onFinish() {

                    }
                }
                timer.start()


                }

        })
        holder.videoView.setOnCompletionListener(object :MediaPlayer.OnCompletionListener{
            override fun onCompletion(mediaPlayer: MediaPlayer?) {
                mediaPlayer?.start()
                holder.txtTimer.text= duration
                val timer = object : CountDownTimer(getDuration(videoList.get(position).videoUrl), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        holder.txtTimer.text=convertMillieToHMmSs(millisUntilFinished)
                    }

                    override fun onFinish() {

                    }
                }
                timer.start()
            }
        })


    }

    private fun getDuration(videoUrl: String): Long {

        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(videoUrl, HashMap())
        val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        val timeInMillisec = time!!.toLong()
        retriever.release()
        return timeInMillisec
    }


    private fun convertMillieToHMmSs(timeInMillisec: Long): String {
        val minutes: Long = timeInMillisec / 1000 / 60

       val seconds: Long = timeInMillisec / 1000 % 60

        val result = ""
        return if (minutes > 0) {
           minutes.toString()+":"+seconds.toString()
        } else  {
             "00:"+seconds.toString()
        }
    }

    override fun getItemCount(): Int = videoList.size



}