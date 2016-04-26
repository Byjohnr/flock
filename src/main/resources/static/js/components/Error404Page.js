var Error404Page = React.createClass({
    render: function () {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-12 text-center">
                        <h1>
                            ERROR: 404 - PAGE NOT FOUND
                        </h1>
                    </div>
                </div>
            </div>
        );
    }
});
ReactDOM.render(<Error404Page/>, document.getElementById('Error404Page'));