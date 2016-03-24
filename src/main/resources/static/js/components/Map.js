var Map= React.createClass({
    componentDidMount: function() {
        console.log("Mounted");
        google.maps.event.addDomListener(window, 'load', this.initMap());
    },
    initMap : function() {
        var myLatLng = {lat: -25.363, lng: 131.044};
        var map = new google.maps.Map(document.getElementById('daMap'), {
            center: {lat: 42.018018, lng: -93.660742},
            zoom: 5});

        var marker = new google.maps.Marker({
            position: myLatLng,
            map: map,
            title: 'Hello World!'
        });
    },
    render: function() {
        return(
            <div id="daMap" style={{height: '800px'}}>
            </div>
        );
    }
});