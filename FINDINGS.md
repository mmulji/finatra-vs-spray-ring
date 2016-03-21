# Project Contents

The following mini-applications are contained herein:

## Hello World (Scala/Finatra)

See src/main/scala/finatra/one/HelloWorld.scala
Finatra example webapp that starts admin console and responds with Hello World.
```sh
⇒  curl localhost:8888
Hello World%
```

Admin console is available on:
```sh
Serving admin http on 0.0.0.0/0.0.0.0:9990
```

## IoTServer (Scala/Finatra)
See src/main/scala/finatra/two/IoTServer.scala

```sh
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

## API

See src/main/scala/domain/domain.scala

## Routes

resource
```
/env
```
get

```json
{"from" :  "", "until" : ""}
```

post
```json
{
  "timestamp" : "20121001-12:32:45"
  "temperature": 30.07,
  "mintemp": 24.34,
  "maxtemp": 30.5,
  "humidity": 43,
  "pressure": 758,
  "light": 93,
  "your_latitude": 51.004932,
  "your_longitude": 5.849955
}
```


