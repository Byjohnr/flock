var ViewAccount = React.createClass({
    mixins: [Reflux.connect(UserStore, 'userInformation')],
    getInitialState: function () {
        return {userInformation: undefined};
    },
    componentDidMount: function () {
        console.log('componentMounted');
        if (this.props.account === true) {
            console.log("truuu");
            UserActions.getUserInformation();
        } else {
            console.log("falseee");
            UserActions.getOtherUserInfo();
        }
    },
    render: function () {
        var connectionStatus = "";
        var connectionGroupLink = <a href="/account/connectionGroups">My Connection Groups</a> ;
        if(this.props.account != true) {
            connectionStatus = <ConnectionStatus />;
            connectionGroupLink = "";
        }
        if (this.state.userInformation !== undefined) {
            return (
                <div>
                    <NavBar />
                    <div className="text-center container">
                        <div className="row">
                            <div className="col-md-1 col-xs-12">
                                {connectionGroupLink}
                            </div>
                            <div className="col-md-6 col-xs-12">
                                <Picture sendingUrl={this.props.account === true ? "/api/picture_upload/profile_picture" : ""}
                                         pictureEditable={this.props.account === true}
                                         getterUrl={this.props.account === true ? "/api/profile_picture" : "/api/user_profile_picture/" + this.state.userInformation.id} />
                            </div>
                            <div className="col-md-5 col-xs-12">
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
                                <div>
                                    {connectionStatus}
                                </div>
                            </div>
                        </div>
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
