# Film und Serien Berwertungsplattform

## Projektübersicht

Dieses Projekt ist eine skalierbare Webanwendung, mit der Nutzer anonym Filme und Serien bewerten können. Frontend und Backend laufen getrennt in Containern. Das Backend nutzt eine gemeinsame Daten- und Vote-Storage-Lösung, um Up- und Downvotes synchron zu halten.

## Funktionen

- **Anonyme Bewertungen**: Kein Login, alle Bewertungen verbleiben ohne Nutzerkonto.
- **Frontend-Backend-Trennung**: Frontend kommuniziert per REST-API mit dem Backend.
- **Skalierbarkeit**: Frontend- und Backend-Deployments auf Kubernetes.

## Spezifikation

1. **Frontend** (React.js)
   - Containerisiert als Deployment `frontend`.
   - Single-Page-Application (React.js) rendert dynamisch Inhalte und lädt Daten zur Laufzeit per REST-API.
   - Statische Ressourcen (CSS, JS) werden via NGINX ausgeliefert; Bilder (z.B. Film-Poster) liegen in gemeinsamem Shared Storage.
2. **Backend** (Spring Boot)
   - Containerisiert als Deployment `backend`.
   - Bietet REST-API für Film- und Serien-Listings, Detaildaten und Voting.
3. **Datenbank** (MySql)
4. **Shared Storage / Cache**
   - Cache Verwendung für Speicherung der votes vor permanenter Speicherung in der Datenbank
5. **Container Registry**
   - Docker-Images werden in der Artifactory abgelegt.

### Datenmodell

- **Content**
  - `id` (Int)
  - `kind` (Enum: movie, series)
  - `title` (String)
  - `description` (Text)
  - `year` (Int)
- **Vote**
  - `content_id` (Int)
  - `vote_type` (Enum: up, down)

## Ausfallsicherheit

Um die hohe Verfügbarkeit und Fehlertoleranz aller Komponenten sicherzustellen:

- **Replikation**: Frontend- und Backend-Deployments skalieren auf mehrere Replikate.
- **Pod Disruption Budgets**: Sicherstellung einer minimalen Pod-Zahl bei Rolling Updates und Knotenwartungen.
- **Datenbank-HA**: PostgreSQL im Primary-Replica-Setup mit automatischem Failover (z.B. via Patroni).
- **Shared Storage**: Verteiltes Storage-System für Persistenz und Ausfallsicherheit.
- **Backups & Monitoring**: Regelmäßige Backups (CronJobs) und Monitoring mit Alerting mit Prometheus

## Weiterführende Punkte

- Monitoring & Logging mit Prometheus
- CI/CD-Pipeline (GitLab CI) zum automatischen Bauen und Deployen
- semantic commits für Versionen
