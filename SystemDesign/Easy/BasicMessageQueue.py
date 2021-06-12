"""
Assume we are having food ordering system.

Food App ---> Order API ---> Payment API

We are having issues with Payment API not able to process payments at the rate at which Order API is operating.
What architectural changes to keep our customers happy?
"""

"""
Follow ups:
How does food app know payment got processed?
Payment API went down? what happens to messages? how ensure message is not lost?
Payment API got huge backlog of messages in the queue, what do we do?
Duplicate payments issue. how to resolve?

Expected:
Add a message queue between them.
Order API produces a message to the queue and return success to Food App.
consumers at payment api side will consume the messages and invoke payment api for processing.
Frontend can keep polling a different api to get payment status 
OR it can give order id back to customer and let payment api charge the customer later.
"""
