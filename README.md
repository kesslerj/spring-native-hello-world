# Build & Run

## Native image

```shell
## Build
$ mvn -Pnative spring-boot:build-image '-Dspring-boot.build-image.imageName=jk/playground/spring-native-hello-world'

## Run
$ docker run --rm -p 8080:8080 jk/playground/spring-native-hello-world:latest
```


## JVM image

```shell
## Build
$ mvn spring-boot:build-image '-Dspring-boot.build-image.imageName=jk/playground/spring-native-hello-world-classic'

## Run
$ docker run --rm -p 8080:8080 jk/playground/spring-native-hello-world-classic:latest
```

