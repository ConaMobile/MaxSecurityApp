package com.conamobile.maxsecurityapp

import android.app.Application
import android.widget.Toast
import com.mukesh.tamperdetector.*
import kotlin.system.exitProcess

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (isFromEmulator()) {
            exitProcess(0)
        }

        if (isFromVpn()) {
            exitProcess(0)
        }
//
        //debug holatda kirgizmaydi
        guardDebugger({
            Toast.makeText(this, "Guard Debugger Success", Toast.LENGTH_SHORT).show()
        }, {
            Toast.makeText(this,
                "detected Debug mode",
                Toast.LENGTH_SHORT).show()
            exitProcess(0)
        })
//
        //Ilova Play Marketdan tortilganligini tekshiradi
        this.verifyInstaller(Installer.GOOGLE_PLAY_STORE)?.let {
            if (it) {
                Toast.makeText(this, "Play Store Done", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "App is not installed from Google Play", Toast.LENGTH_SHORT)
                    .show()
                exitProcess(0)
            }
        }
//
        //Ilova buzilgan yoki buzilmaganligini tekshiradi
        if (this.validateSignature("INSERT YOUR RELEASE SIGNATURE HERE") == Result.VALID) {
            Toast.makeText(this, "Verified", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "modded version", Toast.LENGTH_SHORT).show()
            exitProcess(0)
        }

    }
}