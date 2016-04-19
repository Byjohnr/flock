var Map= React.createClass({
    mixins: [Reflux.connect(UserStore,'user')],
    getInitialState: function() {
        return {googleMap: undefined,
        user : undefined}
    },
    componentDidMount: function() {
        google.maps.event.addDomListener(window, 'load', this.initMap());
        UserActions.getUserInformation;
        if (this.props.data != undefined) {
            this.getLocations();
        }
    },
    initMap : function() {
        var myLatLng = {lat: -25.363, lng: 131.044};
        console.log(this.state.geocoder);
        this.setState({googleMap : new google.maps.Map(document.getElementById('daMap'), {
            center: {lat: 42.018018, lng: -93.660742},
            zoom: 5})});
    },
    getLocations : function() {
        var parent = this;
        var geocoder = new google.maps.Geocoder();
        if (parent.props.data.location != undefined) {
            var address = parent.props.data.location;
            geocoder.geocode({'address': address}, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    var marker = new google.maps.Marker({
                        map: parent.state.googleMap,
                        position: results[0].geometry.location
                    });
                }
            });
        }
        else if (this.props.data === undefined) {
            geocoder.geocode({'address': this.state.user.currentCity}, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    var marker = new google.maps.Marker({
                        map: parent.state.googleMap,
                        position: results[0].geometry.location
                    });
                }
            });
        }
        else {
            var markerNodes = parent.props.data.map(function (event) {
                var address = event.address;
                geocoder.geocode({'address': address}, function (results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        var marker = new google.maps.Marker({
                            map: parent.state.googleMap,
                            position: results[0].geometry.location
                        });
                    }
                });
            });
        }
    },
    render: function() {
        return(
            <div id="daMap" style={{height: this.props.height, width: this.props.width}}>
            </div>
        );
    }
});