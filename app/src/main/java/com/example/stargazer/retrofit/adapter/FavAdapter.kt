package com.example.stargazer.retrofit.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.stargazer.activity.SecondActivity
import com.example.stargazer.database.entity.RoomFavorite
import com.example.stargazer.R

class FavAdapter(
        private val listener: OnItemClickListener,
        val context: Context
        ): RecyclerView.Adapter<FavAdapter.ViewHolder>() {

    private var favRepoList = emptyList<RoomFavorite>()

    fun setData(data: List<RoomFavorite>){
        this.favRepoList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.for_fragment, parent, false)
        return ViewHolder(v, listener)
    }

    override fun getItemCount(): Int {
        return favRepoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = favRepoList[position]
        holder.itemView.findViewById<TextView>(R.id.favReposText).text = currentItem.favRepo
        holder.bind(favRepoList[position])

        holder.nameRepos.text = currentItem.favRepo

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "You clicked on repository #${position + 1}", Toast.LENGTH_SHORT).show()
//            val model = favRepoList[position]
//            val user = model.userName
//            val repo = model.favRepo
//
//            val intent = Intent(context, SecondActivity::class.java)
//
//            intent.putExtra("clickBool", true)
//            intent.putExtra("clickUser", user)
//            intent.putExtra("clickRepo", repo)
//
//            context.startActivity(intent)
        }
    }
    class ViewHolder(itemView: View, private val listener: OnItemClickListener):
            RecyclerView.ViewHolder(itemView) {
        private val delete = itemView.findViewById<View>(R.id.buttonDelete)!!
        val nameRepos = itemView.findViewById<TextView>(R.id.favReposText)!!

        fun bind(data: RoomFavorite){
            delete.setOnClickListener {
                listener.onDelete(data)
            }
        }
    }

    interface OnItemClickListener{
        fun onDelete(repo: RoomFavorite)
    }

}