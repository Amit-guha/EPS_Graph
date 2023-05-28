package com.example.earninggraph

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.earninggraph.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var listOfEpsGraphItem : ArrayList<EpsModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listOfEpsGraphItem = EpsModel().getListOfEpsGraphItem()
        binding.customChartView.drawChart(listOfEpsGraphItem)

    }
}