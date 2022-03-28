# WA2-Lab2-Group09

## Team Members:

| Surname    | First Name  | ID      |
|------------|-------------|---------|
| Catalano   | Sofia       | s290364 |
| Ernesto    | Cristian    | s281697 |
| Paladino   | Mariagrazia | s290393 |
| Policastro | Francesco   | s281705 |


##LoadTest instrucion:
1) Build & Run ServerApplication
2) From the terminal:
   1) move into the `\benchmark` folder 
   2) (if not already done: install **loadtest** `npm install -g loadtest` and **jsonwebtoken** `npm install jsonwebtoken`)
   3) launch `node .\benchmark.js` command
3) Wait until RESULT are shown in the terminal

### keep-alive connection
loadtest -p -k token.js -n 2 -c 2 http://localhost:8080/validate -T application/json

### time-limit: Max number of seconds to wait until requests no longer go out.
loadtest -p -t 10 token.js -n 2 -c 2 http://localhost:8080/validate -T application/json

### -T content-type: set the MIME content type for POST data. Default: text/plain.
loadtest -p 10 token.js -n 2 -c 2 http://localhost:8080/validate
