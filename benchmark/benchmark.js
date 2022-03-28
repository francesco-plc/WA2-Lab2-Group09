'use strict';

const loadtest = require('loadtest');

const exec = require('child_process').exec;

exec('loadtest -p token.js -n 2 -c 2 http://localhost:8080/validate -T application/json',
    function (error, stdout, stderr) {
        console.log('stdout: ' + stdout);
        console.log('stderr: ' + stderr);
        if (error !== null) {
            console.log('exec error: ' + error);
        }
    });

