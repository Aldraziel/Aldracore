package fr.aldraziel.aldracore.api.utils;

public enum AldraCommand {

    STATUS("status");

    private final String command;

    AldraCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    @Override
    public String toString() {
        return this.getCommand();
    }
}
