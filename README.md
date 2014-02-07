# Querydsl Tutorial

Examples showing how to create complex queries for JPA or MongoDB using [Querydsl].

## Java Persistence API

Run the JPA examples using the following command:

    mvn package && cd jpa-example && mvn -Dexec.mainClass=com.hascode.app.JPAExamples exec:java

This should produce a similar output:

    Using Querydsl:			Book [id=2, title=The big book of something, author=Author [id=1, name=Some Guy], published=Fri Dec 31 16:55:00 CET 2010, tags=[Tag [name=Horror], Tag [name=Drama], Tag [name=Science]]]
    Using JPA Criteria API:		Book [id=2, title=The big book of something, author=Author [id=1, name=Some Guy], published=Fri Dec 31 16:55:00 CET 2010, tags=[Tag [name=Horror], Tag [name=Drama], Tag [name=Science]]]
    Using JPA Query Language:	Book [id=2, title=The big book of something, author=Author [id=1, name=Some Guy], published=Fri Dec 31 16:55:00 CET 2010, tags=[Tag [name=Horror], Tag [name=Drama], Tag [name=Science]]]


## MongoDB with Morphia

Run the MongoDB examples using the following command:

    mvn package && cd mongodb-example && mvn -Dexec.mainClass=com.hascode.app.MongoDbExamples exec:java


## Lucene

Run the Lucene examples using the following command:

    mvn package && cd lucene-example && mvn -Dexec.mainClass=com.hascode.app.LuceneExamples exec:java


Please feel free to have a look at my blog at [www.hascode.com] for sources and additional information.

----

**2014 Micha Kops / hasCode.com**

   [Querydsl]:http://www.querydsl.com/
   [www.hascode.com]:http://www.hascode.com/