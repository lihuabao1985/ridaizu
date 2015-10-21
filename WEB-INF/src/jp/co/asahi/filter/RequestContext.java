package jp.co.asahi.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.asahi.common.MessageResource;

import org.apache.commons.lang3.math.NumberUtils;

public final class RequestContext {
	private RequestContext(){
	}
	private static final String ENCODING = "UTF-8";
	private static final ThreadLocal<RequestContext> CONTEXTS = new ThreadLocal<RequestContext>();

	private ServletContext context;
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, Cookie> cookies;
	private List<String> messages;
	private String responseContent;
	private boolean loginPageFlg,printFlg;

	public static RequestContext begin(ServletContext ctx, HttpServletRequest req, HttpServletResponse res) {
		RequestContext rc = new RequestContext();
		rc.context = ctx;
		rc.request = req;
		try {
			rc.request.setCharacterEncoding(ENCODING);
		} catch (UnsupportedEncodingException e) {}
		rc.response = res;
		rc.response.setCharacterEncoding(ENCODING);
		rc.response.setContentType("text/html;charset=utf-8");
		CONTEXTS.set(rc);
		return rc;
	}
	public static RequestContext current(){
		return CONTEXTS.get();
	}
	public void end() {
		this.context = null;
		this.request = null;
		this.response = null;
		this.session = null;
		this.cookies = null;
		this.messages = null;
		CONTEXTS.remove();
	}
	public String param(String name) {
		return request.getParameter(name);
	}

	public String param(String name,String defValue){
		String v = request.getParameter(name);
		return (v==null?defValue:v);
	}

	public long param(String name, long defValue) {
		return NumberUtils.toLong(param(name), defValue);
	}

	public int param(String name, int defValue) {
		return NumberUtils.toInt(param(name), defValue);
	}

	public byte param(String name, byte defValue) {
		return (byte)NumberUtils.toInt(param(name), defValue);
	}

	public String uri(){
		return request.getRequestURI();
	}

	public String contextPath(){
		return request.getContextPath();
	}

	public void redirect(String uri) throws IOException {
		response.sendRedirect(uri);
	}

	/**
	 * Returns a RequestDispatcher object that acts as a wrapper for the
	 * resource located at the given path. A RequestDispatcher object can be
	 * used to forward a request to the resource or to include the resource in a
	 * response. The resource can be dynamic or static. The pathname must begin
	 * with a "/" and is interpreted as relative to the current context root.
	 * Use getContext to obtain a RequestDispatcher for resources in foreign
	 * contexts. This method returns null if the ServletContext cannot return a
	 * RequestDispatcher.
	 *
	 * @param path
	 *            a String specifying the pathname to the resource
	 * @throws ServletException
	 * @throws IOException
	 */
	public void forward(String path) throws ServletException, IOException {
		RequestDispatcher rd = context.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	public ServletContext context() { return context; }
	public HttpSession session() {
		if(session == null) {
			session = request.getSession();
		}
		return session;
	}
	public void print(String html) throws IOException{
		PrintWriter print = response.getWriter();
		print.write(html);
		print.flush();
		print.close();
	}
	public Object getSession(String attr) {
		return session().getAttribute(attr);
	}
	public void setSession(String attr,Object value) {
		session().setAttribute(attr, value);
	}
	public void removeSession(String attr){
		session().removeAttribute(attr);
	}
	public void setAttribute(String key,Object value){
		request.setAttribute(key, value);
	}
	public Object getAttribute(String key){
		return request.getAttribute(key);
	}
	public HttpServletRequest request() { return request; }
	public HttpServletResponse response() { return response; }
	public Cookie getCookie(String name) {
		if(cookies == null){
			cookies = new HashMap<String, Cookie>();
			Cookie[] tempCookies = request.getCookies();
			if(cookies != null){
				for(Cookie ck : tempCookies) {
					cookies.put(ck.getName(), ck);
				}
			}
		}
		return cookies.get(name);
	}
	public void setCookie(String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	public void deleteCookie(String name) {
		setCookie(name, "", 0);
	}

	public String header(String name) {
		return request.getHeader(name);
	}

	public void header(String name, String value) {
		response.setHeader(name, value);
	}

	public void header(String name, int value) {
		response.setIntHeader(name, value);
	}

	public void header(String name, long value) {
		response.setDateHeader(name, value);
	}

	public List<String> getMessages(){
		return messages;
	}
	public void addMessage(String key){
		if(messages == null){
			messages = new ArrayList<String>();
		}
		messages.add(MessageResource.getString(key,request));
	}
	protected void addMessage(String key,Object ... objects){
		if(messages == null){
			messages = new ArrayList<String>();
		}
		messages.add(String.format(MessageResource.getString(key,request), objects));
	}
	public boolean isResponsePrintWrite() {
		return printFlg;
	}
	public void setPrintFlg(boolean printFlg) {
		this.printFlg = printFlg;
	}
	public String getResponseContent(){
		return responseContent;
	}
	public void setResponseContent(String responsePrintContent){
		this.responseContent = responsePrintContent;
	}
	public boolean isReturnToLoginPage() {
		return loginPageFlg;
	}
	public void toLoginPage(boolean loginPageFlg) {
		this.loginPageFlg = loginPageFlg;
	}
}
