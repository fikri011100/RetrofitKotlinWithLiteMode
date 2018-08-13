package com.santriprogrammer.retrokotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.santriprogrammer.retrokotlin.R
import com.santriprogrammer.retrokotlin.model.News
import com.squareup.picasso.Picasso

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    private var data = mutableListOf<News.Item>()
    private var status: Int? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainHolder {
        return MainHolder(
                LayoutInflater.from(p0.context).inflate(R.layout.item_main, p0, false)
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder?.bindData(data[position])
        if (status != null) {
            if (status == 0) {
                holder.image.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = data.size

    fun addData(news: List<News.Item>?, status: Int?) {
        if (news != null)
            data.addAll(news)
        this.status = status
        notifyDataSetChanged()
    }

    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title = view.findViewById<TextView>(R.id.item_text_title)
        val desc = view.findViewById<TextView>(R.id.item_desc)
        val image = view.findViewById<ImageView>(R.id.item_image)

        fun bindData(item: News.Item) {
            title.text = item.title
            desc.text = item.description
            Picasso.get().load(item.urlToImage).into(image)
        }

    }

}