"""

Given a basic architecture for simple booking system that supports 1k users today.

Frontend (1 server) ---> Backend Rest API (1 server) ---> SQL Database (1 server)

Features supported
- make a booking
- see past bookings
- basic user profile - name, email, phone, language, currency etc.

How to scale it for 1 million users and guarantee 99% uptime?

Slow queries?
How to handle traffic spikes?
Slow backend?
Frontend loading very slowly?

"""

"""
Expectation:

- group the traffic to reads and writes (80:20 read-write ratio)
- horizontal scaling of backend api and frontend with LB.
- autoscaling for peak traffic
- microservice architecture preferred. (user service, booking service)
- caching layer
- master-slave db architecture
- db indexing
- CDN for static content for frontend.

"""
