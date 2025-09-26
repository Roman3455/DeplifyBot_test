.DEFAULT_GOAL := build-application

clean:
	./gradlew clean
build:
	./gradlew build
lint:
	./gradlew checkstyleMain
	./gradlew checkstyleTest
test:
	./gradlew test
run:
	./gradlew bootRun

app-build: clean build lint test

metadata-remove:
	rm gradle/verification-metadata.xml
metadata-create:
	./gradlew --write-verification-metadata sha256
metadata-update:
	./gradlew --refresh-dependencies --write-verification-metadata sha256

metadata: metadata-remove metadata-create metadata-update

docker-ngrok:
	docker-compose up -d --no-deps ngrok

.PHONY: clean build lint test run app-build \
	docker-build docker-up docker-up-db docker-down docker-restart \
	app-start-container docker-load-test
