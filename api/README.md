# api

BFF for Ticket viewer frontend. Connect to Zendesk API to retrieve tickets information.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

You will also need Java SDK 6 and above installed.

## Development

### Running

The application requires 3 environment variables:

 * `ZENDESK_EMAIL`: your zendesk account email
 * `ZENDESK_PASSWORD`: your zendesk account password
 * `ZENDESK_SUBDOMAIN`: your registered zendesk subdomain

(Assumming you have leiningen installed) To start a web server for the application, run:

```
ZENDESK_EMAIL={email} ZENDESK_PASSWORD={password} ZENDESK_SUBDOMAIN={domain} lein ring server
```

It will start the server at `localhost:3000`

### Test

To run all test:

    lein test

## License

Copyright © 2017 by Pei Shi Yong
