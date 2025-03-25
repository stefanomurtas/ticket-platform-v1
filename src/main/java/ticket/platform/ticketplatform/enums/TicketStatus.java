package ticket.platform.ticketplatform.enums;

public enum TicketStatus {
    TO_DO("Da fare"),
    IN_PROGRESS("In corso"),
    COMPLETED("Completato");

    private final String displayName;

    TicketStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}