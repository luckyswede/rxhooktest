```
gradle run
curl localhost:8080/rx1
```
will hang...

Comment out `compile "io.micronaut:micronaut-tracing"` in `build.gradle` and rerun...works :)
