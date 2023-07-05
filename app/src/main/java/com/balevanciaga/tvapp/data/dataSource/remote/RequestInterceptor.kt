package com.balevanciaga.tvapp.data.dataSource.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            appendApiKeyToUrl(chain)
                .newBuilder()
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .build()
        )
    }

    private fun appendApiKeyToUrl(chain: Interceptor.Chain): Request {
        val request = chain.request()
        val modifiedUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", API_KEY).build()
        return request.newBuilder().url(modifiedUrl).build()
    }

    companion object {
        private const val API_KEY = "87c2acb477cafbf173726e5144502fc2"
    }
}