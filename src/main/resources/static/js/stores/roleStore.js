var RoleStore = Reflux.createStore({
    listenables: [RoleActions],
    init : function() {
        console.log('Init');
    },
    getInitialState() {
        console.log('Initial State');
    },
    onIsEventAdmin() {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            url: '/api/event/isEventAdmin/' + id,
            type: 'GET',
            dataType: 'text',
            success: this.pushRole
        })
    },
    pushRole (data) {
        this.trigger(data);
    }
});