plugins {
    java
    application
}


repositories {
    mavenCentral()
}

val vertxVersion = "4.2.1"
val junitJupiterVersion = "5.7.0"



dependencies {
    implementation(platform("io.vertx:vertx-stack-depchain:4.2.1"))
    implementation("io.vertx:vertx-web:4.2.1")
    implementation("io.vertx:vertx-mongo-client:4.2.1")

    implementation("org.json:json:20210307")
    implementation("org.mongodb:mongo-java-driver:3.12.10")
    
    implementation("org.slf4j:slf4j-api:2.0.0-alpha5")
    implementation("com.hivemq:hivemq-mqtt-client:1.2.2")
    testImplementation("io.vertx:vertx-junit5:4.2.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
    implementation("io.vertx:vertx-web-client:_")
    runtimeOnly("io.netty:netty-resolver-dns-native-macos:_")
    implementation("com.opencsv:opencsv:4.1")
}


sourceSets {
    main {
        resources {
            srcDir("src/main/java")
        }
    }
}

application {
    mainClass.set("Main")
}