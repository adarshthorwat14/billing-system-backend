package com.invoice.billing_system.service.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.billing_system.model.notification.Notification;
import com.invoice.billing_system.repository.notification.NotificationRepository;

import jakarta.transaction.Transactional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void createNotification(String userRole, String message, String recipientId) {
        Notification notification = new Notification();
        notification.setUserRole(userRole);
        notification.setMessage(message);
        notification.setRecipientId(recipientId);
        notificationRepository.save(notification);
    }

  
    

    @Override
    public Notification markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        return notificationRepository.save(notification);
    }

    
    
    @Override
    public List<Notification> getPlantNotifications() {
        return notificationRepository.findByUserRoleOrderByTimestampDesc("PLANT");
    }

    @Override
    public long getUnreadPlantNotificationCount() {
        return notificationRepository.countByUserRoleAndIsReadFalse("PLANT");
    }
    
	@Override
	public List<Notification> getNotificationsForUser(String recipientId, String userRole) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long getUnreadCountForUser(String recipientId, String userRole) {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
    public List<Notification> getUnreadVehicleDeptNotifications() {
        return notificationRepository.findByUserRoleAndIsReadFalse("VEHICLE_DEPT");
    }

    @Override
    public long getUnreadVehicleDeptNotificationCount() {
        return notificationRepository.countByUserRoleAndIsReadFalse("VEHICLE_DEPT");
    }

    @Override
    public void markVehicleDeptNotificationAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        if ("VEHICLE_DEPT".equals(notification.getUserRole())) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }
    }
}

