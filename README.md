Image:
Base model Midjourney 5.2
License Free

## Installeringssvejledning

### Nødvendig software
Du skal installere en Java JDK version 17 eller nyere.
Du skal installere en MySQL server.
Du skal installere git.

### Klon projektet
Åben en terminal, og skriv:

```shell
git clone https://github.com/HungVyNguyen/Carrental.git
```

### Opret databasen
Start MySQL serveren.

Du skal bruge filen `CREATE_bilabonnement_gruppe_08.sql` fra roden af dette projekt.

Åben en MySQL-prompt, og kør:

```sql
source C:\sti\til\Carrental\CREATE_bilabonnement_gruppe_08.sql
```

Hvor `C:\sit\til` er lokationen i dit filsystem, hvor du har klonet projektet.

### Indsæt data
Du skal bruge filen `INSERT_bilabonnement_gruppe_08.sql` fra roden af dette projekt.

Åben en MySQL-prompt, og kør:

```sql
source C:\sti\til\Carrental\INSERT_bilabonnement_gruppe_08.sql
```

Hvor `C:\sit\til` er lokationen i dit filsystem, hvor du har klonet projektet.

### Kompiler programmet
cd til mappen, som git klonede projektet til.
Projektet bruger maven. Du kan installere maven seperat eller bruge maven wrapper i roden af projektet.

Med maven installeret bruges kommandoen `mvn`.
Med maven wrapper bruges `mvnw.cmd` på windows og `./mvnw` på linux.

Kompiler:

```shell
mvn clean package
```

Udskift `mvn` med maven wrapper kommando, hvis du ikke har det installeret.

### Kør programmet
Efter succesfuld kompilering vil der være en target mappe i roden af projektet.
I denne mappe vil være en jar file med navnet `Carrental-0.0.1-SNAPSHOT.jar`.

Kør kommandoen:
```shell
java -jar target/Carrental-0.0.1-SNAPSHOT.jar
```

Du kan også flytte jar filen til en anden lokation og køre den derfra.