var SearchStore = Reflux.createStore({
   listenables : [SearchActions],
    onSearch : function(query) {
        if(query == undefined || query == "") {
            query = " ";
        }
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url : '/api/home/search',
            type: 'POST',
            data : query,
            dataType: 'application/json',
            success: this.triggerState
        });
    },
    triggerState : function() {
        console.log("teeheeee");
    }


});