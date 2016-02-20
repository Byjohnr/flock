/**
 * Created by Thomas on 2/9/2016.
 */
var ViewAccount = React.createClass({
    mixins: [Reflux.connect(UserStore, 'userInformation')],
    getInitialState: function() {
        return {userInformation : undefined};
    },
    componentDidMount: function() {
        console.log('componentMounted');
        UserActions.getUserInformation("thomas@test.com");
    },
    render: function () {
        if (this.state.userInformation !== undefined) {
        //UserStore.getUserInformation("thomas@test.com");
            return (
                <div>
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
        //UserStore.getUserInformation("thomas@test.com");
            return (
                <span>Please wait while your information is being loaded...</span>
            )
        }
    }
});

ReactDOM.render(<ViewAccount />, document.getElementById('view_account'));