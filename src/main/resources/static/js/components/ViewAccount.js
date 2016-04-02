var UserPage = React.createClass({
    render: function() {
        return <ViewAccount account={false} />
    }
});
ReactDOM.render(<UserPage account="false" />, document.getElementById('admin_user'));