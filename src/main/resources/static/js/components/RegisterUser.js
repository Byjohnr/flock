var RegisterUser = React.createClass({
    mixins: [Reflux.connect(UserStore, 'user')],

    handleCreation: function () {
        var userInfo = {
            firstName: this.refs.firstName.value,
            lastName: this.refs.lastName.value,
            email: this.refs.email.value,
            password: this.refs.password.value
        };
        UserActions.createUser(userInfo);
    },
    render: function () {
        if (this.state.user === undefined || this.state.user.id === undefined) {
            return (
                <div>
                    <h2>Not a user? Register!</h2>
                    <div className="form-horizontal">

                        <div className="form-group">
                            <div>
                                <label className="col-sm-2 control-label" htmlFor="firstName">First Name</label>
                            </div>
                            <div className="col-sm-8">
                                <input id="firstName" className="form-control" type="text" ref="firstName"/>
                            </div>
                        </div>
                        <div className="form-group">
                            <div>
                                <label className="col-sm-2 control-label" htmlFor="lastName">Last Name</label>
                            </div>
                            <div className="col-sm-8">
                                <input id="lastName" className="form-control" type="text" ref="lastName"/>
                            </div>
                        </div>
                        <div className="form-group">
                            <div>
                                <label className="col-sm-2 control-label" htmlFor="email">Email Address</label>
                            </div>
                            <div className="col-sm-8">
                                <input id="email" className="form-control" type="email" ref="email"/>
                            </div>
                        </div>
                        <div className="form-group">
                            <div>
                                <label className="col-sm-2 control-label" htmlFor="password">Password</label>
                            </div>
                            <div className="col-sm-8">
                                <input id="password" className="form-control" type="password" ref="password"/>
                            </div>
                        </div>
                        <div className="form-group">
                            <div>
                                <label className="col-sm-2 control-label" htmlFor="password_verify">Re-enter
                                    Password</label>
                            </div>
                            <div className="col-sm-8">
                                <input id="password_verify" className="form-control" type="password"
                                       ref="password_verify"/>
                            </div>
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