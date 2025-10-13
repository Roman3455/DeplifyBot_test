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
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.cloud.starter.openfeign)
	implementation(libs.spring.cloud.starter.resilience4j)
    implementation(platform(libs.spring.cloud.bom))
    implementation(libs.springdoc.openapi.starter.webmvc)
    implementation(libs.spring.dotenv)
    implementation(libs.squareup.okhttp)

	testImplementation(libs.spring.boot.starter.test)
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
