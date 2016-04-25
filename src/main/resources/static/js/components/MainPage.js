var MainPage = React.createClass({
    mixins: [Reflux.connect(UserStore, 'user')],
    componentDidMount: function () {
        google.maps.event.addDomListener(window, 'load', this.initMap());
    },
    initMap: function () {
        var myLatLng = {lat: 42.0308, lng: -93.6319};
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                myLatLng = {lat: position.coords.latitude, lng: position.coords.longitude};
                map.setCenter(myLatLng);

            });
        }
        var mapOptions = {
            zoom: 8,
            center: myLatLng
        };
        var map = new google.maps.Map(document.getElementById("anonymous_user_map"), mapOptions);
        this.addMarker(map, myLatLng);
    },
    addMarker: function (map, latLng) {
        var marker = new google.maps.Marker({
            position: latLng,
            map: map
        });
        // google.maps.event.addListener(marker, 'click', function () {
        //     console.log("You clicked " + marker.title);
        // })
    },
    render: function () {
        return (
            <div className="row">
                <NavBar hideInfo="true"/>
                <div className="col-sm-6">
                    <h2 className="text-center">See events near you!</h2>
                    <div id="anonymous_user_map"
                         style={{height: '500px', width: '90%', marginLeft: '5%', marginRight: '5%', borderWidth: 1, borderStyle: 'solid'}}></div>
                </div>
                <div className="col-sm-6">
                    <RegisterUser />
                </div>
            </div>
        );
    }
});

ReactDOM.render(<MainPage />, document.getElementById("main_page"));