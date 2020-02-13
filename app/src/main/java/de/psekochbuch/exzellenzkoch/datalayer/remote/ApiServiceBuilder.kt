package de.psekochbuch.exzellenzkoch.datalayer.remote


import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class ApiServiceBuilder(firebaseToken:String?) {
    var retrofit: Retrofit? = null
    private val BASE_URL = "http://193.196.38.185:8080/api/"
    //Thomas: Ich bin mir recht sicher dass die URL http://193.196.38.185:8080/ sein muss(vlt ohne "/" am schluss)
    //private val BASE_URL = "http://192.168.0.110:8080/api/"
    private val token = firebaseToken

    init {

        val customDateAdapter: Any = object : Any() {
            var dateFormat: DateFormat? = null
            @ToJson
            @Synchronized
            fun dateToJson(d: Date?): String? {
                return dateFormat!!.format(d)
            }

            @FromJson
            @Synchronized
            @Throws(ParseException::class)
            fun dateToJson(s: String?): Date? {
                return dateFormat!!.parse(s)
            }

            init {
                dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
            //    dateFormat.timeZone = "GMT"
            }
        }
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).add(customDateAdapter)
            .build()


        if (token != null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createHttpClient())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
              //Brauche ich das doch, laut Jack Wharton ist das inzwischen depcrecated
                // .addCallAdapterFactory(CoroutineCallAdapterFactory())
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