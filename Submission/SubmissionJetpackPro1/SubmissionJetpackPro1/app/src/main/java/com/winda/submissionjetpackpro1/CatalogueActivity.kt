package com.winda.submissionjetpackpro1

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.winda.submissionjetpackpro1.databinding.ActivityCatalogueBinding
import com.winda.submissionjetpackpro1.ui.main.SectionsPagerAdapter

class CatalogueActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCatalogueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatalogueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTabbedLayout()
    }

    private fun setTabbedLayout(){
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        supportActionBar?.elevation = 0f
    }
}