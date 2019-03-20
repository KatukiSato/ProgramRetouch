package ec;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import dao.UserBuyHistryDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//以下記述した所
		HttpSession session = request.getSession();
		try {

			String buyId = request.getParameter("buy_id");
			UserBuyHistryDAO bdb = new UserBuyHistryDAO();
			List<BuyDataBeans> bdbList =  bdb.History(buyId);
			session.setAttribute("buyIDBListHistory", bdbList);

			String ItemId = request.getParameter("itemid");
			List<BuyDataBeans> bdbItemList =  bdb.ItemHistory(ItemId);

//			ArrayList<BuyDataBeans> cartIDBList = (ArrayList<BuyDataBeans>) bdbItemList;
			session.setAttribute("buyIDBListItemHistory", bdbItemList);

//			int inputDeliveryMethodId = Integer.parseInt(request.getParameter("buy_id"));
//			DeliveryMethodDataBeans userSelectDMB = DeliveryMethodDAO.getDeliveryMethodDataBeansByID(inputDeliveryMethodId);

//
//			int totalPrice = EcHelper.getTotalItemPrice(cartIDBList);
//
//			BuyDataBeans Bdb = new BuyDataBeans();
//			Bdb.setUserId((int) session.getAttribute("userId"));
//			Bdb.setTotalPrice(totalPrice+userSelectDMB.getPrice());
//			Bdb.setDelivertMethodId(userSelectDMB.getId());
//			Bdb.setDeliveryMethodName(userSelectDMB.getName());
//			Bdb.setDeliveryMethodPrice(userSelectDMB.getPrice());
//
//
//
//			//購入確定で利用
//			session.setAttribute("bdb", Bdb);

			request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}


	}
}
