/**
 * <!-- TODO When update to Spring Boot 3.X.X enabled this dependency for dockercompose test -->
 *         <!--<dependency>
 *           <groupId>org.springframework.boot</groupId>
 *           <artifactId>spring-boot-docker-compose</artifactId>
 *           <version>3.2.3</version>
 *           <scope>runtime</scope>
 *           <optional>true</optional>
 *         </dependency>
 *     -->
 */

dependencies {
    api(libs.springboot.starter.test){
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "com.vaadin.external.google", module = "android-json")
    }
    api(libs.awaitility)
    api(libs.rest.assured)

}
tasks.jar { enabled = true }