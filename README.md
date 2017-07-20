# Chat-Application
A desktop chat application supporting modern time features like last seen and multiple users. It is developed in java swing using socket programming, multi-threading and event listeners. 
Database used is MySQL. Database connectivity is done using JDBC 

Working:
1. ServerGui class establishes a server through which multiple clients can connect using sockets.Each client is connected to the   server.
2. Login class opens the client GUI and on proper authentication login is done to connect it to the server. For the first time user SignUp facility is provided.
3. Each client connected to the server can see the list of available clients i.e. clients which are connected to the server and thus ready for chat. 
4.To send messages to different users one can easily navigate between different clients by clicking on the username shown in this list.
5. Since there are multiple users, to prevent mixing of messages from different users, on selecting the user from the available user list a different panel is opened in the same chat window to start the chat with this new user.
6.Another additional feature added is last seen. One can see whether tha client is online or the time he/she has last seen the chat window. This time shown in last seen is basically the last time that client focus was on the chat screen.
7.There is a difference between available and online users. Users are available when they have connected to the server and thus eligible for chat and thus shown in the list to each client. While online users are those whose screen focus is on the chat window.
8. Logout button is provided to disconnect from the server.

Procedure to run:
1. Create database using database file.
2. Run ServerGui class on one cmd/machine
3. Run Login class to open a user on another cmd/machine
4. Either signUp as a new user or login using username and password
5. To open another user Run Login class on another cmd/machine. Multiple users can be opened simultaneously on different machines/cmd.
6. Happy chatting !!

Pre-requisites:
1. Database must be created as the first step
2. Must have java installed on the machine
3. Sql connector must be in the classpath
4. Server machine and all the client machines must be in the network.
5. Before login as a user, make sure ServerGui class is running.

Features:

1. A signup page for new user. All the required constraints are applied in the signup form.
2. If already a user, login using username and password. Authentication constraints with unique usernames is applied.
3. If a user account is active on one cmd/machine, same user account cannot be used to login as another client on another cmd/machine.
4. On the client side user interface, one can see the list of available users connected to the server and thus ready for chat.
5. On selecting the user from this list a different panel is opened in the same window to start the chat with this new user.
6. For ease of user event listeners are used. Like key listener so that a button action is triggered on pressing enter. Tabs can function.
7. Whether the user is online or the last seen time of the user is displayed on the chat window.
8. Multiple messages can be sent to multiple users simultaneously.
9. Logout to disconnect from the chat server.
