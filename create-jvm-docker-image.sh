
./gradlew build -x test &&

docker build -f src/main/docker/Dockerfile.jvm -t docker.dankito.net/dankito/favicon-finder:1.0.0-beta1 . &&

docker push docker.dankito.net/dankito/favicon-finder:1.0.0-beta1