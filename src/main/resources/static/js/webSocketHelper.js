'use strict';

function register(registrations) {
    var socket = SockJS('/payroll');
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        registrations.forEach(function (registration) {
            stompClient.subscribe(registration.route, registration.callback);
        });
    });
}

module.exports.register = register;