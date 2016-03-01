/**
 * Created by jeffrey on 2/27/16.
 */
var AccountPage = React.createClass({
   render: function() {
       return <ViewAccount account={true} />
   }
});
ReactDOM.render(<AccountPage />, document.getElementById('view_account'));