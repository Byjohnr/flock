package cs309.controller;

import cs309.service.EventService;
import cs309.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotificationController {

        @Autowired
        private NotificationService notificationService;

        @RequestMapping("/notifications")
        public String notificationsPage() {
            return "notificationsPage";
        }
}
