package de.psekochbuch.exzellenzkoch.datalayer.remote


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class ApiServiceBuilder(firebaseToken:String?) {
    var retrofit: Retrofit? = null
    private val BASE_URL = "http://193.196.38.185:8080/api/"
    //private val BASE_URL = "http://192.168.0.110:8080/api/"
    private val token = firebaseToken

    init {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        if (token != null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createHttpClient())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        } else {
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        }
    }

    private fun createHttpClient(): OkHttpClient {
        //var token = uss?.getIdToken(false)?.result?.token

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

    fun createApi(api: Class<out Any>): Any {
        return retrofit!!.create(api)
    }

}