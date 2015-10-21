package jp.co.asahi.util;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class RequestUtil {

    private static final Logger logger = Logger
            .getLogger(RequestUtil.class);

    public static Boolean getBoolean(HttpServletRequest _request, String _key) {

        return Boolean.parseBoolean(getString(_request, _key));

    }

    public static boolean getBoolean(HttpServletRequest _request, String _key,
            String _match) {

        String value = _request.getParameter(_key);

        return _match == null ? value == null : _match.equals(value);
    }

    public static int getInt(HttpServletRequest _request, String _key) {
        return getInt(_request, _key, 0);
    }

    public static int getInt(HttpServletRequest _request, String _key, int _alt) {
        int value = _alt;

        try {

            value = Integer.parseInt(getString(_request, _key));

        } catch (NumberFormatException e) {

            if (_request.getParameter(_key) != null
                    && !_request.getParameter(_key).isEmpty())
                logger.warn(String.format(
                        "invalid integer value for key '%s': %s", _key,
                        _request.getParameter(_key)), e);

        } catch (Exception e) {

            logger.warn("", e);

        }

        return value;
    }

    public static String getString(HttpServletRequest _request, String _key) {
        return getString(_request, _key, null);
    }

    public static String getString(HttpServletRequest _request, String _key,
            String _alt) {
        String value = null;

        try {

            if (_request != null)
                value = _request.getParameter(_key);

            if (value != null)
                value = value.trim();

        } catch (Exception e) {

            logger.warn("", e);

        }

        if (value == null)
            value = _alt;

        return value;
    }

    public static Integer getInteger(HttpServletRequest _request, String _key,
            Integer _alt) {
        Integer value = _alt;

        try {

            if (_request.getParameter(_key) != null)
                value = Integer.valueOf(getString(_request, _key));

        } catch (NumberFormatException e) {

            if (_request.getParameter(_key) != null
                    && !_request.getParameter(_key).isEmpty())
                logger.warn(String.format(
                        "invalid integer value for key '%s': %s", _key,
                        _request.getParameter(_key)), e);

        } catch (Exception e) {

            logger.warn("", e);

        }

        return value;
    }

	public static Timestamp getTimestamp(HttpServletRequest _request,
			String _key) {
		return getTimestamp(_request, _key, DateUtil.getCurrentDateTime());
	}

	public static Timestamp getTimestamp(HttpServletRequest _request,
			String _key, Timestamp _alt) {
		Timestamp value = _alt;

		try {

			if (getString(_request, _key) != null)
				value = DateUtil.stringToTimestamp(DateUtil.SHORT_DATE_HYPHEN,
						getString(_request, _key));

		} catch (Exception e) {

			logger.warn("", e);

		}

		return value;
	}
}
