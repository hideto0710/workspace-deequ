FROM mcr.microsoft.com/vscode/devcontainers/java:0-8

# https://github.com/microsoft/vscode-dev-containers/blob/v0.191.0/containers/java-8/.devcontainer/Dockerfile#L9-L10
ARG MAVEN_VERSION="3.8.1"
RUN su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven \"${MAVEN_VERSION}\""

# https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Linux.html
RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee /etc/apt/sources.list.d/sbt.list && \
  echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | tee /etc/apt/sources.list.d/sbt_old.list && \
  curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | apt-key add && \
  apt-get update && \
  apt-get install sbt

# spark
ARG SPARK_VERSION="3.1.2"
RUN curl -sL "https://archive.apache.org/dist/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop3.2.tgz" | \
  tar zx -C /opt/ && \
  mv "/opt/spark-${SPARK_VERSION}-bin-hadoop3.2" /opt/spark
ENV PATH $PATH:/opt/spark/bin
