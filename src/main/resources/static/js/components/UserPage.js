/**
 * Created by jeffrey on 2/27/16.
 */
var UserPage = React.createClass({
    render: function() {
        return <ViewAccount account={false} />
    }
});
ReactDOM.render(<UserPage account="false" />, document.getElementById('user_info'));