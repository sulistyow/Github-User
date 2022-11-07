package com.sulistyo.github.user.core.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseFollowers(
    val responseFollowers: List<ResponseUserItem>
) : Parcelable