var EventLine = React.createClass({
    expandWell : function(id) {
        if(id.endsWith('-small')) {
            $('#' + id).hide();
            $('#' + id.substring(0,id.length - 5) + 'large').show();
        } else {
            $('#' + id).hide();
            $('#' + id.substring(0,id.length - 5) + 'small').show();
        }
    console.log(id);
    },
    render: function() {
        //TODO jeffreyh, add picture when we get that setup, everything needs links to stuff
        return (
            <div id={this.props.data.eventId}>
                <div id={this.props.data.eventId + '-small'} className="well">
                    <div onClick={this.expandWell.bind(this,this.props.data.eventId + '-small')} ><i className="fa fa-arrows-alt" /></div>
                    <div>
                        <h3>{this.props.data.name} -- {this.props.data.startTime}</h3>
                    </div>
                    <div>
                        <span><i className="fa fa-comments" /> {this.props.data.numberOfComments}</span>
                        <span className="pull-right" title="# of People not going"> <i className="fa fa-times" /> {this.props.data.notAttending} </span>
                        <span className="pull-right" title="# of People unsure"> <i className="fa fa-question" /> {this.props.data.maybeAttending} &nbsp;&nbsp;&nbsp;</span>
                        <span className="pull-right"  title="# of people going" > <i className="fa fa-check"/> {this.props.data.attending} &nbsp;&nbsp;&nbsp;</span>
                    </div>
                </div>
                <div id={this.props.data.eventId + '-large'} className="well well-lg" hidden="hidden">
                    <div onClick={this.expandWell.bind(this,this.props.data.eventId + '-large')}><i className="fa fa-compress"/></div>
                    <h2 className="text-center">{this.props.data.name}</h2>
                    <h4 className="text-center">Created by: {this.props.data.creator.firstName} {this.props.data.creator.lastName}</h4>
                    <div className="row">
                        <div className="col-sm-5">
                            <div>
                                <strong>When: </strong> {this.props.data.startTime}
                            </div>
                            <div>
                                <strong>Where: </strong> {this.props.data.address}
                            </div>
                            <div>
                                <strong>Event Type: </strong> {this.props.data.type}
                            </div>
                        </div>
                        <div className="col-sm-5 col-sm-offset-2">
                            <h1>PICTURE</h1>
                        </div>
                    </div>
                    <div>
                        <strong>Description: </strong><p>{this.props.data.description}</p>
                    </div>
                    <div>
                        <span><i className="fa fa-comments" /> {this.props.data.numberOfComments}</span>
                        <span className="pull-right" title="# of People not going"> <i className="fa fa-times" /> {this.props.data.notAttending} </span>
                        <span className="pull-right" title="# of People unsure"> <i className="fa fa-question" /> {this.props.data.maybeAttending} &nbsp;&nbsp;&nbsp;</span>
                        <span className="pull-right"  title="# of people going" > <i className="fa fa-check"/> {this.props.data.attending} &nbsp;&nbsp;&nbsp;</span>
                    </div>
                </div>
            </div>
        );
    }
});