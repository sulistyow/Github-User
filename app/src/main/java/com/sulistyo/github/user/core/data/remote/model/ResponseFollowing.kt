package com.sulistyo.github.user.core.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseFollowing(

    @field:SerializedName("ResponseFollowing")
    val responseFollowing: List<ResponseUserItem>
) : Parcelable
