'use strict';

const exec = require('child_process').exec;

//loadtest command line --> modify the following string to compute different tests:
exec('loadtest -p token.js -n 10000 -c 32 http://localhost:8080/validate -T application/json',
    function (error, stdout, stderr) {
        console.log('stdout: ' + stdout);
        console.log('stderr: ' + stderr);
        if (error !== null) {
            console.log('exec error: ' + error);
        }
    });

console.log('-----------------------------------')
console.log('loadtest running...wait for result!')