/**
 * Created by jeffrey on 1/28/16.
 */
var EventList = React.createClass({
    getInitialState() {

    },
    render: function() {
       return <div><h3>Current Events!</h3>
           {this.props.data}
       </div>
   }
});

ReactDOM.render(<EventList data='bloop' />, document.getElementById('event_div'));