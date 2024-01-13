# Verb Data Fetcher

A spring boot application designed to provide an API for dynamically configuring a database connection and persisting verb data within it.

Suppported database types:
- MySQL
- MariaDB
- PostgreSQL

## API endpoints

### Database config

**Description:** Send database configuration parameters to server and set up the connection.

- **Method:** POST

- **Path:** api/database/

- **JSON body example:**
```
{
  "url": "localhost:3306",
  "type": "MYSQL",
  "username": "root",
  "password": 1234,
  "table": "verbs"
}
```

### Varb data

**Description:** Send a array with verb data to server for persist it within the database.

- **Method:** POST

- **Path:** api/verb-data/

- **JSON body example:**
```
[
  {
    "infinitiveForm": "run",
    "simplePastForm": "ran",
    "participleForm": "run",
    "britishParticipleForm": "ran",
    "britishSimplePastForm": "run",
    "definition": "lorem ipsum dolor",
    "usageIndex": 0.000001,
    "audioUrl": "example-url.mp3",
    "phonetic": "/rʌnz/"
  }
]
```


