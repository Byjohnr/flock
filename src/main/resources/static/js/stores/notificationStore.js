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
    onFetchNotifications: function() {
        $.ajax({
            url: '/api/notifications',
            type: 'GET',
            dataType: 'json',
            success: this.addNotifications
        });
    },
    addNotifications: function(data) {
        this.trigger(data);
    }
    //ToDO allow users to delete notifications
});