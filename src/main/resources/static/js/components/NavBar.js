var NavBar = React.createClass({
   render: function() {
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
                   <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
                                   <li><a href="#">Sign out</a></li>
                               </ul>
                           </li>
                       </ul>
                   </div>
               </div>
           </nav>);
       }
       });