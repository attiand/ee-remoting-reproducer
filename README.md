# ee-remoting-reproducer

## build

```bash
./mvnw clean install
```

## deploy

```bash
cp test-ear/target/test-ear-0.0.1-SNAPSHOT.ear ../tools/jboss-eap-8.0.5/standalone/deployments
cp test-war/target/test-war-0.0.1-SNAPSHOT.war ../tools/jboss-eap-8.0.5/standalone/deployments
```

## test 1 (with class)

Restarted server

```bash
# works
curl -v -X PUT http://localhost:8080/test-war-0.0.1-SNAPSHOT/class
# works
curl -v -X GET http://localhost:8080/test-war-0.0.1-SNAPSHOT/class
```

## test 2 (with record)

Restarted server

```bash
# works
curl -v -X GET http://localhost:8080/test-war-0.0.1-SNAPSHOT/record
# fails
curl -v -X PUT http://localhost:8080/test-war-0.0.1-SNAPSHOT/record 
# works
curl -v -X GET http://localhost:8080/test-war-0.0.1-SNAPSHOT/record 
```

## test 3 (with record, other order)

Restarted server

```bash
# works
curl -v -X PUT http://localhost:8080/test-war-0.0.1-SNAPSHOT/record
# fails
curl -v -X GET http://localhost:8080/test-war-0.0.1-SNAPSHOT/record 
# works
curl -v -X PUT http://localhost:8080/test-war-0.0.1-SNAPSHOT/record 
```

## Stacktrace

```
Caused by: java.lang.IllegalAccessException: no such method: api.RecordGreeting.message()Message/invokeVirtual
	at java.base/java.lang.invoke.MemberName.makeAccessException(MemberName.java:911)
	at java.base/java.lang.invoke.MemberName$Factory.resolveOrFail(MemberName.java:994)
	at java.base/java.lang.invoke.MethodHandles$Lookup.resolveOrFail(MethodHandles.java:3750)
	at java.base/java.lang.invoke.MethodHandles$Lookup.findVirtual(MethodHandles.java:2767)
	at org.jboss.marshalling@2.1.6.Final-redhat-00001//org.jboss.marshalling.reflect.JDKSpecific.getRecordComponentValue(JDKSpecific.java:85)
	... 97 more
Caused by: java.lang.LinkageError: loader constraint violation: when resolving method 'api.Message api.RecordGreeting.message()' the class loader 'org.jboss.marshalling@2.1.6.Final-redhat-00001' @48ae9b55 of the current class, org/jboss/marshalling/reflect/JDKSpecific, and the class loader 'deployment.test-war-0.0.1-SNAPSHOT.war' @d9d7f67 for the method's defining class, api/RecordGreeting, have different Class objects for the type api/Message used in the signature (org.jboss.marshalling.reflect.JDKSpecific is in unnamed module of loader 'org.jboss.marshalling@2.1.6.Final-redhat-00001' @48ae9b55, parent loader 'app'; api.RecordGreeting is in unnamed module of loader 'deployment.test-war-0.0.1-SNAPSHOT.war' @d9d7f67, parent loader 'app')
	at java.base/java.lang.invoke.MethodHandleNatives.resolve(Native Method)
	at java.base/java.lang.invoke.MemberName$Factory.resolve(MemberName.java:962)
	at java.base/java.lang.invoke.MemberName$Factory.resolveOrFail(MemberName.java:991)
	... 100 more
```