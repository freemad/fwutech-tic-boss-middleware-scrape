package com.fwutech.oss.middleware.adapters.fiberhometransmit.enums;

public enum ManagerType {
    EMS("EMS"),
    MANAGED_ELEMENT("ManagedElement"),
    EQUIPMENT_INVENTORY("EquipmentInventory"),
    PERFORMANCE_MANAGEMENT("PerformanceManagement"),
    MULTI_LAYER_SUBNETWORK("MultiLayerSubnetwork"),
    TC_PROFILE("TCProfile"),
    PROTECTION("Protection"),
    FLOW_DOMAIN("FlowDomain"),
    EXTENDED_MANAGED_ELEMENT("ExtendedManagedElement"),
    EXTENDED_EMS("ExtenedEMS");

    String type;

    ManagerType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
