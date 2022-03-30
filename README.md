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

#### keep-alive connection
loadtest -p -k token.js -n 2 -c 2 http://localhost:8080/validate -T application/json

#### time-limit: Max number of seconds to wait until requests no longer go out.
loadtest -p -t 10 token.js -n 2 -c 2 http://localhost:8080/validate -T application/json

#### -T content-type: set the MIME content type for POST data. Default: text/plain.
loadtest -p 10 token.js -n 2 -c 2 http://localhost:8080/validate

<hr/>

### USL Plot Lab2/Point 5

![usl1](server/src/main/resources/usl1.jpg?raw=true "usl1")

### USL Plot Lab2/Point 6

![usl2](server/src/main/resources/usl2.jpg?raw=true "usl2")


