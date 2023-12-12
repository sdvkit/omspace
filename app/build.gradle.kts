plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.sdv.kit.omspace"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sdv.kit.omspace"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    
    // Datastore Preferences
    val datastorePreferencesVersion = "1.0.0"
    implementation("androidx.datastore:datastore-preferences:$datastorePreferencesVersion")

    // AppAuth
    val appAuthVersion = "0.11.1"
    implementation("net.openid:appauth:$appAuthVersion")

    // Retrofit2 + Gson
    val retrofit2GsonVersion = "2.9.0"
    implementation("com.squareup.retrofit2:converter-gson:$retrofit2GsonVersion")

    // Room
    val roomVersion = "2.6.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    // Coil-compose version
    val coilComposeVersion = "2.5.0"
    implementation("io.coil-kt:coil-compose:$coilComposeVersion")

    // Splash screen
    val splashScreenVersion = "1.1.0-alpha02"
    implementation("androidx.core:core-splashscreen:$splashScreenVersion")

    // Firebase realtime-database
    val firebaseRealtimeDatabaseVersion = "20.3.0"
    implementation("com.google.firebase:firebase-database:$firebaseRealtimeDatabaseVersion")

    // Google auth
    val googleAuthVersion = "20.7.0"
    implementation("com.google.android.gms:play-services-auth:$googleAuthVersion")

    // Firebase auth
    val firebaseAuthVersion = "22.3.0"
    implementation("com.google.firebase:firebase-auth:$firebaseAuthVersion")

    // Hilt navigation compose
    val hiltNavComposeVersion = "1.1.0"
    implementation("androidx.hilt:hilt-navigation-compose:$hiltNavComposeVersion")

    // Navigation
    val navVersion = "2.7.5"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // Hilt
    val hiltVersion = "2.48.1"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    ksp("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Compose + Material3
    val material3Version = "1.2.0-alpha11"
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:$material3Version")
    implementation("androidx.compose.material3:material3-window-size-class:$material3Version")

    // Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Tools
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}