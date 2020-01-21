package de.psekochbuch.exzellenzkoch

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.RegistrateFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        supportActionBar?.setTitle("Exzellenzkoch")
        //setSupportActionBar(findViewById(R.id.my_toolbar))
        setContentView(R.layout.activity_main)

        val frag = supportFragmentManager.findFragmentById(R.id.regisFrag)


        val auth = Authentification()
        auth.registrate("test@test.de", "12345678"){ it, status ->
            if(it != null) Log.d("ContentValues", "Ypeee")
        }
    }

    
}