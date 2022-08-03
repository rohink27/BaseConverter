package Servlets;

import java.io.IOException;

import CalculationClass.base_conversion;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String num= req.getParameter("number");
		int base= Integer.parseInt(req.getParameter("base"));
		int convert= Integer.parseInt(req.getParameter("conv"));
		
		base_conversion bc = new base_conversion (num,base,convert);
		if(!bc.check_validity())
			req.setAttribute("error", "invalid base or number or conversion. Base and conversion has to range between 2 and 36 inclusive");
		String result= bc.convert();
		req.setAttribute("result", result);
		req.setAttribute("number", num);
		req.setAttribute("base", base);
		req.setAttribute("conv", convert);
		RequestDispatcher rd= req.getRequestDispatcher("result.jsp");
		rd.forward(req, resp);
		
	}

}
