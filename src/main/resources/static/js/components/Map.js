var Map= React.createClass({
    mixins: [Reflux.connect(UserStore, 'user')],
    getInitialState: function() {
        return {user : undefined, googleMap: undefined};
    },
    componentDidMount: function() {
        UserActions.getUserInformation();
        this.initMap();
    },
    initMap : function(position) {
        var myLatLng = {lat: -25.363, lng: 131.044};
        var parent = this;
        this.setState({
            googleMap: new google.maps.Map(document.getElementById('daMap'), {
                center: myLatLng,
                zoom: 1
            })
        });
    },
    getLocations : function() {
        var parent = this;
        var geocoder = new google.maps.Geocoder();
        if (this.state.user != undefined) {
            if (parent.props.data === undefined) {
                geocoder.geocode({'address': this.state.user.currentCity}, function (results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        parent.state.googleMap.setCenter(results[0].geometry.location);
                        parent.state.googleMap.setZoom(5);
                    }
                });
            }
            else if (parent.props.data.eventName != undefined) {
                var address = parent.props.data.location;
                var latitude = parent.props.data.latitude;
                var longitude = parent.props.data.longitude;
                var myLatlng = new google.maps.LatLng(latitude, longitude);
                geocoder.geocode({'address': address}, function (results, status) {
                    if (longitude != undefined) {
                        parent.state.googleMap.setCenter(myLatlng);
                        parent.state.googleMap.setZoom(5);
                        var marker = new google.maps.Marker({
                            map: parent.state.googleMap,
                            position: myLatlng
                        });
                    }
                    else if (status == google.maps.GeocoderStatus.OK) {
                        parent.state.googleMap.setCenter(results[0].geometry.location);
                        parent.state.googleMap.setZoom(5);
                        var marker = new google.maps.Marker({
                            map: parent.state.googleMap,
                            position: results[0].geometry.location
                        });
                    }
                    else {
                        console.log('yes');
                        geocoder.geocode({'address': parent.state.user.currentCity}, function (results, status) {
                            if (status == google.maps.GeocoderStatus.OK) {
                                parent.state.googleMap.setCenter(results[0].geometry.location);
                                parent.state.googleMap.setZoom(5);
                            }
                        });
                    }
                });
            }
            else {
                var markerNodes = parent.props.data.map(function (event) {
                    var address = event.address;
                    var latitude = event.latitude;
                    var longitude = event.longitude;
                    var myLatlng = new google.maps.LatLng(latitude, longitude);
                    geocoder.geocode({'address': parent.state.user.currentCity}, function (results, status) {
                        if (status == google.maps.GeocoderStatus.OK) {
                            parent.state.googleMap.setCenter(results[0].geometry.location);
                            parent.state.googleMap.setZoom(8);
                        }
                    });
                    geocoder.geocode({'address': address}, function (results, status) {
                        if (longitude != undefined) {
                            var marker = new google.maps.Marker({
                                map: parent.state.googleMap,
                                position: myLatlng
                            });
                        }
                        else if (status == google.maps.GeocoderStatus.OK) {
                            var marker = new google.maps.Marker({
                                map: parent.state.googleMap,
                                position: results[0].geometry.location
                            });
                        }
                    });
                });
            }
        }
    },
    addMarker : function() {
        var parent = this;
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({'address': this.state.user.currentCity}, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                var marker = new google.maps.Marker({
                    map: parent.state.googleMap,
                    position: results[0].geometry.location,
                    draggable:true
                });
                marker.addListener('dragend', function() {
                    parent.state.googleMap.setCenter(marker.getPosition());
                    parent.props.marker(marker.getPosition());
                });
            }
        });
    },
    render: function() {
        console.log(this.state.user);
        this.getLocations();
        if (this.props.data === undefined) {
            return (
                <div>
                    <div id="daMap" style={{height: this.props.height, width: this.props.width}}></div>
                    <button type="button" className="btn btn-primary" id="add" onClick={this.addMarker}>Add Marker
                    </button>
                </div>
            );
        }
        return (
            <div id="daMap" style={{height: this.props.height, width: this.props.width}}></div>
        );
    }
});