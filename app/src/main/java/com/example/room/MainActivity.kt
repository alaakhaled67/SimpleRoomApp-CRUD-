package com.example.room

import android.content.Context
import android.content.res.Resources.Theme
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.core.content.withStyledAttributes
import androidx.core.graphics.toColor
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val navController = findNavController(R.id.fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
       binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        window.statusBarColor = Color.parseColor("#FF018786")
        setSupportActionBar(binding.toolbar)
    }
}