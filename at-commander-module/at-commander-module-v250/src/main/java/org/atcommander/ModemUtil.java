package org.atcommander;

public class ModemUtil {

  public static String getErrorString(final Integer error) {
    final StringBuilder sb = new StringBuilder();
    if (error != null) {
      sb.append("(");
      sb.append(error);
      final ExtendedError extendedError = ExtendedError.fromInt(error);
      if (extendedError != null) {
        sb.append(": ");
        sb.append(extendedError.diagnostic());
      }
      sb.append(")");
    }
    return sb.toString();
  }

  public static String getNetworkErrorString(final Integer networkError) {
    final StringBuilder sb = new StringBuilder();
    if (networkError != null) {
      sb.append("(");
      sb.append(networkError);
      final RejectCause rejectCause = RejectCause.fromInt(networkError);
      if (rejectCause != null) {
        sb.append(": ");
        sb.append(rejectCause.diagnostic());
      }
      sb.append(")");
    }
    return sb.toString();
  }
}
