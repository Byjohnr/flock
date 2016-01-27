var React = require('react');
var TestStore = require('../stores/testStore');

var Hello = React.createClass({
    displayName: 'Hello',

    getInitialState: function () {
        this.props.list = TestStore.onFetchUsers();
    },
    render: function () {
        return React.createElement(
            'div',
            null,
            ' Hello ',
            this.props.name
        );
    }
});

ReactDOM.render(React.createElement(Hello, { name: 'BOBBY' }), document.getElementById('react_div'));
