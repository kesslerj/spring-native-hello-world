# Build & Run

## Native image via Buildpacks
- result: docker image
- compilation only, no native testing
    - but tests must work!? executed non-native!?
- no GraalVM installation needed

- not working on ARM (e. g. M1 mac)?

```shell
## Build
$ mvn -Pnative spring-boot:build-image -Dspring-boot.build-image.imageName=jk/playground/spring-native-hello-world

## Run
$ docker run --rm -p 8080:8080 jk/playground/spring-native-hello-world:latest
```


### JVM image (for performance comparison)
- result: docker image
- non native

```shell
## Build
$ mvn spring-boot:build-image -Dspring-boot.build-image.imageName=jk/playground/spring-native-hello-world-classic

## Run
$ docker run --rm -p 8080:8080 jk/playground/spring-native-hello-world-classic:latest
```

## Native Build Tools
- needs local GraalVM installation

```shell
# Switch to GraalVM via Sdkman
$ sdk use java 22.3.r17-nik

# Build 
$ mvn -Pnative native:compile

# Run
$ target/spring-native-hello-world
```

# Testing with Native Build Tools
- needs local GraalVM installation
- 
```shell
# Switch to GraalVM via Sdkman
$ sdk use java 22.3.r17-nik

# Test
$ mvn -PnativeTest test
```
