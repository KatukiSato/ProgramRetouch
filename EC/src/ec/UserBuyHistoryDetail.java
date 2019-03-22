package ec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.ItemDataBeans;
import dao.BuyDetailDAO;

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

			int buyId = Integer.parseInt( request.getParameter("buy_id"));
			BuyDetailDAO bdb = new BuyDetailDAO();
			List<BuyDataBeans> bdbList =  bdb.History(buyId);
			session.setAttribute("buyIDBListHistory", bdbList);

			ArrayList<ItemDataBeans> bdbItemList =  BuyDetailDAO.getItemDataBeansListByBuyId(buyId);

			session.setAttribute("buyIDBListItemHistory", bdbItemList);

			ArrayList<BuyDataBeans> bdbDeliveryList =  (ArrayList<BuyDataBeans>) bdb.ItemHistory(buyId);
			session.setAttribute("buyIDBListDeliveryHistory", bdbDeliveryList);

			request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}


	}
}
