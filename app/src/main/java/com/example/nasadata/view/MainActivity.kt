package com.example.nasadata.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.nasadata.R
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var controller: NavController

    private val progressDialog by lazy {
        AlertDialog.Builder(this).setView(R.layout.view_progress_dialog).create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setView()
    }

    override fun onStop() {
        dismissProgressBar()
        super.onStop()
    }

    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp()
    }

    private fun setView() {
        supportActionBar?.elevation = 0f
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        controller = Objects.requireNonNull(navHostFragment).navController
        setupActionBarWithNavController(this, controller)
    }

    fun showProgressBar(isCancelable: Boolean) {
        progressDialog.setCancelable(isCancelable)
        progressDialog.show()
    }

    fun dismissProgressBar() {
        if(progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}