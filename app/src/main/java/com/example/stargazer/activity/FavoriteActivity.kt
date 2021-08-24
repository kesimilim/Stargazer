package com.example.stargazer.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stargazer.database.AppDatabase
import com.example.stargazer.database.entity.RoomFavorite
import com.example.stargazer.R
import com.example.stargazer.StarApplication
import com.example.stargazer.database.dao.FavoriteDao
import com.example.stargazer.presenter.FavoritePresenter
import com.example.stargazer.retrofit.adapter.FavAdapter
import com.example.stargazer.view.FavoriteView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.presenter.InjectPresenter
import javax.inject.Inject

class FavoriteActivity : BaseActivity<FavoritePresenter>(), FavAdapter.OnItemClickListener, FavoriteView {

    @InjectPresenter
    override lateinit var presenter: FavoritePresenter

    @Inject lateinit var favDao: FavoriteDao

    init {
        StarApplication.appComponent.inject(this)
    }

    lateinit var adapter: FavAdapter
    lateinit var favList: RecyclerView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        favList = findViewById(R.id.favList)
        toolbar = findViewById(R.id.toolBar4)
        setSupportActionBar(toolbar)

        presenter.showFavoriteRepos()
    }

    override fun viewFavoriteList(repo: List<RoomFavorite>) {
        adapter = FavAdapter(this@FavoriteActivity,this@FavoriteActivity)
        adapter.setData(repo)
        favList.adapter = adapter
        favList.layoutManager = LinearLayoutManager(this@FavoriteActivity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemView = item.itemId

        when(itemView){
            R.id.repoFavList -> {
                Toast.makeText(this,"It's Favorite List", Toast.LENGTH_SHORT).show()
            }
        }
        return false
    }

    override fun onDelete(repo: RoomFavorite) {
        GlobalScope.launch(Dispatchers.IO) {
            favDao.deleteFavRepo(repo)
            presenter.showFavoriteRepos()
        }
    }

}