var chakram = require('chakram');
var chai = require('chai');  
var assert = chai.assert;    // Using Assert style
var expect = chai.expect;    // Using Expect style

module.exports = {
    nothing: function() {
    },

    API_ROOT : function() { return "http://localhost:8010/"; }
}
