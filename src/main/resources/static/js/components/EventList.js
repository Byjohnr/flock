/**
 * Created by jeffrey on 1/28/16.
 */
//var React = require('react');

var EventList = React.createClass({
   render: function() {
       return <div><h3>Current Events!</h3></div>
   }
});

ReactDOM.render(<EventList/>, document.getElementById('event_div'));