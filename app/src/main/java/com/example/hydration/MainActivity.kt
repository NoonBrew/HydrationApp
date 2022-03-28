package com.example.hydration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var waterViewPager: ViewPager2
    private lateinit var waterTabLayout: TabLayout

    private val waterViewModel by lazy {
        // WaterViewModelFactory has reference to the application and defines it as a
        // HydrationApplication that allows it to access the repository link of the application object
        // Application has access to the WaterDao and the WaterDatabase
        val app = application as HydrationApplication
        WaterViewModelFactory(app.waterRepository, app.daysRepository)
            .create(WaterViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        waterViewPager = findViewById(R.id.water_view_pager)
        waterTabLayout = findViewById(R.id.water_days_tab_layout)

        // Holds the string array
//        val days = resources.getStringArray(R.array.days)
        val days = waterViewModel.getWeekdays()
        // Creates a instance of our WaterViewPageAdapter passing the activity that will display the
        // fragment (mainActivity) and the String Array that will be used to create new instances
        // of the fragments.
        val pagerAdapter = WaterViewPagerAdapter(this, days)

        // Links our viewContainer as an adapter to display and recycle different fragments created
        // by our pagerAdapter.
        waterViewPager.adapter = pagerAdapter

        // Sets the tab text of our TabLayout to the days.
        TabLayoutMediator(waterTabLayout, waterViewPager) { tab, position ->
            tab.text = days[position]
        }.attach()

        val todayIndex = waterViewModel.getTodayIndex()
        waterViewPager.setCurrentItem(todayIndex, false)

//        val tues = WaterRecord("Tuesday", 3)
//        waterViewModel.insertNewRecord(tues)
//
//        val wed = WaterRecord("Wednesday", 2)
//        waterViewModel.insertNewRecord(wed)

        // Observes the database and passes displays the record data from the database using
        // logcat.
        waterViewModel.allRecords.observe(this){ records ->
            Log.d("MAIN_ACTIVITY", "$records")
        }

//        supportFragmentManager.beginTransaction()
//            .add(R.id.dayContent, HydrationFragment.newInstance("Friday"))
//            .commit()

    }
}