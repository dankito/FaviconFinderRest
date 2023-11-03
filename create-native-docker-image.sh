
./gradlew build -Dquarkus.package.type=native -x test &&

docker build -f src/main/docker/Dockerfile.native-micro -t docker.dankito.net/dankito/favicon-finder . &&

docker push docker.dankito.net/dankito/favicon-finder