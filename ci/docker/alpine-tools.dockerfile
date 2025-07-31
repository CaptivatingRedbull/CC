FROM harbor.k8s.dbs.hof-university.de/docker-io/library/alpine:3.22.0

# Install common tools needed for CI operations
RUN apk add --no-cache \
    bash \
    curl \
    jq \
    git \
    ca-certificates \
    grep \
    sed

# Set up working directory
WORKDIR /app
