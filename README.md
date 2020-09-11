# Client-Server-Project
Develop a server and a client simultaneously.
The client will allow the user to enter an arbitrary list of integers to be sent to the server. 
The server will then return a list of prime integers contained in the original list.

Implement client to prompt the user to enter a list of integers (and use “!” to end the list.)
Send the list of integers to the server, print the list of integers sent to the server, and later print the list of prime integers received from the server.

Implement server to wait for a socket connection.  
When a socket is accepted, read a list of integers, determine which in the list are prime, and then send a list containing the prime integers back to the client.
