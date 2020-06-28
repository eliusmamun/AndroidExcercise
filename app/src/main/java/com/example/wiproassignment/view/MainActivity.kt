package com.example.wiproassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiproassignment.R
import com.example.wiproassignment.viewmodel.ListDataViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListDataViewModel

    private val factsAdapter = ListDataAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ListDataViewModel::class.java)

        factsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = factsAdapter
        }

        observeViewModel()

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }
    }

    private fun observeViewModel() {
        viewModel.getFactsObservable().observe(this, Observer { facts ->
            facts?.let {
                factsList.visibility = View.VISIBLE
                factsAdapter.updateFacts(it.rows)
                supportActionBar?.apply {
                    title = it.title
                }
            }
        })

        viewModel.factsLoadError.observe(this, Observer { isError ->
            list_error.visibility = if (isError == null) View.GONE else View.VISIBLE
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    factsList.visibility = View.GONE
                }
            }
        })
    }


}
