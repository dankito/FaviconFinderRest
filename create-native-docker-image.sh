
./gradlew build -Dquarkus.package.type=native -x test &&

docker build -f src/main/docker/Dockerfile.native-micro -t docker.dankito.net/dankito/favicon-finder:1.0.0-beta1 . &&

docker push docker.dankito.net/dankito/favicon-finder:1.0.0-beta1