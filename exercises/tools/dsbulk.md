# DSBulk

[DSBulk](https://docs.datastax.com/en/dsbulk/doc/dsbulk/reference/dsbulkCmd.html) is a bulk loader for Apache Cassandra. [Here](https://docs.datastax.com/en/astra/docs/loading-and-unloading-data-with-datastax-bulk-loader.html) is the official documentation to use it with DataStax Astra.

## Installation

Please follow the instructions from the official documentation [here](https://docs.datastax.com/en/dsbulk/doc/dsbulk/install/dsbulkInstall.html#Installationsteps).
Once installed, you should be able to run `dsbulk` command from your terminal.

To use DSBulk against DataStax Astra, you will need to download Astra secured bundle to authenticate.
Then generate an Astra token for DSBulk to authenticate [here](https://astra.datastax.com/settings/tokens).

## Load data

```shell
dsbulk load -url movies.csv -k catalog -t movies -header true -b "~/Downloads/secure-connect-dataflix.zip" -u <client_id> -p <client_secret>
```

## Unload data

```shell
dsbulk unload -url movies_and_tv.csv -k catalog -t movies_and_tv -b "~/Downloads/secure-connect-dataflix.zip" -u <client_id> -p <client_secret>
```



## CQLSH

Side node, you can connect with CQLSH from your local to Astra using bundle and credentials.

```shell
cqlsh -b "~/Downloads/secure-connect-dataflix.zip" -u <client_id> -p <client_secret>
```