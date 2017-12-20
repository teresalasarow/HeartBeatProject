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

    console.log('try send to rest-api: ' + heartbeatValue);

    try {
        $.ajax({
        		type: "POST",
    			url: 'http://localhost:9080/HeartBeat',
    			data: JSON.stringify({rate: heartbeatValue}),
        
            contentType: "application/json; charset=utf-8",
            success: function(data){
                console.log('success');
                console.log(data);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log('error');
            }
        });
    } catch(ex) {
        console.log('error catched');
        console.log(ex);
    }
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