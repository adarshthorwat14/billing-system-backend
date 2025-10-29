package com.invoice.billing_system.controller.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.billing_system.model.notification.Notification;
import com.invoice.billing_system.repository.notification.NotificationRepository;
import com.invoice.billing_system.service.notification.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationRepository notificationRepository;
    
    @PostMapping("/create")
    public ResponseEntity<Void> createNotification(@RequestParam String userRole, @RequestParam String message, @RequestParam String requestId) {
        notificationService.createNotification(userRole, message, requestId);
        return ResponseEntity.ok().build();
    }

   

    @PutMapping("/mark-read/{id}")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
        return ResponseEntity.ok().build();
    }

    
    @GetMapping("/count")
    public ResponseEntity<Long> getUnreadNotificationCount(
            @RequestParam String recipientId,
            @RequestParam String userRole) {
        long count = notificationRepository.countByRecipientIdAndUserRoleAndIsReadFalse(recipientId, userRole);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotifications(
            @RequestParam String recipientId,
            @RequestParam String userRole) {
        return ResponseEntity.ok(
            notificationRepository.findByRecipientIdAndUserRoleOrderByTimestampDesc(recipientId, userRole));
    }
    
 // Get all plant notifications
    @GetMapping("/plant")
    public List<Notification> getPlantNotifications() {
        return notificationService.getPlantNotifications();
    }

    // Get count of unread plant notifications
    @GetMapping("/plant/count")
    public long getUnreadPlantNotificationCount() {
        return notificationService.getUnreadPlantNotificationCount();
    }

   
 // Get unread VEHICLE_DEPT notifications
    @GetMapping("/vehicle/approve/unread")
    public List<Notification> getUnreadVehicleDeptNotifications() {
        return notificationService.getUnreadVehicleDeptNotifications();
    }

    // Count unread VEHICLE_DEPT notifications
    @GetMapping("/vehicle/approve/count")
    public long getUnreadVehicleDeptNotificationCount() {
        return notificationService.getUnreadVehicleDeptNotificationCount();
    }

    // Mark VEHICLE_DEPT notification as read
    @PutMapping("/vehicle/approve/mark-read/{id}")
    public ResponseEntity<Void> markVehicleDeptNotificationAsRead(@PathVariable Long id) {
        notificationService.markVehicleDeptNotificationAsRead(id);
        return ResponseEntity.ok().build();
    }

}

