
jvm-test-build:
	./gradlew :lg5-jvm-test:build
jvm-test-test:
	./gradlew :lg5-jvm-test:test
jvm-test-publish:
	./gradlew :lg5-jvm-test:publishLibraryPublicationToMavenLocal

jvm-publish:
	jvm-test-publish



all-build:
	./gradlew build --warning-mode all

publish-local:
	./gradlew publishLibraryPublicationToMavenLocal -Pversion=1.0.0-alpha.95 --warning-mode all


gradle-update:
	./gradlew wrapper --gradle-version=8.9-20240417001901+0000