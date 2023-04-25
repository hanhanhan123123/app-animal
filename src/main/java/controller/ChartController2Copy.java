package controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.animal.AnimalResponse;
import util.AnimalAPI;


public class ChartController2Copy extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//데이터 전처리를 해주는 작업이 들어갈것이고 (3번 호출)
		String[] de = new String[] {"20230418", "20230417", "20230416", "20230415","20230414"};
		for(String d : de) {
			
		//string[]로 데이터 불러오기
		AnimalResponse response = AnimalAPI.getAnimals(null, null, null, d, d);
		
		int total = response.getBody().getTotalCount();
		System.out.println(total);
		}
		
		//처치결과를 setAttribute
		Map<String,Object>map = new LinkedHashMap<>();
		map.put("labels", List.of ("월","화","수","목","금"));
		map.put("datasets", List.of(Map.of("label","발생건수","data",List.of(33,11,5,1,9))));
			//label이 스트링, data가 배열
			Gson gson = new Gson();

			String mapJSON =gson.toJson(map);
			System.out.println(mapJSON);
			req.setAttribute("mapJSON", mapJSON);
			
			//뷰로 제어를 넘기는
		req.getRequestDispatcher("/WEB-INF/view/chart.jsp").forward(req, resp);
	}
}