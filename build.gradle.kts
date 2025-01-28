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
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
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

springBoot {
	mainClass.set("com.example.sudoku.SudokuApplicationKt") // Deine Hauptklasse
}

// Konfiguration für den BootJar Task
tasks.withType<BootJar> {
	// Sicherstellen, dass die Manifest-Datei korrekt ist
	manifest {
		attributes["Main-Class"] = "org.springframework.boot.loader.JarLauncher" // Diese Klasse wird für Spring Boot benötigt
		attributes["Start-Class"] = "com.example.sudoku.SudokuApplicationKt" // Deine Start-Klasse
	}

	// Entfernen von doppelten Dateien und Sicherstellen, dass alles enthalten ist
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE

	// Diese Zeilen sichern alle Laufzeit-Abhängigkeiten im JAR
	from({
		configurations.runtimeClasspath.get().map { zipTree(it) }
	}) {
		exclude("META-INF/LICENSE")
		exclude("META-INF/NOTICE")
	}
}

// Stellt sicher, dass der bootJar-Task beim Build ausgeführt wird
tasks.build {
	dependsOn(tasks.named("bootJar"))
}

// Optional: Docker Build Image (falls du Docker nutzen möchtest)
tasks.bootBuildImage {
	imageName = "com/example/sudoku-app"  // Korrektes Format für Docker Image Namen
}
