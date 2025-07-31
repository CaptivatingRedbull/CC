# [1.22.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.21.0...v1.22.0) (2025-07-31)


### Features

* enhance monitoring stack with kube-state-metrics integration and configuration updates ([ca39a31](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/ca39a31f6890acea8c32c9bf07e2d7d083b5b65f))

# [1.21.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.20.2...v1.21.0) (2025-07-29)


### Bug Fixes

* update output messages for production monitoring deployment ([696c1ac](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/696c1acde337e79b7e1dba928bafda2dc94e7156))


### Features

* add Prometheus metrics support for backend, frontend, PostgreSQL, and Redis services ([dcec48a](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/dcec48ab639563f8a544ccabc76c2c33574798c4))
* implement Grafana and Prometheus monitoring stack with Helm configuration ([cc7b854](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/cc7b854835e9ecadfbe6e0bd96f8fb142d02e9c8))

# [1.21.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.20.2...v1.21.0) (2025-07-29)


### Features

* add Prometheus metrics support for backend, frontend, PostgreSQL, and Redis services ([dcec48a](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/dcec48ab639563f8a544ccabc76c2c33574798c4))

## [1.20.2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.20.1...v1.20.2) (2025-07-29)


### Bug Fixes

* standardize RELEASE_NAME to "rateflix" in deployment scripts ([5cd59f0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/5cd59f0f36edae020a4a6f1c5bd9f650f1a9f81e))

## [1.20.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.20.0...v1.20.1) (2025-07-29)


### Bug Fixes

* add deploy_p_system stage and include for deployment in CI pipeline ([172930a](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/172930ad79a0b0a3ebb20a97cf7cad257b40d729))

# [1.20.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.19.10...v1.20.0) (2025-07-29)


### Features

* implement production deployment configuration with Helm for backend, frontend, PostgreSQL HA, and Redis ([892193b](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/892193b203092911ca481f12fbd3e87b12ecc3c9))

## [1.19.10](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.19.9...v1.19.10) (2025-07-29)


### Bug Fixes

* add Helm annotations for data initialization job to manage lifecycle ([59e54a9](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/59e54a917bede039e95f8898ee1179e9b7d1f973))

## [1.19.9](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.19.8...v1.19.9) (2025-07-29)


### Bug Fixes

* add PostgreSQL and Redis HA failover test jobs to CI pipeline ([a83c62c](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/a83c62c21b8910c006a7513834d2963c24eba40b))

## [1.19.8](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.19.7...v1.19.8) (2025-07-29)


### Bug Fixes

* update Redis configuration; enable HA, increase replica count, and add persistence settings ([21303b9](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/21303b9863453c3c5e5513ab61a07a9e8db0ef2f))

## [1.19.7](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.19.6...v1.19.7) (2025-07-29)


### Bug Fixes

* enhance data initialization handling in deployment; add timeout and completion checks for data init job ([66d9b4b](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/66d9b4b33924cdcc2e600871fe4a5415e8f6eacf))

## [1.19.6](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.19.5...v1.19.6) (2025-07-29)


### Bug Fixes

* simplify database URL configuration in secret.yaml; unify username and password references for PostgreSQL ([83ed9de](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/83ed9de171342c78bc3f67f6d42b800f7dc15076))

## [1.19.5](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.19.4...v1.19.5) (2025-07-29)


### Bug Fixes

* remove unused PostgreSQL initialization job and related configurations; enable data initialization in Helm chart ([5f8002d](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/5f8002dbda3e40ada3733585ea2fef5b9c9b0d09))
* update deployment scripts to disable data initialization and improve readiness checks ([745a553](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/745a553bb697b70fb2acd4a55330ee670a68948e))

## [1.19.4](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.19.3...v1.19.4) (2025-07-28)


### Bug Fixes

* trigger release ([a53a453](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/a53a453c5635777b3e54db36c163a3153d113c4c))

## [1.19.3](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.19.2...v1.19.3) (2025-07-28)


### Bug Fixes

* add missing @Column annotations for upVote and downVote fields in Content class ([1a6d676](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/1a6d676cff5acb5ac78c079a55ad5663ccab5a8e))

## [1.19.2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.19.1...v1.19.2) (2025-07-28)


### Bug Fixes

* update Redis and PostgreSQL image names to include 'library' prefix in configuration files ([81512d9](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/81512d9a2cabff86815f943c4b7205c1cecc3598))

## [1.19.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.19.0...v1.19.1) (2025-07-28)


### Bug Fixes

* update image paths in CI configuration to include 'library' prefix ([1b3f50d](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/1b3f50da6b33bae9e240a5fab2a1d221813e6ec0))

# [1.19.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.18.5...v1.19.0) (2025-07-28)


### Features

* implement PostgreSQL HA configuration with failover support and related updates, improve vote handeling ([36ebf73](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/36ebf7354ad333b49ac7d14c30430ec7a9488e28))

## [1.18.5](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.18.4...v1.18.5) (2025-07-28)


### Bug Fixes

* update deployment script to include CI_REGISTRY_CACHE and correct subdomain variable ([3e6fc03](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/3e6fc03f27ec8d8fc7e2846ab31b18e5248b5037))

## [1.18.4](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.18.3...v1.18.4) (2025-07-28)


### Bug Fixes

* remove colon from ingress host echo statement in deployment scripts ([41aafc4](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/41aafc467bdc9ac45f5cc0420821fff269fae554))
* update registry configurations and improve kubeconfig handling in CI/CD scripts ([04c90d9](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/04c90d939c8e8b5da2e74583704d7d32ad2e09b3))

## [1.18.3](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.18.2...v1.18.3) (2025-07-26)


### Bug Fixes

* increase CPU limit for backend service ([f2d9266](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/f2d92668bd7f559369582f49a64a1db8d017498a))

## [1.18.2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.18.1...v1.18.2) (2025-07-26)


### Bug Fixes

* update Kubernetes configuration ([a302974](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/a302974f977e0286c9dff948c800fdd0066895d1))

## [1.18.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.18.0...v1.18.1) (2025-07-25)


### Bug Fixes

* add ActiveProfiles annotation to test classes and prevent app exit in test properties ([0d8047b](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/0d8047b9262cf5b5a0fd8e89d2073c59366b0f19))
* disable release for gitlab ([860e091](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/860e0919244594542eab4652afb6a3fab6673eba))
* ensure publish is disabled in release configuration ([96d8b3c](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/96d8b3c3efda2bd7b451d49dbc38da3e90023b00))
* remove GitLab plugin configuration from release config ([b82ce7b](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/b82ce7b87f89d427c856e785b6083eaf70093c8d))
* remove publish option from release configuration ([7288a75](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/7288a75009c97227e2982e1aaf21d4ed18f1eb90))

## [1.18.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.18.0...v1.18.1) (2025-07-25)


### Bug Fixes

* add ActiveProfiles annotation to test classes and prevent app exit in test properties ([0d8047b](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/0d8047b9262cf5b5a0fd8e89d2073c59366b0f19))
* disable release for gitlab ([860e091](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/860e0919244594542eab4652afb6a3fab6673eba))
* ensure publish is disabled in release configuration ([96d8b3c](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/96d8b3c3efda2bd7b451d49dbc38da3e90023b00))
* remove GitLab plugin configuration from release config ([b82ce7b](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/b82ce7b87f89d427c856e785b6083eaf70093c8d))

## [1.18.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.18.0...v1.18.1) (2025-07-25)


### Bug Fixes

* add ActiveProfiles annotation to test classes and prevent app exit in test properties ([0d8047b](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/0d8047b9262cf5b5a0fd8e89d2073c59366b0f19))
* remove GitLab plugin configuration from release config ([b82ce7b](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/b82ce7b87f89d427c856e785b6083eaf70093c8d))

## [1.18.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.18.0...v1.18.1) (2025-07-25)


### Bug Fixes

* add ActiveProfiles annotation to test classes and prevent app exit in test properties ([0d8047b](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/0d8047b9262cf5b5a0fd8e89d2073c59366b0f19))

# [1.18.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.17.0...v1.18.0) (2025-07-08)


### Bug Fixes

* remove artifacts from build template ([7e02a64](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/7e02a648343e14a28b7076b695d4be676c08f9c3))
* remove remnants of changed dockerfile ([5082bc7](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/5082bc7758a1b0023bd2193b84970642707931e4))
* remove unneccessary command from Dockerfile ([c06c4d1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/c06c4d19064a019dd1d5c7fa5151cf58e08d84b4))


### Features

* update dockerfiles and build step to not scan etc if wanted can be done in Harbor ([8967ca3](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/8967ca30d39ec2937c548736b4edc8fcaeb6850b))

# [1.17.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.16.0...v1.17.0) (2025-07-03)


### Bug Fixes

* add installation of curl and ca-certificates in kubectl Dockerfile ([aa8eb65](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/aa8eb6589125a9b801b099b371346a177354f731))


### Features

* deployment process to use Helm for PostgreSQL and application services ([0e551c8](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/0e551c8f4965ffa0471452ff6e7d222402789853))

# [1.16.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.15.0...v1.16.0) (2025-06-30)


### Features

* improved score calculation to Content model and update related components ([dc66cfb](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/dc66cfbbc703e34e857dd7d8a0b813ad9476d847))

# [1.15.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.14.3...v1.15.0) (2025-06-30)


### Features

* update CI configuration for improved testing and deployment processes and gernerall cleanup ([32ff1c5](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/32ff1c54a1548a71a9931ddee90e9db27611a770))

## [1.14.3](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.14.2...v1.14.3) (2025-06-26)


### Bug Fixes

* update ESLint configuration to ignore coverage directory and suppress React act warnings in tests ([ddebb96](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/ddebb96d13bf7b1064534dfcae7569006bd582de))

## [1.14.2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.14.1...v1.14.2) (2025-06-26)


### Bug Fixes

* enhance frontend testing setup with coverage reports and error suppression ([08417e8](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/08417e85137935a7b0d46b102301a81648d3508a))

## [1.14.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.14.0...v1.14.1) (2025-06-26)


### Bug Fixes

* update CI images for backend and frontend tests to use latest versions ([d876e57](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/d876e57c08a133e64a95aec0dadb6d67775a853c))

# [1.14.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.13.0...v1.14.0) (2025-06-26)


### Features

* Add unit and integration test configurations for backend and frontend ([4505305](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/45053058b115f371ea701cf40bb412b25d244282))

# [1.13.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.12.0...v1.13.0) (2025-06-25)


### Bug Fixes

* added oras login ([60dea80](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/60dea8078eb24cd101ad3f7a10da93bc236d3f77))
* correct image tag formatting in build push sign template ([2bf8c6d](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/2bf8c6d93b4777bd531777dd36bbf23a6c99a0b8))
* make format comatible with harbor ([494b186](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/494b186fb0f7bc0a6e24b03d441a6a2372756b88))
* move Trivy installation to a separate RUN command ([7560453](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/75604532a39955cbce716a6cd6fc5d31534f6613))
* remove empty lines ([d93c5d8](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/d93c5d8655beba3ea126a7b8ceae872c3c93661a))
* update artifact handling and remove unnecessary push commands which are incompatible with harbor ([fc74c08](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/fc74c08ee7a313dfcc2f34d3bc04fc11e8e1346d))
* update image list options and remove unused postgres image from scheduled tool images ([3cc3928](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/3cc392837aad28ef93765f0cdd65062b4880e420))
* update SBOM argument in buildctl command for image building [skip ci] ([e2fb92d](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/e2fb92d1808f3bdf1962a133daf201d725b370e6))
* update vulnerability scan output format and add summary step ([f68a405](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/f68a405f2b3d581a719f1e48e09e0ebae34c9ebc))


### Features

* add sbom to images [skip ci] ([1210470](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/121047096356fbd73aed13a3af92f946a869c498))
* update Dockerfiles to use images from the harbor registry and enhance CI/CD pipeline with SBOM and vulnerability scanning ([e3263b2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/e3263b2831411f24b0602dd5d75c02c68d26a746))

# [1.12.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.11.0...v1.12.0) (2025-06-25)


### Bug Fixes

* update deployment stage name to 'deploy_t_system' ([35530a7](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/35530a7dd020e8531bf29a9d7a6b480205fce84e))
* update deployment stage name to 'deploy_t_system' in PostgreSQL deployment files ([e14a8c2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/e14a8c2ca87e8d604ea37500fa88391a2b4c66ca))


### Features

* update application configuration and security settings, add actuator endpoints, and modify deployment files ([06f497f](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/06f497fb9aadc42e36eb5b9990c6cecb6a573979))

# [1.11.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.10.7...v1.11.0) (2025-06-24)


### Features

* add seperate jobs for initialisation of postgres and sample data ([3893bd7](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/3893bd75f4372585b6a39c23c20f12e67d70b730))

## [1.10.7](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.10.6...v1.10.7) (2025-06-24)


### Bug Fixes

* remove readiness and liveness probes from backend deployment configuration ([40ba204](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/40ba204553aa7cb972175caf6bccc6fe672c04f2))

## [1.10.6](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.10.5...v1.10.6) (2025-06-24)


### Bug Fixes

* add postgres image to harbor to load images faster in deployment ([5367482](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/53674827577ea6ff759a1d81a1c5c593f63753db))

## [1.10.5](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.10.4...v1.10.5) (2025-06-24)


### Bug Fixes

* delete statefull set before applying new for testing ([98cfcea](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/98cfceac740880779a26a151715171f9db74c51f))

## [1.10.4](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.10.3...v1.10.4) (2025-06-24)


### Bug Fixes

* update PostgreSQL deployment to use StatefulSet with 3 replicas and remove secret reference ([9a3c305](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/9a3c305d4877c396783cd8aef786ed89b1a7c215))

## [1.10.3](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.10.2...v1.10.3) (2025-06-24)


### Bug Fixes

* change PostgreSQL deployment to StatefulSet and update volume claims ([84fe4a6](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/84fe4a600b49424450f85beb4a9b11cf18e6cb73))

## [1.10.2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.10.1...v1.10.2) (2025-06-24)


### Bug Fixes

* increase failure thresholds for readiness and liveness probes in deployment configuration ([3a8ee4e](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/3a8ee4eab5978dd29840ec0f0a2cd4c2e549da48))

## [1.10.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.10.0...v1.10.1) (2025-06-24)


### Bug Fixes

* update deployment configurations and add simplified deployment files for backend, frontend, and postgres ([5cd1304](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/5cd130467aa451b1c90d0dddb30af87ad25da211))

# [1.10.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.9.0...v1.10.0) (2025-06-24)


### Features

* add database for backend, add ressouce config, add healthchecks ([487afef](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/487afef1e8122805389f939aa70472e2f3908bf2))

# [1.9.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.8.6...v1.9.0) (2025-06-23)


### Bug Fixes

* add cosign login step before signing images in CI/CD pipeline ([3056b96](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/3056b962df52433b2e55127af9f19e1939333442))
* remove colon from echo statement for tool image version ([1039344](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/1039344ee1a6d146665a81e4ced408a761a14cfe))
* remove TOOL_IMAGE_VERSION variable and use 'latest' tag for tool images ([9ee50ad](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/9ee50add6184749b209851e08a15f22b12e3440f))
* update TOOL_IMAGE_VERSION to use CI_PIPELINE_CREATED_AT format ([4fe526a](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/4fe526ae312d3867993f4e7efa0f0c1dd845f0ad))


### Features

* add scheduled pipeline for building and pushing tool images with updated Dockerfiles ([b5a9341](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/b5a93410c2c2da9364e6403ab2552083b9b30bae))
* enhance CI/CD pipeline for tool images with new scheduling rules and improved variable management ([8af2572](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/8af2572aae082f4ab53ea1a39523ad1127f1044d))
* switched to pipeline with inputs for scheduled building of images ([517f3ab](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/517f3ab2b8e20a2ed1d617de991d96ce99cf0e0f))

## [1.8.6](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.8.5...v1.8.6) (2025-06-23)


### Bug Fixes

* update Ingress paths for API and root to improve routing ([3972ccc](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/3972ccce3d71264e7ddd4c4a63181261dc8fc39b))

## [1.8.5](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.8.4...v1.8.5) (2025-06-23)


### Bug Fixes

* update CORS_ALLOWED_ORIGINS and refactor API_BASE_URL to use relative URL ([8d588aa](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/8d588aab0e57fb05d52740cc4bea828f7a03c353))

## [1.8.4](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.8.3...v1.8.4) (2025-06-23)


### Bug Fixes

* refactor deployment script to export variables and improve debugging ([cea6ca6](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/cea6ca61fb0d3cfdb147546a89523dd065e67e6f))

## [1.8.3](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.8.2...v1.8.3) (2025-06-23)


### Bug Fixes

* hardcode the api url ([e5c2117](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/e5c21177997b30f8545bb626d0d9144dec6a486b))

## [1.8.2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.8.1...v1.8.2) (2025-06-22)


### Bug Fixes

* add CORS configuration and environment variable for allowed origins and set VITE_API_URL during build ([57300dc](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/57300dc3cb900c96144586a4f7eaa9a256632994))

## [1.8.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.8.0...v1.8.1) (2025-06-19)


### Bug Fixes

* update skopeo inspect command to use correct format for image digest ([5905529](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/59055293ab49c20656a2737141b5e64218e50787))

# [1.8.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.7.1...v1.8.0) (2025-06-19)


### Bug Fixes

* rename build_push_sign and update needs in deploy job ([d340306](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/d34030620bc4815decf7b97ffd6d2477b8083591))
* update build and push job reference in CI configuration ([6689d01](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/6689d011998875214e1cc749b7676ecbb468821a))
* update deployment job dependencies to streamline build process ([62df570](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/62df570fe4d8495be6043a8f8bcc29203fe046df))


### Features

* consolidate build and push jobs into a single configuration for backend and frontend and create custom image for build stage ([235559a](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/235559af85e6762660a8a7a56ab4bbbc6482f323))

## [1.7.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.7.0...v1.7.1) (2025-06-19)


### Bug Fixes

* refactor API base URL handling for improved readability and error logging ([df43bbb](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/df43bbb6878288db8b51fcbb69844338f6494577))

# [1.7.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.6.12...v1.7.0) (2025-06-19)


### Bug Fixes

* wrap image references in quotes for proper parsing in deployment files ([ffa5d17](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/ffa5d17d86aa823700dccee433c065cf21cc8f72))


### Features

* add custom image for release to reduce traffic and speed up the job ([90790da](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/90790dab956baa0142926649eae14a6f9c81ecdd))

## [1.6.12](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.6.11...v1.6.12) (2025-06-17)


### Bug Fixes

* add  environment variable echoes in deployment script for debugging ([63a0f3c](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/63a0f3cf05bbbbf0792fbb08d02a9e6d8cde353a))

## [1.6.11](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.6.10...v1.6.11) (2025-06-17)


### Bug Fixes

* update image references in deployment files for backend and frontend services ([961ca85](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/961ca854545263f7f46030cfc17fa8c63e3603cb))

## [1.6.10](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.6.9...v1.6.10) (2025-06-17)


### Bug Fixes

* update Kubernetes configuration and deployment files for backend and frontend services ([589cdc2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/589cdc29aa5c35918ec7147b74a6c43fb3ee7227))

## [1.6.9](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.6.8...v1.6.9) (2025-06-17)


### Bug Fixes

* fixed backend problems with enums and cloumn names in h2 and updated the deployments ([506d214](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/506d214fb26c7562fd9986b199eb485d33b84d08))

## [1.6.8](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.6.7...v1.6.8) (2025-06-17)


### Bug Fixes

* add missing argument to kubectl apply command in deploy_t.yml ([058bd5c](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/058bd5cf172a3a3ee5abde65f405ca1d48e843a3))

## [1.6.7](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.6.6...v1.6.7) (2025-06-17)


### Bug Fixes

* update image in deploy_t.yml from alpine/helm to bitnami/kubectl ([1fa1f05](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/1fa1f054fb29ba40c4a62ce96f72bcbc6da6c551))

## [1.6.6](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.6.5...v1.6.6) (2025-06-16)


### Bug Fixes

* add newline at end of deploy_t.yml for to trigger release ([15192e0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/15192e099519c190b9c19bf495261ba4e4faa045))

## [1.6.5](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.6.4...v1.6.5) (2025-06-16)


### Bug Fixes

* update backend environment variables and add placeholders for sensitive data ([f970bf1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/f970bf16ed91f64b34da7762292569b7104ff93c))

## [1.6.4](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.6.3...v1.6.4) (2025-06-16)


### Bug Fixes

* add new ci variable for project name and update image repository paths in build and deploy scripts ([a246ce0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/a246ce07012999cc9509240defb35a94194513e9))
* remove ':' from frontend and backend repository echo statements ([9fd5970](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/9fd5970a99cfac6a2bd3801d4b92db12f7ab8cd1))
* removed ':' from build and push jobs ([977e01e](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/977e01e6ec33de83ee2056894db509b8aaf053a9))

## [1.6.3](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.6.2...v1.6.3) (2025-06-15)


### Bug Fixes

* helm linting does not fail anymore ([f25b738](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/f25b7387fb283798c6c7bf2bf5d19f86f1cee3d1))

## [1.6.2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.6.1...v1.6.2) (2025-06-15)


### Bug Fixes

* update helm dependency paths and versions in deploy_t.yml and Chart.yaml ([100c8b1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/100c8b1a469e2a0e522ffe9a0df13669b57dccb1))

## [1.6.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.6.0...v1.6.1) (2025-06-15)


### Bug Fixes

* correct file paths in deploy script for k8s.yaml and values-t-system.yaml ([3ac202d](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/3ac202dd5e2a6a7bf6b8f7d22a9fca8ad37471d7))

# [1.6.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.5.6...v1.6.0) (2025-06-15)


### Features

* reworked helm ([85ed06f](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/85ed06fe0fda8d2e7904defc11265f444502902e))

## [1.5.6](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.5.5...v1.5.6) (2025-06-15)


### Bug Fixes

* update ingress paths and change pathType to ImplementationSpecific ([c4ab0d6](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/c4ab0d616932c68c77232166d2e91806b3961772))

## [1.5.5](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.5.4...v1.5.5) (2025-06-15)


### Bug Fixes

* update ingress annotations for nginx configuration ([a98205e](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/a98205eefc186b5512286e527d74aa35a1050943))

## [1.5.4](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.5.3...v1.5.4) (2025-06-15)


### Bug Fixes

* correct file path for values-t-system.yaml in deployment script ([b032d0e](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/b032d0e085c4d90d682d9aea502c0b155b43be72))

## [1.5.3](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.5.2...v1.5.3) (2025-06-15)


### Bug Fixes

* correct path for values-t-system.yaml in deployment script ([56facb0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/56facb0c55abdba31ab2807ed8b383a9757ee80b))

## [1.5.2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.5.1...v1.5.2) (2025-06-15)


### Bug Fixes

* update image to alpine/helm and use RELEASE_VERSION for image tags ([4fe9b38](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/4fe9b385790218819cdc938cf69576e28250e2fc))

## [1.5.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.5.0...v1.5.1) (2025-06-15)


### Bug Fixes

* specify correct path for k8s.yaml in deployment script ([65a5095](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/65a50951f7863e06d622bb826ebfa59171c77a6e))

# [1.5.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.4.8...v1.5.0) (2025-06-15)


### Bug Fixes

* add k8s configuration file and cp command for KUBECONFIG ([b08dd70](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/b08dd709b53de2141584e1ca5f219330dbb3eee3))


### Features

* use helm again with the corect kubeconfig ([f84cf5b](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/f84cf5b1cede3bf2e87c62b36fd5d77b9847a702))

## [1.4.8](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.4.7...v1.4.8) (2025-06-15)


### Bug Fixes

* add PGDATA environment variable to PostgreSQL deployment configuration ([34329d9](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/34329d977020b1b69b8b5db7b43589656e85f112))

## [1.4.7](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.4.6...v1.4.7) (2025-06-15)


### Bug Fixes

* update deployment configurations to include necessary environment variables and clean up k8s configuration ([9dd219e](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/9dd219e2ecd257031012e144c109f3f7493465cd))

## [1.4.6](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.4.5...v1.4.6) (2025-06-14)


### Bug Fixes

* update backend and frontend deployment images to use specific registry URL ([a75ad53](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/a75ad53cfb84d6253317f293084ce290ead2d3f6))

## [1.4.5](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.4.4...v1.4.5) (2025-06-14)


### Bug Fixes

* update backend and frontend images to handle registry URL formatting ([b8434ad](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/b8434ad92ea02424203babf55b542b87a53b8917))

## [1.4.4](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.4.3...v1.4.4) (2025-06-14)


### Bug Fixes

* correct job dependencies in deployment configuration ([8982253](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/89822538a3f33d192fd9ab37a8e66446f129cc3d))
* update deployment configurations to use dynamic image versioning ([de971cb](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/de971cb577dc0ce2472412d0d9ff55e542de0f37))

## [1.4.3](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.4.2...v1.4.3) (2025-06-14)


### Bug Fixes

* update backend and frontend images to use 'latest' tag ([61a2780](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/61a278019778df9b0599417dd4479bb7df1504ac))

## [1.4.2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.4.1...v1.4.2) (2025-06-14)


### Bug Fixes

* update ingress paths to correct regex patterns ([20e7295](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/20e7295e08ba7df8c01329843e93ddecd47a2e35))

## [1.4.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.4.0...v1.4.1) (2025-06-14)


### Bug Fixes

* add missing regex annotation to ingress configuration ([e0593c5](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/e0593c5c40422d02b55f7a2b3c91caecec907799))
* correct order of stages in CI configuration ([f9aab80](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/f9aab80a58c20311178e4df816078cefc5831955))

# [1.4.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.3.2...v1.4.0) (2025-06-14)


### Features

* add redis deployment manifest for Kubernetes ([45b4703](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/45b470308f830c12ade8827b9c30300c8678b4c6))

## [1.3.2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.3.1...v1.3.2) (2025-06-14)


### Bug Fixes

* update deployment script to reference correct k8s.yaml file ([d645d7f](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/d645d7f173798493bbc879eafed499e5631a8502))

## [1.3.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.3.0...v1.3.1) (2025-06-14)


### Bug Fixes

* update deployment script to create namespace and set context for kubectl ([1314adb](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/1314adb047f80cba4e809619e825dd46bddfab45))

# [1.3.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.2.11...v1.3.0) (2025-06-14)


### Features

* refactor deployment to use kubectl and remove HelmChart configurations ([f5101d3](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/f5101d317a6e3873a739a6d14724198e0f13b1ac))

## [1.2.11](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.2.10...v1.2.11) (2025-06-14)


### Bug Fixes

* move file to correct location ([82598da](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/82598da2703c49372b5f87d74c7a866bbd82d87f))

## [1.2.10](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.2.9...v1.2.10) (2025-06-14)


### Bug Fixes

* update deployment script to reference new HelmChart manifest location and restore HelmChart configuration ([2e0f4cf](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/2e0f4cfb7153bedc5bc48ae34a6bd196d8e006ec))

## [1.2.9](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.2.8...v1.2.9) (2025-06-14)


### Bug Fixes

* update deployment script to use kubectl and add HelmChart configuration ([35ce7e4](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/35ce7e48251fd0fe52396ef4c1b4e4705fc66d88))

## [1.2.8](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.2.7...v1.2.8) (2025-06-14)


### Bug Fixes

* reorder helm upgrade command options for clarity and consistency ([999c8c7](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/999c8c74afa6e2bff1df11e2d267df2ef7dbf7c9))

## [1.2.7](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.2.6...v1.2.7) (2025-06-14)


### Bug Fixes

* remove secret management by k8s to avoid permission issue ([660e6aa](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/660e6aa850024e5951722d536284d2c415ad369b))

## [1.2.6](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.2.5...v1.2.6) (2025-06-14)


### Bug Fixes

* add debugging information for Helm installation in deployment script ([10ec22a](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/10ec22a7a77a285a1f8d110d3faa33e076d51cf0))

## [1.2.5](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.2.4...v1.2.5) (2025-06-14)


### Bug Fixes

* update helm image version to 3.15.2 in deployment configuration so --driver is available ([775d10d](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/775d10d3330a4f3bdc3a44d07eebb264061f2ac4))

## [1.2.4](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.2.3...v1.2.4) (2025-06-14)


### Bug Fixes

* add missing driver option in helm upgrade command for testing deployment to avoid permission issue ([5a17bfb](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/5a17bfb0da4169ac7f5ec514a15bd1aa06730749))

## [1.2.3](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.2.2...v1.2.3) (2025-06-14)


### Bug Fixes

* remove unnecessary colons in push image echo statements ([49ca82c](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/49ca82c7719c875032520d01c722150626570257))
* update build scripts to include skopeo and streamline image pushing process ([40fbb87](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/40fbb87d60d1ece705d0c9e804e6b37305cf0381))

## [1.2.2](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.2.1...v1.2.2) (2025-06-14)


### Bug Fixes

* create .docker directory before writing config ([2fe2517](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/2fe25172f936e5b4b4c0e28eec5d52b7b465d7cc))

## [1.2.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.2.0...v1.2.1) (2025-06-14)


### Bug Fixes

* change include to propper files and renamed them to fit the new jobs ([e37e376](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/e37e376b2e44743957e5fab99f0e517f852f110b))
* remove unnecessary colon in build and push image echo statements ([7f5cc9f](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/7f5cc9f28b37ffb6d01a73cac79c085e6ef66893))
* to large images get pushed directly after build ([c375a47](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/c375a47c9f95e5e07531e9ef96130e1978bfbac8))

# [1.2.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.1.1...v1.2.0) (2025-06-14)


### Features

* add build, push, and deploy stages to pipeline ([614a422](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/614a4229e8bff28015d20a400a516c316eb2d482))

## [1.1.1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.1.0...v1.1.1) (2025-06-14)


### Bug Fixes

* correct include syntax in .gitlab-ci.yml ([93574a1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/93574a1a6a73c2753cfcff0406d365906c038a10))
* update include paths in .gitlab-ci.yml for build version components ([72ae0b5](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/72ae0b5f3c255ab9c20c94f51825e1a2d1a15745))
* update include paths in .gitlab-ci.yml to absolute paths ([389baa4](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/389baa4f3ef052376a76051816405122d0122183))

# [1.1.0](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/compare/v1.0.0...v1.1.0) (2025-06-14)


### Bug Fixes

* add GitLab integration for semantic release ([bdd90da](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/bdd90dac2a7f570ae9102132abb0f79b17fa0279))
* add missing comma in release.config.js plugins array ([78e99f1](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/78e99f197a6ae23592427406b5ac0673b41359aa))


### Features

* update GitLab CI configuration and remove release_version.txt ([18f5a46](https://gitlab.hof-university.de/ccvinf2025/mschoeffend_1652/commit/18f5a465d676e6cabfd7333785b5c6f28d538b0b))
