package de.psekochbuch.exzellenzkoch.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class Repository(firebaseToken:String?)
{
    var retrofit:Retrofit? = null
    private val BASE_URL = "http://193.196.38.185:8080/api/"
    private val token = firebaseToken

    init
    {
        val gson : Gson = GsonBuilder().setLenient().create()

        retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(createHttpClient()).addConverterFactory(
            GsonConverterFactory.create(gson)).build()
    }

    fun createHttpClient() : OkHttpClient
    {
        //var token = uss?.getIdToken(false)?.result?.token
        if(token != null) {
            return OkHttpClient().newBuilder().addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response? {
                    val originalRequest: Request = chain.request()
                    val builder: Request.Builder = originalRequest.newBuilder().header(
                        "Authorization", token
                    )
                    val newRequest: Request = builder.build()
                    return chain.proceed(newRequest)
                }
            }).build()
        }
        return OkHttpClient().newBuilder().addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response? {
                val originalRequest: Request = chain.request()
                val builder: Request.Builder = originalRequest.newBuilder().header(
                    "Authorization", token
                )
                val newRequest: Request = builder.build()
                return chain.proceed(newRequest)
            }
        }).build()
    }

    fun createApi(api :Class<out Any>) : Any
    {
        return retrofit!!.create(api)
    }


}