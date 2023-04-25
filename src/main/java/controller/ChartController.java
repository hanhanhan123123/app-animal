package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.animal.AnimalResponse;
import util.AnimalAPI;

@WebServlet("/chart")
public class ChartController extends HttpServlet {
	//최근 5일의 유기동물 발생건수
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //변환하기 위해 만든 코드
		List<String> de = new ArrayList<>();
		for(int i=4; i>=0; i--) {
			Date target = new Date(System.currentTimeMillis() - 1000*60*60*24*i);//하루에 해당하는 밀리세컨드
			//현재에서 적절한 숫자를 빼가면 하루전, 이틀전, 사일전 
			String s = sdf.format(target);
			de.add(s);
		}
		Gson gson = new Gson();
		
		//이부분이 가장 중요 ( 왜 이작업을 해야하는지 알아야함)
		req.setAttribute("labels", gson.toJson(de));
		req.setAttribute("labelData", de);
		//labels counts 배열을 넣어놔서 chart.jsp에서 찍어줌
		List<Integer> counts = new ArrayList<>();
		for (String d : de) {
			AnimalResponse response = AnimalAPI.getAnimals(null, null, null, d, d);
			int total = response.getBody().getTotalCount();
			counts.add(total);
		}
		req.setAttribute("counts", gson.toJson(counts));
		req.setAttribute("countData", counts);
		//labels counts 배열을 넣어놔서 chart.jsp에서 찍어줌
		
		req.getRequestDispatcher("/WEB-INF/views/chart.jsp").forward(req, resp);

	}
}


