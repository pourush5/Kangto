# Kangto — Chapter A: Arunachal Pradesh

> **Offline-first mountain safety, battery-efficient emergency SOS, and ecological awareness utility.**

[![License](https://img.shields.io/badge/License-Apache_2.0-orange.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Google Play](https://img.shields.io/badge/Google_Play-Download-brightgreen.svg)](https://play.google.com/store/apps/details?id=com.pourush.kangto)
[![Initiative](https://img.shields.io/badge/Initiative-A2Z_Bharat-blue.svg)](https://github.com/pourush5/A2Z_Bharat)

## 🏔️ Overview

Inspired by the 'Land of Dawn-lit Mountains' Arunachal Pradesh and named after its highest peak, Mount Kangto, **Kangto** is an Android application engineered to provide life-saving assistance in high-altitude, remote terrains where conventional mobile internet is unavailable.

It pairs critical offline telephony dispatch with community-driven environmental stewardship.

## Key Features

- **Instant SOS Dispatch:** Automatically fetches precise latitude and longitude coordinates and transmits an emergency dispatch payload via cellular SMS fallback to predefined emergency contacts.
- **One-Tap Emergency Helpline:** Immediate single-tap dialer for direct communication with National Disaster Safety Emergency response authorities.
- **Hollong Score (Eco-Stewardship):** A gamified conservation metric—named after Arunachal Pradesh's state tree that rewards users with an incremented score whenever they:
  - Plant a tree
  - Report a forest danger or hazard
  - Recycle an item
- **Forest Fire Safety Advisories:** Offline, quick-access safety guidelines to identify, prevent, and respond to wildfire hazards, curated from official government disaster resources.

## 🏗️ Technical Stack & Attributions

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose (Material 3)
- **Location Engine:** Powered by Google Play Services **Fused Location Provider API** for low-latency, accurate coordinate acquisition.
- **Telephony:** Android Telephony SMS framework for zero-data emergency transmissions.

## 📲 Installation & Downloads

- **Google Play Store:** [Download on Google Play](https://play.google.com/store/apps/details?id=com.pourush.kangto)

## Building From Source

### Prerequisites

- Android Studio (Ladybug or newer)
- JDK 17+
- Android SDK (API Level 34+)

### Build Steps

```bash
git clone https://github.com/pourush5/Kangto.git
cd Kangto
./gradlew assembleDebug
