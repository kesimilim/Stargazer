package com.example.stargazer.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stargazer.R
import com.example.stargazer.StarApplication
import com.example.stargazer.database.dao.FavoriteDao
import com.example.stargazer.database.entity.RoomFavorite
import com.example.stargazer.database.entity.RoomStar
import com.example.stargazer.presenter.LastPresenter
import com.example.stargazer.presenter.SecondPresenter
import com.example.stargazer.retrofit.adapter.StarAdapter
import com.example.stargazer.view.LastView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.presenter.InjectPresenter
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class LastActivity : BaseActivity<LastPresenter>(), LastView {

    @InjectPresenter
    override lateinit var presenter: LastPresenter

    @Inject lateinit var favoritesDao: FavoriteDao

    init {
        StarApplication.appComponent.inject(this)
    }

    lateinit var adapter: StarAdapter
    lateinit var starList: RecyclerView
    lateinit var toolbar: Toolbar
    private var repo = emptyList<RoomFavorite>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last)
        starList = findViewById(R.id.starList)
        toolbar = findViewById(R.id.toolBar3)
        setSupportActionBar(toolbar)

        val argument: Bundle = intent.extras!!
        val stargazerList = argument.get("StarList")
        //val stargazerList = star

        adapter = StarAdapter(stargazerList as List<RoomStar>, this@LastActivity)
        starList.adapter = adapter
        starList.layoutManager = LinearLayoutManager(this@LastActivity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemView = item.itemId

        when(itemView){
            R.id.repoFavList -> {
                val intent = Intent(this@LastActivity, FavoriteActivity::class.java)
                @Suppress("NAME_SHADOWING")
                GlobalScope.launch(Dispatchers.IO){
                    repo = favoritesDao.readAllFavRepo()
                    intent.putExtra("FavList", repo as ArrayList)
                }
                startActivity(intent)
            }

        }
        return false
    }

}