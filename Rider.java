public class Rider extends User{
	private String name;
	private String phoneNumber;
	private Order currentOrder;
	ArrayList<Order> pastOrders = new ArrayList<>();
	private int queuePos;
	private int deliveryCount;
	public Rider(){}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getPhoneNum(){
		return phoneNumber;
	}
	
	public void setPhoneNum(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNum(){
		return phoneNumber;
	}
	
	public void setPhoneNum(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	public Order getCurrentOrder(){
		return currentOrder;
	}
	
	public void setCurrentOrder(Order currentOrder){
		this.currentOrder = currentOrder;
	}
	
	public ArrayList<Order> getPastOrders(){
		return pastOrders;
	}
	
	public void setPastOrders(ArrayList<Order> pastOrders){
		this.pastOrders = pastOrders;
	}
	
	public int getQueuePos(){
		return queuePos;
	}
	
	public void setQueuePos(int queuePos){
		this.queuePos = queuePos;
	}
	
	public int getDeliveryCount(){
		return deliveryCount;
	}
	
	public void setDeliveryCount(int deliveryCount){
		this.deliveryCount = deliveryCount;
	}
	
	public  boolean validateLogin(String userAttempt, String passwordAttempt){
		if(userAttempt.equals(getUsername()) && passwordAttempt.equals(getPassword()))
			return true;
		else
			return false;
	}
}