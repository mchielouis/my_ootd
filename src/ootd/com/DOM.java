/*
DOM (database object metadata) utility class for constructing sql statements for 
creating tables in sqlite.
 */

package ootd.com;
import java.util.*;
/**
 *
 * @author Michelle
 */
public class DOM {
    private LinkedHashMap<String,String> tableMetadata = new LinkedHashMap<String,String>();
    String _table_name;
    String _id;
    String _object_name;
    String _foreign_object_name;
    
    String[] colsMetaData;
    
    public DOM(String table, String id, String object) {
        colsMetaData = new String[2];
        //set table name and object name
        this._table_name = table;
        this._id = id;
        this._object_name = object;
        
        //put table creation phrases with appropriate table name and object name
        tableMetadata.put(_id, " integer primary key autoincrement, ");
        tableMetadata.put(_object_name, " text not null");
        
        //put column names into array
        colsMetaData[0] = _id;
        colsMetaData[1] =_object_name;
    }
    public DOM(String table, String id, String object, String foreign_object, String foreign_object_table) {
        colsMetaData = new String[3];
        
        this._table_name = table;
        this._id = id;
        this._object_name = object;
        this._foreign_object_name = foreign_object;
        
        tableMetadata.put(_id, " integer primary key autoincrement, ");
        tableMetadata.put(_object_name, " text not null, "); 
        tableMetadata.put(" foreign key (" +_foreign_object_name +")"," references "+foreign_object_table+"("+_foreign_object_name+")");
        
        colsMetaData[0] = _id;
        colsMetaData[1] =_object_name;
        colsMetaData[2] = _foreign_object_name;
    }
    public String[] getCols() {
        return colsMetaData;
    }
    
    public LinkedHashMap<String,String> getTable() {
        return tableMetadata;
    }
    
    public String getTableName() {
        return _table_name;
    }
}

