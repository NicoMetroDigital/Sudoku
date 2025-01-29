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
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// ✅ H2 Datenbank-Abhängigkeit
	runtimeOnly("com.h2database:h2")

	implementation("org.apache.logging.log4j:log4j-api:2.19.0")
	implementation("org.apache.logging.log4j:log4j-core:2.19.0") {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// ✅ Spring Boot verwaltet automatisch die Main-Class
springBoot {
	mainClass.set("com.example.sudoku.SudokuApplicationKt")
}

tasks.bootJar {
	// 🛑 Entfernt die manuelle Manifest-Konfiguration (Spring Boot übernimmt das automatisch)

	// 💡 Stelle sicher, dass ein Fat JAR gebaut wird
	archiveFileName.set("sudoku-app.jar") // Optional: Name des Outputs

	// 🔄 Vermeidet doppelte Dateien im JAR
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE

	// 📦 Inkludiere alle Abhängigkeiten für das Fat JAR
	from({
		configurations.runtimeClasspath.get().map { zipTree(it) }
	}) {
		exclude("META-INF/LICENSE", "META-INF/NOTICE")
	}
}

// 🚀 Stellt sicher, dass `bootJar` beim Build ausgeführt wird
tasks.build {
	dependsOn(tasks.bootJar)
}

// 🐳 Optional: Docker Build Image
tasks.bootBuildImage {
	imageName.set("com/example/sudoku-app")
}
