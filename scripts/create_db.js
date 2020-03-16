var db = connect("localhost:27017/cooperative");
db.createCollection("composite_tag");
db.createCollection("template");