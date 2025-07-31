# CI/CD Variables for Monitoring Helm Chart

This document lists all CI/CD variables that need to be configured for the monitoring Helm chart.

## Required Variables

### Image Registry Configuration
These variables should already be configured for the main Rateflix application:

| Variable | Description | Example Value | Status |
|----------|-------------|---------------|---------|
| `CI_REGISTRY_URL` | Base registry URL | `harbor.dbs.hof-university.de` | ✅ Already used by Rateflix |
| `CI_REGISTRY_CACHE` | Docker Hub cache path | `docker-hub-cache` | ✅ Already used by Rateflix |
| `CI_REGISTRY_PROJECT` | Project namespace in registry | `projekt-mschoeffend` | ✅ Already used by Rateflix |

### Ingress Configuration
These variables should already be configured for the main Rateflix application:

| Variable | Description | Example Value | Status |
|----------|-------------|---------------|---------|
| `INGRESS_BASE_HOST` | Base domain for ingresses | `k8s.dbs.hof-university.de` | ✅ Already used by Rateflix |

### New Variables for Monitoring

| Variable | Description | Example Value | Security Level |
|----------|-------------|---------------|----------------|
| `MONITORING_INGRESS_SUBDOMAIN` | Subdomain for Grafana access | `grafana.monitoring` | **🔧 REQUIRED** |
| `GRAFANA_ADMIN_USER` | Grafana admin username | `admin` | **🔒 SECURITY SENSITIVE** |
| `GRAFANA_ADMIN_PASSWORD` | Grafana admin password | `secure-password-123` | **🔒 SECURITY SENSITIVE** |

## Variable Usage in Helm Values

The monitoring chart uses these variables in the following way:

```yaml
# Image registry pattern (same as Rateflix)
image:
  registry: "${CI_REGISTRY_URL}"              # harbor.dbs.hof-university.de
  cache: "${CI_REGISTRY_CACHE}"               # docker-hub-cache
  project: "${CI_REGISTRY_PROJECT}"           # projekt-mschoeffend

# Grafana ingress
grafana:
  ingress:
    host: "${MONITORING_INGRESS_SUBDOMAIN}.${INGRESS_BASE_HOST}"  # grafana.monitoring.k8s.dbs.hof-university.de
  
  # Grafana admin credentials
  admin:
    user: "${GRAFANA_ADMIN_USER}"             # admin
    password: "${GRAFANA_ADMIN_PASSWORD}"     # secure-password-123
```

## Image Pull Benefits

By using the cache pattern, all Docker images will be pulled through the Harbor cache:

- ✅ **Prometheus**: `harbor.dbs.hof-university.de/docker-hub-cache/prom/prometheus:v2.47.2`
- ✅ **Grafana**: `harbor.dbs.hof-university.de/docker-hub-cache/grafana/grafana:10.2.0`
- ✅ **Kube-state-metrics**: `harbor.dbs.hof-university.de/docker-hub-cache/registry.k8s.io/kube-state-metrics/kube-state-metrics:v2.10.1`

This provides:
- Faster image pulls (cached locally)
- Reduced external dependency on Docker Hub
- Better reliability in restricted network environments

## Deployment Command

With these variables set, deploy the monitoring stack using:

```bash
helm upgrade --install monitoring-rateflix ./ci/k8s/monitoring -n projekt-mschoeffend-p
```

## Security Considerations

### High Security Variables
- `GRAFANA_ADMIN_PASSWORD`: Should be a strong, randomly generated password
- `GRAFANA_ADMIN_USER`: Consider using a non-default username

### Cluster-Specific Variables
- `MONITORING_INGRESS_SUBDOMAIN`: Should follow your organization's naming conventions
- Storage classes in values.yaml should match your cluster's available storage

## Recommended Values

For your project, I recommend these values:

```bash
export MONITORING_INGRESS_SUBDOMAIN="grafana.monitoring"
export GRAFANA_ADMIN_USER="admin"
export GRAFANA_ADMIN_PASSWORD="$(openssl rand -base64 32)"  # Generate secure password
```

This will result in Grafana being accessible at:
`https://grafana.monitoring.k8s.dbs.hof-university.de`
