var chakram = require('chakram');
var common = require('./common');

var chai = require('chai');  
var assert = chai.assert;    // Using Assert style
var expect = chai.expect;    // Using Expect style

var API_ROOT = common.API_ROOT();

describe("Example", function() {
    it("should return status from Java", function () {
        var response = chakram.get(API_ROOT + "example/status" )
        .then( function(response) {
            var body = response.body;
            console.log("body", body);
            expect( body ).to.have.string( "Blah Blah Blah" );
            expect( body ).to.have.string( "This is active Java code with server time" );
            return chakram.wait();
        });
    });
    /*
    it("should return status from Kotlin", function () {
        chakram.get(API_ROOT + "test/status" )
        .done( function(response) {
            var body = response.body;
            //console.log("body", body);
            expect( body ).to.contain( "Blah Blah Blah" );
            expect( body ).to.contain( "This is active Kotlin code with server time" );
        })    
    });*/
});
