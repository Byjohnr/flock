var NavBar = React.createClass({
    mixins: [Reflux.connect(NavStore, 'user'), Reflux.connect(SearchStore, 'searchResults'), Reflux.connect(UserStore, 'adminAuthentication')],
    getInitialState: function () {
        var auth = UserActions.getAdminAuthentication();
        return {
            user: undefined,
            searchResults: undefined,
            adminAuthentication: auth
        };
    },
    searchChange: function (query) {
        console.log(query.target.value);
        if (query.target.value == "") {
            this.setState({searchResults: undefined});
        } else {
            SearchActions.search(query.target.value);
        }

    },
    render: function () {
        var searchResults = "";
        var navbar = null;
        if (this.props.hideInfo === "true") {
            navbar = (<div className="collapse navbar-collapse navbar-static-top" id="bs-example-navbar-collapse-1">
                    <ul className="nav navbar-nav navbar-right">
                        <li className="dropdown">
                            <button className="btn btn-primary navbar-btn" data-toggle="modal"
                                    data-target="#signInModal">Sign in
                            </button>
                        </li>
                    </ul>
                    <div className="modal fade" id="signInModal" tabIndex="-1" role="dialog"
                         aria-labelledby="myModalLabel">
                        <div className="modal-dialog" role="document">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span></button>
                                    <h4 className="modal-title" id="myModalLabel">Sign in</h4>
                                </div>
                                <form action="/main" method="post">
                                    <div className="modal-body">
                                        <div className="form-group">
                                            <label htmlFor="email" className="control-label">Email:</label>
                                            <input type="text" className="form-control" id="email" name="email"/>
                                        </div>
                                        <div className="form-group">
                                            <label htmlFor="message-text" className="control-label">Password:</label>
                                            <input type="password" className="form-control" id="password"
                                                   name="password"/>
                                        </div>

                                    </div>
                                    <div className="modal-footer">
                                        <button type="button" className="btn btn-default" data-dismiss="modal">Close
                                        </button>
                                        <input type="submit" className="btn btn-primary" value="Sign in"/>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            )
        } else {
            var events = "";
            var users = "";
            var adminLinks = "";
            if (this.state.searchResults != undefined) {
                users = this.state.searchResults.users.map(function (user) {
                    return <a href={'/user/' + user.id} key={'user' + user.id}>
                        <li className="list-group-item">{user.firstName} {user.lastName}</li>
                    </a>
                });
                events = this.state.searchResults.events.map(function (event) {
                    return <a href={'/event/' + event.id} key={'event' + event.id}>
                        <li className="list-group-item"> {event.eventName} </li>
                    </a>
                });
            }
            if (this.state.user === undefined) {
                NavActions.getUserInfo();
            } else {
                if (this.state.searchResults != undefined) {
                    searchResults = (
                        <div className="panel panel-default col-md-offset-2 col-md-4"
                             style={{zIndex: '100', position : 'fixed', marginLeft: '26%', marginTop : '-2.1em'}}>
                            <div>
                                <div className="panel-body">
                                    <h4><strong>Users</strong></h4>
                                </div>
                                <ul className="list-group">
                                    {users}
                                </ul>
                                <div className="panel-body">
                                    <h4><strong>Events</strong></h4>
                                </div>
                                <ul className="list-group">
                                    {events}
                                </ul>
                            </div>
                        </div>
                    );
                }
                if (this.state.adminAuthentication !== false) {
                    adminLinks = (
                        <li><a href="/admin/user_list">Admin: Users</a></li>
                    );
                }
                navbar = (<div>
                    <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul className="nav navbar-nav">
                        <li><a href="/event/create">Create Event</a></li>
                        <li><a href="/map">Map</a></li>
                        <li><a href="/chat/list">My Chats</a></li>
                    </ul>
                    <form className="navbar-form navbar-left" role="search">
                        <div className="form-group">
                            <input type="text" className="form-control" onChange={this.searchChange} placeholder="Search"/>
                        </div>
                    </form>
                    <ul className="nav navbar-nav navbar-right">
                        <li className="dropdown">
                            <a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true"
                               aria-expanded="false">{this.state.user.firstName} {this.state.user.lastName} <span
                                className="caret"/></a>
                            <ul className="dropdown-menu">
                                <li><a href='/account'>My Profile</a></li>
                                <li><a href="#">Notifications</a></li>
                                <li><a href="#">Settings</a></li>
                                {adminLinks}
                                <li role="separator" className="divider"/>
                                <li>
                                    <form action="/logout">
                                        <input className="form-control btn btn-default btn-block" type="submit"
                                               value="Logout"/>
                                    </form>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
                );
            }
        }
        return (<div>
            <nav className="navbar navbar-inverse">
                <div className="container-fluid">
                    <div className="navbar-header">
                        <button type="button" className="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span className="sr-only">Toggle navigation</span>
                            <span className="icon-bar"/>
                            <span className="icon-bar"/>
                            <span className="icon-bar"/>
                        </button>
                        <a className="navbar-brand" href="/">flock</a>
                    </div>
                    {navbar}
                </div>
            </nav>
            {searchResults}
        </div>);
    }
});