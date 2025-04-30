JARFILE = calix-addons-1.0-SNAPSHOT.jar

.PHONY: build-server

build:
	mvn package

build-server: build
	cp target/${JARFILE} ../dev-server/plugins/

clean:
	mvn clean dependency:copy-dependencies package
