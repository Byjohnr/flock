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
        if (this.state.userInformation !== undefined) {
            return (
                <div>
                    <NavBar />
                    <Picture />
                    <h1 className="text-center">
                        {this.state.userInformation.firstName} {this.state.userInformation.lastName}
                    </h1>
                    <h2 className="text-center">
                        {this.state.userInformation.email}
                    </h2>
                    <h2 className="text-center">
                        {this.state.userInformation.description}
                    </h2>
                    <h2 className="text-center">
                        {this.state.userInformation.currentCity}
                    </h2>
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
