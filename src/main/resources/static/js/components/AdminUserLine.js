var AdminUserLine = React.createClass({
    makeNormalUser: function () {
        console.log("makeNormalUser hit");
        RoleActions.makeUserAuthenticationLevelUser(this.props.data.id);
    },
    makeAdminUser: function () {
        console.log("makeAdminUser hit");
        RoleActions.makeUserAuthenticationLevelAdmin(this.props.data.id);
    },
    render: function () {
        var displayedAuth = (<label>{this.props.authorityLevel}</label>);
        var changeAuth = "";
        if (this.props.authorityLevel === "Admin") {
            changeAuth = (
                <button className="btn btn-default" onClick={this.makeNormalUser}>Make Normal User</button>
            )
        }
        if (this.props.authorityLevel === "User") {
            changeAuth = (
                <button className="btn btn-default" onClick={this.makeAdminUser} >Make Admin</button>
            )
        }
        return (
            <div id={this.props.data.email}>
                <div className="well" style={{height: '75px'}}>
                    <div className="col-xs-3">
                        {this.props.data.email}
                    </div>
                    <div className="col-xs-3">
                        {this.props.data.firstName} {this.props.data.lastName}
                    </div>
                    <div className="col-xs-2">
                        {displayedAuth}
                    </div>
                    <div className="col-xs-2">
                        {changeAuth}
                    </div>
                    <div className="col-xs-2">
                        <a href={'/user/' + this.props.data.id} className="btn btn-default">Go To Profile</a>
                    </div>
                </div>
            </div>
        );
    }
});