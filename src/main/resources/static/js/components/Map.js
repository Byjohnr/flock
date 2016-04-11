var Map= React.createClass({
    getInitialState: function() {
        return {googleMap: undefined,
        geocoder: undefined}
    },
    componentDidMount: function() {
        console.log("Mounted");
        google.maps.event.addDomListener(window, 'load', this.initMap());
        this.getLocations();
    },
    initMap : function() {
        var myLatLng = {lat: -25.363, lng: 131.044};
        this.state.geocoder = new google.maps.Geocoder();
        this.state.googleMap = new google.maps.Map(document.getElementById('daMap'), {
            center: {lat: 42.018018, lng: -93.660742},
            zoom: 5});
    },
    getLocations : function() {
        var parent = this;
        var markerNodes = parent.props.events.map(function (event) {
            var address = event.address;
            parent.state.geocoder.geocode( { 'address': address}, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    var marker = new google.maps.Marker({
                        map: parent.state.googleMap,
                        position: results[0].geometry.location
                    });
                }
            });
        });
    },
    render: function() {
        return(
            <div id="daMap" style={{height: this.props.height, width: this.props.width}}>
            </div>
        );
    }
});