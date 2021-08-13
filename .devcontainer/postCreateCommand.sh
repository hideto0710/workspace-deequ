set -eux

curl -o ./tmp/deequ-2.0.0-spark-3.1.jar \
    https://repo1.maven.org/maven2/com/amazon/deequ/deequ/2.0.0-spark-3.1/deequ-2.0.0-spark-3.1.jar

curl -o ./tmp/postgresql-42.2.23.jar \
    https://repo1.maven.org/maven2/org/postgresql/postgresql/42.2.23/postgresql-42.2.23.jar
