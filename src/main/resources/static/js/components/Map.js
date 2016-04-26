var Map= React.createClass({
    mixins: [Reflux.connect(UserStore, 'user')],
    getInitialState: function() {
        return {user : undefined,
            googleMap: undefined};
    },
    componentDidMount: function() {
        UserActions.getUserInformation();
        this.initMap();
    },
    initMap : function() {
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
                var latitude = parent.props.data.latitude;
                var longitude = parent.props.data.longitude;
                var myLatlng = new google.maps.LatLng(latitude, longitude);
                if (longitude != undefined) {
                    parent.state.googleMap.setCenter(myLatlng);
                    parent.state.googleMap.setZoom(5);
                    var marker = new google.maps.Marker({
                        map: parent.state.googleMap,
                        position: myLatlng
                    });
                }
                else {
                    geocoder.geocode({'address': parent.state.user.currentCity}, function (results, status) {
                        if (status == google.maps.GeocoderStatus.OK) {
                            parent.state.googleMap.setCenter(results[0].geometry.location);
                            parent.state.googleMap.setZoom(5);
                        }
                    });
                }
            }
            else {
                var markerNodes = parent.props.data.map(function (event) {
                    var latitude = event.latitude;
                    var longitude = event.longitude;
                    var type = event.type;
                    console.log(type);
                    var myLatlng = new google.maps.LatLng(latitude, longitude);
                    geocoder.geocode({'address': parent.state.user.currentCity}, function (results, status) {
                        if (status == google.maps.GeocoderStatus.OK) {
                            parent.state.googleMap.setCenter(results[0].geometry.location);
                            parent.state.googleMap.setZoom(8);
                        }
                    });
                    if (longitude != undefined) {
                        var marker = new google.maps.Marker({
                            map: parent.state.googleMap,
                            position: myLatlng,
                        });
                        marker.addListener('click', function(){
                            parent.showInfo(event, marker);
                        });
                        if (type === 'Open') {
                            marker.setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png');
                        }
                        else if (type === 'Connection_Only') {
                            marker.setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png');
                        }
                        else if (type === 'Invite_Only') {
                            marker.setIcon('http://maps.google.com/mapfiles/ms/icons/red-dot.png');
                        }
                    }
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
                    var zoom = parent.state.googleMap.getZoom();
                    console.log(zoom);
                    parent.props.marker(marker.getPosition());
                    parent.state.googleMap.setCenter(marker.getPosition());
                    parent.state.googleMap.setZoom(zoom);
                });
            }
        });
        this.setState({user: undefined});
    },
    showInfo : function (event, marker) {
        console.log('yeeees');
        var url = '/event/' + event.eventId;
        infoWindow = new google.maps.InfoWindow();
        var type;
        if (event.type === 'Open') {
            type = 'Open';
        }
        else if (event.type === 'Connection_Only') {
            type = 'Connections Only';
        }
        else if (event.type === 'Invite_Only') {
            type = 'Invite Only';
        }
        var string = '<a href=' + url + '><b>' + event.name + '</b></a><br>' +
            event.description + '<br>' +
            event.address + '<br>' +
            event.startTime + '<br>' +
            type + ' ' + event.tagName;
        infoWindow.setContent(string);
        infoWindow.setPosition(marker.getPosition());
        infoWindow.open(this.state.googleMap);
    },
    render: function() {
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