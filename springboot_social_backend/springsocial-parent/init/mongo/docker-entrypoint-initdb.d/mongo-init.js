//WILL SEE THIS IN THE MONGODB CONSOLE

//log the start if the script execution
print("START");

//switch to the 'post' db (or creat it if it doesnt exists)
db = db.getSiblingDB('post');

db.createUser(
    {
        user: 'rootadmin',
        pwd: 'password',
        roles: [{ role: 'readWrite', db: 'post'}],
    }
);

//create a new collection named 'user' within the 'post' database
db.createCollection('user');

print('END');