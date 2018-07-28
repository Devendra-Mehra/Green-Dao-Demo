# Green DAO : Android ORM
Saving data is requirement of any application. In Android, we can save data mainly by three ways.
   * SQLite
   * SharedPreferences
   * File System
   
The easy way to save data is by using the SQLite database and working through `SqliteOpenHelper`.
 
We need to write raw queries and manipulation through `cursors`. 

It becomes difficult to manage when code base becomes large and manual errors are more possibe.   
##So how do we solve our problems?
The solution to this is using `Data Access Objects` or DAOs.
##And what is greenDao exactly?
It is an open source Android ORM `(Object-Relational Mapping)` making development for SQLite databases easy
##So the question is how do we start?