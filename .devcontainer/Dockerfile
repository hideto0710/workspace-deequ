FROM mcr.microsoft.com/vscode/devcontainers/java:0-8

# https://github.com/microsoft/vscode-dev-containers/blob/v0.191.0/containers/java-8/.devcontainer/Dockerfile#L9-L10
ARG MAVEN_VERSION="3.8.1"
ARG SBT_VERSION="1.5.5"
ARG SPARK_VERSION="3.1.2"
RUN su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven \"${MAVEN_VERSION}\"" && \
    su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install sbt \"${SBT_VERSION}\"" && \
    su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install spark \"${SPARK_VERSION}\""
