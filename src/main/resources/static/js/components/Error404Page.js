var Error404Page = React.createClass({
    render: function() {
        return (
            <div className="col-sm-5 col-sm-offset-3">
                <h1 className="text-center">
                    ERROR: 404 - YOU BROKE THE INTERNET
                </h1>
                <a href="home">
                    <img src="https://pbs.twimg.com/media/CbSg8UkUYAE6Lht.jpg" alt="IDK" width="600" height="800"/>
                </a>
            </div>
        );
    }
});

ReactDOM.render(<Error404Page/>, document.getElementById('Error404Page'));