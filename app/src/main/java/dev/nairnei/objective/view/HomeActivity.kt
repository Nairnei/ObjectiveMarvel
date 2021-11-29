package dev.nairnei.objective.view

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dev.nairnei.objective.databinding.ActivityMainBinding
import dev.nairnei.objective.viewModel.HomeViewModel
import dev.nairnei.objective.viewModelAdapter.CharactersAdapter
import dev.nairnei.objective.viewModelAdapter.PaginationAdapter


class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var homeAdapter: CharactersAdapter
    private lateinit var paginationAdapter: PaginationAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.setContext(this)
        homeViewModel.listShop(currentPage = null)

        binding.recycleViewStores.apply {
            homeAdapter = CharactersAdapter()

            this.layoutManager = LinearLayoutManager(
                this@HomeActivity, LinearLayoutManager.VERTICAL, false
            )
        }

        binding.recycleViewPagination.apply {
            paginationAdapter = PaginationAdapter(homeViewModel)

            this.layoutManager = LinearLayoutManager(
                this@HomeActivity, LinearLayoutManager.HORIZONTAL, false,
            )

        }

        binding.editTextTextPersonName.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                homeViewModel.search(binding.editTextTextPersonName.text.toString())
                return@OnEditorActionListener true
            }
            false
        })



        homeViewModel.liveDataStoreModel.observe(this, {
            if (it != null) {
                homeAdapter.storeModelList = it
                binding.recycleViewStores.adapter = homeAdapter
                binding.recycleViewStores.setItemViewCacheSize(it.data.limit)

                paginationAdapter.total = it.data.total
                paginationAdapter.offSet = it.data.offset
                paginationAdapter.limit = it.data.limit
                binding.recycleViewPagination.adapter = paginationAdapter
                binding.recycleViewPagination.setItemViewCacheSize(it.data.total)


            }

        })

        homeViewModel.liveDataLoading.observe(this, {
            if (it)
                binding.progressLoading.visibility = View.VISIBLE
            else
                binding.progressLoading.visibility = View.GONE
        })

        homeViewModel.liveDataError.observe(this, {
            Snackbar.make(binding.root, it.toString(), Snackbar.LENGTH_LONG).show()
        })


    }
}