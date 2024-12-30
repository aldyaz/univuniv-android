package com.aldyaz.univuniv.utils

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

fun Context.launchCustomTabs(url: String) {
    val customTabsIntent = CustomTabsIntent.Builder()
        .build()
    customTabsIntent.launchUrl(this, Uri.parse(url))
}
