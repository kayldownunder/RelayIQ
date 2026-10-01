# RelayIQ - Google Play listing draft

## App details
- **App name (max 30):** RelayIQ
- **Category:** Communication (or Productivity)
- **Package:** com.k.hosken.relayiq
- **Contact email:** kayl.hosken@gmail.com
- **Privacy policy URL:** https://kayldownunder.github.io/RelayIQ/ - publish the
  updated `index.html` before submitting for review.
- **Free app**, no ads, no in-app purchases (matches the privacy policy).

## Short description (max 80)
Speak your message, polish it with AI, and send it via SMS, WhatsApp, or Email.

## Full description (max 4000)
RelayIQ turns speech into a clean text message and hands it off to the app you
choose.

**Speak** - tap the mic and dictate using your phone's built-in speech recognition.

**Fix spelling & punctuation** - optionally tidy up grammar and punctuation while
keeping your meaning and tone. Bring your own API key for Claude (Anthropic),
ChatGPT (OpenAI), or Gemini (Google). Your text is sent directly to your chosen
provider. An API key is required for polishing, and provider charges may apply.
Typing, dictation, and sharing work without an AI key.

**Send anywhere** - SMS, WhatsApp, Messenger, Microsoft Teams, Email, or any
other app via the share sheet. You always pick the recipient and press send
yourself; nothing goes out automatically.

**Make it yours** - choose text size, font, and text colour for the message box.

**Private by design** - no accounts, no ads, no tracking. Your API key and
preferences are saved in the app's private storage. API keys are excluded from
Android backup and device transfer; display preferences may be backed up.
Your device's speech service may process audio online under its own policy.

## Graphic assets
| Asset | Status |
|---|---|
| App icon 512x512 | Done - `relayiq-play-store-icon-512.png` |
| Feature graphic 1024x500 | Created - `feature-graphic-1024x500.png` |
| Phone screenshots | Created - `screenshots/01-home.png` and `screenshots/02-settings.png` (both 1080x2000); Send options screenshot still outstanding |

## Release handoff

- The project is configured for versionCode 2 / versionName 1.0.
- The latest local bundle is `app/build/outputs/bundle/release/app-release.aab`
  (built September 20, 2026). The bundles named `RelayIQ.aab` in that directory
  and `app/release/app-release.aab` are older September 4 artifacts.
- The previous session stopped at the Google Play Console developer account
  chooser for "Kayl Down Under". Console setup and uploads are not confirmed.
- Resume by opening the developer account and checking the existing app and
  release status before uploading assets or creating a release.

## Data safety form (suggested answers - review before submitting)
- **Does the app collect or share user data?** Yes. Google's definition of
  collection includes transmission to third parties, even when the developer
  receives nothing. AI polishing sends message text and an authentication key
  to the selected provider. Declare message drafts under the applicable
  *Messages / Other user-generated content* categories as collected, optional,
  for app functionality. Review whether the API credential also requires a
  user identifier declaration. Do not claim ephemeral processing without
  verifying provider retention. Assess sharing using Google's user-initiated
  transfer exception and the provider's role; optional does not mean exempt
  from the collection declaration.
- **Data encrypted in transit:** Yes (HTTPS to all three providers).
- **Deletion:** users can remove local keys or clear app storage. Provider-held
  data follows provider controls; do not claim a developer-operated deletion
  service exists.
- **Audio:** the app declares no RECORD_AUDIO permission. Speech recognition is done
  by the system recognizer via `RecognizerIntent`. RelayIQ receives text, not
  audio; the recognizer may process audio online.
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

## Official references
- Release upload: https://support.google.com/googleplay/android-developer/answer/9859348
- Data safety definitions: https://support.google.com/googleplay/android-developer/answer/10787469
- Store assets: https://support.google.com/googleplay/android-developer/answer/9866151
