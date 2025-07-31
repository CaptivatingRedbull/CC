# Stage 1: Build the React application
FROM harbor.k8s.dbs.hof-university.de/docker-io/library/node:20-alpine AS builder
WORKDIR /app
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ ./
RUN npm run build

# ---

# Stage 2: Serve the static files with NGINX
FROM harbor.k8s.dbs.hof-university.de/docker-io/library/nginx:stable-alpine
COPY --from=builder /app/dist /usr/share/nginx/html
# Das NGINX-Config wird ebenfalls aus dem Docker-Ordner kopiert
COPY ci/docker/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
