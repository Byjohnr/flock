var RegisterUser = React.createClass({
    mixins: [Reflux.connect(UserStore, 'user')],

    handleCreation: function () {
        var userInfo = {
            firstName: this.refs.firstName.value,
            lastName: this.refs.lastName.value,
            email: this.refs.email.value,
            password: this.refs.password.value,
            currentCity: this.refs.city.value + ',' + this.refs.state.value
        };
        UserActions.createUser(userInfo);
    },
    render: function () {
        if (this.state.user === undefined || this.state.user.id === undefined) {
            return (
                <div>
                    <h2>Not a user? Register!</h2>
                    <div>

                        <div className="form-group">
                            <label className="control-label" htmlFor="firstName">First Name</label>
                            <input id="firstName" className="form-control" type="text" ref="firstName"/>
                        </div>
                        <div className="form-group">
                            <label className="control-label" htmlFor="lastName">Last Name</label>
                            <input id="lastName" className="form-control" type="text" ref="lastName"/>
                        </div>
                        <div className="form-group">
                            <label className="control-label" htmlFor="email">Email Address</label>
                            <input id="email" className="form-control" type="email" ref="email"/>
                        </div>
                        <div className="form-group">
                            <div className="col-sm-6">
                                <label className="control-label" htmlFor="city">City</label>
                                <div>
                                    <input id="city" className="form-control" type="text" ref="city"/>
                                </div>
                            </div>
                            <div className="col-sm-6">
                                <label className="control-label" htmlFor="state">State</label>
                                <div>
                                    <input id="state" className="form-control" type="text" ref="state"/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label" htmlFor="password">Password</label>
                            <input id="password" className="form-control" type="password" ref="password"/>
                        </div>
                        <div className="form-group">
                            <label className="control-label" htmlFor="password_verify">Re-enter
                                Password</label>
                            <input id="password_verify" className="form-control" type="password"
                                   ref="password_verify"/>
                        </div>
                        <input className="btn btn-primary" type="button" onClick={this.handleCreation}
                               value="Create Account"/>
                    </div>
                </div>
            )
        } else {
            return (
                <div><h2>Account successfully created!</h2></div>
            )
        }
    }
});