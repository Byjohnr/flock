var AdminUserPage = React.createClass({
    mixins: [Reflux.connect(UserStore, 'allUsers')],
    getInitialState: function () {
        return {allUsers: undefined};
    },
    componentDidMount: function () {
        UserActions.getAllUsers();
    },
    render: function () {
        var connectionStatus = "";
        var listOfEvents = "";
        var connectionGroupLink =
            <div className="row" style={spacedRow}>
                <div className="col-md-12">
                    <a href="/account/connectionGroups">My Connection Groups</a>
                </div>
            </div>;
        if (this.props.account !== true) {
            connectionStatus =
                <div>
                    <ConnectionStatus />
                </div>;
            connectionGroupLink = "";
        } else {
            listOfEvents =
                <div className="row" style={{spacedRow, borderStyle: 'solid', borderWidth: '1px'}}>
                    <div className="col-md-12" style={{width: '100%', height: '400px', overflowY: 'scroll'}}>
                        <EventList />
                    </div>
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
ReactDOM.render(<AdminUserPage />, document.getElementById('admin_user'));