# RelayIQ

Speak, polish, and send. Relay turns speech into a clean text message and hands it
off to SMS, WhatsApp, Messenger, Teams, Email, or any other app on your phone -
you always pick the recipient and hit send yourself, nothing goes out automatically.

## Features

- **Speak** - dictate a message using Android's built-in speech recognizer.
- **Fix spelling & punctuation** - sends the dictated text to your selected AI provider to
  clean up grammar and punctuation while preserving your meaning and tone.
- **Clear** - wipes the current message.
- **Send** - opens SMS, WhatsApp, Messenger, Teams, Email, or a general share
  sheet ("Other Apps") with the text pre-filled.
- **Settings** - text size, font, and text colour for the message box.

## Requirements

- Android Studio (current stable channel)
- Android Studio's bundled JDK (Java source compatibility is 11)
- Android SDK Platform 37 (compileSdk/targetSdk)
- A physical device or emulator running Android 8.0 (API 26) or later
- A [Claude API key](https://console.anthropic.com/) if you want to use
  "Fix spelling & punctuation"
- Or an OpenAI or Google Gemini API key

## Getting started

1. Clone the repo:
   ```
   git clone https://github.com/kayldownunder/RelayIQ.git
   ```
2. Open the project folder in Android Studio and let it sync Gradle.
3. Run the `app` configuration on a device or emulator (▶ in Android Studio, or
   `./gradlew installDebug` from a terminal).
4. Tap Speak to open your device's speech recognizer. RelayIQ does not request
   microphone permission itself; your speech service manages audio access.

## Setting up an AI API key

The Polish feature needs an API key from your selected provider. RelayIQ never ships with a key
built in, and the key is **not** included in Android's cloud backup or device
transfer, so every fresh install starts blank and each user must add their own:

1. Get an API key from Anthropic, OpenAI, or Google AI Studio.
2. Open Relay, tap the gear icon (Settings).
3. Under **API Key**, tap the lock, select your provider, and paste its key.
   Provider charges may apply. Basic typing, dictation, and sharing need no key.

If no key is set, tapping "Fix spelling & punctuation" shows a prompt asking
you to add one in Settings instead of failing silently.

## Building a release build

1. Copy `keystore.properties.sample` to `keystore.properties` and fill in the
   upload keystore path, alias, and passwords (the file and the `.jks` are
   git-ignored - never commit them).
2. Bump `versionCode` (and `versionName`) in `app/build.gradle.kts` - Play
   rejects an upload whose `versionCode` it has already seen.
3. Build the signed bundle:
   ```
   ./gradlew bundleRelease
   ```
   Output: `app/build/outputs/bundle/release/app-release.aab`. Upload that to
   the Play Console; the R8 mapping file is in
   `app/build/outputs/mapping/release/mapping.txt` if you want to upload it for
   deobfuscated crash reports.

Play store listing text and assets are in `store-assets/`.

## Project structure

```
app/src/main/java/com/k/hosken/relay/
├── MainActivity.kt          # screen navigation, speech recognition, permissions
├── AppPreferences.kt        # persisted settings (appearance + API key, separate stores)
├── ai/                      # Claude API integration
├── messaging/, messenger/   # SMS/Email/WhatsApp/Teams send intents
└── ui/
    ├── components/          # Header, MicrophoneButton, MessageCard, ActionButtons, etc.
    ├── screens/              # HomeScreen, SettingsScreen
    └── theme/                 # colours, fonts, text-size/colour options
```
