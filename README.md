# Querydsl Tutorial

Examples showing how to create complex queries for JPA or MongoDB using [Querydsl].

## Java Persistence API

Run the JPA examples using the following command:

    mvn package && cd jpa-example && mvn -Dexec.mainClass=com.hascode.app.JPAExamples exec:java

This should produce a similar output:

    Using Querydsl:				Book [id=2, title=The big book of something, author=Author [id=1, name=Some Guy], published=Fri Dec 31 16:55:00 CET 2010, tags=[Tag [name=Horror], Tag [name=Drama], Tag [name=Science]]]
    Using JPA Criteria API:		Book [id=2, title=The big book of something, author=Author [id=1, name=Some Guy], published=Fri Dec 31 16:55:00 CET 2010, tags=[Tag [name=Horror], Tag [name=Drama], Tag [name=Science]]]
    Using JPA Query Language:	Book [id=2, title=The big book of something, author=Author [id=1, name=Some Guy], published=Fri Dec 31 16:55:00 CET 2010, tags=[Tag [name=Horror], Tag [name=Drama], Tag [name=Science]]]


## MongoDB with Morphia

Run the MongoDB examples using the following command:

    mvn package && cd mongodb-example && mvn -Dexec.mainClass=com.hascode.app.MongoDbExamples exec:java

This should produce a similar output (besides the environment's startup output):

    Book [id=52fa7cf10fcee8e5948a548b, title=The big book of something, author=Author [id=52fa7cf10fcee8e5948a5487, name=Some guy], published=Fri Dec 31 20:41:37 CET 2010, tags=[Tag [id=52fa7cf10fcee8e5948a5488, name=Horror], Tag [id=52fa7cf10fcee8e5948a5489, name=Drama], Tag [id=52fa7cf10fcee8e5948a548a, name=Science]]]


## Lucene

Run the Lucene examples using the following command:

    mvn package && cd lucene-example && mvn -Dexec.mainClass=com.hascode.app.LuceneExamples exec:java

This should produce a similar output:

    4 books stored in index
    Searching a Lucene Index with Querydsl
    generated lucene query: +(+(+author:"some guy" +published:{* TO 2011-02-13}) +price:[10.0 TO 30.0]) +title:*something
    2 books found
    Document<stored,indexed,tokenized<title:The big book of something> stored,indexed,tokenized<author:Some Guy> stored,indexed,tokenized,omitNorms,indexOptions=DOCS_ONLY<published:2010-01-01> stored<price:20.5>>
    Document<stored,indexed,tokenized<title:Anything, onething, something> stored,indexed,tokenized<author:Some Guy> stored,indexed,tokenized,omitNorms,indexOptions=DOCS_ONLY<published:2010-01-01> stored<price:29.99>>


Please feel free to have a look at my blog at [www.hascode.com] for sources and additional information.

----

**2014 Micha Kops / hasCode.com**

   [Querydsl]:http://www.querydsl.com/
   [www.hascode.com]:http://www.hascode.com/