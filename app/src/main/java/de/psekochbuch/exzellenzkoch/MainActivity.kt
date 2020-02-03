package de.psekochbuch.exzellenzkoch

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

import de.psekochbuch.exzellenzkoch.datalayer.dto.PublicRecipeDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {


        private lateinit var appBarConfiguration: AppBarConfiguration

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            //Set up toolbar and components for the app menu
            val toolbar: Toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)
            val drawerLayout: DrawerLayout = findViewById(R.id.activity_main)
            val navView: NavigationView = findViewById(R.id.nav_view)
            val navController = findNavController(R.id.nav_host_fragment)
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.publicRecipeSearchFragment, R.id.loginFragment, R.id.adminFragment
                ), drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.main, menu)
            return true
        }

        override fun onSupportNavigateUp(): Boolean {
            val navController = findNavController(R.id.nav_host_fragment)
            return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }

        //setSupportActionBar(findViewById(R.id.my_toolbar))


        //val frag = supportFragmentManager.findFragmentById(R.id.regisFrag)

        /*val call : PublicRecipeApi = ApiServiceBuilder(
            null
        ).createApi(PublicRecipeApi::class.java) as PublicRecipeApi
        call.getRecipe(1).enqueue(object : Callback<PublicRecipeDto?> {
            override fun onResponse(
                call: Call<PublicRecipeDto?>?,
                response: Response<PublicRecipeDto?>?
            ) {
                if(response!!.isSuccessful)
                {
                    val publicRecipe = response.body()
                }
            }

            override fun onFailure(
                call: Call<PublicRecipeDto?>,
                t: Throwable
            ) {
                val t = ""
            }
        })*/


       /* repo.getRecipe(1).enqueue(Callback<PublicRecipeDto>()
        {

        })*/

        //ResponseCallback({
        //            var publicrecipe : PublicRecipeDto = it as PublicRecipeDto
        //        }) as Callback<PublicRecipeDto?>)


        /*val auth = Authentification()
        auth.registrate("test@test.de", "12345678"){ it, status ->
            if(it != null) Log.d("ContentValues", "Ypeee")
        }*/
    }




    
