var anonymousUserMap;
var MainPage = React.createClass({
    mixins: [Reflux.connect(EventStore, 'publicEventAddresses')],
    getInitialState: function() {
        return {publicEventAddresses: undefined}
    },
    componentDidMount: function () {
        EventActions.getPublicEventAddresses();
        google.maps.event.addDomListener(window, 'load', this.initMap());
    },
    initMap: function () {
        var myLatLng = {lat: 42.0308, lng: -93.6319};
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                myLatLng = {lat: position.coords.latitude, lng: position.coords.longitude};
                anonymousUserMap.setCenter(myLatLng);
            });
        }
        var mapOptions = {
            zoom: 8,
            center: myLatLng
        };
        anonymousUserMap = new google.maps.Map(document.getElementById("anonymous_user_map"), mapOptions);
    },
    addMarkers: function () {
        this.state.publicEventAddresses.forEach(function (address) {
                    var marker = new google.maps.Marker({
                        position: {lat: address.latitude, lng: address.longitude},
                        map: anonymousUserMap
                    });
        });
    },
    render: function () {
        if (this.state.publicEventAddresses != undefined && this.state.publicEventAddresses.length > 0) {
            this.addMarkers();
        }
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