package com.example.stargazer.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stargazer.database.entity.RoomRepo
import com.example.stargazer.presenter.MainPresenter
import com.example.stargazer.R
import com.example.stargazer.StarApplication
import com.example.stargazer.database.dao.FavoriteDao
import com.example.stargazer.database.entity.RoomFavorite
import com.example.stargazer.retrofit.RepoServise
import com.example.stargazer.retrofit.adapter.RepoAdapter
import com.example.stargazer.view.MainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.presenter.InjectPresenter
import javax.inject.Inject

//var login: String = ""

class MainActivity : BaseActivity<MainPresenter>(), MainView, RepoAdapter.OnItemClickListenerRepo {

    @InjectPresenter
    override lateinit var presenter: MainPresenter

    @Inject lateinit var favDao: FavoriteDao

    init {
        StarApplication.appComponent.inject(this)
    }

    lateinit var adapter: RepoAdapter
    lateinit var reposList: RecyclerView
    lateinit var editText: EditText
    lateinit var toolbar: Toolbar
    lateinit var progressBar: ProgressBar

    private var login = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reposList = findViewById(R.id.reposList)
        editText = findViewById(R.id.edit_id)
        toolbar = findViewById(R.id.toolBar)
        progressBar = findViewById(R.id.progressBar)

        setSupportActionBar(toolbar)
        progressBar.setVisibility(View.GONE)

        findViewById<View>(R.id.button_id).setOnClickListener {
            login = editText.text.toString()
            presenter.showRepos(login)
        }
    }

    override fun clickRepo(repo: String) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("loginName", login)
        intent.putExtra("repoName", repo)
        startActivity(intent)
    }

    override fun viewRepos(repos: List<RoomRepo>) {
        //Toast.makeText(this,"Repo!", Toast.LENGTH_SHORT).show()
        adapter = RepoAdapter(repos, this@MainActivity, this@MainActivity)
        reposList.adapter = adapter
        reposList.layoutManager = LinearLayoutManager(this@MainActivity)

    }

    override fun viewError() {
        Toast.makeText(this,"Error!", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemView = item.itemId

        when(itemView){
            R.id.repoFavList -> {
                val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
                @Suppress("NAME_SHADOWING")
                GlobalScope.launch(Dispatchers.IO) {
                    val repo = favDao.readAllFavRepo()
                    intent.putExtra("FavList", repo as ArrayList)
                }
                startActivity(intent)
            }
        }
        return false
    }

}