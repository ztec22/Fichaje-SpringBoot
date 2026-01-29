db = db.getSiblingDB("clockin_db");
db.createCollection("clock_in_types")

db.createUser({
  user: "user",
  pwd: "pass",
  roles: [
    {
      role: "readWrite",
      db: "clockin_db"
    }
  ]
});

db.clock_in_types.insertMany(
    [
        {"description":"Enter to Work","io":true},
        {"description":"Work Break","io":false},
        {"description":"Return to Work","io":true},
        {"description":"Leave Work","io":false}
    ]
);
