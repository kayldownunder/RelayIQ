package com.k.hosken.relayiq.messenger

import android.content.Context
import android.content.Intent

object MessengerSender {

    fun sendMessenger(
        context: Context,
        message: String
    ) {

        try {

            val intent = Intent(Intent.ACTION_SEND)

            intent.type = "text/plain"

            intent.putExtra(
                Intent.EXTRA_TEXT,
                message
            )

            intent.setPackage(
                "com.facebook.orca"
            )

            context.startActivity(intent)

        } catch (e: Exception) {

            val fallback = Intent(Intent.ACTION_SEND)

            fallback.type = "text/plain"

            fallback.putExtra(
                Intent.EXTRA_TEXT,
                message
            )

            context.startActivity(
                Intent.createChooser(
                    fallback,
                    "Messenger not installed"
                )
            )
        }
    }
}
