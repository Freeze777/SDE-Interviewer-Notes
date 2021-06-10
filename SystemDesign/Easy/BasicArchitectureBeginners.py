"""
Given a basic architecture for simple/minimal booking system that supports 1k users today.

Frontend (1 server) ---> Backend Rest API (1 server) ---> SQL Database (1 server)

Frontend - features a static list of 100 hotels
Backend - handles all data needs of FE
Database - stores all the user, hotel and booking info.

Features supported today
- make a booking - book a hotel
- see past bookings - all booking made by user in the past.
- basic user profile - name, email, phone, language, currency etc.

Architectural changes to scale this system for 1 million users?
How to guarantee 99.9% uptime?
"""

"""
Challenge more :
Slow queries?
How to handle traffic spikes?
Slow backend?
Frontend loading very slowly?
New requirement to track user activity data from frontend. How to handle?
1 billion users?

Expectation:
- horizontal scaling of backend api and frontend with LB.
- autoscaling for peak traffic
- caching layer
- master-slave db architecture (geo sharded if billion users)
- db indexing
- CDN for static content for frontend.
- group the traffic to reads and writes (80:20 read-write ratio)
- microservice architecture preferred. (user service, booking service)
"""
