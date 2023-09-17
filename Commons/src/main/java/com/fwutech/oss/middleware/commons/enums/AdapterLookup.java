package com.fwutech.oss.middleware.commons.enums;

public enum AdapterLookup {
    ADAPTER_UNKNOWN(VendorType.UNKNOWN, NetworkType.UNKNOWN, ProtocolType.UNKNOWN),
    FIBERHOME_TRANSMIT_CORBA(VendorType.FIBERHOME, NetworkType.TRANSMIT, ProtocolType.CORBA),
    HUAWEI_DATA_SOAP(VendorType.HUAWEI, NetworkType.DATA, ProtocolType.SOAP),
    FANA_TRANSMIT_CORBA(VendorType.FANA, NetworkType.TRANSMIT, ProtocolType.CORBA),
    HUAWEI_TRANSMIT_SOAP(VendorType.HUAWEI, NetworkType.TRANSMIT, ProtocolType.RPC),
    CISCO_DATA_REST(VendorType.CISCO, NetworkType.DATA, ProtocolType.REST),
    NCE_DATA_SOAP(VendorType.NCE, NetworkType.DATA, ProtocolType.SOAP),
    HUAWEI_SWITCH_REST(VendorType.HUAWEI, NetworkType.SWITCH, ProtocolType.REST),
    HUAWEI_EV_SWITCH_REST(VendorType.HUAWEI_EV, NetworkType.SWITCH, ProtocolType.REST),
    EWSD_SWITCH_REST(VendorType.EWSD, NetworkType.SWITCH, ProtocolType.REST),
    NEAX_SWITCH_REST(VendorType.NEAX, NetworkType.SWITCH, ProtocolType.REST),
    ZTE_SWITCH_REST(VendorType.ZTE, NetworkType.SWITCH, ProtocolType.REST);



    private VendorType vendorType;
    private NetworkType networkType;
    private ProtocolType protocolType;

    AdapterLookup(VendorType vendorType, NetworkType networkType, ProtocolType protocolType) {
        this.vendorType = vendorType;
        this.networkType = networkType;
        this.protocolType = protocolType;
    }

    public VendorType getVendorType() {
        return vendorType;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public static AdapterLookup getByVendorAndNetwork(VendorType vendorType, NetworkType networkType) {
        for (AdapterLookup adapterLookup : AdapterLookup.values()) {
            if (adapterLookup.vendorType.equals(vendorType) && adapterLookup.networkType.equals(networkType)) {
                return adapterLookup;
            }
        }
        return ADAPTER_UNKNOWN;
    }

    public static AdapterLookup getByVendorAndNetworkName(String vendorName, String networkName) {
        for (AdapterLookup adapterLookup : AdapterLookup.values()) {
            if (adapterLookup.vendorType.name().equals(vendorName)
                    && adapterLookup.networkType.name().equals(networkName)) {
                return adapterLookup;
            }
        }
        return ADAPTER_UNKNOWN;
    }

    public static AdapterLookup getByVendorAndNetworkId(Integer vendorId, Integer networkId) {
        for (AdapterLookup adapterLookup : AdapterLookup.values()) {
            if (adapterLookup.vendorType.getType().equals(vendorId)
                    && adapterLookup.networkType.getType().equals(networkId)) {
                return adapterLookup;
            }
        }
        return ADAPTER_UNKNOWN;
    }
}
