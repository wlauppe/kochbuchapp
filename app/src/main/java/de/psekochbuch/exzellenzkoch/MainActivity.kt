package de.psekochbuch.exzellenzkoch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import de.psekochbuch.exzellenzkoch.datalayer.dto.PublicRecipeDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)


        supportActionBar?.setTitle("Exzellenzkoch")
       // setSupportActionBar(findViewById(R.id.my_toolbar))
        

        //setContentView(R.layout.activity_main)
        supportActionBar?.setTitle("Exzellenzkoch")
        //setSupportActionBar(findViewById(R.id.my_toolbar))


        val frag = supportFragmentManager.findFragmentById(R.id.regisFrag)

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

    
}