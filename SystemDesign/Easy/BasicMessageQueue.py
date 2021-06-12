"""
Assume we are having food ordering system.

Food App ---> Order API ---> Payment API

We are having issues with Payment API not able to process payments at the rate at which Order API is operating.
What architectural changes to keep our customers happy?
"""

"""

Add a message queue between them.
Order API submits a message and return success to Frontend end.
Frontend can keep polling a different api to get payment status or it can give order id back to customer 
or payment api will charge the customer later.
"""
