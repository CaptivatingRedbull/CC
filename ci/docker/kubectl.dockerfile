FROM harbor.k8s.dbs.hof-university.de/docker-io/bitnami/kubectl:latest

# Install curl and other necessary tools
USER root
RUN install_packages curl ca-certificates

# Install Helm
RUN curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 \
    && chmod 700 get_helm.sh \
    && ./get_helm.sh \
    && rm get_helm.sh

# Switch back to non-root user
USER 1001

# Verify kubectl and helm are installed
RUN kubectl version --client
RUN helm version

# Set working directory
WORKDIR /app
