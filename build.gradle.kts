plugins {
	alias(libs.plugins.java)
    alias(libs.plugins.jacoco)
    alias(libs.plugins.sonarqube)
    alias(libs.plugins.checkstyle)
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
// === Spring Boot ===
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.validation)
// === Spring Cloud ===
    implementation(libs.spring.cloud.starter.openfeign)
	implementation(libs.spring.cloud.starter.resilience4j)
    implementation(platform(libs.spring.cloud.bom))
// === OpenAPI / Swagger ===
    implementation(libs.springdoc.openapi.starter.webmvc)
// === Utility ===
    implementation(libs.spring.dotenv)
    implementation(libs.squareup.okhttp)
// === Database ===
    implementation(libs.flyway.db.core)
    implementation(libs.flyway.db.postgresql)
    runtimeOnly(libs.postgresql)
// === Testing ===
	testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.testcontainers.junit.jupiter)
    testImplementation(libs.testcontainers.postgresql)
	testRuntimeOnly(libs.junit.jupiter)
}

sonar {
    properties {
        property("sonar.projectKey", "Roman3455_DeplifyBot")
        property("sonar.organization", "roman3455")
    }
}

tasks.withType<Test> {
	useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
        html.required = true
    }
}

tasks.bootJar {
    archiveFileName.set("app.jar")
}
