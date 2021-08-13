# workspace deequ

see [microsoft/vscode-remote-release#5388](https://github.com/microsoft/vscode-remote-release/issues/5388).

[![Open in Visual Studio Code](https://open.vscode.dev/badges/open-in-vscode.svg)](https://open.vscode.dev/hideto0710/workspace-deequ)

## Testing
```bash
sbt package
spark-submit \
  --class "example.Hello" \
  --master spark://master:7077 \
  --driver-class-path ./tmp/postgresql-42.2.23.jar \
  --jars ./tmp/deequ-2.0.0-spark-3.1.jar,./tmp/postgresql-42.2.23.jar \
  target/scala-2.12/workspace-deequ_2.12-0.1.0-SNAPSHOT.jar
```

## Debug
```bash
spark-shell --driver-class-path=./tmp/postgresql-42.2.23.jar
val jdbcDF = spark.read.
  format("jdbc").
  option("url", s"jdbc:postgresql://${System.getenv("POSTGRES_HOST")}/${System.getenv("POSTGRES_DB")}").
  option("dbtable", "public.demodata").
  option("user", System.getenv("POSTGRES_USER")).
  load()
jdbcDF.printSchema()
```
