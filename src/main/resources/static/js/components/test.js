var React = require('react');
var TestStore = require('../stores/testStore');

var Hello = React.createClass({
    getInitialState: function() {
        this.props.list = TestStore.onFetchUsers();
    },
   render: function() {
       return <div> Hello {this.props.name}</div>;
   }
});

ReactDOM.render(<Hello name="BOBBY"/>,document.getElementById('react_div'));