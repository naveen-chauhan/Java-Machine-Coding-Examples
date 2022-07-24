**Problem:** 
Design a Ride Sharing App.

**Problem Description:** 
https://www.geeksforgeeks.org/flipkart-machine-coding-round-experience/

**Input -**

add_user(“Rohan, M, 36”);

add_vehicle(“Rohan, Swift, KA-01-12345“);

add_user(“Shashank, M, 29”);

add_vehicle(“Shashank, Baleno, TS-05-62395“);

add_user(“Nandini, F, 29”);

add_user(“Shipra, F, 27”);

add_vehicle(“Shipra, Polo, KA-05-41491“);

add_vehicle(“Shipra, Activa, KA-12-12332”);

add_user(“Gaurav, M, 29“);

add_user(“Rahul, M, 35“);

add_vehicle(“Rahul, XUV, KA-05-1234”);

offer_ride(“Rohan, Origin=Hyderabad, Available Seats=1, Vehicle=Swift, KA-01-12345, Destination= Bangalore”);

offer_ride(“Shipra, Origin=Bangalore, Available Seats=1, Vehicle=Activa, KA-12-12332, Destination=Mysore”);

offer_ride(“Shipra, Origin=Bangalore, Available Seats=2, Vehicle=Polo, KA-05-41491, Destination=Mysore”);

offer_ride(“Shashank, Origin=Hyderabad, Available Seats=2, Vehicle=Baleno, TS-05-62395, Destination=Bangalore”);

offer_ride(“Rahul, Origin=Hyderabad, Available Seats=5, Vehicle=XUV,  KA-05-1234, Destination=Bangalore”);

offer_ride(“Rohan, Origin=Bangalore, Available Seats=1, Vehicle=Swift, KA-01-12345, Destination=Pune”);

select_ride(“Nandini, Origin=Bangalore, Destination=Mysore, Seats=1, Most Vacant”);

select_ride(“Gaurav, Origin=Bangalore, Destination=Mysore, Seats=1, Preferred Vehicle=Activa”);

select_ride(“Shashank, Origin=Mumbai, Destination=Bangalore, Seats=1, Most Vacant”);

select_ride(“Rohan, Origin=Hyderabad, Destination=Bangalore, Seats=1, Preferred Vehicle=Baleno”);

select_ride(“Shashank, Origin=Hyderabad, Destination=Bangalore, Seats=1,Preferred Vehicle=Polo”);

print_ride_stats();

end_ride($unique_Id);

//end_ride(”e4ecd212-33cb-4ab4-9008-96f69ecdcf34”);

print_ride_stats();