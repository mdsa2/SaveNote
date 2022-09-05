package com.mohammed.savenote


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ViewModel.NotesView
import com.mohammed.savenote.ui.Adapter.NotesAdapter

class MainActivity : AppCompatActivity() {

    val viewModel : NotesView by viewModels()
    lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController=findNavController(R.id.fragmentContainerView)
        setupActionBarWithNavController(navController)

    }




    override fun onNavigateUp(): Boolean {
        return  navController.navigateUp() || super.onNavigateUp()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}