var chakram = require('chakram');
var common = require('./common');

var chai = require('chai');  
var assert = chai.assert;    // Using Assert style
var expect = chai.expect;    // Using Expect style

var API_ROOT = common.API_ROOT();

describe("Example", function() {
    it("should return status", function () {
        chakram.get(API_ROOT + "example/status" )
        .done( function(response) {
            var body = response.body;
            expect( body ).to.contain( "Blah Blah Blah" );
            expect( body ).to.contain( "This is active Java code with server time" );
        })    
    });
    it("should just work", function () {
        common.nothing();
    });
});
