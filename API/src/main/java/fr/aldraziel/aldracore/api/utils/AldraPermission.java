package fr.aldraziel.aldracore.api.utils;

public enum AldraPermission {

    STATUS("status");

    private static final String BASE = "aldracore.";
    private final String permission;

    AldraPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return BASE + this.permission;
    }

    @Override
    public String toString() {
        return this.getPermission();
    }
}
