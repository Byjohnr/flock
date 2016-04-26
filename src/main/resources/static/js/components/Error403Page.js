var Error403Page = React.createClass({
    render: function () {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-12 text-center">
                        <h1>
                            ERROR: 403 - ACCESS DENIED
                        </h1>
                    </div>
                </div>
            </div>
        );
    }
});
ReactDOM.render(<Error403Page/>, document.getElementById('Error403Page'));