package de.psekochbuch.exzellenzkoch.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit

class Repository(token:String)
{
    var retrofit:Retrofit? = null

    init
    {

    }

    private fun createHttpClient() : OkHttpClient?
    {
        return null
    }

    fun createApi(api :Class<out T>)
    {
        
    }


}