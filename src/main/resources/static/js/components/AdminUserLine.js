var AdminUserLine = React.createClass({
    render: function () {
        var accountPageForUser = "/user/" + this.props.data.id;
        return (
            <div id={this.props.data.email}>
                <div className="well" style={{height: '75px'}}>
                    <div className="col-xs-2">
                        {this.props.data.email}
                    </div>
                    <div className="col-xs-2">
                        {this.props.data.firstName} {this.props.data.lastName}
                    </div>
                    <div className="col-xs-6">
                    </div>
                    <div className="col-xs-2">
                        <a href={accountPageForUser} className="btn btn-default">Go To Profile</a>
                    </div>
                </div>
            </div>
        );
    }
});