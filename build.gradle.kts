plugins {
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.jetbrains.kotlin.jvm") version "1.8.0"
	id("org.jetbrains.kotlin.plugin.spring") version "1.8.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17  // Sicherstellen, dass Java 17 verwendet wird

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot Dependencies
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Kotlin Dependencies
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Database Dependencies
	implementation("com.h2database:h2")

	// Test Dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
}

kotlin {
	jvmToolchain(17) // Kotlin JVM auf 17 setzen
}

