class ItemCompartorByPrice implements
      java.util.Comparator<Item> {
  @Override
  public int compare(Item t1, Item t2){ // sort items by price in descending order
		if(t1.getItemPrice() > t2.getItemPrice())
			return -1; // > greater than
		else if(t1.getItemPrice() == t2.getItemPrice())
			return 0; // ==
		else
			return 1; // <
	}
	
	// Collections.sort(arryList,new ItemCompartorByPrice()); : line needed
}