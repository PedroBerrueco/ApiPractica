package com.pberrueco.apipractica.ui.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pberrueco.apipractica.PracticeAdapter
import com.pberrueco.apipractica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding: ActivityMainBinding get() = _binding

    private val viewModel: MainViewModel by viewModels()
    private val adapter = PracticeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNuevabase.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNuevabase.adapter = adapter

        viewModel.getUserName(this)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.userName.observe(this) { userName ->
            if (userName != null) {
                viewModel.getHomeWork(userName)
            }
        }
        viewModel.homeWorkResponse.observe(this, Observer { response ->
            if (response != null) {
                adapter.submitList(response)
            } else {
                Toast.makeText(this, "Algo fue mal", Toast.LENGTH_SHORT).show()
            }
        })

    }
}