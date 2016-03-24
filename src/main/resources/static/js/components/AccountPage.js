var AccountPage = React.createClass({
   render: function() {
       return <ViewAccount account={true} />
   }
});
ReactDOM.render(<AccountPage />, document.getElementById('view_account'));