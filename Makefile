.PHONY: clean install run

clean:
	@mvn clean

install: clean
	@mvn install -DskipTests

run: install
	@mvn spring-boot:run

test:
	@mvn test
