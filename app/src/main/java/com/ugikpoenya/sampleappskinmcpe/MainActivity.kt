package com.ugikpoenya.sampleappskinmcpe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ugikpoenya.sampleappskinmcpe.databinding.ActivityMainBinding
import com.ugikpoenya.skinmcpe.Novaskin
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val groupAdapter = GroupAdapter<GroupieViewHolder>()

    var isLoading = false
    var next_key = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridLayoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.adapter = groupAdapter

        binding.recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                if (!isLoading) {
                    isLoading = true
                    Novaskin().getSearch(applicationContext, "Ben 10", next_key) { next, skins ->
                        isLoading = false
                        next_key = next
                        skins?.forEach {
                            groupAdapter.add(GridViewHolder(it))
                        }
                    }
                }
            }
        })


        isLoading = true
        Novaskin().getSearch(this, "Ben 10", next_key) { next, skins ->
            isLoading = false
            next_key = next
            skins?.forEach {
                groupAdapter.add(GridViewHolder(it))
            }
        }

        groupAdapter.setOnItemClickListener { item, view ->
            when (item) {
                is GridViewHolder -> {
                    val intent = Intent(this, DetailActivity::class.java)
                    val bundle = Bundle()
                    bundle.putSerializable("skinModel", item.skinModel)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }
}