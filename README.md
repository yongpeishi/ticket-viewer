# Ticket Viewer

This repository consists of two projects: backend API and frontend. Both servers need to be started to complete the end to end flow.

---

## API

The API is responsible for connecting to Zendesk and returning a subset of ticket information that's required by the frontend.

It's written in Clojure. At the moment, there's only one endpoint:

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

The API requires 3 environment variables:

 * `ZENDESK_EMAIL`: your zendesk account email
 * `ZENDESK_PASSWORD`: your zendesk account password
 * `ZENDESK_SUBDOMAIN`: your registered zendesk subdomain


To start the server for the API, run:

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

The frontend provides browser UI to view Zendesk tickets. The tickets information is served by the backend API.
The application is written in ClojureScript, using Figwheel and Reagent.

### Running

To start the web server for the application, run:

```
cd frontend/
./scripts/start-server
```

The script will download the librabry dependency and start the server at [localhost:3449](http://localhost:3449/). This app will work under the assumption that the backend api has been started at `localhost:3000`.

### Usage

Enter a Zendesk ticket number (eg. `1`) into the input box on the browser. The ticket information (subject, description, and last updated date) will be displayed.

If you enter an invalid ticket number, you'll see an error message.

---

## Future Improvement

* Add test coverage for frontend code.
* Better error handling. At the moment users will see the same 'Something went wrong' message on any error other than 404.
* If scalability is a requirement, the backend can be changed to call Zendesk API asynchronously.
* Additional features
