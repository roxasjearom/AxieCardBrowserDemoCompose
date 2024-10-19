plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.android.ksp)
}

android {
    namespace = "com.roxasjearom.axiecardbrowserdemo.compose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.roxasjearom.axiecardbrowserdemo.compose"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    /*implementation 'androidx.core:core-ktx:1.13.1'
    implementation 'androidx.appcompat:appcompat:1.7.0'
    implementation 'com.google.android.material:material:1.12.0'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.2.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.6.1'

    // Retrofit with Moshi Converter
    implementation 'com.squareup.retrofit2:converter-moshi:2.9.0'

    // Moshi
    implementation 'com.squareup.moshi:moshi-kotlin:1.13.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3'

    //Room
    implementation "androidx.room:room-runtime:2.6.1"
    kapt "androidx.room:room-compiler:2.6.1"
    implementation "androidx.room:room-ktx:2.6.1"

    // Coroutine Lifecycle Scopes
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.8.6"

    //Hilt
    implementation "com.google.dagger:hilt-android:2.48"
    kapt "com.google.dagger:hilt-android-compiler:2.48"

    // Coil
    implementation "io.coil-kt:coil:2.2.2"
    implementation"io.coil-kt:coil-compose:2.2.2"

    //Tests
    testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"
    testImplementation "com.google.truth:truth:1.1.3"

    // Compose
    implementation 'androidx.activity:activity-compose:1.9.3'
    implementation 'androidx.compose.material:material:1.7.4'
    implementation 'androidx.compose.animation:animation:1.7.4'
    implementation 'androidx.compose.ui:ui-tooling:1.7.4'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6'
    implementation "androidx.compose.material3:material3:1.3.0"
    implementation "androidx.constraintlayout:constraintlayout-compose:1.1.0-rc01"*/

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.coroutines.test)
    testImplementation(libs.google.truth)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi.converter)
    implementation(libs.moshi)
    implementation(libs.okhttp.interceptor)

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.testing)

    //Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)

}
