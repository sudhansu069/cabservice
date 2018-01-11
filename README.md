# cabservice


Sampel Request :

1. /drop_points  ---> For Adding the users drop location into the System

  Method Type : POST

  Accept - Application/json

Requets Paypay Load :

{ 
    "target_headquarter":"1,8,1,2,1",
    "pointA": "0,1,2,1,2",
    "pointB": "8,0,1,3,1",
    "pointC": "7,9,0,1,1",
    "pointD": "2,2,2,0,1",
    "pointE": "2,9,6,7,0"
}

 Response - 201 (Successfully created)

 2. /register


  Method Type : POST

  Accept - Application/json

  Sample Request Json :

  {
  "team_members": [
    {
      "team_member_id": "1",
      "gender": "M",
      "drop_point": "pointC"
    },
    {
      "team_member_id": "2",
      "gender": "M",
      "drop_point": "pointB"
    },
    {
      "team_member_id": "3",
      "gender": "M",
      "drop_point": "pointA"
    },
    {
      "team_member_id": "4",
      "gender": "M",
      "drop_point": "pointE"
    },
    {
      "team_member_id": "5",
      "gender": "M",
      "drop_point": "pointE"
    }
  ]
}

Response - 201 (Successfully created)


3. /cabs ( For Posting Cabs into the System)


  Method Type : POST

  Accept - Application/json

  Sample Request Json :

  {
  "cabs": [
    {
      "id": "cabA",
      "cost": 2,
      "capacity": 2
    },
    {
      "id": "cabB",
      "cost": 1,
      "capacity": 3
    },
    
     {
      "id": "cabC",
      "cost": 2,
      "capacity": 3
    }
  ]
}


Response - 201 when successfully posted.
Response - 400 Bad Request When the capacity/cabs needed is not enough to help members to drop at their drop locations.


4./route_plan

 Method Type : GET

 Sample Response :

 {
    "total_cost": 5,
    "routes": [
        {
            "cab_id": "cabA",
            "team_member_ids": "4,5",
            "route": "target_headquarter,pointE",
            "route_cost": 2
        },
        {
            "cab_id": "cabB",
            "team_member_ids": "3",
            "route": "target_headquarter,pointA",
            "route_cost": 1
        },
        {
            "cab_id": "cabC",
            "team_member_ids": "2",
            "route": "target_headquarter,pointB",
            "route_cost": 2
        }
    ]
}






