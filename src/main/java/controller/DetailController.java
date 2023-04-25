package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.address.AddressDocument;
import data.animal.AnimalItem;
import data.vo.Message;
import repoistory.MessagesDAO;
import util.AddressAPI;
import util.AnimalAPI;

@WebServlet("/detail")
public class DetailController extends HttpServlet {
//사용자가 보고있는 동물을 클릭할때 번호를 가지고 넘어오도록
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no =req.getParameter("no");
		//파라미터가 전달되면
		
		AnimalItem item  = AnimalAPI.findByDesertionNo(no); 
		//no로 받고
		
		//AnimalAPI 이걸 가지고 데이터를 찾아온다.
		
		// AnimalItem은 vo!!
		if(item == null) {//동물정보가 비어있다면 ↓ 여기로 이동
			req.getRequestDispatcher("/WEB-INF/views/not-found.jsp").forward(req, resp);	
		}else {
			
			
			List<Message> li = MessagesDAO.readMessages(no);//메세지찾아준다.
			//dao이용해서 자료 가져온다.
			
			AddressDocument doc =AddressAPI.getAddress(item.getHappenPlace());
			//특정 장소의 주소를 가져오기 위해 AddressAPI를 사용하는 것으로 보입니다. 
			//가져올 장소는 item.getHappenPlace() 메소드의 반환 값으로 지정
			
			System.out.println(doc);
			
			req.setAttribute("item", item);
			req.setAttribute("messages", li);
			req.setAttribute("address", doc);
			req.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(req, resp);
		}
	}
}


