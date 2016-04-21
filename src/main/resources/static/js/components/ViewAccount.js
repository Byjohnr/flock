var spacedRow = {
    paddingBottom: '25px'
};

var ViewAccount = React.createClass({
    mixins: [Reflux.connect(UserStore, 'userInformation'), Reflux.connect(ConnectionStore, 'connectionGroupList')],
    getInitialState: function () {
        return {userInformation: undefined, connectionGroupList: undefined};
    },
    componentDidMount: function () {
        console.log('componentMounted');
        if (this.props.account === true) {
            console.log("truuu");
            UserActions.getUserInformation();
            ConnectionActions.getConnectionGroups();
        } else {
            console.log("falseee");
            UserActions.getOtherUserInfo();
        }
    },
    render: function () {
        var connectionStatus = "";
        var listOfEvents = "";
        var connectionGroupLink = "";
        if (this.props.account !== true) {
            connectionStatus =
                <div>
                    <ConnectionStatus />
                </div>;
        } else if (this.state.connectionGroupList != undefined) {
            var listOfConnectionGroups = this.state.connectionGroupList.map(function (connectionGroup) {
                var connectionGroupUrl = "/account/connectionGroup/" + connectionGroup.id;
                return (
                    <div className="col-md-4 well-sm" key={connectionGroup.id}>
                        <a className="btn btn-default well-lg" style={{width: '80%'}} href={connectionGroupUrl}>{connectionGroup.groupName}</a>
                    </div>
                )
            });
            listOfEvents =
                <div className="row" style={{spacedRow, borderStyle: 'solid', borderWidth: '1px'}}>
                    <div className="col-md-12" style={{width: '100%', maxHeight: '400px', overflowY: 'scroll'}}>
                        <EventList />
                    </div>
                </div>;
            connectionGroupLink =
                <div className="row" style={{spacedRow, maxHeight: '300px', overflowY: 'scroll', borderStyle: 'solid', borderWidth: '1px'}}>
                    <span className="col-md-12 well-sm"><strong>Connection Groups</strong></span>
                    <div className="col-md-4 well-sm">
                        <a className="btn btn-default well-lg" style={{width: '80%'}} href="/account/connectionGroups">Manage Connection Groups</a>
                    </div>
                    {listOfConnectionGroups}
                </div>;
        }
        if (this.state.userInformation !== undefined) {
            return (
                <div>
                    <NavBar />
                    <div className="text-center container">
                        <div className="row" style={spacedRow}>
                            <div className="col-md-6 col-xs-12">
                                <Picture
                                    sendingUrl={this.props.account === true ? "/api/picture_upload/profile_picture" : ""}
                                    pictureEditable={this.props.account === true}
                                    getterUrl={this.props.account === true ? "/api/profile_picture" : "/api/user_profile_picture/" + this.state.userInformation.id}/>
                            </div>
                            <div className="col-md-6 col-xs-12">
                                <h1>
                                    {this.state.userInformation.firstName} {this.state.userInformation.lastName}
                                </h1>
                                <h2>
                                    {this.state.userInformation.email}
                                </h2>
                                <h2>
                                    {this.state.userInformation.description}
                                </h2>
                                <h2>
                                    {this.state.userInformation.currentCity}
                                </h2>
                                {connectionStatus}
                            </div>
                        </div>
                        {connectionGroupLink}
                        {listOfEvents}
                    </div>
                </div>
            );
        } else {
            return (
                <div>
                    <NavBar />
                    <h1 className="text-center">Loading <i className="fa fa-spin fa-refresh"/></h1>
                </div>
            )
        }
    }
});
