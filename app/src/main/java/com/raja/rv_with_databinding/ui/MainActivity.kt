package com.raja.rv_with_databinding.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.raja.rv_with_databinding.BR
import com.raja.rv_with_databinding.R
import com.raja.rv_with_databinding.databinding.ActivityMainBinding
import com.raja.rv_with_databinding.ui.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

        binding.rvUserList.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            val decoration =
                DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }

        viewModel.userList.observe(this, Observer {
            binding.progressbar.visibility = View.GONE
            if (it != null) {
                //update the adapter
                viewModel.setAdapterData(it.items)
            } else {
                Toast.makeText(this@MainActivity, "Error in fetching data", Toast.LENGTH_LONG)
                    .show()
            }
        })
        viewModel.makeAPICall("newyork")

    }

    override fun onResume() {
        super.onResume()
        binding.btnShare.setOnClickListener {

            val url = "https://wa.me/8607796935"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

}