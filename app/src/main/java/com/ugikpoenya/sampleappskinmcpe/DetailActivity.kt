package com.ugikpoenya.sampleappskinmcpe

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ugikpoenya.sampleappskinmcpe.databinding.ActivityDetailBinding
import com.ugikpoenya.skinmcpe.SkinModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val bundle = intent.extras
        val skinModel = bundle?.getSerializable("skinModel") as SkinModel

        binding.webView.setBackgroundColor(Color.TRANSPARENT)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.builtInZoomControls = true
        binding.webView.settings.displayZoomControls = false

        val folderPath = "file:android_asset/"
        val file = folderPath + "3dskinviewer/index.html?url=" + skinModel.url_direct
        binding.webView.loadUrl(file)
    }
}