package com.k.hosken.relay.messaging

import android.content.Context
import android.content.Intent

object OtherAppsSender {

    fun send(
        context: Context,
        message: String
    ) {

        val intent = Intent(Intent.ACTION_SEND)

        intent.type = "text/plain"

        intent.putExtra(
            Intent.EXTRA_TEXT,
            message
        )

        context.startActivity(
            Intent.createChooser(
                intent,
                "Send message using"
            )
        )
    }
}
