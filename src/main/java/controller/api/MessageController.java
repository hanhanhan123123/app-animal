package controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import data.vo.Message;
import repoistory.MessagesDAO;
/*
 * desertionNo에 해당하는 메세지를 찾아서 json으로 응답보내는 프론트 협업용 컨트롤러 만들고
   새로고침 눌렀을때  AJAX로 위에 만든 백엔드와 AJAX 통신하는 스크립트 짜서 데이터 받아오면 
   html 만들어서 메세지 찍어주던 div 영역에  innerHTML로 설정
 */
@WebServlet("/api/message")
public class MessageController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String no = req.getParameter("no");
        List<Message> li = MessagesDAO.readMessages(no);
        
       
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("result", true);
        map.put("items", li);
    	map.put("total", li.size());
        Gson gson = new Gson();
        
        out.print(gson.toJson(map));
        
    }
}