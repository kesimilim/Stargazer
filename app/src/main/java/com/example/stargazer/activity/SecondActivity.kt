package com.example.stargazer.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.stargazer.database.entity.RoomFavorite
import com.example.stargazer.database.entity.RoomStar
import com.example.stargazer.presenter.SecondPresenter
import com.example.stargazer.R
import com.example.stargazer.StarApplication
import com.example.stargazer.database.dao.FavoriteDao
import com.example.stargazer.retrofit.adapter.FavAdapter
import com.example.stargazer.retrofit.adapter.RepoAdapter
import com.example.stargazer.view.SecondView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.presenter.InjectPresenter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class SecondActivity : BaseActivity<SecondPresenter>(), SecondView {

    @InjectPresenter
    override lateinit var presenter: SecondPresenter

    @Inject lateinit var favDao: FavoriteDao

    init {
        StarApplication.appComponent.inject(this)
    }

    lateinit var barChartView: BarChart
    lateinit var userName: TextView
    lateinit var repoName: TextView
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    var star: ArrayList<RoomStar> = arrayListOf()
    private var boolFav = false

    private var Jan:Int = 0
    private var Feb:Int = 0
    private var Mar:Int = 0
    private var Apr:Int = 0
    private var May:Int = 0
    private var Jun:Int = 0
    private var Jul:Int = 0
    private var Aug:Int = 0
    private var Sep:Int = 0
    private var Oct:Int = 0
    private var Nov:Int = 0
    private var Dec:Int = 0

    private var mJan: ArrayList<RoomStar> = arrayListOf()
    private var mFeb: ArrayList<RoomStar> = arrayListOf()
    private var mMar: ArrayList<RoomStar> = arrayListOf()
    private var mApr: ArrayList<RoomStar> = arrayListOf()
    private var mMay: ArrayList<RoomStar> = arrayListOf()
    private var mJun: ArrayList<RoomStar> = arrayListOf()
    private var mJul: ArrayList<RoomStar> = arrayListOf()
    private var mAug: ArrayList<RoomStar> = arrayListOf()
    private var mSep: ArrayList<RoomStar> = arrayListOf()
    private var mOct: ArrayList<RoomStar> = arrayListOf()
    private var mNov: ArrayList<RoomStar> = arrayListOf()
    private var mDec: ArrayList<RoomStar> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val argument: Bundle = intent.extras!!

        userName = findViewById(R.id.a_userName)
        val login = argument.get("loginName").toString()
        userName.text = login

        repoName = findViewById(R.id.a_repoName)
        val repo = argument.get("repoName").toString()
        repoName.text = repo

        toolbar = findViewById(R.id.toolBar2)
        setSupportActionBar(toolbar)

        barChartView = findViewById(R.id.chartConsumptionGraph)

//        boolFav = argument.get("clickBool") as Boolean
//        if (boolFav) {
//            val clickUser = argument.get("clickUser").toString()
//            val clickRepo = argument.get("clickRepo").toString()
//            presenter.showStarChart(clickUser, clickRepo)
//            boolFav = false
//        }

        presenter.showStarChart(login, repo)

        findViewById<View>(R.id.buttonAddFavRepo).setOnClickListener {
            presenter.addFavRepo(login, repo)
        }
    }

    override fun addRepoToFavoriteList(login: String, repo: String) {

        if(inputCheck(repo)){
            //Create RoomFavorite Object
            val favRepo = RoomFavorite(
                0,
                login,
                repo
            )
            //Add Data to Database
            GlobalScope.launch(Dispatchers.IO){
                favDao.addFavRepo(favRepo)
            }

            Toast.makeText(this,"Successfully added!", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Error in added!!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(repo: String): Boolean{
        return !(TextUtils.isEmpty(repo))
    }

    private fun zeroState() {
        Jan = 0
        Feb = 0
        Mar = 0
        Apr = 0
        May = 0
        Jun = 0
        Jul = 0
        Aug = 0
        Sep = 0
        Oct = 0
        Nov = 0
        Dec = 0

        mJan.clear()
        mFeb.clear()
        mMar.clear()
        mApr.clear()
        mMay.clear()
        mJun.clear()
        mJul.clear()
        mAug.clear()
        mSep.clear()
        mOct.clear()
        mNov.clear()
        mDec.clear()
    }

    @SuppressLint("SimpleDateFormat")
    override fun dataChart(dataList: List<RoomStar>) {
        Log.d("test_10", "test2")
        val formater = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

        val calendar = Calendar.getInstance()
        dataList.forEach {
            val data: Date = formater.parse(it.starred_at) as Date
            calendar.time = data

            when (calendar.get(Calendar.MONTH)) {
                0 -> {
                    Jan += 1
                    mJan.add(it)
                }
                1 -> {
                    Feb += 1
                    mFeb.add(it)
                }
                2 -> {
                    Mar += 1
                    mMar.add(it)
                }
                3 -> {
                    Apr += 1
                    mApr.add(it)
                }
                4 -> {
                    May += 1
                    mMay.add(it)
                }
                5 -> {
                    Jun += 1
                    mJun.add(it)
                }
                6 -> {
                    Jul += 1
                    mJul.add(it)
                }
                7 -> {
                    Aug += 1
                    mAug.add(it)
                }
                8 -> {
                    Sep += 1
                    mSep.add(it)
                }
                9 -> {
                    Oct += 1
                    mOct.add(it)
                }
                10 -> {
                    Nov += 1
                    mNov.add(it)
                }
                11 -> {
                    Dec += 1
                    mDec.add(it)
                }
            }
        }
    }

    override fun buildChart() {
        val barWidth = 0.7f

        val xAxisValues = ArrayList<String>()
        xAxisValues.add("Jan")
        xAxisValues.add("Feb")
        xAxisValues.add("Mar")
        xAxisValues.add("Apr")
        xAxisValues.add("May")
        xAxisValues.add("Jun")
        xAxisValues.add("Jul")
        xAxisValues.add("Aug")
        xAxisValues.add("Sep")
        xAxisValues.add("Oct")
        xAxisValues.add("Nov")
        xAxisValues.add("Dec")

        val yValueGroup = ArrayList<BarEntry>()
        yValueGroup.add(BarEntry(1f, Jan.toFloat()))
        yValueGroup.add(BarEntry(2f, Feb.toFloat()))
        yValueGroup.add(BarEntry(3f, Mar.toFloat()))
        yValueGroup.add(BarEntry(4f, Apr.toFloat()))
        yValueGroup.add(BarEntry(5f, May.toFloat()))
        yValueGroup.add(BarEntry(6f, Jun.toFloat()))
        yValueGroup.add(BarEntry(7f, Jul.toFloat()))
        yValueGroup.add(BarEntry(8f, Aug.toFloat()))
        yValueGroup.add(BarEntry(9f, Sep.toFloat()))
        yValueGroup.add(BarEntry(10f, Oct.toFloat()))
        yValueGroup.add(BarEntry(11f, Nov.toFloat()))
        yValueGroup.add(BarEntry(12f, Dec.toFloat()))

        val barDataSet: BarDataSet
        barDataSet = BarDataSet(yValueGroup, "")
        barDataSet.setColors(Color.BLUE)
        barDataSet.setDrawIcons(false)
        barDataSet.setDrawValues(true)
        barDataSet.isHighlightEnabled = true

        val barData = BarData(barDataSet)
        barData.setValueFormatter(LargeValueFormatter())

        barChartView.description.isEnabled = false
        barChartView.description.textSize = 0f
        barData.setValueFormatter(LargeValueFormatter())
        barChartView.setData(barData)
        barChartView.getBarData().setBarWidth(barWidth)
        barChartView.getXAxis().setAxisMinimum(0f)
        barChartView.getXAxis().setAxisMaximum(12f)
        barChartView.getData().setHighlightEnabled(true)
        barChartView.invalidate()

        // set bar label
        val legend = barChartView.legend
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM)
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL)
        legend.setDrawInside(false)

        val legenedEntries = arrayListOf<LegendEntry>()
        legenedEntries.add(LegendEntry("2016", Legend.LegendForm.SQUARE, 8f, 8f, null, Color.GREEN))
        legend.setCustom(legenedEntries)

        legend.setYOffset(2f)
        legend.setXOffset(2f)
        legend.setYEntrySpace(0f)
        legend.setTextSize(5f)

        val xAxis = barChartView.getXAxis()
        xAxis.setGranularity(1f)
        xAxis.setGranularityEnabled(true)
        xAxis.setCenterAxisLabels(true)
        xAxis.setDrawGridLines(false)
        xAxis.textSize = 9f

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxis.setValueFormatter(IndexAxisValueFormatter(xAxisValues))

        xAxis.setLabelCount(12)
        xAxis.mAxisMaximum = 12f
        xAxis.setCenterAxisLabels(true)
        xAxis.setAvoidFirstLastClipping(true)
        xAxis.spaceMin = 4f
        xAxis.spaceMax = 4f

        barChartView.setVisibleXRangeMaximum(12f)
        barChartView.setVisibleXRangeMinimum(12f)
        barChartView.setDragEnabled(false)
        barChartView.setTouchEnabled(true)
        barChartView.isHighlightPerTapEnabled = true
        //Y-axis
        barChartView.getAxisRight().setEnabled(false)
        barChartView.setScaleEnabled(true)

        val leftAxis = barChartView.getAxisLeft()
        leftAxis.setValueFormatter(LargeValueFormatter())
        leftAxis.setDrawGridLines(false)
        leftAxis.setSpaceTop(1f)
        leftAxis.setAxisMinimum(0f)

        barChartView.data = barData
        barChartView.setVisibleXRange(1f, 12f)

        barChartView.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, h: Highlight) {
                val x:Int = barChartView.barData.getDataSetForEntry(e).getEntryIndex(e as BarEntry)
                getListMonthStargazer(x)
                if (star.size != 0){
                    Toast.makeText(this@SecondActivity, "Month ${x + 1}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SecondActivity, LastActivity::class.java)
                    intent.putExtra("StarList", star)
                    startActivity(intent)
                }
            }
            override fun onNothingSelected() {}
        })
    }

    private fun getListMonthStargazer(num:Int){
        star = when (num) {
            0 -> mJan
            1 -> mFeb
            2 -> mMar
            3 -> mApr
            4 -> mMay
            5 -> mJun
            6 -> mJul
            7 -> mAug
            8 -> mSep
            9 -> mOct
            10 -> mNov
            else -> mDec
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemView = item.itemId

        when(itemView) {
            R.id.repoFavList -> {
                val intent = Intent(this@SecondActivity, FavoriteActivity::class.java)
                startActivity(intent)
            }
        }
        return false
    }


}