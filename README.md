SmartWareHouse

SmartWareHouse è un sistema di gestione strumenti e lavori, sviluppato con Spring Boot 3, JWT per autenticazione, Spring Security, JPA/Hibernate, e MySQL.
Supporta ruoli, tracciamento utenti, gestione strumenti e lavori in corso. Include anche Swagger/OpenAPI per il testing degli endpoint.

Tecnologie:
- Java 17
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA
- MySQL
- Maven

Struttura del progetto:
/SmartWareHouse/src/main/java/com/example/demo
 
     ├─ AUTH
     ├─ Component
     ├─ Configuration
     ├─ Controller
     ├─ Dati
     ├─ DTO
     ├─ ENUM
     ├─ Exception
     ├─ Repository
     └─ Service

API Endpoints:
- Auth:
  
POST /auth/register – registra un nuovo utente

POST /auth/login – login utente, restituisce JWT
- Admin:
  
GET /admin/getLastSeen/{stringAccessCode} – ottiene la lista degli accessi recenti di un utente

DELETE /admin/deleteUser/{stringAccessCode} – elimina un utente (insieme ai suoi strumenti e lastSeen)
- Tool:
  
POST /tool/createTool – crea uno strumento

POST /tool/modTool – modifica lo stato di uno strumento

POST /tool/takeTheTool/{nameTool} – prende uno strumento

POST /tool/putTheToolAway – restituisce uno strumento

DELETE /tool/deleteTool/{nameTool} – elimina uno strumento

- Work:
  
POST /work/createWork – crea un lavoro

GET /work/takeTheJob/{nameJob} – prende un lavoro

GET /work/getAllUsersWorkingOnTheJob/{nameJob} – lista utenti su un lavoro

POST /work/updateJob – aggiorna lavoro

POST /work/completeJob/{nameJob} – chiude il lavoro

Sicurezza:
- Autenticazione JWT
- Gestione ruoli (ROLE_ADMIN, ROLE_USER, ecc.)
- Accesso agli endpoint basato su ruoli e su JWT
- Password salvate in BCrypt
     
