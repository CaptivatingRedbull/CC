FROM harbor.k8s.dbs.hof-university.de/docker-io/library/alpine:3.22.0

# Install required tools for building, pushing and signing container images
RUN apk add --no-cache \
    buildctl \
    oras-cli \
    skopeo \
    cosign \
    bash \
    curl \
    jq \
    git 

# Verify installed tools versions
RUN buildctl --version && \
    oras version && \
    skopeo --version && \
    cosign version

# Set working directory
WORKDIR /app
