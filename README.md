my_ootd
=======

An Android App for keeping track of garments. A user shall be able to create entries for garments and to associate them via closet and outfit relationships. Garments shall be identified in five main ways; by garment type--either a preconstructed type or a user defined type--by a set of colors, a set of patterns, a set of materials and by placement (eg torso, legs, full body etc).   Additionally, garments  have an associated image.  Outfits may be a set of user selected garments and accessories, or they may be generated according to user defined criteria. 
Garments and associated data, as well as closets and outfits shall be stored in an onboard SQlite database.

Database design
===============

Outfit:
-------
This table is a collection of <outfitid, outfitname> pairs, which represent an entry in a record of distinct outfits.   
* Primary Key (OutfitID): integer NOT NULL Auto_Increment  
primary key OutfitID is auto generated by SQLite database and incremented as user adds new outfits to the database; this is what associates the outfit with a collection of garments/accessories  
* Outfit_name: varchar(255) NOT NULL  
String identification for outfit, created by user upon outfit creation  


Outfit_has_garment:
------------------
This table is a collection of <entrynum, outfitid, garmentid> tuples, which represent an entry in a record of distinct outfit, garmentid pairs. The entrynum primary key allows a particular outfit to associate with multiple garments.  
* Garment_num (Primary Key): integer NOT NULL Auto_Increment  
Integer identification of distinct outfit,garment entry  
* OutfitID (Foreign Key): integer  
references Outfit.OutfitID  
represents the outfit in the “outfit_has_garment” relationship  
* GarmentID (Foreign Key): integer  
references Garment.GarmentID  
represents the garment in the “outfit_has_garment” relationship  

Closet:
------
This table is a collection of <closetid, userid, closetname> tuples, which represent an entry in a record of distinct closets. A single closet can only be associated with one user.  
- ClosetID (Primary Key): integer NOT NULL Auto_Increment  
integer identification of distinct closet  
- Closet_name: varchar(255) NOT NULL  
string identification of closet  
- UserID (Foreign Key): integer  
references User.UserID  
represents user who created the closet  

Closet_has_outfit:
-----------------
This table is a collection of <entrynum, closetid, outfitid> tuples, which represent an entry in a record of distinct closetid,outfitid pairs. The entrynum primary key allows a particular closet to associate with multiple outfits.  
- Outfit_num (Primary Key):  integer Auto_Increment	 
Integer identification of distinct closet,outfit entry   
- OutfitID (Foreign Key):  
references Outfit.OutfitID  
represents the outfit in the “closet_has_outfit” relationship  
- ClosetID (Foreign Key):  
references Closet.ClosetID  
references the closet in the “closet_has_outfit” relationship  

Closet_has_garments:
-------------------
This table is a collection of <entrynum, closetid, garmentid> tuples, which represent an entry in a record of distinct closetid, garmentid pairs. The entrynum primary key allows a particular closet to associate with multiple garments.  
- Garment_num (Primary Key): integer Auto_Increment  
Integer identification of distinct closet, garment entry  
- GarmentID (Foreign Key):  
references Garment.GarmentID  
represents the garment in the “closet_has_garments” relationship  
- ClosetID (Foreign Key):  
references Closet.ClosetID  
represents closet in the “closet_has_garments” relationship  

Garment:
--------
This table is a collection of <garmentid,placmentid,image> tuples, which represent an entry in a record of distinct garments.  
- GarmentID (Primary Key): integer NOT NULL Auto_Increment  
integer identification of distinct garment  
- PlacmentID (Foreign Key): varchar(255) NOT NULL  
references Placement.PlacementID  
represents the user selected placement option for this garment  
- Imagepath: varchar(255)  
string path to image file chosen by user upon creation  

Type:
-----
This table is a collection of <typeid, typename> pairs which represent an entry in a record of distinct garmentypes.  
- TypeID (Primary Key): integer NOT NULL Auto_Increment  
integer identification of distinct garment type  
- Typename: varchar(255)  
string name of garment type  

Garment_has_Type:
-----------------
This table is a collection of <typenum, typeid, garmentid> pairs which represent an entry in a record of distinct typeid,garmentid pairs. The typenum primary key allows a particular garment to associate with multiple types.  
- Type_num (Primary Key): integer NOT NULL Auto_Increment  
integer identification of distinct typeid, garmentid pair  
- TypeID (Foreign Key): integer  
references Type.TypeID  
represents type in the “garment_has_type” relationship  
- GarmentId (Foreign Key): integer  
references Garment.GarmentID  
represents garment in “garment_has_type” relationship  

Color:
------
This table is a collection of <colorid, colorname> pairs which represent an entry in a record of distinct colors.  
- ColorID (Primary Key): varchar(255) NOT NULL Auto_Increment  
integer identification of distinct color  
- Color_name: varchar(255) NOT NULL  
string identification of distinct color  

Garment_has_Color:
------------------
This table is a collection of <colornum, colorid, garmentid> pairs which represent an entry in a record of distinct colorid,garmentid pairs.  The colornum primary key allows a particular garment to associate with multiple colors.  
- Color_num (Primary Key): integer NOT NULL Auto_Increment  
integer identification of distinct colorid, garmentid pair  
- ColorID (Foreign Key): integer  
references Color.ColorID  
represents color in the “garment_has_color” relationship  
- GarmentID (Foreign Key): integer  
references Garment.GarmentID  
represents garment in “garment_has_color” relationship  

Pattern:
--------
This table is a collection of <patternid, patternname> pairs which represent an entry in a record of distinct patterns.  
- PatternID (Primary Key): varchar(255) NOT NULL Auto_Increment  
integer identification of distinct pattern  
- Pattern_name: varchar(255) NOT NULL  
string identification of distinct pattern  

Garment_has_Pattern:
-------------------
This table is a collection of <patternum, patternid, garmentid> tuples which represent an entry in a record of distinct patternid, garmentid pairs. The patternum primary key allows a particular garment to associate with multiple patterns.  
- Pattern_num (Primary Key): integer NOT NULL Auto_Increment  
integer identification of distinct patternid, garmentid pair  
- PatternID (Foreign Key): integer  
references Pattern.PatternID  
represents the pattern in the “garment_has_pattern” relationship  
- GarmentID (Foreign Key): integer  
references Garment.GarmentID  

Material  
This table is a collection of <materialid, materialname> pairs which represent an entry in a record of distinct materials.  
- MaterialID (Primary Key): varchar(255) NOT NULL Auto_Increment  
integer identification of distinct material  
- Material_name: varchar(255) NOT NULL  
string indentification of distinct material  

Garment_has_Material:
--------------------
This table is a collection of <materialnum,materialid, garmentid> tuples which represent an entry in a record of distinct materialid, garmentid pairs. The materialnum primary key allows a particular garment to associate with multiple materials.  
- Material_num (Primary Key): integer NOT NULL Auto_Increment  
integer identification of distinct materialid, garmentid pair  
- MaterialID (Foreign Key): integer  
references Material.Material  
represents the material in the “garment_has_material” relationship  
- GarmentID (Foreign Key): integer  
references Garment.GarmentID  
represents the garment in the “garment_has_material” relationship  
