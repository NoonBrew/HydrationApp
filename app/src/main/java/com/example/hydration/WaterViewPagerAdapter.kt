package com.example.hydration

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// Fragment activity is any activity that can display a fragment.
class WaterViewPagerAdapter(activity: FragmentActivity, private val days: Array<String>):
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return days.size
    }

    override fun createFragment(position: Int): Fragment {
        // Grabs the element at the position in the array
        val day = days[position]
        // Passes that string value to create a new fragment using that day as the
        // key to the database.
        return HydrationFragment.newInstance(day)
    }

}