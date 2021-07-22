# Building

##### Open IntelliJ IDEA -> Import project -> select "calc" folder -> Ok

##### Now pick "Import project from external model" and select Maven -> Finish

##### In the class CalcInfoDAOImp replace the Connection String (CONN) with your path.

for example:

```java
private static final String CONN = "jdbc:sqlite:C:/.../(folder that contains the project)/alkfejl-calc/calc-core/calc.db"
```

CalcInfoDAOImp is located in calc\calc-core\src\main\java\hu\alkfejl\DAO

##### In the Maven Tool Window on the right hand side:

- mvn clean install
- compile the calc-core module (calc-core->Plugins->compiler->compiler:compile)
- compile the calc_fx module (calc-fx->Plugins->compiler->compiler:compile)
- compile the calc_fx module (calc-fx->Plugins->javafx->javafx:compile)
- run the calc-fx module (calc-fx->Plugins->javafx->javafx:run)