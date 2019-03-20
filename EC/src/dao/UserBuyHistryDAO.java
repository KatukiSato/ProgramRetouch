//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import base.DBManager;
//import beans.BuyDataBeans;
//
//public class UserBuyHistryDAO {
//
//	//ユーザデータ画面に映っている詳細情報を出す
//	public List<BuyDataBeans> History(String buyId)  throws SQLException {
//
//		Connection con = null;
//		PreparedStatement st = null;
//		List<BuyDataBeans> BuyDataBeansList = new ArrayList<BuyDataBeans>();
//
//
//		try {
//			con = DBManager.getConnection();
//
//			st = con.prepareStatement(
//					"SELECT * FROM t_buy"
//							+ " JOIN m_delivery_method"
//							+ " ON t_buy.delivery_method_id = m_delivery_method.id"
//							+ " WHERE t_buy.id = ?");
//			//					"select * from t_buy"
//			//							+"JOIN m_delivery_method"
//			//							+"on t_buy.delivery_method_id = m_delivery_method.id"
//			//							+" WHERE t_buy.id = ?");
//			st.setString(1,buyId);
//			ResultSet rs = st.executeQuery();
//
//			while (rs.next()) {
//				BuyDataBeans bdb = new BuyDataBeans();
//				bdb.setId(rs.getInt("id"));
//				bdb.setTotalPrice(rs.getInt("total_price"));
//				bdb.setBuyDate(rs.getTimestamp("create_date"));
//				bdb.setDelivertMethodId(rs.getInt("delivery_method_id"));
//				bdb.setUserId(rs.getInt("user_id"));
//				bdb.setDeliveryMethodPrice(rs.getInt("price"));
//				bdb.setDeliveryMethodName(rs.getString("name"));
//
//				BuyDataBeansList.add(bdb);
//			}
//
//
//
//			System.out.println("表示に成功しました。");
//
//			return BuyDataBeansList;
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//			throw new SQLException(e);
//		} finally {
//			if (con != null) {
//				con.close();
//			}
//		}
//
//	}
//
//	//購入した商品の詳細をだす。
//	public List<BuyDataBeans> ItemHistory(String buyId)  throws SQLException {
//
//		Connection con = null;
//		PreparedStatement st = null;
//		List<BuyDataBeans> ItemDataBeansList = new ArrayList<BuyDataBeans>();
//
//		try {
//			con = DBManager.getConnection();
//
//			st = con.prepareStatement(
//					"select "
//							+ " t_buy.create_date, m_delivery_method.name, t_buy.total_price, m_item.name,m_item.price"
//							+ " from t_buy"
//							+ " join t_buy_detail "
//							+ " on t_buy.id = t_buy_detail.buy_id"
//							+ " inner join m_delivery_method"
//							+ " on t_buy.delivery_method_id = m_delivery_method.id"
//							+ " inner join m_item"
//							+ " on t_buy_detail.item_id = m_item.id"
//							+ " WHERE t_buy.id= ?");
//
//			st.setString(1,buyId);
//			ResultSet rs = st.executeQuery();
//
//			while (rs.next()) {
//
//				BuyDataBeans bdb = new BuyDataBeans();
//				//				bdb.setId(rs.getInt("id"));
//				bdb.setTotalPrice(rs.getInt("total_price"));
//				bdb.setBuyDate(rs.getTimestamp("create_date"));
//				bdb.setItemName(rs.getString("m_item.name"));
//				bdb.setItemPrice(rs.getInt("m_item.price"));
//				bdb.setDeliveryMethodPrice(rs.getInt("price"));
//				bdb.setDeliveryMethodName(rs.getString("name"));
//
//				ItemDataBeansList.add(bdb);
//
//
//			}
//
//
//
//			System.out.println("表示に成功したんですよ！");
//
//			return ItemDataBeansList;
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//			throw new SQLException(e);
//		} finally {
//			if (con != null) {
//				con.close();
//			}
//		}
//
//	}
//}
