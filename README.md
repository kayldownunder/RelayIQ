# RelayIQ

Speak, polish, and send. Relay turns speech into a clean text message and hands it
off to SMS, WhatsApp, Messenger, Teams, Email, or any other app on your phone -
you always pick the recipient and hit send yourself, nothing goes out automatically.

## Features

- **Speak** - dictate a message using Android's built-in speech recognizer.
- **Fix spelling & punctuation** - sends the dictated text to the Claude API to
  clean up grammar and punctuation while preserving your meaning and tone.
- **Clear** - wipes the current message.
- **Send** - opens SMS, WhatsApp, Messenger, Teams, Email, or a general share
  sheet ("Other Apps") with the text pre-filled.
- **Settings** - text size, font, and text colour for the message box.

## Requirements

- Android Studio (current stable channel)
- JDK 11
- Android SDK Platform 37 (compileSdk/targetSdk)
- A physical device or emulator running Android 8.0 (API 26) or later
- A [Claude API key](https://console.anthropic.com/) if you want to use
  "Fix spelling & punctuation"
-Or a OpenAI ChatGPT or Google Gemini API Key

## Getting started

1. Clone the repo:
   ```
   git clone https://github.com/kayldownunder/RelayIQ.git
   ```
2. Open the project folder in Android Studio and let it sync Gradle.
3. Run the `app` configuration on a device or emulator (▶ in Android Studio, or
   `./gradlew installDebug` from a terminal).
4. On first launch, grant the microphone permission when prompted - it's
   required for the Speak button.

## Setting up the Claude API key

The Polish feature needs a Claude API key to work. Relay never ships with a key
built in, and the key is **not** included in Android's cloud backup or device
transfer, so every fresh install starts blank and each user must add their own:

1. Get a key from [console.anthropic.com](https://console.anthropic.com/).
2. Open Relay, tap the gear icon (Settings).
3. Under **Claude API Key**, tap the 🔒 to reveal the input field, paste your
   key, and tap the 🙈/👁 icon if you want to check what you typed.

If no key is set, tapping "Fix spelling & punctuation" shows a prompt asking
you to add one in Settings instead of failing silently.

## Building a release build

```
./gradlew assembleRelease
```

Release builds are unsigned by default - add your own signing config in
`app/build.gradle.kts` before distributing a release APK/AAB.

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
