# Querydsl JPA Tutorial

Examples showing how to create complex queries for JPA or MongoDB using [Querydsl].

## Java Persistence API

Run the JPA examples using the following command:

    mvn package && cd jpa-example && mvn -Dexec.mainClass=com.hascode.app.JPAExamples exec:java 

This should produce a similar output:

    Book [id=2, title=The big book of something, author=Author [id=1, name=Some Guy], published=Fri Dec 31 22:43:42 CET 2010,tags=[Tag [name=Horror], Tag [name=Drama], Tag [name=Science]]]
    Book [id=2, title=The big book of something, author=Author [id=1, name=Some Guy], published=Fri Dec 31 22:43:42 CET 2010, tags=[Tag [name=Horror], Tag [name=Drama], Tag [name=Science]]]
    Book [id=2, title=The big book of something, author=Author [id=1, name=Some Guy], published=Fri Dec 31 22:43:42 CET 2010, tags=[Tag [name=Horror], Tag [name=Drama], Tag [name=Science]]]


## MongoDB

_to be done_


Please feel free to have a look at my blog at [www.hascode.com] for sources and additional information.

----

**2014 Micha Kops / hasCode.com**

   [Querydsl]:http://www.querydsl.com/
   [www.hascode.com]:http://www.hascode.com/