package de.psekochbuch.exzellenzkoch

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {


        private lateinit var appBarConfiguration: AppBarConfiguration
        lateinit var loginMenuItem: MenuItem

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
                    // shown in drawer
                    R.id.publicRecipeSearchFragment, R.id.loginFragment, R.id.adminFragment,
                    R.id.feed, R.id.recipeListFragment,
                    // need direct access to drawer
                    R.id.displaySearchListFragment, R.id.profileDisplayFragment,
                    R.id.recipeDisplayFragment, R.id.registrationFragment, R.id.profileEditFragment,
                    R.id.changePasswordFragment
                ), drawerLayout

            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)

            // TODO Für Login Menü ändern
            val menu:Menu = navView.menu
            loginMenuItem = menu.findItem(R.id.loginFragment)
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




    
