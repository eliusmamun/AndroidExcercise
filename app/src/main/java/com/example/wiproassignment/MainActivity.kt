package com.example.wiproassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiproassignment.view.ListDataAdapter
import com.example.wiproassignment.viewmodel.ListDataViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListDataViewModel

    private val factsAdapter = ListDataAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ListDataViewModel::class.java)
        viewModel.refresh()

        factsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = factsAdapter
        }

        observeViewModel()

        // This is  to implement pull to refresh
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }
    }



    fun observeViewModel() {
        viewModel.facts.observe(this, Observer { facts ->
            facts?.let {
                factsList.visibility = View.VISIBLE
               factsAdapter.updateFacts(it.rows)
            }
        })

        viewModel.factsLoadError.observe(this, Observer { isError ->
           // list_error.visibility = if (isError == null) View.GONE else View.VISIBLE
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
