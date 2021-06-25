package retrofit

import com.brunets.data.remote.api.WizardApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {
    private const val BASE_URL = "https://9ea55ad6-7c3c-4d9b-b8f6-0d311e8a571a.mock.pstmn.io/"
    private fun initRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory
                    .create()
            ).build()
    }
    var api: WizardApi = initRetrofit().create(WizardApi::class.java)

}