var Map= React.createClass({
    getInitialState: function() {
        return {googleMap: undefined,
        geocoder: undefined}
    },
    componentDidMount: function() {
        console.log("Mounted");
        google.maps.event.addDomListener(window, 'load', this.initMap());
        this.codeAddresses();
    },
    initMap : function() {
        var myLatLng = {lat: -25.363, lng: 131.044};
        this.state.geocoder = new google.maps.Geocoder();
        this.state.googleMap = new google.maps.Map(document.getElementById('daMap'), {
            center: {lat: 42.018018, lng: -93.660742},
            zoom: 5});

        var marker = new google.maps.Marker({
            position: myLatLng,
            map: this.state.googleMap,
            title: 'Hello World!'
        });
    },
    codeAddresses : function() {
        var parent = this;
        var markerNodes = this.props.events.map(function (event) {
            var address = event.location;
            this.state.geocoder.geocode( { 'address': address}, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    parent.state.googleMap.setCenter(results[0].geometry.location);
                    var marker = new google.maps.Marker({
                        map: parent.state.googleMap,
                        position: results[0].geometry.location
                    });
                } else {
                    alert("Geocode was not successful for the following reason: " + status);
                }
            });
        });
    },
    render: function() {
        return(
            <div id="daMap" style={{height: '800px'}}>
            </div>
        );
    }
});