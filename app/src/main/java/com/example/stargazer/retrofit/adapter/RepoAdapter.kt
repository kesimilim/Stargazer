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
import com.example.stargazer.database.entity.RoomRepo
import com.example.stargazer.R
import com.example.stargazer.database.entity.RoomFavorite

class RepoAdapter (private val userRepos: List<RoomRepo>, val context: Context, val listener: OnItemClickListenerRepo):
    RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return userRepos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = userRepos[position]
        holder.nameRepos.text = repo.name

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "You clicked on repository #${position + 1}", Toast.LENGTH_SHORT).show()
            val model = userRepos[position]
            val repoUser = model.name
//            val intent = Intent(context, SecondActivity::class.java)
//            intent.putExtra("repoName", repoUser)
//            context.startActivity(intent)

            listener.clickRepo(repoUser)

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameRepos = itemView.findViewById<TextView>(R.id.reposText)!!

    }

    interface OnItemClickListenerRepo{
        fun clickRepo(repo: String)
    }
}