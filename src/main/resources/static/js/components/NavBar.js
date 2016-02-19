var NavBar = React.createClass({
    getInitialState: function() {
        return {user : undefined};
    },
   render: function() {
       var authed = null;
       if(this.props.hideInfo === "true") {
           authed = (<div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                   <ul className="nav navbar-nav navbar-right">
                       <li className="dropdown">
                           <button className="btn btn-primary navbar-btn" data-toggle="modal" data-target="#signInModal">Sign in</button>
                       </li>
                   </ul>
                   <div className="modal fade" id="signInModal" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel">
                       <div className="modal-dialog" role="document">
                           <div className="modal-content">
                               <div className="modal-header">
                                   <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                   <h4 className="modal-title" id="myModalLabel">Sign in</h4>
                               </div>
                               <form action="/main" method="post">
                                   <div className="modal-body">
                                       <div className="form-group">
                                           <label htmlFor="email" className="control-label">Email:</label>
                                           <input type="text" className="form-control" id="email"  name="email"/>
                                       </div>
                                       <div className="form-group">
                                           <label htmlFor="message-text" className="control-label" >Password:</label>
                                           <input type="password" className="form-control" id="password" name="password"/>
                                       </div>

                                   </div>
                                   <div className="modal-footer">
                                       <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
                                       <input type="submit" className="btn btn-primary" value="Sign in"/>
                                   </div>
                               </form>
                           </div>
                       </div>
                   </div>
               </div>
           )
       } else {
           authed = (<div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
               <ul className="nav navbar-nav">
                   <li><a href="/create">Create Event</a></li>
                   <li><a href="#">Map</a></li>
               </ul>
               <form className="navbar-form navbar-left" role="search">
                   <div className="form-group">
                       <input type="text" className="form-control" placeholder="Search"/>
                   </div>
                   <button type="submit" className="btn btn-default">Submit</button>
               </form>
               <ul className="nav navbar-nav navbar-right">
                   <li className="dropdown">
                       <a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User's name goes here <span className="caret"></span></a>
                       <ul className="dropdown-menu">
                           <li><a href="#">Profile</a></li>
                           <li><a href="#">Notifications</a></li>
                           <li><a href="#">Settings</a></li>
                           <li role="separator" className="divider"/>
                           <li>
                               <form action="/logout">
                                   <input className="form-control btn btn-default btn-block" type="submit" value="Logout"/>
                               </form>
                           </li>
                       </ul>
                   </li>
               </ul>
           </div>);
       }
       return(
           <nav className="navbar navbar-inverse">
               <div className="container-fluid">
                   <div className="navbar-header">
                       <button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                           <span className="sr-only">Toggle navigation</span>
                           <span className="icon-bar"></span>
                           <span className="icon-bar"></span>
                           <span className="icon-bar"></span>
                       </button>
                       <a className="navbar-brand" href="/">flock</a>
                   </div>
                   {authed}
               </div>
           </nav>);
   }
});