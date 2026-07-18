package com.springsecurity.Spring_Security.enums;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permission.ADD_ROOM,Permission.ROOM_VIEW,Permission.ROOM_VIEW_ALL)),
    STAFF(Set.of(Permission.ROOM_VIEW_ALL,Permission.ROOM_VIEW)),
    GUEST(Set.of(Permission.ROOM_VIEW));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
