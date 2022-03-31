# HOW TO BUILD SAT4J FROM SOURCE

> Nighlty builds are available [here](https://gitlab.ow2.org/sat4j/sat4j/pipelines)

## Using Maven (library users)

Just launch 

```shell
$ mvn -DskipTests=true install
```

to build the SAT4J modules from the source tree.

All the dependencies will be gathered by Maven.


## Using ant (solvers users)

Just type:

```shell
$ ant [core,pseudo,maxsat,sat]
```

to build the solvers from source.

The solvers will be available in the directory `dist/CUSTOM`.

You may want to use a custom release name.

```shell
$ ant -Drelease=MINE maxsat
```

In that case, the solvers will be available in the directory `dist/MINE`.

Type

```shell
$ ant -p
```

to see available options.
