package com.pelf.util;

public class TransactionCode {
	//Broadcast Related
    public static short Broadcast_Request_Response = 5001;
    public static short Request_Stop_Broadcast = 92;
    public static short Tick_Data_Request = 28;
    public static short Tick_Data_Response = 29;
    
    //Interactive Related
    public static short Login_Request_Response = 1;
    public static short General_Erro_Response = 97;
    public static short Logoff_Request_Response = 61;
    public static short Change_Pwd = 38;
    public static short Change_Trn_Pwd = 438;
    public static short Order_Error = 99;
    public static short Order_Place_Request = 100;
    public static short Order_Modify_Request = 110;
    public static short Order_Cancel_Request = 120;
    public static short Order_Broker_Received = 101;
    public static short Order_Modify_Broker_Received = 111;
    public static short Order_Cancel_Broker_Received = 121;
    public static short Order_RMS_Processed = 102;
    public static short Order_Modify_RMS_Processed = 112;
    public static short Order_Cancel_RMS_Processed = 122;
    public static short Order_Sent_To_Exchange = 1103;
    public static short Order_Received_By_Exchange = 1104;
    public static short Exchange_Confirmation = 1105;
    public static short Exchange_Reject = 1106;
    public static short Exchange_Freeze = 1107;
    public static short Exchange_Killed = 1108;
    public static short Trade_Confirmation = 2222;
    public static short Stoploss_Triggerred = 2212;
    public static short Master_Download_Request = 500;
    public static short Cash_Segment_Master_Response = 501;
    public static short FO_Master_Response_Summary = 502;
    public static short FO_Master_Response_Details = 503;
    public static short FO_Master_Download_Complete = 504;
    public static short Order_request_response = 11;
}
