plugins {
    id("java")
}

group = "omg.alcoloid.shitdungeon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    // Paper API
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}

java {
    // 17 - минимальная версия java для сборки
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.processResources {
    filesMatching("**/plugin.yml") {
        // Заменяем ${version} в plugin.yml на версию проекта
        expand("version" to project.version)
    }
}

tasks.withType<JavaCompile> { // Фиксим кракозябры
    options.encoding = "UTF-8"
}