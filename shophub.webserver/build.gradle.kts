plugins {
	java
	id("org.springframework.boot") version "3.5.4"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.sonarqube") version "6.2.0.5505"
}

group = "com.evaitcsmatt"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt-api:0.13.0")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.13.0")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.13.0")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")
	runtimeOnly("com.h2database:h2:2.3.232")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.testcontainers:junit-jupiter:1.21.3")
	testImplementation("org.testcontainers:mysql:1.21.3")
	testImplementation("org.mockito:mockito-core:5.19.0")
	testImplementation("org.mockito:mockito-junit-jupiter:5.19.0")
	testImplementation("io.rest-assured:rest-assured:5.5.6")
	testImplementation("io.rest-assured:spring-mock-mvc:5.5.6")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jar {
    enabled = false
    archiveClassifier.set("")
}

sonar {
	properties {
		property("sonar.projectKey", "Test")
		property("sonar.projectName", "Test")
		property("sonar.host.url", "http://localhost:9000")
		property("sonar.token", "sqp_550fb057327e815c2b83bda2893312cd73228684")
	}
}