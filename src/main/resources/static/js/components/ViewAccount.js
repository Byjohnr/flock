/**
 * Created by Thomas on 2/9/2016.
 */
var ViewAccount = React.createClass({
    render: function () {
        return (
            <div>
                <h1 className="text-center">
                    Firstname Lastname
                </h1>
                <h2 className="text-center">
                    email
                </h2>
                <h2 className="text-center">
                    description
                </h2>
                <h2 className="text-center">
                    current city
                </h2>
            </div>
        )
    }
});

ReactDOM.render(<ViewAccount />, document.getElementById('view_account'));