plugins {
    id("java")
}

group = "no.miles"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}



dependencies {
    implementation("com.opencsv:opencsv:5.5")
    implementation("javax.jms:javax.jms-api:2.0.1")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}


tasks.compileJava{
    options.compilerArgs = options.compilerArgs + "--enable-preview"
}

tasks.compileTestJava {
    options.compilerArgs = options.compilerArgs + "--enable-preview"
}

tasks.test {
    useJUnitPlatform()
}