FROM maven
COPY /src ./src
COPY pom.xml /
cmd mvn test