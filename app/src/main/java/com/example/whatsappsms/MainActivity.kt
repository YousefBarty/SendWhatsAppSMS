package com.example.whatsappsms

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phone = findViewById<EditText>(R.id.phonetxt)
        val message = findViewById<EditText>(R.id.messagetxt)
        val sendMessage = findViewById<Button>(R.id.openwhatsbtn)

        sendMessage.setOnClickListener {

            val installed: Boolean = isAppInstalled("com.whatsapp")
            val checkInputs=checkNumandMessage(phone.text.toString(),message.text.toString())

            if (installed && !checkInputs) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data =
                    Uri.parse("http://api.whatsapp.com/send?phone=" + phone.text.toString() + "&text=" + message.text)
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "Whatsapp May Be not installed! Or Error in Inputs", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    private fun checkNumandMessage(phone: String, message: String):Boolean {

        return (phone.isEmpty() && message.isEmpty())
    }

    private fun isAppInstalled(s: String): Boolean {

        val packageManager = packageManager
        var is_installed: Boolean

        try {
            packageManager.getPackageInfo(s, PackageManager.GET_ACTIVITIES)
            is_installed = true
        } catch (e: PackageManager.NameNotFoundException) {
            is_installed = false
            e.printStackTrace()
        }
        return is_installed
    }
}