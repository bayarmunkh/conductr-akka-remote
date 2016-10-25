# conductr-akka-remote

`conduct` version is 0.39.

To run locally:

```
cd example-2
activator -Dhttp.port=9000 run
```

```
cd example-1
activator -Dhttp.port=9001 run
```

With a successful run, something like the following should appear in console log.

`
Received from remote actor: ActorIdentity(akka.tcp://application@localhost:2560/user/dumb-counter,Some(Actor[akka.tcp://application@localhost:2560/user/dumb-counter#322795395]))`


with unsuccessful run, a `akka.pattern.AskTimeoutException` exception should appear in console log.
