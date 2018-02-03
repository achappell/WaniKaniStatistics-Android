package com.amplifiedprojects.wanikanistatistics

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by amanda.chappell on 2/2/18.
 */

interface WaniKaniService {
    @Headers("Authorization: Bearer 7df922d8-9af6-4763-90c9-aae6db0baee6")
    @GET("level_progressions")
    fun levelProgressions(): Call<LevelProgressions>

    class LevelProgressions {
        var url: String? = null
        var data: List<LevelProgression>? = null
    }

    class LevelProgression {
        var data: LevelProgressionData? = null
    }

    class LevelProgressionData {
        var level: Int? = 0
        var started_at: String? = null
        var passed_at: String? = null
        var abandoned_at: String? = null
    }
}