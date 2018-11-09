
This project locates mobile based on three points and distances from these three points. It is using 2D Trilateration algorithm.


The mathematical reference is taken from below book.


https://books.google.ee/books?id=Ki2DMaeeHpUC&pg=PA79&lpg=PA79&dq=trilateration+algorithm+2d+java&source=bl&ots=8YGZE33BEF&sig=kZII8OWtpCccJxPIuvtGmjTNr-A&hl=en&sa=X&ved=2ahUKEwiarp3cpcTeAhWFjSwKHcKkBxM4ChDoATALegQIARAB#v=onepage&q=trilateration%20algorithm%202d%20java&f=false 




End point 1: save input 

URL: http://localhost:8080/api/v1/baseStation/save

Input JSON

{  

	"base_station_id": "b1",
   
   	"listMobileReport":
   	[
   
		{

		 "mobile_station_id":"m1",

		 "distance":10,

		 "timeStamp":"2018-10-02 00:00:00"

		},

		{  

		 "mobile_station_id":"m2",

		 "distance":13,

		 "timeStamp":"2018-10-02 01:01:01"

		}
	
	]
  
}







End point 2: locateMobile input

URL: http://localhost:8080/api/v1/baseStation/locateMobile

Input JSON

{

    "mobile_station_id":"m2",
    
    "timeStamp":"2018-10-04 01:01:12"
    
}
