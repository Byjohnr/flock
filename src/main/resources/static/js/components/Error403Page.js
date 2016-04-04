var Error403Page = React.createClass({
    render: function() {
        return (
            <div className="col-sm-5 col-sm-offset-3">
                <h1 className="text-center">
                    ERROR: 403 - ACCESS DENIED
                </h1>
                <a href="home">
                <img src="https://pbs.twimg.com/media/CbSg8UkUYAE6Lht.jpg" alt="IDK" width="600" height="800"/>
                </a>
            </div>
        );
    }
});

ReactDOM.render(<Error403Page/>, document.getElementById('Error403Page'));