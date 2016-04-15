var AdminUserLine = React.createClass({
    render: function () {
        var displayedAuth = (<label>{this.props.authorityLevel}</label>);
        return (
            <div id={this.props.data.email}>
                <div className="well" style={{height: '75px'}}>
                    <div className="col-xs-2">
                        {this.props.data.email}
                    </div>
                    <div className="col-xs-2">
                        {this.props.data.firstName} {this.props.data.lastName}
                    </div>
                    <div className="col-xs-1">
                    </div>
                    <div className="col-xs-4">
                        {displayedAuth}
                    </div>
                    <div className="col-xs-1">
                    </div>
                    <div className="col-xs-2">
                        <a href={'/user/' + this.props.data.id} className="btn btn-default">Go To Profile</a>
                    </div>
                </div>
            </div>
        );
    }
});