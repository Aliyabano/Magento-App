package com.aliyabano.vendorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.aliyabano.vendorapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.vendor_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        var itemView = item.itemId
//        when (itemView) {
//            R.id.notification -> Toast.makeText(this, "Clicked notification", Toast.LENGTH_SHORT)
//                .show()
//            R.id.contact -> Toast.makeText(this, "Clicked contact", Toast.LENGTH_SHORT).show()
//            R.id.dot_menu -> Toast.makeText(this, "Clicked more option menu", Toast.LENGTH_SHORT)
//                .show()
//        }
//        return false
//    }
}