# Ticket Viewer

This repository consists of two part: backend API and frontend. Both servers need to be started to complete the end to end flow. 

---

## API

The API is responsible for connecting to Zendesk and return a subset of data that's required by the frontend.

It's written in Clojure. At the moment, there's only one endpoint avaiable:

```
GET /tickets/:id
```

which will return a json payload like this:

```
{
  id: 1,
  subject: "...",
  description: "...",
  updated_at: "..."
}

```

### Running

The application requires 3 environment variables:
 
 * `ZENDESK_EMAIL`: your zendesk account email
 * `ZENDESK_PASSWORD`: your zendesk account password 
 * `ZENDESK_SUBDOMAIN`: your registered zendesk subdomain


To start a server for the application, run:

```
cd api/
ZENDESK_EMAIL={email} ZENDESK_PASSWORD={password} ZENDESK_SUBDOMAIN={domain} ./scripts/start-server
```

The script will download the librabry dependency and start the server at [localhost:3000](http://localhost:3000/).

### Test

To run all test:

```
cd api/
lein test
```

### Other dependency 

Java JDK version 6 or later

---

## Frontend

The frontend provides browser UI to view Zendesk tickets. The tickets information is served by a backend API. The application is written in ClojureScript, using Figwheel and Reagent.

### Running

To start the web server for the application, run:

```
cd frontend/
./scripts/start-server
```

The script will download the librabry dependency and start the server at [localhost:3449](http://localhost:3449/). This app will work under the assumption that the backend api has been started at `localhost:3000`.

