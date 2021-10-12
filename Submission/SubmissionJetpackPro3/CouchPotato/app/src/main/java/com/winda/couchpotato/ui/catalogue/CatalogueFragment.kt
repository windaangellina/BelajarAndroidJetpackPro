package com.winda.couchpotato.ui.catalogue

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.winda.couchpotato.ui.favorite.FavoriteActivity
import com.winda.couchpotato.R
import com.winda.couchpotato.adapter.RecyclerShowAdapter
import com.winda.couchpotato.data.Show
import com.winda.couchpotato.databinding.FragmentCatalogueBinding
import com.winda.couchpotato.ui.catalogue.viewpager.PageViewModel
import com.winda.couchpotato.ui.detail.DetailActivity
import com.winda.couchpotato.utils.FunctionLibrary
import com.winda.couchpotato.data.viewmodel.ShowsViewModel
import com.winda.couchpotato.data.viewmodel.ViewModelFactory
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class CatalogueFragment : Fragment() {
    // tab layout
    private lateinit var pageViewModel: PageViewModel
    private var categoryId : Int = -1

    // data
    private lateinit var binding: FragmentCatalogueBinding
    private lateinit var showsViewModel : ShowsViewModel
    private lateinit var showsAdapter : RecyclerShowAdapter
    private var listShow = ArrayList<Show>()

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
        categoryId = if (tabTitle.toLowerCase(Locale.ROOT) == "movies"){
            1
        }
        else{
            2
        }

        // ViewModel
        showsViewModel = getViewModel(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // untuk menampilkan option menu dari fragment
        setHasOptionsMenu(true)
        binding = FragmentCatalogueBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerList()

        binding.progressBar.visibility = View.GONE
        setImageStatusVisibility(showImage = true, noResult = false)

        observeLoadingStatus()
        observeResponseCode()
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_TAB_TITLE = "tab_title"

        @JvmStatic
        fun newInstance(sectionNumber: Int, tabTitle: String): CatalogueFragment {
            return CatalogueFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString(ARG_TAB_TITLE, tabTitle)
                }
            }
        }
    }

    private fun getViewModel(fragment: Fragment) : ShowsViewModel {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        return ViewModelProvider(fragment, factory)[ShowsViewModel::class.java]
    }

    private fun initRecyclerList(){
        // set recycler
        binding.recyclerListShow.setHasFixedSize(true)
        binding.recyclerListShow.layoutManager = LinearLayoutManager(context)

        // adapter
        initRecyclerViewAdapter()
    }

    private fun initRecyclerViewAdapter(){
        // adapter
        showsAdapter = RecyclerShowAdapter(listShow)
        binding.recyclerListShow.adapter = showsAdapter
        showsAdapter.notifyDataSetChanged()

        // on click
        showsAdapter.setOnItemClickCallback(object : RecyclerShowAdapter.OnItemClickCallback {
            override fun onItemClickCallback(show: Show) {
                val detailIntent = Intent(context, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.ARG_SHOW, show)
                startActivity(detailIntent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_search, menu)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                if (categoryId == 1) {
                    observeMoviesList(query)
                } else {
                    observeTvShowsList(query)
                }
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    listShow.clear()
                    initRecyclerViewAdapter()
                    setImageStatusVisibility(showImage = true, noResult = false)
                }
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite_menu -> {
                val favoriteIntent = Intent(requireContext(), FavoriteActivity::class.java)
                startActivity(favoriteIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeLoadingStatus(){
        showsViewModel.loadingStatus.observe(viewLifecycleOwner, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE

            if (it) {
                setImageStatusVisibility(showImage = false, noResult = false)
            }
        })
    }

    private fun observeResponseCode(){
        showsViewModel.responseCode.observe(viewLifecycleOwner, {
            val idDrawableError : Int = when (it) {
                401 -> {
                    R.drawable.ic_undraw_warning_cyit
                }
                403 -> {
                    R.drawable.ic_undraw_access_denied_re_awnf
                }
                404 -> {
                    R.drawable.ic_undraw_page_not_found_su7k
                }
                else -> {
                    R.drawable.ic_undraw_cancel_u1it
                }
            }

            if (it != 200){
                binding.imgSearch.visibility = View.VISIBLE
                binding.imgSearch.setImageResource(idDrawableError)
            }
        })
    }

    private fun setImageStatusVisibility(showImage: Boolean, noResult: Boolean){
        if (!showImage){
            binding.imgSearch.visibility = View.GONE
        }
        else{
            if (noResult){
                binding.imgSearch.visibility = View.VISIBLE
                binding.imgSearch.setImageResource(R.drawable.ic_undraw_empty_xct9)
            }
            else{
                binding.imgSearch.visibility = View.VISIBLE
                binding.imgSearch.setImageResource(R.drawable.ic_undraw_searching_p5ux)
            }
        }
    }

    private fun observeMoviesList(searchKeyword : String){
        showsViewModel.getSearchMovies(searchKeyword).observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled().let { response ->
                if (response != null) {
                    listShow.clear()
                    for (resultDetail in response.searchResultMovieResponses) {
                        val userVote = resultDetail.voteAverage * 10
                        var releaseDateEpoch : Long = 0
                        kotlin.runCatching {
                            if (resultDetail.releaseDate != null){
                                resultDetail.releaseDate.let { dateStr ->
                                    releaseDateEpoch = dateStr?.let { FunctionLibrary.getDateAsLong(it) }!!
                                }
                            }
                        }.getOrNull()

                        val show = Show(
                            resultDetail.id,
                            resultDetail.getPosterUrl(),
                            resultDetail.getBackdropUrl(),
                            resultDetail.title,
                            releaseDateEpoch,
                            userVote.roundToInt(),
                            resultDetail.overview
                        )

                        if (show !in listShow) {
                            listShow.add(show)
                        }
                    }

                    if (listShow.size == 0) {
                        binding.imgSearch.visibility = View.VISIBLE
                        binding.imgSearch.setImageResource(R.drawable.ic_undraw_empty_xct9)
                    } else {
                        binding.imgSearch.visibility = View.GONE
                    }

                    if (listShow.size == 0) {
                        setImageStatusVisibility(showImage = true, noResult = true)
                    } else {
                        setImageStatusVisibility(showImage = false, noResult = false)
                    }

                    initRecyclerViewAdapter()
                } else {
                    setImageStatusVisibility(showImage = true, noResult = true)
                }
            }
        })
    }

    private fun observeTvShowsList(searchKeyword: String){
        showsViewModel.getSearchTvShows(searchKeyword).observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled().let { response ->
                if (response != null) {
                    listShow.clear()
                    for (resultDetail in response.searchResultTvShowResponses) {
                        val userVote = resultDetail.voteAverage * 10

                        // runCatching -> prevent crash when there is no first_air_date key in json result
                        var releaseDateEpoch : Long = 0
                        kotlin.runCatching {
                            if (resultDetail.firstAirDate != "") {
                                releaseDateEpoch = FunctionLibrary.getDateAsLong(resultDetail.firstAirDate)
                            }
                        }.getOrNull()

                        val show = Show(
                            resultDetail.id,
                            resultDetail.getPosterUrl(),
                            resultDetail.getBackdropUrl(),
                            resultDetail.name,
                            releaseDateEpoch,
                            userVote.roundToInt(),
                            resultDetail.overview
                        )

                        if (show !in listShow) {
                            listShow.add(show)
                        }
                    }

                    if (listShow.size == 0) {
                        setImageStatusVisibility(showImage = true, noResult = true)
                    } else {
                        setImageStatusVisibility(showImage = false, noResult = false)
                    }

                    initRecyclerViewAdapter()
                } else {
                    setImageStatusVisibility(showImage = true, noResult = true)
                }
            }
        })
    }
}