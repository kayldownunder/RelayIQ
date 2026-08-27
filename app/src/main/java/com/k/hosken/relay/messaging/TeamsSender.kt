package com.k.hosken.relay.messaging

import android.content.Context
import android.content.Intent

object TeamsSender {

    fun sendTeams(
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
                "com.microsoft.teams"
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
                    "Microsoft Teams not installed"
                )
            )
        }
    }
}
