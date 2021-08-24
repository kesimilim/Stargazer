package com.example.stargazer.retrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stargazer.database.entity.RoomStar
import com.example.stargazer.R
import com.squareup.picasso.Picasso

class StarAdapter (val starList: List<RoomStar>, val context: Context):
    RecyclerView.Adapter<StarAdapter.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bildItems(model: RoomStar){
            itemView.findViewById<TextView>(R.id.loginText).text = model.user.login
            itemView.findViewById<TextView>(R.id.idText).text = model.user.id.toString()
            itemView.findViewById<TextView>(R.id.textData).text = model.starred_at
            val imageView = itemView.findViewById<ImageView>(R.id.imageStargazer)

            Picasso.with(itemView.context)
                .load(model.user.avatar_url)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.stargazers_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bildItems(starList[position])
    }

    override fun getItemCount(): Int {
        return starList.size
    }
}