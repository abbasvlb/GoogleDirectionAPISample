package com.example.gdirectionspoc

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gdirectionspoc.databinding.ActivityMainBinding
import com.example.gdirectionspoc.viewModel.GDirectionViewModel
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val origin: String = "Chennai,IN"
    private val destination: String = "Chennai,IN"
    private val wayPoints: ArrayList<String> = arrayListOf("Coimbatore,IN", "Banglore,IN", "Madurai,IN", "Trichy,IN")

    private val gDirectionViewModel: GDirectionViewModel by lazy {
        ViewModelProvider(this).get(GDirectionViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_map, R.id.nav_locations
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val wayPointStringBuilder: StringBuilder = StringBuilder("optimize:true|")
        wayPoints.forEachIndexed { index, s ->
            if (index < wayPoints.size-1){
                wayPointStringBuilder.append(s).append("|")
            } else {
                wayPointStringBuilder.append(s)
            }
        }

        gDirectionViewModel.getGDirection(origin, destination, wayPointStringBuilder.toString())

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}