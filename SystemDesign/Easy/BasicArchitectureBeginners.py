"""
Given a basic architecture for simple/minimal booking system that supports 1k users today.

Frontend (1 server) <---> Backend Rest API (1 server) <---> SQL Database (1 server)

Frontend - showcases a static list of 100 hotels
Backend - handles all data needs of FE
Database - stores all the user, hotel and booking info.

Features supported today
- make a booking - book a hotel with checkin and checkout date.
- see past bookings - all booking made by user in the past.
- basic user profile - name, email, phone, language, currency etc.

Architectural changes to scale this system for 1 million+ users?
How to guarantee 99.9% uptime?
"""

"""
Challenge more :
Slow db queries?
How to handle traffic spikes?
Slow backend? How to debug?
Frontend loading very slowly? How to debug?
New requirement to add Payment service. A payment takes 1 minute to process. 
New requirement to track user activity data from frontend. How to handle realtime vs delayed statistics?
How to generate unique bookingId across DCs?
1 billion users?

Expectation:
- horizontal scaling of backend api and frontend with LB.
- autoscaling for peak traffic by request rates
- caching layer for slow changing data
- master-slave db architecture (geo sharded if billion users)
- db indexing for queries
- CDN for static content for frontend.
- group the traffic to reads and writes (80:20 read-write ratio) and optimise these paths.
- microservice architecture preferred. (user service, booking service)
- User activity tracking is done by sending message to kafka --> hadoop --> map reduce aggregator --> Data Warehouse
"""
