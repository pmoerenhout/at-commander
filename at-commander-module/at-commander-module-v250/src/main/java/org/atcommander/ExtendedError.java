package org.atcommander;

//the failure in the last unsuccessful call setup (originating or answering);
//the last call release;
//the last unsuccessful GPRS attach or unsuccessful PDP context activation;
//the last GPRS detach or PDP context deactivation.

public enum ExtendedError {
  NO_ERROR(0, "No error"),
  UNASSIGNED_NUMBER(1, "Unassigned (unallocated) number"),
  UNKNOWN_2(2, "Unknown (2)"),
  NO_ROUTE_TO_DESTINATION(3, "No route to destination"),
  CHANNEL_UNACCEPTABLE(6, "Channel unacceptable"),
  OPERATOR_DETERMINED_BARRING(8, "Operator determined barring"),
  NORMAL_CALL_CLEARING(16, "Normal call clearing"),
  USER_BUSY(17, "User busy"),
  NO_USER_RESPONDING(18, "No user responding"),
  USER_ALERTING_NO_ANSWER(19, "User alerting, no answer"),
  CALL_REJECTED(21, "Call rejected"),
  NUMBER_CHANGED(22, "Number changed"),
  NON_SELECTED_USER_CLEARING(26, "Non selected user clearing"),
  DESTINATION_OUT_OF_ORDER(27, "Destination out of order"),
  INVALID_NUMBER_FORMAT(28, "Invalid number format (incomplete number)"),
  FACILITY_REJECTED(29, "Facility rejected"),
  RESPONSE_TO_STATUS_ENQUIRY(30, "Response to STATUS ENQUIRY"),
  NORMAL_UNSPECIFIED(31, "Normal, unspecified"),
  NO_CIRCUIT_CHANNEL_AVAILABLE(34, "No circuit/channel available"),
  NETWORK_OUT_OF_ORDER(38, "Network out of order"),
  TEMPORARY_FAILURE(41, "Temporary failure"),
  SWITCHING_EQUIPMENT_CONGESTION(42, "Switching equipment congestion"),
  ACCESS_INFORMATION_DISCARDED(43, "Access information discarded"),
  REQUESTED_CIRCUIT_CHANNEL_NOT_AVAILABLE(44, "Requested circuit/channel not available"),
  RESOURCES_UNAVAILABLE_UNSPECIFIED(47, "Resources unavailable, unspecified"),
  QUALITY_OF_SERVICE_UNAVAILABLE(49, "Quality of service unavailable"),
  REQUESTED_FACILITY_NOT_SUBSCRIBED(50, "Requested facility not subscribed"),
  INCOMING_CALLS_BARRED_WITH_IN_THE_CUG(55, "Incoming calls barred with in the CUG"),
  BEARER_CAPABILITY_NOT_AUTHORIZED(57, "Bearer capability not authorized"),
  BEARER_CAPABILITY_NOT_PRESENTLY_AVAILABLE(58, "Bearer capability not presently available"),
  SERVICE_OR_OPTION_NOT_AVAILABLE_UNSPECIFIED(63, "Service or option not available, unspecified"),
  BEARER_SERVICE_NOT_IMPLEMENTED(65, "Bearer service not implemented"),
  ACM_EQUAL_TO_OR_GREATER_THAN_ACMMAX(68, "ACM equal to or greater than ACMmax"),
  REQUESTED_FACILITY_NOT_IMPLEMENTED(69, "Requested facility not implemented"),
  ONLY_RESTRICTED_DIGITAL_INFORMATION_BEARER_CAPABILITY_IS_AVAILABLE(70, "Only restricted digital information bearer capability is available"),
  SERVICE_OR_OPTION_NOT_IMPLEMENTED_UNSPECIFIED(79, "Service or option not implemented, unspecified"),
  INVALID_TRANSACTION_IDENTIFIER_VALUE(81, "Invalid transaction identifier value"),
  USER_NOT_MEMBER_OF_CUG(87, "User not member of CUG"),
  INCOMPATIBLE_DESTINATION(88, "Incompatible destination"),
  INVALID_TRANSIT_NETWORK_SELECTION(91, "Invalid transit network selection"),
  SEMANTICALLY_INCORRECT_MESSAGE(95, "Semantically incorrect message"),
  INVALID_MANDATORY_INFORMATION(96, "Invalid mandatory information"),
  MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED(97, "Message type non-existent or not implemented"),
  MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE(98, "Message type not compatible with protocol state"),
  INFORMATION_ELEMENT_NON_EXISTENT_OR_NOT_IMPLEMENTED(99, "Information element non-existent or not implemented"),
  CONDITIONAL_IE_ERROR(100, "Conditional IE error"),
  MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE(101, "Message not compatible with protocol state"),
  RECOVERY_ON_TIMER_EXPIRY(102, "Recovery on timer expiry"),
  PROTOCOL_ERROR_UNSPECIFIED(111, "Protocol error, unspecified"),
  INTERWORKING_UNSPECIFIED(127, "Interworking, unspecified"),
  /* GPRS related */
  MS_REQUESTED_DETACH(224, "MS requested detach"),
  NWK_REQUESTED_DETACH(225, "NWK requested detach"),
  UNSUCCESSFUL_ATTACH_CAUSE_NO_SERVICE(226, "Unsuccessful attach cause NO SERVICE"),
  UNSUCCESSFUL_ATTACH_CAUSE_NO_ACCESS(227, "Unsuccessful attach cause NO ACCESS"),
  UNSUCCESSFUL_ATTACH_CAUSE_GPRS_SERVICE_REFUSED(228, "Unsuccessful attach cause GPRS SERVICE REFUSED"),
  PDP_DEACTIVATION_REQUESTED_BY_NWK(229, "PDP deactivation requested by NWK"),
  PDP_DEACTIVATION_CAUSE_LLC_LINK_ACTIVATION_FAILED(230, "PDP deactivation cause LLC link activation failed"),
  PDP_DEACTIVATION_CAUSE_NWK_REACTIVATION_WITH_SAME_TI(231, "PDP deactivation cause NWK reactivation with same TI"),
  PDP_DEACTIVATION_CAUSE_GMM_ABORT(232, "PDP deactivation cause GMM abort"),
  PDP_DEACTIVATION_CAUSE_LLC_OR_SNDCP_FAILURE(233, "PDP deactivation cause LLC or SNDCP failure"),
  PDP_UNSUCCESSFUL_ACTIVATION_CAUSE_GMM_ERROR(234, "PDP unsuccessful activation cause GMM error"),
  PDP_UNSUCCESSFUL_ACTIVATION_CAUSE_NWK_REJECT(235, "PDP unsuccessful activation cause NWK reject"),
  PDP_UNSUCCESSFUL_ACTIVATION_CAUSE_NO_NSAPI_AVAILABLE(236, "PDP unsuccessful activation cause NO NSAPI available"),
  PDP_UNSUCCESSFUL_ACTIVATION_CAUSE_SM_REFUSE(237, "PDP unsuccessful activation cause SM refuse"),
  PDP_UNSUCCESSFUL_ACTIVATION_CAUSE_MMI_IGNORE(238, "PDP unsuccessful activation cause MMI ignore"),
  PDP_UNSUCCESSFUL_ACTIVATION_CAUSE_NB_MAX_SESSION_REACH(239, "PDP unsuccessful activation cause Nb Max Session Reach"),
  PDP_UNSUCCESSFUL_ACTIVATION_CAUSE_WRONG_APN(256, "PDP unsuccessful activation cause wrong APN"),
  PDP_UNSUCCESSFUL_ACTIVATION_CAUSE_UNKNOWN_PDP_ADDRESS_OR_TYPE(257, "PDP unsuccessful activation cause unknown PDP address or type"),
  PDP_UNSUCCESSFUL_ACTIVATION_CAUSE_SERVICE_NOT_SUPPORTED(258, "PDP unsuccessful activation cause service not supported"),
  PDP_UNSUCCESSFUL_ACTIVATION_CAUSE_QOS_NOT_ACCEPTED(259, "PDP unsuccessful activation cause QOS not accepted"),
  PDP_UNSUCCESSFUL_ACTIVATION_CAUSE_SOCKET_ERROR(260, "PDP unsuccessful activation cause socket error"),
  /* Other custom values */
  FDN_IS_ACTIVE_AND_NUMBER_NOT_IN_FDN(240, "FDN is active and number is not in FDN"),
  CALL_OPERATION_NOT_ALLOWED(241, "Call operation not allowed"),
  CALL_BARRING_ON_OUTGOING_CALLS(252, "Call barring on outgoing calls"),
  CALL_BARRING_ON_INCOMING_CALLS(253, "Call barring on incoming calls"),
  CALL_IMPOSSIBLE(254, "Call impossible"),
  LOWER_LAYER_FAILURE(255, "Lower layer failure");

  private final int value;
  private final String diagnostic;

  ExtendedError(int value, String diagnostic) {
    this.value = value;
    this.diagnostic = diagnostic;
  }

  public static ExtendedError fromInt(final int extendedError) {
    for (ExtendedError s : ExtendedError.values()) {
      if (s.value() == extendedError) {
        return s;
      }
    }
    return null;
  }

  public int value() {
    return value;
  }

  public String diagnostic() {
    return diagnostic;
  }
}
