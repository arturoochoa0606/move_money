plugins {
    application
    kotlin("jvm") version "1.9.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.4")
    implementation("io.ktor:ktor-server-netty:2.3.4")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-jackson:2.3.4")

    // Stripe SDK para backend
    implementation("com.stripe:stripe-java:24.6.0")
}

application {
    mainClass.set("com.movemoney.backend.ApplicationKt")
}
