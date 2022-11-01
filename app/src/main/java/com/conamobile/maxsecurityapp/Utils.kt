package com.conamobile.maxsecurityapp

import android.os.Build
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*

fun isFromVpn(): Boolean {
    var iface = ""
    try {
        for (networkInterface in Collections.list(NetworkInterface.getNetworkInterfaces())) {
            if (networkInterface.isUp) iface = networkInterface.name
            if (iface.contains("tun") || iface.contains("ppp") || iface.contains("pptp")) {
                return true
            }
        }
    } catch (e1: SocketException) {
        e1.printStackTrace()
    }
    return false
}

fun isFromEmulator(): Boolean {
    return (Build.FINGERPRINT.startsWith("google/sdk_gphone_")
            && Build.FINGERPRINT.endsWith(":user/release-keys")
            && Build.MANUFACTURER == "Google" && Build.PRODUCT.startsWith("sdk_gphone_") && Build.BRAND == "google"
            && Build.MODEL.startsWith("sdk_gphone_"))
            //
            || Build.FINGERPRINT.startsWith("generic")
            || Build.FINGERPRINT.startsWith("unknown")
            || Build.MODEL.contains("google_sdk")
            || Build.MODEL.contains("Emulator")
            || Build.MODEL.contains("Android SDK built for x86")
            //bluestacks
//                || "QC_Reference_Phone" == Build.BOARD && !"Xiaomi".equals(Build.MANUFACTURER, ignoreCase = true) //bluestacks
            || Build.MANUFACTURER.contains("Genymotion")
            || Build.HOST == "Build2" //MSI App Player
            || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
            || Build.PRODUCT == "google_sdk"
            // another Android SDK emulator check
            || SystemProperties.getProp("ro.kernel.qemu") == "1"
}