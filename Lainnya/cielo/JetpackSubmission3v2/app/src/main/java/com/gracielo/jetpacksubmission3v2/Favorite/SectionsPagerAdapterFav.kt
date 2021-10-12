package com.gracielo.jetpacksubmission3v2.Favourite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class SectionsPagerAdapterFav(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {

        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MoviesFavourite()
            1 -> fragment = TVFavourite()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }


}


