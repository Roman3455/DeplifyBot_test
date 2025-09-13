plugins {
	alias(libs.plugins.java)
    alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependency.management)
}

group = "com.roman3455"
version = "0.0.1-SNAPSHOT"
description = "GitHub & Docker Hub updates in Telegram"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(libs.versions.java.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.cloud.starter.openfeign)
	implementation(libs.spring.cloud.starter.resilience4j)
    implementation(platform(libs.spring.cloud.bom))
    implementation(libs.spring.dotenv)

	testImplementation(libs.spring.boot.starter.test)
	testRuntimeOnly(libs.junit.jupiter)
}

tasks.withType<Test> {
	useJUnitPlatform()
}
