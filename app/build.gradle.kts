import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.mardikh.register_and_login__retrofit_hotelapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.mardikh.register_and_login__retrofit_hotelapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        val localProperties = rootProject.file("local.properties")
        val properties = Properties()
        properties.load(localProperties.inputStream())
        val apiUrl = properties["API_URL"] as String
        buildConfigField("String", "BASE_URL", "\"$apiUrl\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // ✅ ADD THIS BLOCK
    buildFeatures {
        buildConfig = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}
