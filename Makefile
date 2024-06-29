
jvm-test-build:
	./gradlew :lg5-jvm-test:build
jvm-test-test:
	./gradlew :lg5-jvm-test:test
jvm-test-publish:
	./gradlew :lg5-jvm-test:publishLibraryPublicationToMavenLocal

spring-parent-build:
	./gradlew :lg5-spring-parent:build
spring-parent-publish:
	./gradlew :lg5-spring-parent:publishJavaPublicationToMavenLocal --warning-mode all

jvm-publish:
	jvm-test-publish

all-build:
	./gradlew build --warning-mode all

publish-local:
	./gradlew publishLibraryPublicationToMavenLocal -Pversion=1.0.0-alpha.95 --warning-mode all


gradle-update:
	./gradlew wrapper --gradle-version=8.9-rc-1