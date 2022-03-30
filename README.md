# WA2-Lab2-Group09

## Team Members:

| Surname    | First Name  | ID      |
|------------|-------------|---------|
| Catalano   | Sofia       | s290364 |
| Ernesto    | Cristian    | s281697 |
| Paladino   | Mariagrazia | s290393 |
| Policastro | Francesco   | s281705 |


### LoadTest instruction:
1) Build & Run ServerApplication
2) From the terminal:
   1) move into the `\benchmark` folder 
   2) **OPTIONAL**: (if not already done: install **loadtest** `npm install -g loadtest` and **jsonwebtoken** `npm install jsonwebtoken`)
   3) launch `node .\benchmark.js` command
3) Wait until RESULT are shown in the terminal
4) When performing consecutive loadtest be sure to stop and run again the Server in order to wipe the tokens hashmap.

In `benchmark.js` file modify the loadtest command line to perform different tests such as:

1) keep-alive connection:

`loadtest -k -p token.js -n [number-of-request] -c [concurrency-level] http://localhost:8080/validate -T application/json`
2) time-limit: Max number of seconds to wait until requests no longer go out:

`loadtest -t 5 -p token.js -n [number-of-request] -c [concurrency-level] http://localhost:8080/validate -T application/json`

<hr/>

### USL Plot Lab2/Point 5

![usl1](server/src/main/resources/usl1.jpg?raw=true "usl1")

### USL Plot Lab2/Point 6

![usl2](server/src/main/resources/usl2.jpg?raw=true "usl2")

As we can see in the graph above the curve has both a slightly lower peak than the graph of point 5 and tends to reach zero faster, 
this is because adding a constraints (tickets with the same subfield cannot be used more times) and introducing a concurrent data structure 
(ConcurrentHashMap) the alpha and beta parameters, therefore, increase so that the absolute maximum capacity gain obtained 𝑁 = √ ((1 − 𝛼) / 𝛽)
decreases.

### USL Plot Lab2/Point 7 (Keep Alive)

![usl3](server/src/main/resources/usl3.jpg?raw=true "usl3")

TTP Keep-alive is the mechanism that instructs the client and server to maintain a persistent TCP connection, decoupling the one-to-one relationship between TCP and HTTP, effectively increasing the scalability of the server.
According to the Universal Scalability Law, since the gamma parameter increases, the average system throughput will increase too, as shown in the graph.

### USL Plot Lab2/Point 7 (Time Limit)

![usl4](server/src/main/resources/usl4.jpg?raw=true "usl4")

As shown in the image, in this case the maximum capacity is the same of using the default value for the time limit. 
But in this case the number of requests per second decreases slower than the previous case, even in case of high concurrency level. 