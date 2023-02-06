package hr.algebra.nba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hr.algebra.nba.databinding.ActivityHostBinding
import hr.algebra.nba.framework.startActivity

class HostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(
            android.R.anim.fade_in, android.R.anim.fade_out
        )
        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //var fbEdit : FloatingActionButton = findViewById(R.id.fbEdit)

        initHamburgerMenu()
        initNavigation()


        /*fbEdit.setOnClickListener {
            startActivity<EditActivity>()
        }*/
    }

    private fun initHamburgerMenu() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    private fun initNavigation() {
        val navController = Navigation.findNavController(this, R.id.navController)
        NavigationUI.setupWithNavController(binding.navView,navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.host_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                toggleDrawer()
                return true
            }
            R.id.menuExit -> {
                exitApp()
                return true
            }
            }
        return super.onOptionsItemSelected(item)
    }

    private fun exitApp() {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.exit)
            setIcon(R.drawable.exit)
            val message = setMessage(getString(R.string.really))
            setCancelable(true)
            setNegativeButton(getString(R.string.cancle), null)
            setPositiveButton("Ok") { _, _ -> finish() }
            show()
        }
    }

    private fun toggleDrawer() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawers()
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    companion object {
        const val PERMISSION_LOCATION_REQUEST_CODE = 1
    }
}