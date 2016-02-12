/**
 * Created by John on 2/11/2016.
 */

var NotificationStore = Reflux.createStore({
    listenables: [NotificationActions],
    init : function() {
        console.log('Init');
    },
    getInitialState() {
        console.log('Initial State');
    },
    onListNotifications: function() {
        $.ajax({
            url: '/api/notifications',
            dataType: 'json',
            success: this.addNotifications
        });
    },
    addNotifications: function(data) {
        this.notifications = data;
        this.trigger(this.notifications);
    },
    onCreateNotification: function(data) {
        console.log(data);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/notifications',
            dataType:'text',
            type: 'POST',
            data: JSON.stringify(data),
            success: function(data) {
                window.location.replace(data);
                console.log("Hope something happens");
            },
            error : function () {
                console.log("error");
            },
            done : function() {
                console.log("sure");
            }
        });

    }
});