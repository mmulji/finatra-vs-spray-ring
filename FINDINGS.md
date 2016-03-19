# Project Contents

The following mini-applications are contained herein:

## Hello World (Scala/Finatra)

See src/main/scala/finatra/one/HelloWorld.scala
Finatra example webapp that starts admin console and responds with Hello World.

```
⇒  curl localhost:8888
Hello World%
```

Admin console is available on:
```
Serving admin http on 0.0.0.0/0.0.0.0:9990
```

## IoTServer (Scala/Finatra)
See src/main/scala/finatra/two/IoTServer.scala

```
⇒  curl localhost:8888/ping
pong

⇒  curl localhost:8888/name
Bob
```

```
⇒  curl -X POST localhost:8888/foo 
bar
```

## IoTServer (Scala/Spray)
See todo.

## RingServer (Clojure/Ring)
See todo.
