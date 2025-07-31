FROM harbor.k8s.dbs.hof-university.de/docker-io/library/node:24-alpine

WORKDIR /app

RUN apk add --no-cache git bash curl jq

# Install semantic-release and required plugins
RUN npm install -g \
    semantic-release \
    @semantic-release/commit-analyzer \
    @semantic-release/exec \
    @semantic-release/release-notes-generator \
    @semantic-release/changelog \
    @semantic-release/git \
    @semantic-release/gitlab

# Verify installation
RUN semantic-release --version

# Set up git configuration
RUN git config --global user.email "ci@gitlab.com" && \
    git config --global user.name "GitLab CI"

# Set working directory where semantic-release will be executed
WORKDIR /app