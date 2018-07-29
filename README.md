# Green DAO : Android ORM
Saving data is a requirement of any application. In Android, we can save data mainly in three ways.
   1. SQLite
   2. SharedPreferences
   3. File System
   
The easy way to save data is by using the SQLite database and working through `SqliteOpenHelper`.
 
We need to write raw queries and manipulation through `cursors`.

It becomes difficult to manage when the code base becomes large and manual errors are more possible.   

##So how do we solve our problems?

The solution to this is using `Data Access Objects` or DAOs.

##And what is DAO or greenDao exactly?

It is an open source Android ORM `(Object-Relational Mapping)` making development for SQLite databases easy

##So the question is how do we start  ?

>Here is the app build using[greenDAO](http://greenrobot.org/greendao/)step by step for you to understand.


###Here is the list of thing to achieve our app.


- [ ] Add Gradle dependency in app/build.gradle.
- [ ] Add  Gradle plugin for the Annotation processing in the project’s build.gradle.
- [ ] GreenDAO requires us to create the schema of the table in the form of a class
- [ ] Construct the DaoSession Object in AndroidManifest
- [ ] Test the table in the main activity


#####So let's start

- [ ] Add Gradle dependency in app/build.gradle.

```
greenDao = '3.1.1'
implementation "org.greenrobot:greendao:$rootProject.greenDao"
```

- [ ] Add  Gradle plugin for the Annotation processing in the project’s build.gradle.

```
classpath ‘org.greenrobot:greendao-gradle-plugin:3.2.1’
```

>Then use this plugin in the app/build.gradle, below com.android.application plugin

```
apply plugin: ‘org.greenrobot.greendao’
```
- [ ] GreenDAO requires us to create the schema of the table in the form of a class

```Java
@Entity(nameInDb = "user_entity")
public class UserEntity {
    
    @Id(autoincrement = true)

    @Property(nameInDb = "user_id")
    private Long userId;
    
    @Property(nameInDb = "user_name")
    private String userName;
    
    @Property(nameInDb = "user_state")
    private String userState;
}
```

When you build the project, it will generate getter, setters, and constructors for this class.
######But what this annotation **@Entity**,**@Id** and  **@Property** mean

1. [@Entity](http://greenrobot.org/greendao/documentation/modelling-entities/)
   - It turns the Java class User into a database-backed entity. This will also instruct greenDAO to generate the necessary code.
2. [@Id](http://greenrobot.org/greendao/documentation/modelling-entities/)
   - It selects a long/ Long property as the entity ID. In database terms, it’s the primary key. The parameter autoincrement is a flag to make the ID value ever increasing (not reusing old values).
3. [@Property](http://greenrobot.org/greendao/documentation/modelling-entities/)
   - It lets you define a non-default column name, which the property is mapped to. If absent, greenDAO will use the field name in a SQL-ish fashion.
