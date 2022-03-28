'use strict'

const jwt = require("jsonwebtoken");

const correctPrivateKey = 'laboratorio2webapplications2ProfessorGiovanniMalnati';
const incorrectPrivateKey = 'laboratorio1webapplications1ProfessorGiovanniMalnati';

const now =  Math.floor(Date.now() / 1000) + (60 * 60)    //defines a token with 1 hour of expiration

// this object will be serialized to JSON and sent in the body of the script request in  benchmark.js
module.exports = function(requestId) {
    //randomly create valid/invalid token fields
    const zone =  "1"
    const vz =  "123"
    const exp =  now+1
    const privateKey =  correctPrivateKey

    return {
        zone: zone,
        token: jwt.sign({
            "sub": "1234567890",
            "name": "John Doe",
            "iat": 1516239022,
            "vz": vz,
            "exp": exp
        }, privateKey),
        requestId: requestId
    }
};
