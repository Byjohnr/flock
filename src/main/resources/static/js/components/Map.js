var Map= React.createClass({
    mixins: [Reflux.connect(UserStore, 'user')],
    getInitialState: function() {
        return {googleMap: undefined, user: undefined}
    },
    componentDidMount: function() {
        google.maps.event.addDomListener(window, 'load', this.initMap());
        this.getLocations();
        UserActions.getUserInformation();
    },
    initMap : function() {
        var myLatLng = {lat: -25.363, lng: 131.044};
        var zoom = 5;
        var initGeoCoder = new google.maps.Geocoder();
        if (this.state.user.currentCity != undefined){
            initGeoCoder.geocode({'address': this.state.user.currentCity}), function (results, status) {
                if (status == google.maps.GeocoderStatus.OK){
                zoom = 7
                }
                else{
                    address = myLatLng;
                }
            }
            zoom = 7;
        }
        else{
            address = myLatLng;
            zoom = 5;
        }

        console.log(this.state.geocoder);
        this.setState({googleMap : new google.maps.Map(document.getElementById('daMap'), {
            center: {address},
            zoom: zoom})});
    },
    getLocations : function() {
        var parent = this;
        var geocoder = new google.maps.Geocoder();
        if (parent.props.events.location != undefined) {
            var address = parent.props.events.location;
            geocoder.geocode({'address': address}, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    var marker = new google.maps.Marker({
                        map: parent.state.googleMap,
                        position: results[0].geometry.location
                    });
                }
            });
        }
        else {
            var markerNodes = parent.props.events.map(function (event) {
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
        if (this.state.user != undefined){
            UserActions.getUserInformation();
        }
        return(
            <div id="daMap" style={{height: this.props.height, width: this.props.width}}>
            </div>
        );
    }
});