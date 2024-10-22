# ee-remoting-reproducer

## build

```bash
./mvnw clean install
```

## deploy

```bash
cp test-ear/target/test-ear-0.0.1-SNAPSHOT.ear ../tools/jboss-eap-8.0/standalone/deployments
cp test-war/target/test-war-0.0.1-SNAPSHOT.war ../tools/jboss-eap-8.0/standalone/deployments
```

## test

### Works

```bash
curl -v http://localhost:8080/test-war-0.0.1-SNAPSHOT/class
```

### Fails

```bash
curl -v http://localhost:8080/test-war-0.0.1-SNAPSHOT/record
```

fails with the following error, full log available in [server.log](server.log).
```
Caused by: java.lang.LinkageError: loader constraint violation: when resolving method 'void api.RecordGreeting.<init>(api.Message)' the class loader 'org.jboss.marshalling@2.1.3.SP1-redhat-00001' @6b19b79 of the current class, org/jboss/marshalling/reflect/JDKSpecific, and the class loader 'deployment.test-ear-0.0.1-SNAPSHOT.ear.test-api-0.0.1-SNAPSHOT.jar' @1e6d78e6 for the method's defining class, api/RecordGreeting, have different Class objects for the type api/Message used in the signature (org.jboss.marshalling.reflect.JDKSpecific is in unnamed module of loader 'org.jboss.marshalling@2.1.3.SP1-redhat-00001' @6b19b79, parent loader 'app'; api.RecordGreeting is in unnamed module of loader 'deployment.test-ear-0.0.1-SNAPSHOT.ear.test-api-0.0.1-SNAPSHOT.jar' @1e6d78e6, parent loader 'app')
```