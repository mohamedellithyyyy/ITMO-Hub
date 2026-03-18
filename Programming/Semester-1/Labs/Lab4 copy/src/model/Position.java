package model;

import enums.LocationType;

public record Position(int x, int y, LocationType type) {
    @Override
    public String toString() {
        return "Позиция{x=" + x + ", y=" + y + ", тип=" + type + "}";
    }
}