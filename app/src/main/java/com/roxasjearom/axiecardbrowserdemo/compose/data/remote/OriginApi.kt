package com.roxasjearom.axiecardbrowserdemo.compose.data.remote

import com.roxasjearom.axiecardbrowserdemo.compose.data.remote.response.OriginData
import retrofit2.http.GET

interface OriginApi {
    @GET("/api/origin-test-data.json")
    suspend fun getOriginData(): OriginData
}
