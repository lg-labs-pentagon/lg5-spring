
jvm-test-build:
	./gradlew :lg5-jvm-test:build
jvm-test-test:
	./gradlew :lg5-jvm-test:test
jvm-test-publish:
	./gradlew :lg5-jvm-test:publishToMavenLocal

spring-parent-build:
	./gradlew :lg5-spring-parent:build
spring-parent-publish:
	./gradlew :lg5-spring-parent:publishToMavenLocal --warning-mode all

jvm-publish:
	jvm-test-publish

all-build:
	./gradlew build --warning-mode all

publish-local:
	./gradlew :lg5-spring-logger:publishToMavenLocal -Pversion=1.0.0-alpha.96 --warning-mode all


gradle-update:
	./gradlew wrapper --gradle-version=9.5.1