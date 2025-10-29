package com.invoice.billing_system.repository.notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.billing_system.model.notification.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	 long countByRecipientIdAndUserRoleAndIsReadFalse(String recipientId, String userRole);

	 List<Notification> findByRecipientIdAndUserRoleOrderByTimestampDesc(String recipientId, String userRole);
	 
	// Fetch all notifications for PLANT role
	    List<Notification> findByUserRoleOrderByTimestampDesc(String userRole);

	    // Count unread notifications for PLANT role
	    long countByUserRoleAndIsReadFalse(String userRole);
	    
	 // Fetch all VEHICLE_DEPT notifications
	    List<Notification> findByUserRole(String userRole);
	    
	    // Fetch unread VEHICLE_DEPT notifications
	    List<Notification> findByUserRoleAndIsReadFalse(String userRole);
}

