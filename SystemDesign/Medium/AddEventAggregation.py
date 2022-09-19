"""
Assume we are at Google scale and we are planning to start displaying ads on our websites.
We want to be able to let the Ad owners know how well their ads are performing in real-time
and bill them later based on the number of clicks.

[MVP] Features to support:
- Return the number of click events for a particular ad in the last M minutes in real-time.
- Return the top 100 most clicked ads in the past 1 minute in real-time. (Nice to have: Make these parameters configurable).

Scale:
- 2 million ads in total
- 1 billion daily active users and each user will click at-least 1 ad per day.

"""
"""
Follow ups:
- Support filtering based on country 

"""
