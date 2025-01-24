import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	compileOnly("org.projectlombok:lombok")
	"developmentOnly"("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	implementation ( "org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly ("com.h2database:h2")


	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	implementation("org.apache.logging.log4j:log4j-api:2.19.0")
	implementation("org.apache.logging.log4j:log4j-core:2.19.0") {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// Spring Boot Plugin Configuration
springBoot {
	mainClass.set("com.example.sudoku.SudokuApplicationKt")
}

tasks.withType<BootJar> {
	// Configure the bootJar task to correctly set the Manifest attributes
	manifest {
		attributes["Main-Class"] = "org.springframework.boot.loader.JarLauncher" // Correct start class for Spring Boot JAR
		attributes["Start-Class"] = "com.example.sudoku.SudokuApplicationKt" // Your Kotlin main class
	}

	// Fix for duplicates
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE

	// Include runtime dependencies in the JAR
	from({
		configurations.runtimeClasspath.get().map { zipTree(it) }
	}) {
		exclude("META-INF/LICENSE.txt")
		exclude("META-INF/NOTICE.txt")
	}
}

// Ensure the `bootJar` task runs as part of the build
tasks.build {
	dependsOn(tasks.named("bootJar"))
}
