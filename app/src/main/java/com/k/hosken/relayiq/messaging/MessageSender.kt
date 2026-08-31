package com.k.hosken.relayiq.messaging

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

object MessageSender {

    fun sendSms(
        context: Context,
        message: String
    ) {

        val intent =
            Intent(Intent.ACTION_SENDTO)

        intent.data = "smsto:".toUri()

        intent.putExtra(
            "sms_body",
            message
        )

        context.startActivity(intent)
    }

    fun sendWhatsApp(
        context: Context,
        message: String
    ) {

        val intent =
            Intent(Intent.ACTION_SEND)

        intent.type = "text/plain"

        intent.putExtra(
            Intent.EXTRA_TEXT,
            message
        )

        intent.setPackage(
            "com.whatsapp"
        )

        try {

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
                    "WhatsApp not installed"
                )
            )
        }
    }

    fun sendEmail(
        context: Context,
        message: String
    ) {

        val intent =
            Intent(Intent.ACTION_SEND)

        intent.type = "message/rfc822"

        intent.putExtra(
            Intent.EXTRA_SUBJECT,
            "Message from RelayIQ"
        )

        intent.putExtra(
            Intent.EXTRA_TEXT,
            message
        )

        context.startActivity(
            Intent.createChooser(
                intent,
                "Send Email"
            )
        )
    }
}
