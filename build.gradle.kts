import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.3.0-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
	kotlin("plugin.jpa") version "1.9.22"
}

allOpen {
	annotation("jakarta.persistence.Entity")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.postgresql:postgresql:42.7.2")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.4.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude("org.junit.vintage', module: 'junit-vintage-engine")
	}
	/*testImplementation("org.testcontainers:postgresql:1.19.7")*/
	testImplementation("com.h2database:h2:2.2.224")
	testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")

	runtimeOnly("org.flywaydb:flyway-database-postgresql:10.10.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	jvmArgs = listOf("-Xshare:off", "-XX:+EnableDynamicAgentLoading")
}
