var heartbeatValueMax = 240;
var heartbeatValueMin = 0;

var heartbeatValueStart = 120;
var heartbeatValue = heartbeatValueStart;
$('#slider-output').html(heartbeatValue);

$( function() {
    $( "#slider" ).slider({
        max: heartbeatValueMax,
        min: heartbeatValueMin,
        value: heartbeatValue,
        slide: function( event, ui ) {
            heartbeatSlide(ui.value);
        },
        change: function( event, ui ) {
            heartbeatChange();
        }
    });
} );

function heartbeatSlide(value) {
    heartbeatValue = value;
    $('#slider-output').html(heartbeatValue);
    pulse();
    console.log('hearbeat: ' + heartbeatValue);
}

function heartbeatChange() {
    console.log('send to api: ' + heartbeatValue);
}



function pulse() {

    var time = heartbeatValueMax - heartbeatValue + 1;

    $('#beating-heart').stop(true);

    if(heartbeatValue != 0) {

        $('#beating-heart').animate({
            fontSize: "150px", // sets the base height and width
            opacity: 0.5
        }, time, function() {
            $('#beating-heart').animate({
                fontSize: "120px", // sets the alternative height and width
                opacity: 1
            }, time, function() {
                pulse();
            });
        });

    }

};

pulse();