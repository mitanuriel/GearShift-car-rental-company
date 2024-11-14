This is a fullstack project in web development, focused to create a car rental company with the help of Java Spring Boot and frontend tools - HTML, Thymeleaf and CSS

Open the link: http://51.107.208.115:8080/
password:123

Image used:
Base model Midjourney 5.2
License Free

---
## Installeringssvejledning

### Nødvendig software:
- [OpenJDK](https://openjdk.org/) version 17 eller nyere
- [MySQL](https://www.mysql.com/) server
- [Git](https://git-scm.com/)

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
I denne mappe vil være en jar fil med navnet `Carrental-0.0.1-SNAPSHOT.jar`.

Kør kommandoen:
```shell
java -jar target/Carrental-0.0.1-SNAPSHOT.jar
```

Du kan også flytte jar filen til en anden lokation og køre den derfra.

**VIGTIGT!** Sæt miljøvariablen ``dbpw`` til kodeordet for bruger5. CREATE-scriptet sætter det automatisk til ``12345``, men du kan ændre det, hvis du vil.
