package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.QueryStringBuilder;

@WebServlet("/test")
public class TestController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		String upr_cd = "6210000";
		String upkind = "244000";
		String pageNo = "1";
		String bgnde = "20230410";
		String endde = null;

		// 쉽게 만들기 위해 Map 을 활용할 수 있다.
		Map<String, String> params = new HashMap<>();
		params.put("upr_cd", upr_cd);
		params.put("upkind", upkind);
		params.put("pageNo", pageNo);
		params.put("bgnde", bgnde);
		

		String query = QueryStringBuilder.build(params);
		System.out.println(query);
	}
}


