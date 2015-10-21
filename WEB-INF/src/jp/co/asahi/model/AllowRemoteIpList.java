package jp.co.asahi.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.asahi.config.Config;

import org.apache.log4j.Logger;

public class AllowRemoteIpList {

	private static class IpRangeFilter {

		private static final Pattern PATTERN = Pattern
				.compile("((?:\\d|\\.)+)(?:/(\\d{1,2}))?");

		/**
		 * Convert the bytes in the InetAddress into a bit mask stored as a
		 * long. We could use int's here, but java represents those in as signed
		 * numbers, which can be a pain when debugging.
		 *
		 * @see http://www.captain.at/howto-java-convert-binary-data.php
		 */
		private static long toMask(InetAddress address) {
			byte[] data = address.getAddress();
			long accum = 0;
			int idx = 3;
			for (int shiftBy = 0; shiftBy < 32; shiftBy += 8) {
				accum |= ((long) (data[idx] & 0xff)) << shiftBy;
				idx--;
			}
			return accum;
		}

		private final long netmask;
		private final long network;

		public IpRangeFilter(String ipRange) throws UnknownHostException {
			Matcher matcher = PATTERN.matcher(ipRange);
			if (matcher.matches()) {
				String networkPart = matcher.group(1);
				String cidrPart = matcher.group(2);

				long netmask = 0;
				int cidr = cidrPart == null ? 32 : Integer.parseInt(cidrPart);
				for (int pos = 0; pos < 32; ++pos) {
					if (pos >= 32 - cidr) {
						netmask |= (1L << pos);
					}
				}

				this.network =
						netmask & toMask(InetAddress.getByName(networkPart));
				this.netmask = netmask;

			} else {
				throw new IllegalArgumentException("Not a valid IP range: "
						+ ipRange);
			}
		}

		public boolean isInRange(InetAddress address) {
			return network == (toMask(address) & netmask);
		}
	}

	protected static Logger systemLogger = Logger.getLogger("system");

	/**
	 * IPアドレスの表現をバイト配列に変換
	 *
	 * @param ip
	 * @return
	 */
	private static byte[] ipToByteArray(String ip) {

		String[] ips = ip.split("\\.");
		byte[] result = new byte[ips.length];
		for (int i = 0; i < ips.length; i++) {
			result[i] = (byte) Integer.parseInt(ips[i]);
		}

		return result;

	}

	public static void main(String[] _arg) {

		try {

			List<String> rawList = Arrays.asList(Config.getString("ALLOW_ACCESS_IP_LIST").split(","));
			System.out.println(String.format("IP list: %s", rawList));

			AllowRemoteIpList list = new AllowRemoteIpList(rawList);

			for (String ip : Arrays.asList("192.168.11.1", "192.168.11.2",
					"192.168.11.254", "192.168.11.255", "192.168.11.256",
					"192.168.12.257", "123.220.225.54", "123.220.225.53", "",
					"fdsafds")) {

				System.out.println(String.format("check ip '%s': %s", ip,
						list.isAllow(ip)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private final Collection<IpRangeFilter> ipRangeFilters =
			new ArrayList<IpRangeFilter>();

	public AllowRemoteIpList(Collection<String> ipRanges) {

		for (String ipAddress : ipRanges) {

			try {
				if (ipAddress != null)
					ipRangeFilters.add(new IpRangeFilter(ipAddress));
			} catch (Exception e) {
				systemLogger.warn("[" + ipAddress
						+ "]is not IP or Network Address.");
			}

		}

	}

	public boolean isAllow(String ip) {

		try {

			byte[] addr = ipToByteArray(ip);
			InetAddress address = InetAddress.getByAddress(addr);
			for (IpRangeFilter ipFilter : ipRangeFilters) {
				if (ipFilter.isInRange(address))
					return true;
			}
			return false;

		} catch (Exception e) {
			systemLogger.debug("[" + ip + "] invalid IP.");
		}

		return false;
	}

}
