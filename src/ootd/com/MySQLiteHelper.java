/*
SQLiteOpenHelper extension creates onboard database and creates tables using DOM class.

 */
package ootd.com;
import java.util.*;
import android.util.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//class for handling db connection, instantiated via getInstance(); private constructor


/**
 *
 * @author root
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    //database constants
    public static final String dbname="my_ootd.db";
    public static final int dbversion=1;
    private static MySQLiteHelper dbinstance = null;

    //getInstance function calls constructor if no instance exists	
    public static MySQLiteHelper getInstance(Context context) {
            if (dbinstance == null) {
                    dbinstance = new MySQLiteHelper(context.getApplicationContext());
            }
            Log.d("UserDAO","Instantiating MySQLiteOpenHelper="+dbinstance);
            return dbinstance;
    }

    //constructor	
    public MySQLiteHelper(Context context) {
            super(context,dbname,null,dbversion);
    }

    /**********Table Definition:**********/

    //user table 
    public static DOM user = new DOM(" user", " user_ID", " username");

    
    //type table
    public static DOM type = new DOM(" type", " type_ID", " type_name");

    
    //color table
    public static DOM color = new DOM(" color", " color_ID", " color_name");
    
    /*public static final HashMap<String,String> color = new HashMap<String,String>();
    public static final String _color_table=" color";
    public static final String _colorID=" colorID";
    public static final String _color_name=" color_name";
    public static final String[] color_cols = {_colorID,_color_name};
    static
    {
    color.put(_colorID, " integer primary key autoincrement, ");
    color.put(_color_name, " text not null");
    }*/
//    //garment has colors table
//    public static final HashMap<String,String> garment_has_colors = new HashMap<String,String>();
//    public static final String _gcolor_num="gcolor_num";
//    public static final String _ghc_table="garment_has_colors";
//    public static final String[] ghc_cols = {_gcolor_num,_colorID,_garmentID};
//    static
//    {
//    garment_has_colors.put(_gcolor_num, "integer primary key autoincrement");
//    garment_has_colors.put(_colorID,"integer not null");
//    garment_has_colors.put(_garmentID, "integer not null");
//    garment_has_colors.put("foreign key("+_garmentID+")", "references garment("+_garmentID+")");
//    garment_has_colors.put("foreign key("+_colorID+")", "references color("+_colorID+")");
//    }
    //pattern table
    public static DOM pattern = new DOM(" pattern", " pattern_ID", " pattern_name");
    
    /*public static final HashMap<String,String> pattern = new HashMap<String,String>();
    public static final String _pattern_table=" pattern";
    public static final String _patternID=" patternID";
    public static final String _pattern_name=" pattern_name";
    public static final String[] pattern_cols = {_patternID,_pattern_name};
    static
    {
            pattern.put(_patternID," integer primary key autoincrement, ");
            pattern.put(_pattern_name, " text not null");
    }
//    //garment has patterns table
//    public static final HashMap<String,String> garment_has_patterns = new HashMap<String,String>();
//    public static final String _ghp_table="garment_has_patterns";
//    public static final String _gpattern_num="gpattern_num";
//    public static final String[] ghp_cols = {_gpattern_num,_patternID,_garmentID};
//    static
//    {
//    garment_has_patterns.put(_gpattern_num, "integer primary key autoincrement");
//    garment_has_patterns.put(_patternID, "integer not null");
//    garment_has_patterns.put(_garmentID, "integer not null");
//    garment_has_patterns.put("foreign key("+_garmentID+")", "references garment("+_garmentID+")");
//    garment_has_patterns.put("foreign key("+_patternID+")", "references garment("+_patternID+")");
//    }*/
    
      //materials table
      public static DOM material = new DOM(" material", " material_ID", " material_name");
      /*public static final HashMap<String,String> material = new HashMap<String,String>();
      public static final String _material_table=" material";
      public static final String _materialID=" materialID";
      public static final String _material_name=" material_name";
      public static final String[] material_cols = {_materialID,_material_name};
      static
      {
          material.put(_materialID," integer primary key autoincrement, ");
          material.put(_material_name," text not null");
      }*/
      
//    //Outfit table
//    public static final HashMap<String,String> outfit = new HashMap<String,String>();
//    public static final String _outfit_table="outfit";
//    public static final String _outfitID="outfitID";
//    public static final String _outfit_name="outfit_name";
//    public static final String[] outfit_cols = {_outfitID,_outfit_name};
//    static
//    {
//            outfit.put(_outfitID, "integer primary key autoincrement,");
//            outfit.put(_outfit_name, "text not null");
//    }
//    //Closet table
//    public static final HashMap<String,String> closet = new HashMap<String,String>();
//    public static final String _closet_table="closet";
//    public static final String _closetID="closetID";
//    public static final String _closet_name="closet_name";
//    public static final String[] closet_cols = {_closetID,_closet_name};
//    static
//    {
//            closet.put(_closetID, "integer primary key autoincrement,");
//            closet.put(_closet_name, "text not null,");
//            closet.put(_userID, "integer,");
//            closet.put("foreign key("+_userID+")", "references user("+_userID+")");
//    }
//
//    //Garment table
//    public static final HashMap<String,String> garment = new HashMap<String,String>();
//    public static final String _garment_table="garment";
//    public static final String _garmentID="garmentID";
//    public static final String _description="description";
//    public static final String _type="type";
//    public static final String _size="size";
//    public static final String _brand="brand";
//    public static final String _image_path="image_path";
//    public static final String[] garment_cols = {_garmentID,_description,_type,_size,_brand,_image_path};
//    static 
//    {
//            garment.put(_garmentID, "integer primary key autoincrement,");
//            garment.put(_description, "text not null");
//            garment.put(_type, "text not null");
//            garment.put(_size,"integer");
//            garment.put(_brand, "text not null");
//            garment.put(_image_path, "text not null");
//    }
//    //closet has garments table
//    public static final HashMap<String,String> closet_has_garments = new HashMap<String,String>();
//    public static final String _chg_table="closet_has_garments";
//    public static final String _cgarment_num="cgarmnet_num";
//    static
//    {
//    closet_has_garments.put(_cgarment_num,"integer primary key autoincrement");
//    closet_has_garments.put(_garmentID, "integer not null");
//    closet_has_garments.put(_closetID, "integer not null");
//    closet_has_garments.put("foreign key ("+_garmentID+")","references garment("+_garmentID+")");
//    closet_has_garments.put("foreign key ("+_closetID+")","references closet("+_closetID+")");
//    }
//
    
    




    /**********Functions**********/

    //create "create table" statement
    public String createTableSQL(LinkedHashMap<String,String> col,String table){
            String table_create ="create table" +table+"(";
            for (String key : col.keySet()) {
                    table_create+=key+col.get(key);
            }
            table_create= table_create + ");";
            Log.d("UserDAO","Creating table: "+table_create);
            return table_create;
    }



    //create all tables, only run if db does not currently exist
    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d("UserDAO","Executing create table statement");
            database.execSQL(createTableSQL(user.getTable(),user.getTableName()));
            database.execSQL(createTableSQL(type.getTable(),type.getTableName()));
            database.execSQL(createTableSQL(color.getTable(),color.getTableName()));
            database.execSQL(createTableSQL(pattern.getTable(),pattern.getTableName()));
            database.execSQL(createTableSQL(material.getTable(),material.getTableName()));
            //database.execSQL(createTableSQL(outfit,"outfit"));
            //database.execSQL(createTableSQL(closet,"closet"));
            // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub

    }

    public void onConfigure(SQLiteDatabase database) {
            database.setForeignKeyConstraintsEnabled(true);
    }   
}
