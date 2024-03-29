plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.felpster.coreui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(platform("androidx.compose:compose-bom:2023.08.00"))
    api("androidx.compose.ui:ui")
    api("androidx.compose.ui:ui-graphics")
    api("androidx.compose.ui:ui-tooling-preview")
    api("androidx.compose.material3:material3")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")

    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.9")
    testImplementation("com.google.truth:truth:1.4.1")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}