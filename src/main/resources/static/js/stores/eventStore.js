/**
 * Created by jeffrey on 1/29/16.
 */
// TODO jeffreyh 1/29/16
var EventStore;
EventStore = Reflux.createStore({
    listenables: [EventActions],
    //eventList: [],
    init: function () {
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
    onListEvents: function () {
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
    addEvents: function (data) {
        this.trigger(data);
    },
    onCreateEvent: function (data) {
        console.log(data);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/create',
            dataType: 'text',
            type: 'POST',
            data: JSON.stringify(data),
            success: function (data) {
                window.location.replace(data);
                console.log("It worked?!?!?");
            },
            error: function () {
                console.log("error");
            },
            done: function () {
                console.log("done?");
            }
        });
    },
    onEvent: function () {
        var id = window.alert(href.substr(href.lastIndexOf('/') + 1));
        $.ajax({
            url: '/api/event/' + id,
            datatype: 'json',
            success: this.getEvent
        })
    },
    getEvent: function (data) {
        this.trigger(data);
    }


});