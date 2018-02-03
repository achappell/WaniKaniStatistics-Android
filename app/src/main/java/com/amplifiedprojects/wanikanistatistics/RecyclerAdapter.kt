package com.amplifiedprojects.wanikanistatistics

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

/**
 * Created by amanda on 2/2/18.
 */

class RecyclerAdapter(private val levelProgressions: List<WaniKaniService.LevelProgressionData>) : RecyclerView.Adapter<RecyclerAdapter.LevelProgressionHolder>()  {

    override fun getItemCount() = levelProgressions.size

    override fun onBindViewHolder(holder: RecyclerAdapter.LevelProgressionHolder, position: Int) {
        val levelProgression = levelProgressions[position]
        holder.bindLevelProgression(levelProgression)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerAdapter.LevelProgressionHolder {
        val inflatedView = parent!!.inflate(R.layout.recyclerview_item_row, false)
        return LevelProgressionHolder(inflatedView)
    }

    class LevelProgressionHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var levelProgression: WaniKaniService.LevelProgressionData? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        fun bindLevelProgression(levelProgression: WaniKaniService.LevelProgressionData) {
            this.levelProgression = levelProgression
            view.itemDate.text = levelProgression.started_at
            view.itemDescription.text = levelProgression.level.toString()
        }
    }
}