package luminac.ui.notifications;

import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationManager {
  private static CopyOnWriteArrayList<Notification> pendingNotifications = new CopyOnWriteArrayList<>();
  
  private static Notification currentNotification = null;
  
  public Notification lastNotif = null;
  
  public Notification getLastNotif() {
    return this.lastNotif;
  }
  
  public CopyOnWriteArrayList<Notification> getNotifications() {
    return pendingNotifications;
  }
  
  public void setLastNotif(Notification lastNotif) {
    this.lastNotif = lastNotif;
  }
  
  public static void show(NotificationType type, String name, String text, int length) {
    Notification newNotification = new Notification(type, name, text, length);
    pendingNotifications.add(newNotification);
    newNotification.show();
  }
  
  public void removeFromList(int index) {
    pendingNotifications.remove(index);
  }
  
  public void removeFromList(Notification object) {
    pendingNotifications.remove(object);
  }
  
  public int getIndex(Notification notification) {
    return pendingNotifications.indexOf(notification);
  }
  
  public Notification getObject(int index) {
    return pendingNotifications.get(index);
  }
  
  public static void notificationUpdate() {
    CopyOnWriteArrayList<Notification> notificationList = pendingNotifications;
    for (Notification notification : notificationList)
      notification.resetOffset(); 
    for (Notification notification : notificationList)
      notification.updateOffset(); 
    for (Notification notification : notificationList)
      notification.render(); 
  }
}
