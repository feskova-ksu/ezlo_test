package com.example.ezlotest.data.model

import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("PK_Device")
    val pkDevice: Int = 0,
    @SerializedName("MacAddress")
    val macAddress: String = "",
    @SerializedName("PK_DeviceType")
    val pkDeviceType: Int = 0,
    @SerializedName("PK_DeviceSubType")
    val pkDeviceSubType: Int = 0,
    @SerializedName("Firmware")
    val firmware: String = "",
    @SerializedName("Server_Device")
    val serverDevice: String = "",
    @SerializedName("Server_Event")
    val serverEvent: String = "",
    @SerializedName("Server_Account")
    val serverAccount: String = "",
    @SerializedName("InternalIP")
    val internalIP: String = "",
    @SerializedName("LastAliveReported")
    val lastAliveReported: String = "",
    @SerializedName("Platform")
    val platform: String = "",
)
fun Device.mapForDB(number:Int) = DeviceItem(
    pkDevice = this.pkDevice,
    title = "$TITLE_PREFIX $number",
    deviceInfo = this
)

const val TITLE_PREFIX ="Home Number"