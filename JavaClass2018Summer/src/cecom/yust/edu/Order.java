package cecom.yust.edu;

public class Order {
	int tableNumber;
	String orderedMenu;
	int menuPrice;
	int orderedNumber;

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getOrderedMenu() {
		return orderedMenu;
	}

	public void setOrderedMenu(String orderedMenu) {
		this.orderedMenu = orderedMenu;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public int getOrderedNumber() {
		return orderedNumber;
	}

	public void setOrderedNumber(int orderedNumber) {
		this.orderedNumber = orderedNumber;
	}

	@Override
	public String toString() {
		return "Order [tableNumber=" + tableNumber + ", orderedMenu=" + orderedMenu + ", menuPrice=" + menuPrice
				+ ", orderedNumber=" + orderedNumber + ", totalPrice=" + menuPrice*orderedNumber + "]";
	}

//	public void printOrder() {
//		System.out.printf("Table: %d\t Menu: %s\t Number: %d\t Price:%d\t Pay:%d\n", tableNumber, orderedMenu, orderedNumber, menuPrice,
//				menuPrice*orderedNumber);
//	} 

}
