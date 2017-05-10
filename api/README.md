# api

BFF for Ticket viewer frontend. Connect to Zendesk API to retrieve tickets information.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Development

### Running

The application require several environment variable to run.

`ZENDESK_EMAIL` is your zendesk account email
`ZENDESK_PASSWORD` is your zendesk account password
`ZENDESK_SUBDOMAIN` is your registered zendesk subdomain

To start a web server for the application, run:

```
ZENDESK_EMAIL={email} ZENDESK_PASSWORD={password} ZENDESK_SUBDOMAIN={domain} lein ring server
```

### Test

To run all test:

    lein test

## License

Copyright © 2017 by Pei Shi Yong
