# RelayIQ - Google Play listing draft

## App details
- **App name (max 30):** RelayIQ
- **Category:** Communication (or Productivity)
- **Package:** com.k.hosken.relayiq
- **Contact email:** kayl.hosken@gmail.com
- **Privacy policy URL:** the hosted copy of `index.html` (GitHub Pages) - push the
  updated `index.html` first so the live page matches the app.
- **Free app**, no ads, no in-app purchases (matches the privacy policy).

## Short description (max 80)
Speak your message, polish it with AI, and send it via SMS, WhatsApp, or Email.

## Full description (max 4000)
RelayIQ turns speech into a clean text message and hands it off to the app you
choose.

**Speak** - tap the mic and dictate using your phone's built-in speech recognition.

**Fix spelling & punctuation** - optionally tidy up grammar and punctuation while
keeping your meaning and tone. Bring your own API key for Claude (Anthropic),
ChatGPT (OpenAI), or Gemini (Google) - RelayIQ has no server and never sees your text.

**Send anywhere** - SMS, WhatsApp, Messenger, Microsoft Teams, Email, or any
other app via the share sheet. You always pick the recipient and press send
yourself; nothing goes out automatically.

**Make it yours** - choose text size, font, and text colour for the message box.

**Private by design** - no accounts, no ads, no tracking. Your API key and
preferences stay in the app's private storage on your device and are excluded
from Android backup and device transfer.

## Graphic assets
| Asset | Status |
|---|---|
| App icon 512x512 | Done - `relayiq-play-store-icon-512.png` |
| Feature graphic 1024x500 | **TODO** |
| Phone screenshots (2-8, 16:9 or 9:16, min 320px) | **TODO** - capture Home, Settings, and Send options |

## Data safety form (suggested answers - review before submitting)
- **Does the app collect or share user data?** The developer collects nothing. The
  optional "Fix spelling & punctuation" feature sends the on-screen message text
  from the device straight to the AI provider the user selected, using the
  user's own API key. Declare this as **shared** data: *Messages / Other
  user-generated content*, purpose *App functionality*, and mark it **optional**
  (user-initiated).
- **Data encrypted in transit:** Yes (HTTPS to all three providers).
- **Can users request data deletion:** No data is held by the developer; say so.
- **Audio:** the app declares no RECORD_AUDIO permission. Speech recognition is done
  by the system recognizer via `RecognizerIntent`, so the app does not collect audio.
- **Location, contacts, financial, identifiers, ads:** none.

## Other Play Console forms
- **Content rating (IARC):** utility/communication app, no violence, no user-to-user
  content hosted by the app -> expect Everyone / PEGI 3.
- **Target audience:** 13+ / not designed for children (matches privacy policy).
- **Ads:** No.
- **App access:** all features work without login (AI polish needs the user's own key;
  no reviewer credentials required, but mention it in the review notes).
- **Government app / News / Health / Financial features:** No.
- **First release:** if this is a personal account created after Nov 2023, Google
  requires a closed test with 12+ testers for 14 days before you can apply for
  production access.
