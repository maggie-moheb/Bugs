package models;

/**
 * Created by youmna on 12/1/15.
 */
public class Notification {
    String Source_ID;
    String Destination_ID;
    String Notifying;

    public String getSource_ID() {
        return Source_ID;
    }

    public void setSource_ID(String source_ID) {
        Source_ID = source_ID;
    }

    public String getDestination_ID() {
        return Destination_ID;
    }

    public void setDestination_ID(String destination_ID) {
        Destination_ID = destination_ID;
    }

    public String getNotifying() {
        return Notifying;
    }

    public void setNotifying(String notifying) {
        Notifying = notifying;
    }

    public Notification() {

    }

    public Notification(String source_ID, String destination_ID, String notifying) {

        Source_ID = source_ID;
        Destination_ID = destination_ID;
        Notifying = notifying;
    }
}
