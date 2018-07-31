# Green DAO : Android ORM
Saving data is a requirement of any application. In Android, we can save data mainly in three ways.
   1. SQLite
   2. SharedPreferences
   3. File System
   
The easy way to save data is by using the SQLite database and working through `SqliteOpenHelper`.
 
We need to write raw queries and manipulation through `cursors`.

It becomes difficult to manage when the code base becomes large and manual errors are more possible.   

#### So how do we solve our problems?

The solution to this is using `Data Access Objects` or DAOs.

## And what is DAO or greenDao exactly :confused:?

It is an open source Android ORM `(Object-Relational Mapping)` making development for SQLite databases easy

## So the question is how do we start  ?

>Here is the app build using [greenDAO](http://greenrobot.org/greendao/)step by step for you to understand.


### Here is the list of thing to achieve our app.


- [ ] Add Gradle dependency in app/build.gradle.
- [ ] Add  Gradle plugin for the Annotation processing in the project’s build.gradle.
- [ ] GreenDAO requires us to create the schema of the table in the form of a class
- [ ] Explore the DAO generated classes
- [ ] Construct a class to communicate with the database
- [ ] Test the table in the Main activity
- [ ] specify the schema version of the database


#### So let's start

- [x] Add Gradle dependency in app/build.gradle.

```gradle
greenDao = '3.1.1'
implementation "org.greenrobot:greendao:$rootProject.greenDao"
```


- [x] Add  Gradle plugin for the Annotation processing in the project’s build.gradle.

```gradle
classpath ‘org.greenrobot:greendao-gradle-plugin:3.2.1’
```

>Then use this plugin in the app/build.gradle, below com.android.application plugin

```gradle
apply plugin: ‘org.greenrobot.greendao’
```

- [x] GreenDAO requires us to create the schema of the table in the form of a class

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

###### But what this annotation **@Entity**,**@Id** and  **@Property** mean?

1. [@Entity](http://greenrobot.org/greendao/documentation/modelling-entities/)
   - It turns the Java class User into a database-backed entity. This will also instruct greenDAO to generate the necessary code.
2. [@Id](http://greenrobot.org/greendao/documentation/modelling-entities/)
   - It selects a long/ Long property as the entity ID. In database terms, it’s the primary key. The parameter autoincrement is a flag to make the ID value ever increasing (not reusing old values).
3. [@Property](http://greenrobot.org/greendao/documentation/modelling-entities/)
   - It lets you define a non-default column name, which the property is mapped to. If absent, greenDAO will use the field name in a SQL-ish fashion.


- [x] Explore the DAO generated classes

```Java
public class BaseRepo {

    private static BaseRepo instance = null;
    protected DaoSession daoSession;

    public BaseRepo() {
        daoSession = DAOSessionHolder.getInstance().getDaoSession();
        if (daoSession == null) {
            MySQLiteOpenHelper helper = new MySQLiteOpenHelper(Application.getContext(), "greenDaoDemo.db");
            Database db = helper.getWritableDb();
            daoSession = new DaoMaster(db).newSession();
            DAOSessionHolder.getInstance().putDaoSession(daoSession);
        }
    }

    public static BaseRepo getInstance() {
        if (instance == null) {
            instance = new BaseRepo();
        }
        return instance;
    }
}
```

1. DaoMaster: This class defines the methods for database creation and manipulation.
2. DaoSession: This class provides the DAO classes for accessing the database tables.
3. MySQLiteOpenHelper class
```Java
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.migrate(db, DaoHelper.getAllDaos());
    }
}
```

- [x] Construct a class to communicate with the database

```Java
public class UserOperations extends BaseRepo {

    private static UserOperations instance = null;
    private UserEntityDao dao;

    private UserOperations(Context context) {
        super();
        dao = daoSession.getUserEntityDao();
    }

    public static UserOperations getInstance(Context context) {
        if (instance == null) {
            instance = new UserOperations(context);
        }
        return instance;
    }
    /** 
     * @return list of user entity from the table name UserEntity in the database
     */
    public List<UserEntity> getUserEntity() {
        return dao.queryBuilder()
                .list();
    }
}
```

- [x] Test the table in the Main activity

```Java
public class MainActivity  {

    private Context context;
    private UserOperations userOperations;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        userOperations = UserOperations.getInstance(context);
        
        //Here we get the list of record from the table name UserEntity in the database
        List<UserEntity> userEntityList=userOperations.getUserEntityList();

        }
}
```

- [x] specify the schema version of the database

You will need to specify the schema version of the database so that you can get the old version and new version when the app is upgraded. To specify this add greendao schema in the app/build.gradle.

```gradle
greendao {
android {
	...
}
    schemaVersion 1
    daoPackage "com.example.greendaodemo.database.base"
}
```
## So here is your app ready :smiley:with greenDao database
<p >
  <img src="/assets/Screen_1.png" alt="Screenshots"  height="500" width="290"/>
  <img src="/assets/Screen_2.png" alt="Screenshots"  height="500" width="290"/>
  <img src="/assets/Screen_3.png" alt="Screenshots"  height="500" width="280"/>
</p>

# Download

<a href="https://github.com/The-Devendra/Green-Dao-Demo/blob/master/apk/app-debug.apk">Click here to download!</a>
### Libraries Used
* [Support Library](https://developer.android.com/topic/libraries/support-library/index.html)
* [Green Dao](http://greenrobot.org/greendao/)
* [Stetho](http://facebook.github.io/stetho/)
* [TextDrawable](https://github.com/amulyakhare/TextDrawable)
* [Markdown](https://guides.github.com/features/mastering-markdown/)
## License
```
Copyright 2018 Devendra Mehra

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
