var Map = React.createClass({
    mixins: [Reflux.connect(UserStore, 'user')],
    getInitialState: function () {
        return {googleMap: undefined, user: undefined}
    },
    componentDidMount: function () {
        UserActions.getUserInformation();
        //google.maps.event.addDomListener(window, 'load', this.initMap());
       // this.getLocations();
    },
    initMap: function () {
        var parent = this;
        var myLatLng = {lat: -25.363, lng: 131.044};;
        var zoom = 5;
        var result;
        var initGeoCoder = new google.maps.Geocoder();
        if (this.state.user.currentCity != undefined) {
            var address = this.state.user.currentCity;
            initGeoCoder.geocode({'address': address}, function (results, status) {
                result= results;
                if (status == google.maps.GeocoderStatus.OK) {
                    zoom = 2;
                }
            });
        }
        else {
            address = myLatLng;
            zoom = 5;
        }
        console.log(this.state.user.currentCity);
        console.log(initGeoCoder);
        console.log(result[0]);
       // this.setState({
            googleMap: new google.maps.Map(document.getElementById('daMap'), {
                map: parent.state.googleMap,
                center: {position: result[0].geometry.location},
                zoom: zoom
            })
        //});
    },
    getLocations: function () {
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
    render: function () {
        if (this.state.user != undefined){
            google.maps.event.addDomListener(window, 'load', this.initMap());
            this.getLocations();
        }
        return (
            <div id="daMap" style={{height: this.props.height, width: this.props.width}}>
            </div>
        );
    }
});