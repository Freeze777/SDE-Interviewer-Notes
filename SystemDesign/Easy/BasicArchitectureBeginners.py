"""
Given a basic architecture for simple booking system that supports 1k users today.

Frontend (1 server) ---> Backend Rest API (1 server) ---> SQL Database (1 server)

Features supported
- make a booking - book a hotel
- see past bookings - all booking made by user in the past.
- basic user profile - name, email, phone, language, currency etc.

How to scale it for 1 million users?
How to guarantee 99.9% uptime?
"""

"""
Challenge more :
Slow queries?
How to handle traffic spikes?
Slow backend?
Frontend loading very slowly?
1 billion users?

Expectation:
- group the traffic to reads and writes (80:20 read-write ratio)
- microservice architecture preferred. (user service, booking service)
- horizontal scaling of backend api and frontend with LB.
- autoscaling for peak traffic
- caching layer
- master-slave db architecture
- db indexing
- CDN for static content for frontend.
"""
