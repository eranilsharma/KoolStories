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
        holder.txtTimer.text="1:20"
        holder.videoView.setVideoURI(uri)
        holder.videoView.start()
        holder.progressBar.visibility=View.VISIBLE
        holder.videoView.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
            override fun onPrepared(mediaPlayer: MediaPlayer?) {
                holder.progressBar.visibility=View.GONE
                mediaPlayer?.start()
                val timer = object : CountDownTimer(120000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        holder.txtTimer.text=(millisUntilFinished / 1000).toString()
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
            }
        })


    }



    private fun convertMillieToHMmSs(timeInMillisec: Long): String {
        val seconds: Long = timeInMillisec / 1000
        val second = seconds % 60
        val minute = seconds / 60 % 60
        val hour = seconds / (60 * 60) % 24

        val result = ""
        return if (hour > 0) {
            String.format("%02d:%02d:%02d", hour, minute, second)
        } else {
            String.format("%02d:%02d", minute, second)
        }
    }

    override fun getItemCount(): Int = videoList.size



}