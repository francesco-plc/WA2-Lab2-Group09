'use strict'

const jwt = require("jsonwebtoken");

const correctPrivateKey = 'laboratorio2webapplications2ProfessorGiovanniMalnati';
const incorrectPrivateKey = 'laboratorio1webapplications1ProfessorGiovanniMalnati';

const now =  Math.floor(Date.now() / 1000) + (60 * 60)    //defines a token with 1 hour of expiration
let i = 1;

// this object will be serialized to JSON and sent in the body of the script request in  benchmark.js
module.exports = function(requestId) {
    //randomly create valid/invalid token fields
    const zone = Math.random() < 0.5 ? "1" : "7"
    const vz = Math.random() < 0.5 ? "123" : "7"
    const exp = Math.random() < 0.5 ? now+1 : 1516239022
    const privateKey = Math.random() < 0.5 ? correctPrivateKey : incorrectPrivateKey
    const sub = String(i++).padStart(10, '0')

    return {
        zone: zone,
        token: jwt.sign({
            "sub": sub,
            "name": "John Doe",
            "iat": 1516239022,
            "vz": vz,
            "exp": exp
        }, privateKey),
        requestId: requestId
    }
};
