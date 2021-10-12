package com.winda.submissionjetpackpro1.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.winda.submissionjetpackpro1.DetailActivity
import com.winda.submissionjetpackpro1.adapter.RecyclerShowAdapter
import com.winda.submissionjetpackpro1.data.Show
import com.winda.submissionjetpackpro1.data.ShowsViewModel
import com.winda.submissionjetpackpro1.databinding.FragmentCatalogueBinding
import java.util.*

class CatalogueFragment : Fragment() {
    // tab layout
    private lateinit var pageViewModel: PageViewModel

    // data
    private lateinit var binding: FragmentCatalogueBinding
    private lateinit var showsViewModel : ShowsViewModel
    private lateinit var showsAdapter : RecyclerShowAdapter

    // arguments
    private var tabTitle : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tabTitle = it.getString(ARG_TAB_TITLE).toString()
        }

        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }

        // get category
        // prepareResources(requireContext())
        val categoryId = if (tabTitle.toLowerCase(Locale.ROOT) == "movies"){
            1
        }
        else{
            2
        }
        showsViewModel = ViewModelProvider(this).get(ShowsViewModel::class.java).apply {
            initResources(requireContext(), categoryId, true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogueBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerList()

        showsViewModel.listShowLiveData.observe(viewLifecycleOwner, {
            showsAdapter.setListShows(it)
        })
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_TAB_TITLE = "tab_title"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int, tabTitle : String): CatalogueFragment {
            return CatalogueFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString(ARG_TAB_TITLE, tabTitle)
                }
            }
        }
    }

    private fun initRecyclerList(){
        // adapter
        showsAdapter = RecyclerShowAdapter()

        // set recycler
        binding.recyclerListShow.setHasFixedSize(true)
        binding.recyclerListShow.layoutManager = LinearLayoutManager(context)

        binding.recyclerListShow.adapter = showsAdapter

        // on click
        showsAdapter.setOnItemClickCallback(object : RecyclerShowAdapter.OnItemClickCallback{
            override fun onItemClickCallback(show: Show) {
                //makeToast(show.title)
                val detailIntent = Intent(context, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.ARG_SHOW, show)
                startActivity(detailIntent)
            }
        })
    }

}