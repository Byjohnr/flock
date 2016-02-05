/**
 * Created by jeffrey on 1/29/16.
 */
// TODO jeffreyh 1/29/16
var EventStore = Reflux.createStore({
    listenables: [EventActions],
    //eventList: [],
    init : function() {
        console.log('Init');
        //this.events = undefined;
        //return {
        //    events : this.events
        //};
    },
    getInitialState() {
        //this.onListEvents();
        console.log('Initial State');
        //this.events = undefined;
        //return {
        //    events : this.events
        //};
    },
    onListEvents: function() {
        $.ajax({
            url: '/api/events',
            dataType: 'json',
            success: this.addEvents
            //function(data) {
            //alert(data);
            //console.log('fetch complete');
            //this.addEvents(data);
            //this.trigger({ events :this.events});
            //return this.eventList;
            //}
        });
        //this.trigger({ events :this.events});
        //console.log(this.events);
        //return this.eventList;
    },
    addEvents: function(data) {
        this.events = data;
        this.trigger(this.events);
    },
    onCreateEvent: function(data) {

    }
});