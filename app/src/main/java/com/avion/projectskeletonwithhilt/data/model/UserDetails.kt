package com.avion.projectskeletonwithhilt.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetails(
    val id: Int? = null,
    val first_name: String? = null,
    val last_name: String? = null,
    val email: String? = null,
    val mobile: String? = null,
    val prfile_image: String? = null,
    val profile_image: String? = null,
    val isCurrentlyLoggedIn: Boolean? = null,
) : Parcelable