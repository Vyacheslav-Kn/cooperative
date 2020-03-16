mongo create_db.js
mongoimport --host 127.0.0.1 --port 27017 --db cooperative --collection template --file ../data/initial/template.json --jsonArray
mongoimport --host 127.0.0.1 --port 27017 --db cooperative --collection composite_tag --file ../data/initial/composite_tag.json --jsonArray