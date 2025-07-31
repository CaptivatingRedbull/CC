# Film und Serien Berwertungsplattform

## Projektübersicht

Dieses Projekt ist eine skalierbare Webanwendung, mit der Nutzer anonym Filme und Serien bewerten können. Frontend und Backend laufen getrennt in Containern. Das Backend nutzt eine gemeinsame Daten- und Vote-Storage-Lösung, um Up- und Downvotes synchron zu halten.

## Funktionen

- **Anonyme Bewertungen**: Kein Login, alle Bewertungen verbleiben ohne Nutzerkonto.
  - kommt mit dem Problem, dass Nutzer mehrere Bewetungen abgeben können
- **Filtern und Sortieren**: Nutzer können die Liste an Inhalten sortieren und Durchsuchen
- **Admin Funktionen**: Neue Inhalte erstellen, bearbeiten, löschen und weitere admin konten erstellen

## Spezifikation

1. **Frontend** (React + Vite)
   - Containerisiert als Deployment `frontend`.
   - Frägt Inhalte über die REST-API ab und stellt sie dar, bietet Nutzern die Möglichkeit mit den Inhalten zu interargieren
2. **Backend** (Spring Boot)
   - Containerisiert als Deployment `backend`.
   - Bietet REST-API für Film- und Serien-Listings, Detaildaten und Voting.
3. **Datenbank** (Postgres)
4. **Shared Storage / Cache**
   - Cache Verwendung für Speicherung der votes vor permanenter Speicherung in der Datenbank

### Datenmodell

- **Content**
  - `id` (Long)
  - `kind` (Enum: MOVIE, SERIES)
  - `title` (String)
  - `description` (Text)
  - `year` (Int)
  - `upVote` (Int)
  - `downVote` (Int)
  - `score` (Int) [`upVote - downvote`]
- **User**
  - `id` (Long)
  - `username` (String)
  - `password` (String)

## Pipeline

- **build_version**:

  - **release_version**: Erstellt Changelog, Gitlab releases und die Versions Nummer, die von der Restlichen Pipeline verwendet wird.
  - **check_release_version**: Überprüfen, ob eine neue version erstellt wurde, da manche Jobs diese brauchen.

- **unit_tests**:

  - **backend_unit_test**
  - **frontend_lint**
  - **frontend_tests**

- **integration_tests**:

  - **backend_integration_tests** (mit redis service)

- **build**:

  Erstellt die Images für das front- und backend.
  Die images werden erstellt, hochgeladen mit einem versions nummer tag von **release_version**, mittels oras wird der latest tag hinzugefügt, ohne das das gesamte Image nochmals hochgeladen werden muss.
  Das Image wird signiert und es werden der SBOM erstellt und auf schwachstellen gescannt.

- **deploy_t_system**

  - **deploy_t_system**: erstellt die deployments für frontend, backend und redis im t-system namespace (inkl. services, ingresses)
  - **deploy_postgres_t**: erstellt das deployment für die postgres Datenbank (wird seperat behandelt, da es nicht einfach applied werden kann auf grund des statefull sets)
  - **initialize_test_data_t**: erstellt einen k8s job, der einen backend pod startet (mit testData Profil aktiv), dieser erstellt einmalig die test daten

  (Die jobs **deploy_postgres_t**, **initialize_test_data_t** sind manuell aus zu lösen, da sie nur bei änderungen an der Datenbank ausgeführt werden müssen)

### Scheduled Pipeline

  Stellt Tool Images bereit, die von der eigentlichen Pipeline verwendet werden.
  Hier werden die notwendigen Wekzeuge installiert, sodass die Pipeline schneller abläuft und Bandbreite Geschont wird.
