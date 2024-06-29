package com.example.newscaster.Fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter (fragmentManager: FragmentManager,
                       lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager,lifecycle){
    override fun getItemCount(): Int {
        return 6
    }
    override fun createFragment(position: Int): Fragment {
        return  when (position) {
            0 -> {HomeFragment()}
            1 -> {SportsFragment()}
            2 -> {HealthFragment()}
            3 -> {EntertainmentFragment()}
            4 -> {ScienceFragment()}
            5 -> {TechnologyFragment()}
            else-> {
                Fragment()
            }


        }

    }

}