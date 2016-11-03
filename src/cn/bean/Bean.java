package cn.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;

import cn.weixin.Authorization;

public class Bean {
	public Bean() {
		super();
	}

	public String login(HttpServletRequest request, HttpServletResponse response)
			throws JSONException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("userid");

		String userid = null;
		if (obj != null) {
			userid = obj.toString();
			System.out.println(userid);
			return userid;
		}

		System.out.print(request.getParameter("code"));
		String s = request.getParameter("code");
		if (s == null || request.getParameter("code") == "") {
			;
		} else {
			Authorization auth = new Authorization(s);
			userid = auth.getOpenid();
			session.setAttribute("userid", userid);
			System.out.print(userid);
		}
		return userid;
	}
}
