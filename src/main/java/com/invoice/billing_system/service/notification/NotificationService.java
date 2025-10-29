package com.invoice.billing_system.service.notification;

import java.util.List;

import com.invoice.billing_system.model.notification.Notification;

public interface NotificationService {
    void createNotification(String userRole, String message, String recipientId);
    
    List<Notification> getNotificationsForUser(String recipientId, String userRole);

    // Count unread notifications for a specific recipient and role
    long getUnreadCountForUser(String recipientId, String userRole);

    // Mark a notification as read
    Notification markNotificationAsRead(Long notificationId);
    
    List<Notification> getPlantNotifications();

    // Count unread notifications for PLANT
    long getUnreadPlantNotificationCount();
    
    List<Notification> getUnreadVehicleDeptNotifications();
    long getUnreadVehicleDeptNotificationCount();
    void markVehicleDeptNotificationAsRead(Long id);
   
    
    

}

